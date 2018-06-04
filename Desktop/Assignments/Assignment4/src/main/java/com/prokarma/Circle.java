package com.prokarma;
public class Circle{
	public double radius;
	public Circle(double radius){
		this.radius = radius;
	}
	public double getArea(){
		return 3.14*radius*radius;
	}
	public double getCircumference(){
		return 2*3.14*radius;
	}
}