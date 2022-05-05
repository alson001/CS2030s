// @author Alson(Group 12H)

class SERVICE_END extends Event{
  private static final int SERVICE_END = 2;
  private Customer customer;
  private Counter counter;
  private double serviceTime;
  private boolean[] available;

  public SERVICE_END(Customer customer, Counter counter, double time, double serviceTime, boolean[] available){
    super(time);
    this.customer = customer;
    this.counter = counter;
    this.serviceTime = serviceTime;
    this.available = available;
  }

  @Override
  public Event[] simulate(){
    this.available[this.counter.getcounterId()] = true;
    return new Event[] {
      new Departure(this.getTime(), this.customer)
    };
  }

  @Override
  public String toString(){
    String str = "";
    str = this.customer.toString() + String.format("service done ") + this.counter.toString();
    return super.toString() + str; 
  }
}

