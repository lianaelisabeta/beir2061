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

import static org.junit.Assert.*;

public class IntrebariRepositoryTestECP {


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

    @Test
    public void TC1_ECP() throws DuplicateIntrebareException, InputValidationFailedException {
        intrebariRepository.addIntrebare(intrebare);
        assertTrue(intrebariRepository.getIntrebari().contains(intrebare));
    }

    @Test(expected = InputValidationFailedException.class)
    public void TC2_ECP() throws DuplicateIntrebareException, InputValidationFailedException {
        intrebare.setEnunt("");
        intrebariRepository.addIntrebare(intrebare);
    }

    @Test(expected = InputValidationFailedException.class)
    public void TC3_ECP() throws DuplicateIntrebareException, InputValidationFailedException {
        String longStr = "Care este ";
        longStr += CharBuffer.allocate(100).toString().replace('\0', '.') + "?";
        intrebare.setEnunt(longStr);
        intrebariRepository.addIntrebare(intrebare);
    }

    @Test(expected = InputValidationFailedException.class)
    public void TC4_ECP() throws DuplicateIntrebareException, InputValidationFailedException {
        intrebare.setVarianta1("");
        intrebariRepository.addIntrebare(intrebare);
    }

    @Test(expected = InputValidationFailedException.class)
    public void TC5_ECP() throws DuplicateIntrebareException, InputValidationFailedException {
        String longStr = "1)";
        longStr += CharBuffer.allocate(60).toString().replace('\0', '.');
        intrebare.setVarianta1(longStr);
        intrebariRepository.addIntrebare(intrebare);
    }

    @Test(expected = InputValidationFailedException.class)
    public void TC6_ECP() throws DuplicateIntrebareException, InputValidationFailedException {
        intrebare.setEnunt("care este?");
        intrebariRepository.addIntrebare(intrebare);
    }

    @Test(expected = InputValidationFailedException.class)
    public void TC7_ECP() throws DuplicateIntrebareException, InputValidationFailedException {
        intrebare.setVarianta1("1 abc");
        intrebariRepository.addIntrebare(intrebare);
    }


}