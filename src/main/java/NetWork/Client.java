package NetWork;

import Baskets.Order;
import Baskets.Orders;
import CheckingThreads.GenerateOrders;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

public class Client extends Thread{

    public static boolean fRun=true;
   private static Orders orders=new Orders();

    public  static void main(String[] args) {
        String host="LocalHost";
        byte[] buf=new byte[20];
        int port;
        InetAddress ip;
        GenerateOrders generateOrders=new GenerateOrders();
        Order order;
        ListenerUDP listenerUDP=new ListenerUDP(orders);
        Thread thread=new Thread(listenerUDP);
        thread.start();

        try {
            DatagramSocket ds = new DatagramSocket(Ports.portUDP.port);
            while(fRun) {
                Thread.sleep(1000);
                DatagramPacket dp = new DatagramPacket(buf, buf.length);
                ds.receive(dp);
                //Устанавливаем TCP

                port = ByteBuffer.wrap(dp.getData()).getInt(0);
                ip = dp.getAddress();
                Socket soc = new Socket(ip, port);

                    OutputStream os = soc.getOutputStream();
                    ObjectOutputStream objectOutputStream=new ObjectOutputStream(os);
                    order=generateOrders.getRandomOrder();
                    orders.addOrder(order);
                    objectOutputStream.writeObject(order);

                soc.close();

            }
            ds.close();
        } catch (SocketException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}
