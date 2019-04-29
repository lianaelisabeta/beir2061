package beir2061MV.evaluator.controller;

import beir2061MV.evaluator.controller.IntrebariController;
import beir2061MV.evaluator.exception.DuplicateIntrebareException;
import beir2061MV.evaluator.exception.InputValidationFailedException;
import beir2061MV.evaluator.model.Intrebare;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.CharBuffer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class IntrebariControllerTestBVA {


    //private IntrebariRepository intrebariRepository = new IntrebariRepository("test.txt");
    private IntrebariController intrebariController = new IntrebariController("test.txt");
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
    public void TC9_BVA() throws DuplicateIntrebareException{
        intrebare.setEnunt("C");
        try{
            intrebariController.addNewIntrebare(intrebare);}
        catch (InputValidationFailedException exc){
            assertEquals("Ultimul caracter din enunt nu e '?'!",exc.getMessage());
        }
    }


    @Test
    public void TC10_BVA() throws DuplicateIntrebareException, InputValidationFailedException {
        String enunt = "Care ";
        enunt += CharBuffer.allocate(93).toString().replace('\0', '.') + "?";
        intrebare.setEnunt(enunt);
        Intrebare intrebare1 = intrebariController.addNewIntrebare(intrebare);

        assertEquals(intrebare, intrebare1);
    }

    @Test
    public void TC11_BVA() throws DuplicateIntrebareException, InputValidationFailedException {
        String enunt = "Care ";
        enunt += CharBuffer.allocate(94).toString().replace('\0', '.') + "?";
        intrebare.setEnunt(enunt);
        Intrebare intrebare1 = intrebariController.addNewIntrebare(intrebare);

        assertEquals(intrebare, intrebare1);
    }

    @Test
    public void TC12_BVA() throws DuplicateIntrebareException{
        String enunt = "Care ";
        enunt += CharBuffer.allocate(95).toString().replace('\0', '.') + "?";
        intrebare.setEnunt(enunt);
        try{
            intrebariController.addNewIntrebare(intrebare);}
        catch (InputValidationFailedException exc){
            assertEquals("Lungimea enuntului depaseste 100 de caractere!",exc.getMessage());
        }
    }

    @Test
    public void TC14_BVA() throws DuplicateIntrebareException, InputValidationFailedException {
        String raspuns1 = "1)";
        raspuns1 += CharBuffer.allocate(47).toString().replace('\0', '.');
        intrebare.setVarianta1(raspuns1);
        Intrebare intrebare1 = intrebariController.addNewIntrebare(intrebare);
        assertEquals(intrebare, intrebare1);
    }

    @Test
    public void TC15_BVA() throws DuplicateIntrebareException, InputValidationFailedException {
        String raspuns1 = "1)";
        raspuns1 += CharBuffer.allocate(48).toString().replace('\0', '.');
        intrebare.setVarianta1(raspuns1);
        Intrebare intrebare1 = intrebariController.addNewIntrebare(intrebare);
        assertEquals(intrebare, intrebare1);
    }

    @Test
    public void TC16_BVA() throws DuplicateIntrebareException {
        String raspuns1 = "1)";
        raspuns1 += CharBuffer.allocate(49).toString().replace('\0', '.');
        intrebare.setVarianta1(raspuns1);
        try{
            intrebariController.addNewIntrebare(intrebare);}
        catch (InputValidationFailedException exc){
            assertEquals("Lungimea variantei1 depaseste 50 de caractere!",exc.getMessage());
        }
    }
}