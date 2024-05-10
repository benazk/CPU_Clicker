<script type="text/javascript">
    function error() {
        alert("El código que has proporcionado es incorrecto");
    }
</script>
<?php
include "email.php";

$servidor = "localhost";
$usuario = "root";
$password = "";
$basedatos = "CPUClicker";
$conn = new mysqli($servidor, $usuario, $password, $basedatos);

if ($conn->connect_error) {
    die("Error al conectarse a la base de datos" . $conn->connect_error);
}

$cod_input = null;
$codigo = null;
$fechaBBDD = null;
$tiempolimite = date($fecha, strtotime('+1 hours'));
$sql_cod = "SELECT fecha , codigo FROM recuperacion WHERE email = 'beezquerro@aulanz.net' AND fecha = (SELECT MAX(fecha) FROM recuperacion);";
$resultsSet = $conn->query($sql_cod);

if ($row = $resultSet->fetch_assoc()) {
    $codigo = $row["codigo"];
    $fechaBBDD = $row["fecha"];
}


if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $cod_input = $_GET["codigoRecuperar"];
    if ($cod_input == $codigo) {
        header("Location: ../cambiar-contrasena.html");
    } else if ($cod_input != $codigo || $tiempolimite < $fechaBBDD) {
        sleep(1);
        header("Location: ../contrasena-olvidada2.php");
        echo '<script type="text/javascript">',
            'error();',
            '</script>';
        exit;


    }
}

?>