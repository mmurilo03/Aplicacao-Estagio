/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.jpa.projectjpa.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author m
 */

@Entity
@Table(name = "tb_aluno")
public class Aluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String matricula;
    private String curso;
    
    @OneToOne(mappedBy = "alunoEstagio")
    private Estagio Estagio;

    @ManyToOne
    private Empresa empresaAluno;

    @ManyToOne
    private Orientador orientadorAluno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Estagio getEstagio() {
        return Estagio;
    }

    public void setEstagio(Estagio Estagio) {
        this.Estagio = Estagio;
    }

    public Empresa getEmpresaAluno() {
        return empresaAluno;
    }

    public void setEmpresaAluno(Empresa empresaAluno) {
        this.empresaAluno = empresaAluno;
    }

    public Orientador getOrientadorAluno() {
        return orientadorAluno;
    }

    public void setOrientadorAluno(Orientador orientadorAluno) {
        this.orientadorAluno = orientadorAluno;
    }
    
    
}
