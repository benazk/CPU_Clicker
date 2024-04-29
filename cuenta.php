<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") { // Solo ejecutar este código si el formulario se ha enviado
    // Guardo el contenido de los campos en variables de php
    $nombre = $_POST["nombre"];
    $apellidos = $_POST["apellido"];
    $email = $_POST["email"];
    $passwd = $_POST["password"];
    $encrypt = md5($passwd); // Encriptar la contraseña con md5
    $fecha = $_POST["fecha"];
    $fecha_format = substr($fecha, 0, 10) . ' ' . substr($fecha, 11, 5) . ':00';
    $nombre_usuario = $_POST['usuario'];


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
    $sql_usuarios = "INSERT INTO Usuario (nombreUsuario, correoElectronico, contraseña, fechaNacimiente, nombre, apellido)
VALUES ('$nombre_usuario', '$email', '$passwd', '$fecha_format', '$nombre', '$apellido');";
    // Función para ejecutar la consulta 
    $conn->query($sql_usuarios);

    echo "<h1 style= 'font-family:Poppins, sans-serif; text-align:center;'>Gracias por rellenar el formulario. Tu cuenta ha sido creada</h1>";
    echo "<h2 style= 'font-family:Poppins, sans-serif; text-align:center;'>Credenciales del Solicitante</h2>
        <ul style='position:relative; left:42%;'>
        <li style= 'font-family:Poppins, sans-serif;'>Nombre: $nombre</li>
        <li style= 'font-family:Poppins, sans-serif;'>Apellidos: $apellidos</li>
        <li style= 'font-family:Poppins, sans-serif;'>Email: $email</li>
        <li style= 'font-family:Poppins, sans-serif;'>Nombre de usuario: $nombre_usuario</li>
        </ul>";
    echo "<h2 style= 'font-family:Poppins, sans-serif;  text-align:center;'><a href='../index.html' style= ' color:#5cccfc;'>Volver al Menú</a></h2>
        <h2 style= ' font-family:Poppins, sans-serif; text-align:center;'><a href='restaurante.php' style= ' color:#5cccfc;'>Iniciar sesión</a></h2>";


    // Cerrar conexión
    $conn->close();
}
