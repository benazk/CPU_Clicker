<?php
include("cuenta.php");
include("iniciar.php");
?>
<script>
  function comprobarContrasennasIguales(){
    var pass = document.getElementById("pass").value;
    var pass2 = document.getElementById("pass2").value;
    if(pass != pass2){
      alert("Las contraseñas deben coincidir")
      return false;
    }
    return true;
  
  }
  function comprobarCamposCompletos(){
    var pass = document.getElementById("pass").value;
    var pass2 = document.getElementById("pass2").value;
    var nombre = document.getElementById("nombre").value
    var apellidos = document.getElementById("apellidos").value
    var date = document.getElementById("date").value
    var email = document.getElementById("email").value
    if(pass == null || pass2 == null || nombre == null || apellidos == null || date == null || email == null){
      alert("Todos los campos tienen que estar completos")
      return false;
    }
    return true;
  }
</script>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="cuenta.css" />
  <title>Form</title>
</head>
<body>
  
  <div class="login-wrap">
    
    <div class="login-html">
      <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Iniciar Sesión</label>
      <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Crear Cuenta</label>
      <div class="login-form">
        
        <div class="sign-in-htm">
          <form name="formIniciarSesion" action="iniciar.php" method="post" class="form" >
          <div class="group">
            <label for="usuario" class="label">Nombre de usuario</label>
            <input id="usuario" type="text" class="input" name="userIniciar">
          </div>
          <div class="group">
            <label for="passIniciar" class="label">Contraseña</label>
            <input id="passIniciar" type="password" class="input" data-type="password" name="passwordIniciar">
          </div>
          <div class="group">
            <input type="submit" class="button" value="Iniciar Sesión">
          </div>
        </form>
        </div>
        
        <div class="sign-up-htm">
          <form name="formCrearCuenta" action="cuenta.php" method="post" class="form" >
          <div class="group">
            <label for="nombre" class="label">Nombre</label>
            <input id="nombre" type="text" class="input" name="nombre">
          </div>
          <div class="group">
            <label for="apellidos" class="label">Apellido</label>
            <input id="apellidos" type="text" class="input" name="apellidos">
          </div>
          <div class="group">
            <label for="date" class="label">Fecha de nacimiento</label>
            <input id="date" type="date" class="input" data-type="date" name="date">
          </div>
          <div class="group">
            <label for="email" class="label">Correo Electrónico</label>
            <input id="email" type="text" class="input" name="email" 
            pattern="[\w]+@[a-zA-Z]+\.[a-z]{2,4}" title="introduzca un Correo Electrónico válido">
          </div>
          <div class="group">
            <label for="user" class="label">Nombre de usuario</label>
            <input id="user" type="text" class="input" name="user" pattern="[\w]{5,25}" title="El nombre de usuario debe de ser como mínimo de 5 carácteres">
          </div>
          <div class="group">
            <label for="pass" class="label">Contraseña</label>
            <input id="pass" type="password" class="input" data-type="password" name="password" 
            pattern="^((?=\S*?[A-Z])(?=\S*?[a-z])(?=\S*?[0-9]).{7,})\S$" title="la contraseña debe tener una mayuscula, minuscula, numero y debe ser como mínimo de 8 de longitud">
          </div>
          <div class="group">
            <label for="pass2" class="label">Repetir contraseña</label>
            <input id="pass2" type="password" class="input" data-type="password" name="password" pattern="^((?=\S*?[A-Z])(?=\S*?[a-z])(?=\S*?[0-9]).{7,})\S$">
          </div>
          <div class="group">
            <input type="submit" class="button" value="Crear Cuenta" onclick="return !!(comprobarContrasennasIguales() & comprobarCamposCompletos());">
          </div>
          <div class="olvidada">
            <a href="contrasena-olvidada1.php">Contraseña olvidada</a>
          </div>
          
        </form>
          </div>
        
        </div>
      
      </div>
    </div>
 

</body>
</html>