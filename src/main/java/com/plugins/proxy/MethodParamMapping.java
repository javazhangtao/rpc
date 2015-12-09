package com.plugins.proxy;

import org.objectweb.asm.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by zhangtao on 2015/12/9.
 * 方法参数映射  基于ASM实现
 */
public class MethodParamMapping {

    /**
     * 获取调用方法所有参数名称
     * @param method 调用方法
     * @return 方法名称数组
     */
    public String[] getMethodParams(final Method method){
        final String[] paramNames = new String[method.getParameterTypes().length];
        final String n = method.getDeclaringClass().getName();
        final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassReader cr = null;
        try{
            cr = new ClassReader(n);
        } catch (IOException e) {
        }
        cr.accept(new ClassVisitor(Opcodes.ASM4, cw) {
            @Override
            public MethodVisitor visitMethod(final int access, final String name, final String desc,
                                             final String signature, final String[] exceptions) {
                final Type[] args = Type.getArgumentTypes(desc);
                // 方法名相同并且参数个数相同
                if (!name.equals(method.getName()) || !sameType(args, method.getParameterTypes())) {
                    return super.visitMethod(access, name, desc, signature, exceptions);
                }
                MethodVisitor v = cv.visitMethod(access, name, desc, signature, exceptions);
                return new MethodVisitor(Opcodes.ASM4, v) {
                    @Override
                    public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
                        int i = index - 1;
                        // 如果是静态方法，则第一就是参数
                        // 如果不是静态方法，则第一个是"this"，然后才是方法的参数
                        if (Modifier.isStatic(method.getModifiers())) {
                            i = index;
                        }
                        if (i >= 0 && i < paramNames.length) {
                            paramNames[i] = name;
                        }
                        super.visitLocalVariable(name, desc, signature, start,end, index);
                    }
                };
            }
        },0);
        return paramNames;
    }

    /**
     * 判断参数个数是否相同
     * @param types
     * @param clazzes
     * @return
     */
    private boolean sameType(Type[] types, Class<?>[] clazzes) {
        if (types.length != clazzes.length)
            return false;
        return true;
    }
}
