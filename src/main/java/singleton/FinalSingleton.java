package singleton;

import java.io.Serializable;

public class FinalSingleton implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    private FinalSingleton() {
        // private constructor
    }
    
    private static class FinalSingletonHolder {
        public static final FinalSingleton INSTANCE = new FinalSingleton();
    }
    
    public static FinalSingleton getInstance() {
        return FinalSingletonHolder.INSTANCE;
    }
    
    protected Object readResolve() {
        return getInstance();
    }
}
