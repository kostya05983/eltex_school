package NetWork;

public enum Ports {

     portUDP(3334),
    portUDPNote(3335);

    public int port;
    private Ports(int port){
        this.port=port;
    }
}
