 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.model;

import gestionnote.dao.EvaluationDaoImpl;
import gestionnote.dao.IEvaluationDao;
import java.io.Serializable;
import java.time.LocalDate;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author steeltitan
 */
@Entity
@Table(name="evaluations")
public class Evaluation implements Serializable{
    private static List <Evaluation> listeEval = new ArrayList<>();
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_evaluation;
    
    @OneToOne
    private Cours cours;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_type")
    private Type type;
    
    @Column(name="poids")
    private double poids = 1;
    
    @Column(name="date")
    private LocalDate date;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_periode")
    private Periode periode;
    
    //private List<Eleve> listeElev = new ArrayList<>();
    //private List<Note> listeNote = new ArrayList<>();
    
    public Evaluation(){
        
    }
    
    public Evaluation(Cours cours, Type type, double poids, LocalDate date,  Periode periode){
        this.poids = poids;
        this.cours = cours;
        this.date = date;
        this.type = type;
        this.periode = periode;
    }
    
    public Evaluation(Cours cours, Type type, double poids,  Periode periode){
        this.poids = poids;
        this.cours = cours;
        this.type = type;
        this.periode = periode;
    }
    
    public Evaluation(Cours cours, Type type,  Periode periode){
        this.cours = cours;
        this.type = type;
        this.periode = periode;
    }
    
    public static void afficherTout(){
        int cpt=1;
        for (Evaluation evaluation : listeEval) {
            System.out.println(cpt++ + ". " + evaluation);
        }
    }
    
    public static void ajouter(Evaluation evaluation){
        listeEval.add(evaluation);
    }
    
    
    public static List<Evaluation> getListeEval() {
        return listeEval;
    }

    public static void setListeEval(List<Evaluation> listeEval) {
        Evaluation.listeEval = listeEval;
    }
    
    public long getId_evaluation() {
        return id_evaluation;
    }

    public void setId_evaluation(long id_evaluation) {
        this.id_evaluation = id_evaluation;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        IEvaluationDao evaluationDao = new EvaluationDaoImpl();
        this.poids = poids;
        evaluationDao.setPoids(this, poids);
    }
    
    public Cours getCours(){
        return this.cours;
    }
    public void setCours(Cours cours){
        this.cours = cours;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Periode getPeriode() {
        return periode;
    }

    public void setPeriode(Periode periode) {
        this.periode = periode;
    }
    
    

    
    @Override
    public String toString(){
        return "ID : " + this.id_evaluation + " => " +this.type + "\tCours " + this.cours + "\tDate :"
                + "" + this.getDate()+  "\tPoids : " + this.poids + "\tPériode : " + this.periode;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.id_evaluation ^ (this.id_evaluation >>> 32));
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
        final Evaluation other = (Evaluation) obj;
        return this.id_evaluation == other.id_evaluation;
    }
}
