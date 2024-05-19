package by.kovalski.hashtable;

import by.kovalski.hashtable.entity.Key;
import by.kovalski.hashtable.hashtable.HashTable;
import by.kovalski.hashtable.hashtable.impl.LinearHashTable;

import java.util.Set;

public class Main {

  private static final int ITERATIONS = 40;

  public static void main(String[] args) {
    HashTable<Key, Integer> hashTable = new LinearHashTable<>();
    for (int i = 0; i < ITERATIONS; i++) {
      hashTable.put(new Key(Integer.toString(i)), i);
    }

   hashTable.remove(new Key("1"));
   hashTable.remove(new Key("2"));
   hashTable.remove(new Key("3"));


    Set<Key> set = hashTable.getKeySet();
    int i = 0;
    for (Key key : set) {
      System.out.println(key + "  ->  " + hashTable.get(key));

    }
  }

}
