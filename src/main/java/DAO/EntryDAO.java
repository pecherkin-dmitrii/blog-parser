package DAO;

import entity.Entry;

import java.util.LinkedList;


public interface EntryDAO {

    void add(LinkedList<Entry> entries);

    boolean alreadyExists(String link);
}
