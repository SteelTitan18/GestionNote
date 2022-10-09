/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author steeltitan
 */
@Entity
@DiscriminatorValue("eleve")
public class Eleve extends Personne{
    private static List<Eleve> listeElev = new ArrayList<>();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_classe")
    private Classe classe;  
    
//    @OneToMany
//    private List<Note> listeNote = new ArrayList<>();
    
    
    
    public Eleve (){
        
    }
    
    public Eleve (String nom, String prenom, String sexe, long numMatric, int age, Classe classe){
        super(nom, prenom, sexe, numMatric, age);
        this.classe = classe;
    }
    
    public Eleve (long numMatric){
        this.numMatric = numMatric;
    }
    
    
    
    
    public Classe getClasse(){
        return this.classe;
    }
    public void setClasse(Classe classe){
        this.classe = classe;
    }

    @Override
    public String toString(){
        return "Numéro matricule : " + this.numMatric + " => Elève : Nom : " 
                + this.nom + "\tPrénom : " + this.prenom + "\tAge = " +
                this.age + "\tSexe : " + this.sexe + "\tClasse : " + this.classe.getLibelle() + ".";
    }

    
    
    
}
