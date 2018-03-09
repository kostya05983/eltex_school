package NetWork;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class SenderUDP extends Thread {
//    private int port;
//    private byte[] buf;
//    private InetAddress inetAddress;
//
//
//    public SenderUDP(byte[] buf,InetAddress inetAddress,int port){
//        this.buf=buf;
//        this.inetAddress=inetAddress;
//        this.port=port;
//    }
//
//    public void start(){
//        try {
//            Thread.sleep(1);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//    }
    DatagramSocket ds;

    public SenderUDP(){
        try {
            ds=new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        try {
             ds=new DatagramSocket();

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void closeDatagramSocket(){
        ds.close();
    }

    public void sendMessage(byte[] buf, InetAddress inetAddress,int port){
        DatagramPacket dp=new DatagramPacket(buf,buf.length,inetAddress,port);
    }

}
