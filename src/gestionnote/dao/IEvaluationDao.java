/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestionnote.dao;

import gestionnote.model.Classe;
import gestionnote.model.Enseignement;
import gestionnote.model.Evaluation;
import gestionnote.model.Periode;
import gestionnote.model.Type;
import java.util.List;

/**
 *
 * @author steeltitanrgnk
 */
public interface IEvaluationDao {
    public void afficherTout();
    public void afficher(Evaluation evaluation);
    public Evaluation ajouter(Evaluation evaluation);
    public void supprimer(Evaluation evaluation);
    public void supprimer (long id);
    public List<Evaluation> getEvaluations();
    public List<Evaluation> getEvaluations(Classe classe);
    public Evaluation getEvaluation(long id);
    public Evaluation getEvaluation(Enseignement enseignement, Type type, Classe classe, Periode periode);
    public void setPoids(Evaluation evaluation, double poids);
}
