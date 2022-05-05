class TakeACab extends Service {
  
  @Override
  public int computeFare(Request req) {
    return 200 + 33 * req.getDistance();
  }

  @Override
  public String toString() {
    return "TakeACab";
  }
}
