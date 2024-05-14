import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.swing.JButton;

public class Functions {

	static Connection conn;
	public static long precioFuncion(long precioBase, long cantMejoras) {
		int multiplier = 0;
		for (int i = 0; i < cantMejoras; i++) {
			if (i % 2 == 0) {
				multiplier += 1;
			}
			precioBase += (long) Math.floor(precioBase / 3) + multiplier;
		}
		return precioBase;
	}

	public static long mejora1Price(long cantMej) {
		long precioBase = 10;
		long precioFinal = precioFuncion(precioBase, cantMej);
		;
		return precioFinal;
	}

	public static long mejora1(long cantMej) {
		long cantFinal = 1;
		for (int i = 0; i <= cantMej; i++) {
			cantFinal += i ^ (1 + Juego.BSoD);
		}
		return cantFinal;
	}

	public static long mejora2Price(long cantMej) {
		long precioBase = 40;
		long precioFinal = precioFuncion(precioBase, cantMej);
		return precioFinal;

	}

	public static long mejora2(long cantMej) {
		long cantFinal = 1;
		for (int i = 0; i <= (cantMej ^ (1 + Juego.BSoD)); i++) {
			cantFinal += i;
		}
		return cantFinal;
	}

	public static long mejora3Price(long cantMej) {
		long precioBase = 500;
		long precioFinal = precioFuncion(precioBase, cantMej);
		return precioFinal;

	}

	public static long mejora3(long cantMej) {
		long cantFinal = 1;
		for (int i = 0; i <= (cantMej ^ (1 + Juego.BSoD)); i++) {
			cantFinal += i * 4;
		}
		return cantFinal;
	}

	public static long mejora4Price(long cantMej) {
		long precioBase = 3000;
		long precioFinal = precioFuncion(precioBase, cantMej);
		return precioFinal;

	}

	public static long mejora4(long cantMej) {
		long cantFinal = 1;
		for (int i = 0; i <= (cantMej ^ (1 + Juego.BSoD)); i++) {
			cantFinal += i * 30;
		}
		return cantFinal;
	}

