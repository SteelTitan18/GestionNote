/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.dao;

import gestionnote.model.Matiere;
import gestionnote.utils.HibernateSessionFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.SessionFactory;

/**
 *
 * @author steeltitanrgnk
 */
public class MatiereDaoImpl implements IMatiereDao{
    private final EntityManager manager;
    
    public MatiereDaoImpl(){
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        this.manager = sessionFactory.createEntityManager();
    }
    
    @Override
    public void afficherTout() {
        IMatiereDao matiereDao = new MatiereDaoImpl();
        List<Matiere> matieres = matiereDao.getMartieres();
        for (Matiere matiere : matieres) {
            afficher(matiere);
        }
    }

    @Override
    public void afficher(Matiere matiere) {
        System.out.println(matiere);
    }

    @Override
    public Matiere ajouter(Matiere matiere) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.persist(matiere);
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        return matiere;
    }

    @Override
    public void supprimer(Matiere matiere) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.remove(matiere);
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
            manager.createNativeQuery("DELETE FROM matieres WHERE id_matiere = ?")
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
    public List<Matiere> getMartieres() {
        List<Matiere> matieres = null;
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            matieres = manager.createNativeQuery("select * from matieres", Matiere.class).getResultList();
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        return matieres;
    }

    @Override
    public Matiere getMatiere(long id) {
        Matiere matiere = manager.find(Matiere.class, id);
        return matiere;
    }
    
}
