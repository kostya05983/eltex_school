import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.ByteBuffer;

public class Server {
    public static boolean  fRun=true;
    public static void main(String[] args) {

            //System.out.println("Server is running");


            byte[] ib=new byte[4];//Порт на соединение
            byte[] ob=new byte[256];
            String host="LocalHost";
            ByteBuffer byteBuffer=ByteBuffer.allocate(4);
            byteBuffer.putInt(Param.portTCP);
            ib=byteBuffer.array();

            //UDPSender sender=new UDPSender("localhost",portUDP,""+port);

            try {
                DatagramSocket ds = new DatagramSocket();
                DatagramPacket dp = new DatagramPacket(ib, ib.length, InetAddress.getByName(host), Param.portUdp);

                    ds.send(dp);//Отсылаем внутри пакета порт на соединение
//                DatagramPacket ip=new DatagramPacket(ob,ob.length);
//                ds.receive(ip);

                ds.close();
                ServerSocket ss=new ServerSocket(Param.portTCP);
                Socket soc=ss.accept();//ждем ответа от клиента
                InputStream is=soc.getInputStream();
                is.read(ob);
                is.close();
                soc.close();
                System.out.println(new String(ob));
            } catch (SocketException e) {
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }



    }
}
