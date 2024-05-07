<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Game Website Template, Free Download, XHTML/CSS Layout</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link href="olvidada.css" rel="stylesheet" type="text/css" />
</head>

<body>
    <div id="templatemo_wrapper_outer">
        <div id="templatemo_wrapper_inner">

            <div id="banner">
                <a href="index.html">
                    <div id="titulo">░█▀▀░█▀█░█░█░░░█▀▀░█░░░▀█▀░█▀▀░█░█░█▀▀░█▀▄<br>
                        ░█░░░█▀▀░█░█░░░█░░░█░░░░█░░█░░░█▀▄░█▀▀░█▀▄<br>
                        ░▀▀▀░▀░░░▀▀▀░░░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀▀▀░▀░▀
                    </div>
                </a>
            </div>


            <div id="menu">
                <ul>
                    <li><a href="index.php">Inicio</a></li>
                    <li><a href="wiki.html">Wiki</a></li>
                    <li><a href="actualizaciones.html">Actualizaciones</a></li>
                    <li><a href="juego.html">Juego</a></li>

                </ul>
            </div>

            <div id="content_wrapper">



                <div class="content margin_right_10">


                    <div class="margin_bottom_40">
                        <form name="formRecuperarContraseña" action="#" method="post" class="form">
                            <label for="correoRecuperar">Escribe tu Correo Electrónico:</label><br>
                            <input type="text" name="correoRecuperar" id="correoRecuperar">
                            <input type="submit" name="boton" id="button" class="button">

                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <div id="footer">
        CPU Clicker © 2024 by IBAN Games is licensed under CC BY-NC-SA 4.0 <a href="#">IBAN Games</a>
    </div>
</body>

</html>

<?php


$codigo = rand(0, 999999);


if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $email = $_POST["correoRecuperar"];
    echo $email;
    $sujeto = "Cambio de contraseña de CPU Clicker";
    $mensaje = "Has solicitado cambiar la contraseña de tu cuenta, el código para cambiarla es: " . $codigo;
    $mensajeSinCuenta = "Este correo no tiene una cuenta de CPU Clicker asociada";
    $cabezera = 'MIME-Version: 1.0' . "\r\n";
    $cabezera .= 'From: CPU Clicker <passwordchange@request.self>' . "\r\n";
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

    $sql_comprobarCorreo = "SELECT * FROM Usuario WHERE correoElectronico = '$email'";
    $resultEmail = $conn->query($sql_comprobarCorreo);

    if ($row = $resultEmail->fetch_assoc()) {
        mail(
            $email,
            $sujeto,
            $mensaje,
            $cabezera
        );
        header("Location: contraseña-olvidada-codigo.php");
    } 
    else {
        mail(
            $email,
            $sujeto,
            $mensajeSinCuenta,
            $cabezera
        );
        header("Location: contraseña-olvidada-codigo.php");
    }
}
?>