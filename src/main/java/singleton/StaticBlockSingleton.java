package singleton;

public class StaticBlockSingleton {
	private static final StaticBlockSingleton SINGLETON;
	
	private StaticBlockSingleton(){
	}
	
	public static StaticBlockSingleton getInstance(){
		return SINGLETON;
	}
	
	static{
		try {
			SINGLETON = new StaticBlockSingleton();
		} catch (Exception e) {
			throw new RuntimeException("I was not expecting this");
		}
	}
}
