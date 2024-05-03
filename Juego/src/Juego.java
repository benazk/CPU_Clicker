import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class Juego extends JFrame implements ActionListener, Runnable, MouseListener {

	public static String[] frasesTexto = { "<html>los ticks son una manera de medir el tiempo utilizada en el juego Minecraft, cada Tick equivale a 50 milisegundos</html>", 
			"<html>El cache es un componente que guarda datos para que al volver a acceder a ellos, lo haga más rápido</html>", 
			"<html>FPS significa Fotogramas por Segundo, se utiliza para medir cuantas imágenes muestra por segundo un dispositivo</html>", 
			"<html>El transistor es el dispositivo que lleva la corriente por el procesador</html>", 
			"<html>Los hercios son la unidad de medida con la que se expresa la frecuencia con la que un evento sucede durante un tiempo determinado</html>", 
			"<html>Es una placa fina de un material semiconductor (conductor o aislante de corriente), con la que construyen microcircuitos</html>", 
			"<html>Cron es un servicio de Linux que permite ejecutar comandos en un momento determinado, por ejemplo, cada minuto, día, semana o mes</html>", 
			"<html>Cache: Un espacio de memoria que almacena los datos de manera temporal para que el programa que los requiera no se quede sin datos durante una transferencia de dato</html>"};

	public static JLabel frases = new JLabel();
	
	public static Thread bitsObtenidos;
	
	public static Thread extras;

	public static Juego juego;

	public static JButton btnCPU, btnMejora1, btnMejora2, btnMejora3, btnMejora4, btnMejora5, btnBSoD;

	public static JLabel lblBits, lblBitsPS, lblMultiplicador;

	public static JLabel lblCostoM1 = new JLabel("5 bits"), lblNombreM1 = new JLabel("Ticks"),
			lblCantidadM1 = new JLabel("0"), lblCostoM2 = new JLabel("40 bits"), lblNombreM2 = new JLabel("Cache"),
			lblCantidadM2 = new JLabel("0"),

			lblCostoM3 = new JLabel("300 bits"), lblNombreM3 = new JLabel("FPS"), lblCantidadM3 = new JLabel("0"),

			lblCostoM4 = new JLabel("2000 bits"), lblNombreM4 = new JLabel("Transistores"),
			lblCantidadM4 = new JLabel("0");

	public static JLabel lblBitsCant, lblInfo, lblInfoMejora1, lblInfoMejora2, lblInfoMejora3, lblInfoMejora4;

	public static long bits = 0;

	public static int bitsPS = 0;

	public static int bitsPC = 1;

	public static int BSoD = 0;

	public static long BSoDPrice = 8000000 * (5 * (1 + BSoD));
	
	public static JButton btnPararTiempo = new JButton("ZaWarudo"), btnBitsGratis = new JButton("FreeFood"), btnPotenciadorBits = new JButton("MoarGalletas"); 
	
	public static JButton bonuses[] = {btnPararTiempo,btnBitsGratis, btnPotenciadorBits };
	
	public static boolean estadoBonus;
	
	public static JLabel lblBSoDNombre = new JLabel("Blue Screen Of Death"), lblBSoD_Cant = new JLabel(String.valueOf(BSoD)),
	lblBSoD_Precio = new JLabel(String.valueOf(BSoDPrice));
	
	public static int mejora1, mejora2, mejora3, mejora4;

	JPanel panel = new JPanel(new GridLayout(1, 8));

	JScrollPane panelDesplazador = new JScrollPane();

	public static Connection conn;

	
	
	Juego() {
		setTitle("CPU Clicker");

		setSize(1366, 768);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setLayout(null);

		btnCPU = new JButton("CPU");
		btnCPU.setLocation(100, 100);
		btnCPU.setSize(200, 200);
		add(btnCPU);
		btnCPU.addActionListener(this);
		btnCPU.addMouseListener(this);

		lblBits = new JLabel("40000000");
		lblBits.setLocation(175, 330);
		lblBits.setSize(200, 20);
		add(lblBits);

		lblBitsPS = new JLabel("0");
		lblBitsPS.setLocation(175, 360);
		lblBitsPS.setSize(80, 20);
		add(lblBitsPS);
		
		frases.setText(frasesTexto[(int)Math.floor(Math.random()*7)]);
		frases.setSize(350,45);
		frases.setLocation(400,100);
		add(frases);
		frases.addMouseListener(this);
		
		btnBSoD = new JButton();
		btnBSoD.setLayout(new GridLayout(2, 2));
		btnBSoD.add(lblBSoDNombre);
		btnBSoD.add(lblBSoD_Cant);
		btnBSoD.add(lblBSoD_Precio);
		btnBSoD.setLocation(820, 30);
		btnBSoD.setSize(400, 100);
		add(btnBSoD);
		btnBSoD.addActionListener(this);

		btnMejora1 = new JButton();
		btnMejora1.setLayout(new GridLayout(2, 2));
		btnMejora1.add(lblNombreM1);
		btnMejora1.add(lblCantidadM1);
		btnMejora1.add(lblCostoM1);
		btnMejora1.setLocation(900, 150);
		btnMejora1.setSize(250, 60);
		add(btnMejora1);
		btnMejora1.addActionListener(this);

		btnMejora2 = new JButton();
		btnMejora2.setLayout(new GridLayout(2, 2));
		btnMejora2.add(lblNombreM2);
		btnMejora2.add(lblCantidadM2);
		btnMejora2.add(lblCostoM2);
		btnMejora2.setLocation(900, 250);
		btnMejora2.setSize(250, 60);
		add(btnMejora2);
		btnMejora2.addActionListener(this);

		btnMejora3 = new JButton();
		btnMejora3.setLayout(new GridLayout(2, 2));
		btnMejora3.add(lblNombreM3);
		btnMejora3.add(lblCantidadM3);
		btnMejora3.add(lblCostoM3);
		btnMejora3.setLocation(900, 350);
		btnMejora3.setSize(250, 60);
		add(btnMejora3);
		btnMejora3.addActionListener(this);

		btnMejora4 = new JButton();
		btnMejora4.setLayout(new GridLayout(2, 2));
		btnMejora4.add(lblNombreM4);
		btnMejora4.add(lblCantidadM4);
		btnMejora4.add(lblCostoM4);
		btnMejora4.setLocation(900, 450);
		btnMejora4.setSize(250, 60);
		add(btnMejora4);
		btnMejora4.addActionListener(this);
		
		add(bonuses[0]);
		bonuses[0].setVisible(false);
		bonuses[0].addActionListener(this);
		bonuses[0].addMouseListener(this);
		bonuses[1].addActionListener(this);
		add(bonuses[1]);
		bonuses[1].setVisible(false);
		bonuses[2].addActionListener(this);
		add(bonuses[2]);
		bonuses[2].setVisible(false);
		
		/* Informacion de las mejoras
		panel.add(panelDesplazador);

		lblInfo = new JLabel("Hola\n\n\n\nHola");
		lblInfo.setSize(400, 100);
		lblInfo.setLocation(50, 0);
		panel.add(lblInfo);

		lblInfoMejora1 = new JLabel("Hola\n\n\n\nHola");
		lblInfoMejora1.setSize(400, 100);
		lblInfoMejora1.setLocation(50, 0);
		panel.add(lblInfoMejora1);

		lblInfoMejora1 = new JLabel("Hola\n\n\n\nHola");
		lblInfoMejora1.setSize(400, 100);
		lblInfoMejora1.setLocation(50, 0);
		panel.add(lblInfoMejora1);

		lblInfoMejora1 = new JLabel("Hola\n\n\n\nHola");
		lblInfoMejora1.setSize(400, 100);
		lblInfoMejora1.setLocation(50, 0);
		panel.add(lblInfoMejora1);

		add(panel);*/

	}

	public void actionPerformed(ActionEvent e) {
		bits = Integer.parseInt(lblBits.getText());
		JButton accion = (JButton) e.getSource();
		if (accion == btnCPU) {
			bits = Integer.parseInt(lblBits.getText());
			bits += bitsPC;
			lblBits.setText(String.valueOf(bits));
		}
		if (accion == btnMejora1) {
			mejora1 = Integer.parseInt(lblCantidadM1.getText());
			if (Integer.parseInt(lblBits.getText()) >= Functions.mejora1Price(mejora1)) {
				bits -= Functions.mejora1Price(mejora1); // Quitar costo de la mejora al dinero total
				lblBits.setText(String.valueOf(bits));
				mejora1++; // Sumar 1 a las mejoras totales de ese tipo
				lblCantidadM1.setText(String.valueOf(mejora1)); // Poner la cantidad de mejoras en el frame
				bitsPC = Functions.mejora1(mejora1) + 1; // Sumar bits por click teniendo en cuenta la mejora
				lblCostoM1.setText(String.valueOf(Functions.mejora1Price(mejora1)) + " bits"); // Poner el costo de la
																								// mejora

			}

		}
		if (accion == btnMejora2) {
			if (Integer.parseInt(lblBits.getText()) >= Functions.mejora2Price(mejora2)) {
				bits -= Functions.mejora2Price(mejora2);
				lblBits.setText(String.valueOf(bits));
				mejora2++;
				lblCantidadM2.setText(String.valueOf(mejora2));
				lblBitsPS.setText(String.valueOf(Integer.parseInt(lblBitsPS.getText()) + Functions.mejora2(mejora2)));
				lblCostoM2.setText(String.valueOf(Functions.mejora2Price(mejora2)) + " bits");
			}
		}

		if (accion == btnMejora3) {
			if (Integer.parseInt(lblBits.getText()) >= Functions.mejora3Price(mejora3)) {
				bits -= Functions.mejora3Price(mejora3);
				lblBits.setText(String.valueOf(bits));
				mejora3++;
				lblCantidadM3.setText(String.valueOf(mejora3));
				lblBitsPS.setText(String.valueOf(Integer.parseInt(lblBitsPS.getText()) + Functions.mejora3(mejora3)));
				lblCostoM3.setText(String.valueOf(Functions.mejora3Price(mejora3)) + " bits");
			}
		}
		if (accion == btnMejora4) {
			if (Integer.parseInt(lblBits.getText()) >= Functions.mejora4Price(mejora4)) {
				bits -= Functions.mejora4Price(mejora4);
				lblBits.setText(String.valueOf(bits));
				mejora4++;
				lblCantidadM4.setText(String.valueOf(mejora4));
				lblBitsPS.setText(String.valueOf(Integer.parseInt(lblBitsPS.getText()) + Functions.mejora4(mejora4)));
				lblCostoM4.setText(String.valueOf(Functions.mejora4Price(mejora4)) + " bits");
			}
		}
		if (accion == btnBSoD) {
			// Codigo para reiniciar en el caso de un BSoD
			if(bits > Integer.parseInt(lblBSoD_Precio.getText())) {
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpuclicker", "root", "");
					
					
					BSoD++;
					lblBSoD_Cant.setText(String.valueOf(BSoD));
					PreparedStatement stmtBSoD = conn.prepareStatement("UPDATE estadisticas SET BSoD = BSoD + 1  WHERE idUsuario = " + "'" + Functions.idUsuario() + "';");
					stmtBSoD.executeUpdate();
					
					bits = 0;
					lblBits.setText(String.valueOf(bits));
					PreparedStatement stmtbits = conn.prepareStatement("UPDATE estadisticas SET bitsActuales = 0  WHERE idUsuario = " + "'" + Functions.idUsuario() + "';");
					stmtbits.executeUpdate();
					
					bitsPS = 0;
					lblBitsPS.setText(String.valueOf(bitsPS));
					PreparedStatement stmtbitsPS = conn.prepareStatement("UPDATE estadisticas SET bitsPS = 0  WHERE idUsuario = " + "'" + Functions.idUsuario() + "';");
					stmtbitsPS.executeUpdate();
					
					bitsPC = 1;

					mejora1 = 0;
					lblCantidadM1.setText(String.valueOf(mejora1));
					
					mejora2 = 0;
					lblCantidadM2.setText(String.valueOf(mejora2));
					
					mejora3 = 0;
					lblCantidadM3.setText(String.valueOf(mejora3));
					
					mejora4 = 0;
					lblCantidadM4.setText(String.valueOf(mejora4));
					
					PreparedStatement stmtMejoras1 = conn.prepareStatement("UPDATE mejoras SET cantidadTicks = 0 WHERE idUsuario = " + "'" + Functions.idUsuario() + "';");
					stmtMejoras1.executeUpdate();
					PreparedStatement stmtMejoras2 = conn.prepareStatement("UPDATE mejoras SET cantidadCache = 0 WHERE idUsuario = " + "'" + Functions.idUsuario() + "';");
					stmtMejoras2.executeUpdate();
					PreparedStatement stmtMejoras3 = conn.prepareStatement("UPDATE mejoras SET cantidadFPS = 0 WHERE idUsuario = " + "'" + Functions.idUsuario() + "';");
					stmtMejoras3.executeUpdate();
					PreparedStatement stmtMejoras4 = conn.prepareStatement("UPDATE mejoras SET cantidadTransistores = 0 WHERE idUsuario = " + "'" + Functions.idUsuario() + "';");
					stmtMejoras4.executeUpdate();
					PreparedStatement stmtMejorasSum = conn.prepareStatement("UPDATE mejoras SET sumMejoras = 0 WHERE idUsuario = " + "'" + Functions.idUsuario() + "';");
					stmtMejorasSum.executeUpdate();
					
					lblCostoM1.setText(String.valueOf(Functions.mejora1Price(mejora1)) + " bits");
					lblCostoM2.setText(String.valueOf(Functions.mejora2Price(mejora2)) + " bits");
					lblCostoM3.setText(String.valueOf(Functions.mejora3Price(mejora3)) + " bits");
					lblCostoM4.setText(String.valueOf(Functions.mejora4Price(mejora4)) + " bits");
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}  
				
				
			}
		}
		if(accion == bonuses[0]) {
				bonuses[0].setVisible(false);
				estadoBonus = false;
			
		}
		if(accion == bonuses[1]) {
			bonuses[1].setVisible(false);
			bits += (int) Integer.parseInt(lblBitsPS.getText()) * 300;
			lblBits.setText(String.valueOf(bits));
			estadoBonus = false;
		}
		if(accion == bonuses[2]) {
			bonuses[2].setVisible(false);
			estadoBonus = false;
		}

		

	}

	

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
				bits = Integer.parseInt(lblBits.getText());
				bitsPS = Integer.parseInt(lblBitsPS.getText()) * (BSoD + 1);
				if (bits > 10) {
					bits += (int) Math.floor(bitsPS / 10);
				} else {
					bits += bitsPS;
				}
				lblBits.setText(String.valueOf(bits));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			int bonus = (int) Math.floor(Math.random()*60);
			estadoBonus = false;
			switch(bonus) {
			case 61:
				if(!estadoBonus) {
					bonuses[0].setSize(80,60);
					bonuses[0].setLocation(Functions.posicionRandomEnRango(10,600),Functions.posicionRandomEnRango(10,750));
					bonuses[0].setVisible(true);
					estadoBonus = true;
				}
			case 2:
				if(!estadoBonus) {
					bonuses[1].setSize(80,60);
					bonuses[1].setLocation(Functions.posicionRandomEnRango(10,600),Functions.posicionRandomEnRango(10,750));
					bonuses[1].setVisible(true);
					estadoBonus = true;

				}
			case 62:
				if(!estadoBonus) {
					bonuses[2].setSize(80,60);
					bonuses[2].setLocation(Functions.posicionRandomEnRango(10,600),Functions.posicionRandomEnRango(10,750));
					bonuses[2].setVisible(true);
					estadoBonus = true;
					
				}
			}
			
		}

	}

	public static void main(String[]args) {
		juego = new Juego();
		String filePath = "timestop.wav";
		Functions.playMusic(filePath);
		// Cargar datos de usuario si el usuario está iniciado sesión
		if (Menu.sesion) {
			int idUsuario = Functions.idUsuario();
			int bitsBBDD = 0;
			int bitsPSBBDD = 0;
			int cantidadTicks = 0;
			int cantidadCache = 0;
			int cantidadFPS = 0;
			int cantidadTransistores = 0;
			int BSoD_BBDD = 0;

			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpuclicker", "root", "");
				PreparedStatement stmtMejoras = conn
						.prepareStatement("SELECT * FROM mejoras WHERE idUsuario = " + "'" + idUsuario + "';");
				ResultSet rsMejoras = stmtMejoras.executeQuery();
				if (rsMejoras.next()) {
					cantidadTicks = rsMejoras.getInt("cantidadTicks");
					cantidadTicks = rsMejoras.getInt("cantidadCache");
					cantidadTicks = rsMejoras.getInt("cantidadFPS");
					cantidadTicks = rsMejoras.getInt("cantidadTransistores");
				}
				PreparedStatement stmtEstadisticas = conn
						.prepareStatement("SELECT * FROM estadisticas WHERE idUsuario = " + "'" + idUsuario + "';");
				ResultSet rsEstadisticas = stmtEstadisticas.executeQuery();
				if (rsEstadisticas.next()) {
					bitsBBDD = rsEstadisticas.getInt("bitsActuales");
					bitsPS = rsEstadisticas.getInt("bitsPS");
					BSoD_BBDD = rsEstadisticas.getInt("BSoD");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			lblBits.setText(String.valueOf(bitsBBDD));
			lblBitsPS.setText(String.valueOf(bitsPSBBDD));
			lblCantidadM1.setText(String.valueOf(cantidadTicks));
			lblCantidadM2.setText(String.valueOf(cantidadCache));
			lblCantidadM3.setText(String.valueOf(cantidadFPS));
			lblCantidadM4.setText(String.valueOf(cantidadTransistores));
			BSoD = BSoD_BBDD;
		}
		juego.setVisible(true);
		bitsObtenidos = new Thread(juego);
		extras = new Thread(juego);
		bitsObtenidos.start();
		extras.start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Bonus de clicks por segundo exponenciales
		int clicks = 0;
			if(e.getSource() == btnCPU && e.getClickCount() >= 1) {
				clicks++;
				bits += bitsPC * (clicks ^ 2);
				System.out.println(bitsPC * (clicks ^ 2));
			}
			if(e.getSource() == frases) {
				frases.setText(frasesTexto[(int)Math.floor(Math.random()*7)]);
			}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
