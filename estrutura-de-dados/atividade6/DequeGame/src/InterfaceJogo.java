import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * INTERFACE DO JOGO - MENU PARA JOGAR
 * É um jogo de inverter palavras ou letras
 *
 * Utilizando recursos do JFrame para entrada e saída por TEXT AREA, já as ações são realizadas por BUTTONS
 */

public class InterfaceJogo extends JFrame {


    /**
     * Instâncias necessárias para a criação e funcionamento do jogo
     * Nomes dados são auto explicativos
     *
     * - Entrada receberá algo
     * - Saída mostrará algo
     *
     * - Deque e List tem suas funções como históricos limitados, pelo "maxHistorico"
     */

    private JTextArea textAreaEntrada;
    private JTextArea textAreaSaida;
    private Deque<String> historicoInvertidos;
    private List<String> frasesOriginais;
    private final int maxHistorico = 5;

    public InterfaceJogo() {
        super("Jogo de Inverter Palavras");

        // Janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 500);
        setLocationRelativeTo(null); // centraliza
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 245, 245));

        historicoInvertidos = new ArrayDeque<>();
        frasesOriginais = new ArrayList<>();

        // Entrada
        JPanel painelEntrada = new JPanel(new BorderLayout());
        painelEntrada.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelEntrada.setBackground(new Color(245, 245, 245));

        JLabel labelEntrada = new JLabel("Digite sua frase:");
        labelEntrada.setFont(new Font("Arial", Font.BOLD, 14));
        painelEntrada.add(labelEntrada, BorderLayout.NORTH);

        textAreaEntrada = new JTextArea(3, 50);
        textAreaEntrada.setLineWrap(true);
        textAreaEntrada.setWrapStyleWord(true);
        textAreaEntrada.setFont(new Font("Arial", Font.PLAIN, 14));
        textAreaEntrada.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        painelEntrada.add(new JScrollPane(textAreaEntrada), BorderLayout.CENTER);

        add(painelEntrada, BorderLayout.NORTH);

        // Botões
        JPanel painelBotoes = new JPanel(new GridLayout(1, 5, 10, 10));
        painelBotoes.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelBotoes.setBackground(new Color(245, 245, 245));

        JButton btnInverter = criarBotao("Inverter frase", new Color(102, 205, 170));
        JButton btnHistoricoOriginais = criarBotao("Frases originais", new Color(135, 206, 250));
        JButton btnDesfazer = criarBotao("Desfazer", new Color(255, 182, 193));
        JButton btnInverterLetras = criarBotao("Inverter letras", new Color(255, 165, 0));
        JButton btnSair = criarBotao("Sair", new Color(220, 20, 60));

        painelBotoes.add(btnInverter);
        painelBotoes.add(btnHistoricoOriginais);
        painelBotoes.add(btnDesfazer);
        painelBotoes.add(btnInverterLetras);
        painelBotoes.add(btnSair);
        add(painelBotoes, BorderLayout.CENTER);

        // Saídas
        textAreaSaida = new JTextArea(10, 50);
        textAreaSaida.setEditable(false);
        textAreaSaida.setLineWrap(true);
        textAreaSaida.setWrapStyleWord(true);
        textAreaSaida.setFont(new Font("Arial", Font.PLAIN, 14));
        textAreaSaida.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        textAreaSaida.setBackground(new Color(250, 250, 250));

        JPanel painelSaida = new JPanel(new BorderLayout());
        painelSaida.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelSaida.setBackground(new Color(245, 245, 245));
        painelSaida.add(new JScrollPane(textAreaSaida), BorderLayout.CENTER);

        add(painelSaida, BorderLayout.SOUTH);

        // Ações dos botões - Basicamente o que cada um fará ao ser clicado
        // Configuração mais abaixo
        configurarAcoes(btnInverter, btnHistoricoOriginais, btnDesfazer, btnInverterLetras, btnSair);
    }

    // Personalização botões
    private JButton criarBotao(String texto, Color corFundo) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 12));
        botao.setBackground(corFundo);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        return botao;
    }

    /**
     * Configuração das ações dos botões da interface
     *
     * @param btnInverter Inverte palavras
     * @param btnHistoricoOriginais Mostra frases originais
     * @param btnDesfazer Desfaz última inversão
     * @param btnInverterLetras Inverte letras das palavras
     * @param btnSair Sai do programa
     *
     * Como eu disse, nomes são auto explicativos
     */

    private void configurarAcoes(JButton btnInverter, JButton btnHistoricoOriginais, JButton btnDesfazer,
                                 JButton btnInverterLetras, JButton btnSair) {

        // Inverter frase
        btnInverter.addActionListener(e -> {
            String fraseOriginal = textAreaEntrada.getText().trim();
            if (fraseOriginal.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite uma frase antes de inverter!");
                return;
            }
            String fraseInvertida = Jogo.inverterPalavras(fraseOriginal);
            adicionarHistorico(fraseInvertida);
            frasesOriginais.add(fraseOriginal);
            textAreaSaida.setText("Original: " + fraseOriginal + "\nInvertida: " + fraseInvertida);
        });

        // Ver frases originais
        btnHistoricoOriginais.addActionListener(e -> {
            if (frasesOriginais.isEmpty()) {
                textAreaSaida.setText("Não há frases cadastradas.");
                return;
            }
            List<String> ordenadas = new ArrayList<>(frasesOriginais);
            Collections.sort(ordenadas);
            textAreaSaida.setText("Frases originais (ordem alfabética):\n" + String.join("\n", ordenadas));
        });

        // Desfazer última inversão
        btnDesfazer.addActionListener(e -> {
            if (!historicoInvertidos.isEmpty()) {
                historicoInvertidos.pop();
                textAreaSaida.setText("Última inversão desfeita!\nHistórico atual:\n" + String.join("\n", historicoInvertidos));
            } else {
                textAreaSaida.setText("Não há histórico para desfazer.");
            }
        });

        // Inverter letras
        btnInverterLetras.addActionListener(e -> {
            String fraseOriginal = textAreaEntrada.getText().trim();
            if (fraseOriginal.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite uma frase antes de inverter letras!");
                return;
            }
            String invertidoLetras = Jogo.inverterLetras(fraseOriginal);
            adicionarHistorico(invertidoLetras);
            frasesOriginais.add(fraseOriginal);
            textAreaSaida.setText("Original: " + fraseOriginal + "\nLetras invertidas: " + invertidoLetras);
        });

        // Sair desgrama
        btnSair.addActionListener(e -> System.exit(0));
    }

    private void adicionarHistorico(String frase) {
        if (historicoInvertidos.size() == maxHistorico) {
            historicoInvertidos.removeLast();
        }
        historicoInvertidos.push(frase);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfaceJogo frame = new InterfaceJogo();
            frame.setVisible(true);
        });
    }
}
