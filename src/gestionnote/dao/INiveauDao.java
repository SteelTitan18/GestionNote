/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestionnote.dao;

import gestionnote.model.Niveau;
import java.util.List;

/**
 *
 * @author steeltitanrgnk
 */
public interface INiveauDao {
    public void afficherTout();
    public void afficher(Niveau niveau);
    public Niveau ajouter(Niveau niveau);
    public void supprimer(Niveau niveau);
    public void supprimer(int id);
    public List<Niveau> getNiveaux();
    public Niveau getInstance(int code);
}
