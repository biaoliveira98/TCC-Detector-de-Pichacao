import javax.swing.*;

public class TelaPrincipal extends JFrame {
    private JPanel mainPanel;
    private JPanel panelRequesicao;
    private JPanel usuarioSenha;
    private JButton OKButton;
    private JFormattedTextField textLocalizacao;
    private JPasswordField textSenha;
    private JFormattedTextField textUsuario;
    private JLabel labelUsuario;
    private JLabel labelSenha;
    private JLabel labelLocalizacao;
    private JButton OKButton1;
    private JPanel panelLocalizacao;
    private JComboBox comboBoxData;
    private JFormattedTextField textData;
    private JButton OKButton2;
    private JPanel panelData;
    private JPanel tipoEntradaPanel;
    private JComboBox comboBoxTipoEntradfa;
    private JFormattedTextField textTipoEntrada;
    private JButton OKButton3;
    private JButton enviarButton;
    private JPanel selecionarArquivoPanel;
    private JScrollPane listarFotosPanel;
    private JFormattedTextField formattedTextField1;
    private JButton OKButton4;
    private JLabel fotoLabel;
    private JPanel imagemUsuarioPanel;
    private JScrollBar scrollBar1;

    public TelaPrincipal(String title){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new TelaPrincipal("Instagram Graffiti Detector");
        frame.setVisible(true);


    }

}
