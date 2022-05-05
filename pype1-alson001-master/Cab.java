class Cab extends Car {
  private String license;
  private int time;

  public Cab(String license, int time) {
    this.license = license;
    this.time = time;
  }

  @Override
  public int getTime() { 
    return time;
  }

  @Override
  public String toString() {
    if (time == 1) {
      return "Cab " + license + " (" + String.format("%d", time) + " min away)";
    } else {
      return "Cab " + license + " (" + String.format("%d", time) + " mins away)";
    }
  }
}

