/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author steeltitan
 */
@Entity
@Table(name="classes")
public class Classe implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_classe;
    
    @Column(name = "subdivision", length = 1)
    private String subdivision;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_niveau")
    private Niveau niveau;
    
    
    
    
    public Classe (){
        
    }
    
    public Classe (Niveau niveau, String subdivision){
        this.niveau = niveau;
        this.subdivision = subdivision;
    }
    
    
    
    public void setNiveau(Niveau niveau){
        this.niveau = niveau;
    }
    public Niveau getNiveau (){
        return this.niveau;
    }
    
    public void setSubdividion(String subdivision){
        this.subdivision = subdivision;
    }
    public String getSubdivision (){
        return this.subdivision;
    }
    
    public String getLibelle (){
        return this.getNiveau().getLibelle() + ' ' + this.getSubdivision();
    }
    
    public long getId_classe() {
        return id_classe;
    }

    public void setId_classe(long id_classe) {
        this.id_classe = id_classe;
    }
    

    
    @Override
    public String toString(){
        return "ID : "+ this.id_classe + " => " + this.getLibelle();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (int) (this.id_classe ^ (this.id_classe >>> 32));
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
        final Classe other = (Classe) obj;
        return this.id_classe == other.id_classe;
    }

    
    
    
}
