package NetWork;

public enum Ports {

     portTCP(3333),
     portUDP(3334),
    portUDPNote(3335);



    protected int port;
    private Ports(int port){
        this.port=port;
    }
}
