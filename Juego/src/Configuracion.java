import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;


public class Configuracion extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JScrollPane scroll = new JScrollPane(panel);

	public static JPanel panel = new JPanel();

	public static JSlider slider = new JSlider();

	public static JButton btnCerrar, btnBorrarGuardado, btnPagWeb;

	Configuracion() {

		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		add(panel);

		panel.setBackground(new Color(0, 0, 0));

		setSize(400, 500);

		setUndecorated(true);

		getRootPane().setWindowDecorationStyle(JRootPane.NONE);

		panel.setLayout(new GridLayout(4, 1));

		add(panel);

		panel.setBounds(40, 20, 350, 450);

		slider.addChangeListener(null);
		slider.setForeground(Juego.color);
		slider.setBackground(new Color(0, 0, 0));
		panel.add(slider);

		btnCerrar = new JButton("Cerrar El Juego");
		panel.add(btnCerrar);
		btnCerrar.setBorder(new LineBorder(new Color(0, 255, 0), 4));
		btnCerrar.addActionListener(this);
		btnCerrar.setForeground(Juego.color);
		btnCerrar.setFont(new Font("JetBrainsMono-Thin", Font.TRUETYPE_FONT, 40));
		btnCerrar.setBackground(new Color(0, 0, 0));

		btnPagWeb = new JButton("");
		panel.add(btnPagWeb);
		btnPagWeb.setBorder(new LineBorder(new Color(0, 255, 0), 4));
		btnPagWeb.addActionListener(this);
		btnPagWeb.setForeground(Juego.color);
		btnPagWeb.setFont(new Font("JetBrainsMono-Thin", Font.TRUETYPE_FONT, 30));
		btnPagWeb.setBackground(new Color(0, 0, 0));
		Functions.pagWeb(btnPagWeb, "localhost/index.php", "Nuestra página web");
		setVisible(true);
		
		btnBorrarGuardado = new JButton("<html>Borrar el archivo<br>de guardado</html>");
		panel.add(btnBorrarGuardado);
		btnBorrarGuardado.setBorder(new LineBorder(new Color(255, 41, 25), 4));
		btnBorrarGuardado.addActionListener(this);
		btnBorrarGuardado.setForeground(new Color(255, 41, 25));
		btnBorrarGuardado.setFont(new Font("JetBrainsMono-Thin", Font.TRUETYPE_FONT, 35));
		btnBorrarGuardado.setBackground(new Color(0, 0, 0));

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCerrar) {

		}
		if (e.getSource() == btnPagWeb) {
			if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    URI uri = new URI("http://localhost/index.php");
                    desktop.browse(uri);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (URISyntaxException ex) {
                    ex.printStackTrace();
                }
			}
		}
		if(e.getSource() == btnCerrar) {
			JOptionPane.showConfirmDialog(btnCerrar, e);
			Runtime.getRuntime().exit(1);
		}
		
		if(e.getSource() == btnBorrarGuardado) {
			Functions.actualizarDatos();
			File file = new File("datos.txt");
			FileWriter fw;
			try {
				fw = new FileWriter(file);
				try {
					fw.write("0\n");
					fw.write("0\n");
					fw.write("0\n");
					fw.write("1\n");
					fw.write("0\n");
					fw.write("0\n");
					fw.write("0\n");
					fw.write("0\n");
					fw.write("0\n");
					fw.write("0\n");
					fw.write("0.0000\n");
					fw.write("Magnus");

				} catch (Exception f) {
					f.printStackTrace();
				} finally {
					fw.flush();
					fw.close();
				}
			} catch (IOException g) {
				g.printStackTrace();
			}
		}
		

	}

}
