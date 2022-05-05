/**
 * A boolean condition with an integer parameter y that can be 
 * apply to another integer x.  Returns true if x is divisible 
 * by y, false otherwise.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Put Your Name (Lab Group)
 */

class DivisibleBy implements BooleanCondition<Integer> {

  private Integer number1;

  public DivisibleBy(Integer number1) {
    this.number1 = number1;
  }
  @Override
  public boolean test(Integer number2) {
    if (number2 % number1 == 0) {
      return true;
    } else {
      return false;
    }
  }
}

