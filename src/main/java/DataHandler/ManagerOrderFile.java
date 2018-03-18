package DataHandler;

import Baskets.Order;
import Baskets.Orders;

import java.io.*;
import java.util.UUID;

public class ManagerOrderFile extends AManageOrder{

    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private FileOutputStream fileOutputStream;
    private FileInputStream fileInputStream;


    public ManagerOrderFile(String name) {
        this.name=name;
    }

    public void openInput()throws FileNotFoundException,IOException{
            fileInputStream = new FileInputStream(name+".txt");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        objectInputStream=new ObjectInputStream(fileInputStream);

    }
    public void openOutput()throws FileNotFoundException,IOException{
            fileOutputStream=new FileOutputStream(name+".txt",true);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        objectOutputStream=new ObjectOutputStream(fileOutputStream);

    }

    @Override
    public Order readById(UUID id) {
        Order order;
        try {
            while (fileInputStream.available()>0) {
                order = (Order)objectInputStream.readObject();
                if(order.credentials.getId().equals(id))
                    return order;
            }
        }catch(ClassNotFoundException e){
         e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


    public void saveById(UUID id, Order order) {
        Order buf;
        try {
            while (fileInputStream.available()>0){
                buf = (Order) objectInputStream.readObject();
                if(buf.credentials.getId().equals(id)) {
                    objectOutputStream.writeObject(order);
                    return;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public Orders readAll() {
        Orders orders=new Orders();
        try {
            while (fileInputStream.available() >0) {
                orders.getOrdersList().add(objectInputStream.readObject());
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void saveAll(Orders orders) {
        try {
            for (int i = 0; i < orders.getOrdersList().size(); i++) {
                objectOutputStream.writeObject(orders.getOrdersList().get(i));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void closeAll(){
        try {
            fileInputStream.close();
            fileOutputStream.close();
            objectInputStream.close();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void closeInput(){
        try {
            fileInputStream.close();
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void closeOutput(){
        try {
            fileOutputStream.close();
            objectOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
