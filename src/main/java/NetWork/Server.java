package NetWork;

import Baskets.Order;
import Baskets.Orders;
import CheckingThreads.WaitCheck;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.*;
import java.nio.ByteBuffer;

public class Server extends Thread{
    public  boolean fRun=true;
    private static Orders orders=new Orders();
    private SenderUDP senderUDP;
    private final String host="localHost";
    private int port;

    public Server(int port){
        this.port=port;
    }

    public  void run()  {
        Orders orders=new Orders();

        byte[] ib;
        ByteBuffer byteBuffer=ByteBuffer.allocate(4);
        byteBuffer.putInt(port);
        ib=byteBuffer.array();

        senderUDP=new SenderUDP(host,Ports.portUDP.port);
        senderUDP.setBuf(ib);

        Thread senderth=new Thread(senderUDP);
        senderth.start();

        WaitCheck waitCheck=new WaitCheck(orders,senderUDP);
        Thread thWait=new Thread(waitCheck);
        thWait.start();
        while(fRun) {
            try {
                ServerSocket ss = new ServerSocket(port);
                Socket soc = ss.accept();
                if (soc.isConnected()) {
                    InputStream is = soc.getInputStream();
                    ObjectInputStream objectInputStream = new ObjectInputStream(is);
                    orders.addOrder((Order) objectInputStream.readObject());

                    Thread.sleep(100);
                    is.close();
                    soc.close();
                }
                ss.close();
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }





}
