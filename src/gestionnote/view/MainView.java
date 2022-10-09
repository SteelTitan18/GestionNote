/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.view;

import java.util.Scanner;

/**
 *
 * @author steeltitan
 */
public class MainView {
    
    public MainView(){
        
    }
    
    public static int getChoix(){
        Scanner sc = new Scanner(System.in);
        int ch0;
        System.out.println("\n#################   GESTION SCOLAIRE ################");
        System.out.println("\t\tMENU");
        System.out.println("1. Gestion des notes");
        System.out.println("2. Modifier la liste des élèves");
        System.out.println("3. Obtenir un bulletin");
        System.out.println("4. Modifier la liste des enseignants");
        System.out.println("5. Modifier la liste des classes");
        System.out.println("6. Modifier la liste des enseignements");
        System.out.println("7. Modifier la liste des cours");
        System.out.println("8. Modifier la liste des matières");
        System.out.println("9. Consulter la liste du personnel");
        System.out.println("10. Quitter");
        System.out.print("\tChoix : ");
        ch0 =sc.nextInt();
        return ch0;
    }
    
}
