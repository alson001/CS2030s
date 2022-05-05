// @author Alson(Group 12H)

class Customer{
  private int customerId;
  public Customer(int customerId){
    this.customerId = customerId;
  }

  public int getId(){
    return this.customerId;
  }

  @Override
  public String toString(){                                                 
    String str = "";                                                             
    str = String.format(": Customer %d ", this.customerId);
    return str;
  }
  
}
