package model;
import java.util.*;
//clase de pessoa base para Aluno e Professor
class Pessoa {
    protected String nome;
    protected String cpf;

    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }
}
//aluno e professor herdam de Pessoa ^^
class Aluno extends Pessoa {
    public Aluno(String nome, String cpf) {
        super(nome, cpf);
    }

    public String toString() {
        return "Aluno: " + nome + " | CPF: " + cpf;
    }
}
// esse é o professor, que também herda de Pessoa <3
class Professor extends Pessoa {
    public Professor(String nome, String cpf) {
        super(nome, cpf);
    }

    public String toString() {
        return "Professor: " + nome + " | CPF: " + cpf;
    }
}
// disciplina tem um nome e um professor
class Disciplina {
    private String nome;
    private Professor professor;

    public Disciplina(String nome, Professor professor) {
        this.nome = nome;
        this.professor = professor;
    }

    public String getNome() {
        return nome;
    }

    public Professor getProfessor() {
        return professor;
    }

    public String toString() {
        return "Disciplina: " + nome + " | Professor: " + professor.nome;
    }
}
// tem que ser um desempenho, com aluno, disciplina, nota e frequência
class Desempenho {
    Aluno aluno;
    Disciplina disciplina;
    double nota;
    int frequencia;

    public Desempenho(Aluno aluno, Disciplina disciplina, double nota, int frequencia) {
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.nota = nota;
        this.frequencia = frequencia;
    }

    public String toString() {
        return aluno.nome + " - " + disciplina.getNome() + " | Nota: " + nota + " | Frequência: " + frequencia + "%";
    }
}

class HorarioAula {
    private String diaSemana;
    private String horario;
    private Disciplina disciplina;

    public HorarioAula(String diaSemana, String horario, Disciplina disciplina) {
        this.diaSemana = diaSemana;
        this.horario = horario;
        this.disciplina = disciplina;
    }

    public String toString() {
        return diaSemana + " | " + horario + " | " + disciplina.getNome();
    }
}

class GradeCurricular {
    private String curso;
    private List<Disciplina> disciplinas = new ArrayList<>();

    public GradeCurricular(String curso) {
        this.curso = curso;
    }

    public void adicionarDisciplina(Disciplina d) {
        disciplinas.add(d);
    }

    public void exibirGrade() {
        System.out.println("Grade do curso: " + curso);
        for (Disciplina d : disciplinas) {
            System.out.println("- " + d.getNome());
        }
    }
}

class CalendarioAcademico {
    private List<String> eventos = new ArrayList<>();

    public void adicionarEvento(String evento) {
        eventos.add(evento);
    }

    public void listarEventos() {
        System.out.println("\n--- Calendário Acadêmico ---");
        for (String e : eventos) {
            System.out.println(e);
        }
    }

    public List<String> getEventos() {
        return eventos;
    }
}

