package edu.hw3.task6;

import java.util.PriorityQueue;
import java.util.Queue;

public class MoscowExchange implements StockMarket {

    private final Queue<Stock> stockList;
    private static final String NULL_STOCK_MESSAGE = "Stock can`t be null";

    public MoscowExchange() {
        stockList = new PriorityQueue<>(new StockComparator());
    }

    @Override
    public void add(Stock stock) {
        if (stock == null) {
            throw new IllegalArgumentException(NULL_STOCK_MESSAGE);
        }
        stockList.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        if (stock == null) {
            throw new IllegalArgumentException(NULL_STOCK_MESSAGE);
        }
        stockList.remove(stock);
    }

    @Override
    public Stock getMostValuebleStock() {
        return stockList.peek();
    }
}
