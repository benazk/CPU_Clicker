<?php
include ("contraseña-olvidada-correo.php");

if($_SERVER["REQUEST_METHOD"] == "POST") {

$cod_input = $_POST["codigoRecuperar"];
echo $cod_input . " " . $codigo;
if ($cod_input == $codigo) {
    header("Location: cambiar-contraseña.php");
    exit;
} else {
    echo '<script>alert("Código incorrecto")</script>';
}

}
?>
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
                            <label for="codigoRecuperar">Escribe el código que hemos enviado a tu correo: </label><br>
                            <input type="text" name="codigoRecuperar" id="codigoRecuperar">
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