class Booking implements Comparable<Booking> {
  private Car car;
  private Service service;
  private Request req;

  public Booking(Car car, Service service, Request req) {
    if (car instanceof Cab && (service instanceof JustRide || service instanceof TakeACab)) {
      this.car = car;
      this.service = service;
      this.req = req;
    } else if (car instanceof PrivateCar && 
              (service instanceof JustRide || service instanceof ShareARide)) {
      this.car = car;
      this.service = service;
      this.req = req;
    } else {
      throw new IllegalArgumentException(car.toString() + " does not provide the " 
                                        + service.toString() + " service.");
    }
  }

  @Override 
  public int compareTo(Booking booking) {
    if (this.service.computeFare(this.req) < booking.service.computeFare(booking.req)) {
      return -1;
    } else if (this.service.computeFare(this.req) > booking.service.computeFare(booking.req)) {
      return 1;
    } else if (this.car.getTime() < booking.car.getTime()) {
      return -1;
    } else if (this.car.getTime() > booking.car.getTime()) {
      return 1;
    } else {
      return 0;
    }
  }

}
