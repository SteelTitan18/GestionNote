/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestionnote.dao;

import gestionnote.model.Classe;
import gestionnote.model.Eleve;
import gestionnote.model.Enseignement;
import gestionnote.model.Periode;
import java.util.List;

/**
 *
 * @author steeltitanrgnk
 */
public interface IEleveDao {
    public Eleve ajouter(Eleve eleve);
    public void supprimer(Eleve eleve);
    public void supprimer(long matric);
    public List<Eleve> getEleves();
    public void afficherTout();
    public void afficher(Eleve eleve);
    public Eleve getEleve(long id);
    public List<Eleve> getEleves(Classe classe);
    public int getRang(Eleve eleve, Periode periode, Enseignement enseignement);
    public int getRangFin(Eleve eleve, Periode periode);
    public double getMoyenne(Eleve eleve, Periode periode);
    public String getMension(Eleve eleve, Periode periode);
    public void setClasse(Classe classe, Eleve eleve);
    public void modifier(Eleve eleve);  
}
