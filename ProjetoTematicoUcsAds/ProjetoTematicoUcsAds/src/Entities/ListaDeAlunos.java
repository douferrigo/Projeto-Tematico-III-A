package Entities;

public class ListaDeAlunos extends Turma {

    public Turma procuraTurma(int codTurma) {
        for (Turma turma : turmasCadastradas) {
            if (turma.getCodigoDaTurma() == codTurma) {
                return turma;
            }
        }
        System.out.println("Turma não encontrada");
        return null;
    }

    ListaDeAlunos.Node head;

    public void incluirNoInicio(Aluno aluno) {
        try {
            
            Node atual = head;
            while (atual != null) {
                if (atual.aluno.equals(aluno)) {
                    throw new ExcecaoDeAlunoJaExistente("Aluno já existe no sistema");
                }
                atual = atual.next;
            }

            ListaDeAlunos.Node novoNode = new ListaDeAlunos.Node(aluno);
            novoNode.next = head;
            head = novoNode;
            System.out.println("Aluno inserido com sucesso no início da lista");
        } catch (ExcecaoDeAlunoJaExistente e) {
            System.out.println(e.getMessage());
        }
    }

    public void incluirNoFIm(Aluno aluno) {
        try {
            Node atual = head;
            while (atual != null) {
                if (atual.aluno.equals(aluno)) {
                    throw new ExcecaoDeAlunoJaExistente("Aluno já existe no sistema");
                }
                atual = atual.next;
            }

            Node novoNode = new Node(aluno);

            if (head == null) {
                head = novoNode;
            } else {
                Node ultimo = head;
                while (ultimo.next != null) {
                    ultimo = ultimo.next;
                }
                ultimo.next = novoNode;
            }
            System.out.println("Aluno inserido com sucesso no final da lista");
        } catch (ExcecaoDeAlunoJaExistente e) {
            System.out.println(e.getMessage());
        }
    }

    public void ordenar() {
        // Se a lista estiver vazia ou tiver apenas um elemento, já está ordenada
        if (head == null || head.next == null) {
            printList(head);
            return;
        }

        // Cria uma nova lista ordenada
        Node novaLista = null;

        // Percorre a lista original
        while (head != null) {
            // Guarda o nó atual e avança na lista original
            Node atual = head;
            head = head.next;

            // Se a nova lista estiver vazia ou o aluno atual tiver um nome que vem antes do
            // primeiro nome na nova lista
            if (novaLista == null || atual.aluno.Nome.compareTo(novaLista.aluno.Nome) < 0) {
                // Insere o aluno no início da lista
                atual.next = novaLista;
                novaLista = atual;
            } else {
                // Localiza o nó antes do ponto de inserção na nova lista
                Node antes = novaLista;
                while (antes.next != null && antes.next.aluno.Nome.compareTo(atual.aluno.Nome) < 0) {
                    antes = antes.next;
                }

                // Insere o aluno atual na nova lista
                atual.next = antes.next;
                antes.next = atual;
            }
        }
        // Atualiza a cabeça da lista para a nova lista ordenada
        head = novaLista;

        printList(novaLista);
    }

    public void printList(Node list) {
        Node current = list;

        System.out.println("Lista Ordenada de Alunos");
        int i = 1;
        while (current != null) {
            System.out.print(i);
            System.out.print(" ");
            System.out.println(current.aluno);

            i++;
            current = current.next;
        }
    }

    public Aluno removerDoFim() {
        // Se a lista estiver vazia não tem o que remover
        if (head == null) {
            return null;
        }

        // Se a lista tiver apenas um elemento, remove a cabeça
        if (head.next == null) {
            Aluno alunoRemovido = head.aluno;
            head = null;
            return alunoRemovido;
        }

        // Localiza o penúltimo nó
        Node penultimo = head;
        while (penultimo.next.next != null) {
            penultimo = penultimo.next;
        }

        // Remove o último nó e retorna o aluno
        Aluno alunoRemovido = penultimo.next.aluno;
        penultimo.next = null;
        return alunoRemovido;
    }

    public int tamanho() {
        int tamanho = 0;
        Node atual = head;

        while (atual != null) {
            tamanho++;
            atual = atual.next;
        }

        return tamanho;
    }

    public Aluno get(int index) {
        if (head == null) {
            System.out.println("A lista não contêm nenhum aluno!");
            return null;
        }

        Node atual = head;
        int contador = 0;

        // Percorre a lista até encontrar o nó na posição desejada
        while (atual != null) {
            if (contador == index) {
                return atual.aluno;
            }
            contador++;
            atual = atual.next;
        }

        // Se a posição for maior que o tamanho da lista, retorna null
        System.out.println("Posição informada é maior que o tamanho da lista");
        return null;
    }

