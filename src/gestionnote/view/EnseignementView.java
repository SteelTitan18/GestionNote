/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.view;

import gestionnote.dao.EnseignementDaoImpl;
import gestionnote.dao.IEnseignementDao;
import gestionnote.model.Classe;
import gestionnote.model.Enseignement;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author steeltitan
 */
public class EnseignementView {
    private IEnseignementDao enseignementDao = new EnseignementDaoImpl();
    
    public EnseignementView(){
        
    }
    
    public int getNumEns(Classe classe0){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nVeuillez choisir l'enseignement correspondant : ");
        List<Enseignement> ensList0 = enseignementDao.getListeEnseignement(classe0);
        for (Enseignement enseignement : ensList0) {
            enseignementDao.afficher(enseignement);
        }
        System.out.print("\tChoix : ");
        int sc1 = sc.nextInt();
        return sc1;
    }
    
//    public static Enseignement getEns(){
//        int numEns = EnseignementView.getNumEns();
//        Enseignement enseignement0 = Enseignement.getListeEns().get(numEns);
//        return enseignement0;
//    }
    
    public static int getCoef(){
        Scanner sc= new Scanner(System.in);
        System.out.print("Veuillez entrer le coefficient pour cet enseignement (2 par défaut) : ");
        int sc7 = sc.nextInt();
        return sc7; 
    }
    
    public static int getChoix(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\tMENU DES ENSEIGNEMENTS");
        System.out.println("1. Ajouter un enseignement");
        System.out.println("2. Supprimer un enseignement");
        System.out.println("3. Consulter la liste des enseignements");
        System.out.println("4. Retour");
        System.out.print("\tChoix : ");
        int choix = sc.nextInt();
        return choix;
    }
    
    public int getNumEns(){
        Scanner sc = new Scanner(System.in);
        enseignementDao.afficherTout();
        System.out.println("Veuillez entrer numéro d'ordre de l'enseignement à supprimer : ");
        int num = sc.nextInt();
        return num;
    }
    
}
