package singleton;

/**
 * @author M
 * 
 * This is a design pattern where an instance of a class is created much before 
 * it is actually required. Mostly it is done on system start up. 
 * In singleton pattern, it refers to create the singleton instance 
 * irrespective of whether any other class actually asked for its instance or not.
 * 
 * one drawback. Instance is created irrespective of it is required in runtime or not. 
 * If this instance is not big object and you can live with it being unused, this is best approach.
 * 
 */
public class EagerSingleton {
	// read and write from the main memory, not from CPU cache
	// for thread safe, visible for all threads
	private static volatile EagerSingleton singleton = new EagerSingleton();

	// private constructor
	private EagerSingleton() {
	};

	public static EagerSingleton getInstance() {
		return singleton;
	}
}
