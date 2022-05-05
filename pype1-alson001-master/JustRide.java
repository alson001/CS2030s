class JustRide extends Service {

  @Override
  public int computeFare(Request req) {
    if (req.getTime() >= 600 && req.getTime() <= 900) {
      return 500 + (22 * req.getDistance());
    } else {
      return 22 * req.getDistance();
    }
  }

  @Override
  public String toString() {
    return "JustRide";
  }
}
