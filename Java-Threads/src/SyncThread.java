
class Data
{
    int i;
    boolean mutex;
    Data(int i)
    {
        this.i = i;
        mutex = false;
    }
}

class Thread1 implements Runnable
{
    Data d;
    Thread1(Data d)
    {
        this.d = d;
    }
    public void run()
    {
        while(true){
            try{
               while(d.mutex) //true wait
               {
                   //wait();
               }
               if(d.i>10){
            	   d.mutex = true;
                    break;
               }
                    
                System.out.println("t1:"+d.i);
                d.i = d.i + 1;
                
                Thread.sleep(100);
                d.mutex = true; //make true to wake up other thread
                
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}

class Thread2 implements Runnable
{
    Data d;
    Thread2(Data d)
    {
        this.d = d;
    }
    
    @Override
    public void run()
    {
        while(true){
            try{
               while(!d.mutex) 
               {
                   //wait();
               }
               
               if(d.i>10){
            	    d.mutex = false; //realse
                    break;
               }
                
                System.out.println("t2:"+d.i);
                d.i = d.i + 1;
                
                Thread.sleep(100);
                
                d.mutex = false; //to wake up other thread
                
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}

public class SyncThread implements Runnable{
    @Override
    public void run(){
        try{
            Thread.sleep(1000);
            System.out.println("new thread");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception{
        Data d = new Data(0);
        Thread t1 = new Thread(new Thread1(d));
        Thread t2 = new Thread(new Thread2(d));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
