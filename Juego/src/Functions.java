import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Functions {
	static Connection conn;

	public static int precioFuncion(int precioBase, int cantMejoras) {
		int multiplier = 0;
		for (int i = 0; i < cantMejoras; i++) {
			if (i % 3 == 0) {
				multiplier += 1;
			}
			precioBase += (int) Math.floor(precioBase / 4) + multiplier;
		}
		return precioBase;
	}

	public static int mejora1Price(int cantMej) {
		int precioBase = 5;
		int precioFinal = precioFuncion(precioBase, cantMej);
		;
		return precioFinal;
	}

	public static int mejora1(int cantMej) {
		int cantFinal = 1;
		for (int i = 0; i <= cantMej; i++) {
			cantFinal += i * (1 + Juego.BSoD);
		}
		return cantFinal;
	}

	public static int mejora2Price(int cantMej) {
		int precioBase = 40;
		int precioFinal = precioFuncion(precioBase, cantMej);
		return precioFinal;

	}

	public static int mejora2(int cantMej) {
		int cantFinal = 1;
		for (int i = 0; i <= cantMej * (1 + Juego.BSoD); i++) {
			cantFinal += i;
		}
		return cantFinal;
	}

	public static int mejora3Price(int cantMej) {
		int precioBase = 300;
		int precioFinal = precioFuncion(precioBase, cantMej);
		return precioFinal;

	}

	public static int mejora3(int cantMej) {
		int cantFinal = 1;
		for (int i = 0; i <= cantMej * (1 + Juego.BSoD); i++) {
			cantFinal += i * 8;
		}
		return cantFinal;
	}

	public static int mejora4Price(int cantMej) {
		int precioBase = 2000;
		int precioFinal = precioFuncion(precioBase, cantMej);
		return precioFinal;

	}

	public static int mejora4(int cantMej) {
		int cantFinal = 1;
		for (int i = 0; i <= cantMej * (1 + Juego.BSoD); i++) {
			cantFinal += i * 50;
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
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpuclicker", "root", "");
			PreparedStatement stmtIdUser = conn.prepareStatement(
					"SELECT idUsuario FROM usuario WHERE nombreUsuario = " + "'" + Menu.usuario + "';");
			ResultSet rsUser = stmtIdUser.executeQuery();
			if (rsUser.next()) {
				idUsuario = rsUser.getInt("idUsuario");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		int bitsBBDD = 0;
		int bitsPSBBDD = 0;
		int clicks = 0;
		int cantidadTicks = 0;
		int cantidadCache = 0;
		int cantidadFPS = 0;
		int cantidadTransistores = 0;
		int BSoD_BBDD = 0;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpuclicker", "root", "");
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
				bitsBBDD = rsEstadisticas.getInt("bitsActuales");
				Juego.bitsPS = rsEstadisticas.getInt("bitsPS");
				BSoD_BBDD = rsEstadisticas.getInt("BSoD");
				bitsPSBBDD = rsEstadisticas.getInt("bitsPS");
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
	}

	public static void guardado() { // Guardar los datos en la base de datos
		actualizarDatos();
		int idUsuario = idUsuario();
		String arquitectura = Juego.arquitectura;
		long bits = (int) Integer.parseInt(Juego.lblBits.getText().substring(0, Juego.lblBits.getText().length() - 5));
		long bitsMax = Juego.bitsMax;
		long bitsPS = (long) Integer
				.parseInt(Juego.lblBitsPS.getText().substring(0, Juego.lblBitsPS.getText().length() - 9));
		int clicks = 0;
		int cantidadTicks = Integer.parseInt(Juego.lblCantidadM1.getText());
		int cantidadCache = Integer.parseInt(Juego.lblCantidadM2.getText());
		int cantidadFPS = Integer.parseInt(Juego.lblCantidadM3.getText());
		int cantidadTransistores = Integer.parseInt(Juego.lblCantidadM4.getText());
		int BSoD = Juego.BSoD;
		double tiempo = Juego.tiempo / 60;
		int sumMejoras = cantidadTicks + cantidadCache + cantidadFPS + cantidadTransistores;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpuclicker", "root", "");
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
			PreparedStatement stmtArq = conn.prepareStatement(
					"UPDATE estadisticas SET nombreArquitectura = " + arquitectura + " WHERE idUsuario = " + idUsuario + ";");
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
				fw.write(String.valueOf(Menu.sesion) + "\n");

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
		Juego.bits = Integer.parseInt(Juego.lblBits.getText().substring(0, Juego.lblBits.getText().length() - 5));
		Juego.bitsPS = Integer.parseInt(Juego.lblBitsPS.getText().substring(0, Juego.lblBitsPS.getText().length() - 9))
				* (Juego.BSoD + 1);
		Juego.bitsPC = Functions.mejora1(Juego.mejora1);
		Juego.mejora1 = Integer.parseInt(Juego.lblCantidadM1.getText());
		Juego.mejora2 = Integer.parseInt(Juego.lblCantidadM2.getText());
		Juego.mejora3 = Integer.parseInt(Juego.lblCantidadM3.getText());
		Juego.mejora4 = Integer.parseInt(Juego.lblCantidadM4.getText());
	}
	public static void pasarTiempo() {
		try {
			Thread.sleep(100);
			Juego.tiempo += 0.1;
			Juego.bits = Integer.parseInt(Juego.lblBits.getText().substring(0,Juego.lblBits.getText().length()-5));
			Juego.bitsPS = Integer.parseInt(Juego.lblBitsPS.getText().substring(0,Juego.lblBitsPS.getText().length()-9)) * (Juego.BSoD + 1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (Juego.bits > 10) {
			Juego.bits += (int) Math.floor(Juego.bitsPS / 10);
		} else {
			Juego.bits += Juego.bitsPS;
		}
		Juego.lblBits.setText(String.valueOf(Juego.bits) + " bits");
		if(Juego.bits > Juego.bitsMax) {
			Juego.bitsMax = Juego.bits;
		}
		
	}
	

}