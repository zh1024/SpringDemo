package com.jimmy.spring.beans.cycle;

public class Car {
	
	private String brand;
	private double price;
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
		System.out.println("setBrand...");
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
		System.out.println("setPrice...");
	}
	@Override
	public String toString() {
		return "Car [brand=" + brand + ", price=" + price + "]";
	}
	public Car() {
		System.out.println("Car's Constructor...");
	}	
	public void init(){
		System.out.println("Car's Init...");
	}	
	public void destory(){
		System.out.println("Car's Destory...");
	}
	
}
