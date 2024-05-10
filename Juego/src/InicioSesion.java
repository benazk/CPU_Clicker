import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.EventListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;

@SuppressWarnings("serial")
public class InicioSesion extends JFrame implements  EventListener, ActionListener, Runnable {
	
	JLabel lblTitulo, lblUsuario, lblContraseña;
	JTextField txtUsuario;
	JButton btnConfirmar;
	JPasswordField pswContraseña;
	
	
	
	InicioSesion(){
		
		Container frame = getContentPane();
		
		LinkedList<Object> a=new LinkedList<Object>();
		a.add(0.3);
		a.add(0.3);
		a.add(new ColorUIResource(0,0,0));
		a.add(new ColorUIResource(60, 60, 60));
		a.add(new ColorUIResource(100, 100, 100));
		
		UIManager.put("Button.gradient",a);
		
		setTitle("Inicio de sesión");

		setSize(300, 400);

	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    setLayout(new GridLayout(6,1));
	    
	    setUndecorated(true);
	    
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);

		frame.setBackground(new Color(0, 0, 0));
		
	    lblTitulo = new JLabel("Inicio de Sesión");
	    lblTitulo.setFont(new Font("Arial", Font.PLAIN, 42));
	    lblTitulo.setForeground(new Color(0, 255, 0));
	    add(lblTitulo);
	    
	    lblUsuario = new JLabel("Nombre de usuario:");
	    lblUsuario.setFont(new Font("Arial", Font.PLAIN, 25));
	    lblUsuario.setForeground(new Color(0, 255, 0));
	    add(lblUsuario);
	    
	    txtUsuario = new JTextField();
	    txtUsuario.setFont(new Font("ayuthaya", Font.PLAIN, 30));
	    txtUsuario.setBackground(new Color(0,0,0));
	    txtUsuario.setForeground(new Color(255, 236, 173));
	    txtUsuario.setBorder(null);
	    add(txtUsuario);
	    txtUsuario.addActionListener(this);
	    
	    lblContraseña = new JLabel("Contraseña:");
	    lblContraseña.setFont(new Font("Arial", Font.PLAIN, 25));
	    lblContraseña.setForeground(new Color(0, 255, 0));
	    add(lblContraseña);
	    
	    pswContraseña = new JPasswordField();
	    pswContraseña.setFont(new Font("ayuthaya", Font.PLAIN, 30));
	    pswContraseña.setBackground(new Color(0,0,0));
	    pswContraseña.setForeground(new Color(255, 236, 173));
	    pswContraseña.setBorder(null);
	    add(pswContraseña);
	    pswContraseña.addActionListener(this);
	    
	    btnConfirmar = new JButton("Iniciar Sesión");
	    add(btnConfirmar);
	    btnConfirmar.addActionListener(this);
	    btnConfirmar.setForeground(Juego.color);
	    btnConfirmar.setBorder(new LineBorder(new Color(0,255,0)));
	}
	
	@Override
	public void run() {
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton seleccion = (JButton) e.getSource();
		String UserBBDD = "";
		String User = txtUsuario.getText();
		String EncryptpasswdBBDD = "";
		String Encryptpasswd = Functions.encriptacion(new String(pswContraseña.getPassword()));
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpuclicker", "root", "");

			Statement stmt = conn.createStatement();
			if (seleccion == btnConfirmar) {
				ResultSet rs = stmt.executeQuery("SELECT nombreUsuario, contraseña FROM usuario WHERE nombreUsuario = '" + txtUsuario.getText() + "'");
				if(rs.next()) {
					EncryptpasswdBBDD = rs.getString("contraseña");
					UserBBDD = rs.getString("nombreUsuario");
				}
				if(EncryptpasswdBBDD.equals(Encryptpasswd) && UserBBDD.equals(User)) {
					Menu.sesion = true;
					Menu.usuario = User;
					JOptionPane.showMessageDialog(seleccion, "Sesión iniciada correctamente");
					Menu.btnIniciarSesion.setEnabled(false);
					Menu.btnCerrarSesion.setEnabled(true);
					Functions.mantenerSesiónLocal();
					System.out.println("sesion: " + Menu.sesion + "; usuario: " + Menu.usuario);
					
					this.dispose();
				}
				else{
					JOptionPane.showMessageDialog(seleccion, "Usuario y/o contraseña incorrectos");
				}
				
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
	
	}
	
	public static void main(String[]args) {
		Functions.cargarDrivers();
		InicioSesion sesion = new InicioSesion();
		sesion.setVisible(true);
		sesion.setResizable(false);
	}

	

}

