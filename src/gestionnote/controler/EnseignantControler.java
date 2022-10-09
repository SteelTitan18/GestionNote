/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.controler;

import gestionnote.dao.CoursDaoImpl;
import gestionnote.dao.EnseignantDaoImpl;
import gestionnote.dao.ICoursDao;
import gestionnote.dao.IEnseignantDao;
import gestionnote.model.Cours;
import gestionnote.model.Enseignant;
import gestionnote.model.Personne;
import gestionnote.view.CoursView;
import gestionnote.view.EnseignantView;
import java.util.Scanner;

/**
 *
 * @author steeltitan
 */
public class EnseignantControler {
    private Enseignant model;
    private EnseignantView view;
    
    
    public EnseignantControler (Enseignant model, EnseignantView view){
        this.model = model;
        this.view = view;
    }
    
    public void ajouter(){
        IEnseignantDao enseignantDao = new EnseignantDaoImpl();
        Scanner scan = new Scanner(System.in);
        char rep;
        do{
            String nom = EnseignantView.getNom();
            String prenom = EnseignantView.getPrenom();
            long numMatric = EnseignantView.getNumMatric();
            String sexe = EnseignantView.getSexe();
            sexe = sexe.toUpperCase();
            int age = EnseignantView.getAge();
            int salaire = EnseignantView.getSalaire();
            Enseignant enseignant = new Enseignant(nom, prenom, sexe, numMatric, age, salaire);
            enseignantDao.ajouter(enseignant);
            System.out.println("Enseignant ajouté avec succès !");
            
            System.out.print("Voulez-vous ajouter un autre enseignant ?(o/n) : ");
            rep = scan.nextLine().charAt(0);
        }while(rep != 'n');
    }
    
    public void supprimer(){
        IEnseignantDao enseignantDao = new EnseignantDaoImpl();
        Scanner scan = new Scanner(System.in);
        char rep;
        do{
            int numEns = EnseignantView.getNumEns();
            enseignantDao.supprimer(numEns);
            System.out.println("Enseignant supprimé avec succès !");
            
            System.out.print("Voulez-vous supprimer un autre enseignant ?(o/n) : ");
            rep = scan.nextLine().charAt(0);
        }while(rep != 'n');
    }
    
    public void assigner(){
        IEnseignantDao enseignantDao = new EnseignantDaoImpl();
        ICoursDao coursDao = new CoursDaoImpl();
//        Enseignant modelEns = new Enseignant();
//        EnseignantView viewEns = new EnseignantView();
//        EnseignantControler controlerEns = new EnseignantControler(modelEns, viewEns);
        Scanner sc = new Scanner(System.in);
        char rep;
        do{
            CoursView viewC = new CoursView();
            int numCours = viewC.getNum();
            Cours cours = coursDao.getCour(numCours);
            int numEnsAss = EnseignantView.getNumEns();
            cours.setEnseignant(enseignantDao.getEnseignant(numEnsAss));
            System.out.println("Cours assigné avec succès !");
            
            System.out.print("Voulez-vous assigner un autre cours ?(o/n) : ");
            rep = sc.nextLine().charAt(0);
        }while(rep != 'n');
    }
    
    public void modifier(){
        IEnseignantDao enseignantDao = new EnseignantDaoImpl();
        Scanner scan = new Scanner(System.in);
        char rep;
        do{
            System.out.println("\n\tVeuillez entrer 0 pour conserver les valeurs iniiales ! ");
            int numEns = EnseignantView.getNumEns();
            Enseignant ens = enseignantDao.getEnseignant(numEns);
            String nomEl = EnseignantView.getNom();
            if (!"0".equals(nomEl)){
                ens.setNom(nomEl);
            }
            String prenomEl = EnseignantView.getPrenom();
            if (!"0".equals(prenomEl)){
                ens.setPrenom(prenomEl);
            }
            String sexeEl = EnseignantView.getSexe();
            sexeEl = sexeEl.toUpperCase();
            if (!"0".equals(sexeEl)){
                ens.setSexe(sexeEl);
            }
//            long numMatric = EnseignantView.getNumMatric();
//            if (numMatric != 0){
//                el.setNumMatric(numMatric);
//            }
            int ageEl = EnseignantView.getAge();
            if (ageEl != 0){
                ens.setAge(ageEl);
            }
            int salair = EnseignantView.getSalaire();
            if (salair != 0){
                ens.setSalaire(salair);
            }
            System.out.println("Enseignant modifié avec succès !");
            
            System.out.print("Voulez-vous modifier un autre enseignant ?(o/n) : ");
            rep = scan.nextLine().charAt(0);
        }while(rep != 'n');
    }
    
    public void menuEnseignant(){
        IEnseignantDao enseignantDao = new EnseignantDaoImpl();
        Enseignant ensMode0 = new Enseignant();
        EnseignantView ensView0 = new EnseignantView();
        EnseignantControler ensCtrl0 = new EnseignantControler(ensMode0, ensView0);
        int choix1;
        do
        {
            choix1 = EnseignantView.getChoix();
            switch (choix1) {
                case 1:
                    ensCtrl0.ajouter();
                    break;

                case 2:
                    ensCtrl0.supprimer();
                    break;
                    
                case 3:
                    ensCtrl0.modifier();
                    break;

                case 4:
                    ensCtrl0.assigner();
                    break;

                case 5:
                    enseignantDao.afficherTout();
                    break;
            }
        }while (choix1 <= 4 && choix1 > 0);
    }
}
