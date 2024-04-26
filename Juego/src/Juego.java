import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Juego extends JFrame implements ActionListener {
	
	public static Juego juego;
	
	public static JButton btnCPU, btnMejora1, btnMejora2, btnMejora3, btnMejora4, btnMejora5, btnBSoD;
	
	public static JLabel lblBits, lblBitsPS, lblMultiplicador;
	
	public static long bits = 0;
	
	public static int bitsPS = 0;
	
	public static int mejora1, mejora2, mejora3, mejora4, mejora5;
	
	Juego(){
		setTitle("CPU Clicker");

	    setSize(1366, 768);

	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    setLayout(null);
	    
	    btnCPU = new JButton("CPU");
	    btnCPU.setLocation(100,100);
	    btnCPU.setSize(200,200);
	    add(btnCPU);
	    btnCPU.addActionListener(this);
	    
	    lblBits = new JLabel("69420");
	    lblBits.setLocation(175,330);
	    lblBits.setSize(200,20);
	    add(lblBits);
	    
	    lblBitsPS = new JLabel("69");
	    lblBitsPS.setLocation(175,360);
	    lblBitsPS.setSize(80,20);
	    add(lblBitsPS);
	    
	    btnBSoD = new JButton("Blue Screen of Death");
	    btnBSoD.setLocation(820,30);
	    btnBSoD.setSize(400,100);
	    add(btnBSoD);
	    btnBSoD.addActionListener(this);
	    
	    btnMejora1 = new JButton("Mejora1");
	    btnMejora1.setLocation(900,150);
	    btnMejora1.setSize(250,60);
	    add(btnMejora1);
	    btnMejora1.addActionListener(this);
	    
	    
	    btnMejora2 = new JButton("Mejora2");
	    btnMejora2.setLocation(900,250);
	    btnMejora2.setSize(250,60);
	    add(btnMejora2);
	    btnMejora1.addActionListener(this);
	    
	    btnMejora3 = new JButton("Mejora3");
	    btnMejora3.setLocation(900,350);
	    btnMejora3.setSize(250,60);
	    add(btnMejora3);
	    btnMejora1.addActionListener(this);
	    
	    btnMejora4 = new JButton("Mejora4");
	    btnMejora4.setLocation(900,450);
	    btnMejora4.setSize(250,60);
	    add(btnMejora4);
	    btnMejora1.addActionListener(this);
	    
	    btnMejora5 = new JButton("Mejora5");
	    btnMejora5.setLocation(900,550);
	    btnMejora5.setSize(250,60);
	    add(btnMejora5);
	    btnMejora1.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton accion = (JButton) e.getSource();
		/*if(accion == btnCPU) {
			bits = Integer.parseInt(lblBits.getText());
			bits ++;
			lblBits.setText(String.valueOf(bits));
		}*/
		if (accion == btnMejora1) {
			mejora1++;
			System.out.println("Funcion valor: " + mejora1(mejora1));
			lblBitsPS.setText(String.valueOf(mejora1(mejora1)));
		}
		/*if (accion == btnMejora2) {
			mejora2++;
		}
		if (accion == btnMejora3) {
			mejora3++;
		}
		if (accion == btnMejora4) {
			mejora4++;
		}
		if (accion == btnMejora5) {
			mejora5++;
		}*/

	}

	public static void main(String[] args) {
		juego = new Juego();
		juego.setVisible(true);
	}
	
	public static int mejora1(int mejora) {
		int resultado = 0;
		int bits = Integer.parseInt(lblBitsPS.getText());
		System.out.println("bits: " + bits);
		resultado = bits + mejora;
		System.out.println("Mejora: " + resultado);
		
		return resultado;
	}
	public static int mejora2() {
		int resultado = 0;
		return resultado;
	}
	public static int mejora3() {
		int resultado = 0;
		return resultado;
	}
	public static int mejora4() {
		int resultado = 0;
		return resultado;
	}
	
	public static int mejora5() {
		int resultado = 0;
		return resultado;
	}
	public static int BSoD() {
		int resultado = 0;
		return resultado;
	}

}
