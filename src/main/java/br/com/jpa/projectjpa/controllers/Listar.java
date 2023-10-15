package br.com.jpa.projectjpa.controllers;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.jpa.projectjpa.entities.Aluno;
import br.com.jpa.projectjpa.entities.Empresa;
import br.com.jpa.projectjpa.entities.Estagio;
import br.com.jpa.projectjpa.entities.Orientador;

public class Listar {
    
    public void listar (EntityManager manager) {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        
        do {
            System.out.println(
                    "\n---------------------\n| LISTAR MENU |\n---------------------\n1 - Listar Alunos\n2 - Listar Orientadores"
                            + "\n3 - Listar Empresas\n4 - Listar Estágios\n5 - Listar Tudo\n0 - Voltar\n");
            System.out.print("\nInsira uma opção: ");

            switch (scanner.nextInt()) {
                case 1:
                    listarAlunos(manager);
                    break;
                case 2:
                    listarOrientadores(manager);
                    break;
                case 3:
                    listarEmpresas(manager);
                    break;
                case 4:
                    listarEstagios(manager);
                    break;
                case 5:
                    listarAlunos(manager);
                    listarOrientadores(manager);
                    listarEmpresas(manager);
                    listarEstagios(manager);
                    break;
                case 0:
                    run = false;
                    break;
                default:
                    System.out.println("\nInsira um valor válido.");
                    break;
            }
        } while (run);
    }

    public void listarAlunos(EntityManager manager) {
        Query queryAluno = manager.createQuery("FROM Aluno");
        List<Aluno> alunos = queryAluno.getResultList();
        
        System.out.println("\nTodos os Alunos");
        for (int index = 0; index < alunos.size(); index++) {
            System.out.println("===========================================");
            System.out.println("Aluno: " + index);
            System.out.println("Nome: " + alunos.get(index).getNome());
            System.out.println("Matrícula: " + alunos.get(index).getMatricula());
            System.out.println("Curso: " + alunos.get(index).getCurso());
            String orientador = "Sem Orientador";
            if (alunos.get(index).getOrientadorAluno() != null) orientador = alunos.get(index).getOrientadorAluno().getNome();
            String empresa = "Sem Empresa";
            if (alunos.get(index).getEmpresaAluno() != null) empresa = alunos.get(index).getEmpresaAluno().getNome();
            System.out.println("Orientador: " + orientador);
            System.out.println("Empresa: " + empresa);
        }
    }

    public void listarOrientadores(EntityManager manager) {
        Query queryOrientador = manager.createQuery("FROM Orientador");
        List<Orientador> orientadores = queryOrientador.getResultList();

        System.out.println("\nTodos os Orientadores");
        for (int index = 0; index < orientadores.size(); index++) {
            System.out.println("===========================================");
            System.out.println("Orientador: " + index);
            System.out.println("Nome: " + orientadores.get(index).getNome());
            System.out.println("Matricula: " + orientadores.get(index).getMatricula());
            System.out.println("Número de orientandos: " + orientadores.get(index).getAlunos().size());
        }
    }

    public void listarEmpresas(EntityManager manager) {
        Query queryEmpresa = manager.createQuery("FROM Empresa");
        List<Empresa> empresas = queryEmpresa.getResultList();

        System.out.println("\nTodas as Empresas");
        for (int index = 0; index < empresas.size(); index++) {
            System.out.println("===========================================");
            System.out.println("Empresa: " + index);
            System.out.println("Nome: " + empresas.get(index).getNome());
            System.out.println("CNPJ: " + empresas.get(index).getCnpj());
            System.out.println("Número de estagiários: " + empresas.get(index).getAlunos().size());
        }
    }

    public void listarEstagios(EntityManager manager) {
        Query queryEstagio = manager.createQuery("FROM Estagio");
        List<Estagio> estagios = queryEstagio.getResultList();

        System.out.println("\nTodos os Estágios");
        for (int index = 0; index < estagios.size(); index++) {
            System.out.println("===========================================");
            System.out.println("Estágio: " + index);
            System.out.println("Data Início: " + estagios.get(index).getDataInicio());
            System.out.println("Data Fim: " + estagios.get(index).getDataFim());
            System.out.println("Carga Horária: " + estagios.get(index).getCargaHoraria());
            System.out.println("Status: " + estagios.get(index).getStatus());
            System.out.println("Aluno: " + estagios.get(index).getAlunoEstagio().getNome());
            System.out.println("Orientador: " + estagios.get(index).getOrientadorEstagio().getNome());
            System.out.println("Empresa: " + estagios.get(index).getEmpresaEstagio().getNome());
        }
        
    }
    
}
