package beir2061MV.evaluator.controller;

import beir2061MV.evaluator.exception.DuplicateIntrebareException;
import beir2061MV.evaluator.exception.InputValidationFailedException;
import beir2061MV.evaluator.exception.NotAbleToCreateTestException;
import beir2061MV.evaluator.model.Intrebare;
import org.junit.After;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.junit.Assert.*;

public class IntrebariControllerTestWBT {

    private IntrebariController intrebariController = new IntrebariController("test2.txt");

    @After
    public void cleanFile() {

        try {
            PrintWriter pw = new PrintWriter("test2.txt");
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void F02_TC01() {
        addIntrebari(3);

        try{
            intrebariController.createNewTest();}
        catch (NotAbleToCreateTestException exc){
            assertEquals("Nu exista suficiente intrebari pentru crearea unui test!(5)",exc.getMessage());
        }
    }

    @Test
    public void F02_TC02() {
        addIntrebari(3);
        Intrebare intrebare1 = new Intrebare("Intrebare 4 ?",
                "1)r1i4", "2)r2i4", "3)r3i4",
                "1",
                "Domeniu1");

        Intrebare intrebare2 = new Intrebare("Intrebare 5 ?",
                "1)r1i5", "2)r2i5", "3)r3i5",
                "1",
                "Domeniu1");
        try {
            intrebariController.addNewIntrebare(intrebare1);
            intrebariController.addNewIntrebare(intrebare2);
        } catch (InputValidationFailedException | DuplicateIntrebareException e) {
            e.printStackTrace();
        }

        try{
            intrebariController.createNewTest();}
        catch (NotAbleToCreateTestException exc){
            assertEquals("Nu exista suficiente domenii pentru crearea unui test!(5)",exc.getMessage());
        }

    }


    @Test
    public void F02_TC04() throws NotAbleToCreateTestException {
        addIntrebari(5);
        beir2061MV.evaluator.model.Test test = intrebariController.createNewTest();
        assertEquals(5,test.getIntrebari().size());

    }

    @Test
    public void F02_TC05() throws NotAbleToCreateTestException {
        addIntrebari(5);
        for(int i = 5; i<10; i++){
            Intrebare intrebare = new Intrebare("Intrebare " + i +" ?",
                    "1)r1i"+i, "2)r2i"+i, "3)r3i"+i,
                    "1",
                    "Domeniu5");
            try {
                intrebariController.addNewIntrebare(intrebare);
            } catch (InputValidationFailedException|DuplicateIntrebareException e) {
                e.printStackTrace();
            }
        }
        beir2061MV.evaluator.model.Test test = intrebariController.createNewTest();
        assertEquals(5,test.getIntrebari().size());

    }


    private void addIntrebari(int n){
        for(int i = 0; i<n; i++){
            Intrebare intrebare = new Intrebare("Intrebare " + i +" ?",
                    "1)r1i"+i, "2)r2i"+i, "3)r3i"+i,
                    "1",
                    "Domeniu"+i);
            try {
                intrebariController.addNewIntrebare(intrebare);
            } catch (InputValidationFailedException|DuplicateIntrebareException e) {
                e.printStackTrace();
            }
        }
    }

}