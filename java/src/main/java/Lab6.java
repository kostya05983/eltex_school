import NetWork.Client;
import NetWork.RunnerServer;

public class Lab6{

    public static void main(String[] args) {

        RunnerServer runnerServer=new RunnerServer(5);
        Thread runnerServerth=new Thread(runnerServer);
        runnerServerth.start();

        Thread[] threads=new Thread[5];
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0;i<5;i++){
            threads[i]=new Client();
            threads[i].start();
        }


    }
}
