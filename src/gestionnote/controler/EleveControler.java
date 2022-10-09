/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.controler;

import gestionnote.dao.ClasseDaoImpl;
import gestionnote.dao.EleveDaoImpl;
import gestionnote.dao.EnseignementDaoImpl;
import gestionnote.dao.EvaluationDaoImpl;
import gestionnote.dao.IClasseDao;
import gestionnote.dao.IEleveDao;
import gestionnote.dao.IEnseignementDao;
import gestionnote.dao.IEvaluationDao;
import gestionnote.dao.INoteDao;
import gestionnote.dao.IPeriodeDao;
import gestionnote.dao.ITypeDao;
import gestionnote.dao.NoteDaoImpl;
import gestionnote.dao.PeriodeDaoImpl;
import gestionnote.dao.TypeDaoImpl;
import gestionnote.model.Classe;
import gestionnote.model.Eleve;
import gestionnote.model.Enseignement;
import gestionnote.model.Evaluation;
import gestionnote.model.Note;
import gestionnote.model.Periode;
import gestionnote.model.Type;
import gestionnote.view.EleveView;
import gestionnote.view.EnseignementView;
import gestionnote.view.EvaluationView;
import gestionnote.view.PeriodeView;
import gestionnote.view.TypeView;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author steeltitan
 */
public class EleveControler {
    private Eleve model;
    private EleveView view;
    
    
    public EleveControler(Eleve model, EleveView view){
        this.model = model;
        this.view = view;
    }
    
    
    public void afficherBulletin(){
        IPeriodeDao periodeDao = new PeriodeDaoImpl();
        IEleveDao eleveDao = new EleveDaoImpl();
        PeriodeView viewP = new PeriodeView();
        Scanner sc = new Scanner(System.in);
        char st3;
        char st4;
        do{
            System.out.println("\n\t-------AFFICHAGE DU BULLETIN--------");
            int numPeriode = viewP.getNumPeriode();
            Periode periode0 = periodeDao.getPeriode(numPeriode);
            do{
                int numEleve = EleveView.getNumEleve();
                Eleve eleve = eleveDao.getEleve(numEleve);
                view.afficherBulletin(eleve, periode0);

                sc.nextLine();
                System.out.print("\nVoulez-vous choisir un autre élève ?(o/n) : ");
                st3 = sc.nextLine().charAt(0);
            }while(st3 != 'n');
            
            sc.nextLine();
            System.out.print("\nVoulez-vous choisir une autre période ? : ");
            st4 = sc.nextLine().charAt(0);
        }while(st4 != 'n');
    }
    
    public void getNoteEleves(){
        IEleveDao eleveDao = new EleveDaoImpl();
        IClasseDao classeDao = new ClasseDaoImpl();
        IEnseignementDao enseignementDao = new EnseignementDaoImpl();
        IEvaluationDao evaluationDao = new EvaluationDaoImpl();
        INoteDao noteDao = new NoteDaoImpl();
        IPeriodeDao periodeDao = new PeriodeDaoImpl();
        ITypeDao typeDao = new TypeDaoImpl();
        PeriodeView viewP = new PeriodeView();
        TypeView viewT = new TypeView();
        EnseignementView viewE = new EnseignementView();
        Scanner sc = new Scanner(System.in);
        char st0;
        char st1;
        char st2;
        char st;
        do{
            EleveView.entete();
            
            int numPeriode = viewP.getNumPeriode();
            Periode periode0 = periodeDao.getPeriode(numPeriode);
            do{
                long numClasse = view.getNumClasse();
                Classe classe0 = classeDao.getClasse(numClasse);
                do{
                    int numEns = viewE.getNumEns(classe0);
                    Enseignement enseignement0 = enseignementDao.getEnseignement(numEns);
                    int coef = EnseignementView.getCoef();
                    enseignement0.setCoefficient(coef);
                    do{
                        String type = viewT.getType();
                        Type type0 = typeDao.getType(type);
                        Evaluation evaluation0 = evaluationDao.getEvaluation(enseignement0, type0, classe0, periode0);
                        double poids = EvaluationView.getPoids();
                        evaluation0.setPoids(poids);
                        List<Eleve> eleves0 = eleveDao.getEleves(classe0);
                        for (Eleve eleve : eleves0) {
                            double sc6 = EleveView.getNote(eleve);
                            Note note = new Note (evaluation0, eleve, sc6);
                            noteDao.ajouter(note);
                        }
                        eleves0.removeAll(eleves0);
                        sc.nextLine();
                        
                        System.out.print("\nVoulez-vous choisir un autre type (o/n)? : ");
                        st2 = sc.nextLine().charAt(0);
                    }while(st2 != 'n');
                    
                    sc.nextLine();
                    System.out.print("\nVoulez-vous choisir une autre matière (o/n)? : ");
                    st1 = sc.nextLine().charAt(0);
                }while(st1 != 'n');
                
                sc.nextLine();
                System.out.print("\nVoulez-vous choisir un autre classe (o/n)? : ");
                st0 = sc.nextLine().charAt(0);
            }while(st0 != 'n');
            
            sc.nextLine();
            System.out.print("\nVoulez-vous choisir un autre période (o/n)? : ");
            st = sc.nextLine().charAt(0);
        }while(st != 'n');
    }
    
