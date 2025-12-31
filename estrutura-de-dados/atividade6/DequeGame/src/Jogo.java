import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Classe dá lógica do jogo
 * Contém as funções para inverter as palavras e também as letras.
 *
 * O resto como histórico está no JFrame
 */

// Demorei um pouco pra entender isso.


public class Jogo {

    /**
     * Inverte a ordem das palavras (no caso da frase inteira).
     *
     * @param palavras as palavras (frase) original
     * @return As palavras invertidas na frase
     */

    public static String inverterPalavras(String palavras) {

        String[] splitandoPalavra = palavras.split(" ");
        Deque<String> temporario = new ArrayDeque<>();

        for (String split : splitandoPalavra) {
            temporario.push(split);
        }

        StringBuilder fraseInvertida = new StringBuilder();

        while (!temporario.isEmpty()) {
            fraseInvertida.append(temporario.pop());
            if (!temporario.isEmpty()) {
                fraseInvertida.append(" ");
            }
        }

        return fraseInvertida.toString();
    }

    /**
     * Este inverte as letras das palavras de uma frase
     *
     * @param palavrasLetras palavras que terão letras invertidas
     * @return palavras com letras invertidas
     */

    public static String inverterLetras(String palavrasLetras) {
        String[] palavras = palavrasLetras.split(" ");
        StringBuilder letrasInvertidas = new StringBuilder();

        for (int i = 0; i < palavras.length; i++) {
            letrasInvertidas.append(new StringBuilder(palavras[i]).reverse());
            if (i < palavras.length - 1) letrasInvertidas.append(" ");
        }

        return letrasInvertidas.toString();
    }
}
