package ui;

import model.Employee;
import model.Order;

public class Diner {

    public static void main(String[] args) {
        Employee employee = new Employee();

        for (int i=0; i < 2 ; i++) {
            System.out.println("Table " + (i + 1) + ":\n");

            employee.greet();
            employee.describeDish();
            Order o = employee.takeOrder();

            System.out.println();

            doOrderRoutine(employee, o);
            System.out.println();
        }

        System.out.println();
        employee.doDishes();
    }

    private static void doOrderRoutine(Employee e, Order o) {
        if (o.needsToBePrepared())
            e.makeDish(o);
        System.out.println();
        if (o.isReadyToBeServed())
            e.deliverFood(o);
        if(o.isReadyToBePaid())
            e.takePayment(o);
    }

}
