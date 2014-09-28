import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * Uses the algorithm described in the paper:
 * 		An Efficient Algorithm for the Knight's Tour Problem
 * by
 * 		Ian Parberry.
 * Divides the space of the board into 4 quadrants and recursively solves each, then recombines.
 * So long at n is even, such a tour exists.
 * @author Aaron Craig
 */
public class Parberrys {
	
	private static List<Point> solutions;
	
	public static boolean solve(int n){
		
		solutions = new LinkedList<>();
		// Parberry's does not solve boards of odd-width
		if (n % 2 != 0) return false;
	
		// for all even n, Parberry's can solve for boards of size
		// n*n or n*(n+2)
		
		solutions = baseCase(n);
		if (!solutions.isEmpty()) return true;
		Point topleft = new Point(0,0);
		Point botright = new Point(n-1,n-1);
		solutions = squareBoardSolve(topleft,botright,n);
	}
	
	/**
	 * Check if the n is a base case. If it is, update the solutions list and return true.
	 * @param n: width of the board
	 * @return true if n is a base case; false otherwise.
	 */
	private static List<Point> baseCase(int n){
		if (n == 10){
			return new ArrayList<Point>(Arrays.asList(StructuredTours.tour_10x10));
		}
		else if (n == 8){
			return new ArrayList<Point>(Arrays.asList(StructuredTours.tour_8x8));
		}
		else if (n == 6){
			return new ArrayList<Point>(Arrays.asList(StructuredTours.tour_6x6));
		}
		else return new ArrayList<Point>();
	}
	
	private static List<Point> squareBoardSolve(Point pt_topleft, Point pt_botright, int n){
		
		// base case
		if (n <= 10){
			return baseCase(pt_botright.x - pt_topleft.x);
		}
		
		// divide
		int k = n/2;
		List<Point> topleft = squareBoardSolve(new Point(0,0), new Point(k-1,k-1), k);
		List<Point> topright = squareBoardSolve(new Point(k,0), new Point(n-1,k-1), k);
		List<Point> botleft = squareBoardSolve(new Point(0,k), new Point(k-1,n-1), k);
		List<Point> botright = squareBoardSolve(new Point(k,k), new Point(n-1,n-1), k);
		
		// combine
		return squareMerge(topleft,topright,botleft,botright,n);
		
	}
	
	private static List<Point> squareMerge(List<Point> topleft, List<Point> topright, List<Point> botleft, List<Point> botright, int n){
		
		int k = n/2;
		/**
		 * 
		 
		 merge (k-2,k-1) with (k-2,k)	[topleft with botleft]
		 merge (k-1,k+1) with (k,k+1)	[botleft with botright]
		 merge (k+1,k) with (k+1,k-1)	[topright with botright]
		 merge (k-1,k-2) with (k,k-2)	[topleft with topright]
		 
		 */
		
		List<Point> merged = new ArrayList<>();
		int a,b,c,d;
		
		// start with point that merges top left to bottom left
		for (a = 0; a < topleft.size(); a++){
			Point pt = topleft.get(a);
			merged.add(pt);
			if (pt.x == k-2 && pt.y == k-1){
				break;
			}
		}

		// find pt in botleft where it merges
		for (b = 0; b < botleft.size(); b++){
			Point pt = botleft.get(b);
			if (pt.x == k-2 && pt.y == k){
				break;
			}
		}
		for (;;b=(b+1)%botleft.size()){
			Point pt = botleft.get(b);
			merged.add(pt);
			if (pt.x == k-1 && pt.y == k+1){
				break;
			}
		}
		
		// find pt in botright where it merges
		for (c = 0; c < botright.size(); c++){
			Point pt = botright.get(c);
			if (pt.x == k && pt.y == k+1){
				break;
			}
		}
		for (;; c = (c+1)%botright.size()){
			Point pt = botright.get(c);
			merged.add(pt);
			if (pt.x == k+1 && pt.y == k){
				break;
			}
		}
		
		// find pt in topright where it merges
		for (d = 0; d < topright.size(); d++){
			Point pt = topright.get(d);
			if (pt.x == k+1 && pt.y == k-1){
				break;
			}
		}
		for (;; d = (d+1)%topright.size()){
			Point pt = topright.get(c);
			merged.add(pt);
			if (pt.x == k && pt.y == k-2){
				break;
			}
		}
		
		// find pt in topleft where it merges
		for (a = 0; )
		
	}
	
	
}
