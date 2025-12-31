import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();

        try (Reader jsonLido = new FileReader("C:\\Users\\IFSul\\IdeaProjects\\Atividade4\\src\\pessoas.json")) {
            Type listType = new TypeToken<ArrayList<Pessoa>>() {}.getType();

            ArrayList<Pessoa> pessoas = gson.fromJson(jsonLido, listType);

            // Ordenando por universidade
            pessoas.sort(Comparator.comparing(p -> p.university));
            for(Pessoa p : pessoas) {
                System.out.println(p);
            }

            // Ordenando por nome
            pessoas.sort(Comparator.comparing(p -> p.first_name));
            for(Pessoa p : pessoas) {
                System.out.println(p);
            }

            // Ordenando por sobrenome
            pessoas.sort(Comparator.comparing(p -> p.last_name));

            try(FileWriter writer = new FileWriter("C:\\Users\\IFSul\\Atividade4\\pessoas_ordenadas.csv")) {
                BufferedWriter wri = new BufferedWriter(writer);
                writer.write("id,first_name,last_name,gender,university,birthdate,email\n");

                for (Pessoa p : pessoas) {
                    writer.write(p.id + "," +
                            p.first_name + "," +
                            p.last_name + "," +
                            p.gender + "," +
                            p.university + "," +
                            p.birthdate + "," +
                            p.email + "\n");
                }

                writer.close();
                wri.close();

                System.out.println("Arquivo CSV criado com sucesso!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}