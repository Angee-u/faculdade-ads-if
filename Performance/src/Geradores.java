import java.time.LocalDate;
import java.util.Random;

/**
 * Gera os alunos conforme os arrays, arrays esse que foram fornecidos pelo professor, serão usados para gerar "nomes aleatórios".
 */
public class Geradores {
    private static final String[] nomes = {"Ana", "João", "Carlos", "Maria", "Pedro", "Juliana", "Lucas", "Fernanda"};
    private static final String[] sobrenomes = {"Silva", "Souza", "Oliveira", "Costa", "Pereira", "Rodrigues", "Almeida", "Ferreira"};

    private final Random rnd;

    public Geradores() {
        this.rnd = new Random();
    }

    /**
     * Gera um nome completo aleatório combinando nome + sobrenome (tem 30% de chance de gerar composto também).
     */
    public String gerarNome() {
        String nome = nomes[rnd.nextInt(nomes.length)];
        String sobrenome = sobrenomes[rnd.nextInt(sobrenomes.length)];
        // 30% de chance de ter nome composto
        if (rnd.nextDouble() < 0.3) {
            String nome2 = nomes[rnd.nextInt(nomes.length)];
            return nome + " " + nome2 + " " + sobrenome;
        }
        return nome + " " + sobrenome;
    }

    /**
     * Gera matrícula com 5 dígitos (ex: 03452)
     */
    public String gerarMatricula() {
        int numero = rnd.nextInt(100_000); // 0 a 99.999
        return String.format("%05d", numero);
    }

    /**
     * Gera data de nascimento aleatória entre 01/01/1980 e 31/12/2006 (para adultos/estudantes em geral).
     */
    public LocalDate gerarDataNascimento() {
        int ano = 1980 + rnd.nextInt(27); //
        int mes = 1 + rnd.nextInt(12);
        int dia = 1 + rnd.nextInt(28);
        return LocalDate.of(ano, mes, dia);
    }

    /**
     * Gera um objeto Aluno com dados.
     *
     * @return novo Aluno
     */
    public Aluno gerarAluno() {
        return new Aluno(gerarNome(), gerarMatricula(), gerarDataNascimento());
    }
}