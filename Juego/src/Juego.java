import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;


public class Juego extends JLabel implements ActionListener, Runnable, MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static String[] frasesTexto = {
			"<html>Los ticks son una manera de medir el tiempo utilizada en el juego Minecraft, cada Tick equivale a 50 milisegundos</html>",
			"<html>El cache es un componente que guarda datos para que al volver a acceder a ellos, lo haga más rápido</html>",
			"<html>FPS significa Fotogramas por Segundo, se utiliza para medir cuantas imágenes muestra por segundo un dispositivo</html>",
			"<html>El transistor es el dispositivo que lleva la corriente por el procesador</html>",
			"<html>Los hercios son la unidad de medida con la que se expresa la frecuencia con la que un evento sucede durante un tiempo determinado</html>",
			"<html>Es una placa fina de un material semiconductor (conductor o aislante de corriente), con la que construyen microcircuitos</html>",
			"<html>Cron es un servicio de Linux que permite ejecutar comandos en un momento determinado, por ejemplo, cada minuto, día, semana o mes</html>",
			"<html>Cache: Un espacio de memoria que almacena los datos de manera temporal para que el programa que los requiera no se quede sin datos durante una transferencia de dato</html>" };

	static JLabel frases = new JLabel();

	static JLabel BSoDImg;

	public static Thread bitsObtenidos, hilo;// Hilo para los bits

	static Info info;

	static Configuracion config;

	static JButton btnSalir, btnCPU, btnOpc, btnEst, btnMejora1, btnMejora2, btnMejora3, btnMejora4, btnMejora5,
			btnBSoD; // Botones de mejoras

	public static JLabel lblArquitectura, lblBits, lblBitsPS, lblMultiplicador, lblBitsPC; // Labels para datos varios

	public static JLabel lblCostoM1 = new JLabel("5 bits"), lblNombreM1 = new JLabel("Ticks"), // Labels para los
																								// botones de mejoras
			lblCantidadM1 = new JLabel("0"), lblCostoM2 = new JLabel("40 bits"), lblNombreM2 = new JLabel("Cache"),
			lblCantidadM2 = new JLabel("0"),

			lblCostoM3 = new JLabel("300 bits"), lblNombreM3 = new JLabel("FPS"), lblCantidadM3 = new JLabel("0"),

			lblCostoM4 = new JLabel("2000 bits"), lblNombreM4 = new JLabel("Transistores"),
			lblCantidadM4 = new JLabel("0");

	public static String arquitectura = "Magnus";

	public static long bits = 0; // Variable de los bits

	public static long bitsMax = 0; // Variable de los bits máximos

	public static long bitsPS = 0; // Variable de los bits por segundo

	public static int bitsPC = 0; // Variable de los bits por click

	public static int clicks = 0; // Variable de los clicks hechos

	public static int BSoD = 0; // Variable de las veces que has hecho BSoD

	public static double tiempo = 0; // Variable para el tiempo de juego

	public static long BSoDPrice = 8000000; // Variable del precio del BSoD
	
	static JButton btnPararTiempo = new JButton("Click"), btnBitsGratis = new JButton("Click"),
			btnPotenciadorBits = new JButton("Click"); // Botones de bonuses

	static JButton bonuses[] = { btnPararTiempo, btnBitsGratis, btnPotenciadorBits }; // array con los botones de
																						// bonuses

	static boolean estadoBonus = false; // Verifica si un boton está en pantalla o no

	static JLabel lblBSoDNombre = new JLabel("Blue Screen Of Death"), // Labels para el botón de BSoD
			lblBSoD_Cant = new JLabel(String.valueOf(BSoD)), lblBSoD_Precio = new JLabel(String.valueOf(BSoDPrice) + " bits");

	static JLabel lblBSoD;
	
	static ImageIcon bsod;
	
	public static long mejora1, mejora2, mejora3, mejora4; // Variables con la cantidad de mejoras individuales

	static double contadorTimestop = 0, contadorBuffer, contadorWafer = 1; // Contadores para parar el tiempo, tener un
																			// bonus de bits por 120 segundos y que un
																			// texto apareza en pantalla por 1 segundo

	static long bitsTimestop, bitsPCoriginal, bitsPSoriginal;  // Los valores originales antes de clicar los bonuses

	static int timestopMultiplier; // El multiplicador de los bits por click en el timestop

	static JLabel lblTimeStop, lblBuffer, lblWafer; //Labels para los temporizadores y los bits instantáneos

	int bonus;
	
	static boolean abierto = true;

	static Timer timer;

	static Timer timerAnimacion;

	static Connection conn; // Variable con la conexión

	public static Color color = new Color(88, 184, 4);

	static boolean panelActivoEst = false;

	static boolean panelActivoOpc = false;
	
	Font font;
	
	public Aplicacion aplicacion;
	
	Graphics g;

	Juego(Aplicacion aplicacion) throws NumberFormatException, IOException {
		this.aplicacion = aplicacion;
		/*try {
			InputStream file = Canvas.class.getResourceAsStream("RammettoOne-Regular.ttf");
			font = Font.createFont(Font.TRUETYPE_FONT, file);

			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);

			font = font.deriveFont(Font.PLAIN, 14);

		} catch (IOException | FontFormatException ex) {
			System.out.println(ex.getMessage());
		}*/

		// Personalización

		
		setLayout(null);

		aplicacion.setBackground(Color.BLACK);

		btnSalir = new JButton("Volver al Menú");
		btnSalir.setLocation(0, 0);
		btnSalir.setSize(150, 50);
		btnSalir.setForeground(color);
		add(btnSalir);
		btnSalir.addActionListener(this);

		lblArquitectura = new JLabel("CPU de arquitectura Magnus");
		lblArquitectura.setLocation(100, 70);
		lblArquitectura.setSize(200, 20);
		lblArquitectura.addMouseListener(this);
		lblArquitectura.setForeground(color);
		add(lblArquitectura);

		btnCPU = new JButton("CPU");
		btnCPU.setLocation(100, 100);
		btnCPU.setSize(200, 200);
		add(btnCPU);
		btnCPU.addActionListener(this);
		btnCPU.addMouseListener(this);
		switch (BSoD) {
		case 0:
			btnCPU.setBackground(new Color(180, 180, 180));
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		}

		LinkedList<Object> a = new LinkedList<Object>();
		a.add(0.3);
		a.add(0.3);
		a.add(new ColorUIResource(0, 0, 0));
		a.add(new ColorUIResource(60, 60, 60));
		a.add(new ColorUIResource(100, 100, 100));

		UIManager.put("Button.gradient", a);

		lblBits = new JLabel("0 bits");
		lblBits.setLocation(175, 330);
		lblBits.setSize(200, 20);
		lblBits.setForeground(color);
		add(lblBits);

		lblBitsPS = new JLabel(bitsPS + " bits P/S");
		lblBitsPS.setLocation(175, 360);
		lblBitsPS.setSize(150, 20);
		lblBitsPS.setForeground(color);
		add(lblBitsPS);

		lblBitsPC = new JLabel(bitsPC + " bits");
		lblBitsPC.setLocation(Functions.posicionRandomEnRango(70, 340), Functions.posicionRandomEnRango(70, 340));
		lblBitsPC.setSize(150, 20);
		add(lblBitsPC);
		lblBitsPC.setVisible(false);

		frases.setText(frasesTexto[(int) Math.floor(Math.random() * 7)]);
		frases.setSize(350, 45);
		frases.setLocation(400, 100);
		frases.setForeground(color);
		add(frases);
		frases.addMouseListener(this);

		btnOpc = new JButton("Opciones");
		btnOpc.setLocation(400, 600);
		btnOpc.setSize(200, 60);
		btnOpc.setForeground(color);
		btnOpc.setBorder(new LineBorder(new Color(0, 255, 0), 2));
		add(btnOpc);
		btnOpc.addActionListener(this);

		btnEst = new JButton("Estadísticas	");
		btnEst.setLocation(600, 600);
		btnEst.setSize(200, 60);
		btnEst.setForeground(color);
		btnEst.setBorder(new LineBorder(new Color(0, 255, 0), 2));
		add(btnEst);
		btnEst.addActionListener(this);

		btnBSoD = new JButton();
		btnBSoD.setLayout(new GridLayout(2, 2));
		btnBSoD.add(lblBSoDNombre);
		btnBSoD.add(lblBSoD_Cant);
		btnBSoD.add(lblBSoD_Precio);
		btnBSoD.setLocation(820, 30);
		btnBSoD.setSize(400, 100);
		lblBSoDNombre.setForeground(color);
		lblBSoD_Cant.setForeground(color);
		lblBSoD_Precio.setForeground(color);
		btnBSoD.setBorder(new LineBorder(new Color(0, 255, 0), 2));
		add(btnBSoD);
		btnBSoD.addActionListener(this);
		
		if(BSoD > 0) {
			BSoDPrice = BSoDPrice * (90 ^ BSoD);
		}

		btnMejora1 = new JButton();
		btnMejora1.setLayout(new GridLayout(2, 2));
		lblCantidadM1.setText(String.valueOf(mejora1));
		btnMejora1.add(lblNombreM1);
		btnMejora1.add(lblCantidadM1);
		btnMejora1.add(lblCostoM1);
		btnMejora1.setLocation(900, 150);
		btnMejora1.setSize(250, 60);
		lblCantidadM1.setForeground(color);
		lblNombreM1.setForeground(color);
		lblCostoM1.setForeground(color);
		btnMejora1.setBorder(new LineBorder(new Color(0, 255, 0), 2));
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
		lblCantidadM2.setForeground(color);
		lblNombreM2.setForeground(color);
		lblCostoM2.setForeground(color);
		btnMejora2.setBorder(new LineBorder(new Color(0, 255, 0), 2));
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
		lblCantidadM3.setForeground(color);
		lblNombreM3.setForeground(color);
		lblCostoM3.setForeground(color);
		btnMejora3.setBorder(new LineBorder(new Color(0, 255, 0), 2));
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
		lblCantidadM4.setForeground(color);
		lblNombreM4.setForeground(color);
		lblCostoM4.setForeground(color);
		btnMejora4.setBorder(new LineBorder(new Color(0, 255, 0), 2));
		add(btnMejora4);
		btnMejora4.addActionListener(this);

		add(bonuses[0]);
		bonuses[0].setBackground(new Color(255, 41, 25));
		bonuses[0].setVisible(false);
		bonuses[0].addActionListener(this);
		bonuses[0].addMouseListener(this);
		lblTimeStop = new JLabel("15");
		lblTimeStop.setLocation(200, 40);
		lblTimeStop.setSize(100, 20);
		lblTimeStop.setForeground(color);
		add(lblTimeStop);
		lblTimeStop.setVisible(false);

		bonuses[1].addActionListener(this);
		lblWafer = new JLabel();
		

		lblWafer.setFont(new Font("Arial", Font.PLAIN, 50));
		lblWafer.setForeground(Color.BLUE);
		lblWafer.setBackground(Color.BLACK);
		lblWafer.setBorder(new LineBorder(Color.BLUE, 2));
		lblWafer.setLocation(500, 280);
		lblWafer.setSize(500, 50);
		add(lblWafer);
		lblWafer.setVisible(false);

		add(bonuses[1]);
		bonuses[1].setBackground(new Color(255, 41, 25));
		bonuses[1].setVisible(false);

		bonuses[2].addActionListener(this);
		add(bonuses[2]);
		bonuses[2].setBackground(new Color(255, 41, 25));
		bonuses[2].setVisible(false);
		lblBuffer = new JLabel();
		lblBuffer.setForeground(color);
		lblBuffer.setLocation(lblTimeStop.getLocation());
		lblBuffer.setSize(lblTimeStop.getSize());
		add(lblBuffer);

		info = new Info();
		Info.panel.setSize(400, 450);
		Info.panel.setLocation(400, 150);
		add(Info.panel);
		Info.panel.setVisible(false);
		info.setVisible(false);
		hilo = new Thread(info);
		hilo.start();

		config = new Configuracion();
		Configuracion.panel.setSize(400, 450);
		Configuracion.panel.setLocation(400, 150);
		add(Configuracion.panel);
		Configuracion.panel.setVisible(false);
		config.setVisible(false);

		ImageIcon icon = new ImageIcon("BSoD.png");
		BSoDImg = new JLabel();
		BSoDImg.setIcon(icon);
		BSoDImg.setLocation(0, 0);
		BSoDImg.setSize(1400, 450);
		add(BSoDImg);
		BSoDImg.setVisible(false);
		Functions.mantenerSesiónLocal();
		Functions.cargarDatosLocal();
		if (Menu.sesion) {
			Functions.cargado();
		}
		bitsObtenidos = new Thread();
		ActionListener guardado = new ActionListener() { // Action listener para el guardado del juego automático
			public void actionPerformed(ActionEvent evt) {

				if (!Menu.sesion) {
					Functions.actualizarDatos();
					Functions.guardarDatosLocal();
				}

				if (Menu.sesion) {
					Functions.guardado();
				}
			}
		};
		timer = new Timer(1000, guardado); // Temporizador para ejecutar ese guardado cada 1 segundo
		timer.setRepeats(true);
		timer.start();
		String filePath = "game1.wav";
		Functions.playMusicLoop(filePath);
		bitsObtenidos.start();

	}

	public void actionPerformed(ActionEvent e) {

		bits = Long.parseLong(lblBits.getText().substring(0, lblBits.getText().length() - 5));
		JButton accion = (JButton) e.getSource();
		if (accion == btnCPU) { // Al clicar el CPU, hará esto
			timestopMultiplier = 1;
			if (contadorTimestop > 0) {
				timestopMultiplier++;
				bitsTimestop += bitsPC * timestopMultiplier;
				Juego.lblBits.setText(Long.parseLong(Juego.lblBits.getText().substring(0, Juego.lblBits.getText().length() - 5)) + bitsTimestop + " bits");
			} else {
				Juego.lblBits.setText(Long.parseLong(Juego.lblBits.getText().substring(0, Juego.lblBits.getText().length() - 5)) + bitsPC + " bits");
			}

			lblBitsPC.setLocation(getMousePosition());
			lblBitsPC.setText(bitsPC + " bits");
			lblBitsPC.setVisible(true);
			setComponentZOrder(lblBitsPC, 0);
		}
		if (accion == btnMejora1) { // Al clicar las mejoras, hará esto
			mejora1 = Integer.parseInt(lblCantidadM1.getText());
			if (Integer.parseInt(lblBits.getText().substring(0, lblBits.getText().length() - 5)) >= Functions
					.mejora1Price(mejora1)) {
				lblBits.setText(Long.parseLong(lblBits.getText().substring(0, lblBits.getText().length() - 5)) - Functions.mejora1Price(mejora1) + " bits"); // Quitar costo de la mejora al dinero total
				mejora1++; // Sumar 1 a las mejoras totales de ese tipo
				lblCantidadM1.setText(String.valueOf(mejora1)); // Poner la cantidad de mejoras en el frame
				bitsPC = (int) (Functions.mejora1(mejora1) + 1); // Sumar bits por click teniendo en cuenta la mejora
				lblCostoM1.setText(String.valueOf(Functions.mejora1Price(mejora1)) + " bits"); // Poner el costo de la mejora
				Functions.actualizarDatos();
				Functions.guardarDatosLocal(); 
			}

		}
		if (accion == btnMejora2) {
			if (Long.parseLong(lblBits.getText().substring(0, lblBits.getText().length() - 5)) >= Functions
					.mejora2Price(mejora2)) {
				lblBits.setText(Long.parseLong(lblBits.getText().substring(0, lblBits.getText().length() - 5)) - Functions.mejora2Price(mejora2) + " bits");
				mejora2++;
				lblBitsPS.setText(String.valueOf(Long.parseLong(lblBitsPS.getText().substring(0, lblBitsPS.getText().length() - 9))
						+ Functions.mejora2(mejora2)) + " bits P/S");
				lblCantidadM2.setText(String.valueOf(mejora2));
				lblCostoM2.setText(String.valueOf(Functions.mejora2Price(mejora2)) + " bits");
				Functions.actualizarDatos();
				Functions.guardarDatosLocal();
			}
		}

		if (accion == btnMejora3) {
			if (Long.parseLong(lblBits.getText().substring(0, lblBits.getText().length() - 5)) >= Functions
					.mejora3Price(mejora3)) {
				lblBits.setText(Long.parseLong(lblBits.getText().substring(0, lblBits.getText().length() - 5)) - Functions.mejora3Price(mejora3) + " bits");
				mejora3++;
				lblBitsPS.setText(String.valueOf(Long.parseLong(lblBitsPS.getText().substring(0, lblBitsPS.getText().length() - 9))
						+ Functions.mejora3(mejora3)) + " bits P/S");
				lblCantidadM3.setText(String.valueOf(mejora3));
				lblCostoM3.setText(String.valueOf(Functions.mejora3Price(mejora3)) + " bits");
				Functions.actualizarDatos();
				Functions.guardarDatosLocal();
			}
		}
		if (accion == btnMejora4) {
			if (Long.parseLong(lblBits.getText().substring(0, lblBits.getText().length() - 5)) >= Functions
					.mejora4Price(mejora4)) {
				lblBits.setText(Long.parseLong(lblBits.getText().substring(0, lblBits.getText().length() - 5)) - Functions.mejora4Price(mejora4) + " bits");
				mejora4++;
				lblBitsPS.setText(String.valueOf(Long.parseLong(lblBitsPS.getText().substring(0, lblBitsPS.getText().length() - 9))
						+ Functions.mejora4(mejora4)) + " bits P/S");
				lblCantidadM4.setText(String.valueOf(mejora4));
				lblCostoM4.setText(String.valueOf(Functions.mejora4Price(mejora4)) + " bits");
				Functions.actualizarDatos();
				Functions.guardarDatosLocal();
			}
		}
		if (accion == btnEst) {
			if (!panelActivoOpc) {

				if (!panelActivoEst) {
					Info.panel.setVisible(true);
					panelActivoEst = true;
				}

				else if (panelActivoEst) {
					Info.panel.setVisible(false);
					panelActivoEst = false;
				}
			}
		}

		if (accion == btnOpc) {
			if (!panelActivoEst) {
				if (!panelActivoOpc) {
					Configuracion.panel.setVisible(true);
					panelActivoOpc = true;
				} else if (panelActivoOpc) {
					Configuracion.panel.setVisible(false);
					panelActivoOpc = false;
				}
			}
		}

		if (accion == btnBSoD) {
			// Codigo para reiniciar en el caso de un BSoD (Pone todos los datos excepto el
			// BSoD a 0)
			if (Long.parseLong(Juego.lblBits.getText().substring(0, Juego.lblBits.getText().length() - 5)) > Long.parseLong(lblBSoD_Precio.getText().substring(0, lblBSoD_Precio.getText().length() - 5))) {
				bonuses[0].setVisible(false);
				bonuses[1].setVisible(false);
				bonuses[2].setVisible(false);
				contadorTimestop = 0;
				contadorBuffer = 0;
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

				if (Menu.sesion) {
					try {
						conn = DriverManager.getConnection("jdbc:mysql://hl1235.dinaserver.com/CPUClicker", "ibangames", "aW=112jWdKlHD013a.O");

						lblBSoD_Cant.setText(String.valueOf(BSoD) + "  BSoDs");
						PreparedStatement stmtBSoD = conn.prepareStatement("UPDATE estadisticas SET BSoD = " + BSoD
								+ "  WHERE idUsuario = " + "'" + Functions.idUsuario() + "';");
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

					} catch (SQLException e1) {

						e1.printStackTrace();
					}
					if(BSoD > 0) {
						BSoDPrice = BSoDPrice * (90 ^ BSoD);
					}
					else {
						BSoDPrice = 8000000;
					}
					lblBSoD_Precio.setText(String.valueOf(BSoDPrice) + " bits");
					Functions.actualizarDatos();
					Functions.guardarDatosLocal();
					estadoBonus = false;
				}
			}
		}
		if (accion == bonuses[0]) { // Al clicar el bonus 1, hará esto
			bonuses[0].setVisible(false);
			lblTimeStop.setVisible(true);
			String filePath = "timestop.wav";
			Functions.playMusic(filePath);
			contadorTimestop = 15;
			bitsPCoriginal = bitsPC;
			btnMejora1.setEnabled(false);

		}
		if (accion == bonuses[1]) { // Al clicar el bonus 2, hará esto
			bonuses[1].setVisible(false);
			String filePath = "bitsInstantaneos.wav";
			Functions.playMusic(filePath);
			lblBits.setText((Long.parseLong(Juego.lblBits.getText().substring(0, Juego.lblBits.getText().length() - 5)) + Long.parseLong(lblBitsPS.getText().substring(0, lblBitsPS.getText().length() - 9)) * 300)  + " bits");
			lblWafer.setText("+" + String.valueOf(Long.parseLong(lblBitsPS.getText().substring(0, lblBitsPS.getText().length() - 9)) * 300) + " bits!!");
			contadorWafer = 0;
			lblWafer.setVisible(true);
			estadoBonus = false;
		}
		if (accion == bonuses[2]) { // Al clicar el bonus 3, hará esto
			bonuses[2].setVisible(false);

			bitsPSoriginal = Long.parseLong(lblBitsPS.getText().substring(0, lblBitsPS.getText().length() - 9));
			lblBitsPS.setText(Long.parseLong(lblBitsPS.getText().substring(0, lblBitsPS.getText().length() - 9)) * 42 + " bits P/S") ;
			contadorBuffer = 60;
			lblBuffer.setVisible(true);
			lblBitsPS.setText(lblBitsPS.getText().substring(0, lblBitsPS.getText().length() - 9) + " bits P/S");
			btnBSoD.setEnabled(false);
			btnMejora1.setEnabled(false);
			btnMejora2.setEnabled(false);
			btnMejora3.setEnabled(false);
			btnMejora4.setEnabled(false);
		}
		if (accion == btnSalir) {
			if (contadorBuffer > 0) {
				lblBitsPS.setText(String.valueOf(bitsPSoriginal) + " bits P/S") ;
			}
			if(contadorTimestop > 0) {
				contadorTimestop = 0;
			}

			Functions.actualizarDatos();
			Functions.guardarDatosLocal();
			if (Menu.sesion) {
				Functions.guardado();
			}
			Info.panel.removeAll();
			Configuracion.panel.removeAll();
			info.dispose();
			config.dispose();
			aplicacion.mostrarMenu();
			
			
		}

	}

	@Override
	public void run() {

		while (abierto) { // Bucle para ir aumentando los bits

			Functions.pasarTiempo();

			bonus = (int) Math.floor(Math.random() * 60);
			// Condiciones para los bonuses
			System.out.println(estadoBonus);
			if(bitsPS > 1000) {
				if (estadoBonus == false) {
					if (bonus == 1) {
						estadoBonus = true;
						bonuses[0].setSize(Functions.posicionRandomEnRango(50, 80),
								Functions.posicionRandomEnRango(50, 80));
						bonuses[0].setLocation(Functions.posicionRandomEnRango(10, 400),
								Functions.posicionRandomEnRango(20, 740));
						bonuses[0].setVisible(true);

					} else if (bonus == 2) {
						estadoBonus = true;
						bonuses[1].setSize(Functions.posicionRandomEnRango(50, 80),
								Functions.posicionRandomEnRango(50, 80));
						bonuses[1].setLocation(Functions.posicionRandomEnRango(10, 400),
								Functions.posicionRandomEnRango(20, 740));
						bonuses[1].setVisible(true);

					} else if (bonus == 3) {
						estadoBonus = true;
						bonuses[2].setSize(Functions.posicionRandomEnRango(50, 100),
								Functions.posicionRandomEnRango(50, 100));
						bonuses[2].setLocation(Functions.posicionRandomEnRango(10, 400),
								Functions.posicionRandomEnRango(20, 740));
						bonuses[2].setVisible(true);
					}
				}
			}

			while (contadorTimestop > 0) {
				try {
					Thread.sleep(100);
					contadorTimestop -= 0.1;
					lblTimeStop.setText(String.valueOf(Info.df.format(contadorTimestop)) + " s");
					lblBits.setText(Long.parseLong(Juego.lblBits.getText().substring(0, Juego.lblBits.getText().length() - 5)) + " bits");
				} catch (InterruptedException e2) {
					e2.printStackTrace();
				}
				if (contadorTimestop <= 0) {
					lblTimeStop.setVisible(false);
					timestopMultiplier = 0;
					String resumir = "timeresume.wav";
					Functions.playMusic(resumir);
					estadoBonus = false;
					btnMejora1.setEnabled(true);
				}
			}
			while (contadorWafer < 1) {
				Functions.pasarTiempo();
				contadorWafer += 0.1;
			}
			while (contadorBuffer > 0) {

				try {
					Thread.sleep(100);
					tiempo += 0.1;

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (Juego.bits > 10) {
					Juego.lblBits.setText(String.valueOf(Long.parseLong(Juego.lblBits.getText().substring(0, Juego.lblBits.getText().length() - 5)) +
							(long) Math.floor(Long.parseLong(Juego.lblBitsPS.getText().substring(0, Juego.lblBitsPS.getText().length() - 9))) / 10) + " bits");
				} else {
					Juego.lblBits.setText(String.valueOf(Long.parseLong(Juego.lblBits.getText().substring(0, Juego.lblBits.getText().length() - 5)) + 
							Long.parseLong(Juego.lblBitsPS.getText().substring(0, Juego.lblBitsPS.getText().length() - 9))) + " bits");
				}
				lblBuffer.setText(String.valueOf((int) contadorBuffer) + "s");
				contadorBuffer -= 0.1;
				if (contadorBuffer <= 0.1) {
					lblBitsPS.setText(String.valueOf(bitsPSoriginal) + " bits P/S");
					estadoBonus = false;
					btnMejora1.setEnabled(true);
					btnMejora2.setEnabled(true);
					btnMejora3.setEnabled(true);
					btnMejora4.setEnabled(true);
					btnBSoD.setEnabled(true);
				}
			}
			
			lblWafer.setVisible(false);
			lblBuffer.setVisible(false);
			
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

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
			timer.restart();
			frases.setText(frasesTexto[(int) Math.floor(Math.random() * 7)]);
			timerAnimacion = new Timer(100, new ActionListener() {
				int x = 0;

				public void actionPerformed(ActionEvent e) {
					frases.setForeground(new Color(88, 184, 4, x++));
					if (x == 255) {
						timer.stop();
					}
				}

			});

			timer.start();
		}

		if (e.getSource() == btnCPU) {
			clicks++;
		}

		if (e.getSource() == lblArquitectura && e.getClickCount() == 2) {
			String nombreArquitectura = JOptionPane.showInputDialog("Elige el nombre para la arquitectura");
			lblArquitectura.setText(nombreArquitectura);
			arquitectura = nombreArquitectura;
			Functions.guardarDatosLocal();
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}



}