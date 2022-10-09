/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestionnote.dao;

import gestionnote.model.Eleve;
import gestionnote.model.Enseignement;
import gestionnote.model.Note;
import gestionnote.model.Periode;
import java.util.List;

/**
 *
 * @author steeltitanrgnk
 */
public interface IPeriodeDao {
    public void afficherTout();
    public void afficher(Periode periode);
    public Periode ajouter(Periode periode);
    public void supprimer(Periode periode);
    public void supprimer(long id);
    public List<Periode> getPeriodes();
    public Periode getPeriode(long id);
    //public void bulletin(Eleve eleve, Periode periode);
}
