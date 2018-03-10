package NetWork;

import Baskets.Order;
import Baskets.Orders;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Date;

public class ListenerUDP extends Thread{
    private Orders orders;
    public boolean fRun=true;

    public ListenerUDP(Orders orders){
        this.orders=orders;
    }

    public void run(){
        try {
            DatagramSocket ds=new DatagramSocket(Ports.portUDPNote.port);
            byte [] buf=new byte[20];
            while(fRun){
                DatagramPacket datagramPacket=new DatagramPacket(buf,buf.length);
                ds.receive(datagramPacket);
                Order order=(Order)orders.getOrdersList().get(0);
                order.waiting=new Date(System.currentTimeMillis());
                System.out.println(order);
                orders.getOrdersList().remove(0);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
