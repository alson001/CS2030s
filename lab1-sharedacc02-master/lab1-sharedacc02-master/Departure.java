// @author Alson(Group 12H)

class Departure extends Event{
  private static final int DEPARTURE = 3; 
  private Customer customer;

  public Departure(double time, Customer customer){
    super(time);
    this.customer = customer;
  }

  @Override
  public Event[] simulate(){
    return new Event[]{};
  }

  @Override 
  public String toString(){
    String str = "";
    str = customer.toString() + String.format("departed");
    return super.toString() + str;
  }
}

