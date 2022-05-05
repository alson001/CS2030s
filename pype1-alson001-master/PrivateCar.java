class PrivateCar extends Car {
  public String license;
  public int time;

  public PrivateCar(String license, int time) {
    this.license = license;
    this.time = time;
  }

  @Override
  public int getTime() {
    return time;
  }

  @Override
  public String toString() {
    if (time <= 1) {
      return "PrivateCar " + license + " (" + String.format("%d", time) + " min away)";
    } else {
      return "PrivateCar " + license + " (" + String.format("%d", time) + " mins away)";
    }
  }
}
