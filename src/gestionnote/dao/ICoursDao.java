/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestionnote.dao;

import gestionnote.model.Classe;
import gestionnote.model.Cours;
import gestionnote.model.Enseignant;
import gestionnote.model.Enseignement;
import gestionnote.model.Periode;
import java.util.List;

/**
 *
 * @author steeltitanrgnk
 */
public interface ICoursDao {
    public Cours ajouter(Cours cours);
    public void supprimer(Cours cours);
    public void supprimer(long id);
    public void afficherTout();
    public void afficher(Cours cours);
    public List<Cours> getCours();
    public Cours getCour(long id);
    public double getMoyenneCours(Classe classe, Enseignement enseignement, Periode periode);
    public void setEnseignant(Enseignant enseignant, Cours cours);
}
