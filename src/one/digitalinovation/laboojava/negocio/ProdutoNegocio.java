package one.digitalinovation.laboojava.negocio;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.Caderno;
import one.digitalinovation.laboojava.entidade.Livro;
import one.digitalinovation.laboojava.entidade.Pedido;
import one.digitalinovation.laboojava.entidade.Produto;
import one.digitalinovation.laboojava.entidade.constantes.Material;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe para manipular a entidade {@link Produto}.
 * @author thiago leite
 */
public class ProdutoNegocio {

    /**
     * {@inheritDoc}.
     */
    private Banco bancoDados;

    /**
     * Construtor.
     *
     * @param banco Banco de dados para ter armazenar e ter acesso os produtos
     */
    public ProdutoNegocio(Banco banco) {
        this.bancoDados = banco;
    }

    /**
     * Salva um novo produto(livro ou caderno) na loja.
     *
     * @param novoProduto Livro ou caderno que pode ser vendido
     */
    public void salvar(Produto novoProduto) {

        String codigo = "PR%04d";
        codigo = String.format(codigo, bancoDados.getProdutos().length);
        novoProduto.setCodigo(codigo);

        boolean produtoRepetido = false;
        for (Produto produto : bancoDados.getProdutos()) {
            if (produto.getCodigo().equals(novoProduto.getCodigo()))  {
                produtoRepetido = true;
                System.out.println("Produto já cadastrado.");
                break;
            }
        }

        if (!produtoRepetido) {
            this.bancoDados.adicionarProduto(novoProduto);
            System.out.println("Produto cadastrado com sucesso.");
        }
    }

    /**
     * Exclui um produto pelo código de cadastro.
     *
     * @param codigo Código de cadastro do produto
     */
    public void excluir(String codigo) {

        int pedidoExclusao = -1;
         
        for (int i = 0; i <= bancoDados.getProdutos().length; i++) {
            Produto produto = bancoDados.getProdutos()[i];
            if (produto.getCodigo().equals(codigo)) {
                pedidoExclusao = i;
                break;
            }
        }

        if (pedidoExclusao != -1) {
            bancoDados.removerProduto(pedidoExclusao);
            System.out.println("Produto excluído com sucesso.");
        } else {
            System.out.println("Produto inexistente");
        }


    }

    /**
     * Obtem um produto a partir de seu código de cadastro.
     *
     * @param codigo Código de cadastro do produto
     * @return Optional indicando a existência ou não do Produto
     */
    public Optional<Produto> consultar(String codigo) {

        for (Produto produto : bancoDados.getProdutos()) {

            if (produto.getCodigo().equalsIgnoreCase(codigo)) {
                return Optional.of(produto);
            }
        }

        return Optional.empty();
    }

    /**
     * Lista todos os produtos cadastrados.
     */
    public void listarTodos() {

        if (bancoDados.getProdutos().length == 0) {
            System.out.println("Não existem produtos cadastrados");
        } else {

            for (Produto produto : bancoDados.getProdutos()) {
                System.out.println(produto.toString());
            }
        }
    }

    public void consultarCadernoPelaMateria(String materia) {
        Material material = Material.valueOf(materia);

        List<Produto> produtoList = List.of(bancoDados.getProdutos());
        List<Produto> cadernoPorMateria = produtoList.stream().filter(item -> item instanceof Caderno).toList();

        if (bancoDados.getProdutos().length==0){
            System.out.println("Não existem produtos cadastrados");
        } else {
            for (Produto produto : cadernoPorMateria) {
                if (produto instanceof Caderno caderno && caderno.getMaterial().equals(material)){
                    System.out.println(caderno);
                }
            }
        }
    }

    public void consultarLivroPorNome(String nome) {
        List<Produto> produtoList = List.of(bancoDados.getProdutos());
        List<Produto> livrosPorNome = produtoList.stream().filter(item -> item instanceof Livro).toList();

        if (bancoDados.getProdutos().length == 0) {
            System.out.println("Não existem produtos cadastrados");
        } else {
            for (Produto produto : livrosPorNome) {
                if (produto instanceof Livro livro && livro.getNome().equals(nome)){
                    System.out.println(livro);
                }
            }
        }
    }
}