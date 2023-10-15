/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.jpa.projectjpa.factory;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.jpa.projectjpa.controllers.Atualizar;
import br.com.jpa.projectjpa.controllers.Buscar;
import br.com.jpa.projectjpa.controllers.Cadastrar;
import br.com.jpa.projectjpa.controllers.Listar;
import br.com.jpa.projectjpa.controllers.Remover;

/**
 *
 * @author m
 */
public class Factory {

    public static void main(String[] args) {
        //Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager manager = factory.createEntityManager();
        
        boolean run = true;

        Cadastrar cadastro = new Cadastrar();
        Atualizar atualizacao = new Atualizar();
        Remover remover = new Remover();
        Listar listar = new Listar();
        Buscar buscar = new Buscar();

        try (Scanner scanner = new Scanner(System.in)) {
            do {
                System.out.println(
                        "\n-------------\n| MAIN MENU |\n-------------\n1 - Cadastrar\n2 - Atualizar"
                                + "\n3 - Remover\n4 - Listar\n5 - Filtragem\n0 - Sair do programa\n");
                System.out.print("\nInsira uma opção: ");

                switch (scanner.nextInt()) {
                    case 1:
                        cadastro.cadastrar(manager);
                        break;
                    case 2:
                        atualizacao.atualizar(manager);
                        break;
                    case 3:
                        remover.remover(manager);
                        break;
                    case 4:
                        listar.listar(manager);
                        break;
                    case 5:
                        buscar.buscar(manager);
                        break;
                    case 0:
                        System.out.println("\nPrograma Finalizado.\n");
                        run = false;
                        scanner.close();
                        manager.close();
                        break;
                    default:
                        System.out.println("\nInsira um valor válido.");
                        break;
                }
            } while (run);
        }

        //Set Orientador
        /*Orientador orientador1 = new Orientador();
        orientador1.setMatricula("202122010000");
        orientador1.setNome("Paulo");
        
        //Set Empresa
        Empresa empresa1 = new Empresa();
        empresa1.setNome("AM4");
        empresa1.setCnpj("777777");

        //Set Aluno
        Aluno aluno1 = new Aluno();
        aluno1.setNome("João");
        aluno1.setCurso("ADS");
        aluno1.setMatricula("123");

        Aluno aluno2 = new Aluno();
        aluno2.setNome("João 2");
        aluno2.setCurso("ADS");
        aluno2.setMatricula("123");

        aluno1.setEmpresaAluno(empresa1);
        aluno2.setEmpresaAluno(empresa1);
        aluno1.setOrientadorAluno(orientador1);
        aluno2.setOrientadorAluno(orientador1);
        
        //Set Estagio
        Estagio estagio1 = new Estagio();
        estagio1.setAlunoEstagio(aluno1);
        estagio1.setEmpresaEstagio(empresa1);
        estagio1.setOrientadorEstagio(orientador1);
        estagio1.setDataInicio("12/10/2023");
        estagio1.setDataFim("12/12/2023");
        estagio1.setCargaHoraria("100");
        estagio1.setStatus("Em andamento");*/

        // manager.getTransaction().begin();
        // manager.persist(orientador1);
        // manager.persist(empresa1);
        // manager.persist(aluno1);
        // manager.persist(aluno2);
        // manager.persist(estagio1);
        // manager.getTransaction().commit();

        // manager.close();

        // Query query = manager.createQuery("FROM Orientador");
        // List<Orientador> orientadores = query.getResultList();

        // for (Orientador o : orientadores) {
        //     List<Aluno> alunos = o.getAlunos();

        //     System.out.println("Orientador: " + o.getNome());
        //     System.out.println("Alunos:");
        //     for (Aluno a : alunos) {
        //         System.out.println("Nome: " + a.getNome());
        //         System.out.println("//");
        //         System.out.println("Matrícula: " + a.getMatricula());
        //     }
        // }
        // Scanner scanner = new Scanner(System.in);
        // scanner.nextLine();
        

        
        /* persistindo um novo produto na categoria 1
        manager.getTransaction().begin();
        
        Categoria c = manager.find(Categoria.class, 1L);
        manager.persist(p);
        p.getCategorias().add(c);
        manager.getTransaction().commit();
        manager.close();*/
        
       /* Listando um coleção de produtos
        manager.getTransaction().begin();
        
        Query query = manager.createQuery("FROM Produto");
        
        List<Produto> produtos = query.getResultList();
        
       
        manager.getTransaction().commit();
        manager.close();
        
        
        for (Produto p: produtos) {
            System.out.println("Nome: " + p.getName());
            System.out.println("Descrição: " + p.getDescription());
            System.out.println("Preço: " + p.getPrice());
            
            System.out.println();
        }*/
        
        
        /* Consultando os produtos com as categorias
       manager.getTransaction().begin();
        
        Query query = manager.createQuery("SELECT p FROM Produto p JOIN FETCH p.categorias WHERE p IN p");
        
        List<Produto> produtos = query.getResultList();
        
        manager.getTransaction().commit();
        //manager.close();
        
        for (Produto p: produtos) {
            System.out.println("Nome: " + p.getName());
            System.out.println("Descrição: " + p.getDescription());
            System.out.println("Preço: " + p.getPrice());
            System.out.println("Categorias: " + p.getCategorias().toString());
            System.out.println();
        }*/
        /*
        Departmento d = new Departmento();
        d.setName("Financeiro");
        Departmento d2 = new Departmento();
        d2.setName("Recursos Humanos");
        
        Funcionario f = new Funcionario();
        f.setName("Fulano de Tal");
        f.setMatricula("123456-0");
        f.setCpf("001.002.003-00");
        d.getFuncionarios().add(f);
        
        Funcionario f2 = new Funcionario();
        f2.setName("Fulano de Tal");
        f2.setMatricula("123456-0");
        f2.setCpf("001.002.003-00");
        d.getFuncionarios().add(f2);
        
        Funcionario f3 = new Funcionario();
        f3.setName("Fulano de Tal");
        f3.setMatricula("123456-0");
        f3.setCpf("001.002.003-00");
        d2.getFuncionarios().add(f3);
       
        manager.getTransaction().begin();
        
        manager.persist(f);
        manager.persist(f2);
        manager.persist(f3);
        
        manager.persist(d);
        manager.persist(d2);
        
        manager.getTransaction().commit();
        manager.close();*/
        
        /*manager.getTransaction().begin();
        
        Long id = 4L;
        
        Query query = manager.createQuery("From Funcionario");
        
        Query query2 = manager.createQuery("From Funcionario f WHERE f.id = :id");
        query2.setParameter("id", id);
        
        List<Funcionario> funcionarios = query.getResultList();
        
        Funcionario fun = (Funcionario) query2.getSingleResult();
       
        
        manager.getTransaction().commit();
        manager.close();
        
        System.out.println();
        for (Funcionario f: funcionarios) {
            System.out.println("Nome: "+f.getName());
            System.out.println("Matricula: "+f.getMatricula() );
            System.out.println("CPF: " +f.getCpf());
            System.out.println();
        }
        System.out.println();
        System.out.println("Funcionário pesquisado: " + "["+fun.getName() + "]");*/
        
        //###########################################################################
        
        /*Departmento d = new Departmento();
        d.setName("Financeiro");
        Departmento d2 = new Departmento();
        d2.setName("Recursos Humanos");
        
        Funcionario f = new Funcionario();
        f.setName("Fulano de Tal");
        f.setMatricula("123456-0");
        f.setCpf("001.002.003-00");
        d.getFuncionarios().add(f);
        f.setDepartamento(d);
        
        Funcionario f2 = new Funcionario();
        f2.setName("Fulano de Tal");
        f2.setMatricula("123456-0");
        f2.setCpf("001.002.003-00");
        d.getFuncionarios().add(f2);
        f2.setDepartamento(d2);
        
        Funcionario f3 = new Funcionario();
        f3.setName("Fulano de Tal");
        f3.setMatricula("123456-0");
        f3.setCpf("001.002.003-00");
        d2.getFuncionarios().add(f3);
        f3.setDepartamento(d2);
       
        manager.getTransaction().begin();
        
        manager.persist(f);
        manager.persist(f2);
        manager.persist(f3);
        
        manager.persist(d);
        manager.persist(d2);
        
        manager.getTransaction().commit();
        manager.close();*/
        
        /*manager.getTransaction().begin();
        
        //Manipulação de funcionários
        Long id = 1L;
        String departamento = "Recursos Humanos";
        
        Query query = manager.createQuery("FROM Funcionario");
        List<Funcionario> list = query.getResultList();
                
        Query query2 = manager.createQuery("FROM Funcionario p WHERE p.id = :id");
        query2.setParameter("id", id);
        Funcionario funcionario = (Funcionario) query2.getSingleResult();
        
        Query query3 = manager.createQuery("FROM Funcionario f WHERE f.departamento.name = :departamento");
        query3.setParameter("departamento", departamento);
        List<Funcionario> list2 = query3.getResultList();
        
        //Manipulação de departamentos
        
        Query query4 = manager.createQuery("FROM Departmento");
        List<Departmento> list3 = query4.getResultList();
        
        Query query5 = manager.createQuery("SELECT d FROM Departmento d JOIN FETCH d.funcionarios WHERE d IN d");
        List<Departmento> list4 = query5.getResultList();
        
        //pegando um departamento com os seus funcionarios pela id
        Query query6 = manager.createQuery("SELECT d FROM Departmento d JOIN FETCH d.funcionarios WHERE d.id = 2");
        Departmento dep = (Departmento) query6.getSingleResult();
        //Ou usando o setParameter
        Long id = 2L
        Query query7 = manager.createQuery("SELECT d FROM Departmento d JOIN FETCH d.funcionarios WHERE d.id = :id");
        query7.setParameter("id", id);
        Departmento dep1 = (Departmento) query7.getSingleResult();
        
       
        manager.getTransaction().commit();
        //manager.close();
        /*System.out.println();
        System.out.println("Todos os funcionários");
        for (Funcionario f: list){
            System.out.println("Nome: " + f.getName());
            System.out.println("Matricula: " + f.getMatricula());
            System.out.println("CPF: " + f.getCpf());
            System.out.println();
        }
        
        System.out.println();
        System.out.println("Apenas um funcionário");
        System.out.println("Nome: " + funcionario.getName());
        System.out.println("Matrícula: " + funcionario.getMatricula());
        System.out.println("CPF: " + funcionario.getCpf());
        
        System.out.println();
        System.out.println();
        System.out.println("Todos os funcionários");
        for (Funcionario f2: list2){
            System.out.println("Nome: " + f2.getName());
            System.out.println("Matricula: " + f2.getMatricula());
            System.out.println("CPF: " + f2.getCpf());
            System.out.println("Departamento: " + f2.getDepartamento().getName());
            System.out.println();
        }*/
        
        
        /*System.out.println();
        System.out.println();
        System.out.println("TODOS OS DEPARTAMENTOS");
        for (Departmento d: list3){
            System.out.println("Nome: " + d.getName());
        }
        
        System.out.println();
        System.out.println("OS FUNCIONÁRIOS");
        for (Departmento d2: list4) {
            System.out.println("Categorias: " + d2.getFuncionarios().toString());
            System.out.println();
        }
        
        System.out.println();
        System.out.println();
        System.out.println("UM DEPARTAMENTO");
        System.out.println();
        System.out.println("Nome departamento: " + dep.getName());
        for (Funcionario f: dep.getFuncionarios()){
            System.out.println("Nome funcionários: " + f.getName());
        }*/
        
        //Aqui observem no banco de dados que gera um tabelão com todas as colunas,
        //Mas a desvantagem disso é que muitas colunas ficaram nulas
        //Executem o codigo e analisem a tabela pessoa
        
        /*Pessoa p = new Pessoa();
        p.setNome("A Pessoa");
        p.setTipo("P");
        
        PessoaFisica pf = new PessoaFisica();
        pf.setNome("Ciclano de Fulano de Tal");
        pf.setCpf("001.002.003-00");
        pf.setTipo("F");
        
        PessoaJuridica pj = new PessoaJuridica();
        pj.setNome("Empresa Tal");
        pj.setCnpj("00.0001.0003/0001-00");
        pj.setTipo("J");
        
        
        
        manager.getTransaction().begin();
        
        manager.persist(p);
        manager.persist(pf);
        manager.persist(pj);
        
        manager.getTransaction().commit();
        manager.close();*/
        
        
        /*PessoaFisica pf = new PessoaFisica();
        PessoaJuridica pj = new PessoaJuridica();
        
        pf.setNome("Fulano Fisica");
        pf.setCpf("000.001.002-01");
        
        pj.setNome("Ciclano Juridico");
        pj.setCnpj("00.001.002/0001-01");
        
        
        
        
        manager.getTransaction().begin();
        
        manager.persist(pf);
        manager.persist(pj);
        
        manager.getTransaction().commit();
        manager.close();*/
        
        
        /*Endereco enderecoF = new Endereco();
        enderecoF.setLogradouro("Rua das Tapiocas");
        enderecoF.setNumero(24);
        enderecoF.setBairro("Bairro das Tapiocas");
        enderecoF.setCep("58.900-000");
        enderecoF.setUf("PB");
        
        Endereco enderecoF2 = new Endereco();
        enderecoF2.setLogradouro("Rua das Tapiocas 2");
        enderecoF2.setNumero(25);
        enderecoF2.setBairro("Bairro das Tapiocas 2");
        enderecoF2.setCep("58.900-000");
        enderecoF2.setUf("PB");
        
        Endereco enderecoF3 = new Endereco();
        enderecoF3.setLogradouro("Rua das Tapiocas 3");
        enderecoF3.setNumero(26);
        enderecoF3.setBairro("Bairro das Tapiocas 3");
        enderecoF3.setCep("58.900-000");
        enderecoF3.setUf("PB");
        
        
        Departmento d = new Departmento();
        d.setName("Financeiro");
        Departmento d2 = new Departmento();
        d2.setName("Recursos Humanos");
        
        Funcionario f = new Funcionario();
        f.setName("Fulano de Tal");
        f.setMatricula("123456-0");
        f.setCpf("001.002.003-00");
        d.getFuncionarios().add(f);
        f.getEnderecos().add(enderecoF);
        f.setDepartamento(d);
        
        Funcionario f2 = new Funcionario();
        f2.setName("Fulano de Tal");
        f2.setMatricula("123456-0");
        f2.setCpf("001.002.003-00");
        d.getFuncionarios().add(f2);
        f2.getEnderecos().add(enderecoF2);
        f2.setDepartamento(d2);
        
        Funcionario f3 = new Funcionario();
        f3.setName("Fulano de Tal");
        f3.setMatricula("123456-0");
        f3.setCpf("001.002.003-00");
        d2.getFuncionarios().add(f3);
        f3.getEnderecos().add(enderecoF3);
        f3.setDepartamento(d2);
       
        manager.getTransaction().begin();
        
        manager.persist(f);
        manager.persist(f2);
        manager.persist(f3);
        
        manager.persist(d);
        manager.persist(d2);
        
        manager.getTransaction().commit();
        manager.close();*/
        
        
        //deleção de produto e categoria
        /*var p = new Produto();
        p = manager.find(Produto.class, 1L);
        var c = new Categoria();
        c = manager.find(Categoria.class, 1L);
        
        if (c == null) {
            System.out.println();
            System.out.println("Objeto não encontrado");
        } else {
            manager.remove(c);
            System.out.println();
            System.out.println("Objeto removido com sucesso");
        }*/
        
        /*Categoria c = new Categoria();
        c.setName("Jogos");
        Produto p = new Produto();
        p.setName("Notbook Gamer RTX 4060");
        p.setDescription("Bixinnn");
        p.setPrice(2500.0);
        
        p.getCategorias().add(c);
        c.getProdutos().add(p);
        
        manager.persist(c);
        manager.persist(p);*/
        
        //atualizando um funcionario
        
        /*Funcionario f = manager.find(Funcionario.class, 1L);
        
        if (f == null) {
            System.out.println();
            System.out.println("Objeto não encontrado");
        }else {
            System.out.println();
            f.setName("Ciclano da Silva");
            manager.merge(f);
            System.out.println("Objeto atualizado com sucesso");
            System.out.println("NOVO NOME DO OBJETO COM O ID: "+ f.getId() +" " + f.getName());
        }*/
        
        // var d = manager.find(Departmento.class, 1L);
        
        // if (d == null) {
        //     System.out.println();
        //     System.out.println("Objeto não encontrado");
        // }else {
        //     System.out.println();
        //     manager.remove(d);
        //     System.out.println("Objeto removido com sucesso");
        // }

        
        
        
        
    }

}
