package Goods;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

@Entity
@Table(name="good")
public abstract class Good implements ICrudAction,Serializable {
    @Id
    @Column(name="id")
    private UUID id;

    @NotNull
    @Column(name="name")
    String name;

    @NotNull
    @Column(name="venderCode")
    String venderCode;

    @NotNull
    @Column(name="price")
    String price;

    @NotNull
    @Column(name="count")
    public static int count=0;

    @NotNull
    @Column(name="companyManufacture")
    String companyManufacture;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
