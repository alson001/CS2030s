import java.util.Scanner;

/**
 * CS2030S Lab 0: Estimating Pi with Monte Carlo
 * Semester 2, 2021/22
 *
 * <p>This program takes in two command line arguments: the 
 * number of points and a random seed.  It runs the
 * Monte Carlo simulation with the given argument and print
 * out the estimated pi value.
 *
 * @author XXX 
 */

class Lab0 {

  // TODO 
  public static double estimatePi(long numOfPoints, int seed) { 
	  double tempin = 0;
          double tempout = 0;
          Circle circle = new Circle(new Point(0.5, 0.5), 0.5);
	  RandomPoint.setSeed(seed);
	  for(long i = 0; i < numOfPoints; i++){
		  if (circle.contains(new RandomPoint(0, 1, 0, 1))){
			  tempin = tempin + 1;
		  }
		  else { tempout = tempout + 1;}
	  }
	  return 4 * tempin / numOfPoints;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int numOfPoints = sc.nextInt();
    int seed = sc.nextInt();

    double pi = estimatePi(numOfPoints, seed);

    System.out.println(pi);
    sc.close();
  }
}
