package factory;

public class ShapeFactory {
	
	public Shape getShape(ShapeType type) {
		switch (type) {
			case LINE:
				return new Line();
			case CIRCLE:
				return new Circle();
			default:
				return new DefaultShape();
		}
	}
}
