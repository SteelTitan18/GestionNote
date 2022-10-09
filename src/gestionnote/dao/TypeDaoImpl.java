/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.dao;

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
public class TypeDaoImpl implements ITypeDao{
    private final EntityManager manager;
    
    public TypeDaoImpl(){
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        this.manager = sessionFactory.createEntityManager();
    }

    @Override
    public void afficherTout() {
        ITypeDao typeDao = new TypeDaoImpl();
        List<Type> types = typeDao.getTypes();
        for (Type type : types) {
            afficher(type);
        }
    }

    @Override
    public void afficher(Type type) {
        System.out.println(type);
    }

    @Override
    public Type ajouter(Type type) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.persist(type);
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        return type;
    }

    @Override
    public void supprimer(Type type) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.remove(type);
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void supprimer(String id) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.createNativeQuery("DELETE FROM types WHERE code = ?")
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
    public List<Type> getTypes() {
        List<Type> types = null;
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            types = manager.createNativeQuery("select * from types", Type.class).getResultList();
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        return types;
    }

    @Override
    public Type getType(String id) {
        Type type = manager.find(Type.class, id);
        return type;
    }
    
}
