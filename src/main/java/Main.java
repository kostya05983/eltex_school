public class Main {

    public static void main(String[] args) {
//1 лаба
//        Good[] good=new Good[Integer.parseInt(args[0])];
//        String flag="";
//
//        if(Integer.parseInt(args[0])<=0)
//            System.out.println("Первый параметр должен принимать значения от 1");
//
//        if(args[1].equals(BuildingMaterials.class.getSimpleName()))
//            for(int i=0;i<Integer.parseInt(args[0]);i++) {
//                good[i] = new BuildingMaterials();
//                good[i].update();
//                good[i].read();
//            }
//        else flag+="0";
//
//        if(args[1].equals(Instruments.class.getSimpleName()))
//            for(int i=0;i<Integer.parseInt(args[0]);i++) {
//                good[i] = new Instruments();
//                good[i].update();
//                good[i].read();
//            }
//        else flag+="0";
//
//        if(args[1].equals(Paints.class.getSimpleName()))
//            for(int i=0;i<Integer.parseInt(args[0]);i++) {
//                good[i] = new Paints();
//                good[i].update();
//                good[i].read();
//            }
//        else flag+="0";
//
//        if (flag.equals("000"))
//            System.out.println("Второй параметр может принимать значения: Goods.BuildingMaterials,Goods.Instruments,Goods.Paints");

        //2 лаба
        /*Baskets.Orders orders=new Baskets.Orders();

        Baskets.Credentials credentials=new Baskets.Credentials();
        credentials.setId(UUID.randomUUID());
        credentials.setName("Граф");
        credentials.setSurname("Дуку");
        credentials.setPatronymic("Графович");
        credentials.setEmail("graph@imperiamail.com");

        Baskets.ShoppingCart shoppingCart=new Baskets.ShoppingCart();
        for(int i=0;i<good.length;i++)
        shoppingCart.add(good[i]);

        shoppingCart.showShoppingCart();

        Baskets.Order order=new Baskets.Order();
        order.credentials=credentials;
        order.shoppingCart=shoppingCart;

        orders.ordersList.add(order);
        orders.makeDeal(order);

        Baskets.Credentials credentialsDart=new Baskets.Credentials();
        credentialsDart.setId(UUID.randomUUID());
        credentialsDart.setName("Энакин");
        credentialsDart.setSurname("Скайуокер");
        credentialsDart.setPatronymic("Вейдер");
        credentialsDart.setEmail("dart@imperialmail.com");

        Goods.Good[] goodsDart=new Goods.Good[2];
        goodsDart[0]=new Goods.BuildingMaterials();
        goodsDart[0].update();
        goodsDart[0].read();

        goodsDart[1]=new Goods.BuildingMaterials();
        goodsDart[1].update();
        goodsDart[1].read();

        UUID uuid=goodsDart[0].id;

        Baskets.ShoppingCart shoppingCartDart=new Baskets.ShoppingCart();
        shoppingCartDart.add(goodsDart[0]);
        shoppingCartDart.add(goodsDart[1]);


        shoppingCartDart.delete(shoppingCartDart.search(uuid));//Проверка функции поиска

        shoppingCartDart.showShoppingCart();

        Baskets.Order orderDart=new Baskets.Order();
        orderDart.credentials=credentialsDart;
        orderDart.shoppingCart=shoppingCartDart;

        orders.ordersList.add(orderDart);
        orders.makeDeal(orderDart);
        orders.showOrdersList();
        System.out.println("================================================");
        orders.ordersList.getFirst().status=true;
        orders.checkOrders();
        orders.showOrdersList();


        try {
            Thread.sleep(2000);
        }catch(InterruptedException e)
        {
            System.out.println(e);
        }
        System.out.println("======================");
        orders.checkOrders();

        orders.showOrdersList();*/
        //3 лаба
//        Orders orders=new Orders();
//        Credentials credentials=new Credentials();
//        credentials.setId(UUID.randomUUID());
//        credentials.setName("Граф");
//        credentials.setSurname("Дуку");
//        credentials.setPatronymic("Графович");
//        credentials.setEmail("graph@imperiamail.com");
//
//        ShoppingCart shoppingCart=new ShoppingCart();
//        for(int i=0;i<good.length;i++)
//            shoppingCart.add(good[i]);
//
//        shoppingCart.add(new BuildingMaterials());
//        shoppingCart.add(new Paints());
//
//
//        Order order=new Order();
//        order.credentials=credentials;
//        order.shoppingCart=shoppingCart;
//
//        orders.ordersList.add(order);
//        orders.makeDeal(order);
//
//
//        //При использовании Generic класса нельзя обратиться к полям класса,который содержится в листе generic класса
//        orders.showOrdersList();

        //4 лаба
//        Scanner scanner=new Scanner(System.in);
//        String data;
//        RunHelper runHelper=new RunHelper();
//        while (true) {
//            data=scanner.nextLine();
//            switch(data){
//                            case "test":
//                                System.out.println("Введите колличество генерирующих потоков");
//                                runHelper.testThreads(scanner.nextInt());
//                                continue;
//                            case "start":
//                                runHelper.startThreads();
//                                System.out.println("Потоки запущены");
//                                continue;
//                            case "stop":
//                                runHelper.stopThreads();
//                                System.out.println("потоки начали остановку");
//                                continue;
//                            case "exit":
//                                runHelper.stopThreads();
//                                break;
//                                default:
//                                    continue;
//                        }
//                        break;
//        }





    }
}