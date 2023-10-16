/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.jpa.projectjpa.controllers;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.jpa.projectjpa.entities.Aluno;
import br.com.jpa.projectjpa.entities.Empresa;
import br.com.jpa.projectjpa.entities.Estagio;
import br.com.jpa.projectjpa.entities.Orientador;

/**
 *
 * @author m
 */
public class Cadastrar {

    public void cadastrar(EntityManager manager) {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        
        do {
            System.out.println(
                    "\n---------------------\n| CADASTRAR MENU |\n---------------------\n1 - Cadastrar Aluno\n2 - Cadastrar Orientador"
                            + "\n3 - Cadastrar Empresa\n4 - Cadastrar Estágio\n0 - Voltar\n");
            System.out.print("\nInsira uma opção: ");

            switch (scanner.nextInt()) {
                case 1:
                    cadastrarAluno(manager);
                    break;
                case 2:
                    cadastrarOrientador(manager);
                    break;
                case 3:
                    cadastrarEmpresa(manager);
                    break;
                case 4:
                    System.out.println(cadastrarEstagio(manager)); 
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

    public void cadastrarAluno(EntityManager manager){
        String nomeAluno;
        String matricula;
        String curso;
        Orientador orientador = new Orientador();
        boolean setOrientador = false;
        Empresa empresa = new Empresa();
        boolean setEmpresa = false;

        Query queryOrientador = manager.createQuery("FROM Orientador");
        List<Orientador> orientadores = queryOrientador.getResultList();

        Query queryEmpresa = manager.createQuery("FROM Empresa");
        List<Empresa> empresas = queryEmpresa.getResultList();

        if (orientadores.size() == 0 && empresas.size() == 0) {
            // return false;
        }
        
        System.out.println("\n---------------------\n| CADASTRAR ALUNO |\n---------------------");

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nNome do aluno: ");
        nomeAluno = scanner.nextLine();

        System.out.print("\nMatricula do aluno: ");
        matricula = scanner.nextLine();

        System.out.print("\nCurso do aluno: ");
        curso = scanner.nextLine();

        int num;

        if (orientadores.size() > 0) {
            System.out.println("\nORIENTADORES");
            for (int index = 0; index < orientadores.size(); index++) {
                System.out.println(index + " Nome: " + orientadores.get(index).getNome());
            }
            
            do {
                System.out.println("\nEscolher orientador? \n1 - SIM\n2 - NÃO: ");
                num = scanner.nextInt();
                if (num == 2) break;

                System.out.println("\nEscolha o número do orientador: ");
                num = scanner.nextInt();

                if(num >= 0 && num <= orientadores.size()) {
                    orientador = orientadores.get(num);
                    setOrientador = true;
                } else System.out.println("Número inválido, tente novamente.");

            } while (num < 0 || num > orientadores.size());
        }

        if (empresas.size() > 0) {
            System.out.println("\nEmpresas");
            for (int index = 0; index < empresas.size(); index++) {
                System.out.println(index + " Nome: " + empresas.get(index).getNome());
            }
            
            do {
                System.out.println("\nEscolher empresa? \n1 - SIM\n2 - NÃO: ");
                num = scanner.nextInt();
                if (num == 2) break;

                System.out.println("\nEscolha o número da empresa: ");
                num = scanner.nextInt();

                if(num >= 0 && num <= empresas.size()) {
                    empresa = empresas.get(num);
                    setEmpresa = true;
                } else System.out.println("Número inválido, tente novamente.");

            } while (num < 0 || num > empresas.size());
        }

        Aluno aluno = new Aluno();
        aluno.setNome(nomeAluno);
        aluno.setCurso(curso);
        aluno.setMatricula(matricula);
        if (setOrientador) {
            orientador.getAlunos().add(aluno);
            aluno.setOrientadorAluno(orientador);
        }
        if (setEmpresa) {
            empresa.getAlunos().add(aluno);
            aluno.setEmpresaAluno(empresa);
        }

        manager.getTransaction().begin();
        manager.persist(aluno);
        if (setOrientador) manager.merge(orientador);
        if (setEmpresa) manager.merge(empresa);
        manager.getTransaction().commit();

        System.out.println("\nAluno criado!");
    }

    public void cadastrarOrientador(EntityManager manager) {
        String nomeOrientador;
        String matricula;

        System.out.println("\n---------------------\n| CADASTRAR ORIENTADOR |\n---------------------");

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nNome do orientador: ");
        nomeOrientador = scanner.nextLine();

        System.out.print("\nMatricula do orientador: ");
        matricula = scanner.nextLine();

        Orientador orientador = new Orientador();
        orientador.setNome(nomeOrientador);
        orientador.setMatricula(matricula);

        manager.getTransaction().begin();
        manager.persist(orientador);
        manager.getTransaction().commit();

        System.out.println("\nOrientador criado!");
    }

    public void cadastrarEmpresa(EntityManager manager) {
        String nomeEmpresa;
        String cnpj;

        System.out.println("\n---------------------\n| CADASTRAR EMPRESA |\n---------------------");

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nNome da empresa: ");
        nomeEmpresa = scanner.nextLine();

        System.out.print("\nCNPJ da empresa: ");
        cnpj = scanner.nextLine();

        Empresa empresa = new Empresa();
        empresa.setNome(nomeEmpresa);
        empresa.setCnpj(cnpj);

        manager.getTransaction().begin();
        manager.persist(empresa);
        manager.getTransaction().commit();

        System.out.println("\nEmpresa criada!");
    }

    public String cadastrarEstagio(EntityManager manager){
        String dataInicio;
        String dataFim;
        String cargaHoraria;
        Aluno aluno = new Aluno();

        Query queryEmpresa = manager.createQuery("FROM Empresa");
        List<Empresa> empresas = queryEmpresa.getResultList();

        Query queryOrientador = manager.createQuery("FROM Orientador");
        List<Orientador> orientadores = queryOrientador.getResultList();
        
        Query queryAluno = manager.createQuery("FROM Aluno");
        List<Aluno> alunos = queryAluno.getResultList();

        if (orientadores.size() == 0 && empresas.size() == 0 && alunos.size() == 0) {
            return "Não foi possível cadastrar, não existem orientadores, empresas ou alunos cadastrados";
        }
        
        System.out.println("\n---------------------\n| CADASTRAR ESTÁGIO |\n---------------------");

        Scanner scanner = new Scanner(System.in);

        int numAluno;
        Aluno alunoEscolhido;

        System.out.println("\nAluno");
        for (int index = 0; index < alunos.size(); index++) {
            System.out.println(index + " Nome: " + alunos.get(index).getNome());
        }
        
        do {
            System.out.println("\nEscolha o número do Aluno, digite um número negativo para cancelar: ");
            numAluno = scanner.nextInt();
            
            if (numAluno < 0) break;
            else if (numAluno >= alunos.size()) break; 

            alunoEscolhido = alunos.get(numAluno);
            
            if(alunoEscolhido.getEmpresaAluno() == null || alunoEscolhido.getOrientadorAluno() == null) {
                System.out.println("Aluno não tem empresa ou orientador");
            } else if (alunoEscolhido.getEstagio() != null) {
                System.out.println("Aluno já possui estágio");
            } else if (numAluno >= 0 && numAluno <= alunos.size()) aluno = alunos.get(numAluno);
            else System.out.println("Número inválido, tente novamente.");

        } while (numAluno > alunos.size() || alunoEscolhido.getEmpresaAluno() == null || alunoEscolhido.getOrientadorAluno() == null || alunoEscolhido.getEstagio() != null);

        if (numAluno < 0 || numAluno >= alunos.size()) return "Cancelado pelo usuário";

        scanner.nextLine();
        System.out.print("\nData de Incio: ");
        dataInicio = scanner.nextLine();

        System.out.print("\nData do Fim: ");
        dataFim = scanner.nextLine();

        System.out.print("\nCarga Horária: ");
        cargaHoraria = scanner.nextLine();

        Estagio estagio = new Estagio();
        estagio.setDataInicio(dataInicio);
        estagio.setDataFim(dataFim);
        estagio.setCargaHoraria(cargaHoraria);
        estagio.setAlunoEstagio(aluno);
        estagio.setOrientadorEstagio(aluno.getOrientadorAluno());
        estagio.setEmpresaEstagio(aluno.getEmpresaAluno());
        estagio.setStatus("Em andamento");
        aluno.setEstagio(estagio);

        manager.getTransaction().begin();
        manager.merge(aluno);
        manager.persist(estagio);
        manager.getTransaction().commit();

        return "Estágio cadastrado com sucesso";
    }
}