    public void ajouter(){
        IEleveDao eleveDao = new EleveDaoImpl();
        IClasseDao classeDao = new ClasseDaoImpl();
        Scanner scan = new Scanner(System.in);
        char rep;
        do{
            String nomEl = EleveView.getNom();
            String prenomEl = EleveView.getPrenom();
            String sexeEl = EleveView.getSexe();
            sexeEl = sexeEl.toUpperCase();
            long numMatric = EleveView.getNumMatric();
            int ageEl = EleveView.getAge();
            int numClasse = view.getNumClasse();
            Classe classe = classeDao.getClasse(numClasse);
            Eleve eleve = new Eleve(nomEl, prenomEl, sexeEl, numMatric, ageEl, classe);
            eleveDao.ajouter(eleve);
            System.out.println("Elève ajouté avec succès !");
            
            System.out.print("Voulez-vous ajouter un autre élève ?(o/n) : ");
            rep = scan.nextLine().charAt(0);
        }while(rep != 'n');
    }
    
    public void supprimer(){
        IEleveDao eleveDao = new EleveDaoImpl();
        Scanner scan = new Scanner(System.in);
        char rep;
        do{
            int numEleve = EleveView.getNumEleve();
            eleveDao.supprimer(numEleve);
            
            System.out.print("Voulez-vous supprimer un autre élève ?(o/n) : ");
            rep = scan.nextLine().charAt(0);
        }while(rep != 'n');
    }
    
    public void modifier(){
        IEleveDao eleveDao = new EleveDaoImpl();
        IClasseDao classeDao = new ClasseDaoImpl();
        Scanner scan = new Scanner(System.in);
        char rep;
        do{
            System.out.println("\n\tVeuillez entrer 0 pour conserver les valeurs iniiales ! ");
            int numEleve = EleveView.getNumEleve();
            Eleve el = eleveDao.getEleve(numEleve);
            String nomEl = EleveView.getNom();
            if (!"0".equals(nomEl)){
                el.setNom(nomEl);
            }
            String prenomEl = EleveView.getPrenom();
            if (!"0".equals(prenomEl)){
                el.setPrenom(prenomEl);
            }
            String sexeEl = EleveView.getSexe();
            sexeEl = sexeEl.toUpperCase();
            if (!"0".equals(sexeEl)){
                el.setSexe(sexeEl);
            }
//            long numMatric = EleveView.getNumMatric();
//            if (numMatric != 0){
//                el.setNumMatric(numMatric);
//            }
            int ageEl = EleveView.getAge();
            if (ageEl != 0){
                el.setAge(ageEl);
            }
            int numClasse = view.getNumClasse();
            Classe classe = classeDao.getClasse(numClasse);
            if (classe != el.getClasse()){
                el.setClasse(classe);
            }
            System.out.println("Elève modifié avec succès !");
            
            System.out.print("Voulez-vous modifier un autre élève ?(o/n) : ");
            rep = scan.nextLine().charAt(0);
        }while(rep != 'n');
    }
    
    public void menuEleve(){
        Eleve model0 = new Eleve();
        EleveView view0 = new EleveView();
        IEleveDao eleveDao = new EleveDaoImpl();
        EleveControler elCtrl0 = new EleveControler(model0, view0);
        int choix;
        do {
            choix = EleveView.getCHoix();
            switch (choix) {
                case 1:
                    elCtrl0.ajouter();
                    break;

                case 2:
                    elCtrl0.supprimer();
                    break;

                case 3:
                    elCtrl0.modifier();
                    break;
                    
                case 4:
                    eleveDao.afficherTout();
                    break;
            }
        }while (choix <= 3 && choix > 0);
    }
}
