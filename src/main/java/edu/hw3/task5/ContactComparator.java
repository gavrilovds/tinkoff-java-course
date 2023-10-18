package edu.hw3.task5;

import java.util.Comparator;

public class ContactComparator implements Comparator<Contact> {

    @Override
    public int compare(Contact o1, Contact o2) {
        if (o1.surname().isEmpty()) {
            if (o2.surname().isEmpty()) {
                return o1.name().compareTo(o2.name());
            }
            return o1.name().compareTo(o2.surname());
        }
        if (o2.surname().isEmpty()) {
            return o1.surname().compareTo(o2.name());
        }
        return o1.surname().compareTo(o2.surname());
    }
}
