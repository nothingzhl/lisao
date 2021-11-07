package org.zhl.parser;

import lombok.extern.slf4j.Slf4j;
import org.zhl.as.ASTList;
import org.zhl.as.ASTree;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author zhanghanlin
 * @date 2021/11/6
 **/
@Slf4j
public abstract class Factory {

    public static final String factoryName = "create";

    /**
     * make0
     *
     * @param arg 参数
     * @return {@link ASTree}
     * @throws Exception 异常
     */
    protected abstract ASTree make0(Object arg) throws Exception;

    /**
     * 使
     *
     * @param object 对象
     * @return {@link ASTree}
     */
    protected ASTree make(Object object) {
        try {
            return make0(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected static Factory getForASTList(Class<? extends ASTree> clazz) {
        Factory f = get(clazz, List.class);
        if (f == null) {
            f = new Factory() {
                @Override
                protected ASTree make0(Object arg) throws Exception {
                    @SuppressWarnings("unchecked")
                    List<ASTree> results = (List<ASTree>)arg;
                    if (results.size() == 1) {
                        return results.get(0);
                    } else {
                        return new ASTList(results);
                    }
                }
            };
        }
        return f;
    }

    protected static Factory get(Class<? extends ASTree> clazz, Class<?> argType) {
        if (clazz == null) {
            return null;
        }
        // 利用方法创建Factory
        try {
            final Method m = clazz.getMethod(factoryName, new Class<?>[] {argType});
            return new Factory() {
                @Override
                protected ASTree make0(Object arg) throws Exception {
                    return (ASTree)m.invoke(null, arg);
                }
            };
        } catch (NoSuchMethodException e) {
            log.warn("{} 没有被实现！",factoryName);
        }
        // 尝试利用构造器去构建Factory
        try {
            final Constructor<? extends ASTree> c = clazz.getConstructor(argType);
            return new Factory() {
                @Override
                protected ASTree make0(Object arg) throws Exception {
                    return c.newInstance(arg);
                }
            };
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
