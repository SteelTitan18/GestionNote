/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.dao;

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
public class PeriodeDaoImpl implements IPeriodeDao{
    private final EntityManager manager;
    
    
    public PeriodeDaoImpl(){
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        this.manager = sessionFactory.createEntityManager();
    }

    @Override
    public void afficherTout() {
        IPeriodeDao periodeDao = new PeriodeDaoImpl();
        List<Periode> periodes = periodeDao.getPeriodes();
        for (Periode periode : periodes) {
            afficher(periode);
        }
    }

    @Override
    public void afficher(Periode periode) {
        System.out.println(periode);
    }

    @Override
    public Periode ajouter(Periode periode) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.persist(periode);
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        return periode;
    }

    @Override
    public void supprimer(Periode periode) {
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.remove(periode);
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
            manager.createNativeQuery("DELETE FROM periodes WHERE code = ?")
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
    public List<Periode> getPeriodes() {
        List<Periode> periodes = null;
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            periodes = manager.createNativeQuery("select * from periodes",Periode.class).getResultList();
            transaction.commit();
        }
        catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        return periodes;
    }
    
//    @Override
//    public void bulletin(Eleve eleve1, Periode periode ){
//        System.out.println( "\t+----------------------------------------------------------------------------------------------------------+" );
//        System.out.println( "\t|                                             BULLETIN DE NOTE                                             |" );
//        System.out.println( "\t|----------------------------------------------------------------------------------------------------------|" );
//        System.out.printf(  "\t|Nom : %-61sNuméro matricule : %-20s|\n", eleve1.getNom(), eleve1.getNumMatric());
//        System.out.printf(  "\t|Prénoms : %-57sClasse : %-30s|\n", eleve1.getPrenom(), eleve1.getClasse().getLibelle());
//        System.out.printf(  "\t|Age : %-61dPériode : %-29s|\n", eleve1.getAge(), this.toString());
//        System.out.println( "\t|----------------------------------------------------------------------------------------------------------|" );
//        System.out.println( "\t|                     |             Notes             |             |         |       |                    |" );
//        System.out.println( "\t|       Matière       |-------------------------------| Coefficient | Moyenne | Rang  |Moyenne de la classe|" );
//        System.out.println( "\t|                     |Interro|  DST  |   TP  |   EX  |             |         |       |                    |" );
//        System.out.println( "\t|---------------------+-------+-------+-------+-------+-------------+---------+-------+--------------------|" );
//
//        List noteList;
//        List<Enseignement> ensList = enseignementDao.getListeEnseignement();
//
//        for(Enseignement ens : ensList){
//            //System.out.println(periode1.getMoyenneEns(eleve1, ens));
//            if (ens.getNiveau() == eleve1.getClasse().getNiveau()){
//                noteList = noteDao.getNoteMat(eleve1, ens, periode);
//                System.out.printf("\t|%-21s|%-7.2f|%-7.2f|%-7.2f|%-7.2f|%-13d|%-9.2f|%-7s|%-20.2f|\n",
//                        ens.getMatiere().getLibelle(), noteList.get(0), noteList.get(1), noteList.get(2), noteList.get(3), 
//                        ens.getCoefficient(), noteDao.getMoyenne(eleve1, ens, periode), eleveDao.getRang(eleve1, periode, ens)+"e", coursDao.getMoyenneCours(eleve1.getClasse(), ens, periode));
//            }
//        }
//        System.out.println( "\t|---------------------+-------+-------+-------+-------+-------------+---------+-------+--------------------|" );
//        System.out.printf( "\t|Moyenne : %-96.2f|\n", eleveDao.getMoyenne(eleve1, periode) );
//        System.out.printf( "\t|Moyenne de la classe : %-22.2f Mension : %-50s|\n", classeDao.getMoyenne(eleve1.getClasse(), periode), eleveDao.getMension(eleve1, periode));
//        System.out.printf( "\t|Rang : %-99s|\n", eleveDao.getRangFin(eleve1, periode)+"e");
//        System.out.println( "\t+----------------------------------------------------------------------------------------------------------+" );
//    }

    @Override
    public Periode getPeriode(long id) {
        Periode periode = manager.find(Periode.class, id);
        return periode;
    }

}
