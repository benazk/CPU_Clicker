import java.awt.GridLayout;
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
	
	public static JLabel lblCostoM1 = new JLabel("5 bits"), lblNombreM1 = new JLabel("Ticks"), lblCantidadM1 = new JLabel("0"), 
	lblCostoM2 = new JLabel("40 bits"), lblNombreM2 = new JLabel("Cache"), lblCantidadM2 = new JLabel("0"), 
	
	lblCostoM3 = new JLabel("300 bits"), lblNombreM3 = new JLabel("FPS"), lblCantidadM3 = new JLabel("0"), 
	
	lblCostoM4 = new JLabel("2000 bits"), lblNombreM4 = new JLabel("Transistores"), lblCantidadM4 = new JLabel("0");
	
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
	    
	    lblBitsPS = new JLabel("0");
	    lblBitsPS.setLocation(175,360);
	    lblBitsPS.setSize(80,20);
	    add(lblBitsPS);
	    
	    btnBSoD = new JButton("Blue Screen of Death");
	    btnBSoD.setLocation(820,30);
	    btnBSoD.setSize(400,100);
	    add(btnBSoD);
	    btnBSoD.addActionListener(this);
	    
	    btnMejora1 = new JButton();
	    btnMejora1.setLayout(new GridLayout(2,2));
	    btnMejora1.add(lblNombreM1);
	    btnMejora1.add(lblCantidadM1);
	    btnMejora1.add(lblCostoM1);
	    btnMejora1.setLocation(900,150);
	    btnMejora1.setSize(250,60);
	    add(btnMejora1);
	    btnMejora1.addActionListener(this);
	    
	    
	    btnMejora2 = new JButton();
	    btnMejora2.setLayout(new GridLayout(2,2));
	    btnMejora2.add(lblNombreM2);
	    btnMejora2.add(lblCantidadM2);
	    btnMejora2.add(lblCostoM2);
	    btnMejora2.setLocation(900,250);
	    btnMejora2.setSize(250,60);
	    add(btnMejora2);
	    btnMejora2.addActionListener(this);
	    
	    btnMejora3 = new JButton();
	    btnMejora3.setLayout(new GridLayout(2,2));
	    btnMejora3.add(lblNombreM3);
	    btnMejora3.add(lblCantidadM3);
	    btnMejora3.add(lblCostoM3);
	    btnMejora3.setLocation(900,350);
	    btnMejora3.setSize(250,60);
	    add(btnMejora3);
	    btnMejora3.addActionListener(this);
	    
	    btnMejora4 = new JButton();
	    btnMejora4.setLayout(new GridLayout(2,2));
	    btnMejora4.add(lblNombreM4);
	    btnMejora4.add(lblCantidadM4);
	    btnMejora4.add(lblCostoM4);
	    btnMejora4.setLocation(900,450);
	    btnMejora4.setSize(250,60);
	    add(btnMejora4);
	    btnMejora4.addActionListener(this);
	  
	}
	
	public void actionPerformed(ActionEvent e) {
		bits = Integer.parseInt(lblBits.getText());
		JButton accion = (JButton) e.getSource();
		if(accion == btnCPU) {
			bits = Integer.parseInt(lblBits.getText());
			bits += bitsPC;
			lblBits.setText(String.valueOf(bits));
		}
		if (accion == btnMejora1) {
			mejora1 = Integer.parseInt(lblCantidadM1.getText());
			if(Integer.parseInt(lblBits.getText()) >= Functions.mejora1Price(mejora1)) {
				bits -= Functions.mejora1Price(mejora1); //Quitar costo de la mejora al dinero total
				lblBits.setText(String.valueOf(bits));
				mejora1++; // Sumar 1 a las mejoras totales de ese tipo
				lblCantidadM1.setText(String.valueOf(mejora1));  // Poner la cantidad de mejoras en el frame
				bitsPC = Functions.mejora1(mejora1) + 1; // Sumar bits por click teniendo en cuenta la mejora
				lblCostoM1.setText(String.valueOf(Functions.mejora1Price(mejora1)) + " bits"); // Poner el costo de la mejora
				
			}
			
		}
		if (accion == btnMejora2) {
			if(Integer.parseInt(lblBits.getText()) >= Functions.mejora2Price(mejora2)) {
				bits -= Functions.mejora2Price(mejora2);
				lblBits.setText(String.valueOf(bits));
				mejora2++;
				lblCantidadM2.setText(String.valueOf(mejora2));
				lblBitsPS.setText(String.valueOf(Integer.parseInt(lblBitsPS.getText()) + Functions.mejora2(mejora2)));
				lblCostoM2.setText(String.valueOf(Functions.mejora2Price(mejora2)) + " bits");
			}
		}
		
		if (accion == btnMejora3) {
			if(Integer.parseInt(lblBits.getText()) >= Functions.mejora3Price(mejora3)) {
				bits -= Functions.mejora3Price(mejora3);
				lblBits.setText(String.valueOf(bits));
				mejora3++;
				lblCantidadM3.setText(String.valueOf(mejora3));
				lblBitsPS.setText(String.valueOf(Integer.parseInt(lblBitsPS.getText()) + Functions.mejora3(mejora3)));
				lblCostoM3.setText(String.valueOf(Functions.mejora3Price(mejora3)) + " bits");
			}
		}
		if (accion == btnMejora4) {
			if(Integer.parseInt(lblBits.getText()) >= Functions.mejora4Price(mejora4)) {
				bits -= Functions.mejora4Price(mejora4);
				lblBits.setText(String.valueOf(bits));
				mejora4++;
				lblCantidadM4.setText(String.valueOf(mejora4));
				lblBitsPS.setText(String.valueOf(Integer.parseInt(lblBitsPS.getText()) + Functions.mejora4(mejora4)));
				lblCostoM4.setText(String.valueOf(Functions.mejora4Price(mejora4)) + " bits");
			}
		}

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
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bits = Integer.parseInt(lblBits.getText());
			bitsPS = Integer.parseInt(lblBitsPS.getText());
			if(bits > 10) {
			bits += (int)Math.floor(bitsPS/10);
			}
			else {
				bits += bitsPS;
			}
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
