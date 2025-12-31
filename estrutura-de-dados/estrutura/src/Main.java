public class Main {
    public static void main(String[] args) {

        Gato gatoUm = new Gato("Haku", 4);
        Urso ursoUm = new Urso("Peter", 4);
        Elefante elefanteUm = new Elefante("Davi", 4);

        System.out.println(gatoUm.name + " " + gatoUm.legs);
        gatoUm.catInfo();

        System.out.println(ursoUm);
        System.out.println(elefanteUm);
    }
}