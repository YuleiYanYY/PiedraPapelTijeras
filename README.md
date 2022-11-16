Demo del juego:

https://user-images.githubusercontent.com/114681824/201523575-5238fb4f-8416-42d1-9eb3-da3dfc2e01c0.mp4

### Documentación de la aplicación

1. Creación de Activity para el menú de inicio

	- Intent:
		- Intent.putEntra y startActivityForResult para enviar el nombre del usuario al 		  siguiente Activity

	- Finish: Finalizar Activity de menú al entrar al siguiente Activity

2. Creación de Activity para el juego en sí
	- Uso de StringBuilder en lugar de String para aumentar eficiencia
		- StringBuilder al ser mutable, consume menos recursos en caso de requerir cambiar constantemente el valor de la variable
		- Con el uso de SringBuilder.delete(0, stringBuilder.length()) en vez de reasignarle un nuevo valor usando new StringBuilder,  conseguimos limpiar  la cadena para reutilizarlo y así aumentar la rapidez

	- Uso de getString(R.string.(componente)) a la hora de obtener los textos de los componentes como puede ser un TextView
		- Facilita la traducción

	- Uso de Toast para mostrar mensaje:
		- Mismo toast para todos los mensajes para tener mayor control:
			- Al utilizar la misma variable toast, podemos usar el método Toast.cancel cada vez que se pulse un botón para así refrescar el mensaje del toast al instante, ya que de manera estándar, se acumularía los mensajes y existiría un retardo al mostrar los mensajes que se va aumentando conforme el usuario cliequea los botones que desata el toast
		 - Uso de Toast.setGravity para modificar la posición del toast

	- Uso de Intent.putExtra y  startActivity para enviar mensaje en vez de  startActivityForResult
		- Se explicará más adelante

3. Creación de Activity para enviar mensaje

	- Uso de Intent.getIntent para recibir datos de otro Activity:
		- Instanciamos un Intent usando dicho método para conectar al Intent creado en el anterior Activity
		- Para recoger datos, usamos el método Intent.get(tipo dato)Extra

	- Uso de Intent.resolveActivity(getPackageManager()) para saber si existe una aplicación en el dispositivo compatible con la acción
		- Si el el valor devuelto no es nulo, utilizamos starActivity para lanzar el intent, en caso contrario, mediante un toast, mostramos mensaje de error


