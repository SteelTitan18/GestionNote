/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.controler;

import gestionnote.dao.ClasseDaoImpl;
import gestionnote.dao.IClasseDao;
import gestionnote.dao.INiveauDao;
import gestionnote.dao.NiveauDaoImpl;
import gestionnote.model.Classe;
import gestionnote.model.Niveau;
import gestionnote.view.ClasseView;
import gestionnote.view.NiveauView;

/**
 *
 * @author steeltitan
 */
public class ClasseControler {
    private Classe model;
    private ClasseView view;
    
    public ClasseControler(Classe model, ClasseView view){
        this.model = model;
        this.view = view;
    }
    
    public void menuClasse(){
        IClasseDao classeDao = new ClasseDaoImpl();
        Classe ensMode0 = new Classe();
        ClasseView ensView0 = new ClasseView();
        ClasseControler ensCtrl0 = new ClasseControler(ensMode0, ensView0);
        int choix1;
        do
        {
            choix1 = ensView0.getChoix();
            switch (choix1) {
                case 1:
                    ensCtrl0.ajouter();
                    break;

                case 2:
                    ensCtrl0.supprimer();
                    break;

                case 3:
                    classeDao.afficherTout();
                    break;
            }
        }while (choix1 <= 3 && choix1 > 0);
    }
    
    public void ajouter(){
        NiveauView nView = new NiveauView();
        INiveauDao niveauDao = new NiveauDaoImpl();
        IClasseDao classeDao = new ClasseDaoImpl();
        int numNiv = nView.getNumNveau();
        Niveau niveau = niveauDao.getInstance(numNiv);
        String sub = NiveauView.getSubdivision();
        sub = sub.toUpperCase();
        Classe classe = new Classe(niveau, sub);
        classeDao.ajouter(classe);
        System.out.println("Classe ajoutée avec succès !");
    }
    
    public void supprimer(){
        IClasseDao classeDao = new ClasseDaoImpl();
        int numClasse = view.getNumClasse();
        classeDao.supprimer(numClasse);
        System.out.println("Classe supprimée avec succès !");
    }
}
