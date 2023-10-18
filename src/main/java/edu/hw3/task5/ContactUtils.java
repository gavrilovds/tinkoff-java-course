package edu.hw3.task5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;

public final class ContactUtils {

    private ContactUtils() {
    }

    private static final String VALID_PERSON_REGEX = "^[a-zA-Z]+( [a-zA-Z]+)?$";

    public static List<Contact> parseContacts(String[] peopleInfo, SortOrder sortOrder) {
        if (peopleInfo == null || peopleInfo.length == 0) {
            return Collections.emptyList();
        }
        List<Contact> parsedContacts = new ArrayList<>();
        for (var person : peopleInfo) {
            if (!person.matches(VALID_PERSON_REGEX)) {
                throw new IllegalArgumentException("Wrong person information");
            }
            String[] personInfo = person.split(" ");
            Contact contact;
            if (personInfo.length == 1) {
                contact = new Contact(personInfo[0]);
            } else {
                contact = new Contact(personInfo[0], personInfo[1]);
            }
            parsedContacts.add(contact);
        }
        parsedContacts.sort(new ContactComparator());
        return parsedContacts;
    }
}
