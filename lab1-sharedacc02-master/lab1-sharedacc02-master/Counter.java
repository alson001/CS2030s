//@author Alson(Group 12H)

class Counter{
  private int counterid;
  public Counter(int counterid){
   this.counterid = counterid;
  }

  public int getcounterId(){
    return this.counterid;
  }
  
  @Override
  public String toString(){
    String str = "";
    str = String.format("(by Counter %d)", counterid);
    return str;
  }
}
