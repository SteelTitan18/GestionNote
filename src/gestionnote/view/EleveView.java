/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.view;

import gestionnote.dao.ClasseDaoImpl;
import gestionnote.dao.CoursDaoImpl;
import gestionnote.dao.EleveDaoImpl;
import gestionnote.dao.EnseignementDaoImpl;
import gestionnote.dao.IClasseDao;
import gestionnote.dao.ICoursDao;
import gestionnote.dao.IEleveDao;
import gestionnote.dao.IEnseignementDao;
import gestionnote.dao.INoteDao;
import gestionnote.dao.NoteDaoImpl;
import gestionnote.model.Eleve;
import gestionnote.model.Enseignement;
import gestionnote.model.Periode;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author steeltitan
 */
public class EleveView {
    
    
    public EleveView(){
        
    }
    
    public static int getNumEleve(){
        IEleveDao eleveDao = new EleveDaoImpl();
        Scanner sc = new Scanner(System.in);
        eleveDao.afficherTout();
        System.out.print("Veuillez choisir un élève : ");
        int sc0 = sc.nextInt();
        return sc0;
    }
    
    public static int menu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\tMENU DES ÉLÈVES");
        System.out.println("1. Ajouter un élève");
        System.out.println("2. Supprimer un élève");
        System.out.println("3. Modifier les informations d'un élève");
        System.out.println("4. Consulter la liste des élèves");
        System.out.println("5. Retour");
        System.out.print("\tChoix : ");
        int choix = sc.nextInt();
        return choix;
    }
    
