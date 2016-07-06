package factory;

public class Demo {

	public static void main(String[] args) {
		ShapeFactory sf = new ShapeFactory();
		Shape shape1 = sf.getShape(ShapeType.LINE);
		Shape shape2 = sf.getShape(ShapeType.CIRCLE);
		Shape shape3 = sf.getShape(ShapeType.DEFAULT);
		shape1.draw();
		shape2.draw();
		shape3.draw();
	}

}
