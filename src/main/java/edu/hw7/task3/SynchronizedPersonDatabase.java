package edu.hw7.task3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SynchronizedPersonDatabase implements PersonDatabase {

    private final Map<String, List<Person>> nameCache = new HashMap<>();
    private final Map<String, List<Person>> addressCache = new HashMap<>();
    private final Map<String, List<Person>> phoneNumberCache = new HashMap<>();
    private final Map<Integer, Person> personStorage = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        personStorage.put(person.id(), person);
        nameCache.computeIfAbsent(person.name(), key -> new ArrayList<>()).add(person);
        addressCache.computeIfAbsent(person.address(), key -> new ArrayList<>()).add(person);
        phoneNumberCache.computeIfAbsent(person.phoneNumber(), key -> new ArrayList<>()).add(person);
    }

    @Override
    public synchronized void delete(int id) {
        nameCache.remove(personStorage.get(id).name());
        addressCache.remove(personStorage.get(id).address());
        phoneNumberCache.remove(personStorage.get(id).phoneNumber());
        personStorage.remove(id);
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return nameCache.getOrDefault(name, Collections.emptyList());
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return addressCache.getOrDefault(address, Collections.emptyList());
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return phoneNumberCache.getOrDefault(phone, Collections.emptyList());
    }
}
