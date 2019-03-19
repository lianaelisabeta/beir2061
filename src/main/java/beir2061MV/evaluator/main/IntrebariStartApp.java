package beir2061MV.evaluator.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import beir2061MV.evaluator.exception.DuplicateIntrebareException;
import beir2061MV.evaluator.exception.InputValidationFailedException;
import beir2061MV.evaluator.exception.NotAbleToCreateTestException;
import beir2061MV.evaluator.model.Intrebare;
import beir2061MV.evaluator.model.Statistica;

import beir2061MV.evaluator.controller.IntrebariController;
import beir2061MV.evaluator.exception.NotAbleToCreateStatisticsException;
import beir2061MV.evaluator.model.Test;

//functionalitati
//F01.	 adaugarea unei noi intrebari pentru un anumit domeniu (enunt intrebare, raspuns 1, raspuns 2, raspuns 3, raspunsul corect, domeniul) in setul de intrebari disponibile;
//F02.	 crearea unui nou test (testul va contine 5 intrebari alese aleator din cele disponibile, din domenii diferite);
//F03.	 afisarea unei statistici cu numarul de intrebari organizate pe domenii.

public class IntrebariStartApp {

	private static final String file = "intrebari.txt";
	
	public static void main(String[] args) throws IOException {

		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

		IntrebariController intrebariController = new IntrebariController(file);
		boolean activ = true;
		String optiune;

		while(activ){

			System.out.println("");
			System.out.println("1.Adauga intrebare");
			System.out.println("2.Creeaza test");
			System.out.println("3.Statistica");
			System.out.println("4.Exit");
			System.out.println("");

			optiune = console.readLine();

			switch(optiune){
			case "1" :
				try{

				String enunt = console.readLine();
				String varianta1 = console.readLine();
				String varianta2 = console.readLine();
				String varianta3 = console.readLine();
				String variantaCorecta = console.readLine();
				String domeniu = console.readLine();

				Intrebare intrebare = new Intrebare(enunt,varianta1,varianta2,varianta3,variantaCorecta,domeniu);
				intrebariController.addNewIntrebare(intrebare);
				}
				catch (InputValidationFailedException | DuplicateIntrebareException ie){
					System.out.println(ie.getMessage());
				}
				break;
			case "2" :
				try {
					Test test = intrebariController.createNewTest();
					for(Intrebare intrebare : test.getIntrebari()){
						System.out.println(intrebare);
					}
				} catch (NotAbleToCreateTestException e) {
					System.out.println(e.getMessage());
				}
				break;
			case "3" :

				Statistica statistica;
				try {
					statistica = intrebariController.getStatistica();
					System.out.println(statistica);
				} catch (NotAbleToCreateStatisticsException e) {
					System.out.println(e.getMessage());
				}

				break;
			case "4" :
				activ = false;
				break;
			default:
				break;
			}
		}

	}

}
