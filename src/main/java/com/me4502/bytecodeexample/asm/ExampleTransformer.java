package com.me4502.bytecodeexample.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.CheckClassAdapter;

public class ExampleTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (name.equals("net.minecraft.server.MinecraftServer")) {
            ClassReader classReader = new ClassReader(basicClass);
            ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
            classReader.accept(new InjectingClassVisitor(classWriter), ClassReader.EXPAND_FRAMES);
            return classWriter.toByteArray();
        } else {
            return basicClass;
        }
    }

    public static final class InjectingClassVisitor extends CheckClassAdapter {

        public InjectingClassVisitor(ClassVisitor cv) {
            super(Opcodes.ASM5, cv, true);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            return new MethodInjector(super.visitMethod(access, name, desc, signature, exceptions), access, name, desc);
        }
    }

    public static final class MethodInjector extends GeneratorAdapter {

        public MethodInjector(MethodVisitor methodVisitor, int i, String s, String s1) {
            super(Opcodes.ASM5, methodVisitor, i, s, s1);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
            if (owner.equals("net/minecraft/server/MinecraftServer")
                    && opcode == Opcodes.INVOKEVIRTUAL
                    && name.equals("init")
                    && desc.equals("()Z")) {
                super.visitMethodInsn(Opcodes.INVOKESTATIC, "com/me4502/bytecodeexample/LoaderHook", "runMe",
                        "(Lnet/minecraft/server/MinecraftServer;)Z", itf);
            } else {
                super.visitMethodInsn(opcode, owner, name, desc, itf);
            }
        }
    }
}
