class ShareARide extends Service {

  @Override
  public int computeFare(Request req) {
    if (req.getTime() >= 600 && req.getTime() <= 900) {
      return 500 + (50 * req.getDistance()) / req.getPassengers();
    } else {
      return (50 * req.getDistance()) / req.getPassengers();
    }
  }

  @Override
  public String toString() {
    return "ShareARide";
  }
}
