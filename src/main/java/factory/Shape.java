package factory;

public interface Shape {
	public default void draw() {
		System.out.println("draw default shape");
	};
}
