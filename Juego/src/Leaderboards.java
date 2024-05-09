import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class Leaderboards extends JFrame implements ActionListener {
	JButton btnAtras;
	JScrollPane sp;

	String datos[][] = { { "1", "Sue", "New York" }, { "2", "John", "Los Angeles" }, { "3", "Charlotte", "Roma" },
			{ "1", "Sue", "New York" }, { "2", "John", "Los Angeles" }, { "3", "Charlotte", "Roma" },
			{ "1", "Sue", "New York" }, { "2", "John", "Los Angeles" }, { "3", "Charlotte", "Roma" },
			{ "1", "Sue", "New York" }, { "2", "John", "Los Angeles" }, { "3", "Charlotte", "Roma" },
			{ "1", "Sue", "New York" }, { "2", "John", "Los Angeles" }, { "3", "Charlotte", "Roma" },
			{ "1", "Sue", "New York" }, { "2", "John", "Los Angeles" }, { "2", "John", "Los Angeles" },
			{ "3", "Charlotte", "Roma" }, { "1", "Sue", "New York" }, { "2", "John", "Los Angeles" },
			{ "3", "Charlotte", "Roma" }, { "1", "Sue", "New York" }, { "2", "John", "Los Angeles" },
			{ "3", "Charlotte", "Roma" }, { "1", "Sue", "New York" }, { "2", "John", "Los Angeles" },
			{ "2", "John", "Los Angeles" }, { "2", "John", "Los Angeles" }, { "2", "John", "Los Angeles" },
			{ "3", "Charlotte", "Roma" }, { "4", "Ed", "Brasilia" } };
	String columnas[] = { "Id", "Nombre", "Lugar" };
	JTable tblDatos;

	Leaderboards() {
		Container frame = getContentPane();
		frame.setBackground(new Color(80,80,80));
		setLayout(null);
		setTitle("Menú");

		setVisible(true);
		setSize(1966, 768);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tblDatos = new JTable(datos, columnas);

		tblDatos.setBackground(Color.white);
		tblDatos.setForeground(Color.black);
		tblDatos.setSelectionBackground(Color.RED);
		tblDatos.setGridColor(Color.RED);
		tblDatos.setSelectionForeground(Color.white);
		tblDatos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tblDatos.setRowHeight(30);
		tblDatos.setAutoCreateRowSorter(true);

		tblDatos.setLocation(400, 0);
		tblDatos.setSize(50, 50);
		tblDatos.setForeground(new Color(0, 0, 0));
		tblDatos.setFont(new Font("Arial", Font.BOLD, 15));

		sp = new JScrollPane(tblDatos);
		add(sp);
		// sp.setLocation(400,0);
		// sp.setSize(500,200);
		sp.setForeground(Color.black);
		sp.setBackground(new Color(255, 255, 255));
		sp.getVerticalScrollBar().setBackground(Color.BLACK);
		
		sp.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = new Color(108, 234, 4);
			}
		});
		sp.setBounds(400, 0, 600, 300);
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

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton elegido = (JButton) e.getSource();
		if (elegido == btnAtras) {

			dispose();
			// ventana.setVisible(false);
			// Menu menu = new Menu();
			// menu.setVisible(true);
		}

	}

	public static void main(String[] args) {
		Leaderboards Leaderboards = new Leaderboards();
		Leaderboards.setVisible(true);

	}

}
