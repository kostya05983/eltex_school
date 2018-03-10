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
    public static boolean fRun=true;
    private static Orders orders=new Orders();
    private static  SenderUDP senderUDP;
    private static String host="LocalHost";

    public  static void main(String[] args)  {
        Orders orders=new Orders();
        byte[] ib;//Порт на соединение
        ByteBuffer byteBuffer=ByteBuffer.allocate(4);
        byteBuffer.putInt(Ports.portTCP.port);
        ib=byteBuffer.array();

        senderUDP=new SenderUDP(ib,host,Ports.portUDP.port);

        Thread senderth=new Thread(senderUDP);
        senderth.start();

        WaitCheck waitCheck=new WaitCheck(orders,senderUDP);
        Thread thWait=new Thread(waitCheck);
        thWait.start();

        try {
            ServerSocket ss = new ServerSocket(Ports.portTCP.port);
            while(fRun) {
                Socket soc = ss.accept();//ждем ответа от клиента
                InputStream is = soc.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(is);
                orders.addOrder((Order) objectInputStream.readObject());
                //Проверить
                is.close();
                soc.close();
                System.out.println("Сервер пришел");
            }
            ss.close();
        } catch (SocketException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }



}
