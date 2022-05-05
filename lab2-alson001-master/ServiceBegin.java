// @author Alson(Group 12H)

class ServiceBegin extends Event {
  private static final int ServiceBegin = 1;
  private Customer customer;
  private Counter counter;
  private Shop shop;

  public ServiceBegin(Customer customer, Counter counter, 
         double time, Shop shop) {
    super(time);
    this.counter = counter;
    this.customer = customer;
    this.shop = shop;
  }

  @Override
  public Event[] simulate() {
    this.counter.counterUsed();
    double endTime = this.getTime() + this.customer.getTime();
    return new Event[] {
      new ServiceEnd(this.customer, this.counter, endTime, this.shop)
    };
  }

  @Override
  public String toString() {
    return super.toString() + ": " + this.customer.toString() + " service begin (by " 
         + this.counter.toString() + ")";
  }
}

