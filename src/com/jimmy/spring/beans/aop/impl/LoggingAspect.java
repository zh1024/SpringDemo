package com.jimmy.spring.beans.aop.impl;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//首先把这个类声明为一个组件
@Component
//再声明为一个切面
@Aspect
//可以使用@Order注解指定切面的优先级，值越小优先级越高
@Order(2)
public class LoggingAspect {
	
	//定义一个空方法，用于声明切点表达式。
	//使用@Pointcut声明切点表达式
	//其他通知直接使用方法名来引用声明好的切点表达式
	@Pointcut("execution(* com.jimmy.spring.beans.aop.impl.*.*(..))")
	public void declareJoinPointExpression(){}
	
	//前置通知使用@Before注解
	//将切点表达式的值作为注解值
	//切点表达式表示执行ArithmeticCalculator接口的add()方法
	//*代表匹配任意修饰符及任意返回值
	//参数列表中的..匹配任意数量的参数
	@Before("declareJoinPointExpression()")
	//可以在通知方法中声明一个类型为JoinPoint的参数. 然后就能访问连接点细节. 如方法名称和参数值.
	public void beforeMethod(JoinPoint joinpoint){
		String methodName = joinpoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinpoint.getArgs());
		System.out.println("The method "+ methodName +" begins with "+ args);
	}
	
	//后置通知使用@After注解
	//在后置通知中还不能访问目标方法的执行结果
	//无论目标方法是否发生异常后置通知都会执行
	@After("declareJoinPointExpression()")
	public void afterMethod(JoinPoint joinpoint){
		String methodName = joinpoint.getSignature().getName();
		System.out.println("The method "+ methodName +" ends");
	}
	
	//返回通知使用@AfterReturning注解
	//在返回通知中可以访问目标方法的执行结果
	//如目标方法发生异常则返回通知不会执行
	@AfterReturning(value = "declareJoinPointExpression()",
			returning = "result")
	public void afterReturning(JoinPoint joinpoint, Object result){
		String methodName = joinpoint.getSignature().getName();
		System.out.println("The method "+ methodName +" ends with " + result);		
	}
	
	//异常通知使用@AfterThrowing注解
	//在返回通知中可以访问异常对象
	//通过指定异常参数的类型当发生指定异常时异常通知才执行
	//如目标方法未发生异常则异常通知不会执行
	@AfterThrowing(value = "declareJoinPointExpression()",
			throwing = "e")
	public void afterThrowing(JoinPoint joinpoint, Exception e){
		String methodName = joinpoint.getSignature().getName();
		System.out.println("The method "+ methodName +" occurs exception: " + e);		
	}
	
	/*
	//环绕通知使用@Around注解
	//环绕通知类似于动态代理的全过程
	//ProceedingJoinPoint的参数可以决定是否执行目标方法
	//环绕通知必须有返回值，返回值即为目标方法的返回值
	@Around(value = "declareJoinPointExpression()")
	public Object aroundMethod(ProceedingJoinPoint pjp){
		
		Object result = null;
		//方法名
		String methodName = pjp.getSignature().getName();
		//参数
		List<Object> args = Arrays.asList(pjp.getArgs());

		try {
			//方法执行前
			result = pjp.proceed();
			//方法执行后
		} catch (Throwable e) {
			//异常发生后
			e.printStackTrace();
		}
		//返回前
		return result;
	}
	*/
}