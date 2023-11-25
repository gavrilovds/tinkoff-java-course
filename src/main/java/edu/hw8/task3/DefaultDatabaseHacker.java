package edu.hw8.task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;

public class DefaultDatabaseHacker implements DatabaseHacker {

    private static final String ALF = "qwertyuiopasdfghjklzxcvbnm0123456789";
    private final ProductHelper productHelper;
    private static final int MIN_PASSWORD_LENGTH = 4;
    private static final int MAX_PASSWORD_LENGTH = 6;

    public DefaultDatabaseHacker() {
        productHelper = new ProductHelper();
    }

    @Override
    public Map<String, String> hack(Map<String, String> leakedDatabase) {
        Map<String, String> hackedData = new HashMap<>();
        Map<String, String> leakedDatabaseCopy = new HashMap<>(leakedDatabase);
        while (!leakedDatabaseCopy.isEmpty()) {
            for (int i = MIN_PASSWORD_LENGTH; i <= MAX_PASSWORD_LENGTH; i++) {

            }
        }
        return hackedData;
    }

    public static <T> Iterable<List<T>> product(List<T>... lists) {
        long total = 1;
        int[] max = new int[lists.length];
        for (int i = 0; i < lists.length; i++) {
            max[i] = lists[i].size();
        }
        int[] initProduct = new int[lists.length];
        Arrays.fill(initProduct, 1);
        for (List<T> list : lists) {
            total *= list.size();
        }

        List<List<T>> result = new ArrayList<>();
        int[] presentProduct = initProduct;
        for (int index = 0; index < total; index++) {
            if (index != 0) {
                presentProduct = generateNextProduct(presentProduct, max);
            }
            List<T> productList = new ArrayList<>();
            for (int i = 0; i < presentProduct.length; i++) {
                productList.add(lists[i].get(presentProduct[i] - 1));
            }
            result.add(productList);
        }

        return result;
    }

    public static int[] generateNextProduct(int[] curr, int[] max) {
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

    private boolean checkPass(String generatedPass, Map<String, String> leakedDatabase) {
        String md5hex = DigestUtils.md5Hex(generatedPass);
        return leakedDatabase.containsValue(md5hex);
    }

    public static void exampleProduct() {
        for (var list : product(
            Arrays.asList(ALF.split("")),
            Arrays.asList(ALF.split("")),
            Arrays.asList(ALF.split("")),
            Arrays.asList(ALF.split("")),
            Arrays.asList(ALF.split(""))
        )) {
           /* for (String s : list) {
                System.out.print(s + " ");
            }
            System.out.println();*/
        }
    }

    public static void main(String[] args) { // 0f5b25cd58319cde0e6e33715b66db4c dima
        //System.out.println(new DefaultDatabaseHacker().hack(Map.of("fwre", DigestUtils.md5Hex("dima"))));
        exampleProduct();
    }
}
