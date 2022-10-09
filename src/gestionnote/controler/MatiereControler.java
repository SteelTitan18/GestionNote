/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.controler;

import gestionnote.dao.IMatiereDao;
import gestionnote.dao.MatiereDaoImpl;
import gestionnote.model.Matiere;
import gestionnote.view.MatiereView;

/**
 *
 * @author steeltitan
 */
public class MatiereControler {
    private Matiere model;
    private MatiereView view;
    
    
    public MatiereControler(Matiere model, MatiereView view){
        this.model = model;
        this.view = view;
    }
    
    public void menuMatiere(){
        IMatiereDao matiereDao = new MatiereDaoImpl();
        Matiere matMode0 = new Matiere();
        MatiereView matView0 = new MatiereView();
        MatiereControler matCtrl0 = new MatiereControler(matMode0, matView0);
        int choix1;
        do
        {
            choix1 = MatiereView.getChoix();
            switch (choix1) {
                case 1:
                    matCtrl0.ajouter();
                    break;

                case 2:
                    matCtrl0.supprimer();
                    break;

                case 3:
                    matiereDao.afficherTout();
                    break;
            }
        }while (choix1 <= 3 && choix1 > 0);
    }
    
    public void ajouter(){
        IMatiereDao matiereDao = new MatiereDaoImpl();
        String libelle = MatiereView.getLibelle();
        Matiere matiere = new Matiere(libelle);
        matiereDao.ajouter(matiere);
        System.out.println("Matière ajoutée avec succès !"); 
    }
    
    public void supprimer(){
        IMatiereDao matiereDao = new MatiereDaoImpl();
        int num = view.getNum();
        matiereDao.supprimer(num);
        System.out.println("Matière supprimée avec succès !");
    }
}
