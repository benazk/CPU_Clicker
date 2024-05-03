import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.*;

public class Menu extends JFrame implements ActionListener {

	public static JButton btnJugar, btnScoreBoard, btnIniciarSesion, btnCerrarSesion, btnAtras;
	public static JLabel lblNombre;

	public static boolean sesion = false;
	
	public static String usuario = ""; 
	
	
	Menu() {
		setLayout(null);
		setTitle("Menú");

		setVisible(true);
		setSize(1966, 768);

		// Nombre Juego
		lblNombre = new JLabel("CPU Clicker");
		add(lblNombre);
		lblNombre.setLocation(400, 0);
		lblNombre.setSize(250, 90);

		lblNombre.setForeground(new Color(0, 0, 0));
		lblNombre.setFont(new Font("Arial", Font.BOLD, 40));

//btnJugar
		btnJugar = new JButton("Jugar");
		add(btnJugar);
		btnJugar.setLocation(400, 220);
		btnJugar.setSize(200, 100);

		btnJugar.addActionListener(this);

		btnJugar.setForeground(new Color(0, 0, 0));
		btnJugar.setFont(new Font("Arial", Font.BOLD, 20));
		btnJugar.setBackground(new Color(100, 0, 100));

//btnScoreBoard

		btnScoreBoard = new JButton("Leaderboards");
		add(btnScoreBoard);
		btnScoreBoard.setLocation(400, 420);
		btnScoreBoard.setSize(200, 100);

		btnScoreBoard.addActionListener(this);

		btnScoreBoard.setForeground(new Color(0, 0, 0));
		btnScoreBoard.setFont(new Font("Arial", Font.BOLD, 20));
		btnScoreBoard.setBackground(new Color(0, 230, 230));

//Boton Cerrar Sesion
		btnCerrarSesion = new JButton("CerrarSesion");
		add(btnCerrarSesion);
		btnCerrarSesion.setLocation(1100, 100);
		btnCerrarSesion.setSize(200, 50);

		btnCerrarSesion.addActionListener(this);
		btnCerrarSesion.setVisible(false);
		btnCerrarSesion.setForeground(new Color(0, 0, 0));
		btnCerrarSesion.setFont(new Font("Arial", Font.BOLD, 20));
		btnCerrarSesion.setBackground(new Color(102, 235, 94));

//Boton Iniciar sesion
		btnIniciarSesion = new JButton("Iniciar sesión");
		btnIniciarSesion.setLocation(1100, 100);
		btnIniciarSesion.setSize(200, 50);
		add(btnIniciarSesion);
		btnIniciarSesion.addActionListener(this);

		btnIniciarSesion.setForeground(new Color(0, 0, 0));
		btnIniciarSesion.setFont(new Font("Arial", Font.BOLD, 20));
		btnIniciarSesion.setBackground(new Color(102, 235, 94));
	}

	void playMusic(String musicLoc) {
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

	public void actionPerformed(ActionEvent e) {
		JButton elegido = (JButton) e.getSource();
		if (elegido == btnJugar) {
			Juego juego = new Juego();
			juego.setVisible(true);
			Thread contador = new Thread(juego);
			contador.start();
		}
		// else if(elegido == btnCerrarSesion) {
		// CerrarSesion cuenta = new CerrarSesion();
		// cuenta.setVisible(true);

		else if (elegido == btnIniciarSesion) {
			InicioSesion sesion = new InicioSesion();
			sesion.setVisible(true);
		} else if (elegido == btnScoreBoard) {
			Leaderboards Leaderboards = new Leaderboards();
			Leaderboards.setVisible(true);
		}

	}

	public static void main(String[] args) {
		Menu menu = new Menu();
	}

}
