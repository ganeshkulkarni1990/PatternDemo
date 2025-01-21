import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Task implements Runnable
{
	int taskid;
	
	Task(int t)
	{
		this.taskid = t;
	}
	public void run()
	{
		try{
			System.out.println("Started task:"+taskid);
			Thread.sleep(1000);
			System.out.println("Completed task:"+taskid);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}


class ThreadPool
{
	int size;
	int currentlyAvailable;
	int inUse;
	BlockingQueue<Task> q;
	
	Thread []t;
	ThreadPool(int size)
	{
		this.size  = size;
		currentlyAvailable = size;
		inUse = 0;
		t = new Thread[size];
		q = new LinkedBlockingQueue<Task>();
		
		for(int i=0;i<size;i++)
		{
			t[i] = new PoolWorker();
			t[i].start();
		}
		System.out.println("Thread Pool created with Size:"+size);
	}
	
	public static ThreadPool createFixedSizePool(int size)
	{
		return new ThreadPool(size);
	}
	
	void submit(Task task)
	{
		synchronized (q) {
			q.add(task);
			q.notify();
		}
	}
	
	private class PoolWorker extends Thread
	{
		Task task;
		public void run()
		{
			while(true)
			{
				synchronized (q) {
					while(q.isEmpty())
					{
						try{
							q.wait();
						}catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					 task = q.poll();
				}
				try
				{
					System.out.println(this.getId()+" started task:"+ task.taskid+" ");
					task.run();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}
		}
	}
}


public class ThreadPoolDomo {
	
	public static void main(String...args)
	{
		//ThreadPool threadPool = ThreadPool.createFixedSizePool(5);
		ThreadPool threadPool = new ThreadPool(2);
		threadPool.submit(new Task(1));
		threadPool.submit(new Task(2));
		threadPool.submit(new Task(3));
		threadPool.submit(new Task(4));
	}
	
	
}
