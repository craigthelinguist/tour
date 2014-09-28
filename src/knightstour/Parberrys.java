package knightstour;
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
	
	private static List<Point> solution;
	
	public static boolean solve(int n){
		
		solution = new LinkedList<>();
		// Parberry's does not solve boards of odd-width
		if (n % 2 != 0) return false;
	
		// for all even n, Parberry's can solve for boards of size
		// n*n or n*(n+2)
		
		solution = baseCase(n,new Point(0,0));
		if (!solution.isEmpty()) return true;
		Point origin = new Point(0,0);
		solution = squareBoardSolve(origin,n);
		return true;
	}
	
	/**
	 * Check if the n is a base case. If it is, update the solution list and return true.
	 * @param n: width of the board
	 * @return true if n is a base case; false otherwise.
	 */
	private static List<Point> baseCase(int n, Point origin){
		System.out.println("Base case " + n);
		
		List<Point> pts = new ArrayList<Point>();
		if (n == 10){
			for (Point pt : Arrays.asList(StructuredTours.tour_10x10)){
				Point new_pt = new Point(pt.x + origin.x, pt.y + origin.y);
				pts.add(new_pt);
			}
		}
		else if (n == 8){
			for (Point pt : Arrays.asList(StructuredTours.tour_8x8)){
				Point new_pt = new Point(pt.x + origin.x, pt.y + origin.y);
				pts.add(new_pt);
			}
		}
		else if (n == 6){
			for (Point pt : Arrays.asList(StructuredTours.tour_6x6)){
				Point new_pt = new Point(pt.x + origin.x, pt.y + origin.y);
				pts.add(new_pt);
			}
		}
		return pts;
	}
	
	private static List<Point> squareBoardSolve(Point origin, int n){
		
		// base case
		if (n <= 10){
			return baseCase(n,origin);
		}
		
		// divide
		int k = n/2;
		List<Point> topleft = squareBoardSolve(new Point(0,0), k);
		List<Point> topright = squareBoardSolve(new Point(k,0), k);
		List<Point> botleft = squareBoardSolve(new Point(0,k), k);
		List<Point> botright = squareBoardSolve(new Point(k,k), k);
		
		// combine
		return squareMerge(topleft,topright,botleft,botright,origin,n);
		
	}
	
	private static List<Point> squareMerge(List<Point> topleft, List<Point> topright, List<Point> botleft, List<Point> botright,
			Point origin, int n){
		
		int k = n/2;
		Point origin_topleft = new Point(0,0);
		Point origin_botleft = new Point(0,k);
		Point origin_topright = new Point(k,0);
		Point origin_botright = new Point(k,k);
		
		List<Point> merged = new ArrayList<>();
		
		// bottom left
		// this adds (k-1,k) and copies the points between (k-1,k) and (k-2,k+2)
		Point goalLink = new Point(k-1 + origin.x, k + origin.y);
		Point otherLink = new Point(k-2 + origin.x, k+2 + origin.y);
		for (int i = 0; i < botleft.size(); i++){
			Point pt = botleft.get(i);
			if (pt.equals(goalLink)){
				merged.add(pt);
				
				// figure out if other link is to the left or right
				Point link = pt;
				boolean otherLinkToLeft = true;
				Point right = botleft.get((i+1)%botleft.size());
				if (right.equals(otherLink)) otherLinkToLeft = false;
				
				// move in opposite of direction to the other link, copying all the points
				int dir = otherLinkToLeft ? 1 : -1;
				int start = i + dir;
				for (int j = start; !botleft.get(j).equals(link) ; j = (j+dir)%botleft.size() ){
					merged.add(botleft.get(j));
					if (j==0 && dir<0) j = botleft.size(); // wrap around if you're moving left
				}
				break;
			}
		}
		
		// bottom right
		// this links (k-2,k+2) -> (k,k+1), and copies the points between (k,k+1) and (k+2,k)
		goalLink = new Point(k + origin.x,k+1 + origin.y);
		otherLink = new Point(k+2 + origin.x, k + origin.y);
		for (int i = 0; i < botright.size(); i++){
			Point pt = botright.get(i);
			if (pt.equals(goalLink)){
				merged.add(pt);
				
				// figure out if other link is to the left or right
				Point link = pt;
				boolean otherLinkToLeft = true;
				Point right = botright.get((i+1)%botright.size());
				if (right.equals(otherLink)) otherLinkToLeft = false;
				
				// move in opposite of direction to the other link, copying all the points
				int dir = otherLinkToLeft ? 1 : -1;
				int start = i + dir;
				for (int j = start; !botright.get(j).equals(link); j = (j+dir)%botright.size()){
					merged.add(botright.get(j));
					if (j==0 && dir<0) j = botleft.size(); // wrap around if you're moving left
				}
				break;
				
			}
		}
		
		// top right
		// this links (k+2,k) -> (k,k-1) and copies the points between (k,k-1) and (k+1,k-3)
		goalLink = new Point(k+origin.x, k-1+origin.y);
		otherLink = new Point(k+1+origin.x, k-3+origin.y);
		for (int i = 0; i < topright.size(); i++){
			Point pt = topright.get(i);
			if (pt.equals(goalLink)){
				merged.add(pt);
				
				// figure out if other link is to the left or right
				Point link = pt;
				boolean otherLinkToLeft = true;
				Point right = topright.get((i+1)%topright.size());
				if (right.equals(otherLink)) otherLinkToLeft = false;
				
				// move in opposite  of direction to the other link, copying all the points
				int dir = otherLinkToLeft ? 1 : -1;
				int start = i + dir;
				for (int j = start; !topright.get(j).equals(link); j = (j+dir)%topright.size()){
					merged.add(topright.get(j));
					if (j==0 && dir<0) j = botleft.size(); // wrap around if you're moving left
				}
				break;
				
			}
		}
		
		// top left
		// this links (k+1,k-3) -> (k-1,k-2) and copies the points between (k-1,k-2) and (k-3,k-1)
		goalLink = new Point(k-1+origin.x,k-2+origin.y);
		otherLink = new Point(k-3+origin.x, k-1+origin.y);
		for (int i = 0; i < topleft.size(); i++){
			Point pt = topleft.get(i);
			if (pt.equals(goalLink)){
				merged.add(pt);
				
				// figure out if other link is to the left or right
				Point link = pt;
				boolean otherLinkToLeft = true;
				Point right = topleft.get((i+1)%topleft.size());
				if (right.equals(otherLink)) otherLinkToLeft = false;
				
				// move in opposite of direction to the other link, copying all the points
				int dir = otherLinkToLeft ? 1 : -1;
				int start = i + dir;
				for (int j = start; !topleft.get(j).equals(link); j = (j+dir)%topleft.size()){
					merged.add(topleft.get(j));
					if (j==0 && dir<0) j = botleft.size(); // wrap around if you're moving left
				}
				break;
			}
		}
		
		// it's a closed tour: add the first point again
		merged.add(merged.get(0));
		return merged;
		
	}
	
	public static List<Point> getTour(){
		return solution;
	}
	
}
