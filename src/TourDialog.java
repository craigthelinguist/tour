import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * A dialog that prompts the user to enter the size of the board. It then instantiates a new board.
 * @author craigthelinguist
 *
 */
public class TourDialog extends JDialog{

	
	public TourDialog(){
		
		JLabel label = new JLabel("Enter size of board: ");
		label.setPreferredSize(new Dimension(200,30));
		final JTextField textField = new JTextField("Enter size in here");
		textField.setPreferredSize(new Dimension(200,30));
		JButton button = new JButton("Create");
		button.setPreferredSize(new Dimension(100,30));
		
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				
				try{
					
					int size = Integer.parseInt(textField.getText());
					if (size <= 1) return;
					dispose();
					new Board(size);
					
				}
				catch(NumberFormatException e){}
				
			}
		
		});
		
		this.add(label,BorderLayout.NORTH);
		this.add(textField,BorderLayout.CENTER);
		this.add(button,BorderLayout.SOUTH);
		
		this.pack();
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	
}