package br.com.jpa.projectjpa.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.jpa.projectjpa.entities.Aluno;
import br.com.jpa.projectjpa.entities.Empresa;
import br.com.jpa.projectjpa.entities.Estagio;
import br.com.jpa.projectjpa.entities.Orientador;

public class Buscar {
    
    public void buscar (EntityManager manager) {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        
        do {
            System.out.println(
                    "\n---------------------\n| BUSCA MENU |\n---------------------\n1 - Busca Aluno\n2 - Busca Orientadores"
                            + "\n3 - Busca Empresas\n4 - Busca Estágios\n0 - Voltar\n");
            System.out.print("\nInsira uma opção: ");

            switch (scanner.nextInt()) {
                case 1:
                    buscarAluno(manager);
                    break;
                case 2:
                    buscarOrientador(manager);
                    break;
                case 3:
                    buscarEmpresa(manager);
                    break;
                case 4:
                    buscarEstagio(manager);
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

    public Map<String, Object> buscarAluno(EntityManager manager) {

        Scanner scanner = new Scanner(System.in);

        String parametro = "vazio";
        int num;
            
        do {
            System.out.println("Qual o parâmetro de busca\n1 - Nome\n2 - Matricula\n3 - Curso");
            num = scanner.nextInt();

            switch(num){
                case 1:
                    parametro = "nome";
                    break;
                case 2:
                    parametro = "matricula";
                    break;
                case 3:
                    parametro = "curso";
                    break;
                default:
                    System.out.println("Número inválido, tente novamente.");
            }

        } while (num < 0 || num > 3);

        scanner.nextLine();
        String valor;
        System.out.print("\nDigite o valor para "+ parametro + ": ");
        valor = scanner.nextLine();

        Query queryAluno = manager.createQuery("FROM Aluno a WHERE LOWER(a." + parametro + ") LIKE :valor");
        queryAluno.setParameter("valor", "%" + valor.toLowerCase() + "%");
        List<Aluno> alunos = queryAluno.getResultList();
        
        System.out.println("\nTodos os Alunos da pesquisa");
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

        Map<String, Object> o = new HashMap<>();

        o.put("alunos", alunos);
        o.put("parametro", parametro);
        o.put("valor", valor);

        return o;
    }

    public Map<String, Object> buscarOrientador(EntityManager manager) {

        Scanner scanner = new Scanner(System.in);

        String parametro = "vazio";
        int num;
            
        do {
            System.out.println("Qual o parâmetro de busca\n1 - Nome\n2 - Matricula");
            num = scanner.nextInt();

            switch(num){
                case 1:
                    parametro = "nome";
                    break;
                case 2:
                    parametro = "matricula";
                    break;
                default:
                    System.out.println("Número inválido, tente novamente.");
            }

        } while (num < 0 || num > 2);

        scanner.nextLine();
        String valor;
        System.out.print("\nDigite o valor para "+ parametro + ": ");
        valor = scanner.nextLine();

        Query queryOrientador = manager.createQuery("FROM Orientador o WHERE LOWER(o." + parametro + ") LIKE :valor");
        queryOrientador.setParameter("valor", "%" + valor.toLowerCase() + "%");
        List<Orientador> orientadores = queryOrientador.getResultList();
        
        System.out.println("\nTodos os Orientadores da pesquisa");
        for (int index = 0; index < orientadores.size(); index++) {
            System.out.println("===========================================");
            System.out.println("Orientador: " + index);
            System.out.println("Nome: " + orientadores.get(index).getNome());
            System.out.println("Matricula: " + orientadores.get(index).getMatricula());
            System.out.println("Número de orientandos: " + orientadores.get(index).getAlunos().size());
            System.out.println("Orientandos: ");
            for (int index2 = 0; index2 < orientadores.get(index).getAlunos().size(); index2++) {
                System.out.println("Nome: " + orientadores.get(index).getAlunos().get(index2).getNome());
            }
        }

        Map<String, Object> o = new HashMap<>();

        o.put("orientadores", orientadores);
        o.put("parametro", parametro);
        o.put("valor", valor);
        
        return o;
    }

    public Map<String, Object> buscarEmpresa(EntityManager manager) {

        Scanner scanner = new Scanner(System.in);

        String parametro = "vazio";
        int num;
            
        do {
            System.out.println("Qual o parâmetro de busca\n1 - Nome\n2 - CNPJ");
            num = scanner.nextInt();

            switch(num){
                case 1:
                    parametro = "nome";
                    break;
                case 2:
                    parametro = "cnpj";
                    break;
                default:
                    System.out.println("Número inválido, tente novamente.");
            }

        } while (num < 0 || num > 2);

        scanner.nextLine();
        String valor;
        System.out.print("\nDigite o valor para "+ parametro + ": ");
        valor = scanner.nextLine();

        Query queryEmpresa = manager.createQuery("FROM Empresa e WHERE LOWER(e." + parametro + ") LIKE :valor");
        queryEmpresa.setParameter("valor", "%" + valor.toLowerCase() + "%");
        List<Empresa> empresas = queryEmpresa.getResultList();
        
        System.out.println("\nTodas as Empresas da pesquisa");
        for (int index = 0; index < empresas.size(); index++) {
            System.out.println("===========================================");
            System.out.println("Empresa: " + index);
            System.out.println("Nome: " + empresas.get(index).getNome());
            System.out.println("CNPJ: " + empresas.get(index).getCnpj());
            System.out.println("Alunos estagiários: ");
            for (int index2 = 0; index2 < empresas.get(index).getAlunos().size(); index2++) {
                System.out.println("Nome: " + empresas.get(index).getAlunos().get(index2).getNome());
            }
        }

        Map<String, Object> o = new HashMap<>();

        o.put("empresas", empresas);
        o.put("parametro", parametro);
        o.put("valor", valor);
        
        return o;
    }

    public Map<String, Object> buscarEstagio(EntityManager manager) {

        Scanner scanner = new Scanner(System.in);

        String parametro = "vazio";
        int num;
            
        do {
            System.out.println("Qual o parâmetro de busca\n1 - Data Inicio\n2 - Data Fim\n3 - Carga Horária\n4 - Status"
            + "\n5 - Aluno do estágio \n6 - Orientador do estágio\n7 - Empresa do estágio");
            num = scanner.nextInt();

            switch(num){
                case 1:
                    parametro = "datainicio";
                    break;
                case 2:
                    parametro = "datafim";
                    break;
                case 3:
                    parametro = "cargahoraria";
                    break;
                case 4:
                    parametro = "status";
                    break;
                case 5:
                    parametro = "alunoestagio";
                    break;
                case 6:
                    parametro = "orientadorestagio";
                    break;
                case 7:
                    parametro = "empresaestagio";
                    break;
                default:
                    System.out.println("Número inválido, tente novamente.");
            }

        } while (num < 0 || num > 7);

        scanner.nextLine();
        List<Estagio> estagiosRetorno = new ArrayList<>();
        String valorRetorno = "vazio";

        if (num <= 4) {
            System.out.print("\nDigite o valor para "+ parametro + ": ");
            String valor = scanner.nextLine();
            Query queryEstagio = manager.createQuery("FROM Estagio e WHERE LOWER(e." + parametro + ") LIKE :valor");
            queryEstagio.setParameter("valor", "%" + valor.toLowerCase() + "%");
            List<Estagio> estagios = queryEstagio.getResultList();
            exibirEstagios(estagios);
            valorRetorno = valor;
        } else {
            Query queryEstagio;
            switch (num) {
                case 5:
                    Map<String, Object> resultAluno = buscarAluno(manager);

                    String parametroPesquisaAluno = (String) resultAluno.get("parametro");
                    String valorPesquisaAluno = (String) resultAluno.get("valor");
                    valorRetorno = valorPesquisaAluno;
                    queryEstagio = manager.createQuery("SELECT e FROM Estagio e JOIN FETCH e.alunoEstagio" + " p WHERE LOWER(p." + parametroPesquisaAluno + ") LIKE :valor");
                    queryEstagio.setParameter("valor", "%" + valorPesquisaAluno.toLowerCase() + "%");
                    List<Estagio> estagiosAluno = queryEstagio.getResultList();
                    exibirEstagios(estagiosAluno);
                    estagiosRetorno = estagiosAluno;
                    break;
                case 6:
                    Map<String, Object> resultOrientador = buscarOrientador(manager);

                    String parametroPesquisaOrientador = (String) resultOrientador.get("parametro");
                    String valorPesquisaOrientador = (String) resultOrientador.get("valor");
                    valorRetorno = valorPesquisaOrientador;
                    queryEstagio = manager.createQuery("SELECT e FROM Estagio e JOIN FETCH e.orientadorEstagio" + " p WHERE LOWER(p." + parametroPesquisaOrientador + ") LIKE :valor");
                    queryEstagio.setParameter("valor", "%" + valorPesquisaOrientador.toLowerCase() + "%");
                    List<Estagio> estagiosOrientador = queryEstagio.getResultList();
                    exibirEstagios(estagiosOrientador);
                    estagiosRetorno = estagiosOrientador;
                    break;
                case 7:
                    Map<String, Object> resultEmpresa = buscarEmpresa(manager);

                    String parametroPesquisaEmpresa = (String) resultEmpresa.get("parametro");
                    String valorPesquisaEmpresa = (String) resultEmpresa.get("valor");
                    valorRetorno = valorPesquisaEmpresa;
                    queryEstagio = manager.createQuery("SELECT e FROM Estagio e JOIN FETCH e.empresaEstagio" + " p WHERE LOWER(p." + parametroPesquisaEmpresa + ") LIKE :valor");
                    queryEstagio.setParameter("valor", "%" + valorPesquisaEmpresa.toLowerCase() + "%");
                    List<Estagio> estagiosEmpresa = queryEstagio.getResultList();
                    exibirEstagios(estagiosEmpresa);
                    estagiosRetorno = estagiosEmpresa;
                    break;
                default:
                    break;
            }
        }
        Map<String, Object> o = new HashMap<>();

        o.put("estagios", estagiosRetorno);
        o.put("parametro", parametro);
        o.put("valor", valorRetorno);

        return o;
    }

    public void exibirEstagios(List<Estagio> estagios) {
        System.out.println("\nTodos os Estágios da pesquisa");
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