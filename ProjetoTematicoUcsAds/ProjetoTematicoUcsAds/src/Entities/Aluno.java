package Entities;

import java.time.LocalDate;
import java.time.Period;

public class Aluno {
    public String Nome;
    public String CPF;
    public String Endereco;
    public LocalDate DataDeNascimento;
    public int Idade;
    public String CodigoTurma;

    public Aluno(String nome, String CPF, String endereco, LocalDate dataDeNascimento) {
        Nome = nome;
        this.CPF = CPF;
        Endereco = endereco;
        DataDeNascimento = dataDeNascimento;
        Idade = ObterIdade(dataDeNascimento);
    }
    public Aluno() {
        // sobrecarga
    }
    public int ObterIdade(LocalDate dataNascimento) {
        LocalDate hoje = LocalDate.now();
        return Period.between(dataNascimento, hoje).getYears();
    }


    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public LocalDate getDataDeNascimento() {
        return DataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        DataDeNascimento = dataDeNascimento;
    }

    public String getCodigoTurma() {
        return CodigoTurma;
    }

    public void setCodigoTurma(String codigoTurma) {
        CodigoTurma = codigoTurma;
    }

    public void setIdade(int idade) {
        Idade = idade;
    }

    @Override
    public String toString() {
        return "Nome: " + Nome + " - Idade: " + Idade + ";";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        Aluno a = (Aluno) obj;
        return (a.Nome.equals(this.Nome) && a.CPF.equals(this.CPF));
    }
}
