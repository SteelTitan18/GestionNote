/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.dao;

import gestionnote.model.Classe;
import gestionnote.model.Cours;
import gestionnote.model.Eleve;
import gestionnote.model.Enseignant;
import gestionnote.model.Enseignement;
import gestionnote.model.Periode;
import gestionnote.utils.HibernateSessionFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.SessionFactory;

/**
 *
 * @author steeltitanrgnk
 */
public class CoursDaoImpl implements ICoursDao{
    private final EntityManager manager;
    
    
    public CoursDaoImpl(){
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        this.manager = sessionFactory.createEntityManager();
    }

    @Override
    public Cours ajouter(Cours cours) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.persist(cours);
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        return cours;
    }

    @Override
    public void supprimer(Cours cours) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.remove(cours);
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
            manager.createNativeQuery("DELETE FROM classes WHERE id = ?")
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
    public void afficherTout() {
        ICoursDao coursDao = new CoursDaoImpl();
        List<Cours> cours = coursDao.getCours();
        for (Cours cour : cours) {
            afficher(cour);
        }
    }

    @Override
    public void afficher(Cours cours) {
        System.out.println(cours);
    }

    @Override
    public List<Cours> getCours() {
        List<Cours> cours = null;
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            cours = manager.createNativeQuery("select * from cours", Cours.class).getResultList();
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        return cours;
    }

    @Override
    public Cours getCour(long id) {
        Cours cours = (Cours) manager.find(Cours.class, id);
        return cours;
    }

    @Override
    public double getMoyenneCours(Classe classe, Enseignement enseignement, Periode periode) {
        INoteDao noteDao = new NoteDaoImpl();
        IEleveDao eleveDao = new EleveDaoImpl();        
        List<Double> moyennes = noteDao.getMoyennesClasse(classe, periode, enseignement);
        int cpt = 0; 
        double total = 0;
        for (Double moyenne : moyennes) {
            cpt++;
            total += moyenne;
        }
        total /= cpt;
        return total;
    }

    @Override
    public void setEnseignant(Enseignant enseignant, Cours cours) {
        ICoursDao coursDao = new CoursDaoImpl();
        IEnseignantDao enseignantDao = new EnseignantDaoImpl();
        
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.createNativeQuery("UPDATE cours SET id_enseignant = ? WHERE "
                    + "id = ?", Cours.class)
                    .setParameter(1, enseignant.getNumMatric())
                    .setParameter(2, cours.getId())
                    .executeUpdate();
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
    }
}
