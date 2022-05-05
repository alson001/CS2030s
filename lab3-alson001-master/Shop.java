// @author Alson(Group 12H)

class Shop {
  private Array<Counter> counter;
  private Queue<Customer> queue;

  public Shop(int numOfCounters, int counterQueue, int numOfQueue) {
    this.counter = new Array<Counter>(numOfCounters);
    for (int i = 0; i < numOfCounters; i++) {
      this.counter.set(i, new Counter(true, counterQueue));
    }
    this.queue = new Queue<Customer>(numOfQueue);
  }

  public Counter getAvailableCounter() {
    Counter availableCounter = counter.min();
    if (!availableCounter.getavailability() && availableCounter.isQueueFull()) {
      // counters.min() may return the first element in counter array despite
      // no available counter and queuefull
      return null;
    }
    return availableCounter;
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

  @Override
  public String toString() {
    return this.queue.toString();
  }

}

