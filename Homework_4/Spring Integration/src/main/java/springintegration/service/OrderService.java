package springintegration.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import springintegration.order.Order;

import java.util.List;

/**
 * Created by Aleksandr on 18.11.2020.
 */
@Service("orderService")
public class OrderService {

    final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public List<Order> processList(List<Order> orders) {
        for(Order order : orders) {
            logger.debug("Order is processing: {}, {}, {}", order.getOrderId(), order.getOrderState(), order.getName());
        }
        return orders;
    }
}
