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
    private Disciplina disciplina;
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
}

public class Matricula {
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
                case 10: calendario.listarEventos(); break;
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
    
        System.out.print("Escolha o índice da disciplina: ");
        int i = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Dia da semana: ");
        String diaSemana = scanner.nextLine();
        System.out.print("Horário (HH:MM): ");
        String horario = scanner.nextLine();
        horarios.add(new HorarioAula(diaSemana, horario, disciplinas.get(i)));
        System.out.println("Horário de aula cadastrado com sucesso.");
        

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
        listarAlunosSimples();
        System.out.print("Índice do aluno: ");
        int a = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n--- Desempenho de " + alunos.get(a).nome + " ---");
        for (Desempenho d : desempenhos) {
            if (d.aluno.equals(alunos.get(a))) {
                System.out.println(d);
            }
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
        System.out.print("CPF do professor (apenas números): ");
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
    System.out.println("✅ Professor cadastrado com sucesso!");
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
// os metodos são bem simples mas pra mim foi difícil entender como organizar tudo isso
    static void cadastrarDisciplina() {
        System.out.print("Nome da disciplina: ");
        String nome = scanner.nextLine();
        listarProfessores();
        System.out.print("Escolha o índice do professor: ");
        int i = scanner.nextInt();
        scanner.nextLine();
        disciplinas.add(new Disciplina(nome, professores.get(i)));
        System.out.println("Disciplina cadastrada com sucesso.");
        pausa();
    }

    static void registrarDesempenho() {
        listarAlunosSimples();
        System.out.print("Índice do aluno: ");
        int a = scanner.nextInt();
        listarDisciplinasSimples();
        System.out.print("Índice da disciplina: ");
        int d = scanner.nextInt();
        System.out.print("Nota: ");
        double nota = scanner.nextDouble();
        System.out.print("Frequência (%): ");
        int freq = scanner.nextInt();
        scanner.nextLine();
        desempenhos.add(new Desempenho(alunos.get(a), disciplinas.get(d), nota, freq));
        System.out.println("Desempenho registrado.");
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

    static void listarAlunosSimples() {
        for (int i = 0; i < alunos.size(); i++) {
            System.out.println(i + " - " + alunos.get(i).nome);
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
    System.out.println("Total de Alunos: " + alunos.size());
    System.out.println("Total de Professores: " + professores.size());
    System.out.println("Total de Disciplinas: " + disciplinas.size());
    System.out.println("Total de Registros de Desempenho: " + desempenhos.size());

    // Análise de desempenho geral
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

    pausa();
}



    static void pausa() {
        System.out.println("\nPressione Enter para continuar...");
        scanner.nextLine();
    }
}