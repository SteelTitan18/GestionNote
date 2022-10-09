/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.dao;

import gestionnote.model.Personne;
import gestionnote.utils.HibernateSessionFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.SessionFactory;

/**
 *
 * @author steeltitanrgnk
 */
public class PersonneDaoImpl implements IPersonneDao{
    private final EntityManager manager;
    
    public PersonneDaoImpl(){
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        this.manager = sessionFactory.createEntityManager();
    }

    @Override
    public void afficherTout() {
        IPersonneDao personneDao = new PersonneDaoImpl();
        List<Personne> personnes = personneDao.getPersonnes();
        for (Personne personne : personnes) {
            afficher(personne);
        }
    }

    @Override
    public void afficher(Personne personne) {
        System.out.println(personne);
    }

    @Override
    public Personne ajouter(Personne personne) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.persist(personne);
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        return personne;
    }

    @Override
    public void supprimer(Personne personne) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.remove(personne);
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
            manager.createNativeQuery("DELETE FROM personnes WHERE id_personne = ?")
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
    public List<Personne> getPersonnes() {
        List<Personne> personnes = null;
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            personnes = manager.createNativeQuery("select * from personnes", Personne.class).getResultList();
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        return personnes;
    }

    @Override
    public void setNom(Personne personne, String nom) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.createNativeQuery("UPDATE personnes SET nom = ? WHERE "
                    + "numMatric = ?", Personne.class)
                    .setParameter(1, nom)
                    .setParameter(2, personne.getNumMatric())
                    .executeUpdate();
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void setPrenom(Personne personne, String prenom) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.createNativeQuery("UPDATE personnes SET prenom = ? WHERE "
                    + "numMatric = ?", Personne.class)
                    .setParameter(1, prenom)
                    .setParameter(2, personne.getNumMatric())
                    .executeUpdate();
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void setAge(Personne personne, int age) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.createNativeQuery("UPDATE personnes SET age = ? WHERE "
                    + "numMatric = ?", Personne.class)
                    .setParameter(1, age)
                    .setParameter(2, personne.getNumMatric())
                    .executeUpdate();
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void setNumMatric(Personne personne, long num) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.createNativeQuery("UPDATE personnes SET numMatric = ? WHERE "
                    + "numMatric = ?", Personne.class)
                    .setParameter(1, num)
                    .setParameter(2, personne.getNumMatric())
                    .executeUpdate();
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void setSexe(Personne personne, String sexe) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.createNativeQuery("UPDATE personnes SET sexe = ? WHERE "
                    + "numMatric = ?", Personne.class)
                    .setParameter(1, sexe)
                    .setParameter(2, personne.getNumMatric())
                    .executeUpdate();
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
    }
    
}
