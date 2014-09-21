import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	
	public Board(int size){
		SIZE = size;
		
		// set up canvas
		canvas = new JPanel(){			
			@Override
			protected void paintComponent(Graphics g){
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(0,0,getWidth(),getHeight());
				g.setColor(Color.BLACK);
				for (int i = 0; i < SIZE; i++){
					for (int j = 0; j < SIZE; j++){
						g.drawRect(i*GRID_WD, j*GRID_WD, GRID_WD, GRID_WD);
					}
				}
				if (selected != null){
					int x = GRID_WD*selected.x;
					int y = GRID_WD*selected.y;
					g.setColor(Color.RED);
					g.fillRect(x,y, GRID_WD,GRID_WD);
					g.setColor(Color.BLACK);
					g.drawRect(x,y, GRID_WD,GRID_WD);
				}
			}
		};
		int panel_wd = GRID_WD*SIZE;
		canvas.setPreferredSize(new Dimension(panel_wd+2,panel_wd+2));

		// set up options
		options = new JPanel();
		JButton btn_runAlgorithm = new JButton("Run Algorithm");
		JButton btn_newBoard = new JButton("New Board");
		options.add(btn_runAlgorithm);
		options.add(btn_newBoard);
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
				if (selected == null){
					JOptionPane.showMessageDialog(frame, "Need to select a starting square");
				}
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
	
}
