package NetWork;

import Baskets.Order;
import Baskets.Orders;
import CheckingThreads.GenerateOrders;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.UUID;

public class Client extends Thread{

    public  boolean fRun=true;
    private Orders orders=new Orders();
    private ListenerUDP listenerUDP;
    private Thread listenerTh;

    public  void run() {
        byte[] buf=new byte[20];
        int port;
        InetAddress ip;
        UUID id=UUID.randomUUID();
        GenerateOrders generateOrders=new GenerateOrders();
        Order order;

        listenerUDP=new ListenerUDP(orders,id);
        listenerTh=new Thread(listenerUDP);
        listenerTh.start();

        try {
            MulticastSocket socket=new MulticastSocket(Ports.portUDP.port);
            while(fRun) {
                Thread.sleep(1000);
                DatagramPacket dp = new DatagramPacket(buf, buf.length);
                socket.receive(dp);

                port = ByteBuffer.wrap(dp.getData()).getInt(0);
                ip = dp.getAddress();
                Socket soc = new Socket(ip, port);

                    OutputStream os = soc.getOutputStream();
                    ObjectOutputStream objectOutputStream=new ObjectOutputStream(os);
                    order=generateOrders.getRandomOrder(id);
                    orders.addOrder(order);
                    objectOutputStream.writeObject(order);

                Thread.sleep(10);
                soc.close();

            }
            socket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}
