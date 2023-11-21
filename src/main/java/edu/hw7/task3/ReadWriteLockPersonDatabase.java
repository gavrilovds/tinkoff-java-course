package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.jetbrains.annotations.Nullable;

public class ReadWriteLockPersonDatabase implements PersonDatabase {

    private final Map<String, List<Person>> nameCache;
    private final Map<String, List<Person>> addressCache;
    private final Map<String, List<Person>> phoneNumberCache;
    private final Map<Integer, Person> personStorage;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public ReadWriteLockPersonDatabase() {
        nameCache = new HashMap<>();
        addressCache = new HashMap<>();
        phoneNumberCache = new HashMap<>();
        personStorage = new HashMap<>();
    }

    @Override
    public void add(Person person) {
        readWriteLock.writeLock().lock();
        try {
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
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        nameCache.remove(personStorage.get(id).name());
        addressCache.remove(personStorage.get(id).address());
        phoneNumberCache.remove(personStorage.get(id).phoneNumber());
        personStorage.remove(id);
    }

    @Override
    @Nullable
    public List<Person> findByName(String name) {
        readWriteLock.readLock().lock();
        try {
            List<Person> cachedPersons =  nameCache.get(name);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    @Nullable
    public List<Person> findByAddress(String address) {
        return addressCache.get(address);
    }

    @Override
    @Nullable
    public List<Person> findByPhone(String phone) {
        return phoneNumberCache.get(phone);
    }
}