public class AMatricula {
    static Scanner scanner = new Scanner(System.in);
    static List<Aluno> alunos = new ArrayList<>();
    static List<Professor> professores = new ArrayList<>();
    static List<Disciplina> disciplinas = new ArrayList<>();
    static List<Desempenho> desempenhos = new ArrayList<>();
    static List<HorarioAula> horarios = new ArrayList<>();
    static GradeCurricular grade = new GradeCurricular("Ciência da Computação");
    static CalendarioAcademico calendario = new CalendarioAcademico();
// será que arrumar um metodo pra guardar os dados é uma boa ideia?
    // talvez seja interessante salvar os dados em um arquivo ou banco de dados hmmm aiai 
    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Cadastrar Professor");
            System.out.println("3. Cadastrar Disciplina");
            System.out.println("4. Registrar Desempenho");
            System.out.println("5. Listar Alunos");
            System.out.println("6. Listar Disciplinas");
            System.out.println("7. Ver Desempenho");
            System.out.println("8. Gerenciar Grade Curricular");
            System.out.println("9. Cadastrar Horário de Aula");
            System.out.println("10. Ver Calendário Acadêmico");
            System.out.println("11. Adicionar Evento ao Calendário");
            System.out.println("12. Gerar Relatório Administrativo");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch(opcao) {
                case 1: cadastrarAluno(); break;
                case 2: cadastrarProfessor(); break;
                case 3: cadastrarDisciplina(); break;
                case 4: registrarDesempenho(); break;
                case 5: listarAlunos(); break;
                case 6: listarDisciplinas(); break;
                case 7: verDesempenho(); break;
                case 8: gerenciarGrade(); break;
                case 9: cadastrarHorario(); break;
                case 10: CalendarioAcademico(); break;
                case 11: adicionarEvento(); break;
                case 12: gerarRelatorio(); break;
                case 0: System.out.println("Saindo..."); break;
            }
        } while (opcao != 0);
    }

    
    private static void adicionarEvento() {
        System.out.print("Descrição do evento: ");
        String evento = scanner.nextLine();
        calendario.adicionarEvento(evento);
        System.out.println("Evento adicionado ao calendário.");
        pausa();
    }


 private static void cadastrarHorario() {
    int i = -1; // Índice da disciplina (inicializado como inválido)
    String diaSemana = "";
    String horario = "";

    // Loop para validar o índice da disciplina
    while (true) {
        listarDisciplinasSimples(); // Mostra a lista de disciplinas

        if (disciplinas.isEmpty()) {
            System.out.println("⚠ Erro: Não há disciplinas cadastradas. Cadastre uma disciplina primeiro.");
            return; // Encerra o método se não houver disciplinas
        }

        System.out.print("Escolha o índice da disciplina: ");
        String inputDisciplina = scanner.nextLine(); // Lê como String para evitar problemas com nextInt()

        try {
            i = Integer.parseInt(inputDisciplina); // Tenta converter para int

            // Verifica se o índice está dentro dos limites da lista
            if (i >= 0 && i < disciplinas.size()) {
                break; // Índice válido, sai do loop
            } else {
                System.out.println("⚠ Erro: Índice inválido. Digite um número entre 0 e " + (disciplinas.size() - 1) + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("⚠ Erro: Digite apenas números.");
        }
    }

    // Loop para validar o dia da semana
    while (true) {
        System.out.print("Dia da semana (ex: Segunda, Terça, Quarta, etc.): ");
        diaSemana = scanner.nextLine().trim();

        // Lista de dias válidos (pode ser ajustada conforme necessidade)
        String[] diasValidos = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo"};
        boolean diaValido = false;

        for (String dia : diasValidos) {
            if (dia.equalsIgnoreCase(diaSemana)) {
                diaValido = true;
                break;
            }
        }

        if (diaValido) {
            break; // Dia válido, sai do loop
        } else {
            System.out.println("⚠ Erro: Dia inválido. Digite um dia da semana (ex: Segunda, Terça, etc.).");
        }
    }

    // Loop para validar o horário (formato HH:MM e intervalo válido)
    while (true) {
        System.out.print("Horário (HH:MM, entre 07:00 e 22:00): ");
        horario = scanner.nextLine().trim();

        // Verifica o formato com regex (HH:MM)
        if (!horario.matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]$")) {
            System.out.println("⚠ Erro: Formato inválido. Use HH:MM (ex: 14:30).");
            continue;
        }

        // Converte para horas e minutos
        String[] partes = horario.split(":");
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);

        // Verifica o intervalo (07:00 às 22:00)
        if (horas < 7 || horas > 22 || (horas == 22 && minutos > 0)) {
            System.out.println("⚠ Erro: Horário fora do intervalo permitido (07:00 às 22:00).");
        } else {
            break; // Horário válido, sai do loop
        }
    }

    // Cadastra o horário
    horarios.add(new HorarioAula(diaSemana, horario, disciplinas.get(i)));
    System.out.println(" Horário de aula cadastrado com sucesso!");
}

    private static void gerenciarGrade() {
        System.out.println("\n--- Gerenciar Grade Curricular ---");
        grade.exibirGrade();
        System.out.println("1. Adicionar Disciplina");
        System.out.println("2. Exibir Grade");
        System.out.print("Opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao == 1) {
            listarDisciplinas();
            System.out.print("Escolha o índice da disciplina: ");
            int i = scanner.nextInt();
            scanner.nextLine();
            grade.adicionarDisciplina(disciplinas.get(i));
            System.out.println("Disciplina adicionada à grade.");
        } else if (opcao == 2) {
            grade.exibirGrade();
        }
        pausa();
    }


  private static void verDesempenho() {
    // Verifica se existem alunos cadastrados
    if (alunos.isEmpty()) {
        System.out.println("Não há alunos cadastrados.");
        pausa();
        return;
    }

    // Lista os alunos disponíveis
    listarAlunos();

    int a = -1;
    // Loop para seleção do aluno com validação
    while (true) {
        System.out.print("Índice do aluno: ");
        String input = scanner.nextLine();
        
        try {
            a = Integer.parseInt(input);
            
            // Verifica se o índice está dentro do intervalo válido
            if (a >= 0 && a < alunos.size()) {
                break;
            } else {
                System.out.println("Índice inválido. Digite um número entre 0 e " + (alunos.size() - 1) + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Digite apenas números.");
        }
    }

    // Obtém o aluno selecionado
    Aluno alunoSelecionado = alunos.get(a);
    System.out.println("\n--- Desempenho de " + alunoSelecionado.nome + " ---");

    // Verifica se o aluno tem desempenhos registrados
    boolean temDesempenho = false;
    for (Desempenho d : desempenhos) {
        if (d.aluno.equals(alunoSelecionado)) {
            System.out.println(d);
            temDesempenho = true;
        }
    }

    if (!temDesempenho) {
        System.out.println("Nenhum desempenho registrado para este aluno.");
    }

    pausa();
}


    static void cadastrarAluno() {
    String nome = "";
    String cpf = "";
    
        
        while (true) {
        System.out.print("Nome do aluno: ");
        nome = scanner.nextLine().trim();
        
        if (nome.isEmpty()) {
            System.out.println("Nome não pode estar vazio. Tente novamente.");
        } else if (nome.matches(".*\\d.*")) {
            System.out.println("Nome não pode conter números. Tente novamente.");
        } else {
            break; // Sai do loop quando o nome for válido
        }
        }
    
        
        while (true) {
        System.out.print("CPF do aluno: ");
        cpf = scanner.nextLine().trim();
        
        if (cpf.isEmpty()) {
            System.out.println("CPF não pode estar vazio. Tente novamente.");
        } else if (!cpf.matches("\\d+")) {
            System.out.println("CPF deve conter apenas números. Tente novamente.");
        } else if (cpf.length() != 11) {
            System.out.println("CPF deve ter 11 dígitos. Tente novamente.");
        } else {
            break; // tudo certo
        }
        }
    
        alunos.add(new Aluno(nome, cpf));
        System.out.println("Aluno cadastrado com sucesso.");
        pausa();
}   

    static void cadastrarProfessor() {
    String nome = "";
    String cpf = "";
    
    // Loop para validação do NOME
    while (true) {
        System.out.print("Nome do professor: ");
        nome = scanner.nextLine().trim(); // muito util o trim() para remover espaços extras
        
        if (nome.isEmpty()) {
            System.out.println("⚠ Erro: O nome não pode ser vazio. Tente novamente.");
        } else if (nome.matches(".*\\d.*")) { // preciso entender melhor essa regex
            System.out.println("⚠ Erro: O nome não pode conter números. Tente novamente.");
        } else {
            break; // Nome válido, sai do loop
        }
    }
    
    // Loop para validação do CPF
    while (true) {
        System.out.print("CPF do professor: ");
        cpf = scanner.nextLine().trim();
        
        if (cpf.isEmpty()) {
            System.out.println("⚠ Erro: CPF não pode ser vazio.");
        } else if (!cpf.matches("\\d{11}")) {
            System.out.println("⚠ Erro: CPF deve ter 11 dígitos (sem pontos ou traços).");
        } else if (cpfJaCadastrado(cpf)) { 
            System.out.println("⚠ Erro: Este CPF já está cadastrado.");
        } else {
            break; // CPF válido, sai do loop
        }
    }
    
    professores.add(new Professor(nome, cpf));
    System.out.println(" Professor cadastrado com sucesso!");
    pausa();
}

private static boolean cpfJaCadastrado(String cpf) {
    // Verifica se o CPF já está em Professores
    for (Professor prof : professores) {
        if (prof.getCpf().equals(cpf)) {
            return true;
        }
    }
    // Verifica se o CPF já está em Alunos
    for (Aluno aluno : alunos) {
        if (aluno.getCpf().equals(cpf)) {
            return true;
        }
    }
    return false; // CPF não cadastrado em nenhuma lista
}
// cAdastrar disciplina, com validação de nome e professor
    static void cadastrarDisciplina() {
    final String nome;
    int i = -1; // Inicializa com um valor inválido

    // Loop para validar o nome da disciplina
    while (true) {
        System.out.print("Nome da disciplina: ");
        String nomeTemp = scanner.nextLine().trim();

        if (nomeTemp.isEmpty()) {
            System.out.println("⚠ Erro: O nome da disciplina não pode ser vazio.");
        } else if (disciplinas.stream().anyMatch(d -> d.getNome().equalsIgnoreCase(nomeTemp))) {
            System.out.println("⚠ Erro: Já existe uma disciplina com esse nome.");
        } else {
            nome = nomeTemp;
            break; // Nome válido, sai do loop
        }
    }

    // Loop para validar o índice do professor
    while (true) {
        listarProfessores(); 
        if (professores.isEmpty()) {
            System.out.println("⚠ Erro: Não há professores cadastrados. Cadastre um professor primeiro.");
            return; // Encerra o método se não houver professores
        }

        System.out.print("Escolha o índice do professor: ");
        String input = scanner.nextLine(); // Lê como String para evitar problemas com nextInt()

        try {
            i = Integer.parseInt(input); // Tenta converter para int

            // Verifica se o índice está dentro dos limites da lista
            if (i >= 0 && i < professores.size()) {
                break; 
            } else {
                System.out.println("⚠ Erro: Índice inválido. Digite um número entre 0 e " + (professores.size() - 1) + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("⚠ Erro: Digite apenas números.");
        }
    }

    // Cadastra a disciplina
    disciplinas.add(new Disciplina(nome, professores.get(i)));
    System.out.println(" Disciplina cadastrada com sucesso!");
    pausa();
}
    
    // Registrar desempenho do aluno em uma disciplina, com validação de entrada
    static void registrarDesempenho() {
    int a = -1; // Índice do aluno (inicializado como inválido)
    int d = -1; // Índice da disciplina (inicializado como inválido)
    double nota = -1; // Nota (inicializada como inválida)
    int freq = -1; // Frequência (inicializada como inválida)

    // Loop para validar o índice do aluno
    while (true) {
        listarAlunos(); // Mostra a lista de alunos

        if (alunos.isEmpty()) {
            System.out.println("⚠ Erro: Não há alunos cadastrados. Cadastre um aluno primeiro.");
            return; // Encerra o método se não houver alunos
        }

        System.out.print("Índice do aluno: ");
        String inputAluno = scanner.nextLine(); // Lê como String para evitar problemas com nextInt()

        try {
            a = Integer.parseInt(inputAluno); // Tenta converter para int

            // Verifica se o índice está dentro dos limites da lista
            if (a >= 0 && a < alunos.size()) {
                break; // Índice válido, sai do loop
            } else {
                System.out.println("⚠ Erro: Índice inválido. Digite um número entre 0 e " + (alunos.size() - 1) + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("⚠ Erro: Digite apenas números.");
        }
    }

    // Loop para validar o índice da disciplina
    while (true) {
        listarDisciplinasSimples(); // Mostra a lista de disciplinas

        if (disciplinas.isEmpty()) {
            System.out.println("⚠ Erro: Não há disciplinas cadastradas. Cadastre uma disciplina primeiro.");
            return; // Encerra o método se não houver disciplinas
        }

        System.out.print("Índice da disciplina: ");
        String inputDisciplina = scanner.nextLine(); // Lê como String para evitar problemas com nextInt()

        try {
            d = Integer.parseInt(inputDisciplina); // Tenta converter para int

            // Verifica se o índice está dentro dos limites da lista
            if (d >= 0 && d < disciplinas.size()) {
                break; // Índice válido, sai do loop
            } else {
                System.out.println("⚠ Erro: Índice inválido. Digite um número entre 0 e " + (disciplinas.size() - 1) + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("⚠ Erro: Digite apenas números.");
        }
    }

    // Loop para validar a nota (ex: 0 a 10)
    while (true) {
        System.out.print("Nota (0 a 10): ");
        String inputNota = scanner.nextLine();

        try {
            nota = Double.parseDouble(inputNota);

            if (nota >= 0 && nota <= 10) {
                break; // Nota válida, sai do loop
            } else {
                System.out.println("⚠ Erro: A nota deve estar entre 0 e 10.");
            }
        } catch (NumberFormatException e) {
            System.out.println("⚠ Erro: Digite um número válido (ex: 7.5).");
        }
    }

    // Loop para validar a frequência (0% a 100%)
    while (true) {
        System.out.print("Frequência (0% a 100%): ");
        String inputFreq = scanner.nextLine();

        try {
            freq = Integer.parseInt(inputFreq);

            if (freq >= 0 && freq <= 100) {
                break; // Frequência válida, sai do loop
            } else {
                System.out.println("⚠ Erro: A frequência deve estar entre 0% e 100%.");
            }
        } catch (NumberFormatException e) {
            System.out.println("⚠ Erro: Digite um número inteiro (ex: 75).");
        }
    }

    // Registra o desempenho
    desempenhos.add(new Desempenho(alunos.get(a), disciplinas.get(d), nota, freq));
    System.out.println(" Desempenho registrado com sucesso!");
    pausa();
}

    private static void listarDisciplinasSimples() {
        System.out.println("\n--- Disciplinas ---");
        for (int i = 0; i < disciplinas.size(); i++) {
            System.out.println(i + " - " + disciplinas.get(i).getNome());
        }
        pausa();
    }


    static void listarAlunos() {
        System.out.println("\n--- Alunos ---");
        for (int i = 0; i < alunos.size(); i++) {
            System.out.println(i + " - " + alunos.get(i));
        }
        pausa();
    }



    static void listarProfessores() {
        System.out.println("\n--- Professores ---");
        for (int i = 0; i < professores.size(); i++) {
            System.out.println(i + " - " + professores.get(i));
        }
        pausa();
    }

    static void listarDisciplinas() {
        System.out.println("\n--- Disciplinas ---");
        for (int i = 0; i < disciplinas.size(); i++) {
            System.out.println(i + " - " + disciplinas.get(i));
        }
        pausa();
    }
    private static void gerarRelatorio() {
    System.out.println("\n--- Relatório Administrativo ---");
    System.out.println("1. Relatório Geral");
    System.out.println("2. Situação de Aprovação ");
    System.out.print("Escolha o tipo de relatório: ");
    int tipoRelatorio = scanner.nextInt();
    scanner.nextLine();

    if (tipoRelatorio == 1) {
        // Relatório geral existente
        System.out.println("\n--- Relatório Geral ---");
        System.out.println("Total de Alunos: " + alunos.size());
        System.out.println("Total de Professores: " + professores.size());
        System.out.println("Total de Disciplinas: " + disciplinas.size());
        System.out.println("Total de Registros de Desempenho: " + desempenhos.size());

        if (!desempenhos.isEmpty()) {
            double somaNotas = 0;
            int somaFrequencia = 0;
            for (Desempenho d : desempenhos) {
                somaNotas += d.nota;
                somaFrequencia += d.frequencia;
            }
            double mediaNota = somaNotas / desempenhos.size();
            double mediaFrequencia = somaFrequencia / (double) desempenhos.size();

            System.out.printf("Média geral de notas: %.2f\n", mediaNota);
            System.out.printf("Média geral de frequência: %.2f%%\n", mediaFrequencia);
        } else {
            System.out.println("Ainda não há dados de desempenho para análise.");
        }
    } else if (tipoRelatorio == 2) {
        // Novo relatório de aprovação
        System.out.println("\n--- Situação de Aprovação ---");
        System.out.println("Critérios: Nota media 6 e Frequência minima 80%");
        
        if (desempenhos.isEmpty()) {
            System.out.println("Não há registros de desempenho para análise.");
            pausa();
            return;
        }

        // Contadores
        int totalAprovados = 0;
        int totalReprovados = 0;
        int reprovadosNota = 0;
        int reprovadosFrequencia = 0;
        int reprovadosAmbos = 0;

        System.out.println("\nDetalhamento por aluno:");
        for (Aluno aluno : alunos) {
            System.out.println("\nAluno: " + aluno.nome);
            boolean temDesempenho = false;

            for (Desempenho d : desempenhos) {
                if (d.aluno.equals(aluno)) {
                    temDesempenho = true;
                    String situacao;
                    if (d.nota >= 6 && d.frequencia >= 80) {
                        situacao = "APROVADO";
                        totalAprovados++;
                    } else {
                        situacao = "REPROVADO";
                        totalReprovados++;
                        
                        // Detalha motivo da reprovação
                        if (d.nota < 6 && d.frequencia >= 80) {
                            reprovadosNota++;
                            situacao += " (Nota insuficiente)";
                        } else if (d.nota >= 6 && d.frequencia < 80) {
                            reprovadosFrequencia++;
                            situacao += " (Frequência insuficiente)";
                        } else {
                            reprovadosAmbos++;
                            situacao += " (Nota e frequência insuficientes)";
                        }
                    }
                    System.out.printf("- %s: Nota %.1f, Frequência %d%% -> %s\n",
                                    d.disciplina.getNome(), d.nota, d.frequencia, situacao);
                }
            }

            if (!temDesempenho) {
                System.out.println("(Nenhum desempenho registrado)");
            }
        }

        // Resumo estatístico
        System.out.println("\n--- Resumo Estatístico ---");
        System.out.println("Total de avaliações: " + desempenhos.size());
        System.out.println("Total de aprovados: " + totalAprovados);
        System.out.println("Total de reprovados: " + totalReprovados);
        System.out.println("  - Reprovados por nota: " + reprovadosNota);
        System.out.println("  - Reprovados por frequência: " + reprovadosFrequencia);
        System.out.println("  - Reprovados por ambos: " + reprovadosAmbos);
    } else {
        System.out.println("Opção inválida.");
    }
    pausa();
}
   static void CalendarioAcademico() {
    System.out.println("\n--- Calendário Acadêmico ---");
    if (calendario.getEventos().isEmpty()) {
        System.out.println("Calendário Acadêmico vazio. Adicione eventos primeiro.");
    } else {
        calendario.listarEventos();
    }
    pausa();
}


    static void pausa() {
        System.out.println("\nPressione Enter para continuar...");
        scanner.nextLine();
    }
}