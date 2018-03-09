package NetWork;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.ByteBuffer;

public class Server {
    public static boolean  fRun=true;
    public static void main(String[] args) {

            //System.out.println("NetWork.Server is running");


            byte[] ib;//Порт на соединение
            byte[] ob=new byte[256];
            String host="LocalHost";
            ByteBuffer byteBuffer=ByteBuffer.allocate(4);
            byteBuffer.putInt(Ports.portTCP.port);
            ib=byteBuffer.array();


            try {

                SenderUDP senderUDP=new SenderUDP();
                senderUDP.sendMessage(ib,InetAddress.getByName(host),Ports.portUDP.port);




                ServerSocket ss=new ServerSocket(Ports.portTCP.port);
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
