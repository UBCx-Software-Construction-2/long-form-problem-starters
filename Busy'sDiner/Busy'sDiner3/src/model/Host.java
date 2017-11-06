package model;

import java.util.List;

public class Host extends Server {

    private static final String ERROR = "ERROR!! ";
    private static final String PREFIX = "HOST - ";

    public Host(Dish dish) {
        super(dish);
    }

    @Override
    public double getCash() {
        System.out.println(ERROR + "Host has no cash!");
        return 0.0;
    }

    @Override
    public List<Order> getActiveOrders() {
        System.out.println(ERROR + "Host does not have any active orders!");
        return null;
    }

    @Override
    public Order takeOrder() {
        System.out.println(ERROR + "Hosts can't take orders!");
        return null;
    }

    @Override
    public void takePayment(Order order) {
        System.out.println(ERROR + "Host can't take payment!");
    }

    @Override
    //MODIFIES: this, order
    //EFFECTS: logs order as served and brings to table
    public void deliverFood(Order order) {
        order.setIsServed();
        System.out.print(PREFIX + "Delivered food: ");
        order.print();
    }

}
