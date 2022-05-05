/**
 * A boolean condition with parameter x that can be applied to
 * a string.  Returns true if the string is longer than x; false
 * otherwise.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Alson(Group 12H)
 */

class LongerThan implements BooleanCondition<String> {
  
  private int wordlimit;

  public LongerThan(int wordlimit){
    this.wordlimit = wordlimit;
  }

  @Override 
  public boolean test(String word) {
    if (word.length() <= this.wordlimit) {
      return false;
    } else {
      return true;
    }
  }
}

