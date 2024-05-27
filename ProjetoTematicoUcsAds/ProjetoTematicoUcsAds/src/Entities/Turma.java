package Entities;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    public int CodigoDaTurma;
    public String Etapa;
    public int Ano;
    public int LimiteDeVagas;
    public int Matriculados;
    public ArrayList<Aluno> alunosMatriculados;
    public List<Turma> turmasCadastradas = new ArrayList<>();

    public Turma(int codigoDaTurma, String etapa, int ano, int limiteDeVagas, int matriculados) {
        CodigoDaTurma = codigoDaTurma;
        Etapa = etapa;
        Ano = ano;
        LimiteDeVagas = limiteDeVagas;
        Matriculados = matriculados;
        alunosMatriculados = new ArrayList<>();
    }

    public Turma() {
        // Sobrecarga
    }
    public int getCodigoDaTurma() {
        return CodigoDaTurma;
    }

    public void adicionarAluno(Aluno aluno) {
        alunosMatriculados.add(aluno);
    }

    public ArrayList<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }

    public void setAlunosMatriculados(ArrayList<Aluno> alunosMatriculados) {
        this.alunosMatriculados = alunosMatriculados;
    }

    public void setCodigoDaTurma(int codigoDaTurma) {
        CodigoDaTurma = codigoDaTurma;
    }

    public String getEtapa() {
        return Etapa;
    }

    public void setEtapa(String etapa) {
        Etapa = etapa;
    }

    public int getAno() {
        return Ano;
    }

    public void setAno(int ano) {
        Ano = ano;
    }

    public int getLimiteDeVagas() {
        return LimiteDeVagas;
    }

    public void setLimiteDeVagas(int limiteDeVagas) {
        LimiteDeVagas = limiteDeVagas;
    }

    public int getMatriculados() {
        return Matriculados;
    }

    public void setMatriculados(int matriculados) {
        Matriculados = matriculados;
    }

    @Override
    public String toString() {
        return '\n' + "CodigoDaTurma: " + CodigoDaTurma + " - Etapa: " + Etapa + " - Ano: " + Ano + " - LimiteDeVagas: " + LimiteDeVagas + " - Matriculados: " + Matriculados ;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        Turma t = (Turma) obj;
        return (t.CodigoDaTurma == this.CodigoDaTurma && t.Etapa == this.Etapa && t.Ano == this.Ano);
    }
}
