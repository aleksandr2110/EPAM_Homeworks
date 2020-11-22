package springintegration.order;

import springintegration.state.OrderState;

/**
 * Created by Aleksandr on 18.11.2020.
 */
public class Order {
    // you need to create these two endpoints between them using SI
    private long orderId;
    private OrderState orderState;
    private String name;

    public Order (long orderId, OrderState orderState, String name) {
        this.orderId = orderId;
        this.orderState = orderState;
        this.name = name;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
