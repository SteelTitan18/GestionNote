/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.view;

import gestionnote.dao.IMatiereDao;
import gestionnote.dao.MatiereDaoImpl;
import java.util.Scanner;

/**
 *
 * @author steeltitan
 */
public class MatiereView {
    
    
    public MatiereView(){
        
    }
    
    public int getNumMatiere(){
        IMatiereDao matiereDao = new MatiereDaoImpl();
        Scanner sc = new Scanner(System.in);
        matiereDao.afficherTout();
        System.out.println("Veuillez entrez le numéro d'ordre de la matiere : ");
        int choix = sc.nextInt();
        return choix;
    }
    
    public static int getChoix(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\t--------MENU DES MATIERES------6");
        System.out.println("1. Ajouter une matière");
        System.out.println("2. Supprimer une matière");
        System.out.println("3. Consulter la liste des matières");
        System.out.println("4. Retour");
        System.out.print("\tChoix : ");
        int choix = sc.nextInt();
        return choix;
    }
    
    public int getNum(){
        IMatiereDao matiereDao = new MatiereDaoImpl();
        Scanner sc = new Scanner(System.in);
        matiereDao.afficherTout();
        System.out.print("Veuillez entrer le numéro de la matière : ");
        int num = sc.nextInt();
        return num;
    }
    
    public static String getLibelle(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Veuillez entrer le libelle de la matière : ");
        String lib = sc.nextLine();
        return lib;
    }
    
}
