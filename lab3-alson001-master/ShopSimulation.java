// @author Alson(Group 12H)

import java.util.Scanner;

/**
 * This class implements a shop simulation.
 *
 * @author Wei Tsang
 * @version CS2030S AY21/22 Semester 2
 */ 
class ShopSimulation extends Simulation {

  /** 
   * The list of customer arrival events to populate
   * the simulation with.
   */
  private Event[] initEvents;

  /** 
   * Constructor for a shop simulation. 
   *
   * @param sc A scanner to read the parameters from.  The first
   *           integer scanned is the number of customers; followed
   *           by the number of service counters.  Next is a 
   *           sequence of (arrival time, service time) pair, each
   *           pair represents a customer.
   */
  public ShopSimulation(Scanner sc) {
    initEvents = new Event[sc.nextInt()];
    int numOfCounters = sc.nextInt();
    int counterQueue = sc.nextInt();
    int numOfQueue = sc.nextInt();
    Shop shop = new Shop(numOfCounters, counterQueue, numOfQueue);
 
    Customer customer;
    int i = 0;
    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      customer = new Customer(serviceTime);
      initEvents[i++] = new Arrival(customer,
          arrivalTime, shop);
    }
  }

  /**
   * Retrieve an array of events to populate the 
   * simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() {
    return initEvents;
  }
}
