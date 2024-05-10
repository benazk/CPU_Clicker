<?php
include ("iniciar.php");
// Saioa irekita ez badago joan hasierara identifikatu dadin
$idUser = null;
$usuario_sesion = null;
$user = null;
$bits = null;
$bitsMaximos = null;
$bitsPs = null;
$tiempoJugado = null;
$clicsHechos = null;
$mejorasTotales = null;
$BSoD = null;
$estadoSesion = null;
$abrirCerrar = null;

$servidor = "localhost";
$usuario = "root";
$password = "";
$basedatos = "CPUClicker";

if (!isset($_SESSION['sesion'])) {
    $estadoSesion = "CrearCuenta.php";
    $abrirCerrar = "Iniciar Sesión";
    $user = "guest";
    $bits = 0;
    $bitsMaximos = 0;
    $bitsPs = 0;
    $tiempoJugado = 0;
    $clicsHechos = 0;
    $mejorasTotales = 0;
    $BSoD = 0;
    


} else {
    $user = $_SESSION['sesion'];
    $estadoSesion = "php/cerrar-sesion.php";
    $abrirCerrar = "Cerrar Sesión";
    $conn = new mysqli($servidor, $usuario, $password, $basedatos);

    if ($conn->connect_error) {
        die("Conexión fallida: " . $conn->connect_error);
    }
    $sql_idUser = "SELECT idUsuario FROM usuario WHERE nombreUsuario = '$usuario_sesion'";
    $resultIdusuario = $conn->query($sql_idUser);

    if ($row = $resultIdusuario->fetch_array()) {
        $idUser = $row["idUsuario"];
    }

    $sql_estadisticas1 = "SELECT * FROM estadisticas WHERE idUsuario = '$idUser'";
    $resultIdest = $conn->query($sql_estadisticas1);

    if ($row = $resultIdest->fetch_assoc()) {
        $bits = $row["bitsActuales"];
        $bitsMaximos = $row["bitsMaximos"];
        $bitsPs = $row["bitsPS"];
        $tiempoJugado = $row["minutosJugados"];
        $clicsHechos = $row["clicksHechos"];
        $BSoD = $row["BSoD"];
    }
    
    $sql_mejoras1 = "SELECT sumaMejoras FROM mejoras WHERE idUsuario = '$idUser'";

    $resultMJ = $conn->query($sql_mejoras1);

    if ($row = $resultMJ->fetch_assoc()) {
        $mejorasTotales = $row["sumaMejoras"];
    }
}