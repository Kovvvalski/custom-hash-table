package by.kovalski.hashtable.entity;

import java.util.Objects;

public class Key {
  private String data;

  public Key(String data) {
    this.data = data;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Key key1 = (Key) o;
    return Objects.equals(data, key1.data);
  }

  @Override
  public int hashCode() {
    //return Objects.hash(data);
    return 1;
  }

  @Override
  public String toString() {
    return data;
  }
}
