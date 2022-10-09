/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.model;

import gestionnote.dao.CoursDaoImpl;
import gestionnote.dao.ICoursDao;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 *
 * @author steeltitan
 */
@Entity
@Table(name = "cours")
public class Cours implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @OneToOne
    private Enseignement enseignement;
    
    @OneToOne
    private Classe classe;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_enseignant")
    private Enseignant enseignant;
    
    
    
    
    public Cours (){
        
    }
    
    public Cours (Enseignement enseignement, Classe classe, Enseignant enseignant){
        this.classe = classe;
        this.enseignant = enseignant;
        this.enseignement = enseignement;
    }
    
    public Cours (Enseignement enseignement, Classe classe){
        this(enseignement, classe, null);
    }
    
    
    
    public Enseignement getEnseignement(){
        return this.enseignement;
    }
    
    public void setEnseignement(Enseignement enseignement){
        this.enseignement = enseignement;
    }
    
    public Classe getClasse(){
        return this.classe;
    }
    public void setClasse(Classe classe){
        this.classe = classe;
    }
    
    public Enseignant getEnseignant(){
        return this.enseignant;
    }
    public void setEnseignant(Enseignant enseignant){
        ICoursDao coursDao = new CoursDaoImpl();
        this.enseignant = enseignant;
        coursDao.setEnseignant(enseignant, this);
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
    
    @Override
    public String toString(){
        return "ID : "+ this.id + " => " + this.enseignement.getMatiere().getLibelle()
                + "\tNiveau : " +this.getClasse() + "\tEnseignant : " + this.getEnseignant();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Cours other = (Cours) obj;
        return this.id == other.id;
    }

    
    
    
}
