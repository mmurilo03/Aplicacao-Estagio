/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.jpa.projectjpa.factory;

import javax.persistence.Persistence;

/**
 *
 * @author cfontes
 */
public class GenerateTables {
    
    public static void main(String[] args) {
        Persistence.createEntityManagerFactory("my_persistence_unit");
    
    }
    
}
