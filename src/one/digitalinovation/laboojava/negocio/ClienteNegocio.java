package one.digitalinovation.laboojava.negocio;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.console.Start;
import one.digitalinovation.laboojava.entidade.Cliente;

import java.util.Optional;

/**
 * Classe para manipular a entidade {@link Cliente}.
 * @author thiago leite
 */
public class ClienteNegocio {

    /**
     * {@inheritDoc}.
     */
    private Banco bancoDados;

    /**
     * Construtor.
     * @param banco Banco de dados para ter acesso aos clientes cadastrados
     */
    public ClienteNegocio(Banco banco) {
        this.bancoDados = banco;
    }

    /**
     * Consulta o cliente pelo seu CPF.
     * @param cpf CPF de um cliente
     * @return O cliente que possuir o CPF passado.
     */

    public Optional<Cliente> consultar(String cpf) {
        return bancoDados.getClientes()
                .stream()
                .filter(n->n.getCpf().equals(cpf))
                .findFirst();
    }


    public void RemoverCliente (String cpf) {

        Optional<Cliente> cliente = bancoDados.getClientes()
                .stream().filter(n-> n.getCpf().equals(cpf))
                .findFirst();


            //dentro do if presente eh certeza que tem dados
            cliente.ifPresent(
                    n -> {
                        bancoDados.getClientes().remove(n);
                    });

        }


}


