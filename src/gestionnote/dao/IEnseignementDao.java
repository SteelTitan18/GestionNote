/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestionnote.dao;

import gestionnote.model.Classe;
import gestionnote.model.Enseignement;
import java.util.List;

/**
 *
 * @author steeltitanrgnk
 */
public interface IEnseignementDao {
    public Enseignement ajouter(Enseignement enseignement);
    public void supprimer(Enseignement enseignement);
    public void supprimer(long matric);
    public List<Enseignement> getListeEnseignement();
    public List<Enseignement> getListeEnseignement(Classe classe);
    public void afficherTout();
    public void afficher(Enseignement enseignement);
    public Enseignement getEnseignement(long id);
    public void setCoefficient(Enseignement enseignement, int coef);
}
