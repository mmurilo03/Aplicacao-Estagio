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
@Table(name = "tb_estagio")
public class Estagio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataInicio;
    private String dataFim;
    private String cargaHoraria;
    private String status;

    @OneToOne
    private Aluno alunoEstagio;
    
    @ManyToOne
    private Orientador orientadorEstagio;
    
    @ManyToOne
    private Empresa empresaEstagio;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Aluno getAlunoEstagio() {
        return alunoEstagio;
    }

    public void setAlunoEstagio(Aluno alunoEstagio) {
        this.alunoEstagio = alunoEstagio;
    }

    public Orientador getOrientadorEstagio() {
        return orientadorEstagio;
    }

    public void setOrientadorEstagio(Orientador orientadorEstagio) {
        this.orientadorEstagio = orientadorEstagio;
    }

    public Empresa getEmpresaEstagio() {
        return empresaEstagio;
    }

    public void setEmpresaEstagio(Empresa empresaEstagio) {
        this.empresaEstagio = empresaEstagio;
    }
    
    
}
