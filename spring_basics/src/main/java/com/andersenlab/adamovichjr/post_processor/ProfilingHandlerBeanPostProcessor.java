package com.andersenlab.adamovichjr.post_processor;

import com.andersenlab.adamovichjr.annotations.Profiling;
import com.andersenlab.adamovichjr.controller.ProfilingController;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;


public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor {
    private Map<String,Class> map = new HashMap<>();
    private ProfilingController controller= new ProfilingController();

    public ProfilingHandlerBeanPostProcessor() throws Exception {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        mBeanServer.registerMBean(controller,new ObjectName("profiling","name","controller"));
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if(beanClass.isAnnotationPresent(Profiling.class)){
            map.put(beanName,beanClass);
        }
        return bean;
    }

    //все измененмя в бине делаются в afterInit
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = map.get(beanName);
        if(beanClass != null){
            //ClassLoader загружает класс в кучу, список интерфейсов которые должна имплементировать прокси,
            //invocationHandler инкапсулирует логику во все методы класса прокси
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if(controller.isEnabled()) {
                        System.out.println("профилирую");
                        long before = System.nanoTime();
                        Object reVal = method.invoke(bean, args);
                        long after = System.nanoTime();
                        System.out.println(after - before );
                        System.out.println("закончил");
                        return reVal;
                    }else {
                        return method.invoke(bean,args);
                    }
                }
            });
        }
        return bean;
    }
}
