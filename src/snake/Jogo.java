package snake;

import java.awt.HeadlessException;

/**
 * <h1>Classe Jogo</h1>
 * Classe do projeto que contem a funcao main. Ponto de partida de execução.
 * <p>
 * @author  Grupo 6
 * @version 1.0
 *  @since   2019-09-19
 */

public class Jogo{
	public static void main(String[] args){
		try{
			JanelaPrincipal programa = new JanelaPrincipal();
			programa.inicia();
		}catch (HeadlessException excecao){
			imprMsgErroETermina("Programa terminado por uma HeadlessException no metodo main()", excecao);
		}catch (Exception excecao){
			imprMsgErroETermina("Programa terminado por uma Generic Exception no metodo main()", excecao);
		}
	}

	/**
	 * <h2>Metodo imprMsgErroETermina</h2>
	 * Objeto utilizado por main();
	 * Este metodo exibe a mensagem e tipo de erro formatada.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 * @param mensagem A mensagem a ser formatada.
	 * @param ocorrencia O tipo de erro ocorrido.	
	 */

	private static void imprMsgErroETermina(String mensagem, Exception ocorrencia){
		System.out.println("Mensagem de erro:\t" + mensagem);
		System.out.println("Texto da excecao:\t" + ocorrencia.getMessage());
		System.exit(1);
	}
}
