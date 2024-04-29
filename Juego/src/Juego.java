import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Juego extends JFrame implements ActionListener, Runnable, MouseListener {
	
	public static Thread bitsObtenidos;
	
	public static Juego juego;
	
	public static JButton btnCPU, btnMejora1, btnMejora2, btnMejora3, btnMejora4, btnMejora5, btnBSoD;
	
	public static JLabel lblBits, lblBitsPS, lblMultiplicador;
	
	public static long bits = 0;
	
	public static int bitsPS = 0;
	
	public static int bitsPC = 1;
	
	public static int BSoD = 0;
	
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
	    btnCPU.addMouseListener(this);
	    
	    lblBits = new JLabel("60000");
	    lblBits.setLocation(175,330);
	    lblBits.setSize(200,20);
	    add(lblBits);
	    
	    lblBitsPS = new JLabel("1");
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
	    
	    
	    btnMejora2 = new JButton("Mejora2\n" + + mejora2);
	    btnMejora2.setLocation(900,250);
	    btnMejora2.setSize(250,60);
	    add(btnMejora2);
	    btnMejora2.addActionListener(this);
	    
	    btnMejora3 = new JButton("Mejora3");
	    btnMejora3.setLocation(900,350);
	    btnMejora3.setSize(250,60);
	    add(btnMejora3);
	    btnMejora3.addActionListener(this);
	    
	    btnMejora4 = new JButton("Mejora4");
	    btnMejora4.setLocation(900,450);
	    btnMejora4.setSize(250,60);
	    add(btnMejora4);
	    btnMejora4.addActionListener(this);
	    
	    btnMejora5 = new JButton("Mejora5");
	    btnMejora5.setLocation(900,550);
	    btnMejora5.setSize(250,60);
	    add(btnMejora5);
	    btnMejora5.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println(Functions.mejora2Price(mejora2));
		System.out.println(lblBitsPS.getText());
		bits = Integer.parseInt(lblBits.getText());
		JButton accion = (JButton) e.getSource();
		if(accion == btnCPU) {
			bits = Integer.parseInt(lblBits.getText());
			bits += mejora1;
			lblBits.setText(String.valueOf(bits));
		}
		if (accion == btnMejora1) {
			if(Integer.parseInt(lblBits.getText()) >= Functions.mejora1Price(mejora1)) {
				bits -= Functions.mejora1Price(mejora1);
				lblBits.setText(String.valueOf(bits));
				mejora1++;
				bitsPC = Functions.mejora1(mejora1) + 1;
				
			}
			
		}
		if (accion == btnMejora2) {
			if(Integer.parseInt(lblBits.getText()) >= Functions.mejora2Price(mejora2)) {
				bits -= Functions.mejora2Price(mejora2);
				lblBits.setText(String.valueOf(bits));
				mejora2++;
				lblBitsPS.setText(String.valueOf(Functions.mejora2(mejora2)));
				btnMejora2.setText("Mejora2 " + mejora2);
			}
		}
		/*
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
		bitsObtenidos = new Thread(juego);
		bitsObtenidos.start();
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bits = Integer.parseInt(lblBits.getText());
			bitsPS = Integer.parseInt(lblBitsPS.getText());
			bits += bitsPS;
			lblBits.setText(String.valueOf(bits));
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
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
	

}
