import NetWork.Client;
import NetWork.Server;

public class Lab6{

    public static void main(String[] args) {
        Client client=new Client();
        Thread clientth=new Thread(client);
        clientth.start();
        Client client1=new Client();
        Thread clientth1=new Thread(client1);
        clientth1.start();
        Server server=new Server();
        Thread serverth=new Thread(server);
        serverth.start();


    }
}
