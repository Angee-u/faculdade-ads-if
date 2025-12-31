public class Gato extends Animal{
    String som = "Miau";
    String cor = "Preto e Branco";

    public Gato(String nomeAnimal, int legs) {
        super(nomeAnimal, legs);
    }

    // Methods

    public void catInfo() {
        System.out.println("O gato faz " + this.som + " e é dá cor " + this.cor);
    };

    public void deliveryFood() {

    };


}
