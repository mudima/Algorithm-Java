//package DesignPattern;

public class FactoryPattern {
	public static void main(String[] args) {
		DrawingClient client = new DrawingClient();
		client.draw(ShapeType.Triangle);
		client.draw(ShapeType.Circle);
	}
}

enum ShapeType {
	Triangle, Circle;
}

abstract class Shape {
	protected String shapeName;
	
	public Shape(String shapeName) {
		this.shapeName = shapeName;
	}
	
	public abstract void draw();
}

class Triangle extends Shape {
	//attributes
	public Triangle() {
		super("Triangle");
	}
	@Override
	public void draw() {
		//draw triangle operations
		System.out.println("Draw" + shapeName);
	}
}

class Circle extends Shape {
	//attributes
	public Circle() {
		super("Circle");
	}
	@Override
	public void draw() {
		//draw circle operations
		System.out.println("Draw" + shapeName);
	}
}

class ShapeFactory {
	public static Shape createShape(ShapeType type) throws Exception {
		if (type == ShapeType.Triangle) return new Triangle();
		else if (type == ShapeType.Circle) return new Circle();
		else throw new Exception("Unknown shape type");
	}
}

class DrawingClient {
	public DrawingClient() {
		//constructor of client
	}

	public void draw(ShapeType type) {
		Shape shape = null;
		try {
			shape = ShapeFactory.createShape(type);
		} catch (Exception e) {
			System.out.println("Unknown shape type");
		}
		shape.draw();
	}
}
