/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.dao;

import gestionnote.model.Classe;
import gestionnote.model.Eleve;
import gestionnote.model.Periode;
import gestionnote.utils.HibernateSessionFactory;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.SessionFactory;

/**
 *
 * @author steeltitanrgnk
 */
public class ClasseDaoImpl implements IClasseDao{
    private final EntityManager manager;
    
    
    public ClasseDaoImpl(){
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        this.manager = sessionFactory.createEntityManager();
    }

    @Override
    public Classe ajouter(Classe classe) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.persist(classe);
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        return classe;
    }

    @Override
    public void supprimer(Classe classe) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.remove(classe);
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
            manager.createNativeQuery("DELETE FROM classes WHERE id_classe = ?")
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
        IClasseDao classeDao = new ClasseDaoImpl();
        List<Classe> classes = classeDao.getClasses();
        for (Classe classe : classes) {
            afficher(classe);
        }
    }


    @Override
    public void afficher(Classe classe) {
        System.out.println(classe);
    }

    @Override
    public List<Classe> getClasses() {
        List<Classe> classes = null;
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            classes = manager.createNativeQuery("SELECT * from classes", Classe.class).getResultList();
//            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        return classes;
    }
    
    @Override
    public Classe getClasse(long id) {
        Classe classe = (Classe) manager.find(Classe.class, id);
        return classe;
    }

    @Override
    public double getMoyenne(Classe classe, Periode periode) {
        INoteDao noteDao = new NoteDaoImpl();
        IEleveDao eleveDao = new EleveDaoImpl();
        List<Eleve> eleves = eleveDao.getEleves(classe);
        List<Double> notes = new ArrayList<>();
        int cpt = 0;
        double moy = 0;
        double total = 0;
        
        for (Eleve eleve : eleves) {
            cpt++;
            moy = eleveDao.getMoyenne(eleve, periode);
            notes.add(moy);
        }
        for (Double note : notes) {
            total += note;
        }
        total /= cpt;
        return  total;
    }
}
