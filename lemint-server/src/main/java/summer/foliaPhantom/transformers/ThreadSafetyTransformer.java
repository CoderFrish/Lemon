package summer.foliaPhantom.transformers;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import summer.foliaPhantom.patchers.FoliaPatcher;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadSafetyTransformer implements ClassTransformer {
    private final Logger logger;

    public ThreadSafetyTransformer(Logger logger) {
        this.logger = logger;
    }

    @Override
    public byte[] transform(byte[] originalBytes) {
        String className = "Unknown";
        try {
            ClassReader cr = new ClassReader(originalBytes);
            className = cr.getClassName();
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            ClassVisitor cv = new ThreadSafetyClassVisitor(cw);
            cr.accept(cv, ClassReader.EXPAND_FRAMES);
            return cw.toByteArray();
        } catch (Exception e) {
            logger.log(Level.WARNING, "[Phantom-extra] Failed to transform class " + className + " for ThreadSafety. Returning original bytes.", e);
            return originalBytes;
        }
    }

    private static class ThreadSafetyClassVisitor extends ClassVisitor {
        public ThreadSafetyClassVisitor(ClassVisitor cv) {
            super(Opcodes.ASM9, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
            return new ThreadSafetyMethodVisitor(mv);
        }
    }

    private static class ThreadSafetyMethodVisitor extends MethodVisitor {
        private static final String PATCHER_INTERNAL_NAME = Type.getInternalName(FoliaPatcher.class);

        public ThreadSafetyMethodVisitor(MethodVisitor mv) {
            super(Opcodes.ASM9, mv);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean isInterface) {
            String methodKey = name + desc;
            if (FoliaPatcher.UNSAFE_METHOD_MAP.containsKey(methodKey)) {
                // We found a method that needs to be wrapped for thread safety.
                // The owner is the class the method belongs to, e.g., "org/bukkit/block/Block"
                String newPatcherMethodName = FoliaPatcher.UNSAFE_METHOD_MAP.get(methodKey);

                // The new descriptor needs to include the instance type as the first argument.
                // e.g., (Lorg/bukkit/Material;)V becomes (Lorg/bukkit/block/Block;Lorg/bukkit/Material;)V
                String instanceType = "L" + owner + ";";
                String newPatcherDesc = "(" + instanceType + desc.substring(1);

                super.visitMethodInsn(Opcodes.INVOKESTATIC, PATCHER_INTERNAL_NAME, newPatcherMethodName, newPatcherDesc, false);
            } else {
                super.visitMethodInsn(opcode, owner, name, desc, isInterface);
            }
        }
    }
}
