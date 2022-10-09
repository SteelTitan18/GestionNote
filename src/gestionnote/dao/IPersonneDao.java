/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestionnote.dao;

import gestionnote.model.Personne;
import java.util.List;

/**
 *
 * @author steeltitanrgnk
 */
public interface IPersonneDao {
    public void afficherTout();
    public void afficher(Personne personne);
    public Personne ajouter(Personne personne);
    public void supprimer(Personne personne);
    public void supprimer(long id);
    public List<Personne> getPersonnes();
    public void setNom(Personne personne, String nom);
    public void setPrenom(Personne personne, String prenom);
    public void setAge(Personne personne, int age);
    public void setNumMatric(Personne personne, long num);
    public void setSexe(Personne personne, String sexe);
}
