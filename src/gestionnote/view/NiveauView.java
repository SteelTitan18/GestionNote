/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.view;

import gestionnote.dao.INiveauDao;
import gestionnote.dao.NiveauDaoImpl;
import gestionnote.model.Niveau;
import java.util.Scanner;

/**
 *
 * @author steeltitan
 */
public class NiveauView {
    
    
    public NiveauView(){
        
    }
    
    public int getNumNveau(){
        INiveauDao niveauDao = new NiveauDaoImpl();
        Scanner sc = new Scanner(System.in);
        niveauDao.afficherTout();
        System.out.print("Veuillez choisir le niveau : ");
        int choix = sc.nextInt();
        return choix;
    }
    
    public static String getSubdivision(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Veuillez entrer la subdivision : ");
        String rep = sc.nextLine();
        return rep;
    }
}
