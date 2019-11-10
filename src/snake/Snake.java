package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * <h1>Classe Snake</h1>
 * Classe do projeto que contem todos os metodos relacionados
 * ao jogo que sera executado. esta classe � um JPanel da JanelaPrincipal
 * <p>
 * @author  Grupo 6
 * @version 1.0
 * @since   2019-09-19
 */

public class Snake extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private final int WIDTH_ = 570;          // Tamanho do JPanel em Largura x Altura
	private final int HEIGHT_ = 500;
	private final int TAMANHO_PONTO = 10;    // Tamanho de cada ponto na tela  
	private static int PONTUA��O = 0;                                   // Contar pontua��o
	private String SCORE = "PONTUA��O: " + PONTUA��O;                    // Mensagem da pontua��o
	Font SCORE_FONT = new Font("Consolas", Font.BOLD, 14);       // Fonte para escrever a pontua��o, estilo da fonte
	FontMetrics SCORE_METRICA = this.getFontMetrics(SCORE_FONT); // Tamanho total da escrita na tela

	private final int RAND_POSICAO = 50;     // Um valor aleat�ria para gerar posi��o
	private final int DELAY = 60;            // Um delay para o tempo de execu��o do jogo
	private int[] x = new int[580]; // Defini��o do plano cartesiano (x,y) do jogo
	private int[] y = new int[500];

	private int pontos;                      // Pontos da cobrinha
	private int comida_x;                    // Posi��o (x) da  comida
	private int comida_y;                    // Posi��o (y) da  comida

	// Defini��o dos movimentos
	private boolean left  = false;
	private boolean right = false;
	private boolean up    = false;
	private boolean down  = false;

	private boolean estaJogando = true; // Denifi��o do status do jogo
	private Timer tempo;                // Tempo de execu��o do jogo

	// Imagens da cabe�a e corpo da cobrinha, e comida
	private Image bola;
	private Image comida;
	private Image cabe�a;

	/**
	 * <h2>Metodo construtor da classe</h2>
	 * Objeto criado em JanelaPrincipal (configuraJanela);
	 * Este metodo define os icones do jogo e inicializa o jogo.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 * @param jp referencia a janela principal
	 */

	public Snake (JanelaPrincipal jp) {
		jp.addKeyListener(new TAdapter());                   // Cria uma instru��o de teclado
		this.setBackground(Color.pink);                      // Seta o plano de fundo como cinza
		ImageIcon bola_ = new ImageIcon("bola.png");         // Cria um icone do arquivo png e seta na imagem correspondente
		bola = bola_.getImage(); 
		ImageIcon comida_ = new ImageIcon("comida.png");     // Cria um icone do arquivo png e seta na imagem correspondente
		comida = comida_.getImage();
		ImageIcon cabe�a_ = new ImageIcon("cabe�a.png");     // Cria um icone do arquivo png e seta na imagem correspondente
		cabe�a = cabe�a_.getImage();
		this.setSize(WIDTH_, HEIGHT_);   // Define o tamanho do JPanel
		initJogo();                      // Inicializa do jogo
	}

	/**
	 * <h2>Metodo initJogo</h2>
	 * Metodo utilizado pelo metodo construtor;
	 * Este metodo cria e posiciona a cobra na posicao inicial,
	 * gera a primeira comida e inicia o contador de tempo.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 */
	public void initJogo() {
		pontos = 3;                       // Define quantidade de pontos iniciais
		for (int i = 0; i < pontos; i++){ // Define a posi��o em (x,y) de cada ponto
			x[i] = 50 - i*10;
			y[i] = 50;
		}
		localComida();                  // Gera a primeira comida
		tempo = new Timer(DELAY, this); // Inicia o tempo de execu��o do jogo
		tempo.start();
	}

	/**
	 * <h2>Metodo paint</h2>
	 * Este metodo analisa se o jogo esta em andamento, se estiver desenha na tela,
	 *  se nao estiver, o jogo � dado como o fim.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 * @param g Permite fazer desenhos
	 */
	public void paint (Graphics g){      
		super.paint(g); 
		if (estaJogando){
			g.drawImage(comida, comida_x, comida_y, this); // Desenha a comida no plano (x,y) do jogo

			// Para cada ponto da cobrinha, desenha a cabe�a e o corpo
			// em (x,y)
			for (int i = 0; i < pontos; i++){
				if (i == 0) { g.drawImage(cabe�a, x[i], y[i], this); }
				else        { g.drawImage(bola, x[i], y[i], this); }
			}
			desenharPontuacao(g);               // Desenha a pontua��o na tela
			Toolkit.getDefaultToolkit().sync(); // Executa a sincronia de dados
			g.dispose();                        // Pausa os gr�ficos
		}
		else {   FimDeJogo(g);}                //Executa fim de jogo
	}

	/**
	 * <h2>Metodo desenharPontuacao</h2>
	 * Metodo utilizado por paint();
	 * Este metodo formata e exibe a string contendo a pontuacao do jogo.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 * @param g Permite desenhar a string
	 */
	public void desenharPontuacao (Graphics g) {
		SCORE = "PONTUA��O: " + PONTUA��O;                // Define a frase para escrever
		SCORE_METRICA = this.getFontMetrics(SCORE_FONT);  // Define a frase para escrever
		g.setColor(Color.blue);                          // Define a cor da fonte
		g.setFont(SCORE_FONT);                            // Seta a fonte para o gr�fico
		g.drawString(SCORE, (WIDTH_ - SCORE_METRICA.stringWidth(SCORE)) /2, HEIGHT_ -50);   // Desenha a fonte na tela
	}

	/**
	 * <h2>Metodo FimDeJogo</h2>
	 * Metodo utilizado quando o jogo e encerrado;
	 * Este metodo exibe a mensagem de fim de jogo com a pontuacao acumulada.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 * @param g permite desenhar a string
	 */
	public void FimDeJogo (Graphics g){
		String msg = "FIM DE JOGO!  SUA PONTUA��O( " + PONTUA��O + " )";                             // Define a frase para escrever
		Font pequena = new Font("Consolas", Font.BOLD, 14);      // Define o estilo da fonte
		FontMetrics metrica = this.getFontMetrics(pequena);      // Define o estilo da fonte
		g.setColor(Color.blue);                                                 // Define a cor da fonte
		g.setFont(pequena);                                                      // Seta a fonte para o gr�fico
		g.drawString(msg, (WIDTH_ - metrica.stringWidth(msg)) / 2, HEIGHT_ / 2); // Desenha a fonte na tela
	}

	/**
	 * <h2>Metodo checarComida</h2>
	 * Metodo utilizado por actionPerformed();
	 * Este metodo verifica se a cobra comer na mesma posi��o (x,y) ent�o aumenta o corpo do mesmo;
	 * aumenta a pontua��o e gera uma nova comida.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 */

	public void checarComida (){
		// Se ele comer na mesma posi��o (x,y) ent�o aumenta o corpo da cobrinha
		// aumenta a pontua��o e gera uma nova comida
		if ((x[0] == comida_x) && (y[0] == comida_y)){
			pontos++;
			PONTUA��O++;
			localComida();
		}
	}

	/**
	 * <h2>Metodo mover</h2>
	 * Metodo utilizado por actionPerformed();
	 * Este metodo acrescenta um TAMANHO_PONTO para a direcao em que esta movendo,
	 * e move sequencialmente todas os pontos que o corpo ocupa. Omite os pontos
	 * restantes, controlados pelo tamanho (pontos) do corpo.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 */
	public void mover (){
		for (int i = pontos; i > 0; i--){  // Para cada ponto da cobrinha desenha em (x,y)
			x[i] = x[(i - 1)];
			y[i] = y[(i - 1)];
		}
		if (left) { x[0] -= TAMANHO_PONTO;    } // Se for para esquerda decrementa em x
		if (right){ x[0] += TAMANHO_PONTO;    } // Se for para direita incrementa em x                 
		if (up)   { y[0] -= TAMANHO_PONTO;    } // Se for para cima decrementa em y
		if (down) { y[0] += TAMANHO_PONTO;    } // Se for para baixo incrementa em y

	}

	/**
	 * <h2>Metodo checarColisao</h2>
	 * Metodo utilizado por actionPerformed();
	 * Este metodo verifica se para cada ponto, este estasobreposto com outro ponto, ou se a cobrinha encostou nas bordas;
	 * se estiver, ele avista que o jogador parou de jogar devido a colisao.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 */
	public void checarColis�o (){
		// Para cada ponto, verifica se este est� em posi��o com outro ponto
		// se estiver ele avista que o jogador parou de jogar devido a colis�o
		for (int i = pontos; i > 0; i--){           
			if ((i > 4) && (x[0] == x[i]) && (y[0] == y[i]))  //i inicia com 3 pontos (tamanho da cobra)
			{ estaJogando = false; }

		}
		// Verifica se a cabe�a da cobrinha encostou em algum ponto (x,y)
		// nas bordas (width,height) da tela
		if (y[1] > HEIGHT_){ estaJogando = false; }
		if (y[1] < 0)      { estaJogando = false; }
		if (x[1] > WIDTH_) { estaJogando = false; }
		if (x[1] < 0)      { estaJogando = false; }
	}

	/**
	 * <h2>Metodo localComida</h2>
	 * Metodo utilizado por checarComida();
	 * Este metodo gera em uma coordenada (x,y) aleatoria a posicao de uma nova comida.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 */

	public void localComida () {
		int random = (int) (Math.random() * RAND_POSICAO); // Define um valor aleat�rio e atribui a uma posi��o x na tela para a  comida
		comida_x = (random * TAMANHO_PONTO);
		random = (int) (Math.random() * RAND_POSICAO);     // Define um valor aleat�rio e atribui a uma posi��o y na tela para a comida
		comida_y = (random * TAMANHO_PONTO); 
	}

	/**
	 * <h2>Metodo actionPerformed</h2>
	 * Metodo utilizado automaticamente ao interagir com alguma funcao;
	 * Este metodo verifica a situacao do jogo, move a cobrinha, e redesenha o campo de acordo com a a��o do jogador.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 * @param e evento que foi executado
	 */
	public void actionPerformed (ActionEvent e){
		/* Se estiver jogando ent�o j� realiza a checagem da comida, depois
	     verifica se existe colis�o, s� ent�o depois, realiza o movimento
	     da cobrinha no jogo, por fim, redesenha os resultados*/
		if (estaJogando){
			checarComida();
			checarColis�o();
			mover();
		}
		repaint();
	}

	/**
	 * <h1>Classe TAdapter</h1>
	 * Classe do projeto que analisao teclado para a movimentacao da cobrinha
	 * ao jogo que sera executado
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 */
	public class TAdapter extends KeyAdapter{  

		/**
		 * <h2>Metodo keyPressed</h2>
		 * Metodo utilizado automaticamente ao interagir com alguma funcao;
		 * Este metodo verifica e define a situacao booleana de qual botao foi pressionado.
		 * <p>
		 * @author  Grupo 6
		 * @version 1.0
		 * @since   2019-09-19
		 * @param e evento que foi executado
		 */
		public void keyPressed (KeyEvent e) {   // M�todo para verificar o que foi teclado
			int key =  e.getKeyCode();          // Obt�m o c�digo da tecla

			// Verifica os movimentos e manipula as vari�veis, para movimentar
			// corretamente sobre a tela
			if ((key == KeyEvent.VK_LEFT) && (!right)){
				left = true;
				up = false;
				down = false;
			}
			if ((key == KeyEvent.VK_RIGHT) && (!left)){
				right = true;
				up = false;
				down = false;
			}
			if ((key == KeyEvent.VK_UP) && (!down)){
				up = true;
				left = false;
				right = false;
			}
			if ((key == KeyEvent.VK_DOWN) && (!up)){
				down = true;
				left = false;
				right = false;
			}
		}
	}

	/**
	 * <h2>Metodo zeraPlacar</h2>
	 * Metodo utilizado na janela principal antes de gerar um novo jogo;
	 * Este metodo redefine a pontuacao para 0.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 */
	static void zeraPlacar(){
		PONTUA��O = 0;
	}
}
