import model.Cliente;
import model.Movimentacao;
import model.TipoMovimentacao;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcaoEscolha;
        List<Cliente> clientes = new ArrayList<>();
        List<Movimentacao> movimentacaoList = new ArrayList<>();

        sistemaPrincipal(scanner, clientes, movimentacaoList);
    }

    private static void sistemaPrincipal(Scanner scanner, List<Cliente> clientes, List<Movimentacao> movimentacaoList) {
        int opcaoEscolha;
        do {
            System.out.println("Sistema de Financeira - Menu Inicial");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Movimentação - Necessário informar um cliente");
            System.out.println("3. Imprimir Todos os Clientes");
            System.out.println("4. Sair do Sistema");
            System.out.print("Escolha uma opção: ");

            opcaoEscolha = scanner.nextInt();

            switch (opcaoEscolha) {
                case 1 -> cadastrarCliente(scanner, clientes, movimentacaoList);
                case 2 -> cadastrarMovimentacoes(scanner, movimentacaoList, clientes);
                case 3 -> imprimirListaDeClientes(clientes);
                case 4 -> {
                    sairDoSistema(scanner);
                }
                default -> System.out.println("Opção inválida. Escolha uma opção válida.");
            }

        } while (opcaoEscolha != 4);

        scanner.close();
    }

    private static void sairDoSistema(Scanner scanner) {
        System.out.println("Saindo do sistema.");
        scanner.close();
        System.exit(0);
        return;
    }

    private static void voltarAoMenu(Scanner scanner) {
        System.out.println("Voltando ao menu do sistema.");
        scanner.close();
        System.exit(0);
        return;
    }

    private static void cadastrarCliente(Scanner scanner, List<Cliente> clientes, List<Movimentacao> movimentacaoList) {

        String deveContinuar = null;
        Scanner scannerCliente = new Scanner(System.in);
        do {
            System.out.print("Digite o nome do cliente: ");
            String nome = scannerCliente.nextLine();
            System.out.print("Digite o cpf do cliente: ");
            String cpf = scannerCliente.nextLine();

            Random random = new Random();

            int randomInt = random.nextInt(100);

            Cliente cliente = new Cliente(randomInt, nome, cpf, true);
            clientes.add(cliente);

            System.out.println("Cliente cadastrado com sucesso!");

            System.out.print("Deseja cadastrar mais um cliente? (SIM/NAO): ");
            deveContinuar = scannerCliente.next().toUpperCase();

        }while (deveContinuar.equals("SIM"));

        sistemaPrincipal(scanner, clientes, movimentacaoList);
    }

    private static void cadastrarMovimentacoes(Scanner scanner, List<Movimentacao> movimentacaoList, List<Cliente> clientes) {

        String deveContinuar = null;
        double valorMovimentacao;
        double valorTotalConta;
        Scanner scannerMovimentacao = new Scanner(System.in);
        do {
            System.out.print("Digite o tipo de movimentação - DEBITO/CREDITO");
            String tipoMovimentacao = scannerMovimentacao.nextLine();
            System.out.print("Digite o valor da movimentação: ");
            valorMovimentacao = Double.parseDouble(scannerMovimentacao.nextLine());

            System.out.print("Digite o valor inicial da conta: ");
            valorTotalConta = Double.parseDouble(scannerMovimentacao.nextLine());



            Random random = new Random();
            int randomInt = random.nextInt(100);

            System.out.println("Digite o nome do cliente: ");
            String nomeCliente = scannerMovimentacao.nextLine();

            Cliente cliente = new Cliente();
            for (Cliente cliente1 : clientes) {
                if (cliente1.getNome().equals(nomeCliente)) {
                    cliente = cliente1;
                }else {
                    throw new RuntimeException("Cliente nao encontrado para o nome informado");
                }
            }

            if (Objects.equals(tipoMovimentacao, "DEBITO")) {
                tipoMovimentacao = TipoMovimentacao.DEBITO.toString();
                if (valorMovimentacao > valorTotalConta) {
                    throw new RuntimeException("Valor da movimentação deve ser maior que o valor inicial da conta");
                }
                if (valorMovimentacao < 0) {
                    throw new RuntimeException("Valor da movimentação deve ser maior que 0");
                }
                valorTotalConta = valorTotalConta - valorMovimentacao;

            }

            if (Objects.equals(tipoMovimentacao, "CREDITO")) {
                tipoMovimentacao = TipoMovimentacao.CREDITO.toString();
                if (valorMovimentacao < 0) {
                    throw new RuntimeException("Valor da movimentação deve ser maior que 0");
                }
                valorTotalConta = valorTotalConta + valorMovimentacao;

            }


            Movimentacao movimentacao = new Movimentacao(randomInt, cliente, tipoMovimentacao, valorMovimentacao, valorTotalConta);


            movimentacaoList.add(movimentacao);

            System.out.println("Movimentação cadastrada com sucesso!");

            System.out.print("Deseja cadastrar mais uma movimentação? (SIM/NAO): ");
            deveContinuar = scannerMovimentacao.next().toUpperCase();

        }while (deveContinuar.equals("SIM"));

        sistemaPrincipal(scanner, clientes, movimentacaoList);
    }



    private static void imprimirListaDeClientes(List<Cliente> clientes) {
        clientes.forEach(x -> {
            System.out.println("========================= CLIENTE ==========");
            System.out.println("Nome := " + x.getNome());
            System.out.println("CPF := " + x.getCpf());
            System.out.println("Status := " + x.getAtivo());
            System.out.println("========================================");
            System.out.println("");

        });
    }
    private static void imprimirListaDeMovimentacoes(List<Movimentacao> movimentacaoList) {
        movimentacaoList.forEach(x -> {
            System.out.println("========================= TODAS MOVIMENTACOES ==========");
            System.out.println("Nome := " + x.getCliente().getNome());
            System.out.println("CPF := " + x.getCliente().getCpf());
            System.out.println("Valor da movimentacao:= " + x.getValor());
            System.out.println("Tipo da movimentacao := " + x.getTipoMovimentacao());
            System.out.println("Saldo total := " + x.getSaldoTotal());
            System.out.println("========================================");
            System.out.println("");

        });
    }


}