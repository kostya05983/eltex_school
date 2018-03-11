package NetWork;

import Baskets.Order;
import Baskets.Orders;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.Date;
import java.util.UUID;

public class ListenerUDP extends Thread{
    private Orders orders;
    public boolean fRun=true;
    private  UUID id;

    public ListenerUDP(Orders orders,UUID id){
        this.orders=orders;
        this.id=id;
    }

    public void run(){
        try {
            MulticastSocket socket=new MulticastSocket(Ports.portUDPNote.port);
            InetAddress group=InetAddress.getByName("224.0.0.1");
            socket.joinGroup(group);
            byte [] buf=new byte[36];

            while(fRun){
                DatagramPacket datagramPacket=new DatagramPacket(buf,buf.length);
                socket.receive(datagramPacket);
                if(id.toString().equals(new String(datagramPacket.getData()))) {
                    Order order = (Order) orders.getOrdersList().get(0);
                    order.waiting = new Date(System.currentTimeMillis());
                    System.out.println(order);
                    orders.getOrdersList().remove(0);
                }
            }
            socket.leaveGroup(group);
            socket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
