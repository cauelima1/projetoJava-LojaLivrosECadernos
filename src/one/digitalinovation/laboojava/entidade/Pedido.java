package one.digitalinovation.laboojava.entidade;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa a entidade pedido, qual é a compra dos produtos por um cliente.
 * @author thiago leite
 */
public class Pedido {
    private String codigo;
    private Cliente cliente;
    private List<Produto> produtoList;
    private double total;

    public Pedido() {
        this.produtoList = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Produto> getProdutoList() {
        return produtoList;
    }


    public void setTotal(double total) {
        this.total = total;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getProdutosComprados () {

        StringBuilder produtos = new StringBuilder();
        produtos.append("[");
        for (Produto produto : getProdutoList()) {
            produtos.append(produto.toString());
            produtos.append("Qdt:");
            produtos.append(produto.getQuantidade());
            produtos.append(" ");
        }
        produtos.append("]");

        return produtos.toString();
    }


    @Override
    public String toString() {
        return ("Pedido do cliente: "+ cliente.getNome() +"{" +
                "codigo='" + codigo + '\''+
                ", produtoList=" + getProdutosComprados() +
                ", total= " + total +
                '}');
    }
}
