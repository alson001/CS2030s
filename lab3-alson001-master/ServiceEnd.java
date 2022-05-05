// @author Alson(Group 12H)

class ServiceEnd extends Event {
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
    if (this.counter.isQueueEmpty()) {
      if (this.shop.isQueueEmpty()) {
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
    } else {
      if (this.shop.isQueueEmpty()) {
        return new Event[] {
          new Departure(this.getTime(), this.customer),
          new ServiceBegin(this.counter.leaveQueue(), this.counter,
              this.getTime(), this.shop)
        };
      } else {
        return new Event[] {
          new Departure(this.getTime(), this.customer),
          new ServiceBegin(this.counter.leaveQueue(), this.counter,
              this.getTime(), this.shop),
          new CounterQueue(this.shop.leaveQueue(), this.getTime(), this.counter)
        };
      }
    }
  }



  @Override
  public String toString() {
    return super.toString() + ": " + this.customer.toString() + " service done (by "
         + this.counter.toString() + " " + this.counter.queueString() + ")";
  }
}

