import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Leaderboards extends JFrame implements ActionListener {
	JButton btnAtras;

	String datos[][]= {
    		{"1","Sue","New York"},
    		{"2","John","Los Angeles"},
    		{"3","Charlotte","Roma"},
    		{"4","Ed","Brasilia"}
	};
	String columnas[]= {"Id","Nombre","Lugar"};
	JTable tblDatos;
    JScrollPane sp;
	
    
    
	Leaderboards(){
		
			 setLayout(null);	
			 setTitle("Menú");
			 
		     setVisible(true);
		     setSize(1966, 768);
		     
		     setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		     //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		     
		    
		     
		     tblDatos=new JTable(datos,columnas);
		     
		     
				
			 sp=new JScrollPane(tblDatos);
			 add(sp);
			 sp.setLocation(400,0);
			 sp.setSize(500,200);
			 
			
			 tblDatos.setLocation(400,0);
			 tblDatos.setSize(50,50);
			 tblDatos.setForeground(new Color(0,0,0));
			 tblDatos.setFont(new Font("Arial",Font.BOLD, 15));
			 
			 	 	
			
			 
			//btnAtras
			 
			 btnAtras = new JButton(new ImageIcon("C:\\Users\\daw\\Desktop\\CPU_Clicker\\images\\atras.png"));	
			 add(btnAtras);	 	
			 btnAtras.setLocation(0,0);
			 btnAtras.setSize(50,50);
			 btnAtras.setOpaque(false);
			 		 		 
			 btnAtras.addActionListener(this);
			 	
			// btnAtras.setForeground(new Color(0,0,0));
			// btnAtras.setFont(new Font("Arial",Font.BOLD, 20));
			 btnAtras.setBackground(new Color(100, 0, 100));
			 
			       
				}
	@Override
	public void actionPerformed(ActionEvent e) {
		 
		 JButton elegido = (JButton) e.getSource();
         if(elegido == btnAtras) {
        	 
        	dispose();
        	// ventana.setVisible(false);
        	// Menu menu = new Menu();
            // menu.setVisible(true);
         }

	}
	
	public static void main(String[]args) {
		Leaderboards Leaderboards = new Leaderboards();
		Leaderboards.setVisible(true);
	}

	

}
