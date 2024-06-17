
const container = document.getElementById("container");
const registerBtn = document.getElementById("register");
const loginBtn = document.getElementById("login");

registerBtn.addEventListener("click", () => {
  container.classList.add("active");
});

loginBtn.addEventListener("click", () => {
  container.classList.remove("active");
});


// Check Login
document.addEventListener('DOMContentLoaded', function() {
  const urlParams = new URLSearchParams(window.location.search);
  if (urlParams.has('error')) {
    Swal.fire({
      icon: "error",
      title: "Ocurrio un Error!!!",
      text: "Usuario o contraseña incorrectos!!!"
    })
    .then((result) => { // Redireccion despues de salir de la alerta...
      window.location.href = '/loggin';
    });
  } else if(urlParams.has('logout')){
    Swal.fire({
      icon: "success",
      text: "La sesión ha sido finalizada!!!"
    })
    .then((result) => { // Redireccion despues de salir de la alerta...
      window.location.href = '/loggin';
    });
  }
});