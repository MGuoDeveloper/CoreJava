package singleton;

public class LazySingleton {
	private static volatile LazySingleton singleton = null;
	
	private LazySingleton(){};
	
	public static LazySingleton getInstance(){
		if (singleton == null) {
			synchronized (LazySingleton.class) {
				// double-checked locking 
				if (singleton == null) {
					singleton = new LazySingleton();
				}
			}
		}
		return singleton;
	}
}
