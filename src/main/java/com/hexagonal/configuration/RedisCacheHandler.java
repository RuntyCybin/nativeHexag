package com.hexagonal.configuration;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class RedisCacheHandler implements InvocationHandler {


    private final Object target;
    private final Map<String, Map<String, Object>> cache = new HashMap<>();

    public RedisCacheHandler(Object t) {
        this.target = t;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(RedisCache.class)) {
            RedisCache isCacheable = method.getAnnotation(RedisCache.class);
            String cacheName = isCacheable.valueNameString();
            String key = isCacheable.valueKey();

            // verificamos si el valor se encuentra ya en la cache
            Map<String, Object> cacheMap = cache.computeIfAbsent(cacheName, k-> new HashMap<>());
            if (cacheMap.containsKey(key)) {
                System.out.println("Devolviendo valor de la cache para la clave: " + key);
                return cacheMap.get(key);
            }

            // Ejecutamos el metodo y almacenamos el valor en la cache
            Object res = method.invoke(target, args);
            cacheMap.put(key, res);
            return res;
        } else {
            return method.invoke(target, args);
        }
    }

    public static Object createProxy(Object targObject) {

        return Proxy.newProxyInstance(
            targObject.getClass().getClassLoader(), 
            targObject.getClass().getInterfaces(), 
            new RedisCacheHandler(targObject));
    }

}
