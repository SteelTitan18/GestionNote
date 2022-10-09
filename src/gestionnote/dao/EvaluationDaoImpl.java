/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.dao;

import gestionnote.model.Classe;
import gestionnote.model.Cours;
import gestionnote.model.Enseignement;
import gestionnote.model.Evaluation;
import gestionnote.model.Periode;
import gestionnote.model.Type;
import gestionnote.utils.HibernateSessionFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.SessionFactory;

/**
 *
 * @author steeltitanrgnk
 */
public class EvaluationDaoImpl implements IEvaluationDao{
    private final EntityManager manager;
    
    public EvaluationDaoImpl(){
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        this.manager = sessionFactory.createEntityManager();
    }

    @Override
    public void afficherTout() {
        IEvaluationDao evaluationDao = new EvaluationDaoImpl();
        List<Evaluation> evaluations = evaluationDao.getEvaluations();
        for (Evaluation evaluation : evaluations){
            afficher(evaluation);
        }
    }

    @Override
    public void afficher(Evaluation evaluation) {
        System.out.println(evaluation);
    }

    @Override
    public Evaluation ajouter(Evaluation evaluation) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.persist(evaluation);
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        return evaluation;
    }

    @Override
    public void supprimer(Evaluation evaluation) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.remove(evaluation);
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
            manager.createNativeQuery("DELETE FROM classes WHERE id_evaluation = ?")
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
    public List<Evaluation> getEvaluations() {
        List<Evaluation> evaluations = null;
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            evaluations = manager.createNativeQuery("select * from evaluations", Evaluation.class).getResultList();
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        return evaluations;
    }

    @Override
    public Evaluation getEvaluation(Enseignement enseignement, Type type, Classe classe, Periode periode) {
        IEvaluationDao evaluationDao = new EvaluationDaoImpl();
        
//        if(evaluation == null){
//            
//        }
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            Evaluation evaluation =(Evaluation) manager.createNativeQuery("SELECT * FROM evaluations AS e, cours AS c"
                + " WHERE id_periode = ? AND e.cours_id = c.id AND c.enseignement_id_enseignement = "
                + "? AND classe_id_classe = ? AND e.id_type = ?", Evaluation.class)
                .setParameter(1, periode.getCode())
                .setParameter(2, enseignement.getId_enseignement())
                .setParameter(3, classe.getId_classe())
                .setParameter(4, type.getCode())
                .getSingleResult();
            transaction.commit();
            return evaluation;
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
            Cours cours = (Cours) manager.createNativeQuery("SELECT * FROM cours "
                    + "WHERE classe_id_classe = ? AND enseignement_id_enseignement = ?", Cours.class)
                    .setParameter(1, classe.getId_classe())
                    .setParameter(2, enseignement.getId_enseignement())
                    .getSingleResult();
            Evaluation evaluation = new Evaluation(cours, type, 0, periode);
            evaluationDao.ajouter(evaluation);
            return evaluation;
        }
    }

    @Override
    public void setPoids(Evaluation evaluation, double poids) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.createNativeQuery("UPDATE evaluations SET poids = ? WHERE "
                    + "id_evaluation = ?", Evaluation.class)
                    .setParameter(1, poids)
                    .setParameter(2, evaluation.getId_evaluation())
                    .executeUpdate();
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public List<Evaluation> getEvaluations(Classe classe) {
//        IEvaluationDao evaluationDao = new EvaluationDaoImpl();
        List<Evaluation> evaluations = manager.createNativeQuery("SELECT * FROM "
                + "evaluations AS e, cours AS c WHERE e.cours_id = c.id AND "
                + "c.classe_id_classe = ?", Evaluation.class)
                .setParameter(1, classe.getId_classe())
                .getResultList();
        return evaluations;
    }

    @Override
    public Evaluation getEvaluation(long id) {
        Evaluation evaluation = manager.find(Evaluation.class, id);
        return evaluation;
    }
}
