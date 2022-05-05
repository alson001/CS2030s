/**
 * The Array<T> for CS2030S 
 *
 * @author Alson(Group 12H)
 * @version CS2030S AY21/22 Semester 2
 */
class Array<T extends Comparable<T>> { // TODO: Change to bounded type parameter
  private T[] array;

  Array(int size) {
    // TODO
    @SuppressWarnings({"unchecked", "rawtypes"})
    T[] temp = (T[]) new Comparable[size];
    this.array = temp;
  }

  public void set(int index, T item) {
    // TODO
    this.array[index] = item;
  }

  public T get(int index) {
    // TODO
    return this.array[index];
  }

  public T min() {
    // TODO
    T min = get(0);
    for (int i = 0; i < this.array.length; i++) {
      if (min.compareTo(array[i]) >= 1) {
        min = array[i];
      }
    }
    return min;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < array.length; i++) {
      s.append(i + ":" + array[i]);
      if (i != array.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}
