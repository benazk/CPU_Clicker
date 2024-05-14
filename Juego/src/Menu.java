import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;
import javax.swing.*;
import java.io.*;


public class Menu extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JButton btnJugar, btnScoreBoard, btnIniciarSesion, btnCerrarSesion;
	JLabel lblNombre;

	public static String usuario = null;

	public static boolean sesion = false;

	static Menu play;
	
	private Aplicacion aplicacion;
	
	Menu(Aplicacion aplicacion) {
		
		this.aplicacion = aplicacion;
		File file = new File("sesion.txt");
		BufferedReader reader;
	    try {
	    	reader = new BufferedReader(new FileReader(file));
	    	usuario = reader.readLine();
	    	sesion = Boolean.parseBoolean(reader.readLine());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    
	    
		setLayout(new GridLayout(5, 1));

		setVisible(true);
		setSize(1366, 768);

		setBackground(new Color(0, 0, 0));

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
		
		btnCerrarSesion = new JButton("Cerrar Sesión");
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
		if(sesion) {
			btnCerrarSesion.setEnabled(true);
		}
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		JButton elegido = (JButton) e.getSource();
		if (elegido == btnJugar) {
				aplicacion.mostrarJuego();
				Juego.abierto = true;
		}
		if(elegido == btnCerrarSesion) {
			sesion = false;
			usuario = null;
			Functions.mantenerSesiónLocal();
			btnIniciarSesion.setEnabled(true);
			btnCerrarSesion.setEnabled(false);
		}
		if (elegido == btnIniciarSesion) {
			aplicacion.mostrarSesion();
		}
		
		if (elegido == btnScoreBoard) {
			aplicacion.mostrarLeaderboards();
		}
		

	}


	

}