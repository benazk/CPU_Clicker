import java.awt.Color;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;


public class InicioSesion extends JPanel implements  EventListener, ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblTitulo, lblUsuario, lblContraseña;
	JTextField txtUsuario;
	JButton btnConfirmar;
	JButton btnAtras;
	JPasswordField pswContraseña;
	private Aplicacion aplicacion;
	
	
	InicioSesion(Aplicacion aplicacion){
		this.aplicacion = aplicacion; 
		Functions.cargarDrivers();
		LinkedList<Object> a=new LinkedList<Object>();
		a.add(0.3);
		a.add(0.3);
		a.add(new ColorUIResource(0,0,0));
		a.add(new ColorUIResource(60, 60, 60));
		a.add(new ColorUIResource(100, 100, 100));
		
		UIManager.put("Button.gradient",a);

		setSize(300, 400);
	    
	    setLayout(new GridLayout(7,1));
		
		setBackground(new Color(0, 0, 0));
		
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
	    
	    btnAtras = new JButton("Atrás");
	    add(btnAtras);
	    btnAtras.addActionListener(this);
	    btnAtras.setForeground(Color.RED);
	    btnAtras.setBorder(new LineBorder(new Color(0,255,0)));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton seleccion = (JButton) e.getSource();
		String UserBBDD = "";
		String User = txtUsuario.getText();
		String EncryptpasswdBBDD = "";
		String Encryptpasswd = Functions.encriptacion(new String(pswContraseña.getPassword()));
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://hl1235.dinaserver.com/CPUClicker", "ibangames", "aW=112jWdKlHD013a.O");

			Statement stmt = conn.createStatement();
			if (seleccion == btnConfirmar) {
				ResultSet rs = stmt.executeQuery("SELECT nombreUsuario, contrasena FROM usuario WHERE nombreUsuario = '" + txtUsuario.getText() + "'");
				if(rs.next()) {
					EncryptpasswdBBDD = rs.getString("contrasena");
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
					aplicacion.mostrarMenu();
				}
				else{
					JOptionPane.showMessageDialog(seleccion, "Usuario y/o contraseña incorrectos");
				}
				
			}
			if (seleccion == btnAtras){
				aplicacion.mostrarMenu();
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
	
	}
	

	

}

