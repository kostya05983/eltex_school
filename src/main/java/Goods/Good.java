package Goods;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

public abstract class Good implements ICrudAction,Serializable {
    public UUID id;
    protected String name;
    protected String venderCode;
    protected String price;
    public static int count=0;
    protected String companyManufacture;

    public Good(){
        create();
    }

    public void create(){
        Random random=new Random(System.currentTimeMillis());
        id=UUID.randomUUID();
        name="Goods.Good"+count;
        venderCode=String.valueOf(random.nextDouble());
        price=String.valueOf(random.nextInt(Integer.MAX_VALUE));
        companyManufacture="Manufacture";
    }

    public void delete(){
        id=null;
        name=null;
        venderCode=null;
        price=null;
        count--;
        companyManufacture=null;
    }
}
