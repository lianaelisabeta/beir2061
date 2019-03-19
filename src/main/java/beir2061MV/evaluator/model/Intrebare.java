package beir2061MV.evaluator.model;

public class Intrebare {

    private String enunt;
    private String varianta1;
    private String varianta2;
    private String varianta3;
    private String variantaCorecta;
    private String domeniu;

    public Intrebare(){}

    public Intrebare(String enunt, String varianta1, String varianta2, String varianta3, String variantaCorecta, String domeniu) {
        this.enunt = enunt;
        this.varianta1 = varianta1;
        this.varianta2 = varianta2;
        this.varianta3 = varianta3;
        this.variantaCorecta = variantaCorecta;
        this.domeniu = domeniu;
    }

    public String getEnunt() {
        return enunt;
    }

    public void setEnunt(String enunt) {
        this.enunt = enunt;
    }

    public String getVarianta1() {
        return varianta1;
    }

    public void setVarianta1(String varianta1) {
        this.varianta1 = varianta1;
    }

    public String getVarianta2() {
        return varianta2;
    }

    public void setVarianta2(String varianta2) {
        this.varianta2 = varianta2;
    }

    public String getVariantaCorecta() {
        return variantaCorecta;
    }

    public void setVariantaCorecta(String variantaCorecta) {
        this.variantaCorecta = variantaCorecta;
    }

    public String getDomeniu() {
        return domeniu;
    }

    public void setDomeniu(String domeniu) {
        this.domeniu = domeniu;
    }

    public String getVarianta3() {
        return varianta3;
    }

    public void setVarianta3(String varianta3) {
        this.varianta3 = varianta3;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Intrebare) {
            Intrebare i = (Intrebare) obj;
            if (this.enunt.equals(i.getEnunt()) &&
                    this.varianta1.equals(i.getVarianta1()) &&
                    this.varianta2.equals(i.getVarianta2()) &&
                    this.variantaCorecta.equals(i.getVariantaCorecta()) &&
                    this.domeniu.equals(i.getDomeniu()) &&
                    this.varianta3.equals(i.getVarianta3()))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Intrebare{" +
                "enunt='" + enunt + '\'' +
                ", varianta1='" + varianta1 + '\'' +
                ", varianta2='" + varianta2 + '\'' +
                ", varianta3='" + varianta3 + '\'' +
                ", variantaCorecta='" + variantaCorecta + '\'' +
                ", domeniu='" + domeniu + '\'' +
                '}';
    }
}
