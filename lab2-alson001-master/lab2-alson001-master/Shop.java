// @author Alson(Group 12H)

class Shop {
  private Counter[] counter;
  private Queue queue;

  public Shop(int numOfCounters, int numOfQueue) {
    this.counter = new Counter[numOfCounters];
    for (int i = 0; i < numOfCounters; i++) {
      this.counter[i] = new Counter(i, true);
    }
    this.queue = new Queue(numOfQueue);
  }

  public Counter getAvailableCounter(int i) {
    return counter[i];
  }

  public int length() {
    return this.counter.length;
  }

  public boolean addQueue(Customer customer) {
    return this.queue.enq(customer);
  }

  public Customer leaveQueue() {
    return (Customer) this.queue.deq();
  }

  public boolean checkQueue() {
    return this.queue.isFull();
  }

  public boolean emptyQueue() {
    return this.queue.isEmpty();
  }

  @Override
  public String toString() {
    return this.queue.toString();
  }

}

