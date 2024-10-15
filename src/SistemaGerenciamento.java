import clinica.*; 
import evento.*;
import restaurante.*;

import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;

public class SistemaGerenciamento {
    //associar valores 
    private static HashSet<Integer> sistemasUsados = new HashSet<>(); // funcionalidade adicional Para rastrear os sistemas usados 
    private static HashMap<String, Integer> popularidadeMedico = new HashMap<>(); // qual medico é mais procurado
    private static HashMap<String, Integer> popularidadeItens = new HashMap<>(); // qual item do menu é mais procurado
    private static HashMap<String, Integer> popularidadeEventos = new HashMap<>(); // qual evento mais é procurado
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEscolha o sistema de gerenciamento:");
            System.out.println("1. Gerenciamento de Clínica Médica");
            System.out.println("2. Gerenciamento de Eventos");
            System.out.println("3. Gerenciamento de Restaurante");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();  

            switch (opcao) {
                case 1:
                    gerenciamentoClinica(scanner);
                    sistemasUsados.add(1);
                    break;
                case 2:
                    gerenciamentoEventos(scanner);
                    sistemasUsados.add(2);
                    break;
                case 3:
                    gerenciamentoRestaurante(scanner);
                    sistemasUsados.add(3);
                    break;
                case 0:
                    if (sistemasUsados.size() == 3) { //funcionalidade adicional
                    System.out.println("\nObrigado por testar o nosso sistema, você é massa!");
                    }
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    
    
    
    
    
    // funcao para gerenciar a clinica medica
    private static void gerenciamentoClinica(Scanner scanner) {
    System.out.println("\nGerenciamento da Clínica Médica");


    // preenchimento das consultas e opcao de fazer outra
    boolean agendarMais = true;
    while (agendarMais) {
        System.out.print("Nome do paciente: ");
        String nomePaciente = scanner.nextLine();

        System.out.print("Idade do paciente: ");
        int idadePaciente = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("CPF do paciente: ");
        String cpfPaciente = scanner.nextLine();

        System.out.print("Nome do médico: ");
        String nomeMedico = scanner.nextLine();

        System.out.print("Especialidade do médico: ");
        String especialidadeMedico = scanner.nextLine();

        System.out.print("Data da consulta (dd/mm/aaaa): ");
        String dataConsulta = scanner.nextLine();

        Paciente paciente = new Paciente(nomePaciente, idadePaciente, cpfPaciente);
        Medico medico = new Medico(nomeMedico, especialidadeMedico);
        Consulta consulta = new Consulta(dataConsulta, paciente, medico);

        System.out.println("\nConsulta agendada com sucesso!");
        System.out.println("Paciente: " + paciente.getNome());
        System.out.println("Médico: " + medico.getNome() + " (" + medico.getEspecialidade() + ")");
        System.out.println("Data: " + consulta.getData());

        //aumenta o numero de consultas do medico
        popularidadeMedico.put(medico.getNome(), popularidadeMedico.getOrDefault(medico.getNome(), 0) + 1);

        System.out.print("Deseja agendar mais consultas? (s/n): ");
        String resposta = scanner.nextLine();
        if (resposta.equalsIgnoreCase("n")) {
            agendarMais = false;
        }
    }

    //diz qual foi o medico mais procurado
    String medicoMaisSolicitado = popularidadeMedico.entrySet().stream().max((entry1, entry2) -> entry1.getValue() - entry2.getValue()).get().getKey();

    System.out.println("O médico mais solicitado foi: " + medicoMaisSolicitado);
}
    




// funcao para gerenciar Restaurante
    private static void gerenciamentoRestaurante(Scanner scanner) {
    System.out.println("\nGerenciamento de Restaurante");


    System.out.print("Número da mesa: ");
    int numeroMesa = scanner.nextInt();
    scanner.nextLine(); 

    Mesa mesa = new Mesa(numeroMesa);
    mesa.ocuparMesa();

    Pedido pedido = new Pedido();

    //preenchimento restaurante e opcao de fazer outra
    boolean adicionarMaisItens = true;
    while (adicionarMaisItens) {
        System.out.print("Nome do item: ");
        String nomeItem = scanner.nextLine();

        System.out.print("Preço do item: ");
        double precoItem = scanner.nextDouble();

        System.out.print("Quantidade: ");
        int quantidadeItem = scanner.nextInt();
        scanner.nextLine(); 

        ItemDoPedido item = new ItemDoPedido(nomeItem, precoItem, quantidadeItem);
        pedido.adicionarItem(item);

        //adiciona item mais procurado
        popularidadeItens.put(nomeItem, popularidadeItens.getOrDefault(nomeItem, 0) + quantidadeItem);

        System.out.print("Deseja adicionar mais itens? (s/n): ");
        String resposta = scanner.nextLine();
        if (resposta.equalsIgnoreCase("n")) {
            adicionarMaisItens = false;
        }
    }

    mesa.registrarPedido(pedido);
    System.out.println("Total da mesa: R$" + mesa.calcularTotal());

    // qual o item mais popular
    String itemMaisPopular = popularidadeItens.entrySet().stream().max((entry1, entry2) -> entry1.getValue() - entry2.getValue()).get().getKey();

    System.out.println("O item mais popular foi: " + itemMaisPopular);
}

    




// funcao para gerenciar eventos
    private static void gerenciamentoEventos(Scanner scanner) {
    System.out.println("\nGerenciamento de Eventos");
        
    //preenchimento evento e opcao de fazer outra
    boolean registrarMais = true;
    while (registrarMais) {

    System.out.print("Nome do evento: ");
    String nomeEvento = scanner.nextLine();

    System.out.print("Data do evento (dd/mm/aaaa): ");
    String dataEvento = scanner.nextLine();

    System.out.print("Nome do local do evento: ");
    String nomeLocal = scanner.nextLine();

    System.out.print("Endereço do local: ");
    String enderecoLocal = scanner.nextLine();

    System.out.print("Capacidade do local: ");
    int capacidadeLocal = scanner.nextInt();
    scanner.nextLine(); 

    Local local = new Local(nomeLocal, enderecoLocal, capacidadeLocal);
    Evento evento = new Evento(nomeEvento, dataEvento, local, capacidadeLocal);

    System.out.print("Nome do participante: ");
    String nomeParticipante = scanner.nextLine();

    System.out.print("Email do participante: ");
    String emailParticipante = scanner.nextLine();

    Participante participante = new Participante(nomeParticipante, emailParticipante);
    if (evento.registrarParticipante(participante)) {
        System.out.println("Participante registrado com sucesso!");
    }
    
    //adiciona
    popularidadeEventos.put(evento.getNome(), popularidadeEventos.getOrDefault(evento.getNome(), 0) + 1);
    String eventoMaisPopular = popularidadeEventos.entrySet().stream().max((entry1, entry2) -> entry1.getValue() - entry2.getValue()).get().getKey();

    System.out.print("Deseja registrar mais uma participação? (s/n): ");
        String resposta = scanner.nextLine();
        if (resposta.equalsIgnoreCase("n")) {
            registrarMais = false;
        }

    //o mais popular
    System.out.println("O evento com mais participantes é: " + eventoMaisPopular);

        evento.gerarRelatorio();
        }
    }
}