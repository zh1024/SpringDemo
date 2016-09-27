package com.jimmy.spring.beans.factorybean;

import org.springframework.beans.factory.FactoryBean;

//定义的FactoryBean需要实现org.springframework.beans.factory.FactoryBean接口
public class CarFactoryBean implements FactoryBean<Car> {

	private String brand;
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	@Override
	//返回Bean实例
	public Car getObject() throws Exception {
		return new Car(brand, 500000);
	}

	@Override
	//返回Bean类型
	public Class<?> getObjectType() {
		return Car.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
