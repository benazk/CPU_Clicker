<style>
   html{
    background-color: #272526;
   }
   @font-face {
	font-family: hack;
	src: url(Hack-Regular.ttf);
  }
</style>
<?php
session_start();
if (isset($_SESSION['email'])) {
    $email = $_SESSION['email'];
} else {
    header("Location: contrasena-olvidada1.php");
    exit;
}
$pass = $_POST["nuevaContraseña"];
$encrypt = md5($pass);

// Variables con las credenciales del servidor/base de datos
$servidor = "hl1235.dinaserver.com";
$usuario = "ibangames";
$password = "aW=112jWdKlHD013a.O";
$basedatos = "CPUClicker";

// Crear conexión usando las credenciales
$conn = new mysqli($servidor, $usuario, $password, $basedatos);

// Verificar conexión
if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}

$sql_nuevaContrasenna = "UPDATE usuario SET contrasena = '$encrypt' WHERE correoElectronico = '$email'";
    if($conn->query($sql_nuevaContrasenna) == true){
        echo "<h1 style= 'font-family:hack; text-align:center; color:rgb(255, 239, 187);'>Contraseña cambiada correctamente</h1>";
        echo "<h2 style= 'font-family:hack;  text-align:center;'><a href='index.php' style= ' color:#5cccfc;'>Volver al Menú</a></h2>";
    };
?>