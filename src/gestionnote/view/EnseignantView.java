/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.view;

import gestionnote.dao.EnseignantDaoImpl;
import gestionnote.dao.IEnseignantDao;
import gestionnote.model.Enseignant;
import java.util.Scanner;

/**
 *
 * @author steeltitan
 */
public class EnseignantView {
    
    public EnseignantView(){
        
    }
    
    public static int getNumEns(){
        IEnseignantDao enseignantDao = new EnseignantDaoImpl();
        Scanner scan1 = new Scanner(System.in);
        enseignantDao.afficherTout();
        System.out.print("Veuillez entrer le numéro d'ordre de l'enseignant : ");
        int numSuppr = scan1.nextInt();
        return numSuppr;
    }
    
    public static String getNom(){
        Scanner scan0 = new Scanner(System.in);
        System.out.print("Entrez le nom de l'enseignant : ");
        String nomEl = scan0.nextLine();
        return nomEl;
    }
    
    public static String getPrenom(){
        Scanner scan0 = new Scanner(System.in);
        System.out.print("Entrez le(s) prénom(s) de l'enseignant : ");
        String prenomEl = scan0.nextLine();
        return prenomEl;
    }
    
    public static long getNumMatric(){
        Scanner scan0 = new Scanner(System.in);
        System.out.print("Entrez le numéro matricule de l'enseignant : ");
        long numEl = scan0.nextLong();
        scan0.nextLine();
        return numEl;
    }
    
    public static int getAge(){
        Scanner scan0 = new Scanner(System.in);
        System.out.print("Entrez l'âge de l'enseignant : ");
        int ageEl = scan0.nextInt();
        return ageEl;
    }
    
    public static int getSalaire(){
        Scanner scan0 = new Scanner(System.in);
        System.out.println("Veuillez entrer le salaire de l'enseignant : ");
        int salaireEns = scan0.nextInt();
        return salaireEns;
    }
    
    public static int getChoix(){
        Scanner scan = new Scanner(System.in);
        System.out.println("\n\tMENU DES ENSEIGNANTS");
        System.out.println("1. Ajouter un enseignant");
        System.out.println("2. Supprimer un enseignant");
        System.out.println("3. Modifier un enseignant");
        System.out.println("4. Assigner un cours à enseignant");
        System.out.println("5. Consulter la liste des enseignants");
        System.out.println("6. Retour");
        System.out.print("\tChoix : ");
        int choix1 = scan.nextInt();
        return choix1;
    }
    
    public static String getSexe(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrez le sexe(M/F) : ");
        String sex = sc.nextLine();
        return sex;
    }
}
