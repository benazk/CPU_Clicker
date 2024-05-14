<?php
include "php/estado-sesion.php";
?>
<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Juego</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link href="style.css" rel="stylesheet" type="text/css" />
</head>

<body>
    <div id="templatemo_wrapper_outer">
        <div id="templatemo_wrapper_inner">

            <div id="banner">

                <div id="titulo">░█▀▀░█▀█░█░█░░░█▀▀░█░░░▀█▀░█▀▀░█░█░█▀▀░█▀▄<br>
                    ░█░░░█▀▀░█░█░░░█░░░█░░░░█░░█░░░█▀▄░█▀▀░█▀▄<br>
                    ░▀▀▀░▀░░░▀▀▀░░░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀▀▀░▀░▀
                </div>

            </div> <!-- end of banner -->

            <div id="menu">
                <ul>
                    <li><a href="index.php" class="current">Inicio</a></li>
                    <li><a href="wiki.php">Wiki</a></li>
                    <li><a href="actualizaciones.php">Actualizaciones</a></li>
                    <li><a href="juego.php">Juego</a></li>

                </ul>
            </div> <!-- end of menu -->

            <div id="content_wrapper">



                <div class="content margin_right_10">

                    <div id="usuario">
                        <p class="padding"><?php echo $user ?>@<?php echo $user ?>:$ ~ cat Datos_de_usuario.txt</p>
                        <h1 class="padding"><?php echo "$bits" ?> Bits</h1>
                        <h2 class="padding">Tiempo Jugado: <?php echo $tiempoJugado ?> mins </h2>
                        <h2 class="padding">Bits máximos: <?php echo $bitsMaximos ?> Bits</h2>
                        <h2 class="padding">Mejoras Totales: <?php echo $mejorasTotales ?></h2>
                        <h2 class="padding">Bits por segundo: <?php echo $bitsPs ?></h2>
                        <h2 class="padding">Clicks Hechos: <?php echo $clicsHechos ?></h2>
                        <h2 class="padding">BSoD: <?php echo $BSoD ?></h2>
                    </div>

                    <div class="margin_bottom_40"></div>
                </div> <!-- end of content -->
                <div class="right">

                    <div class="header_01">Menú</div>
                    <div class="margin_bottom_20"></div>


                    <div class="latest_news border_bottom">
                       <div class="header_03"><a href="<?php echo $estadoSesion?>" id = "sesion"><?php echo $abrirCerrar?></a></div>

                    </div>

                    <div class="margin_bottom_10"></div>

                    <div class="latest_news border_bottom">
                        <div class="header_03"><a href="creditos.php">Créditos</a></div>

                    </div>
                    <div class="margin_bottom_10"></div>

                    <div class="header_03"><a href="cuenta.php">Descargar Juego</a></div>
                </div> <!-- end of right side bar -->



                <div class="cleaner"></div>
            </div> <!-- end of content wrapper -->

        </div>
    </div>

    <div id="footer">
        CPU Clicker © 2024 by IBAN Games is licensed under CC BY-NC-SA 4.0 <a href="#">IBAN Games</a>
        <!-- Credit: www.templatemo.com -->
    </div> <!-- end of footer -->



    <!-- templatemo 131 game -->
    <!-- 
Game Template 
http://www.templatemo.com/preview/templatemo_131_game 
-->
</body>

</html>