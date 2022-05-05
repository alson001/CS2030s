// @author Alson(Group 12H)

class CounterQueue extends Event {
  private Customer customer;
  private Counter counter;

  public CounterQueue(Customer customer, double time, Counter counter) {
    super(time);
    this.customer = customer;
    this.counter = counter;
  }

  @Override
  public Event[] simulate() {
    this.counter.addQueue(this.customer);
    return new Event[] {};
  }
    
  @Override
  public String toString() {
    return super.toString() + ": " + this.customer.toString() +
           " joined counter queue (at " + this.counter.toString() +
           " " + this.counter.queueString() + ")";
  } 
}
