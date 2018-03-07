//import java.net.DatagramPacket;
//import java.net.DatagramSocket;
//
//public class Client {
//
//    private final int MESSAGELEN=1024;
//
//    public static void main(String[] args) {
//        String host="LocalHost";
//
//        int arg1=15;
//        int arg2=2;
//
//        try{
//            String portStr=UDP.reciveMessage(Param.portUdp);
//            port=new Integer(portStr);
//
//            System.out.println();
//        }
//    }
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
//}
