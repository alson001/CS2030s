class Request {
  private int distanceOfRide;
  private int noOfPassengers;
  private int timeOfRequest;

  public Request(int distanceOfRide, int noOfPassengers, int timeOfRequest) {
    this.distanceOfRide = distanceOfRide;
    this.noOfPassengers = noOfPassengers;
    this.timeOfRequest = timeOfRequest;
  }

  public int getDistance() {
    return this.distanceOfRide;
  }

  public int getPassengers() {
    return this.noOfPassengers;
  }

  public int getTime() {
    return this.timeOfRequest;
  }

}
