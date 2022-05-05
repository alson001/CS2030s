// @author Alson(Group 12H)

class SERVICE_BEGIN extends Event{
  private static final int SERVICE_BEGIN = 1;
  private Customer customer;
  private Counter counter;
  private double serviceTime;
  private boolean[] available;

  public SERVICE_BEGIN(Customer customer, Counter counter, double time, double serviceTime, boolean[] available){
    super(time);
    this.counter = counter;
    this.customer = customer;
    this.serviceTime = serviceTime;
    this.available = available;
  }

  @Override
  public Event[] simulate(){
    this.available[this.counter.getcounterId()] = false;
      double endTime = this.getTime() + this.serviceTime;
      return new Event[] {
        new SERVICE_END(this.customer, this.counter, endTime,
            this.serviceTime, this.available)
      };
  }

  @Override
  public String toString(){
    String str = "";
    str = this.customer.toString() + String.format("service begin ") + this.counter.toString();
    return super.toString() + str;
  }
}
