/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author steeltitan
 */
@Entity
@Table(name="matieres")
public class Matiere implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_matiere;
    
    
    @Column(name="libelle")
    private String libelle;
    
    public Matiere(String libelle){
        this.libelle = libelle;
    }
    
    public Matiere(){
        
    }

    
    
    public long getId_matiere() {
        return id_matiere;
    }

    public void setId_matiere(long id_matiere) {
        this.id_matiere = id_matiere;
    }
    
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    public String getLibelle(){
        return libelle;
    }
    

    @Override
    public String toString(){
        return "ID : " + this.id_matiere + " => " + this.libelle ;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (int) (this.id_matiere ^ (this.id_matiere >>> 32));
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
        final Matiere other = (Matiere) obj;
        return this.id_matiere == other.id_matiere;
    }
}

