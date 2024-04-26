import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;

public class Info extends JFrame implements ActionListener {
	public static JLabel lblBits = new JLabel("1");
	
	public static Info info;
	
	Info(){
		
		setTitle("CPU Clicker");

	    setSize(500, 300);

	    setUndecorated(true);
	    
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);

	    setLayout(null);
	    
	    setLocation(533,268);
	    
		lblBits.setLocation(0,0);
		lblBits.setSize(100,20);
		add(lblBits);
		
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
