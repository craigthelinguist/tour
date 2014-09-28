package knightstour;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Graphical representation of an nxn chessboard.
 * @author craigthelinguist
 */
public class Board {

	public static final int GRID_WD = 30;
	public final int SIZE;
	
	private JFrame frame;
	private JPanel canvas;
	private JPanel options;
	
	private Point selected = null;
	private JLabel runtime_label;
	private JLabel runtime;
	private JLabel barometer_label;
	private JLabel barometer;
	private Algorithm state = Algorithm.UNOPTIMISED;
	
	private enum Algorithm{
		NONE, UNOPTIMISED, OPTIMISED, OPTIMISED_CLOSED, PARBERRY;
	}
	
	final Color ODD_TILES = new Color(0,180,250);
	final Color ODD_TILES_DARKER = new Color(0,120,190);
	final Color EVEN_TILES = new Color(255,255,255);
	final Color EVEN_TILES_DARKER = new Color(195,195,195);
	
	public Board(int size){
		SIZE = size;
		
		// set up canvas
		canvas = new JPanel(){			
			@Override
			protected void paintComponent(Graphics g){
				
				// draw the board
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(0,0,getWidth(),getHeight());
				g.setColor(Color.BLACK);
				for (int i = 0; i < SIZE; i++){
					for (int j = 0; j < SIZE; j++){
						
						if ((i+j)%2 == 0) g.setColor(EVEN_TILES);
						else g.setColor(ODD_TILES);
						g.fillRect(i*GRID_WD, j*GRID_WD, GRID_WD, GRID_WD);
						
						g.setColor(Color.BLACK);
						g.drawRect(i*GRID_WD, j*GRID_WD, GRID_WD, GRID_WD);
						
					}
				}
				
				// draw the selected tile
				if (selected != null){
					int x = GRID_WD*selected.x;
					int y = GRID_WD*selected.y;
					if ((selected.x+selected.y)%2 == 0) g.setColor(EVEN_TILES_DARKER);
					else g.setColor(ODD_TILES_DARKER);
					g.fillRect(x,y, GRID_WD,GRID_WD);
					g.setColor(Color.BLACK);
					g.drawRect(x,y, GRID_WD,GRID_WD);
				}

				// draw the tour
				List<Point> solution = getTour();
				
				if (solution != null && !solution.isEmpty()){
					g.setColor(Color.BLACK);
					Point prev = solution.get(0);
					for (int i = 1; i < solution.size(); i++){
						int prevX = prev.x * GRID_WD + GRID_WD/2;
						int prevY = prev.y * GRID_WD + GRID_WD/2;
						Point current = solution.get(i);
						int currentX = current.x * GRID_WD + GRID_WD/2;
						int currentY = current.y * GRID_WD + GRID_WD/2;
						g.drawLine(prevX,prevY,currentX,currentY);
						prev = current;
					}
				}
				
			}
		};
		int panel_wd = GRID_WD*SIZE;
		canvas.setPreferredSize(new Dimension(panel_wd+2,panel_wd+2));

		// set up options
		options = new JPanel();
		JButton btn_runAlgorithm = new JButton("Naiive Algorithm");
		JButton btn_runOptimised = new JButton("Optimised");
		JButton btn_parberry = new JButton("Parberry");
		JButton btn_newBoard = new JButton("New Board");
		JButton btn_saveTour = new JButton("Save Tour");
		JButton btn_clear = new JButton("Clear");
		options.add(btn_runAlgorithm);
		options.add(btn_runOptimised);
		options.add(btn_parberry);
		options.add(btn_newBoard);
		options.add(btn_saveTour);
		options.add(btn_clear);
		options.setPreferredSize(new Dimension(btn_runAlgorithm.getPreferredSize().width+20,panel_wd));
		options.setBackground(Color.WHITE);
		
		// set up button listeners
		btn_newBoard.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				frame.dispose();
				new TourDialog();
			}
		
		});
		btn_runAlgorithm.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				if (selected == null) KnightsTour.knightsTour(SIZE);
				else KnightsTour.knightsTour(SIZE, selected);
				state = Algorithm.UNOPTIMISED;
				canvas.repaint();
			}
		
		});
		btn_runOptimised.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (selected == null) OptimisedOpen.knightsTour(SIZE);
				else OptimisedOpen.knightsTour(SIZE, selected);
				state = Algorithm.OPTIMISED;
				canvas.repaint();
			}
		
		});
		btn_parberry.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (SIZE % 2 != 0){
					JOptionPane.showMessageDialog(null, "Parberry's only works on boards of even width","Warning!",JOptionPane.WARNING_MESSAGE);
				}
				else{
					Parberrys.solve(SIZE);
					state = Algorithm.PARBERRY;
					canvas.repaint();
				}
			}
		
		});
		btn_saveTour.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				List<Point> solution = getTour();
				if (solution == null) return;
				StringBuilder sb = new StringBuilder();
				sb.append(SIZE + "x" + SIZE + "_");
				switch(state){
					case UNOPTIMISED:
						sb.append("unoptimised_open.txt");
						break;
					case OPTIMISED:
						sb.append("optimised_open.txt");
						break;
					case OPTIMISED_CLOSED:
						sb.append("optimised_closed.txt");
						break;
					case PARBERRY:
						sb.append("parberry.txt");
						break;
					default:
						return;
				}
				File f = new File(sb.toString());
				try {
					PrintStream ps = new PrintStream(f);
					for (Point pt : solution){
						ps.println(pt.x + "," + pt.y);
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		btn_clear.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				state = Algorithm.NONE;
				canvas.repaint();
			}
		
		});
		
		// set up mouse listener
		canvas.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX(); int y = e.getY();
				Point p = new Point(x / GRID_WD, y / GRID_WD);
				if (p.x < 0 || p.x >= SIZE || p.y < 0 || p.y >= SIZE){
					selected = null;
				}
				else if (selected != null && p.equals(selected)){
					selected = null;
				}
				else{
					selected = p;
				}
				canvas.repaint();
			}

			// dummy methods; need to implement for MouseListener
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		
		});
		
		// set up labels
		runtime_label = new JLabel("Running Time");
		runtime = new JLabel("");
		barometer_label = new JLabel("Comparisons");
		barometer = new JLabel("");
		options.add(runtime_label);
		options.add(runtime);
		options.add(barometer_label);
		options.add(barometer);
		
		// set up frame
		frame = new JFrame();
		frame.add(canvas, BorderLayout.CENTER);
		frame.add(options, BorderLayout.EAST);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public List<Point> getTour(){
		List<Point> solution = null;
		switch (state){
		case UNOPTIMISED:
			solution = KnightsTour.getTour();
			break;
		case OPTIMISED:
			solution = OptimisedOpen.getTour();
			break;
		case OPTIMISED_CLOSED:
			solution = OptimisedClosed.getTour();
			break;
		case PARBERRY:
			solution = Parberrys.getTour();
			break;
		default:
			solution = null;
			break;
		}
		return solution;
	}
	
}
