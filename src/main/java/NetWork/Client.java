package NetWork;

import Baskets.Order;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

public class Client extends Thread{

    private static final int MESSAGELEN=1024;

    public void run() {
        String host="LocalHost";


        byte[] buf=new byte[4];
        int port;
        InetAddress ip;
        try {
            while(true) {
                DatagramSocket ds = new DatagramSocket(Ports.portUDP.port);
                DatagramPacket dp = new DatagramPacket(buf, buf.length);
                ds.receive(dp);

                //Устанавливаем TCP
                port = ByteBuffer.wrap(dp.getData()).getInt(0);
                ip = dp.getAddress();
                Socket soc = new Socket(ip, port);
                if(dp.getData().length>4){//Обработка оповещений
                    InputStream is=soc.getInputStream();
                    ObjectInputStream objectInputStream=new ObjectInputStream(is);
                    System.out.println((Order)objectInputStream.readObject());
                }else {
                    OutputStream os = soc.getOutputStream();
                    ObjectOutputStream objectOutputStream=new ObjectOutputStream(os);
                    Order order=new Order();//TODO генерация заказа
                    objectOutputStream.writeObject(order);
                    os.close();
                    soc.close();
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }


    }
//    public void
//    public static String reciveMessage(int port){
//
//            try {
//                DatagramSocket ds=new DatagramSocket(port);
//                while(true) {
//                    DatagramPacket pack=new DatagramPacket(new byte[MESSAGELEN],M);
//                    ds.receive(pack);
//                    System.out.println(pack.getData());
//                    return new String(pack.getData());
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                return null;
//            }
//    }
}
