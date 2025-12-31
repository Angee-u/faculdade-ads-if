import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * Programa principal que gera 350.000 alunos nas três tipos de listas (ArrayList, LinkedList e Vector),
 * sera organizado, exportado e seu tempo medido em ms.
 */

public class Main {
    private static final int quantAlunos = 350_000;

    public static void main(String[] args) {
        Geradores gerador = new Geradores();

        // Listas completas
        List<Aluno> arrayList = new ArrayList<>(quantAlunos);
        List<Aluno> linkedList = new LinkedList<>();
        List<Aluno> vector = new Vector<>(quantAlunos);

        System.out.println("Iniciando benchmark para " + quantAlunos + " alunos em cada estrutura...\n");

        // Cadastro
        long t0 = System.nanoTime();
        for (int i = 0; i < quantAlunos; i++) {
            arrayList.add(gerador.gerarAluno());
        }
        long t1 = System.nanoTime();

        long t2 = System.nanoTime();
        for (int i = 0; i < quantAlunos; i++) {
            linkedList.add(gerador.gerarAluno());
        }
        long t3 = System.nanoTime();

        long t4 = System.nanoTime();
        for (int i = 0; i < quantAlunos; i++) {
            vector.add(gerador.gerarAluno());
        }
        long t5 = System.nanoTime();

        long cadastroArrayMs = (t1 - t0) / 1_000_000;
        long cadastroLinkedMs = (t3 - t2) / 1_000_000;
        long cadastroVectorMs = (t5 - t4) / 1_000_000;

        System.out.println("Cadastro concluído.");

        // Ordenação
        long sortStart, sortEnd;

        sortStart = System.nanoTime();
        arrayList.sort(null); // usa Comparable (compareTo por nome)
        sortEnd = System.nanoTime();
        long ordenaArrayMs = (sortEnd - sortStart) / 1_000_000;

        sortStart = System.nanoTime();
        linkedList.sort(null);
        sortEnd = System.nanoTime();
        long ordenaLinkedMs = (sortEnd - sortStart) / 1_000_000;

        sortStart = System.nanoTime();
        vector.sort(null);
        sortEnd = System.nanoTime();
        long ordenaVectorMs = (sortEnd - sortStart) / 1_000_000;

        System.out.println("Ordenação concluída.");

        // Exportação
        long expStart, expEnd;
        try {
            expStart = System.nanoTime();
            Excel.exportar(arrayList, "alunos_arraylist.csv");
            expEnd = System.nanoTime();
            long exportArrayMs = (expEnd - expStart) / 1_000_000;

            expStart = System.nanoTime();
            Excel.exportar(linkedList, "alunos_linkedlist.csv");
            expEnd = System.nanoTime();
            long exportLinkedMs = (expEnd - expStart) / 1_000_000;

            expStart = System.nanoTime();
            Excel.exportar(vector, "alunos_vector.csv");
            expEnd = System.nanoTime();
            long exportVectorMs = (expEnd - expStart) / 1_000_000;

            // Resultadp
            System.out.println("\nResumo de tempos (milissegundos):\n");
            System.out.printf("%-15s %-15s %-15s %-15s\n", "Operação", "ArrayList (ms)", "LinkedList (ms)", "Vector (ms)");
            System.out.printf("%-15s %-15d %-15d %-15d\n", "Cadastro", cadastroArrayMs, cadastroLinkedMs, cadastroVectorMs);
            System.out.printf("%-15s %-15d %-15d %-15d\n", "Ordenação", ordenaArrayMs, ordenaLinkedMs, ordenaVectorMs);
            System.out.printf("%-15s %-15d %-15d %-15d\n", "Exportação", exportArrayMs, exportLinkedMs, exportVectorMs);

            System.out.println("\nArquivos gerados: alunos_arraylist.csv, alunos_linkedlist.csv, alunos_vector.csv");
        } catch (IOException e) {
            System.err.println("Erro ao exportar CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }
}