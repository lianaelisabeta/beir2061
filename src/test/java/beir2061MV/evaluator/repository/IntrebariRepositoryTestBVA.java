package beir2061MV.evaluator.repository;

import beir2061MV.evaluator.exception.DuplicateIntrebareException;
import beir2061MV.evaluator.exception.InputValidationFailedException;
import beir2061MV.evaluator.model.Intrebare;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.CharBuffer;

import static org.junit.Assert.assertTrue;


public class IntrebariRepositoryTestBVA {


    private IntrebariRepository intrebariRepository = new IntrebariRepository("test.txt");
    private Intrebare intrebare;

    @Before
    public void setUp() {

        intrebare = new Intrebare("Care este aria patratului?",
                "1)l*l", "2)l-l", "3)4*l",
                "1",
                "Geometrie");
    }

    @AfterClass
    public static void cleanFile() {

        try {
            PrintWriter pw = new PrintWriter("test.txt");
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = InputValidationFailedException.class)
    public void TC9_BVA() throws DuplicateIntrebareException, InputValidationFailedException {
        intrebare.setEnunt("C");
        intrebariRepository.addIntrebare(intrebare);
    }


    @Test
    public void TC10_BVA() throws DuplicateIntrebareException, InputValidationFailedException {
        String enunt = "Care ";
        enunt += CharBuffer.allocate(93).toString().replace('\0', '.') + "?";
        intrebare.setEnunt(enunt);
        intrebariRepository.addIntrebare(intrebare);

        assertTrue(intrebariRepository.getIntrebari().contains(intrebare));
    }

    @Test
    public void TC11_BVA() throws DuplicateIntrebareException, InputValidationFailedException {
        String enunt = "Care ";
        enunt += CharBuffer.allocate(94).toString().replace('\0', '.') + "?";
        intrebare.setEnunt(enunt);
        intrebariRepository.addIntrebare(intrebare);

        assertTrue(intrebariRepository.getIntrebari().contains(intrebare));
    }

    @Test(expected = InputValidationFailedException.class)
    public void TC12_BVA() throws DuplicateIntrebareException, InputValidationFailedException {
        String enunt = "Care ";
        enunt += CharBuffer.allocate(95).toString().replace('\0', '.') + "?";
        intrebare.setEnunt(enunt);
        intrebariRepository.addIntrebare(intrebare);
        assertTrue(intrebariRepository.getIntrebari().contains(intrebare));
    }

    @Test
    public void TC14_BVA() throws DuplicateIntrebareException, InputValidationFailedException {
        String raspuns1 = "1)";
        raspuns1 += CharBuffer.allocate(47).toString().replace('\0', '.');
        intrebare.setVarianta1(raspuns1);
        intrebariRepository.addIntrebare(intrebare);
        assertTrue(intrebariRepository.getIntrebari().contains(intrebare));
    }

    @Test
    public void TC15_BVA() throws DuplicateIntrebareException, InputValidationFailedException {
        String raspuns1 = "1)";
        raspuns1 += CharBuffer.allocate(48).toString().replace('\0', '.');
        intrebare.setVarianta1(raspuns1);
        intrebariRepository.addIntrebare(intrebare);
        assertTrue(intrebariRepository.getIntrebari().contains(intrebare));
    }

    @Test(expected = InputValidationFailedException.class)
    public void TC16_BVA() throws DuplicateIntrebareException, InputValidationFailedException {
        String raspuns1 = "1)";
        raspuns1 += CharBuffer.allocate(49).toString().replace('\0', '.');
        intrebare.setVarianta1(raspuns1);
        intrebariRepository.addIntrebare(intrebare);
    }
}