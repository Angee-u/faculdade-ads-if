import java.util.*;

public class Main {

    static Scanner read = new Scanner(System.in);

    public static void main(String[] args) {
        int control;

        System.out.println("Aperte 1 - Adicionar pessoa" + " n/ Aperte 2 - Para sair");
        control = read.nextInt();

        if (control == 1) {
            addPerson();
        } else if (control == 2) {
            System.out.println("Sistema fechado");
        }
    }


    public static void addPerson() {

        pessoa pessoa1 = new pessoa();

        System.out.println("Escreva o nome:");
        String addName = read.nextLine();
    }
}