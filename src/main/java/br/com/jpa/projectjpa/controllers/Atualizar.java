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
public class Atualizar {

    public void atualizar(EntityManager manager) {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        
            do {
                System.out.println(
                    "\n---------------------\n| ATUALIZAR MENU |\n---------------------\n1 - Atualizar Aluno\n2 - Atualizar Orientador"
                    + "\n3 - Atualizar Empresa\n4 - Atualizar Estágio\n0 - Voltar\n");
                System.out.print("\nInsira uma opção: ");

                switch (scanner.nextInt()) {
                    case 1:
                        atualizarAluno(manager);
                        break;
                    case 2:
                        atualizarOrientador(manager);
                        break;
                    case 3:
                        atualizarEmpresa(manager);
                        break;
                    case 4:
                        System.out.println(atualizarEstagio(manager));
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

    public void atualizarAluno(EntityManager manager){
        Scanner scanner = new Scanner(System.in);

        Listar listar = new Listar();
        listar.listarAlunos(manager);

        Query queryAluno = manager.createQuery("FROM Aluno");
        List<Aluno> alunos = queryAluno.getResultList();
        Aluno aluno = new Aluno();

        String nomeAluno;
        String matricula;
        String curso;
        
        System.out.println("\n---------------------\n| ATUALIZAR ALUNO |\n---------------------");

        int numAluno;
        
        do {
            System.out.println("\nEscolha o número do aluno para editar: ");
            numAluno = scanner.nextInt();

            if(numAluno >= 0 && numAluno <= alunos.size()) {
                aluno = alunos.get(numAluno);
            } else System.out.println("Número inválido, tente novamente.");

        } while (numAluno < 0 || numAluno > alunos.size());
        
        scanner.nextLine();
        System.out.print("\nNome do aluno: ");
        nomeAluno = scanner.nextLine();

        System.out.print("\nMatricula do aluno: ");
        matricula = scanner.nextLine();

        System.out.print("\nCurso do aluno: ");
        curso = scanner.nextLine();

        Orientador orientador = new Orientador();
        boolean setOrientador = false;
        Empresa empresa = new Empresa();
        boolean setEmpresa = false;

        Query queryOrientador = manager.createQuery("FROM Orientador");
        List<Orientador> orientadores = queryOrientador.getResultList();

        Query queryEmpresa = manager.createQuery("FROM Empresa");
        List<Empresa> empresas = queryEmpresa.getResultList();

        int num;

        if (orientadores.size() > 0) {
            System.out.println("\nORIENTADORES");
            for (int index = 0; index < orientadores.size(); index++) {
                System.out.println(index + " Nome: " + orientadores.get(index).getNome());
            }
            
            do {
                System.out.println("\nEscolher orientador? \n1 - SIM\n2 - NÃO: ");
                num = scanner.nextInt();
                if (num != 1) break;

                System.out.println("\nEscolha o número do orientador: ");
                num = scanner.nextInt();

                if(num >= 0 && num < orientadores.size()) {
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
        
        aluno.setNome(nomeAluno == "" ? aluno.getNome() : nomeAluno);
        aluno.setCurso(curso == "" ? aluno.getCurso() : curso);
        aluno.setMatricula(matricula == "" ? aluno.getMatricula() : matricula);

        Orientador orientadorAnterior = aluno.getOrientadorAluno();
        Empresa empresaAnterior = aluno.getEmpresaAluno();

        if (setOrientador) {
            orientadorAnterior.getAlunos().remove(aluno);
            orientador.getAlunos().add(aluno);
            aluno.setOrientadorAluno(orientador);
        }
        if (setEmpresa) {
            empresaAnterior.getAlunos().remove(aluno);
            empresa.getAlunos().add(aluno);
            aluno.setEmpresaAluno(empresa);
        }
        
        manager.getTransaction().begin();
        manager.merge(aluno);

        if (setOrientador) {
            manager.merge(orientadorAnterior);
            manager.merge(orientador);
        }
        if (setEmpresa) {
            manager.merge(empresaAnterior);
            manager.merge(empresa);
        }

        if (aluno.getEstagio() != null) {
            Query queryEstagio = manager.createQuery("FROM Estagio e WHERE e.id = :id");
            queryEstagio.setParameter("id", aluno.getEstagio().getId());
            
            Estagio estagio = (Estagio) queryEstagio.getSingleResult();
            estagio.setEmpresaEstagio(aluno.getEmpresaAluno());
            estagio.setOrientadorEstagio(aluno.getOrientadorAluno());
            manager.merge(estagio);
        }

        manager.getTransaction().commit();

        System.out.println("\nAluno atualizado.");
    }

    public void atualizarOrientador(EntityManager manager) {

        Scanner scanner = new Scanner(System.in);
        
        Listar listar = new Listar();
        listar.listarOrientadores(manager);

        Query queryOrientador = manager.createQuery("FROM Orientador");
        List<Orientador> orientadores = queryOrientador.getResultList();
        
        Orientador orientador = new Orientador();

        int numOrientador;
        
        do {
            System.out.println("\nEscolha o número do orientador para editar: ");
            numOrientador = scanner.nextInt();

            if(numOrientador >= 0 && numOrientador <= orientadores.size()) {
                orientador = orientadores.get(numOrientador);
            } else System.out.println("Número inválido, tente novamente.");

        } while (numOrientador < 0 || numOrientador > orientadores.size());

        String nomeOrientador;
        String matricula;
        
        System.out.println("\n---------------------\n| ATUALIZAR ORIENTADOR |\n---------------------");
        
        System.out.print("\nNome do orientador: ");
        nomeOrientador = scanner.nextLine();

        System.out.print("\nMatricula do orientador: ");
        matricula = scanner.nextLine();
        
        orientador.setNome(nomeOrientador == "" ? orientador.getNome() : nomeOrientador);
        orientador.setMatricula(matricula == "" ? orientador.getMatricula() : matricula);

        manager.getTransaction().begin();
        manager.merge(orientador);
        manager.getTransaction().commit();
        
        System.out.println("\nOrientador atualizado!");
    }

    public void atualizarEmpresa(EntityManager manager) {

        Scanner scanner = new Scanner(System.in);
        
        Listar listar = new Listar();
        listar.listarEmpresas(manager);

        Query queryEmpresa = manager.createQuery("FROM Empresa");
        List<Empresa> empresas = queryEmpresa.getResultList();
        
        Empresa empresa = new Empresa();

        int numEmpresa;
        
        do {
            System.out.println("\nEscolha o número da empresa para editar: ");
            numEmpresa = scanner.nextInt();

            if(numEmpresa >= 0 && numEmpresa <= empresas.size()) {
                empresa = empresas.get(numEmpresa);
            } else System.out.println("Número inválido, tente novamente.");

        } while (numEmpresa < 0 || numEmpresa > empresas.size());

        String nomeEmpresa;
        String cnpj;
        
        System.out.println("\n---------------------\n| ATUALIZAR EMPRESA |\n---------------------");

        scanner.nextLine();
        System.out.print("\nNome da empresa: ");
        nomeEmpresa = scanner.nextLine();

        System.out.print("\nCNPJ da empresa: ");
        cnpj = scanner.nextLine();
        
        empresa.setNome(nomeEmpresa == "" ? empresa.getNome() : nomeEmpresa);
        empresa.setCnpj(cnpj == "" ? empresa.getCnpj() : cnpj);

        manager.getTransaction().begin();
        manager.merge(empresa);
        manager.getTransaction().commit();
        
        System.out.println("\nEmpresa atualizado!");
    }

    public String atualizarEstagio(EntityManager manager){

        Scanner scanner = new Scanner(System.in);
        
        Listar listar = new Listar();
        listar.listarEstagios(manager);

        Query queryEstagio = manager.createQuery("FROM Estagio");
        List<Estagio> estagios = queryEstagio.getResultList();
        Estagio estagio = new Estagio();

        int numEstagio;
    
        do {
            System.out.println("\nEscolha o número do estágio para editar: ");
            numEstagio = scanner.nextInt();

            if(numEstagio >= 0 && numEstagio <= estagios.size()) {
                estagio = estagios.get(numEstagio);
            } else System.out.println("Número inválido, tente novamente.");

        } while (numEstagio < 0 || numEstagio > estagios.size());

        String dataInicio;
        String dataFim;
        String cargaHoraria;
        int status;

        Aluno aluno = new Aluno();
        Orientador orientador = new Orientador();
        Empresa empresa = new Empresa();

        Query queryAluno = manager.createQuery("FROM Aluno");
        List<Aluno> alunos = queryAluno.getResultList();

        Query queryOrientador = manager.createQuery("FROM Orientador");
        List<Orientador> orientadores = queryOrientador.getResultList();

        Query queryEmpresa = manager.createQuery("FROM Empresa");
        List<Empresa> empresas = queryEmpresa.getResultList();
        
        System.out.println("\n---------------------\n| ATUALIZAR ESTÁGIO |\n---------------------");
        
        scanner.nextLine();
        System.out.print("\nData de Incio: ");
        dataInicio = scanner.nextLine();

        System.out.print("\nData do Fim: ");
        dataFim = scanner.nextLine();

        System.out.print("\nCarga Horária: ");
        cargaHoraria = scanner.nextLine();        

        do {
                System.out.print("\nStatus | 1 - Em andamento \n2 - Finalizado\n");
                status = scanner.nextInt();

                if(status > 2 || status < 1) System.out.println("Número inválido, tente novamente.");

            } while (status < 1 || status > 2);

        int escolhaAluno;

        boolean escolherAluno = false;

        do {
            System.out.println("\nDeseja escolher um Aluno? ");
            System.out.println("1 - SIM");
            System.out.println("2 - NÃO");
            System.out.println("Escolha o número: ");
            escolhaAluno = scanner.nextInt();

            if(escolhaAluno == 1) escolherAluno = true;

        } while (escolhaAluno < 1 || escolhaAluno > 2);

        int numAluno;
        Aluno alunoEscolhido;

        System.out.println("\nAluno");
        for (int index = 0; index < alunos.size(); index++) {
            System.out.println(index + " Nome: " + alunos.get(index).getNome());
        }
        
        do {
            System.out.println("\nEscolha o número do Aluno, digite um número negativo para cancelar: ");
            numAluno = scanner.nextInt();

            alunoEscolhido = alunos.get(numAluno);
            
            if(alunoEscolhido.getEmpresaAluno() == null || alunoEscolhido.getOrientadorAluno() == null) {
                System.out.println("Aluno não tem empresa ou orientador");
            } else if (numAluno >= 0 && numAluno <= alunos.size()) aluno = alunos.get(numAluno);
            else System.out.println("Número inválido, tente novamente.");

        } while (numAluno > alunos.size() || alunoEscolhido.getEmpresaAluno() == null || alunoEscolhido.getOrientadorAluno() == null);

        if (numAluno < 0) return "\nCancelado pelo usuário";

        // int escolhaOrientador;

        // boolean escolherOrientador = false;

        // do {
        //     System.out.println("\nDeseja escolher um Orientador? ");
        //     System.out.println("1 - SIM");
        //     System.out.println("2 - NÃO");
        //     System.out.println("Escolha o número: ");
        //     escolhaOrientador = scanner.nextInt();

        //     if(escolhaOrientador == 1) escolherOrientador = true;

        // } while (escolhaOrientador < 1 || escolhaOrientador > 2);

        // int numOrientador;
    
        // if (escolherOrientador) {
        //     listar.listarOrientadores(manager);

        //     do {
        //         System.out.println("\nEscolha o número do orientador: ");
        //         numOrientador = scanner.nextInt();

        //         if(numOrientador >= 0 && numOrientador <= orientadores.size()) {
        //             orientador = orientadores.get(numOrientador);
        //         } else System.out.println("Número inválido, tente novamente.");

        //     } while (numOrientador < 0 || numOrientador > orientadores.size());
        // }
        
        // int escolhaEmpresa;

        // boolean escolherEmpresa = false;

        // do {
        //     System.out.println("\nDeseja escolher uma Empresa? ");
        //     System.out.println("1 - SIM");
        //     System.out.println("2 - NÃO");
        //     System.out.println("Escolha o número: ");
        //     escolhaEmpresa = scanner.nextInt();

        //     if(escolhaEmpresa == 1) escolherEmpresa = true;

        // } while (escolhaEmpresa < 1 || escolhaEmpresa > 2);

        // int numEmpresa;
    
        // if (escolherEmpresa) {
        //     listar.listarEmpresas(manager);

        //     do {
        //         System.out.println("\nEscolha o número da Empresa: ");
        //         numEmpresa = scanner.nextInt();

        //         if(numEmpresa >= 0 && numEmpresa <= empresas.size()) {
        //             empresa = empresas.get(numEmpresa);
        //         } else System.out.println("Número inválido, tente novamente.");

        //     } while (numEmpresa < 0 || numEmpresa > empresas.size());
        // }
        estagio.setDataInicio(dataInicio == "" ? estagio.getDataInicio() : dataInicio);
        estagio.setDataFim(dataFim == "" ? estagio.getDataFim() : dataFim);
        estagio.setCargaHoraria(cargaHoraria == "" ? estagio.getCargaHoraria() : cargaHoraria);
        estagio.setStatus(status == 1 ? "Em andamento" : "Finalizado");
        
        estagio.setAlunoEstagio(!escolherAluno ? estagio.getAlunoEstagio() : aluno);
        estagio.setOrientadorEstagio(!escolherAluno ? estagio.getAlunoEstagio().getOrientadorAluno() : aluno.getOrientadorAluno());
        estagio.setEmpresaEstagio(!escolherAluno ? estagio.getAlunoEstagio().getEmpresaAluno() : aluno.getEmpresaAluno());

        manager.getTransaction().begin();
        manager.merge(estagio);
        manager.getTransaction().commit();

        return "\nEstagio atualizado.";
    }
}
