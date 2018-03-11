import Baskets.Orders;
import NetWork.ListenerUDP;
import NetWork.Ports;
import NetWork.SenderUDP;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.UUID;

public class TestListener {
    public static void main(String[] args) {
        Orders orders=new Orders();
        Orders orders1=new Orders();

        Thread[] threads=new Thread[2];
        UUID uuid=UUID.randomUUID();
        UUID uuid1=UUID.randomUUID();
        ListenerUDP listenerUDP=new ListenerUDP(orders, uuid);
        ListenerUDP listenerUDP1=new ListenerUDP(orders1,uuid1);

        threads[0]=new Thread(listenerUDP);
        threads[0].start();
        threads[1]=new Thread(listenerUDP1);
        threads[1].start();

        try {
            DatagramSocket ds=new DatagramSocket();
            SenderUDP senderUDP=new SenderUDP("LocalHost", Ports.portUDP.port);
            senderUDP.sendNotification(uuid);
            senderUDP.sendNotification(uuid1);
        } catch (SocketException e) {
            e.printStackTrace();
        }




    }
}
