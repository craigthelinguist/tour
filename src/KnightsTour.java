import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class KnightsTour {

	private KnightsTour(){}
	private static List<Point> solution;
	
	public static boolean knightsTour(int n, Point p){
		boolean[][] board = new boolean[n][n];
		solution = new ArrayList<>();
		boolean ans = backtrack(board,p.x,p.y,n,1);
		if (!ans) solution = new ArrayList<>();
		else Collections.reverse(solution);
		return ans;
	}
	
	public static boolean knightsTour(int n){
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				boolean[][] board = new boolean[n][n];
				solution = new ArrayList<>();
				if (backtrack(board,i,j,n,1)){
					Collections.reverse(solution);
					return true;
				}
			}
		}
		solution = new ArrayList<>();
		return false;
	}
	
	public static List<Point> getTour(){
		return solution;
	}
	
	private static boolean backtrack(boolean[][] board, int i, int j, int n, int tilesVisited){
		board[i][j] = true;
		if (tilesVisited == n*n){
			solution.add(new Point(i,j));
			return true;
		}
		Point[] validMoves = validMoves(i,j,n);
		for (Point p : validMoves){
			if (p == null) continue;
			if (!board[p.x][p.y]){
				if (backtrack(copy(board),p.x,p.y,n,tilesVisited+1)){
					solution.add(new Point(i,j));
					return true;
				}
				
			}
		}
		return false;
	}
	
	private static Point[] validMoves(int i, int j, int n){
		Point[] validMoves = new Point[8];
		int z = 0;
		for (int k = 0; k < moves.length; k++){
			Point p = new Point(i+moves[k].x,j+moves[k].y);
			if (p.x >= 0 && p.x < n && p.y >= 0 && p.y < n){
				validMoves[z++] = p;
			}
		}
		return validMoves;
	}
	
	private static Point[] moves = new Point[]{
		new Point(2,1),
		new Point(2,-1),
		new Point(1,2),
		new Point(1,-2),
		new Point(-1,2),
		new Point(-1,-2),
		new Point(-2,1),
		new Point(-2,-1)
	};
	
	private static boolean[][] copy(boolean[][] board){
		boolean[][] copy = new boolean[board.length][board[0].length];
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[0].length; j++){
				copy[i][j] = board[i][j];
			}
		}
		return copy;
	}
	
}
