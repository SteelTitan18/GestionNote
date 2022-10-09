/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.view;

import gestionnote.dao.CoursDaoImpl;
import gestionnote.dao.ICoursDao;
import gestionnote.model.Cours;
import java.util.Scanner;

/**
 *
 * @author steeltitan
 */
public class CoursView {
    private ICoursDao coursDao = new CoursDaoImpl();
    
    public CoursView(){
        
    }
    
    public int getNum(){
        Scanner scan2 = new Scanner(System.in);
        coursDao.afficherTout();
        System.out.print("Veuillez entrer l'ID du cours : ");
        int numCoursAss = scan2.nextInt();
        return numCoursAss;
    } 
    
    public static int getCHoix(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\tMENU DES COURS");
        System.out.println("1. Ajouter un cours");
        System.out.println("2. Supprimer un cours");
        System.out.println("3. Consulter la liste des cours");
        System.out.println("4. Retour");
        System.out.print("\tChoix : ");
        int choix = sc.nextInt();
        return choix;
    }
}
