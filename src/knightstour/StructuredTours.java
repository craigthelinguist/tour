package knightstour;
import java.awt.Point;

/**
 * This class is an enumeration of some structured tours on board dimensions that are specified in Ian Parberry's Knight's Tour paper.
 * @author Aaron Craig.
 *
 */
public class StructuredTours {

	private StructuredTours(){}
	
	public static Point[] tour_10x10 = new Point[]{
		new Point(0,0), new Point(2,1), new Point(4,0), new Point(6,1), new Point(8,0), new Point(9,2), new Point(8,4), 
		new Point(9,6), new Point(8,8), new Point(6,9), new Point(4,8), new Point(2,9), new Point(0,8), 
		new Point(1,6), new Point(0,4), new Point(1,2), new Point(2,0), new Point(0,1), new Point(1,3), 
		new Point(0,5), new Point(1,7), new Point(0,9), new Point(2,8), new Point(4,9), new Point(6,8), 
		new Point(8,9), new Point(9,7), new Point(7,6), new Point(9,5), new Point(8,7), new Point(9,9), 
		new Point(7,8), new Point(5,9), new Point(3,8), new Point(1,9), new Point(0,7), new Point(2,6), 
		new Point(1,8), new Point(3,9), new Point(4,7), new Point(6,6), new Point(8,5), new Point(9,3), 
		new Point(8,1), new Point(6,0), new Point(7,2), new Point(9,1), new Point(7,0), new Point(8,2), 
		new Point(9,0), new Point(7,1), new Point(8,3), new Point(6,4), new Point(5,2), new Point(7,3), 
		new Point(9,4), new Point(8,6), new Point(9,8), new Point(7,9), new Point(6,7), new Point(7,5), 
		new Point(6,3), new Point(5,1), new Point(3,0), new Point(1,1), new Point(3,2), new Point(2,4), 
		new Point(4,5), new Point(5,7), new Point(3,6), new Point(5,5), new Point(7,4), new Point(5,3), 
		new Point(6,5), new Point(7,7), new Point(5,8), new Point(3,7), new Point(5,6), new Point(4,4), 
		new Point(2,5), new Point(0,6), new Point(2,7), new Point(4,6), new Point(3,4), new Point(1,5), 
		new Point(2,3), new Point(4,2), new Point(5,0), new Point(3,1), new Point(1,0), new Point(0,2), 
		new Point(1,4), new Point(3,5), new Point(5,4), new Point(3,3), new Point(4,1), new Point(6,2), 
		new Point(4,3), new Point(2,2), new Point(0,3), new Point(0,0)
	};

	public static Point[] tour_10x8 = new Point[]{
		new Point(0,0), new Point(2,1), new Point(4,0), new Point(6,1), new Point(8,0), new Point(9,2), new Point(7,1), 
		new Point(9,0), new Point(8,2), new Point(9,4), new Point(8,6), new Point(6,7), new Point(4,6), 
		new Point(3,4), new Point(5,3), new Point(6,5), new Point(4,4), new Point(5,2), new Point(7,3), 
		new Point(5,4), new Point(3,3), new Point(2,5), new Point(1,3), new Point(3,2), new Point(2,4), 
		new Point(0,5), new Point(1,7), new Point(3,6), new Point(5,5), new Point(4,3), new Point(6,2), 
		new Point(7,4), new Point(9,3), new Point(8,1), new Point(6,0), new Point(4,1), new Point(2,0), 
		new Point(0,1), new Point(2,2), new Point(1,4), new Point(0,6), new Point(2,7), new Point(3,5), 
		new Point(2,3), new Point(0,2), new Point(1,0), new Point(3,1), new Point(5,0), new Point(4,2), 
		new Point(6,3), new Point(8,4), new Point(7,2), new Point(6,4), new Point(4,5), new Point(5,7), 
		new Point(7,6), new Point(9,7), new Point(8,5), new Point(6,6), new Point(4,7), new Point(2,6), 
		new Point(0,7), new Point(1,5), new Point(0,3), new Point(1,1), new Point(3,0), new Point(5,1), 
		new Point(7,0), new Point(9,1), new Point(8,3), new Point(9,5), new Point(8,7), new Point(7,5), 
		new Point(9,6), new Point(7,7), new Point(5,6), new Point(3,7), new Point(1,6), new Point(0,4), 
		new Point(1,2), new Point(0,0)
	};

