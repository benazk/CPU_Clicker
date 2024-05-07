import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ventana extends JFrame {
    public Ventana() {
        this.setSize(300, 300);
        this.getContentPane().setBackground(Color.GREEN);
        JButton boton = new JButton("Abrir Ventana2");
        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Ventana2(Ventana.this).setVisible(true);
            }
        });
        this.add(boton);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Ventana().setVisible(true);
    }
}

class Ventana2 extends JDialog {
    public Ventana2(JFrame owner) {
        super(owner, true); // true para modal
        this.setSize(200, 200);
        this.getContentPane().setBackground(Color.RED);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
}