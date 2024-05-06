import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class Juego extends JFrame implements ActionListener, Runnable, MouseListener, WindowListener {

	static String[] frasesTexto = {
			"<html>los ticks son una manera de medir el tiempo utilizada en el juego Minecraft, cada Tick equivale a 50 milisegundos</html>",
			"<html>El cache es un componente que guarda datos para que al volver a acceder a ellos, lo haga más rápido</html>",
			"<html>FPS significa Fotogramas por Segundo, se utiliza para medir cuantas imágenes muestra por segundo un dispositivo</html>",
			"<html>El transistor es el dispositivo que lleva la corriente por el procesador</html>",
			"<html>Los hercios son la unidad de medida con la que se expresa la frecuencia con la que un evento sucede durante un tiempo determinado</html>",
			"<html>Es una placa fina de un material semiconductor (conductor o aislante de corriente), con la que construyen microcircuitos</html>",
			"<html>Cron es un servicio de Linux que permite ejecutar comandos en un momento determinado, por ejemplo, cada minuto, día, semana o mes</html>",
			"<html>Cache: Un espacio de memoria que almacena los datos de manera temporal para que el programa que los requiera no se quede sin datos durante una transferencia de dato</html>" };

	static JLabel frases = new JLabel();

	static JLabel BSoDImg;
	
	public static Juego juego;// Ventana del juego
	
	public static Thread bitsObtenidos;// Hilo para los bits

	static JButton btnSalir, btnCPU, btnMejora1, btnMejora2, btnMejora3, btnMejora4, btnMejora5, btnBSoD; // Botones de mejoras

	public static JLabel lblBits, lblBitsPS, lblMultiplicador, lblBitsPC; // Labels para datos varios

	public static JLabel lblCostoM1 = new JLabel("5 bits"), lblNombreM1 = new JLabel("Ticks"), // Labels para los botones de mejoras
			lblCantidadM1 = new JLabel(), lblCostoM2 = new JLabel("40 bits"), lblNombreM2 = new JLabel("Cache"),
			lblCantidadM2 = new JLabel("0"),

			lblCostoM3 = new JLabel("300 bits"), lblNombreM3 = new JLabel("FPS"), lblCantidadM3 = new JLabel("0"),

			lblCostoM4 = new JLabel("2000 bits"), lblNombreM4 = new JLabel("Transistores"),
			lblCantidadM4 = new JLabel("0");

	public static JLabel lblBitsCant, lblInfo, lblInfoMejora1, lblInfoMejora2, lblInfoMejora3, lblInfoMejora4; // Labels en desuso

	public static long bits = 0; // Variable de los bits

	public static int bitsPS = 0; // Variable de los bits por segundo

	public static int bitsPC = 1; // Variable de los bits por click

	public static int clicks = 0; // Variable de los clicks hechos

	public static int BSoD = 0; // Variable de las veces que has hecho BSoD
	
	public static double tiempo = 2000; // Variable para el tiempo de juego

	public static long BSoDPrice = 8000000 * (5 * (1 + BSoD)); // Variable del precio del BSoD

	static JButton btnPararTiempo = new JButton("ZaWarudo"), btnBitsGratis = new JButton("FreeFood"),
			btnPotenciadorBits = new JButton("MoarGalletas");  // Botones de bonuses

	static JButton bonuses[] = { btnPararTiempo, btnBitsGratis, btnPotenciadorBits }; // array con los botones de bonuses

	static boolean estadoBonus; // Verifica si un boton está en pantalla o no

	public static JLabel lblBSoDNombre = new JLabel("Blue Screen Of Death"),  // Labels para el botón de BSoD
			lblBSoD_Cant = new JLabel(String.valueOf(BSoD)), lblBSoD_Precio = new JLabel(String.valueOf(BSoDPrice));

	public static int mejora1, mejora2, mejora3, mejora4; // Variables con la cantidad de mejoras individuales

	static double contadorTimestop, contadorGuardado = 300;

	static boolean guardadoInput = false;
	
	static boolean abierto = true;

	static Connection conn; // Variable con la conexión

	
	
	Juego() {
		
		// Personalización
		setTitle("CPU Clicker");

		setSize(1366, 768);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setLayout(null);
		
		setUndecorated(true);
	    
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);

		btnSalir = new JButton("Volver al Menú");
		btnSalir.setLocation(0,0);
		btnSalir.setSize(150,50);
		add(btnSalir);
		btnSalir.addActionListener(this);
		
		btnCPU = new JButton("CPU");
		btnCPU.setLocation(100, 100);
		btnCPU.setSize(200, 200);
		add(btnCPU);
		btnCPU.addActionListener(this);
		btnCPU.addMouseListener(this);

		lblBits = new JLabel("0 bits");
		lblBits.setLocation(175, 330);
		lblBits.setSize(200, 20);
		add(lblBits);

		lblBitsPS = new JLabel("0 bits P/S");
		lblBitsPS.setLocation(175, 360);
		lblBitsPS.setSize(150, 20);
		add(lblBitsPS);
		
		lblBitsPC = new JLabel(bitsPC + " bits");
		lblBitsPC.setLocation(Functions.posicionRandomEnRango(70,340), Functions.posicionRandomEnRango(70,340));
		lblBitsPC.setSize(150, 20);
		add(lblBitsPC);
		lblBitsPC.setVisible(false);

		frases.setText(frasesTexto[(int) Math.floor(Math.random() * 7)]);
		frases.setSize(350, 45);
		frases.setLocation(400, 100);
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
		lblCantidadM1.setText(String.valueOf(mejora1));
		btnMejora1.add(lblNombreM1);
		btnMejora1.add(lblCantidadM1);
		btnMejora1.add(lblCostoM1);
		btnMejora1.setLocation(900, 150);
		btnMejora1.setSize(250, 60);
		add(btnMejora1);
		btnMejora1.addActionListener(this);

		btnMejora2 = new JButton();
		btnMejora2.setLayout(new GridLayout(2, 2));
		lblCantidadM2.setText(String.valueOf(mejora2));
		btnMejora2.add(lblNombreM2);
		btnMejora2.add(lblCantidadM2);
		btnMejora2.add(lblCostoM2);
		btnMejora2.setLocation(900, 250);
		btnMejora2.setSize(250, 60);
		add(btnMejora2);
		btnMejora2.addActionListener(this);

		btnMejora3 = new JButton();
		btnMejora3.setLayout(new GridLayout(2, 2));
		lblCantidadM3.setText(String.valueOf(mejora3));
		btnMejora3.add(lblNombreM3);
		btnMejora3.add(lblCantidadM3);
		btnMejora3.add(lblCostoM3);
		btnMejora3.setLocation(900, 350);
		btnMejora3.setSize(250, 60);
		add(btnMejora3);
		btnMejora3.addActionListener(this);

		btnMejora4 = new JButton();
		btnMejora4.setLayout(new GridLayout(2, 2));
		lblCantidadM3.setText(String.valueOf(mejora3));
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

		
		ImageIcon icon = new ImageIcon("BSoD.png");
		BSoDImg = new JLabel();
		BSoDImg.setIcon(icon);
		BSoDImg.setLocation(0,0);
		BSoDImg.setSize(1400,450);
		add(BSoDImg);
		BSoDImg.setVisible(false);
		
		if (Menu.sesion) {
			Functions.cargado();
		}

	}
	public void actionPerformed(ActionEvent e) {
		
		System.out.println("clicks efectuados: " + clicks);
		System.out.println("bits por click: " + bitsPC);
		System.out.println("bits actuales: " + bits);
		System.out.println("BSoDs: " + BSoD);
		System.out.println("Usuario: " + Menu.usuario);
		System.out.println("Sesion: " + Menu.sesion);
		
		
		bits = Integer.parseInt(lblBits.getText().substring(0,lblBits.getText().length()-5));
		JButton accion = (JButton) e.getSource();
		if (accion == btnCPU) { // Al clicar el CPU, hará esto
			bits = Integer.parseInt(lblBits.getText().substring(0,lblBits.getText().length()-5));
			bits += bitsPC;
			System.out.println(bitsPC);
			lblBits.setText(String.valueOf(bits) + " bits");
			lblBitsPC.setLocation(getMousePosition());
			lblBitsPC.setText(bitsPC + " bits");
			lblBitsPC.setVisible(true);
			setComponentZOrder(lblBitsPC,0);
			
		}
		if (accion == btnMejora1) { // Al clicar las mejoras, hará esto
			mejora1 = Integer.parseInt(lblCantidadM1.getText());
			if (Integer.parseInt(lblBits.getText().substring(0,lblBits.getText().length()-5)) >= Functions.mejora1Price(mejora1)) {
				bits -= Functions.mejora1Price(mejora1); // Quitar costo de la mejora al dinero total
				lblBits.setText(String.valueOf(bits) + " bits");
				mejora1++; // Sumar 1 a las mejoras totales de ese tipo
				lblCantidadM1.setText(String.valueOf(mejora1)); // Poner la cantidad de mejoras en el frame
				bitsPC = Functions.mejora1(mejora1) + 1; // Sumar bits por click teniendo en cuenta la mejora
				lblCostoM1.setText(String.valueOf(Functions.mejora1Price(mejora1)) + " bits"); // Poner el costo de la
																								// mejora
				guardadoInput = true;
			}

		}
		if (accion == btnMejora2) {
			if (Integer.parseInt(lblBits.getText().substring(0,lblBits.getText().length()-5)) >= Functions.mejora2Price(mejora2)) {
				bits -= Functions.mejora2Price(mejora2);
				lblBits.setText(String.valueOf(bits) + " bits");
				mejora2++;
				lblCantidadM2.setText(String.valueOf(mejora2));
				lblBitsPS.setText(String.valueOf(Integer.parseInt(lblBitsPS.getText().substring(0,lblBitsPS.getText().length()-9)) + Functions.mejora2(mejora2)) + " bits P/S");
				lblCostoM2.setText(String.valueOf(Functions.mejora2Price(mejora2)) + " bits");
				guardadoInput = true;
			}
		}

		if (accion == btnMejora3) {
			if (Integer.parseInt(lblBits.getText().substring(0,lblBits.getText().length()-5)) >= Functions.mejora3Price(mejora3)) {
				bits -= Functions.mejora3Price(mejora3);
				lblBits.setText(String.valueOf(bits) + " bits");
				mejora3++;
				lblCantidadM3.setText(String.valueOf(mejora3));
				lblBitsPS.setText(String.valueOf(Integer.parseInt(lblBitsPS.getText().substring(0,lblBitsPS.getText().length()-9)) + Functions.mejora3(mejora3)) + " bits P/S");
				lblCostoM3.setText(String.valueOf(Functions.mejora3Price(mejora3)) + " bits");
				guardadoInput = true;
			}
		}
		if (accion == btnMejora4) {
			if (Integer.parseInt(lblBits.getText().substring(0,lblBits.getText().length()-5)) >= Functions.mejora4Price(mejora4)) {
				bits -= Functions.mejora4Price(mejora4);
				lblBits.setText(String.valueOf(bits) + " bits");
				mejora4++;
				lblCantidadM4.setText(String.valueOf(mejora4));
				lblBitsPS.setText(String.valueOf(Integer.parseInt(lblBitsPS.getText().substring(0,lblBitsPS.getText().length()-9)) + Functions.mejora4(mejora4)) + " bits P/S");
				lblCostoM4.setText(String.valueOf(Functions.mejora4Price(mejora4)) + " bits");
				guardadoInput = true;
			}
		}
		if (accion == btnBSoD) {
			// Codigo para reiniciar en el caso de un BSoD (Pone todos los datos excepto el BSoD a 0)
			if (bits > Integer.parseInt(lblBSoD_Precio.getText())) {
				
				BSoD++;
				
				bits = 0;
				lblBits.setText(bits + " bits");
				
				
				bitsPS = 0;
				lblBitsPS.setText(bitsPS + " bits P/S");
				
				bitsPC = 1;
				
				clicks = 0;
				
				mejora1 = 0;
				lblCantidadM1.setText(String.valueOf(mejora1));

				mejora2 = 0;
				lblCantidadM2.setText(String.valueOf(mejora2));

				mejora3 = 0;
				lblCantidadM3.setText(String.valueOf(mejora3));

				mejora4 = 0;
				lblCantidadM4.setText(String.valueOf(mejora4));

				lblCostoM1.setText(String.valueOf(Functions.mejora1Price(mejora1)) + " bits");
				lblCostoM2.setText(String.valueOf(Functions.mejora2Price(mejora2)) + " bits");
				lblCostoM3.setText(String.valueOf(Functions.mejora3Price(mejora3)) + " bits");
				lblCostoM4.setText(String.valueOf(Functions.mejora4Price(mejora4)) + " bits");
				
				if(Menu.sesion) {
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpuclicker", "root", "");

					lblBSoD_Cant.setText(String.valueOf(BSoD) + "  BSoDs");
					PreparedStatement stmtBSoD = conn
							.prepareStatement("UPDATE estadisticas SET BSoD = " + BSoD + "  WHERE idUsuario = " + "'"
									+ Functions.idUsuario() + "';");
					stmtBSoD.executeUpdate();

					
					lblBits.setText(String.valueOf(bits) + " bits");
					PreparedStatement stmtbits = conn
							.prepareStatement("UPDATE estadisticas SET bitsActuales = 0  WHERE idUsuario = " + "'"
									+ Functions.idUsuario() + "';");
					stmtbits.executeUpdate();

					
					lblBitsPS.setText(String.valueOf(bitsPS) + " bits P/S");
					PreparedStatement stmtbitsPS = conn
							.prepareStatement("UPDATE estadisticas SET bitsPS = 0  WHERE idUsuario = " + "'"
									+ Functions.idUsuario() + "';");
					stmtbitsPS.executeUpdate();

					
					PreparedStatement stmtMejoras1 = conn
							.prepareStatement("UPDATE mejoras SET cantidadTicks = 0 WHERE idUsuario = " + "'"
									+ Functions.idUsuario() + "';");
					stmtMejoras1.executeUpdate();
					PreparedStatement stmtMejoras2 = conn
							.prepareStatement("UPDATE mejoras SET cantidadCache = 0 WHERE idUsuario = " + "'"
									+ Functions.idUsuario() + "';");
					stmtMejoras2.executeUpdate();
					PreparedStatement stmtMejoras3 = conn
							.prepareStatement("UPDATE mejoras SET cantidadFPS = 0 WHERE idUsuario = " + "'"
									+ Functions.idUsuario() + "';");
					stmtMejoras3.executeUpdate();
					PreparedStatement stmtMejoras4 = conn
							.prepareStatement("UPDATE mejoras SET cantidadTransistores = 0 WHERE idUsuario = " + "'"
									+ Functions.idUsuario() + "';");
					stmtMejoras4.executeUpdate();
					PreparedStatement stmtMejorasSum = conn
							.prepareStatement("UPDATE mejoras SET sumMejoras = 0 WHERE idUsuario = " + "'"
									+ Functions.idUsuario() + "';");
					stmtMejorasSum.executeUpdate();
					guardadoInput = true;
					

				} catch (SQLException e1) {

					e1.printStackTrace();
				}
				}
			}
		}
		if (accion == bonuses[0]) { // Al clicar el bonus 1, hará esto
			bonuses[0].setVisible(false);
			String filePath = "timestop.wav";
			Functions.playMusic(filePath);
			estadoBonus = false;
			guardadoInput = true;
		}
		if (accion == bonuses[1]) { // Al clicar el bonus 2, hará esto
			bonuses[1].setVisible(false);
			bits += (int) Integer.parseInt(lblBitsPS.getText().substring(0,lblBitsPS.getText().length()-9)) * 300;
			lblBits.setText(String.valueOf(bits) + " bits");
			estadoBonus = false;
			guardadoInput = true;
		}
		if (accion == bonuses[2]) { // Al clicar el bonus 3, hará esto
			bonuses[2].setVisible(false);
			estadoBonus = false;
			guardadoInput = true;
		}
		if(accion == btnSalir) {
			abierto = false;
			Functions.guardarDatosLocal();
			if(Menu.sesion) {
				Functions.guardado();
			}
			this.dispose();
		}

	}

	@Override
	public void run() {
		while (abierto) {  // Bucle para ir aumentando los bits
			try {
				
				Thread.sleep(100);
				tiempo += 0.1;
				contadorGuardado += 0.1;
				bits = Integer.parseInt(lblBits.getText().substring(0,lblBits.getText().length()-5));
				bitsPS = Integer.parseInt(lblBitsPS.getText().substring(0,lblBitsPS.getText().length()-9)) * (BSoD + 1);
				if (bits > 10) {
					bits += (int) Math.floor(bitsPS / 10);
				} else {
					bits += bitsPS;
				}
				lblBits.setText(String.valueOf(bits) + " bits");
				if(contadorGuardado >= 300 && !Menu.sesion || guardadoInput && !Menu.sesion) {
					Functions.actualizarDatos();
					Functions.guardarDatosLocal();
					contadorGuardado = 0;
					guardadoInput = false;
				}
				
				if(contadorGuardado >= 300 && Menu.sesion) {
					Functions.guardado();
				} 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			int bonus = (int) Math.floor(Math.random() * 60);
			estadoBonus = false;
			switch (bonus) { // Condiciones para los bonuses
			case 1:
				if (!estadoBonus) {
					bonuses[0].setSize(60, 60);
					bonuses[0].setLocation(Functions.posicionRandomEnRango(10, 400),
							Functions.posicionRandomEnRango(20, 740));
					bonuses[0].setVisible(true);
					estadoBonus = true;
				}
			case 63:
				if (!estadoBonus) {
					bonuses[1].setSize(60, 60);
					bonuses[1].setLocation(Functions.posicionRandomEnRango(10, 400),
							Functions.posicionRandomEnRango(20, 740));
					bonuses[1].setVisible(true);
					estadoBonus = true;

				}
			case 62:
				if (!estadoBonus) {
					bonuses[2].setSize(60, 60);
					bonuses[2].setLocation(Functions.posicionRandomEnRango(10, 400),
							Functions.posicionRandomEnRango(20, 740));
					bonuses[2].setVisible(true);
					estadoBonus = true;

				}
			}
			
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		juego = new Juego();
		bitsObtenidos = new Thread(juego);
		Functions.mantenerSesiónLocal();
		Functions.cargarDatosLocal();
		String filePath = "timestop.wav";
		// Functions.playMusicLoop(filePath);
		// Cargar datos de usuario si el usuario está iniciado sesión
		
		juego.setVisible(true);
		
		bitsObtenidos.start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Bonus de clicks por segundo exponenciales
		int clicksBonus = 0;
		if (e.getSource() == btnCPU && e.getClickCount() >= 1) {
			clicksBonus++;
			bits += bitsPC * (clicksBonus ^ 2);
		}
		if (e.getSource() == frases) {
			frases.setText(frasesTexto[(int) Math.floor(Math.random() * 7)]);
		}
		
		if(e.getSource() == btnCPU) {
			clicks++;
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
	@Override
	public void windowClosing(WindowEvent e) {

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
