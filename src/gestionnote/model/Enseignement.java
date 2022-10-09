/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.model;

import gestionnote.dao.EnseignementDaoImpl;
import gestionnote.dao.IEnseignementDao;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author steeltitan
 */
@Entity
@Table(name="enseignements")
public class Enseignement implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_enseignement;
    
    @OneToOne
    private Matiere matiere;
    
    @OneToOne
    private Niveau niveau;
    
    @Column(name="coefficient")
    private int coefficient;
    
    public Enseignement(){
        
    }
    
    public Enseignement (Matiere matiere, Niveau niveau, int coefficient) {
        this.matiere = matiere;
        this.niveau = niveau;
        this.coefficient = coefficient;
    }
    
    public Niveau getNiveau(){
        return this.niveau;
    }
    void setNiveau(Niveau niveau){
        this.niveau = niveau;
    }
    
    public Matiere getMatiere(){
        return this.matiere;
    }
    void setMatiere(Matiere matiere){
        this.matiere = matiere;
    }
    
    String getLibelle(){
        return this.matiere.getLibelle() + " niveau " + this.niveau.getLibelle();
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        IEnseignementDao enseignementDao = new EnseignementDaoImpl();
        this.coefficient = coefficient;
        enseignementDao.setCoefficient(this, coefficient);
    }

    public long getId_enseignement() {
        return id_enseignement;
    }

    public void setId_enseignement(long id_enseignement) {
        this.id_enseignement = id_enseignement;
    }
    

    
    
    @Override
    public String toString (){
        return "ID : "+ this.id_enseignement + " => " + this.matiere.getLibelle() + " niveau " + this.niveau.getLibelle();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.id_enseignement ^ (this.id_enseignement >>> 32));
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
        final Enseignement other = (Enseignement) obj;
        return this.id_enseignement == other.id_enseignement;
    }
    
}
