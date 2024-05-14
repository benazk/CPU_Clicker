import java.awt.CardLayout;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Aplicacion extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Menu menu;
    private Leaderboards leader;
    private InicioSesion sesionA; 
    private Juego juego;
    private CardLayout cardLayout;
    
    public Aplicacion() throws NumberFormatException, IOException {
        setTitle("CPUClicker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1366, 768);

        cardLayout = new CardLayout();
        setLayout(cardLayout);
        
        menu = new Menu(this);
        leader = new Leaderboards(this);
        sesionA = new InicioSesion(this);
        juego = new Juego(this);
        
        add(menu, "menu");
        add(leader, "ranking");
        add(sesionA, "sesion");
        add(juego, "juego");
        
        cardLayout.show(this.getContentPane(), "menu");
    }

    public void mostrarLeaderboards() {
        cardLayout.show(this.getContentPane(), "ranking");
        validate();
        repaint();
        revalidate();
    }
    
    public void mostrarMenu() {
        cardLayout.show(this.getContentPane(), "menu");
        validate();
        repaint();
        revalidate();
    }
    
    public void mostrarSesion() {
        cardLayout.show(this.getContentPane(), "sesion");
        validate();
        repaint();
        revalidate();
    }
    public void mostrarJuego() {
        cardLayout.show(this.getContentPane(), "juego");
        validate();
        repaint();
        revalidate();
    }
    
    
    
    public static void main(String[] args) {
            Aplicacion app;
            
			try {
				app = new Aplicacion();
				app.setVisible(true);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
    }
}
