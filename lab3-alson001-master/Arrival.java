// @author Alson(Group 12H)

class Arrival extends Event {
  private Customer customer;
  private Shop shop;

  public Arrival(Customer customer, double time, Shop shop) {
    super(time);
    this.customer = customer;
    this.shop = shop;
  }

  @Override
  public Event[] simulate() {
    Counter availableCounter = customer.goToCounter(shop);
    if (this.shop.isQueueFull() && availableCounter == null) {
      return new Event[] {
        new Departure(this.getTime(), this.customer)
      };
    } else {
      if (availableCounter == null) {
        return new Event[] {
          new QueueEvent(this.customer, this.getTime(), this.shop) 
        }; 
      } else if (availableCounter.getavailability()) {
        return new Event[] {
          new ServiceBegin(this.customer, this.shop.getAvailableCounter(), 
              this.getTime(), this.shop) 
        }; 
      } else {
        return new Event[] {
          new CounterQueue(this.customer, this.getTime(), availableCounter)
        };
      }
    }
  }

  @Override 
  public String toString() {
    return super.toString() + ": " + this.customer.toString() + " arrived " 
         + this.shop.toString();
  }
}


