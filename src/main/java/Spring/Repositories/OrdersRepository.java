package Spring.Repositories;

import Baskets.Order;
import Baskets.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,LinkedList<Order>>{

}
