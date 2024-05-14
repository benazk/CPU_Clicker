<script type="text/javascript">
    function error() {
        alert("El código que has proporcionado es incorrecto");
    }
</script>
<?php
session_start();

if (isset($_SESSION['email'])) {
    $email = $_SESSION['email'];
} else {
    header("Location: contrasena-olvidada1.php");
    exit;
}
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $servidor = "hl1235.dinaserver.com";
    $usuario = "ibangames";
    $password = "aW=112jWdKlHD013a.O";
    $basedatos = "CPUClicker";
    $conn = new mysqli($servidor, $usuario, $password, $basedatos);

    if ($conn->connect_error) {
        die("Error al conectarse a la base de datos" . $conn->connect_error);
    }

    $codigo = null;
    $fechaBBDD = null;
    $sql_cod = "SELECT fecha , codigo FROM recuperacion WHERE email = '$email' AND fecha = (SELECT MAX(fecha) FROM recuperacion);";
    $resultSetcod = $conn->query($sql_cod);

    if ($row = $resultSetcod->fetch_assoc()) {
        $codigo = $row["codigo"];
        $fechaBBDD = $row["fecha"];
    }




    $cod_input = $_POST["codigoRecuperar"];
    if ($cod_input == $codigo) {
        header("Location: ../cambiar-contrasena.html");
    } else if ($cod_input != $codigo) {
        sleep(1);
        header("Location: ../contrasena-olvidada2.php");
        echo '<script type="text/javascript">',
            'error();',
            '</script>';
        exit;


    }
}

?>