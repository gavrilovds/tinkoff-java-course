package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SynchronizedPersonDatabase implements PersonDatabase {

    private final Map<String, List<Person>> nameCache;
    private final Map<String, List<Person>> addressCache;
    private final Map<String, List<Person>> phoneNumberCache;
    private final Map<Integer, Person> personStorage;

    public SynchronizedPersonDatabase() {
        nameCache = new HashMap<>();
        addressCache = new HashMap<>();
        phoneNumberCache = new HashMap<>();
        personStorage = new HashMap<>();
    }

    @Override
    public void add(Person person) {
        personStorage.put(person.id(), person);
        if (!nameCache.containsKey(person.name())) {
            nameCache.put(person.name(), new ArrayList<>());
        }
        if (!addressCache.containsKey(person.address())) {
            addressCache.put(person.address(), new ArrayList<>());
        }
        if (!phoneNumberCache.containsKey(person.phoneNumber())) {
            phoneNumberCache.put(person.phoneNumber(), new ArrayList<>());
        }
        nameCache.get(person.name()).add(person);
        addressCache.get(person.address()).add(person);
        phoneNumberCache.get(person.phoneNumber()).add(person);
    }

    @Override
    public void delete(int id) {
        nameCache.remove(personStorage.get(id).name());
        addressCache.remove(personStorage.get(id).address());
        phoneNumberCache.remove(personStorage.get(id).phoneNumber());
        personStorage.remove(id);
    }

    @Override
    public List<Person> findByName(String name) {
        return nameCache.get(name);
    }

    @Override
    public List<Person> findByAddress(String address) {
        return addressCache.get(address);
    }

    @Override
    public List<Person> findByPhone(String phone) {
        return phoneNumberCache.get(phone);
    }
}
