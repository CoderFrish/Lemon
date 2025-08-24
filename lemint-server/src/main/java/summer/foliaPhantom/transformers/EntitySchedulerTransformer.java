package summer.foliaPhantom.transformers;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;
import summer.foliaPhantom.patchers.FoliaPatcher;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EntitySchedulerTransformer implements ClassTransformer {

    private final Logger logger;

    public EntitySchedulerTransformer(Logger logger) {
        this.logger = logger;
    }

    @Override
    public byte[] transform(byte[] originalBytes) {
        String className = "Unknown";
        try {
            ClassReader cr = new ClassReader(originalBytes);
            className = cr.getClassName();
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            ClassVisitor cv = new EntitySchedulerClassVisitor(cw);
            cr.accept(cv, ClassReader.EXPAND_FRAMES);
            return cw.toByteArray();
        } catch (Exception e) {
            logger.log(Level.WARNING, "[Phantom-extra] Failed to transform class " + className + " for EntityScheduler. Returning original bytes.", e);
            return originalBytes;
        }
    }

    private static class EntitySchedulerClassVisitor extends ClassVisitor {
        public EntitySchedulerClassVisitor(ClassVisitor cv) {
            super(Opcodes.ASM9, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
            Type[] argTypes = Type.getArgumentTypes(desc);

            // Check for a typical event handler signature: public void methodName(SomeEvent event)
            // And ensure it's a public, non-static method
            if ((access & Opcodes.ACC_PUBLIC) != 0 && (access & Opcodes.ACC_STATIC) == 0 &&
                argTypes.length == 1 && Type.getReturnType(desc).equals(Type.VOID_TYPE)) {

                String eventType = argTypes[0].getInternalName();
                // We will expand this map with more events later.
                // Key: Event class internal name, Value: [Getter method name, Getter method return type descriptor]
                Map<String, String[]> eventMap = new HashMap<>();
                eventMap.put("org/bukkit/event/player/PlayerEvent", new String[]{"getPlayer", "()Lorg/bukkit/entity/Player;"});
                eventMap.put("org/bukkit/event/entity/EntityEvent", new String[]{"getEntity", "()Lorg/bukkit/entity/Entity;"});
                eventMap.put("org/bukkit/event/entity/EntityDamageByEntityEvent", new String[]{"getDamager", "()Lorg/bukkit/entity/Entity;"});

                // This is a very basic check. A real implementation would check the class hierarchy.
                for(Map.Entry<String, String[]> entry : eventMap.entrySet()){
                    if (eventType.contains(entry.getKey().substring(15))) { // Heuristic check
                        return new EntitySchedulerMethodVisitor(mv, access, name, desc, eventType, entry.getValue()[0], entry.getValue()[1]);
                    }
                }
            }
            return mv;
        }
    }

    private static class EntitySchedulerMethodVisitor extends AdviceAdapter {
        private final String eventTypeInternalName;
        private final String entityGetterName;
        private final String entityGetterDesc;
        private static final String PATCHER_INTERNAL_NAME = Type.getInternalName(FoliaPatcher.class);

        protected EntitySchedulerMethodVisitor(MethodVisitor mv, int access, String name, String desc, String eventType, String getterName, String getterDesc) {
            super(Opcodes.ASM9, mv, access, name, desc);
            this.eventTypeInternalName = eventType;
            this.entityGetterName = getterName;
            this.entityGetterDesc = getterDesc;
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean isInterface) {
            if ("org/bukkit/scheduler/BukkitScheduler".equals(owner) && opcode == Opcodes.INVOKEINTERFACE &&
                FoliaPatcher.REPLACEMENT_MAP.containsKey(name + desc) &&
                !name.contains("Async")) { // Only patch sync tasks for EntityScheduler

                Type[] argTypes = Type.getArgumentTypes(desc);
                int[] localVars = new int[argTypes.length];
                for (int i = argTypes.length - 1; i >= 0; i--) {
                    localVars[i] = newLocal(argTypes[i]);
                    storeLocal(localVars[i]);
                }

                pop(); // Pop the original scheduler instance from the stack

                // Load the entity from the event parameter (which is the first argument of the event handler method)
                loadArg(0);
                visitMethodInsn(Opcodes.INVOKEVIRTUAL, this.eventTypeInternalName, this.entityGetterName, this.entityGetterDesc, false);

                // Load arguments back onto the stack from local variables
                for (int localVar : localVars) {
                    loadLocal(localVar);
                }

                // Build the new descriptor for the patcher method
                String newPatcherMethodName = "folia_" + name; // e.g., folia_runTask
                String newPatcherDesc = "(Lorg/bukkit/entity/Entity;" + desc.substring(1);

                super.visitMethodInsn(Opcodes.INVOKESTATIC, PATCHER_INTERNAL_NAME, newPatcherMethodName, newPatcherDesc, false);
            } else {
                super.visitMethodInsn(opcode, owner, name, desc, isInterface);
            }
        }
    }
}
