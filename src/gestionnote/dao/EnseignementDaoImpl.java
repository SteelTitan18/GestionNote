/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.dao;

import gestionnote.model.Classe;
import gestionnote.model.Enseignement;
import gestionnote.utils.HibernateSessionFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.SessionFactory;

/**
 *
 * @author steeltitanrgnk
 */
public class EnseignementDaoImpl implements IEnseignementDao{
    private final EntityManager manager;
    
    public EnseignementDaoImpl(){
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        this.manager = sessionFactory.createEntityManager();
    } 

    @Override
    public Enseignement ajouter(Enseignement enseignement) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.persist(enseignement);
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        return enseignement;
    }

    @Override
    public void supprimer(Enseignement enseignement) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.remove(enseignement);
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
            manager.createNativeQuery("DELETE FROM enseignements WHERE id_enseignement = ?")
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
    public List<Enseignement> getListeEnseignement() {
        List<Enseignement> enseignements = null;
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            enseignements = manager.createNativeQuery("select * from enseignements"
                    , Enseignement.class).getResultList();
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        return enseignements;
    }

    @Override
    public void afficherTout() {
        IEnseignementDao enseignementDao = new EnseignementDaoImpl();
        List<Enseignement> enseignements = enseignementDao.getListeEnseignement();
        for (Enseignement enseignement : enseignements) {
            afficher(enseignement);
        }
    }

    @Override
    public void afficher(Enseignement enseignement) {
        System.out.println(enseignement);
    }
    
    @Override
    public Enseignement getEnseignement(long id) {
        Enseignement enseignement = manager.find(Enseignement.class, id);
        return enseignement;
    }

    @Override
    public List<Enseignement> getListeEnseignement(Classe classe) {
        List<Enseignement> enseignements = null;
        enseignements = manager.createNativeQuery("select * from enseignements "
                + "WHERE niveau_numero = ?"
                    , Enseignement.class)
                .setParameter(1, classe.getNiveau().getNumero())
                .getResultList();
        return enseignements;
    }

    @Override
    public void setCoefficient(Enseignement enseignement, int coef) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.createNativeQuery("UPDATE enseignements SET coefficient = ? "
                    + "WHERE id_enseignement = ?"
                    , Enseignement.class)
                    .setParameter(1, coef)
                    .setParameter(2, enseignement.getId_enseignement())
                    .executeUpdate();
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
    }
}
