<?php
$codigo;
date_default_timezone_set(date_default_timezone_get());
$date = date('Y/m/d h:i:s', time());
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $codigo = rand(0, 999999);
    $email = $_POST["correoRecuperar"];
    $sujeto = "Cambio de contraseña de CPU Clicker";
    $mensaje = "Has solicitado cambiar la contraseña de tu cuenta, el código para cambiarla es: " . $codigo;
    $mensajeSinCuenta = "Este correo no tiene una cuenta de CPU Clicker asociada";
    $cabezera = 'MIME-Version: 1.0' . "\r\n";
    $cabezera .= 'From: Your name <info@address.com>' . "\r\n";
    $cabezera .= 'Content-type: text/html; charset=iso-8859-1' . "\r\n";

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

    $sql_comprobarCorreo = "SELECT * FROM usuario WHERE correoElectronico = '$email'";
    $resultEmail = $conn->query($sql_comprobarCorreo);
    


    if ($row = $resultEmail->fetch_assoc()) {
        mail(
            $email,
            $sujeto,
            $mensaje,
            $cabezera
        );

        header("Location: ../contrasena-olvidada2.php");
        exit;
    } else {
        mail(
            $email,
            $sujeto,
            $mensajeSinCuenta,
            $cabezera
        );
        $sql_codigo = "INSERT INTO recuperacion (email, codigo, fecha) VALUES ('$email',$codigo,'$date');";
        $conn->query($sql_codigo);
        header("Location: ../contrasena-olvidada2.php");
        exit;
    }

}
