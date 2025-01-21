import java.util.ArrayList;
import java.util.Random;

class Mutex
{
	int mutex = 0;
	Mutex(int i)
	{
		this.mutex = i;
	}
}

class Full
{
	int full = 0;
	Full(int i)
	{
		this.full = i;
	}
}

class Empty
{
	int empty;
	Empty(int i)
	{
		empty = i;
	}
}

class Sync
{
	static void wait(Object o)
	{
		if(o instanceof Empty)
		{
			while(((Empty)o).empty<=0);
			((Empty)o).empty--;
		}
		if(o instanceof Full)
		{
			while(((Full)o).full<=0);
			((Full)o).full--;
		}
		if(o instanceof Mutex)
		{
			while(((Mutex)o).mutex<=0);
			((Mutex)o).mutex--;
		}
	}
	static void signal(Object o)
	{
		if(o instanceof Empty)
		{
			((Empty)o).empty++;
		}
		if(o instanceof Full)
		{
			((Full)o).full++;
		}
		if(o instanceof Mutex)
		{
			((Mutex)o).mutex++;
		}
	}
}

class Producer implements Runnable
{
	ArrayList<Integer> list;
	Mutex mutex;
	Full full;
	Empty empty;
	
	Producer(ArrayList<Integer> list, Mutex mutex, Full full, Empty empty)
	{
		this.list = list;
		this.mutex = mutex;
		this.full = full;
		this.empty = empty;
	}
	
	public void run()
	{
		while(true)
		{
			Sync.wait(empty);
			Sync.wait(mutex);
			int a = new Random().nextInt();
			System.out.println("Produced:"+a);
			list.add(a);
			Sync.signal(mutex);
			Sync.signal(full);
		}
	}
	
	
}

class Consumer implements Runnable
{

	ArrayList<Integer> list;
	Mutex mutex;
	Full full;
	Empty empty;
	
	Consumer(ArrayList<Integer> list, Mutex mutex, Full full, Empty empty)
	{
		this.list = list;
		this.mutex = mutex;
		this.full = full;
		this.empty = empty;
	}
	
	public void run()
	{
		while(true)
		{
			Sync.wait(full);
			Sync.wait(mutex);
			System.out.println("Consumed:"+list.get(0));
			list.remove(0);
			Sync.signal(mutex);
			Sync.signal(empty);
		}
	}
	
}
public class ProducerConsumerDemo {

	public static void main(String...args)throws Exception
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		Mutex mutex = new Mutex(1);
		Full full = new Full(0);
		Empty empty = new Empty(10);
		
		
		Thread producer = new Thread(new Producer(list,mutex,full,empty));

		Thread consumer = new Thread(new Consumer(list,mutex,full,empty));
		
		producer.start();
		consumer.start();
		
		producer.join();
		consumer.join();
	
	}
}
