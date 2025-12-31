import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Objeto de um aluno com nome, matrícula e data de nascimento.
 * Esta classe contém métodos simples de acesso (getters) e um {@code toString}
 * formatado para exportar em CSV.
 *
 * @author Angelo
 */
public class Aluno implements Comparable<Aluno> {
    private final String nome;
    private final String matricula; // 5 dígitos
    private final LocalDate dataNascimento;

    private static final DateTimeFormatter CSV_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Construtor do aluno.
     *
     * @param nome nome completo
     * @param matricula matrícula formatada com 5 dígitos
     * @param dataNascimento data de nascimento
     */
    public Aluno(String nome, String matricula, LocalDate dataNascimento) {
        this.nome = nome;
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Retorna a linha no Excel do aluno selecionado: Nome,Matrícula,Data de Nascimento
     * (CSV é o tipo de arquivo)
     * @return linha no Excel
     */
    public String toCsvLine() {
        return String.format("%s,%s,%s", nome, matricula, dataNascimento.format(CSV_DATE));
    }

    @Override
    public String toString() {
        return "Aluno{" + "nome='" + nome + '\'' + ", matricula='" + matricula + '\'' + ", dataNascimento=" + dataNascimento + '}';
    }

    @Override
    public int compareTo(Aluno other) {
        return this.nome.compareToIgnoreCase(other.nome);
    }
}