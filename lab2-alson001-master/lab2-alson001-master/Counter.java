//@author Alson(Group 12H)

class Counter {
  private int counterid;
  private boolean available;

  public Counter(int counterid, boolean available) {
    this.counterid = counterid;
    this.available = available;
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
  
  @Override
  public String toString() {
    String str = "";
    str = String.format("S%d", counterid);
    return str;
  }
}
