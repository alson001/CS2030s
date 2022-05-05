//@author Alson(Group 12H)

class Counter implements Comparable<Counter> {
  private static int counter = 0;
  private int counterid;
  private boolean available;
  private Queue<Customer> queue;

  public Counter(boolean available, int counterQueue) {
    this.counterid = counter++;
    this.available = available;
    this.queue = new Queue<Customer>(counterQueue);
  }

  public int getcounterId() {
    return this.counterid;
  }

  public boolean getavailability() {
    return this.available;
  }

  public void counterUsed() {
    this.available = false;
  }

  public void counterFreed() {
    this.available = true;
  }

  public boolean addQueue(Customer customer) {
    return this.queue.enq(customer);
  }

  public Customer leaveQueue() {
    return (Customer) this.queue.deq();
  }

  public boolean isQueueFull() {
    return this.queue.isFull();
  }

  public boolean isQueueEmpty() {
    return this.queue.isEmpty();
  }

  public int queueLength() {
    return this.queue.length();
  }

  @Override
  public int compareTo(Counter c) {
    if (this.getavailability() && !c.getavailability()) {
      return -1;
    } else if (!this.getavailability() && c.getavailability()) {
      return 1;
    } else if (this.getavailability() && c.getavailability()) {
      if (this.getcounterId() < c.getcounterId()) {
        return -1;
      } else {
        return 1;
      }
    } else if (!this.getavailability() && !c.getavailability()) {
      if (this.queueLength() < c.queueLength()) {
        return -1;
      } else if (this.queueLength() > c.queueLength()) {
        return 1;
      } else {
        if (this.getcounterId() < c.getcounterId()) {
          return -1;
        } else {
          return 1;
        }
      }
    }
    return 1;
  }

  @Override
  public String toString() {
    return String.format("S%d", this.counterid);
  }

  public String queueString() {
    return this.queue.toString();
  }
}
