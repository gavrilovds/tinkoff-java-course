package edu.hw8.task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ProductHelper {

    public <T> Iterable<List<T>> product(List<T>... lists) {
        int total = 1;
        int[] max = new int[lists.length];
        for (int i = 0; i < lists.length; i++) {
            max[i] = lists[i].size();
        }
        int[] initProduct = new int[lists.length];
        Arrays.fill(initProduct, 1);
        for (List<T> list : lists) {
            total *= list.size();
        }
        int finalTotal = total;
        return () -> new Iterator<>() {
            int index = -1;
            int[] presentProduct;

            @Override
            public boolean hasNext() {
                index++;
                return index < finalTotal;
            }

            @Override
            public List<T> next() {
                if (index == 0) {
                    presentProduct = initProduct;
                } else {
                    presentProduct = generateNextProduct(presentProduct, max);
                }
                List<T> result = new ArrayList<>();
                for (int i = 0; i < presentProduct.length; i++) {
                    result.add(lists[i].get(presentProduct[i] - 1));
                }
                return result;
            }
        };
    }

    private int[] generateNextProduct(int[] curr, int[] max) {
        int n = curr.length - 1;
        curr[n]++;
        for (int i = n; i > 0; i--) {
            if (curr[i] > max[i]) {
                curr[i] = 1;
                curr[i - 1]++;
            }
        }
        return curr;
    }
}
