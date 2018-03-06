package Goods;

import java.util.Scanner;

public class BuildingMaterials extends Good {

    public BuildingMaterials(){
        this.count++;
    }

    @Override
    public void read() {
        System.out.println("id товара "+this.id+" Название строительного материала: "+this.name+"Артикул: "+this.venderCode+" Цена: "
                + this.price+" Колличество товаров: "+this.count+" компания производитель "+this.companyManufacture);
    }

    @Override
    public void update() {
        Scanner scanner=new Scanner(System.in);

            System.out.print("Введите название строительного материала:");
            this.name = scanner.next();

            System.out.print("Введите Артикул:");
            this.venderCode=scanner.next();

            System.out.print("Введите Цену:");
            this.price=scanner.next();

            System.out.print("Введите Производителя:");
            this.companyManufacture=scanner.next();



    }

    public String toString(){
        return "id товара "+this.id+" Название строительного материала: "+this.name+"Артикул: "+this.venderCode+" Цена: "
                + this.price+" Колличество товаров: "+this.count+" компания производитель "+this.companyManufacture;
    }

}
