package NetWork;

public enum Ports {

     portTCP(5333),
     portUDP(5334),
     portUDPNote(5335);


    protected int port;
    private Ports(int port){
        this.port=port;
    }
}
