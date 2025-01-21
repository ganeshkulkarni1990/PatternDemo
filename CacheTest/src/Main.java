
public class Main {
	public static void main(String[] args) {
		Cache<String, String> cache = new Cache<>(4);
		
		Runnable r = () -> {
			cache.put("1","2");
			cache.put("3","4");
			cache.get("1");
		};
		
		Runnable r2 = () -> {
			cache.get("1");
			cache.put("5","4");
			cache.put("6","111");
			cache.put("7", "9");
			cache.put("7","8");
			System.out.println(cache.get("7"));
		};
		
		Runnable r3 = () -> {
			cache.get("ganesh");
			cache.put("Test","New");
			cache.put("ganesh","111");
			cache.put("help", "9");
			cache.put("7","8");
			System.out.println(cache.get("7"));
		};
		
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r2);
		Thread t3 = new Thread(r3);
		t1.start();
		t2.start();
		t3.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		cache.print();
	}
}
