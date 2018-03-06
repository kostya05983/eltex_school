package Goods;

import java.util.Scanner;

public class Instruments extends Good {

    public Instruments(){
        this.count++;
    }

    public void read() {
        System.out.println("id товара "+this.id+" Название инструмента: "+this.name+"Артикул: "+this.venderCode+" Цена: "
                + this.price+" Колличество товариров: "+this.count+" компания производитель "+this.companyManufacture);

    }


    public void update() {
       Scanner scanner=new Scanner(System.in);
            System.out.print("Введите название инструмента:");
            this.name = scanner.next();

            System.out.print("\nВведите Артикул:");
            this.venderCode=scanner.next();

            System.out.print("\nВведите Цену:");
            this.price=scanner.next();

            System.out.print("\nВведите Производителя:");
            this.companyManufacture=scanner.next();


    }

    public String toString(){
        return "id товара "+this.id+" Название инструмента: "+this.name+"Артикул: "+this.venderCode+" Цена: "
                + this.price+" Колличество товаров: "+this.count+" компания производитель "+this.companyManufacture;
    }
}
