package one.digitalinovation.laboojava.console;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.*;
import one.digitalinovation.laboojava.negocio.ClienteNegocio;
import one.digitalinovation.laboojava.negocio.PedidoNegocio;
import one.digitalinovation.laboojava.negocio.ProdutoNegocio;
import one.digitalinovation.laboojava.utilidade.LeitoraDados;
import java.util.Optional;

/**
 * Classe responsável por controlar a execução da aplicação.
 * @author thiago leite
 */
public class Start {

    private static Cliente clienteLogado = null;

    private static Banco banco = new Banco();

    private static ClienteNegocio clienteNegocio = new ClienteNegocio(banco);
    private static PedidoNegocio pedidoNegocio = new PedidoNegocio(banco);
    private static ProdutoNegocio produtoNegocio = new ProdutoNegocio(banco);


    public static void main(String[] args) {
        System.out.println("\nBem vindo ao sitema.\n");

        Start.menuInicial();

    }

    public static void menuInicial (){

        String escolha = "";

        while (true) {

            System.out.println("Menu Inicial");
            System.out.println("Selecione uma das opcoes abaixo: ");
            System.out.println("1 - Cadastrar um novo cliente");
            System.out.println("2 - Excluir algum cliente");
            System.out.println("3 - Consultar clientes cadastrados");
            System.out.println("4 - Login");
            System.out.println("5 - Desligar sistema");

            escolha = LeitoraDados.lerDado();
            switch (escolha) {
                case "1":
                    System.out.println("\nInforme o nome do cliente para cadastro:");
                    String nome = LeitoraDados.lerDado();
                    System.out.println("\nInforme o CPF do cliente com 11 digitos para cadastro:");
                    String cpfCadastro = LeitoraDados.lerDado();
                        if (cpfCadastro.length() != 11){
                            System.out.println("Informe o cpf corretamente com 11 digitos.");
                            menuInicial();
                        } else {
                            banco.adicionarCliente(nome, cpfCadastro);
                            System.out.println("Cliente: " + nome + " foi cadastrado com sucesso!");
                        }
                    break;
                case "2":
                    System.out.println("Informe o CPF do cliente para exclusao:");
                    cpfCadastro = LeitoraDados.lerDado();
                    if (clienteNegocio.consultar(cpfCadastro).isPresent()) {
                        System.out.println("Cliente " + clienteNegocio.consultar(cpfCadastro) + " removido com sucesso!");
                        clienteNegocio.RemoverCliente(cpfCadastro);
                    } else {
                        System.out.println("Cliente nao removido, favor verificar.");
                        menuInicial();
                    }
                    break;
                case "3":
                    System.out.println(banco.getClientes());
                    break;
                case "4":
                    menu();
                    break;
                case "5":
                    System.out.println("\nDesligando sistema...");
                    System.exit(0);
            }
        }
    }

    public static void menu() {
            while (true) {
                {
                    if (clienteLogado == null) {
                        System.out.println("Digite o CPF para efetuar login:");
                        String cpfLogin = LeitoraDados.lerDado();
                        identificarUsuario(cpfLogin);
                    }

                    System.out.println("### SISTEMA INICIADO ###");
                    System.out.println("Selecione uma opção:");
                    System.out.println("-----------------");
                    System.out.println("1 - Cadastrar Livro");
                    System.out.println("2 - Excluir Livro");
                    System.out.println("3 - Cadastrar Caderno");
                    System.out.println("4 - Consultar Livro por nome");
                    System.out.println("5 - Consultar Caderno por materia");
                    System.out.println("6 - Consultar Pedido por codigo");
                    System.out.println("7 - Excluir Caderno");
                    System.out.println("8 - Fazer pedido");
                    System.out.println("9 - Excluir pedido");
                    System.out.println("10 - Listar produtos");
                    System.out.println("11 - Listar pedidos");
                    System.out.println("12 - Deslogar");
                    System.out.println("13 - Sair");

                    String opcao = LeitoraDados.lerDado();

                    switch (opcao) {
                        case "1":
                            Livro livro = LeitoraDados.lerLivro();
                            produtoNegocio.salvar(livro);
                            break;
                        case "2":
                            System.out.println("Digite o código do livro");
                            String codigoLivro = LeitoraDados.lerDado();
                            produtoNegocio.excluir(codigoLivro);
                            break;
                        case "3":
                            Caderno caderno = LeitoraDados.lerCaderno();
                            produtoNegocio.salvar(caderno);
                            break;
                        case "6":
                            System.out.println("Digite o codigo do pedido para consulta: ");
                            String codigo = LeitoraDados.lerDado();
                            pedidoNegocio.consultarPorCodigo(codigo);
                            break;
                        case "7":
                            System.out.println("Digite o código do caderno");
                            String codigoCaderno = LeitoraDados.lerDado();
                            produtoNegocio.excluir(codigoCaderno);
                            break;

                        case "8":
                            Pedido pedido = LeitoraDados.lerPedido(banco);
                            Optional<Cupom> cupom = LeitoraDados.lerCupom(banco);

                            if (cupom.isPresent()) {
                                pedidoNegocio.salvar(pedido, cupom.get());
                            } else {
                                pedidoNegocio.salvar(pedido);
                            }
                            break;
                        case "9":
                            System.out.println("Digite o código do pedido");
                            String codigoPedido = LeitoraDados.lerDado();
                            pedidoNegocio.excluir(codigoPedido);
                            break;
                        case "10":
                            System.out.println("Listando todos produtos...");
                            produtoNegocio.listarTodos();
                            break;
                        case "11":
                            System.out.println("Listando todos pedidos...");
                            pedidoNegocio.listarTodos();
                            break;
                        case "12":
                            System.out.println(String.format("Volte sempre %s!", clienteLogado.getNome()));
                            clienteLogado = null;
                            menuInicial();
                            break;
                        case "13":
                            System.out.println("Aplicação encerrada.");
                            System.exit(0);
                            break;
                        case "4":
                            System.out.println("Digite o nome do livro: ");
                            String nomeLivro = LeitoraDados.lerDado();
                            produtoNegocio.consultarLivroPorNome(nomeLivro);
                            break;
                        case "5":
                            System.out.println("Digite o codigo do caderno: ");
                            String materia = LeitoraDados.lerDado().toUpperCase();
                            produtoNegocio.consultarCadernoPelaMateria(materia);
                            break;
                        default:
                            System.out.println("Opção inválida.");
                            menu();
                            break;
                    }
                }

            }
        }


        private static void identificarUsuario (String cpf){

            Optional<Cliente> resultado = clienteNegocio.consultar(cpf);

            if (resultado.isPresent()) {
                Cliente cliente = resultado.get();
                System.out.printf("Olá %s! Você está logado.%n", cliente.getNome());
                clienteLogado = cliente;
            } else {
                System.out.println("Usuário não cadastrado.");
                Start.menu();

            }
        }
    }





