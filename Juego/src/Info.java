import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;

public class Info extends JFrame implements ActionListener {
	
	
	
	
	
	public static Info info;
	
	JFrame frame = new JFrame("Simple JScrollPane Example"); 
	
	
	JScrollPane barraDesplazadora;
	Info(){
		
		setTitle("CPU Clicker");

	    setSize(500, 300);

	    setLayout(null);
	    
	    setLocation(533,268);
	    
	    
	    panel.setBounds(0,0,500,400);        
	    
		
		
		getContentPane().add(panel);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		info = new Info();
		info.setVisible(true);

	}

}
