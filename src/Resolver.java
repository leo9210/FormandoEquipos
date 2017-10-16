import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Resolver {
	private int cantidadColaboradores;
	private int cantidadPreguntas;
	private ArrayList<Colaborador> listaColaboradores = new ArrayList<>();
	
	public Resolver() throws FileNotFoundException {
		Scanner s = new Scanner(new File("C:\\Users\\leo\\Desktop\\datos.in"));
		cantidadPreguntas = s.nextInt();
		cantidadColaboradores = s.nextInt();
		s.nextLine();
		int i = 0;
		while(s.hasNextLine()) {
			listaColaboradores.add(new Colaborador(i, s.nextLine()));
			i++;
		}
		s.close();
		
	}
	public void procesar() throws FileNotFoundException {
		int maximaAfinidad = 0;
		int cantidadMaxColaboradores = 0;
		String palabraRepetida = null;
		double numeroFinal = 0;
		for(Colaborador col:listaColaboradores) {
			int numeroAfinidad = 0;
			for(Colaborador col2:listaColaboradores) {
				int i = 0;
				col.numeroAfinidad = 0;
				while(col.preguntas.charAt(i)==col2.preguntas.charAt(i) && col.numeroColaborador != col2.numeroColaborador) {
					col.numeroAfinidad += 1;
					i++;
				}
				if (col.numeroAfinidad > numeroAfinidad ) {
					numeroAfinidad = col.numeroAfinidad;
				}
			}
			col.numeroAfinidad = numeroAfinidad;
			if (col.numeroAfinidad > maximaAfinidad) {
				maximaAfinidad = col.numeroAfinidad;
			}
		}
		for(int j = 0;j<cantidadColaboradores;j++) {
			if (listaColaboradores.get(j).numeroAfinidad == maximaAfinidad){
				cantidadMaxColaboradores += 1;
				palabraRepetida = listaColaboradores.get(j).preguntas.substring(0,maximaAfinidad);
			}
		}
		numeroFinal = cantidadMaxColaboradores * (Math.pow(maximaAfinidad, 2));
		PrintWriter salida = new PrintWriter(new File("C:\\Users\\leo\\Desktop\\datos.out"));
		salida.println(numeroFinal);
		salida.println(palabraRepetida);
		salida.close();
		
	}
}
