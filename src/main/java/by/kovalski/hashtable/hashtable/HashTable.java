package by.kovalski.hashtable.hashtable;


import java.util.Set;

/**
 * before using this data structure override hashcode and equals
 * @param <K>
 * @param <V>
 */

public interface HashTable <K, V>{
  /**
   * puts value and associates it with key
   * @param key - any object
   * @param value - any object
   * @return previous value if it existed or null
   */
  V put(K key, V value);

  /**
   * @param key - any object
   * @return value associated with this key or null
   */
  V get(K key);

  /**
   * removes value associated with this key
   * @param key - any object
   * @return old value if it existed else null
   */
  V remove(K key);

  /**
   * @return Set of all keys in map
   */
  Set<K> getKeySet();

  int size();
}
