package NetWork;



import java.util.LinkedList;

public class RunnerServer extends Thread{
    private static LinkedList<Port> ports=new LinkedList<>();
    private  int amount;


    public RunnerServer(int amount){
        this.amount=amount;
        for(int i=3336;i<3336+amount;i++){
            ports.add(new Port(i,true));
        }
    }

    public void run(){
        Thread[] threads=new Thread[amount];
        for(int i=0;i<threads.length;i++){
            threads[i]=new Thread(getThServer());
            threads[i].start();
        }
    }

    private Server getThServer(){
        return new Server(getFreeTcp());
    }

    private static int getFreeTcp(){
        for(int i=0;i<ports.size();i++){
            if(ports.get(i).free==true){
                ports.get(i).free=false;
                return ports.get(i).number;
            }
        }
        return 0;
    }

    private static void freeTcp(int port) {
        for (int i = 0; i < ports.size(); i++) {
            if (port == ports.get(i).number) {
                ports.get(i).free = true;
                break;
            }
        }
    }
}
