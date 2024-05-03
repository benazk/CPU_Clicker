import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollBar;

public class Info extends JFrame implements ActionListener {
	public static JLabel lblActuales, lblMaximos, lblPS, lblPS_Raw, lblSumMejoras, lblBitsPC, lblVersion;
	
	public static JLabel lblMargin;
	
	public static Info info;
	
	public static JScrollBar scroll = new JScrollBar();
	
	JPanel panel = new JPanel();
	
	int idUsuario = Functions.idUsuario();
	
	Connection conn;
	
	Info(){
		
		setTitle("CPU Clicker");

	    setSize(400, 500);

	    setUndecorated(true);
	    
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);

	    setLayout(null);
	    
	    panel.setLayout(new GridLayout(7,1));
	    
	    add(panel);
	    
	    lblMargin = new JLabel();
	    lblMargin.setSize(50,500);
	    panel.setBounds(40,20,350,450);
	    lblActuales = new JLabel("Bits Actuales: " + Juego.bits + " bits");
	    panel.add(lblActuales);
	    lblMaximos = new JLabel("Bits Máximos: Inicia sesión para ver los bits máximos");
	    panel.add(lblMaximos);
	    lblPS = new JLabel("Bits Por Segundo: " + Juego.bitsPS * (Juego.BSoD + 1) + " bits");
	    panel.add(lblPS);
	    lblPS_Raw = new JLabel("Bits Por Segundo: " + Juego.bitsPS + " bits");
	    panel.add(lblPS_Raw);
	    lblSumMejoras = new JLabel("Mejoras Totales: " + (Juego.mejora1 + Juego.mejora2 + Juego.mejora3 + Juego.mejora4));
	    panel.add(lblSumMejoras);
	    lblBitsPC = new JLabel("Bits Por Click: " + Juego.bitsPC + " bits");
	    panel.add(lblBitsPC);
	    lblVersion = new JLabel("Versión 0.5");
	    panel.add(lblVersion);
	    
	    if(Menu.sesion) {
	    Functions.cargarDrivers();
	    try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpuclicker", "root", "");
			Statement stmtMax = conn.createStatement();
			ResultSet rsMax = stmtMax.executeQuery("SELECT bitsMaximos FROM estadisticas WHERE idUsuario = " + idUsuario);
			if(rsMax.next()) {
				lblMaximos.setText("Bits Máximos: " + rsMax.getInt("bitsMaximos") + " bits");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
		
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
