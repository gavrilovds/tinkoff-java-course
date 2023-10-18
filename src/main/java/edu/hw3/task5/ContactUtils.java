package edu.hw3.task5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
            Contact contact = getContact(person.split(" "));
            parsedContacts.add(contact);
        }
        if (sortOrder == SortOrder.DESC) {
            parsedContacts.sort(new ContactComparatorDesc());
        } else {
            parsedContacts.sort(new ContactComparatorAsc());
        }
        return parsedContacts;
    }

    private static Contact getContact(String[] personInfo) {
        Contact contact;
        if (personInfo.length == 1) {
            contact = new Contact(personInfo[0]);
        } else {
            contact = new Contact(personInfo[0], personInfo[1]);
        }
        return contact;
    }
}
