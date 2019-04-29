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

import static org.junit.Assert.*;

public class IntrebariControllerTestECP {


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
    public void TC1_ECP() throws DuplicateIntrebareException, InputValidationFailedException {
        Intrebare intrebare1 = intrebariController.addNewIntrebare(intrebare);
        assertEquals(intrebare, intrebare1);
    }

    @Test
    public void TC2_ECP() throws DuplicateIntrebareException{
        intrebare.setEnunt("");
        try{
        intrebariController.addNewIntrebare(intrebare);}
        catch (InputValidationFailedException exc){
            assertEquals("Enuntul este vid!",exc.getMessage());
        }
    }

    @Test
    public void TC3_ECP() throws DuplicateIntrebareException{
        String longStr = "Care este ";
        longStr += CharBuffer.allocate(100).toString().replace('\0', '.') + "?";
        intrebare.setEnunt(longStr);
        try{
            intrebariController.addNewIntrebare(intrebare);}
        catch (InputValidationFailedException exc){
            assertEquals("Lungimea enuntului depaseste 100 de caractere!",exc.getMessage());
        }
    }

    @Test
    public void TC4_ECP() throws DuplicateIntrebareException{
        intrebare.setVarianta1("");
        try{
            intrebariController.addNewIntrebare(intrebare);}
        catch (InputValidationFailedException exc){
            assertEquals("Varianta1 este vida!",exc.getMessage());
        }
    }

    @Test
    public void TC5_ECP() throws DuplicateIntrebareException{
        String longStr = "1)";
        longStr += CharBuffer.allocate(60).toString().replace('\0', '.');
        intrebare.setVarianta1(longStr);
        try{
            intrebariController.addNewIntrebare(intrebare);}
        catch (InputValidationFailedException exc){
            assertEquals("Lungimea variantei1 depaseste 50 de caractere!",exc.getMessage());
        }
    }

    @Test
    public void TC6_ECP() throws DuplicateIntrebareException{
        intrebare.setEnunt("care este?");
        try{
            intrebariController.addNewIntrebare(intrebare);}
        catch (InputValidationFailedException exc){
            assertEquals("Prima litera din enunt nu e majuscula!",exc.getMessage());
        }
    }

    @Test
    public void TC7_ECP() throws DuplicateIntrebareException {
        intrebare.setVarianta1("1 abc");
        try{
            intrebariController.addNewIntrebare(intrebare);}
        catch (InputValidationFailedException exc){
            assertEquals("Varianta1 nu incepe cu '1)'!",exc.getMessage());
        }
    }


}