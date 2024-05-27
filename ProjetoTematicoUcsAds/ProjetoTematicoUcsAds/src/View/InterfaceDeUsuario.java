package View;

import Entities.Aluno;
import Entities.ListaDeAlunos;
import Entities.Turma;

import java.time.LocalDate;
import java.util.Scanner;

public class InterfaceDeUsuario extends Turma{

    Scanner scan = new Scanner(System.in);
    ListaDeAlunos lista = new ListaDeAlunos();

    public void mostrarMenuPrincipal() {
        int opcao = menuDeOpcoes();

        while (opcao != 0) {
            switch (opcao) {
                case 1: // Cadastro de turma
                    cadastraTurma();
                    break;
                case 2: // Listar turmas cadastradas
                    System.out.println("Lista de turmas:");
                    System.out.println(turmasCadastradas.toString());
                    break;
                case 3: // Cadastro de alunos
                    menuListaAlunos();
                    break;
                case 4: // Cadastro de alunos em uma turma
                    System.out.println("Digite o Id da turma: ");
                    String idTurma = scan.nextLine();
                    menuMatricularAluno(procuraTurma(Integer.parseInt(idTurma)));
                    break;
                case 5: // Mostrar alunos cadastrados em uma turmas e fora da etapa
                    System.out.println("Informe o código da Turma");
                    mostrarPossiveisTurmas();
                    break;
            }

            System.out.println();
            opcao = menuDeOpcoes();
        }
        System.out.println("Finalizando programa...");
        System.exit(0);
    }
    public void menuMatricularAluno(Turma turma) {
        System.out.println("Informe o código da Turma");
        mostrarPossiveisTurmas();
        int turmaEscolhida = Integer.parseInt(scan.nextLine());
        System.out.println();
        System.out.println("Informe o nome do aluno");
        String alunoEscolhido = scan.nextLine();

        Turma turmaEncontrada = procuraTurma(turmaEscolhida);
        Aluno alunoEncontrado = lista.getPorNome(alunoEscolhido);

        adicionarAlunoTurmaFinal(alunoEncontrado, turmaEncontrada);
    }
    public int menuDeOpcoes() {
        System.out.println("Menu Principal");
        System.out.println("1 - Cadastro de turmas");
        System.out.println("2 - Listar turmas cadastradas");
        System.out.println("3 - Cadastro de alunos");
        System.out.println("4 - Cadastrar de alunos em uma turma");
        System.out.println("5 - Mostrar alunos cadastrados em uma turmas e fora da etapa");
        System.out.println("0 - Sair");
        return Integer.parseInt(scan.nextLine());
    }
    public void menuListaAlunos() {
        boolean opcao = false;

        do {
            System.out.println("O que você deseja? ");
            System.out.println("""
                    1- Incluir aluno no início da lista
                    2- Incluir aluno no final da lista
                    3- Ordenar alunos
                    4- Remover aluno do final da lista
                    5- Quantidade de alunos da lista
                    6- Aluno por posição""");
            int escolha = Integer.parseInt(scan.nextLine());

            switch (escolha) {
                case 1:
                    Aluno newAluno = cadastraAluno();
                    lista.incluirNoInicio(newAluno);
                    opcao = true;
                    break;
                case 2:
                    newAluno = cadastraAluno();
                    lista.incluirNoFIm(newAluno);
                    opcao = true;
                    break;
                case 3:
                    lista.ordenar();
                    opcao = true;
                    break;
                case 4:
                    Aluno removido = lista.removerDoFim();
                    System.out.println(removido);
                    opcao = true;
                    break;
                case 5:
                    int tamanho = lista.tamanho();
                    System.out.println("Tamanho total da lista: " + tamanho);
                    opcao = true;
                    break;
                case 6:
                    System.out.println("Informe a posição que deseja:");
                    int index = Integer.parseInt(scan.nextLine());
                    Aluno posicaoAluno = lista.get(index);

                    if (posicaoAluno != null) {
                        System.out.println("Aluno na posição solitada");
                        System.out.println(posicaoAluno);
                    }

                    opcao = true;
                    break;
                default:
                    break;
            }
        } while (!opcao);
    }

    public void cadastraTurma() {
        while (true) {
            try {
                boolean opcao = false;
                String nomeEtapa = null;
                int ano;

                System.out.println("Código da turma:");
                int codigo = Integer.parseInt(scan.nextLine());

                do {
                    System.out.println("Selecione a etapa da turma: ");
                    System.out.println("1- Infantil \n2- Fundamental Inicial \n3- Fundamental Final \n4- Ensino Médio");
                    int etapa = Integer.parseInt(scan.nextLine());
                    switch (etapa) {
                        case 1:
                            nomeEtapa = "Infantil";
                            opcao = true;
                            break;
                        case 2:
                            nomeEtapa = "Fundamental Inicial";
                            opcao = true;
                            break;
                        case 3:
                            nomeEtapa = "Fundamental Final";
                            opcao = true;
                            break;
                        case 4:
                            nomeEtapa = "Ensino Médio";
                            opcao = true;
                            break;
                        default:
                            System.out.println("Valor inválido.");
                            break;
                    }
                } while (!opcao);

                System.out.println("Ano");
                ano = Integer.parseInt(scan.nextLine());

                System.out.println("Limite de Vagas");
                int vagas = Integer.parseInt(scan.nextLine());

                System.out.println("Número de Matriculados");
                int matriculas = Integer.parseInt(scan.nextLine());

                while (matriculas > vagas) {
                    System.out.println("Número de matrículas maior que o número de vagas. \nDigite novamente: ");
                    matriculas = Integer.parseInt(scan.nextLine());
                }

                Turma turma = new Turma(codigo, nomeEtapa, ano, vagas, matriculas);
                turmasCadastradas.add(turma);
                
                System.out.println("Turma " + nomeEtapa +" criada com sucesso!");
                break;

            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um valor válido para o cadastro das turmas.");
            }
        }
    }
    public Aluno cadastraAluno() {
        
        System.out.println("Nome do Aluno:");
        String nome = scan.nextLine();

        System.out.println("CPF do Aluno: (Apenas números)");
        String cpf = scan.nextLine();

        System.out.println("Endereço do Aluno:");
        String endereco = scan.nextLine();

        System.out.println("Data de Nascimento do Aluno: Formato(AAAA-MM-DD)");
        LocalDate nascimento = LocalDate.parse(scan.nextLine());

        Aluno novoAluno = new Aluno(nome, cpf, endereco, nascimento);
        return novoAluno;
    }

    public Turma procuraTurma (int codTurma) {
        for (Turma turma : turmasCadastradas) {
            if (turma.getCodigoDaTurma() == codTurma) {
                return turma;
            }
        }
        System.out.println("Turma não encontrada");
        return null;
    }

    public void mostrarPossiveisTurmas() {
        for (Turma turma : turmasCadastradas) {
            System.out.println("Turma: " + turma.getCodigoDaTurma() + " - " + turma.getEtapa());
            System.out.println("Alunos Matriculados:");

            for (Aluno aluno : turma.getAlunosMatriculados()) {
                System.out.println("- " + aluno.getNome());
            }
            System.out.println(); // Adiciona uma linha em branco entre as turmas
        }
    }

    public static void adicionarAlunoTurmaFinal(Aluno aluno, Turma turma) {
        if (turma.LimiteDeVagas > turma.Matriculados) {
            turma.getAlunosMatriculados().add(aluno);
            turma.setMatriculados(turma.getMatriculados() - 1);
        } else {
            System.out.println("Turma cheia!");
        }
    }
}





