package singleton;

// recommend to use
public class BillPughSingleton {
	private BillPughSingleton(){
	}
	
	private static class LazyHolder {
		private static final BillPughSingleton INSTANCE = new BillPughSingleton();
	}
	
	public static BillPughSingleton getInstance() {
		return LazyHolder.INSTANCE;
	}
}
