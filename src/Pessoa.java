public class Pessoa {
    int id;
    String first_name;
    String last_name;
    String email;
    String gender;
    String university;
    String birthdate;


    /*
    Tentando alocar os dados das pessoas diretamente na classe Pessoa, não sei se é o correto,
    mas é semelhante ao exemplo que vi na internet
     */





    /* Methods */

    public String getFullName() {
        String fullName = first_name + " " + last_name;
        return fullName;
    }

    public String getDescription() {
        return id + "," + first_name + "," + last_name + "," + email + " " + gender + " " + university + " " + birthdate;
    }

    public String getAge() {
        String age = "0";
        return age;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Nome: " + first_name +
                ", Sobrenome: " + last_name +
                ", Email: " + email +
                ", Gênero: " + gender +
                ", Universidade: " + university +
                ", Nascimento: " + birthdate;
    }
};
