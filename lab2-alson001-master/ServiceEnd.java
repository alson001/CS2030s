// @author Alson(Group 12H)

class ServiceEnd extends Event {
  private static final int SERVICE_END = 2;
  private Customer customer;
  private Counter counter;
  private Shop shop;

  public ServiceEnd(Customer customer, Counter counter, 
         double time, Shop shop) {
    super(time);
    this.customer = customer;
    this.counter = counter;
    this.shop = shop;
  }

  @Override
  public Event[] simulate() {
    this.counter.counterFreed();
    if (this.shop.emptyQueue()) {
      return new Event[] {
        new Departure(this.getTime(), this.customer)
      };
    } else {
      return new Event[] {
        new Departure(this.getTime(), this.customer),
        new ServiceBegin(this.shop.leaveQueue(), this.counter,
            this.getTime(), this.shop)
      };
    }
  }

  @Override
  public String toString() {
    return super.toString() + ": " + this.customer.toString() + " service done (by "
         + this.counter.toString() + ")";
  }
}

