package jdkdyproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import jdkdyproxy.proxy.User;
import jdkdyproxy.proxy.UserImpl;

public class jdkdl implements InvocationHandler{

	private Object jdkdl;
	

	public jdkdl(Object target) {
		this.jdkdl= target;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("开启代理。。。");
		Object invoke = method.invoke(jdkdl, args);
		System.out.println("代理");
		return invoke;
	}
	
	public static void main(String[] args) {
		User user=new UserImpl();
		jdkdl jdkdl=new jdkdl(user);
		//加载user
		ClassLoader classLoader=user.getClass().getClassLoader();
		//加载接口
		Class<?>[] interfaces = user.getClass().getInterfaces();
		//代理实现
		User User=(jdkdyproxy.proxy.User) Proxy.newProxyInstance(classLoader, interfaces, jdkdl);
		User.add();
	}

}
