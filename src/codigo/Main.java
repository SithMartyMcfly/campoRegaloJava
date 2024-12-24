package codigo;

import java.util.Random;
import java.util.Scanner;

/*
 * Vamos a realizar un programa que se llama Campo Regalos, el cual
 * mediante una matriz bidimensional nos ofrecerá la oportunidad de 
 * ir sumando puntos mientras nos desplazamos por ella y nos permitirá
 * seguir el camino mientras no obtengamos una casilla con valor 0, en
 * ese caso el programa nos dirá que hemos perdido
 */

public class Main {

	public static void main(String[] args) {
		
		/*
		 * En el main vamos a ir ejecutando los metodos para que este programa
		 * tenga modularidad y si quisiesemos cambiar la funcionalidad del mismo
		 * no tengamos que hacer cambios en todo el programa
		 */
		
		// establecemos los limites de una matriz cuadrada
		int limite = 5;
		// inicializamos una matriz cuadrada con los parámetros establecidos por el

		int[][] tablero = new int[limite][limite];

		// llamamos a la clase random para el inicio aleatorio del juego
		Random aleatorio = new Random();

		// iniciamos aleatoriamente en una posición al jugador
		int[] posicionJugador = { 0, aleatorio.nextInt(tablero.length) };

		// iniciamos los puntos totales
		int puntosTotales = 0;
		// llamamos a la creación del tablero
		crearTablero(tablero);
		System.out.println();
		System.out.println();
		System.out.println("tamaño " + (tablero[0].length - 1));

		mostrarTablero(tablero, posicionJugador);
		System.out.println();
		puntosTotales = tablero[posicionJugador[0]][posicionJugador[1]];
		System.out.println("Has empezado con: " +puntosTotales+ " puntos");
		
		System.out.println(movimiento(tablero, posicionJugador, puntosTotales));
	}

	/*
	 * En primer lugar vamos a crear el tablero, de manera que le pasaremos por parámetros
	 * los valores de filas y columnas que el usuario ha pasado por consola, ya que estos
	 * nos servirán para marcar los limites de nuestros bucles for anidados para pintar la
	 * matriz y asignar con la clase Random un número entre el 1 y el 100
	 */

	static void crearTablero(int tablero[][]) {
		Random aleatorio = new Random();
		// primero pintamos con los valores aleatrios la matriz
		for (int i = 0; i < tablero.length; i++) {
			System.out.println();
			for (int j = 0; j < tablero.length; j++) {
				tablero[i][j] = aleatorio.nextInt(100) + 1;
			}
			//generamos los ceros en columnas aleatorias, solo un cero por fila
			if (i > 0) {
				int posicionAleatoria = aleatorio.nextInt(tablero[i].length);
				tablero[i][posicionAleatoria] = 0;

				}
			for (int j = 0; j < tablero.length; j++) {
				System.out.print(tablero[i][j] + "\t");
			}
		}
	}
    //En caso de no querer tener esta guía y para una mejor experiencia, se deben borrar los Console
    //para que el programa no muestre ni puntuaciones ni donde se ocultan los ceros


    /*
     * Este metodo nos oculta los valores que tienen las casillas de nuestra Matriz, para ello
     * el codigo que va a a mostrar sera:
     * I => posición donde se encuentra el usuario
     * X => posiciones ocultas
     * - => posiciones de filas ya pasadas
     * * => posiciones que contenían un cero
     * El método va a recibir por parámetros la posición del jugador y los puntos Parciales que va obteniendo
     * el usuario, y un array con las coordenadas, de la posición del Jugador.
     */
	
	static void mostrarTablero (int[][] tablero, int posicionJugador[]) {
		int posicionFila = posicionJugador[0];
		int posicionColumna = posicionJugador[1];
		

		for (int i = 0; i < tablero.length; i++) {
			System.out.println("");

			for (int j = 0; j < tablero.length; j++) {
				if (i == posicionFila && j == posicionColumna) {
					System.out.print("I\t");
				} else if (i <= posicionFila && tablero[i][j] == 0) {
					System.out.print("*\t");
				} else if (i < posicionFila) {
					System.out.print("-\t");
				} else {
					System.out.print("X\t");
				}
			}
		}
	}

	 /*
	  * El método Movimiento es el motor principal de nuestro programa, ya que se encarga de que
	  * el usuario pueda moverse por el tablero, este recibe la posición del jugador y la puntuación
	  * que va obteniendo el usuario.
	  * Este es un método que va a pedir al usuario que se mueva, para ello
	  * ponemos un bucle do/while para que nos pida movimiento y ejecute el movimiento
	  * hasta cumplir las condiciones de éxito o derrota.
	  */
	static String movimiento(int tablero[][], int posicionJugador[], int puntosTotales)
			throws ArrayIndexOutOfBoundsException {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println();
			System.out.println("Introduce i para ir a la izquierda, d para ir a la derecha, r para ir recto");
			String mover = sc.next();
			int puntosJugada= 0;
			
			 //con un switch vamos a realizar el movimiento
			 //todos los movimientos suman filas, oséa que vamos siempre hacia abajo y en función 
			 //de si vamos a izquierda o derecha, sumaremos o restaremos en las columnas
			 //Además se ha implementado con una estructura if/else que el usuario no pueda desbordar la matriz
			switch (mover) {
			case "i":
				if (posicionJugador[1] > 0) {
					posicionJugador[0]++;
					posicionJugador[1]--;
					puntosJugada = tablero[posicionJugador[0]][posicionJugador[1]];
					puntosTotales += tablero[posicionJugador[0]][posicionJugador[1]];
					mostrarTablero(tablero, posicionJugador);
					System.out.println();
					System.out.println("Has obtenido " + puntosJugada + " has obtenido " + puntosTotales);
				} else {
					System.out.println("No puedes hacer ese movimiento, saldrías de los límites de la matriz (por la izq)");
				}
				break;
			case "d":
				if (posicionJugador[1] < (tablero[1].length-1)) {
					posicionJugador[0]++;
					posicionJugador[1]++;
					puntosJugada = tablero[posicionJugador[0]][posicionJugador[1]];
					puntosTotales += tablero[posicionJugador[0]][posicionJugador[1]];
					System.out.println();
					mostrarTablero(tablero, posicionJugador);
					System.out.println();
					System.out.println("Has obtenido " + puntosJugada + " has obtenido " + puntosTotales);
				} else {
					System.out.println("No puedes hacer ese movimiento, saldrías de los límites de la matriz (por la dcha)");
				}
				break;
			case "r":
				
				posicionJugador[0]++;
				puntosJugada = tablero[posicionJugador[0]][posicionJugador[1]];
				puntosTotales += tablero[posicionJugador[0]][posicionJugador[1]];
				mostrarTablero(tablero, posicionJugador);
				System.out.println();
				System.out.println("Has obtenido " + puntosJugada + " has obtenido " + puntosTotales);
				break;
			default:
				System.out.println("Movimiento DESCONOCIDO");
				// continue;
			}

		} while (posicionJugador[0] < (tablero[0].length - 1) && tablero[posicionJugador[0]][posicionJugador[1]] != 0);
		sc.close();
		
		//una vez finaliazo el bucle, se le anuncia al usuario si ha ganado o perdido
		if (tablero[posicionJugador[0]][posicionJugador[1]] == 0) {
			return "HAS PERDIDO";
		} else {
			return "Has GANADO " + puntosTotales;
		}

	}
}
