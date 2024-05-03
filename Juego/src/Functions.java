import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Functions {

	public static int precioFuncion(int precioBase, int cantMejoras)
	{
		int multiplier = 0;
		for(int i = 0; i < cantMejoras; i++) {
			if (i%3 == 0) {
				multiplier += 1;
			}
			precioBase += (int) Math.floor(precioBase/4) + multiplier;
		}
		return precioBase;
	}
	
	public static int mejora1Price(int cantMej) {
		int precioBase = 5;
		int precioFinal = precioFuncion(precioBase,cantMej);;
		return precioFinal;
	}
	
	public static int mejora1(int cantMej) {
		int cantFinal = 1;
		for(int i = 0; i <= cantMej; i++) {
			cantFinal += i * (1 + Juego.BSoD);
		}
		return cantFinal;
	}
	
	public static int mejora2Price(int cantMej) {
		int precioBase = 40;
		int precioFinal = precioFuncion(precioBase,cantMej);
		return precioFinal;

	}
	
	public static int mejora2(int cantMej) {
		int cantFinal = 1;
		for(int i = 0; i <= cantMej * (1+Juego.BSoD); i++) {
			cantFinal += i;
		}
		return cantFinal;
	}

	public static int mejora3Price(int cantMej) {
		int precioBase = 300;
		int precioFinal = precioFuncion(precioBase,cantMej);
		return precioFinal;

	}
	
	public static int mejora3(int cantMej) {
		int cantFinal = 1;
		for(int i = 0; i <= cantMej * (1+Juego.BSoD); i++) {
			cantFinal += i*8;
		}
		return cantFinal;
	}

	public static int mejora4Price(int cantMej) {
		int precioBase = 2000;
		int precioFinal = precioFuncion(precioBase,cantMej);
		return precioFinal;

	}
	
	public static int mejora4(int cantMej) {
		int cantFinal = 1;
		for(int i = 0; i <= cantMej * (1+Juego.BSoD); i++) {
			cantFinal += i*50;
		}
		return cantFinal;
	}

	public static void cargarDrivers() {
		try {

            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }
	}
	public static String encriptacion(String contrasena) {
		String contrasenaEncriptada = "";
		try   
	        {  
	            /* Instancia MessageDigest para MD5 */  
	            MessageDigest m = MessageDigest.getInstance("MD5");  
	              
	            /* Añadir bytes de texto de la contraseña para digerir con MD5. */  
	            m.update(contrasena.getBytes());  
	              
	            /* Convertir el valor hash a bytes */   
	            byte[] bytes = m.digest();  
	              
	            /* El array de bytes tiene bytes en forma decimal. Conversion a formato hexadecimal. */  
	            StringBuilder s = new StringBuilder();  
	            for(int i=0; i< bytes.length ;i++)  
	            {  
	                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
	            }  
	              
	            /* Completar contraseña hash en formato hexadecimal  */  
	            contrasenaEncriptada = s.toString();  
	        }   
	        catch (NoSuchAlgorithmException e)   
	        {  
	            e.printStackTrace(); 
	        }
		return contrasenaEncriptada;  
	}
	
	public static int idUsuario() {
		int idUsuario = 0;
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpuclicker", "root", "");
			PreparedStatement stmtIdUser = conn.prepareStatement(
					"SELECT idUsuario FROM usuario WHERE nombreUsuario = " + "'" + Menu.usuario + "';");
			ResultSet rsUser = stmtIdUser.executeQuery();
			if (rsUser.next()) {
				idUsuario = rsUser.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idUsuario;
	}
	
	public static int posicionRandomEnRango (int min, int max) {
		int random = 0;
		random = (int) Math.floor(Math.random() * (max - min) + min);
		
		return random;
	}
	public static void playMusic(String musicLoc) {
		try {
			File musicPath = new File(musicLoc);
			if (musicPath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				clip.loop(2147000000);

			} else {
				System.out.println("Couldn't find Music file");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
