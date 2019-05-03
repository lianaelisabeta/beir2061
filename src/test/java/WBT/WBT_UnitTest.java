package WBT;

import beir2061MV.evaluator.controller.IntrebariController;
import beir2061MV.evaluator.exception.DuplicateIntrebareException;
import beir2061MV.evaluator.exception.InputValidationFailedException;
import beir2061MV.evaluator.exception.NotAbleToCreateStatisticsException;
import beir2061MV.evaluator.exception.NotAbleToCreateTestException;
import beir2061MV.evaluator.model.Intrebare;
import beir2061MV.evaluator.model.Statistica;
import org.junit.After;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

import static org.junit.Assert.*;

public class WBT_UnitTest {

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

        try {
            intrebariController.createNewTest();
        } catch (NotAbleToCreateTestException exc) {
            assertEquals("Nu exista suficiente intrebari pentru crearea unui test!(5)", exc.getMessage());
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

        try {
            intrebariController.createNewTest();
        } catch (NotAbleToCreateTestException exc) {
            assertEquals("Nu exista suficiente domenii pentru crearea unui test!(5)", exc.getMessage());
        }

    }


    @Test
    public void F02_TC04() throws NotAbleToCreateTestException {
        addIntrebari(5);
        beir2061MV.evaluator.model.Test test = intrebariController.createNewTest();
        assertEquals(5, test.getIntrebari().size());

    }

    @Test
    public void F02_TC05() throws NotAbleToCreateTestException {
        addIntrebari(5);
        for (int i = 5; i < 10; i++) {
            Intrebare intrebare = new Intrebare("Intrebare " + i + " ?",
                    "1)r1i" + i, "2)r2i" + i, "3)r3i" + i,
                    "1",
                    "Domeniu5");
            try {
                intrebariController.addNewIntrebare(intrebare);
            } catch (InputValidationFailedException | DuplicateIntrebareException e) {
                e.printStackTrace();
            }
        }
        beir2061MV.evaluator.model.Test test = intrebariController.createNewTest();
        assertEquals(5, test.getIntrebari().size());

    }

    @Test
    public void F03_Valid() {
        addIntrebari(4);
        Intrebare intrebare = new Intrebare("Intrebare5 ?",
                "1)r1i7", "2)r2i7", "3)r3i7",
                "2",
                "Domeniu3");
        try {
            intrebariController.addNewIntrebare(intrebare);
            Statistica statistica = intrebariController.getStatistica();
            Map<String, Integer> intrebariDomenii = statistica.getIntrebariDomenii();
            assertEquals(4, intrebariDomenii.keySet().size());
            assertEquals(2, intrebariDomenii.get("Domeniu3").intValue());
            assertEquals(1, intrebariDomenii.get("Domeniu1").intValue());
            assertEquals(1, intrebariDomenii.get("Domeniu2").intValue());
            assertEquals(1, intrebariDomenii.get("Domeniu0").intValue());

        } catch (NotAbleToCreateStatisticsException | DuplicateIntrebareException | InputValidationFailedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void F03_NonValid() {
        try {
            Statistica statistica = intrebariController.getStatistica();
        } catch (NotAbleToCreateStatisticsException e) {
            assertEquals("Repository-ul nu contine nicio intrebare!", e.getMessage());
        }
    }


    private void addIntrebari(int n) {
        for (int i = 0; i < n; i++) {
            Intrebare intrebare = new Intrebare("Intrebare " + i + " ?",
                    "1)r1i" + i, "2)r2i" + i, "3)r3i" + i,
                    "1",
                    "Domeniu" + i);
            try {
                intrebariController.addNewIntrebare(intrebare);
            } catch (InputValidationFailedException | DuplicateIntrebareException e) {
                e.printStackTrace();
            }
        }
    }

}