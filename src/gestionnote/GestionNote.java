/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestionnote;

import gestionnote.controler.MainControler;
import gestionnote.view.MainView;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author steeltitan
 */
public class GestionNote extends JFrame{

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
//        mainFenetre fenetre = new mainFenetre();
//        fenetre.setVisible(true);
        
        GestionNote model = new GestionNote();
        MainView view = new MainView();
        MainControler controler = new MainControler(model, view);
        controler.execution();
    }
    
}
