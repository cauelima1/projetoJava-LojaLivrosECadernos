package one.digitalinovation.laboojava.entidade;

import one.digitalinovation.laboojava.entidade.constantes.Genero;

/**
 * Classe que representa um livro, qual é uma especialização de um produto da loja.
 * @author thiago leite
 */
public class Livro extends Produto {

    /**
     * Nome do livro.
     */
    private String nome;

    /**
     * Gênero do livro.
     */
    private Genero genero;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }


    public double CalcularFrete() {
        return (getPreco() * getQuantidade()) * (1 + genero.getFator());
    }



    public String toString() {
        return "Livro{" +
                "nome='" + nome + '\'' +
                ", genero=" + genero + '\'' +
                ", codigo='" + getCodigo() + '\'' +
                ", preço='" + getPreco() + '\'' +
                '}';
    }
}
