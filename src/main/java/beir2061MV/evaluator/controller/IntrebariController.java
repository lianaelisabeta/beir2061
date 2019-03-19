package beir2061MV.evaluator.controller;

import java.util.LinkedList;
import java.util.List;

import beir2061MV.evaluator.exception.InputValidationFailedException;
import beir2061MV.evaluator.model.Intrebare;
import beir2061MV.evaluator.model.Statistica;
import beir2061MV.evaluator.model.Test;
import beir2061MV.evaluator.repository.IntrebariRepository;
import beir2061MV.evaluator.exception.DuplicateIntrebareException;
import beir2061MV.evaluator.exception.NotAbleToCreateStatisticsException;
import beir2061MV.evaluator.exception.NotAbleToCreateTestException;

public class IntrebariController {

    private IntrebariRepository intrebariRepository;

    public IntrebariController(String filename) {
        intrebariRepository = new IntrebariRepository(filename);
    }

    public Intrebare addNewIntrebare(Intrebare intrebare) throws DuplicateIntrebareException, InputValidationFailedException {

        intrebariRepository.addIntrebare(intrebare);

        return intrebare;
    }

    public Test createNewTest() throws NotAbleToCreateTestException {

        if (intrebariRepository.getIntrebari().size() < 5)
            throw new NotAbleToCreateTestException("Nu exista suficiente intrebari pentru crearea unui test!(5)");

        if (intrebariRepository.getNumberOfDistinctDomains() < 5)
            throw new NotAbleToCreateTestException("Nu exista suficiente domenii pentru crearea unui test!(5)");

        List<Intrebare> testIntrebari = new LinkedList<Intrebare>();
        List<String> domenii = new LinkedList<String>();
        Intrebare intrebare;
        Test test = new Test();

        while (testIntrebari.size() < 5) {
            intrebare = intrebariRepository.pickRandomIntrebare();

            if (!domenii.contains(intrebare.getDomeniu())) {
                testIntrebari.add(intrebare);
                domenii.add(intrebare.getDomeniu());
            }
        }

        test.setIntrebari(testIntrebari);
        return test;
    }

    public Statistica getStatistica() throws NotAbleToCreateStatisticsException {

        if (intrebariRepository.getIntrebari().isEmpty())
            throw new NotAbleToCreateStatisticsException("Repository-ul nu contine nicio intrebare!");

        Statistica statistica = new Statistica();
        for (String domeniu : intrebariRepository.getDistinctDomains()) {
            statistica.add(domeniu, intrebariRepository.getNumberOfIntrebariByDomain(domeniu));
        }

        return statistica;
    }

}
