package snake;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * <h1>Classe JanelaPrincipal</h1>
 * Classe do projeto que contem todos os metodos relacionados
 * a janela que exibe o jogo: Paineis, Menus, Ouvintes, Acoes, etc.
 * <p>
 * @author  Grupo 6
 * @version 1.0
 * @since   2019-09-19
 */
public class JanelaPrincipal extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel            snake;
	private JPanel            painelStatus;
	private JLabel            labelStatus;
	private JMenuBar          menuBar;
	private JMenu             menuSair;
	private JMenu             menuInicio;
	private JMenu             menuSobre;
	private JMenuItem         menuItemSobre;
	private JMenuItem         menuItemSair;
	private JMenuItem         menuNovoJogo;
	private JMenuItem         menuInicioInformacoes;

	/**
	 * <h2>Metodo construtor da classe</h2>
	 * Objeto criado em Jogo.java (programa);
	 * Este metodo ativa todos os metodos para criacao da janela.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 */
	public JanelaPrincipal (){
		super(Sobre.getNomeVersao());
		configuraJanela();
		criaAdicionaMenu();
		adicionaOuvinteMenus(this);
		inicializaAdicionaComponentes();
	}

	/**
	 * <h2>Metodo configuraJanela</h2>
	 * Metodo utilizado por JanelaPrincipal();
	 * Este metodo adiciona um objeto da classe Snake e posiciona
	 * dentro da Janela Principal.
	 * Define tambem sua localização na tela, seu tamanho, impede o redisionamento e adiciona operações default
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 */
	private void configuraJanela() {
		this.snake = new Snake(this);
		this.add(snake);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a saida da aplicação
		this.setSize(600, 600);                              // Define o tamanho da janela
		this.setLocationRelativeTo(null);                    // A localização
		this.setTitle("Snake-Game (Grupo 6)");               // O titulo da janela
		this.setResizable(false);                            // Impede o redimensionamento da janela
		this.setVisible(true);    
	}

	/**
	 * <h2>Metodo inicializaAdicionaComponentes</h2>
	 * Metodo utilizado por JanelaPrincipal();
	 * Este metodo cria e posiciona a janela e o rotulo cinza
	 * no canto inferior da tela.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 */
	private void inicializaAdicionaComponentes(){
		this.painelStatus = new JPanel();
		this.labelStatus = new JLabel();
		this.painelStatus.add(labelStatus);
		this.painelStatus.setBackground(Color.gray);
		this.painelStatus.setBorder(BorderFactory.createEtchedBorder());
		this.add(painelStatus, BorderLayout.SOUTH);
	}

	/**
	 * <h2>Metodo setMsgStatus</h2>
	 * Metodo utilizado por inicia();
	 * Este metodo define a mensagem a ser exibida no rotulo inferior
	 * segundo parametro.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 * @param texto A frase a ser apresentada no rotulo.
	 */
	void setMsgStatus(String texto){
		this.labelStatus.setText(texto);
	}

	/**
	 * <h2>Metodo inicia</h2>
	 * Metodo utilizado pelo objeto programa;
	 * Este metodo define o parametro do metodo setMsgStatus()
	 * e faz a janela visivel.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 */
	void inicia(){
		this.setMsgStatus(Sobre.universidade);
		this.setVisible(true);
	}

	/**
	 * <h2>Metodo criaAdicionaMenu</h2>
	 * Metodo utilizado por JanelaPrincipal();
	 * Este metodo cria os menus (e seus sub items) e opcoes e mnemonicos na janela principal.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 */

	private void criaAdicionaMenu(){  
		menuInicio = new JMenu("Inicio");
		menuInicio.setMnemonic('I');
		menuNovoJogo = new JMenuItem("Novo Jogo");
		menuInicio.setMnemonic('N');
		menuInicio.add(menuNovoJogo);
		menuInicioInformacoes = new JMenuItem("Como Jogar");
		menuInicio.setMnemonic('C');
		menuInicio.add(menuInicioInformacoes);

		menuSobre = new JMenu("Sobre");
		menuSobre.setMnemonic('S');
		menuItemSobre = new JMenuItem("Creditos");
		menuItemSobre.setMnemonic('C');
		menuSobre.add(menuItemSobre);

		menuSair = new JMenu("Sair");
		menuSair.setMnemonic('S');
		menuItemSair = new JMenuItem("Sair do Jogo");
		menuItemSair.setMnemonic('S');
		menuSair.add(menuItemSair);

		menuBar = new JMenuBar();
		menuBar.add(menuInicio);
		menuBar.add(menuSobre);
		menuBar.add(menuSair);

		this.setJMenuBar(menuBar);
	}

	/**
	 * <h2>Metodo adicionaOuvinteMenus</h2>
	 * Metodo utilizado por JanelaPrincipal();
	 * Este metodo adiciona ouvintes para os menus.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 * @param ouvinte acao a ser ouvida
	 */
	void adicionaOuvinteMenus(ActionListener ouvinte){
		for (Component menuPrincipal : this.getJMenuBar().getComponents()){
			if (menuPrincipal instanceof JMenu){
				adicionaOuvinteItemMenu(ouvinte, (JMenu) menuPrincipal);
			}
		}
	}
	/**
	 * <h2>Metodo adicionaOuvinteItemMenu</h2>
	 * Metodo utilizado por adicionaOuvinteMenus();
	 * Este metodo adiciona ouvintes para os itens de menus.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 * @param ouvinte acao a ser ouvida
	 * @param menuPrincipal objeto de onde sao adquiridos os componentes
	 */

	private void adicionaOuvinteItemMenu(ActionListener ouvinte, JMenu menuPrincipal){
		for (Component alvo : menuPrincipal.getMenuComponents()){
			if (alvo instanceof JMenuItem){
				((JMenuItem) alvo).addActionListener(ouvinte);
			}
		}
	}

	/**
	 * <h2>Metodo actionPerformed</h2>
	 * Metodo utilizado automaticamente ao interagir com algum item de menu;
	 * Executa certa acao de acordo com o Item de menu escolhido.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 * @param evento o evento que foi executado
	 */
	public void actionPerformed(ActionEvent evento){
		if (evento.getSource() == menuItemSobre){
			mostraTelaMensagem(new TelaDeMensagem(this, "Sobre - " + Sobre.getNomeVersao(), Sobre.getTextoSobre()));
		}
		if (evento.getSource() == menuInicioInformacoes){
			mostraTelaMensagem(new TelaDeMensagem(this, "Informações - " + Sobre.getNomeVersao(), Sobre.getTextoInformacoes()));
		}
		if (evento.getSource() == menuItemSair){
			System.exit(NORMAL);
		}
		if (evento.getSource() == menuNovoJogo){
			closeJpanel ();
			this.snake = new Snake(this);
			this.add(snake);
		}
	}
	/**
	 * <h2>Metodo closeJpanel</h2>
	 * Metodo utilizado para excluir o Jpanel snake.;
	 * Este metodo remove os componentes do Jpanel e define sua visibilidade como false. Ele é chamado quando queremos iniciar um novo jogo a partir da opção do menu Inicio
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 */

	public void closeJpanel (){
		Snake.zeraPlacar();
		snake.invalidate();
		snake.setVisible(false);
		snake.removeAll();
		this.getContentPane().remove(snake);
	}

	/**
	 * <h2>Metodo mostraTelaMensagem</h2>
	 * 
	 * Este metodo define a visibilidade do objeto de tela de mensagem como true.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 * @param tela a tela de mensagem gerada
	 */

	private void mostraTelaMensagem(TelaDeMensagem tela){
		tela.setVisible(true);
	}

}




