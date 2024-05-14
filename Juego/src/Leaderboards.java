import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicScrollBarUI;


public class Leaderboards extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton btnAtras;
	JScrollPane sp;
	JLabel lblTitulo;
	static String datos[][] = new String[25][4];
	String columnas[] = { "Posición", "Nombre de usuario", "BSoD" , "Victoria"};
	JTable tblDatos;
	static Connection conn;
	private Aplicacion aplicacion;
	JPanel leader;
	
	Leaderboards(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
		leader = new JPanel();
		Functions.cargarDrivers();
		try {
			int pos = 0;
			conn = DriverManager.getConnection("jdbc:mysql://hl1235.dinaserver.com/CPUClicker", "ibangames", "aW=112jWdKlHD013a.O");
			PreparedStatement stmtLeaderboards = conn.prepareStatement("SELECT estadisticas.BSoD, estadisticas.Victorias, usuario.nombreUsuario FROM estadisticas INNER JOIN usuario ON usuario.idUsuario = estadisticas.idUsuario ORDER BY BSoD LIMIT 25;");
			ResultSet rsLeaderboards = stmtLeaderboards.executeQuery();
			while(rsLeaderboards.next()) {
				datos[pos][0] = String.valueOf(pos + 1);
				datos[pos][1] = rsLeaderboards.getString("nombreUsuario");
				datos[pos][2] = String.valueOf(rsLeaderboards.getInt("BSoD"));
				datos[pos][3] = String.valueOf(rsLeaderboards.getInt("Victorias"));
				pos++;
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setBackground(new Color(80,80,80));
		setLayout(null);
		


		tblDatos = new JTable(datos, columnas);

		
		tblDatos.setBackground(Color.BLACK);
		tblDatos.setForeground(Color.WHITE);
		tblDatos.setSelectionBackground(Color.GREEN);
		tblDatos.setGridColor(Color.GREEN);
		tblDatos.setShowHorizontalLines(false);
		
		tblDatos.setSelectionForeground(Color.BLACK);
		tblDatos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tblDatos.setRowHeight(50);
		tblDatos.setAutoCreateRowSorter(true);

		tblDatos.setLocation((int) Math.floor(getSize().getWidth() / 3),(int) Math.floor(getSize().getHeight() / 2.5) );
		tblDatos.setSize(50, 50);
		tblDatos.setFont(new Font("Arial", Font.PLAIN, 30));

		sp = new JScrollPane(tblDatos);
		add(sp);
		sp.setRowHeader(null);
		sp.setSize(500,200);
		sp.setForeground(new Color(108, 234, 4));
		sp.setBorder(null);
		sp.setBackground(Color.BLACK);
		sp.getVerticalScrollBar().setBackground(Color.BLACK);
		sp.getVerticalScrollBar().setForeground(new Color(108, 234, 4));
		
		sp.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = new Color(108, 234, 4);
			}
		});
		sp.setBounds((int) Math.floor(getSize().getWidth() / 10), (int) Math.floor(getSize().getHeight() / 10), (int) Math.floor(getSize().getWidth() * 0.8), (int) Math.floor(getSize().getHeight() * 0.8));
		sp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		// btnAtras

		btnAtras = new JButton(new ImageIcon("C:\\Users\\daw\\Desktop\\CPU_Clicker\\images\\atras.png"));
		add(btnAtras);
		btnAtras.setLocation(0, 0);
		btnAtras.setSize(50, 50);
		btnAtras.setOpaque(false);

		btnAtras.addActionListener(this);

		// btnAtras.setForeground(new Color(0,0,0));
		// btnAtras.setFont(new Font("Arial",Font.BOLD, 20));
		btnAtras.setBackground(new Color(100, 0, 100));
		
		tblDatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblDatos.getColumnModel().getColumn(0).setPreferredWidth(sp.getWidth() / 10);
		tblDatos.getColumnModel().getColumn(1).setPreferredWidth((int) ((int)sp.getWidth() / 2.5));
		tblDatos.getColumnModel().getColumn(2).setPreferredWidth(sp.getWidth() / 4 - 8);
		tblDatos.getColumnModel().getColumn(3).setPreferredWidth(sp.getWidth() / 4 - 8);
		leader.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	JButton elegido = (JButton) e.getSource();
		if (elegido == btnAtras) {
			aplicacion.mostrarMenu();
		}
	
	}

}
