package by.kovalski.hashtable.hashtable.impl;


import by.kovalski.hashtable.hashtable.HashTable;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Implementation of Kovalski's HashTable with linear collision solving
 */

public class LinearHashTable<K, V> implements HashTable<K, V> {

  static final int DEFAULT_INITIAL_CAPACITY = 16;

  static final int DEFAULT_CAPACITY_EXTENSION = 16;

  static class Cell<K, V> {
    final K key;
    V value;
    boolean isCollision;

    public Cell(K key, V value, boolean isCollision) {
      this.key = key;
      this.value = value;
      this.isCollision = isCollision;
    }
  }

  static int hash(Object key) {
    int out;
    return (out = key == null ? 0 : key.hashCode()) < 0 ? out * -1 : out;
  }

  /* ----------FIELDS---------- */

  Cell<K, V>[] table;

  int size;

  @SuppressWarnings({"unchecked"})
  public LinearHashTable() {
    table = (Cell<K, V>[]) new Cell[DEFAULT_INITIAL_CAPACITY];
  }

  /* ----------METHODS---------- */

  @Override
  public V put(K key, V value) {
    int index = findIndexOfEqualsOrNull(key);
    if (index == -1) {
      resize();
      index = findIndexOfEqualsOrNull(key);
    }
    V out;
    if (table[index] == null) { // add
      table[index] = new Cell<>(key, value, index != hash(key) % table.length);
      size++;
      out = null;
    } else { // update
      out = table[index].value;
      table[index].value = value;
    }
    return out;
  }

  @Override
  public V get(K key) {
    int index;
    return (index = findIndexOfEquals(key)) == -1 ? null : table[index].value;
  }

  /**
   * NOT OPTIMIZED
   */
  @Override
  @SuppressWarnings({"unchecked"})
  public V remove(K key) {
    int index;
    if ((index = findIndexOfEquals(key)) == -1)
      return null;
    size = 0;
    V out = table[index].value;
    Cell<K, V>[] oldTable = table;
    table = (Cell<K, V>[]) new Cell[table.length];
    for (int i = 0; i < oldTable.length; i++) {
      if (i != index && oldTable[i] != null) {
        put(oldTable[i].key, oldTable[i].value);
      }
    }
    return out;
  }

  @Override
  public Set<K> getKeySet() {
    Set<K> keySet = new HashSet<>();
    for (Cell<K, V> cell : table) {
      if (cell != null)
        keySet.add(cell.key);
    }
    return keySet;
  }

  @Override
  public int size() {
    return size;
  }

  /* ----------PRIVATE METHODS---------- */

  /**
   * @param key - any key
   * @return index of this key in array or -1 if array is full
   */
  int findIndexOfEqualsOrNull(K key) {
    int hash = hash(key);
    int index = hash % table.length;
    Cell<K, V> prev = table[index];
    if (prev == null || Objects.equals(prev.key, key)) {
      return index;
    }

    for (int i = index; i < table.length; i++) {
      prev = table[i];
      if (prev == null || Objects.equals(prev.key, key))
        return i;
    }
    for (int i = 0; i < index; i++) {
      prev = table[i];
      if (prev == null || Objects.equals(prev.key, key))
        return i;
    }

    return -1;
  }

  /**
   * @param key - any key
   * @return index of this key in array or -1 if this index does not exists
   */
  int findIndexOfEquals(K key) {
    int hash = hash(key);
    int index = hash % table.length;
    Cell<K, V> prev = table[index];
    if (prev != null && Objects.equals(prev.key, key)) {
      return index;
    }

    for (int i = index; i < table.length; i++) {
      prev = table[i];
      if (prev != null && Objects.equals(prev.key, key))
        return i;
    }
    for (int i = 0; i < index; i++) {
      prev = table[i];
      if (prev != null && Objects.equals(prev.key, key))
        return i;
    }

    return -1;
  }

  /**
   * resizes table (increases it on DEFAULT_CAPACITY_EXTENSION)
   */
  @SuppressWarnings({"unchecked"})
  final void resize() {
    Cell<K, V>[] oldTable = table;
    table = (LinearHashTable.Cell<K, V>[]) new LinearHashTable.Cell[table.length + DEFAULT_CAPACITY_EXTENSION];
    for (Cell<K, V> cell : oldTable) {
      int index = findIndexOfEqualsOrNull(cell.key);
      table[index] = cell;
    }
  }

}
