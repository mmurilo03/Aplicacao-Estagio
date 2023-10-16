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
    }

}
