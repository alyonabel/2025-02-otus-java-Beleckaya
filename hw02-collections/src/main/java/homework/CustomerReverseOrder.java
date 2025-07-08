package homework;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class CustomerReverseOrder {

    static LinkedHashSet<Customer> set = new LinkedHashSet<>();
    static int count = 0;
    //todo: 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"

    public void add(Customer customer) {
        set.add(customer);
    }

    public Customer take() {
        LinkedList<Customer> list = new LinkedList<>(set);
        Iterator<Customer> customerIterator = list.listIterator();
        if (customerIterator.hasNext())
            count++;
        return  list.get(list.size()-count);
    }
}

