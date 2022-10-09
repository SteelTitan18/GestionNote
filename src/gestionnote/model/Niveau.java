/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author steeltitan
 */
@Entity
@Table(name="niveaux")
public class Niveau implements Serializable{
    
    @Id
    private int numero;
    
    @Column(name="libelle", length=10)
    private String libelle;
    
    @Column(name="description", length=100)
    private String description;
    
    private static List <Niveau> liste = new ArrayList<>();
    
    public Niveau(){
        
    }
    
    public Niveau(int numero){
        this.numero = numero;
    }
    
    public Niveau(String libelle, int numero, String description){
        this.libelle = libelle;
        this.numero = numero;
        this.description = description;  
    }
    
    
    
    
    
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    
    public String getLibelle(){
        return libelle;
    }
    
    public void setNumero(int numero){
        this.numero = numero;
    }
    
    public int getNumero(){
        return numero;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public String getDescription(){
        return description;
    }
    
    
    
    
    
    @Override
    public String toString(){     
        return "ID : "+ this.numero +" => Niveau " + this.libelle; 
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.numero;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Niveau other = (Niveau) obj;
        return this.numero == other.numero;
    }
}

