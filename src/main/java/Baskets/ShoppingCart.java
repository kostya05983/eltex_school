package Baskets;

import Goods.Good;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.UUID;


public class ShoppingCart <T extends Good> implements Serializable {
    @OneToMany(cascade = CascadeType.ALL,
    fetch = FetchType.LAZY,
    mappedBy = "shoppingCart")
    ArrayList<T> goods=new ArrayList();
    TreeSet<UUID> treeSet=new TreeSet<>();



   public void add(T good){
       goods.add(good);
       treeSet.add(good.getId());
   }

   public void add(int index,T good){
       goods.add(index,good);
       treeSet.add(good.getId());
   }

   public  void delete(T o){
        goods.remove(o);
        Good.count--;
    }


   public Good search(UUID id){

       for(int i=0;i<goods.size();i++){
           System.out.println("я тут");
           if(goods.get(i).getId().equals(id))
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

    public <T> List<T> deserializeList(String json, Type type) {
        Gson gson = new Gson();
        return  gson.fromJson(json, type);
    }

}
