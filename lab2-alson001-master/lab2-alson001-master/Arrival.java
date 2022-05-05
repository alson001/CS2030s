// @author Alson(Group 12H)

class Arrival extends Event {
  private static final int ARRIVAL = 0;
  private Customer customer;
  private Shop shop;

  public Arrival(Customer customer, double time, Shop shop) {
    super(time);
    this.customer = customer;
    this.shop = shop;
  }

  @Override
  public Event[] simulate() {
    int counter = -1;
    for (int i = 0; i < this.shop.length(); i += 1) {
      if (this.shop.getAvailableCounter(i).getavailability()) {
        counter = i;
        break;
      }
    }
    if (counter == -1) {
      if (shop.checkQueue()) {
        return new Event[] {
          new Departure(this.getTime(), this.customer)
        };
      } else {
        return new Event[] {
          new QueueEvent(this.customer, this.getTime(), this.shop)
        };
      }
    } else {
      return new Event[] {
          new ServiceBegin(this.customer, this.shop.getAvailableCounter(counter), 
              this.getTime(), this.shop)
      };
    }
  }

  @Override 
  public String toString() {
    return super.toString() + ": " + this.customer.toString() + " arrived " 
         + this.shop.toString();
  }
}


