import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class CustomExecutor {
	private final int poolSize;
	private final Thread[] threads;
	private final BlockingQueue<Runnable> queue;

	public CustomExecutor(int poolSize) {
		this.poolSize = poolSize;
		threads = new Thread[poolSize];
		queue = new LinkedBlockingQueue<Runnable>();
		createAndStartAll();
	}

	public void submit(Runnable runnable) {
		try {
			queue.put(runnable);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void createAndStartAll() {
		// create threads and start
		for (int i = 0; i < poolSize; i++) {
			threads[i] = new WorkerThread();
		}
		// start all the threads
		for (Thread thread : threads) {
			thread.start();
		}
	}

	public void shutdown() {
		boolean b = true;
		while (b) {
			try {
				Thread.sleep(10);
				if (queue.isEmpty()) {
					b = false;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (Thread thread : threads) {
			thread.interrupt();
		}
	}

	private class WorkerThread extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					Runnable task = queue.take();
					task.run();
				} catch (InterruptedException e) {
//					e.printStackTrace();
					Thread.currentThread().interrupt();
					break;
				}
			}
		}
	}
}

public class CustomExecutorService {
	public static int count = 0;

	public static void main(String[] args) {
		CustomExecutor executor = new CustomExecutor(2);
		for (int i = 0; i < 100; i++) {
			// System.out.println(i);
			executor.submit(() -> {
				System.out.println(count++ + " " + Thread.currentThread().getName());
			});
		}
		executor.shutdown();
	}

}
