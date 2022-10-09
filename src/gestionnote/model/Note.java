/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.model;

import gestionnote.dao.INoteDao;
import gestionnote.dao.NoteDaoImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author steeltitan
 */
@Entity
@Table(name="notes")
public class Note implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_note;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_evaluation")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Evaluation evaluation;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_eleve")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Eleve eleve;
    
    @Column(name="valeur")
    private double valeur;
    private static List<Note> listeNote = new ArrayList <>();
    
    
    public Note(){
        
    }
    
    public Note (Evaluation evaluation, Eleve eleve, double valeur){
        this.evaluation = evaluation;
        this.eleve = eleve;
        this.valeur = valeur;
    }
    
    public Note (long id, Evaluation evaluation, Eleve eleve, double valeur){
        this.id_note = id;
        this.evaluation = evaluation;
        this.eleve = eleve;
        this.valeur = valeur;
    }

    
    
    
    public static List<Note> getListeNote() {
        return listeNote;
    }

    public static void setListeNote(List<Note> listeNote) {
        Note.listeNote = listeNote;
    }

    public long getId_note() {
        return id_note;
    }

    public void setId_note(long id_note) {
        this.id_note = id_note;
    }

    
    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        INoteDao noteDao = new NoteDaoImpl();
        this.valeur = valeur;
        noteDao.setNote(this, valeur);
    }
    
    
    
    
    @Override
    public String toString(){
        return "ID : " + this.id_note + " => Valeur = " + this.valeur + "\tEleve : " + this.eleve.getNom()+ " "
                + this.eleve.getPrenom()+ " " + this.eleve.getNumMatric() 
                + " " + this.eleve.getClasse().getLibelle() 
                + "\tEvaluation : " + this.getEvaluation().getType().getLibelle()+" de "+
                this.evaluation.getCours().getEnseignement().getMatiere().getLibelle() 
                + "\tPoids : "+ this.evaluation.getPoids() + "\tPeriode "+ this.getEvaluation().getPeriode() + "\n";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (int) (this.id_note ^ (this.id_note >>> 32));
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
        final Note other = (Note) obj;
        return this.id_note == other.id_note;
    }

    
}
