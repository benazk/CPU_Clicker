import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Menu extends JFrame implements ActionListener {

	public static JButton btnJugar, btnScoreBoard, btnIniciarSesion, btnCerrarSesion;
	JLabel lblNombre;

	public static String usuario = null;

	public static boolean sesion = false;


	Menu() {
		File file = new File("sesion.txt");
		BufferedReader reader;
	    try {
	    	reader = new BufferedReader(new FileReader(file));
	    	usuario = reader.readLine();
	    	sesion = Boolean.parseBoolean(reader.readLine());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setLayout(null);
		setTitle("Menú");
		setLayout(new GridLayout(5, 1));

		setVisible(true);
		setSize(1366, 768);

		Container frame = getContentPane();

		frame.setBackground(new Color(0, 0, 0));

		// Nombre Juego
		lblNombre = new JLabel(
				"<html>░█▀▀░█▀█░█░█░░░█▀▀░█░░░▀█▀░█▀▀░█░█░█▀▀░█▀▄<br>░█░░░█▀▀░█░█░░░█░░░█░░░░█░░█░░░█▀▄░█▀▀░█▀▄<br>░▀▀▀░▀░░░▀▀▀░░░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀▀▀░▀░▀ </html>");
		add(lblNombre);
		lblNombre.setHorizontalAlignment(JLabel.CENTER);
		lblNombre.setSize(250, 90);
		lblNombre.setForeground(new Color(255,255,255));
		lblNombre.setFont(new Font("Arial", Font.BOLD, 10));

		btnJugar = new JButton("Jugar");
		add(btnJugar);
		btnJugar.setBorder(new LineBorder(new Color(0, 255, 0), 4));
		btnJugar.addActionListener(this);
		btnJugar.setForeground(Juego.color);
		btnJugar.setFont(new Font("JetBrainsMono-Thin", Font.TRUETYPE_FONT, 40));
		btnJugar.setBackground(new Color(0, 0, 0));
		

		btnScoreBoard = new JButton("Leaderboards");
		add(btnScoreBoard);
		btnScoreBoard.setBorder(new LineBorder(new Color(0, 255, 0), 4));
		btnScoreBoard.addActionListener(this);
		btnScoreBoard.setForeground(Juego.color);
		btnScoreBoard.setFont(new Font("JetBrainsMono-Light", Font.TRUETYPE_FONT, 30));
		btnScoreBoard.setBackground(new Color(0, 0, 0));
		

		btnIniciarSesion = new JButton("Iniciar sesión");
		add(btnIniciarSesion);
		btnIniciarSesion.setBorder(new LineBorder(new Color(0, 255, 0), 4));
		btnIniciarSesion.addActionListener(this);
		btnIniciarSesion.setForeground(Juego.color);
		btnIniciarSesion.setFont(new Font("JetBrainsMono-Light", Font.TRUETYPE_FONT, 30));
		btnIniciarSesion.setBackground(new Color(0, 0, 0));
		
		
		if (sesion) {
			btnIniciarSesion.setEnabled(false);
		}
		else {
			btnIniciarSesion.setEnabled(true);
		}
		
		btnCerrarSesion = new JButton("Cerrar Sesion");
		add(btnCerrarSesion);
		btnCerrarSesion.setBorder(new LineBorder(new Color(0, 255, 0), 4));
		btnCerrarSesion.addActionListener(this);
		btnCerrarSesion.setForeground(Juego.color);
		btnCerrarSesion.setFont(new Font("JetBrainsMono-Light", Font.TRUETYPE_FONT, 30));
		btnCerrarSesion.setBackground(new Color(0, 0, 0));
		
		if (sesion) {
			btnCerrarSesion.setEnabled(true);
		}
		else {
			btnCerrarSesion.setEnabled(false);
		}
	}
	public void actionPerformed(ActionEvent e) {
		JButton elegido = (JButton) e.getSource();
		if (elegido == btnJugar) {
			Juego juego;
			try {
				juego = new Juego();
				juego.setVisible(true);
				Juego.abierto = true;
				Thread contador = new Thread(juego);
				contador.start();
				
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		else if(elegido == btnCerrarSesion) {
			sesion = false;
			usuario = null;
			Functions.mantenerSesiónLocal();
			btnIniciarSesion.setEnabled(true);
			btnCerrarSesion.setEnabled(false);
		}
		if (elegido == btnIniciarSesion) {
			InicioSesion sesion = new InicioSesion();
			sesion.setVisible(true);
		}
		
		if (elegido == btnScoreBoard) {
			Leaderboards Leaderboards = new Leaderboards();
			Leaderboards.setVisible(true);
		}
		

	}

	public static void main(String[] args) {
		Menu play = new Menu();
		System.out.println(sesion + usuario);
		if(sesion) {
			btnCerrarSesion.setEnabled(true);
		}
		
	    

	}

}