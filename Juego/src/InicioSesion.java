import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InicioSesion extends JFrame implements ActionListener, KeyListener, MouseListener {
	
	JLabel lblSesion, lblUsuario, lblContrasena;
	JTextField txtUsuario, txtContrasena;
	JButton btnIniciar;
	
	
	InicioSesion(){
		setLayout(null);	
		setTitle("Menú");
		 
	    setVisible(true);
        setSize(500,500);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //Label Sesion
        lblSesion = new JLabel("Iniciar Sesión");		 		
		add(lblSesion);	 	
		lblSesion.setLocation(50,0);
		lblSesion.setSize(150,50);
		 		 		 
		lblSesion.setForeground(new Color(0,0,0));
		lblSesion.setFont(new Font("Arial",Font.BOLD, 18));	
		
		 //Label Usuario
        lblUsuario = new JLabel("Usuario o correo electrónico");		 		
		add(lblUsuario);	 	
		lblUsuario.setLocation(50,100);
		lblUsuario.setSize(250,50);
		 		 		 
		lblUsuario.setForeground(new Color(0,0,0));
		lblUsuario.setFont(new Font("Arial",Font.BOLD, 18));	
		//JText Usuario
		txtUsuario = new JTextField();		 		
		add(txtUsuario);	 	
		txtUsuario.setLocation(0,150);
		txtUsuario.setSize(300,50);
		 		 		 
		txtUsuario.setForeground(new Color(0,0,0));
		txtUsuario.setFont(new Font("Arial",Font.BOLD, 18));
		
		//Label Contrasena
        lblContrasena = new JLabel("Contraseña");		 		
		add(lblContrasena);	 	
		lblContrasena.setLocation(50,250);
		lblContrasena.setSize(150,50);
		 		 		 
		lblContrasena.setForeground(new Color(0,0,0));
		lblContrasena.setFont(new Font("Arial",Font.BOLD, 18));	
		//JText Contrasena
		txtContrasena = new JTextField();		 		
		add(txtContrasena);	 	
		txtContrasena.setLocation(0,300);
		txtContrasena.setSize(300,50);
				 		 		 
		txtContrasena.setForeground(new Color(0,0,0));
		txtContrasena.setFont(new Font("Arial",Font.BOLD, 18));
		
		
		//Boton Crear Cuenta
		btnIniciar = new JButton("Inicar Sesión");	
		add(btnIniciar);	 	
		btnIniciar.setLocation(10,380);
		btnIniciar.setSize(200,50);
		 		 		 
		btnIniciar.addActionListener(this);
		 	
		btnIniciar.setForeground(new Color(0,0,0));
		btnIniciar.setFont(new Font("Arial",Font.BOLD, 20));
		btnIniciar.setBackground(new Color(102, 235, 94));	
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		InicioSesion inicioSesion = new InicioSesion();
		inicioSesion.setVisible(true);

	}

}
