package br.com.jpa.projectjpa.controllers;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.persistence.EntityManager;

import br.com.jpa.projectjpa.entities.Aluno;
import br.com.jpa.projectjpa.entities.Empresa;
import br.com.jpa.projectjpa.entities.Estagio;
import br.com.jpa.projectjpa.entities.Orientador;

public class Remover {

    Buscar buscar = new Buscar();
    
    public void remover(EntityManager manager) {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        
        do {
            System.out.println(
                "\n---------------------\n| REMOVER MENU |\n---------------------\n1 - Remover Aluno\n2 - Remover Orientador"
                + "\n3 - Remover Empresa\n4 - Remover Estágio\n0 - Voltar\n");
            System.out.print("\nInsira uma opção: ");

            switch (scanner.nextInt()) {
                case 1:
                    removerAluno(manager);
                    break;
                case 2:
                    removerOrientador(manager);
                    break;
                case 3:
                    removerEmpresa(manager);
                    break;
                case 4:
                    removerEstagio(manager);
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

    public void removerAluno(EntityManager manager) {

        Map<String, Object> resultAluno = buscar.buscarAluno(manager);
        List<Aluno> alunos = (List<Aluno>) resultAluno.get("alunos");
        Scanner scanner = new Scanner(System.in);
        int num;

        Aluno aluno;

        if (alunos.size() > 0) {
            do {
                System.out.println("\nEscolha o número do aluno, digite um número negativo para cancelar: ");
                num = scanner.nextInt();

                if (num < 0) break;

                if(num >= 0 && num <= alunos.size()) {
                    aluno = alunos.get(num);
                    System.out.println("Remover aluno? \n1 - SIM\n2 - NÃO\nDigite: ");
                    int remover = scanner.nextInt();
                    if (remover == 1) {
                        if (aluno.getEstagio() == null) {
                            manager.getTransaction().begin();
                            manager.remove(aluno);
                            aluno.getOrientadorAluno().getAlunos().remove(aluno);
                            aluno.getEmpresaAluno().getAlunos().remove(aluno);
                            manager.merge(aluno.getOrientadorAluno());
                            manager.merge(aluno.getEmpresaAluno());
                            manager.getTransaction().commit();
                            System.out.println("Aluno deletado!");
                        } else {
                            System.out.println("Aluno possui estágio e não pode ser deletado, delete o estágio primeiro");
                        }
                    } 
                } else System.out.println("Número inválido, tente novamente.");

            } while (num < 0 || num > alunos.size());

            if (num < 0) System.out.println("Cancelado pelo usuário");
        }

    } 

    public void removerOrientador(EntityManager manager){

        Map<String, Object> resultOrientador = buscar.buscarOrientador(manager);
        List<Orientador> orientadores = (List<Orientador>) resultOrientador.get("orientadores");
        Scanner scanner = new Scanner(System.in);
        int num;

        Orientador orientador;

        if (orientadores.size() > 0) {
            do {
                System.out.println("\nEscolha o número do orientador, digite um número negativo para cancelar: ");
                num = scanner.nextInt();

                if (num < 0)
                    break;

                if (num >= 0 && num <= orientadores.size()) {
                    orientador = orientadores.get(num);
                    System.out.println("Remover orientador? \n1 - SIM\n2 - NÃO\nDigite: ");
                    int remover = scanner.nextInt();
                    if (remover == 1) {
                        if (orientador.getAlunos().size() == 0 && orientador.getEstagio().size() == 0) {
                            manager.getTransaction().begin();
                            manager.remove(orientador);
                            manager.getTransaction().commit();
                            System.out.println("Orientador deletado!");
                        } else {
                            System.out.println("Orientador possui alunos ou estágios e não pode ser deletado, delete os alunos e estágios primeiro");
                        }
                    }
                } else System.out.println("Número inválido, tente novamente.");

            } while (num < 0 || num > orientadores.size());

            if (num < 0)
                System.out.println("Cancelado pelo usuário");
        }
    }
    
    public void removerEmpresa(EntityManager manager){

        Map<String, Object> resultEmpresa = buscar.buscarEmpresa(manager);
        List<Empresa> empresas = (List<Empresa>) resultEmpresa.get("empresas");
        Scanner scanner = new Scanner(System.in);
        int num;

        Empresa empresa;

        if (empresas.size() > 0) {
            do {
                System.out.println("\nEscolha o número do empresa, digite um número negativo para cancelar: ");
                num = scanner.nextInt();

                if (num < 0)
                    break;

                if (num >= 0 && num <= empresas.size()) {
                    empresa = empresas.get(num);
                    System.out.println("Remover empresa? \n1 - SIM\n2 - NÃO\nDigite: ");
                    int remover = scanner.nextInt();
                    if (remover == 1) {
                        if (empresa.getAlunos().size() == 0 && empresa.getEstagio().size() == 0) {
                            manager.getTransaction().begin();
                            manager.remove(empresa);
                            manager.getTransaction().commit();
                            System.out.println("Empresa deletada!");
                        } else {
                            System.out.println("Empresa possui alunos ou estágios e não pode ser deletado, delete os alunos e estágios primeiro");
                        }
                    }
                } else System.out.println("Número inválido, tente novamente.");

            } while (num < 0 || num > empresas.size());

            if (num < 0)
                System.out.println("Cancelado pelo usuário");
        }
    }
    
    public void removerEstagio(EntityManager manager){
        Map<String, Object> resultEstagio = buscar.buscarEstagio(manager);
        List<Estagio> estagios = (List<Estagio>) resultEstagio.get("estagios");

        Scanner scanner = new Scanner(System.in);
        int num;

        Estagio estagio;

        if (estagios.size() > 0) {
            do {
                System.out.println("\nEscolha o número do estagio, digite um número negativo para cancelar: ");
                num = scanner.nextInt();

                if (num < 0)
                    break;

                if (num >= 0 && num <= estagios.size()) {
                    estagio = estagios.get(num);
                    System.out.println("Remover estágio? \n1 - SIM\n2 - NÃO\nDigite: ");
                    int remover = scanner.nextInt();
                    if (remover == 1) {
                        manager.getTransaction().begin();
                        estagio.getAlunoEstagio().setEstagio(null);
                        manager.merge(estagio.getAlunoEstagio());
                        manager.remove(estagio);
                        manager.getTransaction().commit();
                        System.out.println("Estágio deletado!");
                    }
                } else System.out.println("Número inválido, tente novamente.");
            } while (num < 0 || num > estagios.size());
            if (num < 0) System.out.println("Cancelado pelo usuário");
        }
    }
}
