package edu.hw3.task6;

import java.util.Comparator;

public class StockComparator implements Comparator<Stock> {

    @Override
    public int compare(Stock o1, Stock o2) {
        return -Double.compare(o1.price(), o2.price());
    }
}
