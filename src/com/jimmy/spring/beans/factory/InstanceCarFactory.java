package com.jimmy.spring.beans.factory;

import java.util.HashMap;
import java.util.Map;

//实例工厂方法：
public class InstanceCarFactory {
	
	private Map<String, Car> cars;
	
	public InstanceCarFactory() {
		cars = new HashMap<String, Car>();
		cars.put("Audi", new Car("Audi", 300000));
		cars.put("Ford", new Car("Ford", 400000));
	}

	//实例工厂方法
	public Car getCar(String name) {
		return cars.get(name);
	}
	
}
