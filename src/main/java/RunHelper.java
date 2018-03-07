import Baskets.Orders;
import CheckingThreads.GenerateOrders;
import CheckingThreads.ReadyCheck;
import CheckingThreads.WaitCheck;

public class RunHelper {
    private Thread[] threads;
    private GenerateOrders[] generateOrders;
    private WaitCheck waitCheck;
    private ReadyCheck readyCheck;

    public void  testThreads(int amountGenerators){
        Orders orders=new Orders();
        threads=new Thread[amountGenerators+2];
        generateOrders=new GenerateOrders[amountGenerators];

        for(int i=0;i<generateOrders.length;i++){
            try {
                generateOrders[i] = new GenerateOrders(orders);
                Thread.sleep(100);
            }catch (InterruptedException e){
                System.out.println(e);
            }
        }

        for(int i=0;i< threads.length-2;i++){
            threads[i]=new Thread(generateOrders[i]);
        }



        waitCheck=new WaitCheck(orders);
        threads[threads.length-2]=new Thread(waitCheck);


        readyCheck=new ReadyCheck(orders);
        threads[threads.length-1]=new Thread(readyCheck);


    }

    public void startThreads(){
        for(int i=0;i<threads.length;i++){
            threads[i].start();
        }
    }

    public void stopThreads(){
        for(int i=0;i<generateOrders.length;i++){
            generateOrders[i].setIsAlive(false);
        }
        readyCheck.setIsAlive(false);
        waitCheck.setIsAlive(false);
    }
}
