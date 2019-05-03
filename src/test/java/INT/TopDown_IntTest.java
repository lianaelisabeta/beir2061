package INT;

import beir2061MV.evaluator.controller.IntrebariController;
import beir2061MV.evaluator.exception.DuplicateIntrebareException;
import beir2061MV.evaluator.exception.InputValidationFailedException;
import beir2061MV.evaluator.exception.NotAbleToCreateStatisticsException;
import beir2061MV.evaluator.exception.NotAbleToCreateTestException;
import beir2061MV.evaluator.model.Intrebare;
import beir2061MV.evaluator.model.Statistica;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class TopDown_IntTest {

    private IntrebariController intrebariController;
    private Intrebare intrebare;


    @Before
    public void setUp() {
        intrebariController =  new IntrebariController("test.txt");
        intrebare = new Intrebare("Care este aria patratului?",
                "1)l*l", "2)l-l", "3)4*l",
                "1",
                "Geometrie");
    }

    @After
    public  void cleanFile() {

        try {
            PrintWriter pw = new PrintWriter("test.txt");
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void A_Test() throws DuplicateIntrebareException, InputValidationFailedException {
        //F01 - unit test
        String enunt = "Care ";
        enunt += CharBuffer.allocate(93).toString().replace('\0', '.') + "?";
        intrebare.setEnunt(enunt);
        Intrebare intrebare1 = null;

        intrebare1 = intrebariController.addNewIntrebare(intrebare);
        assertEquals(intrebare, intrebare1);

    }

    @Test
    public void B_Test() throws NotAbleToCreateTestException, DuplicateIntrebareException, InputValidationFailedException {
        //F02 - unit test
        addIntrebari(5);
        for (int i = 5; i < 10; i++) {
            Intrebare intrebare = new Intrebare("Intrebare " + i + " ?",
                    "1)r1i" + i, "2)r2i" + i, "3)r3i" + i,
                    "1",
                    "Domeniu5");

            intrebariController.addNewIntrebare(intrebare);

        }
        beir2061MV.evaluator.model.Test test = intrebariController.createNewTest();
        assertEquals(5, test.getIntrebari().size());

    }

    @Test
    public void C_Test() throws DuplicateIntrebareException, InputValidationFailedException, NotAbleToCreateStatisticsException {
        //F03 - unit test
        addIntrebari(4);
        Intrebare intrebare = new Intrebare("Intrebare5 ?",
                "1)r1i7", "2)r2i7", "3)r3i7",
                "2",
                "Domeniu3");

        intrebariController.addNewIntrebare(intrebare);
        Statistica statistica = intrebariController.getStatistica();
        Map<String, Integer> intrebariDomenii = statistica.getIntrebariDomenii();
        assertEquals(4, intrebariDomenii.keySet().size());
        assertEquals(2, intrebariDomenii.get("Domeniu3").intValue());
        assertEquals(1, intrebariDomenii.get("Domeniu1").intValue());
        assertEquals(1, intrebariDomenii.get("Domeniu2").intValue());
        assertEquals(1, intrebariDomenii.get("Domeniu0").intValue());
    }

    @Test
    public void P_A_Test() throws DuplicateIntrebareException, InputValidationFailedException {
        for (int i = 0; i < 5; i++) {
            Intrebare intrebare = new Intrebare("Intrebare " + i + " ?",
                    "1)r1i" + i, "2)r2i" + i, "3)r3i" + i,
                    "1",
                    "Domeniu" + i);

            Intrebare intrebare1 = intrebariController.addNewIntrebare(intrebare);
            assertEquals(intrebare,intrebare1);
        }
    }

    @Test
    public void P_A_B_Test() throws DuplicateIntrebareException, InputValidationFailedException, NotAbleToCreateTestException {
        List<Intrebare> intrebariList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Intrebare intrebare = new Intrebare("Intrebare " + i + " ?",
                    "1)r1i" + i, "2)r2i" + i, "3)r3i" + i,
                    "1",
                    "Domeniu" + i);

            intrebariList.add(intrebare);
            Intrebare intrebare1 = intrebariController.addNewIntrebare(intrebare);
            assertEquals(intrebare,intrebare1);
        }


        beir2061MV.evaluator.model.Test test = intrebariController.createNewTest();
        for(Intrebare intrebare : intrebariList){
            assertTrue(test.getIntrebari().contains(intrebare));
        }
        assertEquals(5, test.getIntrebari().size());
    }

    @Test
    public void P_A_B_C_Test() throws DuplicateIntrebareException, InputValidationFailedException, NotAbleToCreateTestException, NotAbleToCreateStatisticsException {

        List<Intrebare> intrebariList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Intrebare intrebare = new Intrebare("Intrebare " + i + " ?",
                    "1)r1i" + i, "2)r2i" + i, "3)r3i" + i,
                    "1",
                    "Domeniu" + i);

            intrebariList.add(intrebare);
            Intrebare intrebare1 = intrebariController.addNewIntrebare(intrebare);
            assertEquals(intrebare,intrebare1);
        }

        beir2061MV.evaluator.model.Test test = intrebariController.createNewTest();
        for(Intrebare intrebare : intrebariList){
            assertTrue(test.getIntrebari().contains(intrebare));
        }
        assertEquals(5, test.getIntrebari().size());

        Statistica statistica = intrebariController.getStatistica();
        Map<String,Integer> intrebariDomenii = statistica.getIntrebariDomenii();
        assertEquals(5, intrebariDomenii.keySet().size());
        assertEquals(1, intrebariDomenii.get("Domeniu3").intValue());
        assertEquals(1, intrebariDomenii.get("Domeniu1").intValue());
        assertEquals(1, intrebariDomenii.get("Domeniu2").intValue());
        assertEquals(1, intrebariDomenii.get("Domeniu0").intValue());
    }

    private void addIntrebari(int n) throws DuplicateIntrebareException, InputValidationFailedException {
        for (int i = 0; i < n; i++) {
            Intrebare intrebare = new Intrebare("Intrebare " + i + " ?",
                    "1)r1i" + i, "2)r2i" + i, "3)r3i" + i,
                    "1",
                    "Domeniu" + i);

            intrebariController.addNewIntrebare(intrebare);
        }
    }
}
