package NetWork;

import java.io.IOException;
import java.net.*;

public class SenderUDP  extends Thread{
    DatagramSocket ds;
    public boolean fRun=true;
    private byte[] buf;
    private int port;
    private String host;


    public SenderUDP(byte[] buf,String host,int port){
        try {
            ds=new DatagramSocket();
            this.buf=buf;
            this.host=host;
            this.port=port;
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10);
            while(fRun){
                sendMessage(buf,InetAddress.getByName(host),port);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void closeDatagramSocket(){
        ds.close();
    }

    public void sendMessage(byte[] buf, InetAddress inetAddress,int port){
        DatagramPacket dp=new DatagramPacket(buf,buf.length,inetAddress,port);
        try {
            ds.send(dp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void sendNotification(){
        String buf="обработано";
        try {
            System.out.println(buf.getBytes().length);
            DatagramPacket dp=new DatagramPacket(buf.getBytes(),buf.getBytes().length,InetAddress.getByName(host),Ports.portUDPNote.port);
            ds.send(dp);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
