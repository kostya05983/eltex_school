package Spring;

public class RequestException extends Exception {
    public RequestException(int number){
        switch(number){
            case 1:
                System.out.println("нет такого заказа");
                break;
            case 2:
                System.out.println("файл поврежден");
                break;
            case 3:
                System.out.println("неправильная команда");
                break;

        }
    }
}
