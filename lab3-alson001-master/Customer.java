// @author Alson(Group 12H)

class Customer {
  private static int counter = 0;
  private int customerId;
  private double serviceTime;

  public Customer(double serviceTime) {
    this.customerId = counter++;
    this.serviceTime = serviceTime;
  }

  public int getId() {
    return this.customerId;
  }

  public double getTime() {
    return this.serviceTime;
  }

  public Counter goToCounter(Shop s) {
    return s.getAvailableCounter();
  }

  @Override
  public String toString() {                                                 
    return String.format("C%d", this.customerId);
  }
}
