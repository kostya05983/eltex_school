package NetWork;



import java.io.IOException;
import java.net.*;
import java.util.LinkedList;

public class RunnerServer extends Thread{
    private static LinkedList<Port> ports=new LinkedList<>();
    private  int amount;



    public RunnerServer(int amount){
        this.amount=amount;
        int tmpPort=0;
        
        Socket soc;
       while(amount>0){
           try {
               soc=new Socket(InetAddress.getByName("LocalHost"),tmpPort);
               soc.close();
               ports.add(new Port(tmpPort,true));
               amount--;
           } catch (SocketException e) {
               System.out.println(tmpPort);
               tmpPort++;
               continue;
           } catch (UnknownHostException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }

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
