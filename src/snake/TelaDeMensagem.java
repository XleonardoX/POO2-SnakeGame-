package snake;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * <h1>Classe TelaDeMensagem</h1>
 * Classe do projeto que contem todos os metodos relacionados
 * a construcao das janelas adicionais geradas pelos menus.
 * <p>
 * @author  Grupo 6
 * @version 1.0
 * @since   2019-09-19
 */

class TelaDeMensagem extends JDialog implements ActionListener{
	private static final long    serialVersionUID = 1L;
	private final JButton        botaoFecha;
	private final JPanel         painelTexto;
	private final JPanel         painelBotoes;
	private final JTextArea      areaTexto;

	/**
	 * <h2>Metodo construtor da classe</h2>
	 * Objeto criado em JanelaPrincipal (configuraJanela);
	 * Este metodo define o formato da janela das acoes dos itens de menu.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 * @param janelaPrincipal referencia a janela principal
	 * @param titulo titulo da janela 
	 * @param texto texto presente na janela de mensagem
	 */

	TelaDeMensagem(JFrame janelaPrincipal, String titulo, String texto) throws HeadlessException {
		super(janelaPrincipal, titulo);
		setSize(800, 300);
		setResizable(false);
		setLocationRelativeTo(janelaPrincipal);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

		areaTexto = new JTextArea();
		areaTexto.setText(texto);
		formatAreaTexto();

		painelTexto = new JPanel();
		painelTexto.setBackground(Color.pink);
		painelTexto.setBorder(new TitledBorder(new LineBorder(Color.gray), Sobre.nome));
		painelTexto.add(areaTexto);
		add(painelTexto, BorderLayout.CENTER);


		painelBotoes = new JPanel();
		botaoFecha = new JButton("Fecha");
		botaoFecha.addActionListener(this);
		painelBotoes.add(botaoFecha);
		add(painelBotoes, BorderLayout.SOUTH);
	}

	/**
	 * <h2>Metodo actionPerformed</h2>
	 * Metodo utilizado automaticamente ao interagir com alguma funcao;
	 * Este metodo define a  visibilidade da janela para false.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 * @param arg0 parametro
	 */
	public void actionPerformed(ActionEvent arg0){
		this.setVisible(false);
	}

	/**
	 * <h2>Metodo formatAreaTexto</h2>
	 * Utilizado pelo metodo construtor;
	 * Este metodo formata as configuracoes da janela de texto.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 */
	private void formatAreaTexto(){
		areaTexto.setPreferredSize(new Dimension(580, 220));
		areaTexto.setForeground(Color.black);
		areaTexto.setBackground(Color.pink);
		areaTexto.setEditable(false);
		areaTexto.setFocusable(false);
		areaTexto.setLineWrap(true);
		areaTexto.setWrapStyleWord(true);
		areaTexto.setFont(new Font("Arial", Font.BOLD, 12));
	}
}



