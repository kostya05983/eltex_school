import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.nio.ByteBuffer;

public class Client {

    private static final int MESSAGELEN=1024;

    public static void main(String[] args) {
        String host="LocalHost";

//        int arg1=15;
//        int arg2=2;

        byte[] buf=new byte[4];
        int port;
        InetAddress ip;
        try {
            DatagramSocket ds = new DatagramSocket(Param.portUdp);
            DatagramPacket dp = new DatagramPacket(buf, buf.length);

                ds.receive(dp);


//            System.out.println("Я получил ее");
            ds.close();
            Server.fRun=false;
            port= ByteBuffer.wrap(dp.getData()).getInt(0);
            ip=dp.getAddress();
            //Устанавливаем TCP
            Socket soc=new Socket(ip,port);
            OutputStream os=soc.getOutputStream();
            os.write("лол".getBytes());
            os.close();
            soc.close();
        } catch (SocketException e) {
            e.printStackTrace();
        }catch(IOException e){
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