	public static Point[] tour_12x10 = new Point[]{
		new Point(0,0), new Point(2,1), new Point(0,2), new Point(1,0), new Point(3,1), new Point(5,0), new Point(7,1), 
		new Point(9,0), new Point(11,1), new Point(10,3), new Point(11,5), new Point(10,7), new Point(11,9), 
		new Point(9,8), new Point(7,9), new Point(5,8), new Point(3,9), new Point(1,8), new Point(3,7), 
		new Point(4,5), new Point(6,6), new Point(4,7), new Point(5,5), new Point(7,4), new Point(6,2), 
		new Point(5,4), new Point(7,3), new Point(8,1), new Point(9,3), new Point(8,5), new Point(9,7), 
		new Point(7,6), new Point(8,4), new Point(10,5), new Point(8,6), new Point(6,7), new Point(7,5), 
		new Point(9,4), new Point(8,2), new Point(6,3), new Point(4,2), new Point(6,1), new Point(5,3), 
		new Point(3,4), new Point(4,6), new Point(6,5), new Point(4,4), new Point(2,3), new Point(3,5), 
		new Point(2,7), new Point(1,5), new Point(3,6), new Point(4,8), new Point(5,6), new Point(7,7), 
		new Point(8,9), new Point(6,8), new Point(8,7), new Point(9,5), new Point(8,3), new Point(6,4), 
		new Point(4,3), new Point(2,4), new Point(3,2), new Point(4,0), new Point(5,2), new Point(3,3), 
		new Point(4,1), new Point(6,0), new Point(7,2), new Point(8,0), new Point(10,1), new Point(11,3), 
		new Point(9,2), new Point(10,0), new Point(11,2), new Point(10,4), new Point(11,6), new Point(10,8), 
		new Point(9,6), new Point(11,7), new Point(10,9), new Point(8,8), new Point(6,9), new Point(5,7), 
		new Point(4,9), new Point(2,8), new Point(0,9), new Point(1,7), new Point(2,9), new Point(0,8), 
		new Point(1,6), new Point(0,4), new Point(2,5), new Point(0,6), new Point(1,4), new Point(2,2), 
		new Point(0,3), new Point(1,1), new Point(3,0), new Point(5,1), new Point(7,0), new Point(9,1), 
		new Point(11,0), new Point(10,2), new Point(11,4), new Point(10,6), new Point(11,8), new Point(9,9), 
		new Point(7,8), new Point(5,9), new Point(3,8), new Point(1,9), new Point(0,7), new Point(2,6), 
		new Point(0,5), new Point(1,3), new Point(0,1), new Point(2,0), new Point(1,2), new Point(0,0)
	};

	public static Point[] tour_6x6 = new Point[]{
		new Point(4,4), new Point(5,2), new Point(4,0), new Point(2,1), new Point(0,0), new Point(1,2), new Point(0,4), 
		new Point(2,5), new Point(3,3), new Point(5,4), new Point(3,5), new Point(1,4), new Point(0,2), 
		new Point(1,0), new Point(3,1), new Point(5,0), new Point(4,2), new Point(2,3), new Point(1,5), 
		new Point(0,3), new Point(1,1), new Point(3,0), new Point(5,1), new Point(4,3), new Point(5,5), 
		new Point(3,4), new Point(2,2), new Point(4,1), new Point(5,3), new Point(4,5), new Point(2,4), 
		new Point(0,5), new Point(1,3), new Point(0,1), new Point(2,0), new Point(3,2), new Point(4,4)
	};

	public static Point[] tour_8x6 = new Point[]{
		new Point(0,0), new Point(1,2), new Point(3,3), new Point(2,5), new Point(0,4), new Point(2,3), new Point(1,5), 
		new Point(0,3), new Point(1,1), new Point(3,0), new Point(5,1), new Point(7,0), new Point(6,2), 
		new Point(7,4), new Point(5,5), new Point(3,4), new Point(2,2), new Point(0,1), new Point(2,0), 
		new Point(4,1), new Point(6,0), new Point(7,2), new Point(6,4), new Point(4,5), new Point(5,3), 
		new Point(6,5), new Point(7,3), new Point(6,1), new Point(4,0), new Point(3,2), new Point(4,4), 
		new Point(6,3), new Point(7,5), new Point(5,4), new Point(4,2), new Point(5,0), new Point(7,1), 
		new Point(5,2), new Point(3,1), new Point(1,0), new Point(0,2), new Point(1,4), new Point(3,5), 
		new Point(4,3), new Point(2,4), new Point(0,5), new Point(1,3), new Point(2,1), new Point(0,0)
	};

	public static Point[] tour_8x8 = new Point[]{
		new Point(7,7), new Point(6,5), new Point(7,3), new Point(6,1), new Point(4,0), new Point(2,1), new Point(0,0), 
		new Point(1,2), new Point(2,0), new Point(0,1), new Point(1,3), new Point(0,5), new Point(1,7), 
		new Point(3,6), new Point(5,7), new Point(7,6), new Point(6,4), new Point(7,2), new Point(6,0), 
		new Point(5,2), new Point(7,1), new Point(5,0), new Point(3,1), new Point(1,0), new Point(0,2), 
		new Point(1,4), new Point(0,6), new Point(2,7), new Point(4,6), new Point(6,7), new Point(7,5), 
		new Point(5,6), new Point(3,7), new Point(2,5), new Point(0,4), new Point(1,6), new Point(3,5), 
		new Point(4,7), new Point(6,6), new Point(5,4), new Point(3,3), new Point(4,5), new Point(2,6), 
		new Point(0,7), new Point(1,5), new Point(2,3), new Point(4,4), new Point(6,3), new Point(4,2), 
		new Point(3,4), new Point(5,5), new Point(4,3), new Point(2,4), new Point(0,3), new Point(2,2), 
		new Point(4,1), new Point(6,2), new Point(7,0), new Point(5,1), new Point(3,0), new Point(1,1), 
		new Point(3,2), new Point(5,3), new Point(7,4), new Point(7,7)
	};

	
}
