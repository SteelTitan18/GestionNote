/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.view;

import gestionnote.dao.ITypeDao;
import gestionnote.dao.TypeDaoImpl;
import gestionnote.model.Type;
import java.util.Scanner;

/**
 *
 * @author steeltitan
 */
public class TypeView {
    
    public TypeView(){
        
    }
    
    public String getType(){
        ITypeDao typeDao = new TypeDaoImpl();
        Scanner sc = new Scanner(System.in);
        System.out.println("\nVeuillez choisir le type de l'évaluation :  ");
        typeDao.afficherTout();
        System.out.print("\tChoix : ");
        String sc4 = sc.nextLine();
        return sc4;
    }
    
}
