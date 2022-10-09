/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.model;

import gestionnote.dao.EnseignantDaoImpl;
import gestionnote.dao.IEnseignantDao;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author steeltitan
 */
@Entity
@DiscriminatorValue("enseignant")
public class Enseignant extends Personne implements Serializable{
    @Column
    private int salaire;
    
    
    
    public Enseignant(){
        
    }
    
    public Enseignant(String nom, String prenom, String sexe,long numMatric, int age, int salaire){
        super(nom, prenom, sexe, numMatric, age);
        this.salaire = salaire;
    }
    
    
    
    
    public void setSalaire(int salaire){
        IEnseignantDao enseignantDao = new EnseignantDaoImpl();
        this.salaire = salaire;
        enseignantDao.setSalaire(this, salaire);
    }
    public int getSalaire(){
        return this.salaire;
    }

    @Override
    public String toString(){
        return "Numéro matricule : " + this.numMatric + " => Elève : Nom : " 
                + this.nom + "\tPrénom : " + this.prenom + "\tAge = " +
                this.age + "\tSexe : " + this.sexe + "\tSalaire = " + this.salaire + '.';
    }
    
}
