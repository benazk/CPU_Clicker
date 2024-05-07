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
include ("contraseña-olvidada-correo.php");
include ("cambiar-contraseña.php");
$pass = $_POST["nuevaContraseña"];
$encrypt = md5($pass);

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

$sql_nuevaContrasenna = "UPDATE Usuario SET contraseña = '$encrypt' WHERE correoElectronico = '$email'";
    if($conn->query($sql_nuevaContrasenna) == true){
        echo "<h1 style= 'font-family:hack; text-align:center; color:rgb(255, 239, 187);'>Contraseña cambiada correctamente</h1>";
        echo "<h2 style= 'font-family:hack;  text-align:center;'><a href='index.php' style= ' color:#5cccfc;'>Volver al Menú</a></h2>";
    };
?>