    public Aluno getPorNome(String nome) {
        if (head == null) {
            System.out.println("A lista não contêm nenhum aluno!");
            return null;
        }

        Node atual = head;

        // Percorre a lista até encontrar o nó na posição desejada
        while (atual != null) {
            if (atual.aluno.Nome.equals(nome)) {
                return atual.aluno;
            }
            atual = atual.next;
        }

        // Se a posição for maior que o tamanho da lista, retorna null
        System.out.println("Aluno não encontrado");
        return null;
    }

    public void mostrarPossiveisTurmas() {
        for (Turma turma : turmasCadastradas
        // .stream().filter(x -> x.alunoNaFaixa(idade)).toArray(Turma[]::new)
        )
            System.out.println(turma);
    }

    public static void adicionaAlunoTurmaComeco(Aluno aluno, Turma turma) {
        if (turma.LimiteDeVagas > turma.Matriculados) {
            turma.getAlunosMatriculados().add(0, aluno);
            turma.setMatriculados(turma.getMatriculados() + 1);
        } else {
            System.out.println("Turma cheia!");
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

    class Node {
        Aluno aluno;
        Node next;

        Node(Aluno aluno) {
            this.aluno = aluno;
            this.next = null;
        }
    }

    public class ListaEncadeada {
        ListaDeAlunos.Node head;

        public void incluirNoInicio(Aluno aluno) {
            ListaDeAlunos.Node novoNode = new ListaDeAlunos.Node(aluno);
            novoNode.next = head;
            head = novoNode;
        }

        public void incluirNoFIm(Aluno aluno) {
            Node novoNode = new Node(aluno);

            if (head == null) {
                head = novoNode;
            } else {
                Node ultimo = head;
                while (ultimo.next != null) {
                    ultimo = ultimo.next;
                }
                ultimo.next = novoNode;
            }
        }

        public void ordenar() {
            // Se a lista estiver vazia ou tiver apenas um elemento, já está ordenada
            if (head == null || head.next == null) {
                printList(head);
                return;
            }

            // Cria uma nova lista ordenada
            Node novaLista = null;

            // Percorre a lista original
            while (head != null) {
                // Guarda o nó atual e avança na lista original
                Node atual = head;
                head = head.next;

                // Se a nova lista estiver vazia ou o aluno atual tiver um nome que vem antes do
                // primeiro nome na nova lista
                if (novaLista == null || atual.aluno.Nome.compareTo(novaLista.aluno.Nome) < 0) {
                    // Insere o aluno no início da lista
                    atual.next = novaLista;
                    novaLista = atual;
                } else {
                    // Localiza o nó antes do ponto de inserção na nova lista
                    Node antes = novaLista;
                    while (antes.next != null && antes.next.aluno.Nome.compareTo(atual.aluno.Nome) < 0) {
                        antes = antes.next;
                    }

                    // Insere o aluno atual na nova lista
                    atual.next = antes.next;
                    antes.next = atual;
                }
            }
            // Atualiza a cabeça da lista para a nova lista ordenada
            head = novaLista;

            printList(novaLista);
        }

        public void printList(Node list) {
            Node current = list;

            System.out.println("Lista Ordenada de Alunos");
            int i = 1;
            while (current != null) {
                System.out.print(i);
                System.out.print(" ");
                System.out.println(current.aluno);

                i++;
                current = current.next;
            }
        }

        public Aluno removerDoFim() {
            // Se a lista estiver vazia não tem o que remover
            if (head == null) {
                return null;
            }

            // Se a lista tiver apenas um elemento, remove a cabeça
            if (head.next == null) {
                Aluno alunoRemovido = head.aluno;
                head = null;
                return alunoRemovido;
            }

            // Localiza o penúltimo nó
            Node penultimo = head;
            while (penultimo.next.next != null) {
                penultimo = penultimo.next;
            }

            // Remove o último nó e retorna o aluno
            Aluno alunoRemovido = penultimo.next.aluno;
            penultimo.next = null;
            return alunoRemovido;
        }

        public int tamanho() {
            int tamanho = 0;
            Node atual = head;

            while (atual != null) {
                tamanho++;
                atual = atual.next;
            }

            return tamanho;
        }

        public Aluno get(int index) {
            if (head == null) {
                System.out.println("A lista não contêm nenhum aluno!");
                return null;
            }

            Node atual = head;
            int contador = 0;

            // Percorre a lista até encontrar o nó na posição desejada
            while (atual != null) {
                if (contador == index) {
                    return atual.aluno;
                }
                contador++;
                atual = atual.next;
            }

            // Se a posição for maior que o tamanho da lista, retorna null
            System.out.println("Posição informada é maior que o tamanho da lista");
            return null;
        }

        public Aluno getPorNome(String nome) {
            if (head == null) {
                System.out.println("A lista não contêm nenhum aluno!");
                return null;
            }

            Node atual = head;

            // Percorre a lista até encontrar o nó na posição desejada
            while (atual != null) {
                if (atual.aluno.Nome.equals(nome)) {
                    return atual.aluno;
                }
                atual = atual.next;
            }

            // Se a posição for maior que o tamanho da lista, retorna null
            System.out.println("Aluno não encontrado");
            return null;
        }
    }
}
