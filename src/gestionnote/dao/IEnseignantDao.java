/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestionnote.dao;

import gestionnote.model.Enseignant;
import java.util.List;

/**
 *
 * @author steeltitanrgnk
 */
public interface IEnseignantDao {
    public Enseignant ajouter(Enseignant enseignement);
    public void supprimer(Enseignant enseignant);
    public void supprimer(long matric);
    public void listCours();
    public List<Enseignant> getListeEnseignant();
    public void afficherTout();
    public void afficher(Enseignant enseignant);
    public Enseignant getEnseignant(long id);
    public void setSalaire(Enseignant enseignant, int salaire);
    public void modifier(Enseignant enseignant);
}
