import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Table {
	private static JTextField textFirstName;
	private static JTextField textLastName;
	private static JTextField textCPU;
	private static JTextField textPhone;

	public static void main(String[] args) {
		JTable table = new JTable();
		Object[] columns = {"First Name", "Nickname", "CPU", "Phone Number"};
		DefaultTableModel model = new DefaultTableModel();
		
		JFrame frame = new JFrame();
		frame.setBackground(new Color(255, 255, 255));
		frame.setTitle("Juego");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\daw\\Downloads\\stardew2.jpg"));
		frame.setForeground(new Color(255, 255, 255));
		frame.getContentPane().setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(100,100,470,416);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
		table.setBackground(Color.white);
		table.setForeground(Color.black);
		table.setSelectionBackground(Color.RED);
		table.setGridColor(Color.RED);
		table.setSelectionForeground(Color.white);
		table.setFont(new Font("Tahoma", Font.PLAIN,17));
		table.setRowHeight(30);
		table.setAutoCreateRowSorter(true);
		
		JScrollPane panel = new JScrollPane(table);
		panel.setForeground(Color.RED);
		panel.setBackground(Color.white);
		panel.setBounds(10,10,434,223);
		frame.getContentPane().add(panel);
		
		textFirstName = new JTextField();
		textFirstName.setBounds(70, 259, 122, 20);
		frame.getContentPane().add(textFirstName);
		textFirstName.setColumns(10);
		
		textLastName = new JTextField();
		textLastName.setBounds(70, 310, 122, 20);
		frame.getContentPane().add(textLastName);
		textLastName.setColumns(10);
		
		textCPU = new JTextField();
		textCPU.setBounds(306, 259, 138, 20);
		frame.getContentPane().add(textCPU);
		textCPU.setColumns(10);
		
		textPhone = new JTextField();
		textPhone.setBounds(306, 310, 138, 20);
		frame.getContentPane().add(textPhone);
		textPhone.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setForeground(new Color(255, 255, 255));
		lblFirstName.setBounds(10, 262, 74, 14);
		frame.getContentPane().add(lblFirstName);
		
		JLabel lblCpu = new JLabel("CPU");
		lblCpu.setForeground(Color.WHITE);
		lblCpu.setBounds(258, 262, 74, 14);
		frame.getContentPane().add(lblCpu);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setForeground(Color.WHITE);
		lblLastName.setBounds(10, 313, 74, 14);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setForeground(Color.WHITE);
		lblPhoneNumber.setBounds(202, 313, 94, 14);
		frame.getContentPane().add(lblPhoneNumber);
		
						//ROWS
				Object[] row = new Object[4];
		
		//BTN AÑADIR
		JButton btnAnnadir = new JButton("Añadir");
		btnAnnadir.setToolTipText("");
		btnAnnadir.setBackground(new Color(0, 128, 0));
		btnAnnadir.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		btnAnnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				row[0] = textFirstName.getText();
				row[1] = textLastName.getText();
				row[2] = textCPU.getText();
				row[3] = textPhone.getText();
				
				model.addRow(row);
			}
			});
		btnAnnadir.setBounds(20, 341, 188, 23);
		frame.getContentPane().add(btnAnnadir);
		
		//BTN ELIMINAR
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEliminar.setBounds(246, 341, 175, 23);
		frame.getContentPane().add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int i = table.getSelectedRow();
				if(i>=0) {
					model.removeRow(i);					
				}
				else {
					JOptionPane.showMessageDialog(frame, "Delete Error");
					
				}
			}
		
	});
		
		
		
		frame.revalidate();
		frame.setVisible(true);
		
	}
}
