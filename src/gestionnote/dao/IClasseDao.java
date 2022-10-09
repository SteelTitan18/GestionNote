/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestionnote.dao;

import gestionnote.model.Classe;
import gestionnote.model.Periode;
import java.util.List;

/**
 *
 * @author steeltitanrgnk
 */
public interface IClasseDao {
    public Classe ajouter(Classe classe);
    public void supprimer(Classe classe);
    public void supprimer(long id);
    public void afficherTout();
    public void afficher(Classe classe);
    public List<Classe> getClasses();
    public Classe getClasse(long id);
    public double getMoyenne(Classe classe, Periode periode);
}
