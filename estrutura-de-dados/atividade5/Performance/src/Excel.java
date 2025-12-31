import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Exporta listas de alunos em formato CSV.
 */
public class Excel {

    /**
     * Exporta a lista passada para o arquivo informado. O arquivo terá cabeçalho.
     *
     * @param lista lista de alunos ordenada
     * @param nomeArquivo caminho ou destino
     * @throws IOException se houver erro de escrita
     */
    public static void exportar(List<Aluno> lista, String nomeArquivo) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))) {
            bw.write("Nome,Matricula,Data de Nascimento");
            bw.newLine();
            for (Aluno a : lista) {
                bw.write(a.toCsvLine());
                bw.newLine();
            }
        }
    }
}
