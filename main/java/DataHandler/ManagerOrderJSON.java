package DataHandler;

import Baskets.Order;
import Baskets.Orders;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.UUID;

public class ManagerOrderJSON extends AManageOrder {

    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;

    public ManagerOrderJSON(String name) {
        this.name=name;
        try {
            bufferedWriter=new BufferedWriter(new FileWriter(name+".json"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Order readById(UUID id) {
       Gson gson=new GsonBuilder().create();
        String buf;
        Orders order;
        try{
           while(bufferedReader.ready()){
               buf=bufferedReader.readLine();
               if(gson.fromJson(buf, Order.class).credentials.getId().equals(id))
                   return gson.fromJson(buf, Order.class);
           }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveById(UUID id, Order order) {
       Gson gson=new GsonBuilder().create();
       String buf;
       File sourceFile=new File(name+".json");
       File outPutFile=new File(name+"b.json");
        try{
            bufferedWriter=new BufferedWriter(new FileWriter(outPutFile));
       while(bufferedReader.ready()){
           buf=bufferedReader.readLine();
           if(gson.fromJson(buf, Order.class).credentials.getId().equals(id)){
               bufferedWriter.write(gson.toJson(order));
               bufferedWriter.newLine();
           }else {
               bufferedWriter.write(buf);
               bufferedWriter.newLine();
           }
       }
            sourceFile.delete();
            outPutFile.renameTo(sourceFile);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Orders readAll() {

        Orders orders=new Orders();
        Gson gson=new GsonBuilder().create();

        String buf;
        try{
            while(bufferedReader.ready()) {
                buf = bufferedReader.readLine();
                orders.addOrder(gson.fromJson(buf, Order.class));
            }

        }catch(IOException e){
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public void saveAll(Orders orders) {
        Gson gson=new GsonBuilder().create();

        try {
            for(int i=0;i<orders.getOrdersList().size();i++) {
                bufferedWriter.write(gson.toJson(orders.getOrdersList().get(i)));
                bufferedWriter.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void closeInput(){
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void closeOutput(){
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeAll(){
        try {

        bufferedReader.close();
       } catch (IOException e) {
           e.printStackTrace();
        }

    }
    @Override
    public void openInput() {
        try {
            bufferedReader=new BufferedReader(new FileReader(name+".json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
