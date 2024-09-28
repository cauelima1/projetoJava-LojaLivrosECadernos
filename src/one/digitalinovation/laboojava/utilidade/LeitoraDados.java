package one.digitalinovation.laboojava.utilidade;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.*;
import one.digitalinovation.laboojava.entidade.constantes.Genero;
import one.digitalinovation.laboojava.entidade.constantes.Material;
import one.digitalinovation.laboojava.negocio.ProdutoNegocio;

import java.util.Optional;
import java.util.Scanner;

/**
 * Classe utilitária para auxiliar na leitura de entradas de dados via teclado.
 * @author thiago leite
 */
public final class LeitoraDados {

	/**
	 * Classe do Java para manipular entradas via teclado.
	 */
	private static Scanner scanner;
	
	static {
		scanner = new Scanner(System.in);
	}

	/**
	 * Ler um dado específico
	 * @return Dado lido
	 */
	public static String lerDado() {

        return scanner.nextLine();
	}

	/**
	 * Ler os dados do livro a ser cadastrado.
	 * @return Um livro a partir dos dados de entrada
	 */
	public static Livro lerLivro() {

			System.out.println("Cadastrando livro...");
			Livro livro = new Livro();

			System.out.println("Informe o nome do livro:");
			String nome = lerDado();
			livro.setNome(nome);

			try {
				System.out.println("Informe o gênero: DRAMA, SUSPENSE, ROMANCE");
				String genero = lerDado();
				livro.setGenero(Genero.valueOf(genero.toUpperCase()));
			} catch (IllegalArgumentException e){
				System.out.println("Genero informado incorretamente");
			}

			System.out.println("Informe o preço(padrão 0.0)");
			String preco = lerDado();
			livro.setPreco(Double.parseDouble(preco));


		return livro;
	}
	/**
	 * Ler os dados do caderno a ser cadastrado.
	 * @return Um caderno a partir dos dados de entrada
	 */

	public static Caderno lerCaderno(){
		System.out.println("Cadastrando caderno...");
		Caderno caderno = new Caderno();


		System.out.println("Informe a quantidade de materias: (M2, M5 OU M10)");
		String materias = lerDado();
		caderno.setMaterial(Material.valueOf(materias.toUpperCase()));

		System.out.println("Informe o preço:");
		String preco = lerDado();
		caderno.setPreco(Double.parseDouble(preco));

		return caderno;
	}



	/**
	 * Ler os dados do pedido e retorna um objeto a partir destes.
	 * @return Um pedido a partir dos dados de entrada
	 */
	public static Pedido lerPedido(Banco banco) {

		ProdutoNegocio produtoNegocio = new ProdutoNegocio(banco);

		System.out.println("Cadastrando pedido...");
		Pedido pedido = new Pedido();

		String opcao = "s";
		do {

			System.out.println("Digite o código do produto(livro/Caderno)");
			String codigo = lerDado();

			Optional<Produto> resultado = produtoNegocio.consultar(codigo);
			if (resultado.isPresent()) {

				Produto produto = resultado.get();

				System.out.println("Digite a quantidade");
				String quantidade = lerDado();
				produto.setQuantidade(Integer.parseInt(quantidade));

				pedido.getProdutoList().add(produto);
			} else {
				System.out.println("Produto inexistente. Escolha um produto válido");
			}

			System.out.println("Deseja selecionar mais um produto? s/n");
			opcao = lerDado().toLowerCase();
		} while("s".equals(opcao));

		return pedido;
	}

	/**
	 * Ler os dados do cupom e retorna um objeto a partir destes.
	 * @return O cupom a partir dos dados de entrada
	 */
	public static Optional<Cupom> lerCupom(Banco banco) {

		System.out.println("Caso queira utilizar algum cupom escolha entre: CUPOM2, CUPOM5, CUPOM7. Se não desejar, deixe em branco.");

		String desconto = lerDado();

		for (Cupom cupom: banco.getCupons()) {
			if (cupom.getCodigo().equalsIgnoreCase(desconto)) {
				return Optional.of(cupom);
			}
		}

		return Optional.empty();
	}

}
