import java.awt.Color;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;


public class Info extends JFrame implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static JLabel lblTitulo, lblActuales, lblMaximos, lblPS, lblSumMejoras, lblBitsPC, lblClicks, lblMins, lblArquitectura, lblVersion;
	
	public static JLabel lblMargin;
	
	public static Info info;
	
	public static JPanel panel = new JPanel();
	
	int idUsuario = Functions.idUsuario();
	
	public static final DecimalFormat df = new DecimalFormat("0.00");
	
	Connection conn;
	
	public static Thread hilo;
	
	JScrollPane scroll = new JScrollPane(panel);
	
	Info() throws IOException{
		
		try {
			
			Functions.cargarDatosLocal();
			
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			
	        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	                
	        add(panel);
	        
	        panel.setBackground(new Color(0,0,0));
	        
			setTitle("CPU Clicker");

			setSize(400, 500);

			setUndecorated(true);
		    
			getRootPane().setWindowDecorationStyle(JRootPane.NONE);

			setLayout(null);
		    
		    panel.setLayout(new GridLayout(11,1));
		    
		    add(panel);
		    
		    
		    panel.setBounds(40,20,350,450);
		    
		    lblTitulo = new JLabel(" Estadísticas");
		    panel.add(lblTitulo);
		    lblTitulo.setForeground(Juego.color);
		    
		    lblActuales = new JLabel(" Bits Actuales: " + Juego.bits + " bits");
		    panel.add(lblActuales);
		    lblActuales.setForeground(Juego.color);
		    
		    lblMaximos = new JLabel(" Bits Máximos: " + Juego.bitsMax + " bits");
		    panel.add(lblMaximos);
		    lblMaximos.setForeground(Juego.color);
		    
		    lblPS = new JLabel(" Bits Por Segundo: " + Juego.bitsPS * (Juego.BSoD + 1) + " bits");
		    panel.add(lblPS);
		    lblPS.setForeground(Juego.color);
		    
		    lblSumMejoras = new JLabel(" Mejoras Totales: " + (Juego.mejora1 + Juego.mejora2 + Juego.mejora3 + Juego.mejora4) + " mejoras");
		    panel.add(lblSumMejoras);
		    lblSumMejoras.setForeground(Juego.color);
		    
		    lblBitsPC = new JLabel(" Bits Por Click: " + Juego.bitsPC + " bits");
		    panel.add(lblBitsPC);
		    lblBitsPC.setForeground(Juego.color);
		    
		    lblClicks = new JLabel(" Clicks efectuados: " + Juego.bitsPC);
		    panel.add(lblClicks);
		    lblClicks.setForeground(Juego.color);
		    
		    if(Juego.tiempo < 3600) {
		    	lblMins = new JLabel(" Tiempo Jugado: " + df.format(Juego.tiempo) + " mins");
			    panel.add(lblMins);
			    lblMins.setForeground(Juego.color);
		    }
		    else {
		    	lblMins = new JLabel(" Tiempo Jugado: " + (df.format(Juego.tiempo / 3600)) + "h");
			    panel.add(lblMins);
			    lblMins.setForeground(Juego.color);
		    }
		    lblArquitectura = new JLabel(" Nombre de la arquitectura: Magnus");
		    lblArquitectura.setForeground(Juego.color);
		    panel.add(lblArquitectura);
		    lblVersion = new JLabel("Versión 0.5");
		    panel.add(lblVersion);
		    
		    if(Menu.sesion) {
		    Functions.cargarDrivers();
		    try {
		    	conn = DriverManager.getConnection("jdbc:mysql://hl1235.dinaserver.com/CPUClicker", "ibangames", "aW=112jWdKlHD013a.O");
				Statement stmtMax = conn.createStatement();
				ResultSet rsMax = stmtMax.executeQuery("SELECT bitsMaximos FROM estadisticas WHERE idUsuario = " + idUsuario);
				if(rsMax.next()) {
					lblMaximos.setText("Bits Máximos: " + rsMax.getLong("bitsMaximos") + " bits");
				}
				Statement stmtMins = conn.createStatement();
				ResultSet rsMins = stmtMins.executeQuery("SELECT minutosJugados FROM estadisticas WHERE idUsuario = " + idUsuario);
				if(rsMins.next()) {
					if(Juego.tiempo < 3600) {
						lblMins.setText("Tiempo Jugado: " + Juego.tiempo + " mins");
					}
					else {
						lblMins.setText("Tiempo Jugado: " + (df.format(Juego.tiempo / 60)) + "h");
					}
				}
				
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    
		    setVisible(true);
		    }
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
			
		Info info = new Info();
		hilo = new Thread(info);
	    }

	
	



	@Override
	public void run() {
		while(Juego.abierto)
		try{
			Thread.sleep(1000);
		if (!Menu.sesion) {
			Functions.actualizarDatos();
			try {
				File file = new File("datos.txt");
				BufferedReader reader = null;
				try {
					reader = new BufferedReader(new FileReader(file));
					lblActuales.setText("Bits Actuales: " + reader.readLine() + " bits");
					lblMaximos.setText(" Bits Máximos: " + reader.readLine() + " bits");
					lblPS.setText("Bits Por Segundo: " + reader.readLine() + " bits P/S");
					lblBitsPC.setText(" Bits Por Click: " + reader.readLine() + " bits");
					lblClicks.setText(" Clicks efectuados: " + reader.readLine());
					lblSumMejoras.setText(" Mejoras Totales: " + (Integer.parseInt(reader.readLine()) + Integer.parseInt(reader.readLine()) + Integer.parseInt(reader.readLine()) + reader.readLine())  + " mejoras");
					if(Double.parseDouble(reader.readLine()) < 3600) {
				    	lblMins.setText(" Tiempo Jugado: " + df.format(Double.parseDouble(reader.readLine()) / 60) + " mins");
					    lblMins.setForeground(Juego.color);
				    }
				    else {
				    	lblMins.setText(" Tiempo Jugado: " + (df.format(Double.parseDouble(reader.readLine()) / 3600)) + "h");
					    lblMins.setForeground(Juego.color);
				    }
					lblArquitectura.setText(" Nombre de la arquitectura: " + reader.readLine());
					reader.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (Menu.sesion) {
			Functions.cargado();
			Functions.actualizarDatos();
		}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
		
}
