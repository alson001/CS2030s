// @author Alson(Group 12H)

class QueueEvent extends Event {
  private Customer customer;
  private Shop shop;

  public QueueEvent(Customer customer, double time, Shop shop) {
    super(time);
    this.customer = customer;
    this.shop = shop;
  }

  @Override
  public Event[] simulate() {
    shop.addQueue(this.customer);
    return new Event[]{};
  }

  @Override
  public String toString() {
    return super.toString() + ": " + this.customer.toString() + " joined queue " 
         + this.shop.toString();
  }
}


