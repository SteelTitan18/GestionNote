/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Enseignants/Class.java to edit this template
 */
package gestionnote.dao;

import gestionnote.model.Enseignant;
import gestionnote.utils.HibernateSessionFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.SessionFactory;

/**
 *
 * @author steeltitanrgnk
 */
public class EnseignantDaoImpl implements IEnseignantDao{
    private final EntityManager manager;
    
    public EnseignantDaoImpl(){
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        this.manager = sessionFactory.createEntityManager();
    }

    @Override
    public Enseignant ajouter(Enseignant enseignement) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.persist(enseignement);
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
            modifier(enseignement);
        }
        return enseignement;
    }

    @Override
    public void supprimer(Enseignant enseignant) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.remove(enseignant);
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void supprimer(long matric) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.createNativeQuery("DELETE FROM personnes WHERE numMatric = ?")
                    .setParameter(1, matric)
                    .executeUpdate();
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void listCours() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Enseignants/Code/GeneratedMethodBody
    }

    @Override
    public List<Enseignant> getListeEnseignant() {
        List<Enseignant> enseignants = null;
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            enseignants = manager.createNativeQuery("select * from personnes WHERE personne_type = 'enseignant'", Enseignant.class).getResultList();
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        return enseignants;
    }

    @Override
    public void afficherTout() {
        IEnseignantDao enseignantDao = new EnseignantDaoImpl();
        List<Enseignant> enseignants = enseignantDao.getListeEnseignant();
        for (Enseignant enseignant : enseignants) {
            afficher(enseignant);
        }
    }

    @Override
    public void afficher(Enseignant enseignant) {
        System.out.println(enseignant);
    }

    @Override
    public Enseignant getEnseignant(long id) {
        Enseignant enseignant = manager.find(Enseignant.class, id);
        return enseignant;
    }

    @Override
    public void setSalaire(Enseignant enseignant, int salaire) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.createNativeQuery("UPDATE personnes SET salaire  = ? WHERE "
                    + "personne_type = 'enseignant' AND numMatric = ?", Enseignant.class)
                    .setParameter(1, salaire)
                    .setParameter(2, enseignant.getNumMatric())
                    .executeUpdate();
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void modifier(Enseignant enseignant) {
        IPersonneDao personneDao = new PersonneDaoImpl();
        ICoursDao coursDao = new CoursDaoImpl();
        IEnseignantDao enseignantDao = new EnseignantDaoImpl();
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            personneDao.setNom(enseignant, enseignant.getNom());
            personneDao.setPrenom(enseignant, enseignant.getPrenom());
            personneDao.setAge(enseignant, enseignant.getAge());
            personneDao.setSexe(enseignant, enseignant.getSexe());
            enseignantDao.setSalaire(enseignant, enseignant.getSalaire());
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        
    }
}
