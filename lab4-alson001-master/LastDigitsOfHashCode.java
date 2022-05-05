/**
 * A transformer with a parameter k that takes in an object x 
 * and outputs the last k digits of the hash value of x.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Alson(Group 12H)
 */

class LastDigitsOfHashCode implements Transformer<Object,Integer> {
  
  private int lastDigits;
  
  public LastDigitsOfHashCode(int lastDigits) {
    this.lastDigits = lastDigits;
  }

  @Override
  public Integer transform(Object a) {
    int hashCode = a.hashCode();
    int temp = 0;
    for (int i = 0; i < lastDigits; i++) {
      int b = hashCode % 10;
      hashCode = hashCode / 10;
      temp = ((int) Math.pow(10, i)) * b + temp;
    }
    return (Integer) Math.abs(temp);
  }  
}

