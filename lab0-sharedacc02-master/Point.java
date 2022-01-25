/**
 * CS2030S Lab 0: Point.java
 * Semester 2, 2021/22
 *
 * <p>The Point class encapsulates a point on a 2D plane.*/
class Point{
 	public double x;
	public double y;
	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}
	public String toString(){
		return "(" + x + ", " + y + ")";
	}
}

