const btnSwitch = document.querySelector('#switch');

btnSwitch.addEventListener('click', () => {
	document.body.classList.toggle('dark');
	btnSwitch.classList.toggle('active');
});

console.log("SE ha conectado el JS con el JSP :)");
alert("Hola soy javascript");