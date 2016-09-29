package com.jimmy.spring.beans.aop.impl;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//首先把这个类声明为一个组件
@Component
//再声明为一个切面
@Aspect
public class LoggingAspect {
	//前置通知使用@Before注解
	//将切点表达式的值作为注解值
	//切点表达式表示执行ArithmeticCalculator接口的add()方法
	//*代表匹配任意修饰符及任意返回值
	//参数列表中的..匹配任意数量的参数
	@Before("execution(* com.jimmy.spring.beans.aop.impl.ArithmeticCalculator.add(..))")
	//可以在通知方法中声明一个类型为JoinPoint的参数. 然后就能访问连接点细节. 如方法名称和参数值.
	public void beforeMethod(JoinPoint joinpoint){
		String methodName = joinpoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinpoint.getArgs());
		System.out.println("The method "+ methodName +" begins with "+ args);
	}
}