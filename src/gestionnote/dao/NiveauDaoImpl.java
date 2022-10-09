/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.dao;

import gestionnote.model.Niveau;
import gestionnote.utils.HibernateSessionFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.SessionFactory;

/**
 *
 * @author steeltitanrgnk
 */
public class NiveauDaoImpl implements INiveauDao{
    private final EntityManager manager;
    
    public NiveauDaoImpl(){
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        this.manager = sessionFactory.createEntityManager();
    }
    
    @Override
    public void afficherTout() {
        INiveauDao niveauDao = new NiveauDaoImpl();
        List<Niveau> niveaux = niveauDao.getNiveaux();
        for (Niveau niveau : niveaux) {
            afficher(niveau);
        }
    }

    @Override
    public void afficher(Niveau niveau) {
        System.out.println(niveau);
    }

    @Override
    public Niveau ajouter(Niveau niveau) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.persist(niveau);
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        return niveau;
    }

    @Override
    public void supprimer(Niveau niveau) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.remove(niveau);
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void supprimer(int id) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.createNativeQuery("DELETE FROM niveaux WHERE numero = ?")
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
    public List<Niveau> getNiveaux() {
        List<Niveau> niveaux = null;
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            niveaux = manager.createNativeQuery("select * from niveaux", Niveau.class).getResultList();
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        return niveaux;
    }

    @Override
    public Niveau getInstance(int code) {
        Niveau niveau = manager.find(Niveau.class, code);
        return niveau;
    }
    
}
