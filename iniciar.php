<style>
   html{
    background-color: #272525;
   }
   @font-face {
	font-family: hack;
	src: url(Hack-Regular.ttf);
  }
</style>
<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") { // Solo ejecutar este código si el formulario se ha enviado
    // Guardo el contenido de los campos en variables de php
    $passwd = $_POST["passwordIniciar"];
    $encrypt = md5($passwd); // Encriptar la contraseña con md5
    $nombre_usuario = $_POST["userIniciar"];


    // Variables con las credenciales del servidor/base de datos
    $servidor = "localhost";
    $usuario = "root";
    $password = "";
    $basedatos = "CPUClicker";

    // Crear conexión usando las credenciales
    $conn = new mysqli($servidor, $usuario, $password, $basedatos);

    // Verificar conexión
    if ($conn->connect_error) {
        die("Conexión fallida: " . $conn->connect_error);
    }
    // Variables en las que guardo consultas a la base de datos ("$sql_usuarios")

    $userBBDD;
    $passwordBBDD;

    $sql_comprobarUser = "SELECT nombreUsuario, contraseña FROM Usuario WHERE nombreUsuario = '$nombre_usuario'";
    $row = $result->fetch_assoc();
    
        
    if($row = $result->fetch_assoc()) {
          $userBBDD = $row["nombreUsuario"];
          $passwordBBDD = $row["contraseña"];
    }

    if ($userBBDD == $usuario && $encrypt == $passwordBBDD) {
        $_SESSION['username'] = $usuario;
        header('location: index.html');
        echo "<h1 style= 'font-family:hack; text-align:center; color:rgb(255, 239, 187);'>Sesión iniciada correctamente</h1>";
        echo "<ul style='position:relative; left:42%;'>
            <li style= 'font-family:hack;'>Nombre de usuario: $nombre_usuario</li>
            </ul>";
        echo "<h2 style= 'font-family:hack;  text-align:center;'><a href='../indexIvan.html' style= ' color:#5cccfc;'>Volver al Menú</a></h2>";
    
    }
    

    // Cerrar conexión
    $conn->close();
}