/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.view;

import gestionnote.dao.ClasseDaoImpl;
import gestionnote.dao.IClasseDao;
import gestionnote.model.Classe;
import java.util.Scanner;

/**
 *
 * @author steeltitan
 */
public class ClasseView {
    private IClasseDao classeDao = new ClasseDaoImpl();
    
    public ClasseView(){
        
    }
    
    public int getNumClasse(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nVeuillez choisir la classe correspondante : ");
        classeDao.afficherTout();
        System.out.print("\tChoix : ");
        int sc3 = sc.nextInt();
        return sc3;
    }
    
    public int getChoix(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\t-------MENU DES CLASSES------");
        System.out.println("1. Ajouter une classe");
        System.out.println("2. Supprimer une classe");
        System.out.println("3. Consulter la liste des classes");
        System.out.println("4. Retour");
        System.out.print("\tChoix : ");
        int choix = sc.nextInt();
        return choix;
    } 
    
    
}