//    public void afficherBulletin(Eleve eleve1, Periode periode){
//        IEnseignementDao enseignementDao = new EnseignementDaoImpl();
//        INoteDao noteDao = new NoteDaoImpl();
//        IEleveDao eleveDao = new EleveDaoImpl();
//        IClasseDao classeDao = new ClasseDaoImpl();
//        ICoursDao coursDao = new CoursDaoImpl();
//        System.out.println( "\t+----------------------------------------------------------------------------------------------------------+" );
//        System.out.println( "\t|                                             BULLETIN DE NOTE                                             |" );
//        System.out.println( "\t|----------------------------------------------------------------------------------------------------------|" );
//        String.format(  "\t|Nom : %-61sNuméro matricule : %-20s|\n", eleve1.getNom(), eleve1.getNumMatric());
//        String.format(  "\t|Prénoms : %-57sClasse : %-30s|\n", eleve1.getPrenom(), eleve1.getClasse().getLibelle());
//        String.format(  "\t|Age : %-61dPériode : %-29s|\n", eleve1.getAge(), periode.toString());
//        System.out.println( "\t|----------------------------------------------------------------------------------------------------------|" );
//        System.out.println( "\t|                     |             Notes             |             |         |       |                    |" );
//        System.out.println( "\t|       Matière       |-------------------------------| Coefficient | Moyenne | Rang  |Moyenne de la classe|" );
//        System.out.println( "\t|                     |Interro|  DST  |   TP  |   EX  |             |         |       |                    |" );
//        System.out.println( "\t|---------------------+-------+-------+-------+-------+-------------+---------+-------+--------------------|" );
//
//        List noteList;
//        List<Enseignement> ensList = enseignementDao.getListeEnseignement(eleve1.getClasse());
//
//        for(Enseignement ens : ensList){
//            //System.out.println(periode1.getMoyenneEns(eleve1, ens));
//            //if (ens.getNiveau() == eleve1.getClasse().getNiveau()){
//                noteList = noteDao.noteFormat(eleve1, ens, periode);
//                String.format("\t|%-21s|%-7.2f|%-7.2f|%-7.2f|%-7.2f|%-13d|%-9.2f|%-7s|%-20.2f|\n",
//                        ens.getMatiere().getLibelle(), noteList.get(0), noteList.get(1), noteList.get(2), noteList.get(3), 
//                        ens.getCoefficient(), noteDao.getMoyenne(eleve1, ens, periode), eleveDao.getRang(eleve1, periode, ens)+"e", coursDao.getMoyenneCours(eleve1.getClasse(), ens, periode));
//            //}
//        }
//        System.out.println( "\t|---------------------+-------+-------+-------+-------+-------------+---------+-------+--------------------|" );
//        String.format( "\t|Moyenne : %-96.2f|\n", eleveDao.getMoyenne(eleve1, periode) );
//        String.format( "\t|Moyenne de la classe : %-22.2f Mension : %-50s|\n", classeDao.getMoyenne(eleve1.getClasse(), periode), eleveDao.getMension(eleve1, periode));
//        String.format( "\t|Rang : %-99s|\n", eleveDao.getRangFin(eleve1, periode)+"e");
//        System.out.println( "\t+----------------------------------------------------------------------------------------------------------+" );
//    }
    
    public String afficherBulletin(Eleve eleve1, Periode periode){
        IEnseignementDao enseignementDao = new EnseignementDaoImpl();
        INoteDao noteDao = new NoteDaoImpl();
        IEleveDao eleveDao = new EleveDaoImpl();
        IClasseDao classeDao = new ClasseDaoImpl();
        ICoursDao coursDao = new CoursDaoImpl();
        String bull = "";
        bull += "\t+----------------------------------------------------------------------------------------------------------+\n";
        bull += "\t|                                             BULLETIN DE NOTE                                             |\n";
        bull += "\t|----------------------------------------------------------------------------------------------------------|\n";
        bull += String.format(  "\t|Nom : %-61sNuméro matricule : %-20s|\n", eleve1.getNom(), eleve1.getNumMatric());
        bull += String.format(  "\t|Prénoms : %-57sClasse : %-30s|\n", eleve1.getPrenom(), eleve1.getClasse().getLibelle());
        bull += String.format(  "\t|Age : %-61dPériode : %-29s|\n", eleve1.getAge(), periode.toString());
        bull += "\t|----------------------------------------------------------------------------------------------------------|\n";
        bull += "\t|                     |             Notes             |             |         |       |                    |\n";
        bull += "\t|       Matière       |-------------------------------| Coefficient | Moyenne | Rang  |Moyenne de la classe|\n";
        bull += "\t|                     |Interro|  DST  |   TP  |   EX  |             |         |       |                    |\n";
        bull += "\t|---------------------+-------+-------+-------+-------+-------------+---------+-------+--------------------|\n";

        List noteList;
        List<Enseignement> ensList = enseignementDao.getListeEnseignement(eleve1.getClasse());

        for(Enseignement ens : ensList){
            //System.out.println(periode1.getMoyenneEns(eleve1, ens));
            //if (ens.getNiveau() == eleve1.getClasse().getNiveau()){
                noteList = noteDao.noteFormat(eleve1, ens, periode);
                bull += String.format("\t|%-21s|%-7.2f|%-7.2f|%-7.2f|%-7.2f|%-13d|%-9.2f|%-7s|%-20.2f|\n",
                        ens.getMatiere().getLibelle(), noteList.get(0), noteList.get(1), noteList.get(2), noteList.get(3), 
                        ens.getCoefficient(), noteDao.getMoyenne(eleve1, ens, periode), eleveDao.getRang(eleve1, periode, ens)+"e", coursDao.getMoyenneCours(eleve1.getClasse(), ens, periode));
            //}
        }
        bull += "\t|---------------------+-------+-------+-------+-------+-------------+---------+-------+--------------------|\n";
        bull += String.format( "\t|Moyenne : %-96.2f|\n", eleveDao.getMoyenne(eleve1, periode) );
        bull += String.format( "\t|Moyenne de la classe : %-22.2f Mension : %-50s|\n", classeDao.getMoyenne(eleve1.getClasse(), periode), eleveDao.getMension(eleve1, periode));
        bull += String.format( "\t|Rang : %-99s|\n", eleveDao.getRangFin(eleve1, periode)+"e");
        bull += "\t+----------------------------------------------------------------------------------------------------------+" ;
        return bull;
    }
    
    public static double getNote(Eleve eleve){
        Scanner sc = new Scanner (System.in);
        System.out.print("\nVeuillez entrer la note de "+eleve.getNom()+
        " "+eleve.getPrenom()+ " : ");
        double sc6 = sc.nextDouble();
        return sc6;
    }
    
    public static void entete(){
        System.out.println("########        CONCEPTEUR DE BULLETION DE NOTE     ########");
        System.out.println("*************** Saisie des notes **************");
    }
    
    public static String getNom(){
        Scanner scan0 = new Scanner(System.in);
        System.out.print("Entrez le nom de l'élève : ");
        String nomEl = scan0.nextLine();
        return nomEl;
    }
    
    public static String getPrenom(){
        Scanner scan0 = new Scanner(System.in);
        System.out.print("Entrez le(s) prénom(s) de l'élève : ");
        String prenomEl = scan0.nextLine();
        return prenomEl;
    }
    
    public static long getNumMatric(){
//        Scanner scan0 = new Scanner(System.in);
        long numEl = EleveView.getNumEleve();
//        System.out.print("Entrez le numéro matricule de l'élève : ");
//        long numEl = scan0.nextLong();
        //scan0.nextLine();
        return numEl;
    }
    
    public static int getAge(){
        Scanner scan0 = new Scanner(System.in);
        System.out.print("Entrez l'âge de l'élève : ");
        int ageEl = scan0.nextInt();
        return ageEl;
    }
    
    public int getNumClasse(){
        IClasseDao classeDao = new ClasseDaoImpl();
        Scanner scan0 = new Scanner(System.in);
        classeDao.afficherTout();
        System.out.println("Veuillez choisir la classe de l'élève : ");
        int numClasseEl = scan0.nextInt();
        return numClasseEl;
    }
    
    public static int getCHoix(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\tMENU DES ÉLÈVES");
        System.out.println("1. Ajouter un élève");
        System.out.println("2. Supprimer un élève");
        System.out.println("3. Modifier les informations d'un élève");
        System.out.println("4. Consulter la liste des élèves");
        System.out.println("5. Retour");
        System.out.print("\tChoix : ");
        int choix = sc.nextInt();
        return choix;
    }
    
    public static String getSexe(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrez le sexe(M/F) : ");
        String sex = sc.nextLine();
        return sex;
    }
    
}
