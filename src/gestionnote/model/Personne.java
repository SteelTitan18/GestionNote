/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.model;

import gestionnote.dao.IPersonneDao;
import gestionnote.dao.PersonneDaoImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author steeltitan
 */
@Entity
@Table(name="personnes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="personne_type")
public class Personne implements Serializable{
    @Id
    protected long numMatric;
    
    @Column(name="nom")
    protected String nom;
    
    @Column(name="prenom")
    protected String prenom;
    
    @Column(name="sexe")
    protected String sexe;
    
    @Column(name="age")
    protected int age;  
    
    protected static List<Personne> listePers = new ArrayList<>();
    
    public Personne (){
        
    }
    
    public Personne(long numMatric){
        this.numMatric = numMatric;
    }
    
    public Personne(String nom, String prenom, String sexe, long numMatric, int age){
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.age = age;
        this.numMatric = numMatric;
    }
    
    
    
    public static void afficherTout(){
        for (Personne personne : listePers){
            System.out.println(personne);
        }
    }
    
    public static void ajouter(Personne personne){
        listePers.add(personne);
    }
    
    public static void ajouter(String nom, String prenom, String sexe, long numMatric, int age){
        Personne personne = new Personne(nom, prenom, sexe, numMatric, age);
        ajouter(personne);
    }
    
    public static void supprimer (Personne personne){
        listePers.remove(personne);
    }
    
    public static void supprimer (long numMatric){
        Personne personne = new Personne(numMatric);
        supprimer(personne);
    }
    
    
    
    
    public static void setListePers(List<Personne> _listePers){
        listePers = _listePers;
    }
    
    public static List<Personne> getListePers(){
        return listePers;
    }
    
    
    public void setNom(String nom){
        IPersonneDao personneDao = new PersonneDaoImpl();
        this.nom = nom;
        personneDao.setNom(this, nom);
    }
    public String getNom(){
        return this.nom;
    }

    public int getAge() {
        IPersonneDao personneDao = new PersonneDaoImpl();
        return age;
    }

    public void setAge(int age) {
        IPersonneDao personneDao = new PersonneDaoImpl();
        this.age = age;
        personneDao.setAge(this, age);
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        IPersonneDao personneDao = new PersonneDaoImpl();
        this.sexe = sexe;
        personneDao.setSexe(this, sexe);
    }
    
    public void setPrenom(String prenom){
        IPersonneDao personneDao = new PersonneDaoImpl();
        this.prenom = prenom;
        personneDao.setPrenom(this, prenom);
    }
    public String getPrenom(){
        return this.prenom;
    }
    
    public void setNumMatric(long numMatric){
        IPersonneDao personneDao = new PersonneDaoImpl();
        this.numMatric = numMatric;
        personneDao.setNumMatric(this, numMatric);
    }
    public long getNumMatric(){
        return this.numMatric;
    }
    
    
    
    @Override
    public String toString(){
        return "Numéro matricule : " + this.numMatric + " => Personne : Nom : " 
                + this.nom + "\tPrénom : " + this.prenom + "\tAge = " +
                this.age + "\tSexe : " + this.sexe;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.numMatric);
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
        final Personne other = (Personne) obj;
        return Objects.equals(this.numMatric, other.numMatric);
    }
}
