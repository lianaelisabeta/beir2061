package beir2061MV.evaluator.repository;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;


import beir2061MV.evaluator.exception.InputValidationFailedException;
import beir2061MV.evaluator.model.Intrebare;
import beir2061MV.evaluator.exception.DuplicateIntrebareException;
import beir2061MV.evaluator.validator.IntrebareValidator;

public class IntrebariRepository {

	private List<Intrebare> intrebari;
	private String filename;
	public IntrebariRepository(String filename) {
		this.filename = filename;
		setIntrebari(loadIntrebariFromFile());

	}
	
	public void addIntrebare(Intrebare i) throws DuplicateIntrebareException, InputValidationFailedException {
		IntrebareValidator.validateEnunt(i.getEnunt());
		IntrebareValidator.validateDomeniu(i.getDomeniu());
		IntrebareValidator.validateVarianta1(i.getVarianta1());
		IntrebareValidator.validateVarianta2(i.getVarianta2());
		IntrebareValidator.validateVarianta3(i.getVarianta3());
		IntrebareValidator.validateVariantaCorecta(i.getVariantaCorecta());

		if(exists(i))
			throw new DuplicateIntrebareException("Intrebarea deja exista!");
		intrebari.add(i);
		writeIntrebareToFile(i);
	}
	
	public boolean exists(Intrebare i){
		for(Intrebare intrebare : intrebari)
			if(intrebare.equals(i))
				return true;
		return false;
	}
	
	public Intrebare pickRandomIntrebare(){
		Random random = new Random();
		return intrebari.get(random.nextInt(intrebari.size()));
	}
	
	public int getNumberOfDistinctDomains(){
		return getDistinctDomains().size();
		
	}
	
	public Set<String> getDistinctDomains(){
		Set<String> domains = new TreeSet<String>();
		for(Intrebare intrebare : intrebari)
			domains.add(intrebare.getDomeniu());
		return domains;
	}
	
	public List<Intrebare> getIntrebariByDomain(String domain){
		List<Intrebare> intrebariByDomain = new LinkedList<Intrebare>();
		for(Intrebare intrebare : intrebari){
			if(intrebare.getDomeniu().equals(domain)){
				intrebariByDomain.add(intrebare);
			}
		}
		
		return intrebariByDomain;
	}
	
	public int getNumberOfIntrebariByDomain(String domain){
		return getIntrebariByDomain(domain).size();
	}
	
	public List<Intrebare> loadIntrebariFromFile(){
		
		List<Intrebare> intrebari = new LinkedList<Intrebare>();
		List<String> intrebareAux;
		Intrebare intrebare;

		try{
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = br.readLine();
			while(line != null){
				intrebareAux = new LinkedList<String>();
				while(!line.equals("##")){
					intrebareAux.add(line);
					line = br.readLine();
				}
				intrebare = new Intrebare();
				intrebare.setEnunt(intrebareAux.get(0));
				intrebare.setVarianta1(intrebareAux.get(1));
				intrebare.setVarianta2(intrebareAux.get(2));
				intrebare.setVarianta3(intrebareAux.get(3));
				intrebare.setVariantaCorecta(intrebareAux.get(4));
				intrebare.setDomeniu(intrebareAux.get(5));
				intrebari.add(intrebare);
				line = br.readLine();
			}
			br.close();
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
		return intrebari;
	}

	private void writeIntrebareToFile(Intrebare intrebare){
		try(FileWriter fw = new FileWriter(filename, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw))
		{
			out.write(intrebare.getEnunt() + "\n");
			out.write(intrebare.getVarianta1() + "\n");
			out.write(intrebare.getVarianta2() + "\n");
			out.write(intrebare.getVarianta3() + "\n");
			out.write(intrebare.getVariantaCorecta() + "\n");
			out.write(intrebare.getDomeniu() + "\n");
			out.write("##\n");

		}
		catch (IOException io){
			System.err.println(io.getMessage());
		}
	}
	
	public List<Intrebare> getIntrebari() {
		return intrebari;
	}

	public void setIntrebari(List<Intrebare> intrebari) {
		this.intrebari = intrebari;
	}
	
}
