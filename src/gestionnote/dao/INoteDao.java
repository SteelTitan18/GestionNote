/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestionnote.dao;

import gestionnote.model.Classe;
import gestionnote.model.Eleve;
import gestionnote.model.Enseignement;
import gestionnote.model.Note;
import gestionnote.model.Periode;
import java.util.List;

/**
 *
 * @author steeltitanrgnk
 */
public interface INoteDao {
    public void afficher(Note note);
    public Note ajouter(Note note);
    public void supprimer(Note note);
    public void supprimer(long id);
    public List<Note> getNotes();
    public Note getNote(long id);
    public void afficherTout();
    public List<Note> getNoteMat(Eleve eleve, Enseignement enseignement, Periode periode);
    public double getMoyenne(Eleve eleve, Enseignement enseignement, Periode periode);
    //public List<Note> getNotesClasse(Classe classe, Periode periode, Enseignement enseignement);
    public List<Double> getMoyennesClasse(Classe classe, Periode periode, Enseignement enseignement);
    public List<Double> noteFormat(Eleve eleve, Enseignement enseignement, Periode periode);
    public List<Note> getNotes(Eleve eleve);
    public void setNote(Note note, double valeur);
    public void setNote(Note note);
}
