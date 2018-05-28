package DesignPattern;

public class Singleton {
	private static Singleton INSTANCE = null;
	
	private/protected Singleton() { //if Server have subclass -> protected
		//do something
	}
	
	public static Singleton getIncetance() {
		if (INSTANCE == null) { //lazy initialization
				INSTANCE = new Singleton();
		}
		return INSTANCE;
	}
}

class ThreadSafeServer1 {
	private static ThreadServer1 INSTANCE = null;
	
	private ThreadSafeServer1() {
		//do something
	}
	
	public static synchronized ThreadSafeServer1 getIncetance() {
		if (INSTANCE == null) { //lazy initialization
			INSTANCE = new ThreadSafeServer1();
		}
		return INSTANCE;
	}
}

class ThreadSafeServer2 {
	private static ThreadSafeServer2 INSTANCE = null;
	
	private ThreadSafeServer2() {
		//do something
	}
	
	public static ThreadSafeServer2 getIncetance() {
		if (INSTANCE == null) { //lazy initialization
			synchronized(ThreadSafeServer2.class) {
				if (INSTANCE == null) INSTANCE = new ThreadSafeServer2();
			}
		}
		return INSTANCE;
	}
}
