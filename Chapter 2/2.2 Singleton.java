//不好的解法一：只适用于单线程环境
public class singleton1 {
	private static final singleton1 instance = new singleton1();
	private singleton1() {
	}
	public static singleton1 getinstance() {
		return(instance);
	}
}

//不好的解法二：虽然在多线程环境中能够工作，但是效率不高
public class singleton2 {
	private static singleton2 instance;
	private singleton2() {
	}
	public static singleton2 getinstace(){
		if(instance == null) {
			instance = new singleton2();
		}
		return(instance);
	}
}

//可行的解法：加同步锁前后两次判断实力是否已存在
public class singleton3 {
	private static singleton3 instance;
	private singleton3() {
	}
	public static singleton3 getinstance() {
		if(instance == null) {
			synchronized(singleton3.class) {
				if(instance == null)
					instance = new singleton3();
			}
		}
		return(instance);
	}
}

//强烈推荐的解法二：实现按需创建实例
public class singleton4 {
	private singleton4() {
		
	}
	private static class createinstance{
		private static singleton4 instance = new singleton4();
	}
	public static singleton4 getinstance() {
		return(createinstance.instance);
	}

}
