package homework;

import java.util.*;

public class CustomerService {

    static NavigableMap <Customer, String> navigableMap = new TreeMap<>((c1, c2) -> (int) (c1.getScores()-c2.getScores()));

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    public Map.Entry<Customer, String> getSmallest(){
        return navigableMap.descendingMap().lastEntry();
    }
    //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return navigableMap.higherEntry(customer);
    }

    public void add(Customer customer, String data) {
        navigableMap.put(customer,data);
    }
}

