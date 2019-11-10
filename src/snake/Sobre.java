package snake;

/**
 * <h1>Classe Sobre</h1>
 * Classe do projeto que contem as variaveis estaticas a serem usadas por todas as classes.
 * <p>
 * @author  Grupo 6
 * @version 1.0
 * @since   2019-09-19
 */

public class Sobre {
	public static final String autores      = "Grupo 6 - POO2 (Andrey, Leonardo, Isaque e Guilherme)";
	public static final String escola       = "FT - Faculdade de Tecnologia";
	public static final String universidade = "Unicamp - Universidade Estadual de Campinas";
	public static final String nome         = "Projeto 1 da Disciplina de POO2 (Jogo da Cobrinha)";
	public static final String versao       = "Ver. 1.0.0";

	/**
	 * <h2>Metodo getTextoSobre</h2>
	 * Este metodo cria uma string formatada utilizando os atributos da classe.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 * @return texto
	 */
	static String getTextoSobre(){
		final StringBuffer text = new StringBuffer();

		text.append("\n");
		text.append(universidade);
		text.append("\n");
		text.append(escola);
		text.append("\n");
		text.append(autores);
		text.append("\n");
		text.append(nome);
		text.append("\n");
		text.append(versao);
		text.append("\n");

		return (text.toString());
	}

	/**
	 * <h2>Metodo getTextoInformacoes</h2>
	 * Este metodo cria uma string formatada com as informacoes sobre o jogo.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 * @return texto
	 */

	static String getTextoInformacoes(){
		final StringBuffer text = new StringBuffer();

		text.append("\n");
		text.append("DESCRIÇÃO: Este é um projeto para disdiplina de programação Orientada a Objetos 2 " 
				+ "da Faculdade de Tecnologia da Unicamp. Jogue com a clássica cobrinha, coma maçãs e evite destruir sua criatura.");
		text.append("\n\n");
		text.append("JOGAR: Para iniciar um novo jogo va em Inicio >> Novo Jogo e comece a partida.");
		text.append("\n\n");
		text.append("COMO JOGAR: Movimente a cobra com os direcionais do teclado e tente evitar que ele encoste em seu "
				+ "próprio corpo ou nas bordas da tela.\n");
		text.append("\n\n\n");
		text.append("DIVIRTA-SE!!!");
		text.append("\n");

		return (text.toString());
	}

	/**
	 * <h2>Metodo getNomeVersao</h2>
	 * Este metodo cria uma string formatada que exibe o nome
	 * do jogo e a versao de forma padronizada.
	 * <p>
	 * @author  Grupo 6
	 * @version 1.0
	 * @since   2019-09-19
	 * @return versão
	 */
	static String getNomeVersao(){
		return (nome + " - " + versao);
	}
}
