// @author Alson(Group 12H)

class Customer {
  private int customerId;
  private double serviceTime;

  public Customer(int customerId, double serviceTime) {
    this.customerId = customerId;
    this.serviceTime = serviceTime;
  }

  public int getId() {
    return this.customerId;
  }

  public double getTime() {
    return this.serviceTime;
  }

  @Override
  public String toString() {                                                 
    String str = "";                                                             
    str = String.format("C%d", this.customerId);
    return str;
  }
}
