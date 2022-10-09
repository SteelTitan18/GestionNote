/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Notes/Class.java to edit this template
 */
package gestionnote.dao;

import gestionnote.model.Classe;
import gestionnote.model.Eleve;
import gestionnote.model.Enseignement;
import gestionnote.model.Note;
import gestionnote.model.Periode;
import gestionnote.utils.HibernateSessionFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.SessionFactory;

/**
 *
 * @author steeltitanrgnk
 */
public class NoteDaoImpl implements INoteDao{
    private final EntityManager manager;
    
    
    public NoteDaoImpl(){
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        this.manager = sessionFactory.createEntityManager();
    }
    
    @Override
    public Note ajouter(Note note) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.persist(note);
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
            setNote(note);
        }
        return note;
    }

    @Override
    public void supprimer(Note note) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.remove(note);
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void supprimer(long id) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.createNativeQuery("DELETE FROM notes WHERE id_note = ?")
                    .setParameter(1, id)
                    .executeUpdate();
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void afficher(Note note) {
        System.out.println(note);
    }

    @Override
    public List<Note> getNotes() {
        List<Note> notes = null;
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            notes = manager.createNativeQuery("select * from notes", Note.class).getResultList();
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        return notes;
    }

    @Override
    public void afficherTout() {
        INoteDao noteDao = new NoteDaoImpl();
        List<Note> notes = noteDao.getNotes();
        for (Note note : notes) {
            afficher(note);
        }
    }

    @Override
    public List<Note> getNoteMat(Eleve eleve, Enseignement enseignement, Periode periode) {
        List<Note> notes = null;
        notes = manager.createNativeQuery("SELECT * FROM notes AS n, evaluations AS e, cours AS c WHERE "
                + "n.id_eleve = ? AND n.id_evaluation = e.id_evaluation AND "
                + "e.cours_id = c.id AND c.enseignement_id_enseignement = ? AND"
                + " e.id_periode = ?", Note.class)
                .setParameter(1, eleve.getNumMatric())
                .setParameter(2, enseignement.getId_enseignement())
                .setParameter(3, periode.getCode())
                .getResultList();
        return notes;
    }
    
    @Override
    public double getMoyenne(Eleve eleve, Enseignement enseignement, Periode periode) {
        double total = 0;
        
        INoteDao noteDao = new NoteDaoImpl();
        List<Note> notes = noteDao.getNoteMat(eleve, enseignement, periode);
        for (Note note : notes) {
            total += note.getValeur()*note.getEvaluation().getPoids();
        }
        return total;
    }

//    @Override
//    public List<Note> getNotesClasse(Classe classe, Periode periode, Enseignement enseignement) {
//        List<Note> notes = null;
//        notes = manager.createNativeQuery("SELECT * FROM notes AS n, evaluations"
//                + " AS e, personnes AS p, cours AS c WHERE n.id_eleve = p.numMatric AND p.id_classe "
//                + "= ? AND n.id_evaluation = e.id_evaluation AND e.id_periode = ?"
//                + " AND e.cours_id = c.id AND c.enseignement_id_enseignement = ?", Note.class)
//                .setParameter(1, classe.getId_classe())
//                .setParameter(2, periode.getCode())
//                .setParameter(3, enseignement.getId_enseignement())
//                .getResultList();
//        return notes;
//    }

    @Override
    public List<Double> getMoyennesClasse(Classe classe, Periode periode, Enseignement enseignement) {
        IEleveDao eleveDao = new EleveDaoImpl();
    INoteDao noteDao = new NoteDaoImpl();
        List<Eleve> eleves = eleveDao.getEleves(classe);
        List<Double> moyennes = new ArrayList<>();
        double moy = 0;
        for (Eleve eleve : eleves) {
            moy = noteDao.getMoyenne(eleve, enseignement, periode);
            moyennes.add(moy);
        }
        Collections.sort(moyennes);
        Collections.reverse(moyennes);
        return moyennes;
    }

    @Override
    public List<Double> noteFormat(Eleve eleve, Enseignement enseignement, Periode periode) {
        INoteDao noteDao = new NoteDaoImpl();
        
        List<Double> listNote = new ArrayList();
        double noteI = 0;
        listNote.add(0, noteI);
        listNote.add(1, noteI);
        listNote.add(2, noteI);
        listNote.add(3, noteI);
        //List<Note> listeNotes = Note.getListeNote();
        List<Note> listeNotes = noteDao.getNoteMat(eleve, enseignement, periode);
        for (Note note : listeNotes) {
//            if(note.getEvaluation().getPeriode() == periode &&
//                    note.getEvaluation().getCours().getEnseignement().getMatiere() == mat){
                switch (note.getEvaluation().getType().getCode()) {
                    case "IN":
                        listNote.add(0, (double)note.getValeur());
                        break;
                    case "DEV":
                        listNote.add(1, (double)note.getValeur());
                        break;
                    case "TP":
                        listNote.add(2, (double)note.getValeur());
                        break;
                    case "EX":
                        listNote.add(3, (double)note.getValeur());
                        break;
                }
            }
//        }
        return listNote;
    }

    @Override
    public List<Note> getNotes(Eleve eleve) {
        List<Note> notes = null;
        notes = manager.createNativeQuery("SELECT * FROM notes WHERE "
                + "id_eleve = ?", Note.class)
                .setParameter(1, eleve.getNumMatric())
                .getResultList();
        return notes;
    }

    @Override
    public Note getNote(long id) {
        Note note = manager.find(Note.class, id);
        return note;
    }

    @Override
    public void setNote(Note note, double valeur) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.createNativeQuery("UPDATE notes SET valeur = ? WHERE"
                    + " id_note = ?", Note.class)
                    .setParameter(1, valeur)
                    .setParameter(2, note.getId_note())
                    .executeUpdate();
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
    }
    
    @Override
    public void setNote(Note note) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.createNativeQuery("UPDATE notes SET valeur = ? WHERE"
                    + " id_note = ?", Note.class)
                    .setParameter(1, note.getValeur())
                    .setParameter(2, note.getId_note())
                    .executeUpdate();
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
    }

    

}
