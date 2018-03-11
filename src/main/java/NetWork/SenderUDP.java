package NetWork;

import java.io.IOException;
import java.net.*;
import java.util.UUID;

public class SenderUDP  extends Thread{
    private DatagramSocket ds;
    public boolean fRun=true;
    private byte[] buf;
    private int port;
    private String host;


    public SenderUDP(String host,int port){
        try {
            ds=new DatagramSocket();
            this.host=host;
            this.port=port;
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void setBuf(byte[] buf) {
        this.buf = buf;
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

    public  void sendNotification(UUID id){
        try {
            DatagramPacket dp=new DatagramPacket( id.toString().getBytes(), id.toString().getBytes().length,InetAddress.getByName("224.0.0.1"),Ports.portUDPNote.port);
            ds.send(dp);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
