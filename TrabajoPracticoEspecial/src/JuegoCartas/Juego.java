package JuegoCartas;

import java.util.*;

public class Juego {

	private int puntos1;
	private int puntos2;
	private Jugador jugador1;
	private Jugador jugador2;
	private Mazo mazo;
	private int rondas;
	private boolean turno;
	private int MAXRONDAS=10;
	
	public Juego(Jugador j1, Jugador j2, Mazo mazo) {
		puntos1=0;
		puntos2=0;
		rondas=1;
		this.jugador1=j1;
		this.jugador2=j2;
		this.mazo=mazo;
	}
	
	public Juego(int maxrondas, Jugador j1, Jugador j2, Mazo mazo) {
		puntos1=0;
		puntos2=0;
		rondas=1;
		turno=false;
		MAXRONDAS=maxrondas;
		this.jugador1=j1;
		this.jugador2=j2;
		this.mazo=mazo;
	}
	
	public void jugar() {
		//SE SETEAN POSOS, CARTAS Y ATRIBUTOS.
		ArrayList<Mazo> posos=this.repartir();
		jugador1.setPoso(posos.get(0));
		jugador2.setPoso(posos.get(1));
		Mazo poso1= jugador1.getPoso();
		Mazo poso2= jugador2.getPoso();
		while((!jugador1.posoVacio()) && (!jugador2.posoVacio()) && (rondas<=MAXRONDAS)) {
			System.out.println("------- Ronda "+this.rondas+" -------");
			Carta carta1=jugador1.elegirCarta(poso1);
			Carta carta2=jugador2.elegirCarta(poso2);
			poso1.removeCarta(carta1);
			poso2.removeCarta(carta2);
			System.out.println("SE MUESTRA EN NUMERO DE CARTAS DENTRO DE LOS POSOS"+poso1.getCantidadCartas()+" - "+poso2.getCantidadCartas());
			//SE ELIGE EL ATRIBUTO POR EL CUAL SE COMPITE Y SE IMPRIME POR PANTALLA
			if(turno==false) {
				Atributo atributo1=jugador1.elegirAtributo(carta1);
				Atributo atributo2=carta2.getAtributoPorNombre(atributo1.getNombre());
				carta2.setAtributoElegido(atributo2);
				System.out.println("El jugador "+jugador1.getNombre()+" selecciona competir por el atributo "+atributo1.getNombre());
				System.out.println(jugador1.toString());
				System.out.println(jugador2.toString());
			}
			else {
				Atributo atributo2=jugador2.elegirAtributo(carta2);
				Atributo atributo1=carta1.getAtributoPorNombre(atributo2.getNombre());
				carta1.setAtributoElegido(atributo1);
				System.out.println("El jugador "+jugador2.getNombre()+" selecciona competir por el atributo "+atributo2);
				System.out.println(jugador1.toString());
				System.out.println(jugador2.toString());
			}
			//SE COMPARAN LOS ATRIBUTOS Y SE AGREGA: LOS PUNTOS, LAS CARTAS AL GANADOR E IMPRIME POR PANTALLA
			if(carta1.compararCartas(carta2)==carta1) {
				jugador1.incrementarPuntos(puntos1);
				poso1.addCarta(carta1);
				poso1.addCarta(carta2);
				System.out.println("SE MUESTRA EN NUMERO DE CARTAS DENTRO DE LOS POSOS"+poso1.getCantidadCartas()+" - "+poso2.getCantidadCartas());
				System.out.println("Gana la ronda "+jugador1.getNombre());
				turno=false;
			}
			else if(carta1.compararCartas(carta2)==carta2) {
				jugador2.incrementarPuntos(puntos2);
				poso2.addCarta(carta1);
				System.out.println("SE MUESTRA EN NUMERO DE CARTAS DENTRO DE LOS POSOS"+poso1.getCantidadCartas()+" - "+poso2.getCantidadCartas());
				poso2.addCarta(carta2);
				System.out.println("SE MUESTRA EN NUMERO DE CARTAS DENTRO DE LOS POSOS"+poso1.getCantidadCartas()+" - "+poso2.getCantidadCartas());
				System.out.println("Gana la ronda "+jugador2.getNombre());
				turno=true;
			}	
			else {
				poso1.addCarta(carta1);
				poso2.addCarta(carta2);
				System.out.println("Empate!!");
			}
			System.out.println(jugador1.getNombre()+" posee ahora "+jugador1.getCantidadCartas()+" cartas y "+jugador2.getNombre()+" posee ahora "+jugador2.getCantidadCartas()+" cartas.");
			rondas++;
		}
		//SALE DEL WHILE E IMPRIME AL GANADOR.
		//Jugador ganador=this.getGanador();
		//System.out.println("El ganador del juego es"+ ganador.getNombre());
	}
	
	public ArrayList<Mazo> repartir() {
		ArrayList<Mazo> posos= new ArrayList<>();
		mazo.mezclar();
		Mazo poso1= new Mazo();
		Mazo poso2= new Mazo();
		for(int i=0; i<mazo.getCartas().size()/2; i++) {
			poso1.addCarta(mazo.getCartas().remove(i));
			posos.add(poso1);
		}
		for (int f=0;f<mazo.getCartas().size();f++){
			poso2.addCarta(mazo.getCartas().remove(f));
			posos.add(poso2);
		}
		return posos;
	}
	
	public Jugador getGanador() {
		if (puntos1>puntos2) {
			return jugador1;
		} else if (puntos2>puntos1) {
			return jugador2;
		} else {
			return null;
		}
	}	

	public Mazo getMazo() {
		return mazo;
	}

	public void setMazo(Mazo mazo) {
		this.mazo = mazo;
	}

	public Jugador getJugador1() {
		return jugador1;
	}

	public void setJugador1(Jugador jugador1) {
		this.jugador1 = jugador1;
	}

	public Jugador getJugador2() {
		return jugador2;
	}

	public void setJugador2(Jugador jugador2) {
		this.jugador2 = jugador2;
	}
	
	
}
