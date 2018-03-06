package Baskets;

import Goods.Good;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.UUID;

public class ShoppingCart <T extends Good> implements Serializable {
    ArrayList<T> goods=new ArrayList();
    TreeSet<UUID> treeSet=new TreeSet<>();


   public void add(T good){
       goods.add(good);
       treeSet.add(good.id);
   }

   public void add(int index,T good){
       goods.add(index,good);
       treeSet.add(good.id);
   }

   public  void delete(T o){
        goods.remove(o);
        Good.count--;
    }


   public Good search(UUID id){
       int i=-1;
       for(UUID uuid:treeSet){
           i++;
           if (uuid.equals(id))
               return goods.get(i);
           }

       return null;
   }

   public void showShoppingCart(){
       StringBuilder stringBuilder=new StringBuilder();

       for(int i=0;i<goods.size();i++){
           stringBuilder.append(this.goods.get(i)+"\n");
       }

       System.out.println(stringBuilder);
   }

    public String toString() {
       StringBuilder stringBuilder=new StringBuilder();

       for(int i=0;i<goods.size();i++){
           stringBuilder.append(this.goods.get(i)+"\n");
       }

    return stringBuilder.toString();
   }

}
