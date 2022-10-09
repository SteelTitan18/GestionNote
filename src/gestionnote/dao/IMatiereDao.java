/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestionnote.dao;

import gestionnote.model.Matiere;
import java.util.List;

/**
 *
 * @author steeltitanrgnk
 */
public interface IMatiereDao {
    public void afficherTout();
    public void afficher(Matiere matiere);
    public Matiere ajouter(Matiere matiere);
    public void supprimer(Matiere matiere);
    public void supprimer(long id);
    public List<Matiere> getMartieres();
    public Matiere getMatiere(long id);
}
