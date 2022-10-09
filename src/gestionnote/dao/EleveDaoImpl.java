/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Eleves/Class.java to edit this template
 */
package gestionnote.dao;

import gestionnote.model.Classe;
import gestionnote.model.Eleve;
import gestionnote.model.Enseignement;
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
public class EleveDaoImpl implements IEleveDao{
    private final EntityManager manager;
    
    
    public EleveDaoImpl(){
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        this.manager = sessionFactory.createEntityManager();
    }

    @Override
    public Eleve ajouter(Eleve eleve) {
        IEleveDao eleveDao = new EleveDaoImpl();
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.persist(eleve);
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
            eleveDao.modifier(eleve);
        }
        return eleve;
    }

    @Override
    public void supprimer(Eleve eleve) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.remove(eleve);
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
    public void afficherTout() {
        IEleveDao eleveDao = new EleveDaoImpl();
        List<Eleve> eleves = eleveDao.getEleves();
        for (Eleve eleve : eleves) {
            afficher(eleve);
        }
    }

    @Override
    public void afficher(Eleve eleve) {
        System.out.println(eleve);
    }

    @Override
    public List<Eleve> getEleves() {
        List<Eleve> eleves = null;
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            eleves = manager.createNativeQuery("select * from personnes WHERE personne_type = 'eleve'", Eleve.class).getResultList();
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        return eleves;
    }

    @Override
    public Eleve getEleve(long id) {
        Eleve eleve = (Eleve) manager.find(Eleve.class, id);
        return eleve;
    }

    @Override
    public List<Eleve> getEleves(Classe classe) {
        List <Eleve> eleves = null;
        eleves = manager.createNativeQuery("SELECT * FROM personnes WHERE"
                + " personne_type = 'eleve' AND id_classe = ?", Eleve.class)
                .setParameter(1, classe.getId_classe())
                .getResultList();
        return eleves;
    }

    @Override
    public int getRang(Eleve eleve, Periode periode, Enseignement enseignement) {
        INoteDao noteDao = new NoteDaoImpl();
        
        List <Double> moyennes = noteDao.getMoyennesClasse(eleve.getClasse(), periode, enseignement);
        double moy = noteDao.getMoyenne(eleve, enseignement, periode);
        
        for (Double moyenne : moyennes) {
            if(moyenne == moy){
                int rang = moyennes.indexOf(moyenne) + 1;
                return rang;
            }
        }
        return 0;
    }

    @Override
    public double getMoyenne(Eleve eleve, Periode periode) {
        INoteDao noteDao = new NoteDaoImpl();
        IEnseignementDao enseignementDao = new EnseignementDaoImpl();
        double total = 0;
        int cpt = 0;
        List<Enseignement> enseignements = enseignementDao.getListeEnseignement(eleve.getClasse());
        for (Enseignement enseignement : enseignements) {
            cpt++;
            double moy = noteDao.getMoyenne(eleve, enseignement, periode);
            total += moy;
        }
        total /= cpt;
        return total;
    }

    @Override
    public String getMension(Eleve eleve, Periode periode) {
        IEleveDao eleveDao = new EleveDaoImpl();
        
        double moyenne = eleveDao.getMoyenne(eleve, periode);
        String mension;
        if (moyenne < 9){
            mension = "Ajourné(e)";
        }
        else if (moyenne >= 9 && moyenne < 12){
            mension = "Passable";
        }
        else if (moyenne >= 12 && moyenne < 14){
            mension = "Assez-bien";
        }
        else if (moyenne >= 14 && moyenne < 17){
            mension = "Bien";
        }
        else if (moyenne >= 17 && moyenne < 18){
            mension = "Très-Bien";
        }
        else{
            mension = "Excellent";
        }
        
        return mension;
    }

    @Override
    public int getRangFin(Eleve eleve, Periode periode) {
        IEleveDao eleveDao = new EleveDaoImpl();
        List<Eleve> eleves = eleveDao.getEleves(eleve.getClasse());
        double moyRech = eleveDao.getMoyenne(eleve, periode);
        List<Double> moyennes = new ArrayList<>();
        double moy = 0;
        //8int cpt = 0;
        
        for (Eleve elev : eleves) {
            //cpt++;
            moy = eleveDao.getMoyenne(elev, periode);
            moyennes.add(moy);
        }
        
        for (Double moyenne : moyennes) {
            if (moyenne == moyRech){
                int rang = moyennes.indexOf(moyenne) + 1;
                return rang;
            }
        }
        
        return 0;
    }

    @Override
    public void setClasse(Classe classe, Eleve eleve) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.createNativeQuery("UPDATE personnes SET id_classe = ? WHERE "
                    + "personne_type = 'eleve' AND numMatric = ?", Eleve.class)
                    .setParameter(1, classe.getId_classe())
                    .setParameter(2, eleve.getNumMatric())
                    .executeUpdate();
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void modifier(Eleve eleve) {
        IPersonneDao personneDao = new PersonneDaoImpl();
        IEleveDao eleveDao = new EleveDaoImpl();
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
//            manager.createNativeQuery("UPDATE personnes SET nom = ?, prenom = ?"
//                    + " age = ?, sexe = ?, id_classe = ? WHERE numMatric = ?", 
//                    Eleve.class)
//                    .setParameter(1, eleve.getNom())
//                    .setParameter(2, eleve.getPrenom())
//                    .set
//                    .executeUpdate();
            personneDao.setNom(eleve, eleve.getNom());
            personneDao.setPrenom(eleve, eleve.getPrenom());
            personneDao.setAge(eleve, eleve.getAge());
            personneDao.setSexe(eleve, eleve.getSexe());
            eleveDao.setClasse(eleve.getClasse(), eleve);
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
    }
    
    
}
