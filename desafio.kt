// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)
import java.util.Scanner

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Usuario(var nome: String, var idade: Int, var email: String)

data class ConteudoEducacional(var nome: String, val duracao: Int = 60)

data class Formacao(val nome: String, val nivel: Nivel, val conteudos: List<ConteudoEducacional>) {
    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        inscritos.add(usuario)
        println("${usuario.nome} foi matriculado na formação $nome!")
    }

    fun listarInscritos() {
        println("Inscritos em $nome:")
        inscritos.forEach { println("- ${it.nome}") }
    }
}

fun limparTela() {
    repeat(30) { println() }
}

fun main() {
    val scanner = Scanner(System.`in`)


    val formacoes = listOf(
        Formacao(
            nome = "Formação Android",
            nivel = Nivel.INTERMEDIARIO,
            conteudos = listOf(ConteudoEducacional("Kotlin Básico"), ConteudoEducacional("Android Jetpack"))
        ),
        Formacao(
            nome = "Formação Backend",
            nivel = Nivel.AVANCADO,
            conteudos = listOf(ConteudoEducacional("Kotlin Avançado"), ConteudoEducacional("Spring Boot"))
        )
    )

    val usuarios = mutableListOf<Usuario>()

    while (true) {
        limparTela()
        println("=== MENU PRINCIPAL ===")
        println("1. Cadastrar novo aluno")
        println("2. Listar formações")
        println("3. Matricular aluno")
        println("4. Listar alunos matriculados por formação")
        println("5. Sair")
        print("Escolha uma opção: ")
        when (scanner.nextLine()) {
            "1" -> {
                print("Nome do aluno: ")
                val nome = scanner.nextLine()
                print("Idade: ")
                val idade = scanner.nextLine().toInt()
                print("Email: ")
                val email = scanner.nextLine()

                usuarios.add(Usuario(nome, idade, email))
                println("Aluno cadastrado com sucesso!")
                readln()
            }

            "2" -> {
                println("\n=== FORMAÇÕES DISPONÍVEIS ===")
                formacoes.forEachIndexed { i, f ->
                    println("${i + 1}. ${f.nome} - Nível: ${f.nivel}")
                    f.conteudos.forEach { println("   - ${it.nome} (${it.duracao} min)") }
                }
                readln()
            }

            "3" -> {
                if (usuarios.isEmpty()) {
                    println("Nenhum aluno cadastrado!")
                    readln()
                    continue
                }

                println("Selecione o aluno:")
                usuarios.forEachIndexed { i, u -> println("${i + 1}. ${u.nome}") }
                val alunoIndex = scanner.nextLine().toInt() - 1
                val alunoSelecionado = usuarios.getOrNull(alunoIndex)

                if (alunoSelecionado == null) {
                    println("Aluno inválido.")
                    readln()
                    continue
                }

                println("Selecione a formação para matricular:")
                formacoes.forEachIndexed { i, f -> println("${i + 1}. ${f.nome}") }
                val formacaoIndex = scanner.nextLine().toInt() - 1
                val formacaoSelecionada = formacoes.getOrNull(formacaoIndex)

                if (formacaoSelecionada == null) {
                    println("Formação inválida.")
                    readln()
                    continue
                }

                formacaoSelecionada.matricular(alunoSelecionado)
                readln()
            }

            "4" -> {
                println("\n=== INSCRITOS POR FORMAÇÃO ===")
                formacoes.forEach {
                    it.listarInscritos()
                    println()
                }
                readln()
            }

            "5" -> {
                println("Encerrando o programa...")
                break
            }

            else -> {
                println("Opção inválida.")
                readln()
            }
        }
    }
}


