package com.changmu.common.utils;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;


/**
 * @author yu peng
 * @version 1.0
 * @date 2022/4/27
 * @description
 */
@Component
public class SpringUtil implements ApplicationContextAware, EmbeddedValueResolverAware {
    private static ApplicationContext applicationContext;
    private static StringValueResolver stringValueResolver;

    public SpringUtil() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }

        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------me.shijunjie.util.SpringUtil------------------------------------------------------");
        System.out.println("========ApplicationContext配置成功,在普通类可以通过调用SpringUtils.getAppContext()获取applicationContext对象,applicationContext=" + SpringUtil.applicationContext + "========");
        System.out.println("---------------------------------------------------------------------");
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        SpringUtil.stringValueResolver = stringValueResolver;
    }

    public static String getPropertiesValue(String name) {
        try {
            if (!name.startsWith("${")) {
                name = "${" + name + "}";
            }

            return stringValueResolver.resolveStringValue(name);
        } catch (Exception var2) {
            return null;
        }
    }

    public static void main(String[] args) {
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    public static <T> List<T> getBeans(Class<T> clazz) {
        List<T> lists = new ArrayList();
        Map<String, T> map = getApplicationContext().getBeansOfType(clazz);
        Iterator var3 = map.values().iterator();

        while(var3.hasNext()) {
            T v = (T) var3.next();
            lists.add(v);
        }

        return lists;
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}
