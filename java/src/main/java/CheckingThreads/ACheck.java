package CheckingThreads;

import Baskets.Orders;

public abstract class ACheck extends Thread{
    protected Orders orders;
    protected boolean isActive=true;

    public ACheck(Orders orders){
        this.orders=orders;
    }
}
