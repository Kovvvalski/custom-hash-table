package by.kovalski.hashtable;

import by.kovalski.hashtable.hashtable.HashTable;
import by.kovalski.hashtable.hashtable.impl.LinearHashTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Set;

public class HashTableTest {

  static class Key {
    String data;

    public Key(String data) {
      this.data = data;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Key key = (Key) o;
      return Objects.equals(data, key.data);
    }

    @Override
    public int hashCode() {
      return Objects.hash(data);
    }
  }

  @Test
  void put() {
    HashTable<Key, Integer> hashTable = new LinearHashTable<>();
    Assertions.assertNull(hashTable.put(new Key("one"), 1));
    Assertions.assertEquals(1, hashTable.put(new Key("one"), 1));
  }

  @Test
  void putNull() {
    HashTable<Key, Integer> hashTable = new LinearHashTable<>();
    Assertions.assertNull(hashTable.put(null, 1));
    Assertions.assertEquals(1, hashTable.put(null, 1));
  }

  @Test
  void get() {
    HashTable<Key, Integer> hashTable = new LinearHashTable<>();
    Assertions.assertNull(hashTable.put(new Key("one"), 1));
    Assertions.assertEquals(1, hashTable.get(new Key("one")));
  }

  @Test
  void getNull() {
    HashTable<Key, Integer> hashTable = new LinearHashTable<>();
    Assertions.assertNull(hashTable.put(null, 1));
    Assertions.assertEquals(1, hashTable.get(null));
  }

  @Test
  void remove() {
    HashTable<Key, Integer> hashTable = new LinearHashTable<>();
    hashTable.put(new Key("one"), 1);
    Assertions.assertEquals(1, hashTable.remove(new Key("one")));
  }

  @Test
  void removeNull() {
    HashTable<Key, Integer> hashTable = new LinearHashTable<>();
    hashTable.put(null, 1);
    Assertions.assertEquals(1, hashTable.remove(null));
  }

  @Test
  void removeNotExisting() {
    HashTable<Key, Integer> hashTable = new LinearHashTable<>();
    hashTable.put(new Key("1"), 1);
    Assertions.assertNull(hashTable.remove(new Key("2")));
  }

  @Test
  void size() {
    HashTable<Key, Integer> hashTable = new LinearHashTable<>();
    for (int i = 0; i < 1000; i++) {
      hashTable.put(new Key(Integer.toString(i)), i);
    }
    hashTable.remove(new Key("1"));
    hashTable.remove(new Key("2"));
    hashTable.remove(new Key("3"));
    Assertions.assertEquals(997, hashTable.size());
  }

  @Test
  void aLotElementsTest() {
    HashTable<Key, Integer> hashTable = new LinearHashTable<>();
    for (int i = 0; i < 10_000; i++) {
      hashTable.put(new Key(Integer.toString(i)), i);
    }
    Set<Key> set = hashTable.getKeySet();
    for (Key key : set) {
      Assertions.assertEquals(Integer.parseInt(key.data), hashTable.get(key));
    }
  }

}
