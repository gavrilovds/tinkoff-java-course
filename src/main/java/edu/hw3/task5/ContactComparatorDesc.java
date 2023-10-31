package edu.hw3.task5;

import java.util.Comparator;

public class ContactComparatorDesc implements Comparator<Contact> {

    @Override
    public int compare(Contact o1, Contact o2) {
        String comparableFieldO1 = o1.surname().isEmpty() ? o1.name() : o1.surname();
        String comparableFieldO2 = o2.surname().isEmpty() ? o2.name() : o2.surname();
        return -comparableFieldO1.compareTo(comparableFieldO2);
    }
}
