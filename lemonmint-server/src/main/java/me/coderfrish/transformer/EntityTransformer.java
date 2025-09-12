package me.coderfrish.transformer;

import me.coderfrish.scheduler.FixedEntityScheduler;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

@Deprecated
public class EntityTransformer extends ClassVisitor {
    private static final int ASM_VERSION =  ASMTransformer.ASM_VERSION;

    protected EntityTransformer(ClassVisitor classVisitor) {
        super(ASM_VERSION, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        Type[] argTypes = Type.getArgumentTypes(desc);

        if ((access & Opcodes.ACC_PUBLIC) != 0 && (access & Opcodes.ACC_STATIC) == 0 &&
                argTypes.length == 1 && Type.getReturnType(desc).equals(Type.VOID_TYPE)) {

            String eventType = argTypes[0].getInternalName();
            if (FixedEntityScheduler.ENTITY_INVOKE_LIST.containsKey(eventType)) {
                return new EntitySchedulerMethodVisitor(mv, access, name, desc, eventType);
            }
        }

        return mv;
    }

    private static class EntitySchedulerMethodVisitor extends AdviceAdapter {
        private final String eventType;

        protected EntitySchedulerMethodVisitor(MethodVisitor methodVisitor, int access, String name, String descriptor, String eventType) {
            super(ASM_VERSION, methodVisitor, access, name, descriptor);
            this.eventType = eventType;
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean isInterface) {
            String key = owner + "." + name + desc;
            if (FixedEntityScheduler.ENTITY_SCHEDULE_METHOD_MAPPINGS.containsKey(key) && opcode == Opcodes.INVOKEINTERFACE) {
                FixedEntityScheduler.MappingInfo mappingInfo = FixedEntityScheduler.ENTITY_SCHEDULE_METHOD_MAPPINGS.get(key);
                Type[] argTypes = Type.getArgumentTypes(desc);
                int[] localVars = new int[argTypes.length];
                for (int i = argTypes.length - 1; i >= 0; i--) {
                    localVars[i] = newLocal(argTypes[i]);
                    storeLocal(localVars[i]);
                }

                pop();

                loadArg(0);
                if (eventType.contains("Player")) {
                    visitMethodInsn(Opcodes.INVOKEVIRTUAL, "org/bukkit/event/player/PlayerEvent", "getPlayer", "()Lorg/bukkit/entity/Player;", false);
                } else {
                    visitMethodInsn(Opcodes.INVOKEVIRTUAL, "org/bukkit/event/entity/EntityEvent", "getEntity", "()Lorg/bukkit/entity/Entity;", false);
                }

                for (int localVar : localVars) {
                    loadLocal(localVar);
                }

                super.visitMethodInsn(Opcodes.INVOKESTATIC, mappingInfo.owner(), mappingInfo.name(), mappingInfo.descriptor(), false);
                return;
            }

            super.visitMethodInsn(opcode, owner, name, desc, isInterface);
        }
    }
}
