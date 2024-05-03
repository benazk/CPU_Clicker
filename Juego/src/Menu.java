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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Menu extends JFrame implements ActionListener {

	public static JButton btnJugar, btnScoreBoard, btnIniciarSesion, btnCerrarSesion, btnAtras;
	JLabel lblNombre;
	
	public static String usuario ="";
	
	public static boolean sesion = false;
	
	Menu() {
		
		
		try {
		    //create the font to use. Specify the size!
			System.out.println("hola");
		    Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("font\\JetBrainsMono-Light.ttf")).deriveFont(20f);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    //register the font
		    ge.registerFont(customFont);
		} catch (IOException e) {
		    e.printStackTrace();
		} catch(FontFormatException e) {
		    e.printStackTrace();
		
	}
		
		
		setLayout(null);
		setTitle("Menú");
		setLayout(new GridLayout(6, 1));

		setVisible(true);
		setSize(1966, 768);

		Container frame = getContentPane();

		frame.setBackground(new Color(0, 0, 0));

		// Nombre Juego
		lblNombre = new JLabel(
				"<html>░█▀▀░█▀█░█░█░░░█▀▀░█░░░▀█▀░█▀▀░█░█░█▀▀░█▀▄<br>░█░░░█▀▀░█░█░░░█░░░█░░░░█░░█░░░█▀▄░█▀▀░█▀▄<br>░▀▀▀░▀░░░▀▀▀░░░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀▀▀░▀░▀ </html>");
		add(lblNombre);
		lblNombre.setHorizontalAlignment(JLabel.CENTER);

//lblNombre.setLocation(400,0);
		lblNombre.setSize(250, 90);

		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setFont(new Font("Arial", Font.BOLD, 10));

//btnJugar
		btnJugar = new JButton("Jugar");
		add(btnJugar);
		// btnJugar.setLocation(400,220);
		btnJugar.setSize(200, 100);

		btnJugar.addActionListener(this);

		btnJugar.setForeground(new Color(255, 255, 255));
		btnJugar.setFont(new Font("JetBrainsMono-Thin", Font.TRUETYPE_FONT, 30));
		btnJugar.setBackground(new Color(30, 30, 30));

//btnScoreBoard

		btnScoreBoard = new JButton("Leaderboards");
		add(btnScoreBoard);
//btnScoreBoard.setLocation(400,420);
		btnScoreBoard.setSize(200, 100);

		btnScoreBoard.addActionListener(this);

		btnScoreBoard.setForeground(new Color(255, 255, 255));
		btnScoreBoard.setFont(new Font("JetBrainsMono-Light", Font.TRUETYPE_FONT, 30));
		btnScoreBoard.setBackground(new Color(30, 30, 30));

//Boton Iniciar sesion
		btnIniciarSesion = new JButton("Iniciar sesión");
		add(btnIniciarSesion);
//btnIniciarSesion.setLocation(1100,100);
		btnIniciarSesion.setSize(200, 50);

		btnIniciarSesion.addActionListener(this);

		btnIniciarSesion.setForeground(new Color(255, 255, 255));
		btnIniciarSesion.setFont(new Font("JetBrainsMono-Light", Font.TRUETYPE_FONT, 30));
		btnIniciarSesion.setBackground(new Color(30, 30, 30));

//Boton Cerrar Sesion
		btnCerrarSesion = new JButton("Cerrar Sesion");
		add(btnCerrarSesion);
//btnCerrarSesion.setLocation(1100,100);
		btnCerrarSesion.setSize(200, 50);

		btnCerrarSesion.addActionListener(this);
		btnCerrarSesion.setForeground(new Color(255, 255, 255));
		btnCerrarSesion.setFont(new Font("JetBrainsMono-Light", Font.TRUETYPE_FONT, 30));
		btnCerrarSesion.setBackground(new Color(30, 30, 30));

//Boton Atras
		btnAtras = new JButton("Atras");
		add(btnAtras);
//btnAtras.setLocation(1100,100);
		btnAtras.setSize(200, 50);

		btnAtras.addActionListener(this);
		btnAtras.setForeground(new Color(255, 255, 255));
		btnAtras.setFont(new Font("JetBrainsMono-Light", Font.TRUETYPE_FONT, 30));
		btnAtras.setBackground(new Color(30, 30, 30));
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


		Menu play = new Menu();

	}

}