	public static void cargarDrivers() {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}
	}

	public static String encriptacion(String contrasena) {
		String contrasenaEncriptada = "";
		try {
			/* Instancia MessageDigest para MD5 */
			MessageDigest m = MessageDigest.getInstance("MD5");

			/* Añadir bytes de texto de la contraseña para digerir con MD5. */
			m.update(contrasena.getBytes());

			/* Convertir el valor hash a bytes */
			byte[] bytes = m.digest();

			/*
			 * El array de bytes tiene bytes en forma decimal. Conversion a formato
			 * hexadecimal.
			 */
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			/* Completar contraseña hash en formato hexadecimal */
			contrasenaEncriptada = s.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return contrasenaEncriptada;
	}

	public static int idUsuario() {
		int idUsuario = 0;
		try {
			 conn = DriverManager.getConnection("jdbc:mysql://hl1235.dinaserver.com/CPUClicker", "ibangames", "aW=112jWdKlHD013a.O");
			PreparedStatement stmtIdUser = conn.prepareStatement(
					"SELECT idUsuario FROM usuario WHERE nombreUsuario = " + "'" + Menu.usuario + "';");
			ResultSet rsUser = stmtIdUser.executeQuery();
			if (rsUser.next()) {
				idUsuario = rsUser.getInt("idUsuario");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idUsuario;
	}

	public static int posicionRandomEnRango(int min, int max) {
		int random = 0;
		random = (int) Math.floor(Math.random() * (max - min) + min);

		return random;
	}

	public static void playMusic(String musicLoc) {
		try {
			File musicPath = new File(musicLoc);
			if (musicPath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();

			} else {
				System.out.println("Couldn't find Music file");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static void playMusicLoop(String musicLoc) {
		try {
			File musicPath = new File(musicLoc);
			if (musicPath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				clip.loop(2147000000);

			} else {
				System.out.println("Couldn't find Music file");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static void cargado() { // Cargar los datos de la base de datos (si el usuario ha iniciado sesión)
		int idUsuario = idUsuario();
		long bitsBBDD = 0;
		long bitsPSBBDD = 0;
		int clicks = 0;
		int cantidadTicks = 0;
		int cantidadCache = 0;
		int cantidadFPS = 0;
		int cantidadTransistores = 0;
		int BSoD_BBDD = 0;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://hl1235.dinaserver.com/CPUClicker", "ibangames", "aW=112jWdKlHD013a.O");
			PreparedStatement stmtMejoras = conn
					.prepareStatement("SELECT * FROM mejoras WHERE idUsuario = " + idUsuario + ";");
			ResultSet rsMejoras = stmtMejoras.executeQuery();
			if (rsMejoras.next()) {
				cantidadTicks = rsMejoras.getInt("cantidadTicks");
				cantidadCache = rsMejoras.getInt("cantidadCache");
				cantidadFPS = rsMejoras.getInt("cantidadFPS");
				cantidadTransistores = rsMejoras.getInt("cantidadTransistores");
			}
			PreparedStatement stmtEstadisticas = conn
					.prepareStatement("SELECT * FROM estadisticas WHERE idUsuario = " + idUsuario + ";");
			ResultSet rsEstadisticas = stmtEstadisticas.executeQuery();
			if (rsEstadisticas.next()) {
				bitsBBDD = rsEstadisticas.getLong("bitsActuales");
				Juego.bitsPS = rsEstadisticas.getLong("bitsPS");
				BSoD_BBDD = rsEstadisticas.getInt("BSoD");
				bitsPSBBDD = rsEstadisticas.getLong("bitsPS");
				clicks = rsEstadisticas.getInt("clicksHechos");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		Juego.lblBits.setText(String.valueOf(bitsBBDD) + " bits");
		Juego.lblBitsPS.setText(String.valueOf(bitsPSBBDD) + " bits P/S");
		Juego.lblCantidadM1.setText(String.valueOf(cantidadTicks));
		Juego.lblCantidadM2.setText(String.valueOf(cantidadCache));
		Juego.lblCantidadM3.setText(String.valueOf(cantidadFPS));
		Juego.lblCantidadM4.setText(String.valueOf(cantidadTransistores));
		Juego.BSoD = BSoD_BBDD;
		Juego.clicks = clicks;
		actualizarDatos();
	}

	public static void guardado() { // Guardar los datos en la base de datos
		actualizarDatos();
		int idUsuario = idUsuario();
		String arquitectura = Juego.arquitectura;
		long bits = Long.parseLong(Juego.lblBits.getText().substring(0, Juego.lblBits.getText().length() - 5));
		long bitsMax = Juego.bitsMax;
		long bitsPS = Long.parseLong(Juego.lblBitsPS.getText().substring(0, Juego.lblBitsPS.getText().length() - 9));
		int clicks = 0;
		int cantidadTicks = Integer.parseInt(Juego.lblCantidadM1.getText());
		int cantidadCache = Integer.parseInt(Juego.lblCantidadM2.getText());
		int cantidadFPS = Integer.parseInt(Juego.lblCantidadM3.getText());
		int cantidadTransistores = Integer.parseInt(Juego.lblCantidadM4.getText());
		int BSoD = Juego.BSoD;
		double tiempo = Juego.tiempo / 60;
		int sumMejoras = cantidadTicks + cantidadCache + cantidadFPS + cantidadTransistores;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://hl1235.dinaserver.com/CPUClicker", "ibangames", "aW=112jWdKlHD013a.O");
			PreparedStatement stmtMejoras1 = conn.prepareStatement(
					"UPDATE mejoras SET cantidadTicks = " + cantidadTicks + " WHERE idUsuario = " + idUsuario + ";");
			stmtMejoras1.executeUpdate();
			PreparedStatement stmtMejoras2 = conn.prepareStatement(
					"UPDATE mejoras SET cantidadCache = " + cantidadCache + " WHERE idUsuario = " + idUsuario + ";");
			stmtMejoras2.executeUpdate();
			PreparedStatement stmtMejoras3 = conn.prepareStatement(
					"UPDATE mejoras SET cantidadFPS = " + cantidadFPS + " WHERE idUsuario = " + idUsuario + ";");
			stmtMejoras3.executeUpdate();
			PreparedStatement stmtMejoras4 = conn.prepareStatement("UPDATE mejoras SET cantidadTransistores = "
					+ cantidadTransistores + " WHERE idUsuario = " + idUsuario + ";");
			stmtMejoras4.executeUpdate();
			PreparedStatement stmtBits = conn.prepareStatement(
					"UPDATE estadisticas SET bitsActuales = " + bits + " WHERE idUsuario = " + idUsuario + ";");
			stmtBits.executeUpdate();
			PreparedStatement stmtBitsMax = conn.prepareStatement(
					"UPDATE estadisticas SET bitsMaximos = " + bitsMax + " WHERE idUsuario = " + idUsuario + ";");
			stmtBitsMax.executeUpdate();
			PreparedStatement stmtBitsPS = conn.prepareStatement(
					"UPDATE estadisticas SET bitsPS = " + bitsPS + " WHERE idUsuario = " + idUsuario + ";");
			stmtBitsPS.executeUpdate();
			PreparedStatement stmtClicks = conn.prepareStatement(
					"UPDATE estadisticas SET clicksHechos = " + clicks + " WHERE idUsuario = " + idUsuario + ";");
			stmtClicks.executeUpdate();
			PreparedStatement stmtBSoD = conn.prepareStatement(
					"UPDATE estadisticas SET BSoD = " + BSoD + " WHERE idUsuario = " + idUsuario + ";");
			stmtBSoD.executeUpdate();
			PreparedStatement stmtMins = conn.prepareStatement(
					"UPDATE estadisticas SET minutosJugados = " + tiempo + " WHERE idUsuario = " + idUsuario + ";");
			stmtMins.executeUpdate();
			PreparedStatement stmtArq = conn.prepareStatement("UPDATE estadisticas SET nombreArquitectura = "
					+ arquitectura + " WHERE idUsuario = " + idUsuario + ";");
			stmtArq.executeUpdate();
			PreparedStatement stmtSum = conn.prepareStatement(
					"UPDATE mejoras SET sumaMejoras = " + sumMejoras + " WHERE idUsuario = " + idUsuario + ";");
			stmtSum.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void mantenerSesiónLocal() { // Esto mantiene la sesión abierta aun cerrando el juego en su totalidad
		File file = new File("sesion.txt");
		FileWriter fw;
		try {
			fw = new FileWriter(file);
			try {

				fw.write(String.valueOf(Menu.usuario) + "\n");
				fw.write(String.valueOf(Menu.sesion));

			} catch (Exception e) {

			} finally {
				fw.flush();
				fw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void guardarDatosLocal() {
		actualizarDatos();
		File file = new File("datos.txt");
		FileWriter fw;
		try {
			fw = new FileWriter(file);
			try {
				fw.write(String.valueOf(Juego.bits) + "\n");
				fw.write(String.valueOf(Juego.bitsMax) + "\n");
				fw.write(String.valueOf(Juego.bitsPS) + "\n");
				fw.write(String.valueOf(Juego.bitsPC) + "\n");
				fw.write(String.valueOf(Juego.clicks) + "\n");
				fw.write(String.valueOf(Juego.BSoD) + "\n");
				fw.write(String.valueOf(Juego.mejora1) + "\n");
				fw.write(String.valueOf(Juego.mejora2) + "\n");
				fw.write(String.valueOf(Juego.mejora3) + "\n");
				fw.write(String.valueOf(Juego.mejora4) + "\n");
				fw.write(String.valueOf(Juego.tiempo) + "\n");
				fw.write(String.valueOf(Juego.arquitectura));

			} catch (Exception e) {

			} finally {
				fw.flush();
				fw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void cargarDatosLocal() throws NumberFormatException, IOException {
		File file = new File("datos.txt");
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			Juego.bits = Long.parseLong(reader.readLine());
			Juego.bitsMax = Long.parseLong(reader.readLine());
			Juego.bitsPS = Integer.parseInt(reader.readLine());
			Juego.bitsPC = Integer.parseInt(reader.readLine());
			Juego.clicks = Integer.parseInt(reader.readLine());
			Juego.BSoD = Integer.parseInt(reader.readLine());
			Juego.mejora1 = Integer.parseInt(reader.readLine());
			Juego.mejora2 = Integer.parseInt(reader.readLine());
			Juego.mejora3 = Integer.parseInt(reader.readLine());
			Juego.mejora4 = Integer.parseInt(reader.readLine());
			Juego.tiempo = Double.parseDouble(reader.readLine());
			Juego.arquitectura = reader.readLine();
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Juego.lblBits.setText(Juego.bits + " bits");
		Juego.lblBitsPS.setText(Juego.bitsPS + " bits P/S");
		Juego.lblCantidadM1.setText(String.valueOf(Juego.mejora1));
		Juego.lblCantidadM2.setText(String.valueOf(Juego.mejora2));
		Juego.lblCantidadM3.setText(String.valueOf(Juego.mejora3));
		Juego.lblCantidadM4.setText(String.valueOf(Juego.mejora4));
		Juego.lblCostoM1.setText(String.valueOf(Functions.mejora1Price(Juego.mejora1)) + " bits");
		Juego.lblCostoM2.setText(String.valueOf(Functions.mejora2Price(Juego.mejora2)) + " bits");
		Juego.lblCostoM3.setText(String.valueOf(Functions.mejora3Price(Juego.mejora3)) + " bits");
		Juego.lblCostoM4.setText(String.valueOf(Functions.mejora4Price(Juego.mejora4)) + " bits");
		Juego.lblArquitectura.setText(Juego.arquitectura);
	}

	public static void actualizarDatos() {
		Juego.bits = Long.parseLong(Juego.lblBits.getText().substring(0, Juego.lblBits.getText().length() - 5));
		Juego.bitsPS = Long.parseLong(Juego.lblBitsPS.getText().substring(0, Juego.lblBitsPS.getText().length() - 9));
		Juego.bitsPC = (int) Functions.mejora1(Juego.mejora1);
		Juego.mejora1 = Integer.parseInt(Juego.lblCantidadM1.getText());
		Juego.mejora2 = Integer.parseInt(Juego.lblCantidadM2.getText());
		Juego.mejora3 = Integer.parseInt(Juego.lblCantidadM3.getText());
		Juego.mejora4 = Integer.parseInt(Juego.lblCantidadM4.getText());
	}

	public static void pasarTiempo() {
		try {
			System.out.println(Juego.bitsPS);
			Thread.sleep(100);
			Juego.tiempo += 0.1;
			Juego.bits = Long.parseLong(Juego.lblBits.getText().substring(0, Juego.lblBits.getText().length() - 5));
			Juego.bitsPS = Long.parseLong(
					Juego.lblBitsPS.getText().substring(0, Juego.lblBitsPS.getText().length() - 9));
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
		if (Juego.bits > Juego.bitsMax) {
			Juego.bitsMax = Juego.bits;
		}
	
		if(Long.parseLong(Juego.lblBits.getText().substring(0, Juego.lblBits.getText().length() - 5)) < 0){
			Juego.lblBits.setText(0 + " bits");
		}
		

	}

	private static LinkedList<Line> altavoces = new LinkedList<Line>();

	public final static void buscarAltavoces() {
		Mixer.Info[] mixers = AudioSystem.getMixerInfo();

		for (Mixer.Info mixerInfo : mixers) {
			if (!mixerInfo.getName().equals("Java Sound Audio Engine"))
				continue;

			Mixer mixer = AudioSystem.getMixer(mixerInfo);
			Line.Info[] lines = mixer.getSourceLineInfo();

			for (Line.Info info : lines) {

				try {
					Line line = mixer.getLine(info);
					altavoces.add(line);

				} catch (LineUnavailableException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException iaEx) {
				}
			}
		}
	}

	static {
		buscarAltavoces();
	}

	public static void cambiarVolumen(float level) {
		System.out.println("setting volume to " + level);
		for (Line line : altavoces) {
			try {
				line.open();
				FloatControl control = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
				control.setValue(limitar(control, level));
			} catch (LineUnavailableException e) {
				continue;
			} catch (java.lang.IllegalArgumentException e) {
				continue;
			}

		}
	}

	private static float limitar(FloatControl control, float level) 
	{
		return Math.min(control.getMaximum(), Math.max(control.getMinimum(), level));
	}
	
	public static void pagWeb(JButton btnPagWeb, final String url, String text) {
        btnPagWeb.setText("<html><a href=\"\" style= 'text-decoration= none'>"+text+"</a></html>");
        btnPagWeb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPagWeb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    try {
                            Desktop.getDesktop().browse(new URI(url));
                    } catch (URISyntaxException | IOException ex) {
                            //It looks like there's a problem
                    }
            }
        });
    }
	
	

}