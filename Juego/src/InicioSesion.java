import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class InicioSesion extends JFrame implements  EventListener, ActionListener, Runnable {
	
	JLabel lblTitulo, lblUsuario, lblContraseña;
	JTextField txtUsuario;
	JButton btnConfirmar;
	JPasswordField pswContraseña;
	
	InicioSesion(){
		
		setTitle("Inicio de sesión");

	    setSize(500, 400);

	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    setLayout(null);
	    
	    lblTitulo = new JLabel("Inicio de Sesión");
	    lblTitulo.setLocation(150,40);
	    lblTitulo.setSize(200,40);	   
	    lblTitulo.setFont(new Font("Arial", Font.PLAIN, 25));
	    add(lblTitulo);
	    
	    lblUsuario = new JLabel("Nombre de usuario:");
	    lblUsuario.setLocation(80, 80);
	    lblUsuario.setSize(170,40);	    
	    add(lblUsuario);
	    
	    txtUsuario = new JTextField();
	    txtUsuario.setLocation(80,120);
	    txtUsuario.setSize(340,40);
	    add(txtUsuario);
	    txtUsuario.addActionListener(this);
	    
	    lblContraseña = new JLabel("Contraseña:");
	    lblContraseña.setLocation(80, 180);
	    lblContraseña.setSize(170,40);
	    add(lblContraseña);
	    
	    pswContraseña = new JPasswordField();
	    pswContraseña.setLocation(80,220);
	    pswContraseña.setSize(340,40);
	    add(pswContraseña);
	    pswContraseña.addActionListener(this);
	    
	    btnConfirmar = new JButton("Iniciar Sesión");
	    btnConfirmar.setLocation(190,280);
	    btnConfirmar.setSize(120,40);
	    add(btnConfirmar);
	    btnConfirmar.addActionListener(this);
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
				System.out.println(Encryptpasswd);
				System.out.println(EncryptpasswdBBDD);
				ResultSet rs = stmt.executeQuery("SELECT nombreUsuario, contraseña FROM usuario WHERE nombreUsuario = '" + txtUsuario.getText() + "'");
				if(rs.next()) {
					EncryptpasswdBBDD = rs.getString("contraseña");
					UserBBDD = rs.getString("nombreUsuario");
				}
				if(EncryptpasswdBBDD.equals(Encryptpasswd) && UserBBDD.equals(User)) {
					Menu.sesion = true;
					Menu.usuario = User;
					JOptionPane.showMessageDialog(seleccion, "Sesión iniciada correctamente");
					Menu.btnIniciarSesion.setVisible(false);
					Menu.btnCerrarSesion.setVisible(true);
					System.out.println("sesion: " + Menu.sesion + "; usuario: " + Menu.usuario);
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


	public static void main(String[] args) {
		Functions.cargarDrivers();
		InicioSesion sesion = new InicioSesion();
		sesion.setVisible(true);
		sesion.setResizable(false);

	}

}

