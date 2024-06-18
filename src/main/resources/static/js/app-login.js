
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
      text: "Usuario o contraseña incorrectos!!!",
      customClass: {
        confirmButton: 'custom-confirm-button'
      }
    })
    .then((result) => { // Redireccion despues de salir de la alerta...
      window.location.href = '/loggin';
    });
  } else if(urlParams.has('logout')){
    Swal.fire({
      icon: "success",
      text: "La sesión ha sido finalizada!!!",
      customClass: {
        confirmButton: 'custom-confirm-button'
      }
    })
    .then((result) => { // Redireccion despues de salir de la alerta...
      window.location.href = '/loggin';
    });
  }
});

// Validar loggin
// validate signup form on keyup and submit
$("#form-loggin").validate({
  rules: {
    username: "required",
    password: "required"
  },
  messages: {
    username: "* Campo obligatorio *",
    password: "* Campo obligatorio *"
    }
});