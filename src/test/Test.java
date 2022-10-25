package src.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {

    public interface HH{
        void hh();
    }

    public static class VM implements HH{
        public String hello(String hh){
            System.out.println(">>>"+hh);
            return ">>>"+hh;
        }

        @Override
        public void hh() {
            System.out.println(">>>xxxx");
        }
    }

    public static void main(String[] args){
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        VM hello = new VM();

        InvocationHandler handler = new ProxyHandler(hello);

        HH proxyHello = (HH) Proxy.newProxyInstance(hello.getClass().getClassLoader(), hello.getClass().getInterfaces(), handler);

        proxyHello.hh();
    }
    public static class ProxyHandler implements InvocationHandler {
        private Object object;
        public ProxyHandler(Object object){
            this.object = object;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Before invoke "  + method.getName());
            Object invoke = method.invoke(object, args);
            System.out.println("After invoke " + method.getName());
            return invoke;
        }
    }



}

