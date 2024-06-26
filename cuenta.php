
<style>
   html{
    background-color: #272525;
    margin-top: 3%;
   }
   @font-face {
	font-family: hack;
	src: url(Hack-Regular.ttf);
  }
</style>
<?php


if ($_SERVER["REQUEST_METHOD"] == "POST") { // Solo ejecutar este código si el formulario se ha enviado
    // Guardo el contenido de los campos en variables de php
    $nombre = $_POST["nombre"];
    $apellidos = $_POST["apellidos"];
    $email = $_POST["email"];
    $passwd = $_POST["password"];
    $encrypt = md5($passwd); // Encriptar la contraseña con md5
    $fecha = $_POST["date"];
    $fecha_format = substr($fecha, 0, 10) . ' ' . substr($fecha, 11, 5) . ':00';
    $nombre_usuario = $_POST["user"];

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
    // Variables en las que guardo consultas a la base de datos ("$sql_usuarios")

    $sql_usuarioRepetido = "SELECT nombreUsuario FROM usuario WHERE nombreUsuario = '$nombre_usuario'";
    $resultUser = $conn->query($sql_usuarioRepetido);
    $row = $resultUser->fetch_assoc();
    if($row != null){
        echo "<h1 style= 'font-family:hack; text-align:center; color:rgb(255, 239, 187);'>El nombre de usuario " .  $nombre_usuario . " ya existe</h1>";
        echo "<h2 style= 'font-family:hack;  text-align:center;'><a href='CrearCuenta.php' style= ' color:#5cccfc;'>Volver a intentarlo</a></h2>";
        return;
    }

    $sql_usuarios = "INSERT INTO usuario (nombreUsuario, correoElectronico, contrasena, fechaNacimiento, nombre, apellido)
VALUES ('$nombre_usuario', '$email', '$encrypt', '$fecha_format', '$nombre', '$apellidos');";
    // Función para ejecutar la consulta 
    $conn->query($sql_usuarios);

    $sql_id = "SELECT idUsuario FROM usuario WHERE nombreUsuario = '$nombre_usuario'";
    $resultId = $conn->query($sql_id);
    $row = $resultId->fetch_assoc();
    $id = $row["idUsuario"];

    $sql_estadisticas = "INSERT INTO estadisticas (idUsuario, bitsActuales, bitsMaximos, bitsPS, minutosJugados, clicksHechos, nombreArquitectura, BSoD, Victorias) VALUES ($id, 0,0,0,0,0,'Magnus',0,0)";
    $conn->query($sql_estadisticas);

    $sql_mejoras = "INSERT INTO mejoras (idUsuario, cantidadTicks, cantidadCache, cantidadFPS, cantidadTransistores, sumaMejoras) VALUES ($id,0,0,0,0,0)";
    $conn->query($sql_mejoras);

    echo "<h1 style= 'font-family:hack; text-align:center; color:rgb(255, 239, 187);'>Gracias por rellenar el formulario. Tu cuenta ha sido creada</h1>";
    echo "<h2 style= 'font-family:hack; text-align:center; color:rgb(255, 239, 187);'>Credenciales del Usuario</h2>
        <ul style='position:relative; left:42%; color:rgb(255, 239, 187);'>
        <li style= 'font-family:hack;'>Nombre: $nombre</li>
        <li style= 'font-family:hack;'>Apellidos: $apellidos</li>
        <li style= 'font-family:hack;'>Email: $email</li>
        <li style= 'font-family:hack;'>Nombre de usuario: $nombre_usuario</li>
        </ul>";
    echo "<h2 style= 'font-family:hack;  text-align:center;'><a href='index.php' style= ' color:#5cccfc;'>Volver al Menú</a></h2>
        <h2 style= ' font-family:hack; text-align:center;'><a href='CrearCuenta.php' style= ' color:#5cccfc;'>Iniciar sesión</a></h2>";


    // Cerrar conexión
    $conn->close();
}
