/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.controler;

import gestionnote.dao.ClasseDaoImpl;
import gestionnote.dao.CoursDaoImpl;
import gestionnote.dao.EnseignementDaoImpl;
import gestionnote.dao.IClasseDao;
import gestionnote.dao.ICoursDao;
import gestionnote.dao.IEnseignementDao;
import gestionnote.model.Classe;
import gestionnote.model.Cours;
import gestionnote.model.Enseignement;
import gestionnote.view.ClasseView;
import gestionnote.view.CoursView;
import gestionnote.view.EnseignementView;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author steeltitan
 */
public class CoursControler {
    private Cours model;
    private CoursView view ;
    
    public CoursControler(Cours model, CoursView view){
        this.model = model;
        this.view = view;
    }
    
    public Cours getCours(Classe classe, Enseignement enseignement){
        ICoursDao coursDao = new CoursDaoImpl();
        List<Cours> listCOurs = coursDao.getCours();
        for (Cours listCOur : listCOurs) {
            if (listCOur.getClasse() == classe 
                    && listCOur.getEnseignement().getMatiere() == enseignement.getMatiere()){
                return listCOur;
            }
            
        }
        return null;
    }
    
    public void menuCours(){
        Cours model0 = new Cours();
        CoursView view0 = new CoursView();
        ICoursDao coursDao = new CoursDaoImpl();
        CoursControler coCtrl0 = new CoursControler(model0, view0);
        int choix;
        do {
            choix = CoursView.getCHoix();
            switch (choix) {
                case 1:
                    coCtrl0.ajouter();
                    break;

                case 2:
                    coCtrl0.supprimer();
                    break;
                    
                case 3:
                    coursDao.afficherTout();
                    break;
            }
        }while (choix <= 3 && choix > 0);
    }
    
    public void ajouter(){
        IClasseDao classeDao = new ClasseDaoImpl();
        IEnseignementDao enseignementDao = new EnseignementDaoImpl();
        ICoursDao coursDao = new CoursDaoImpl();
        ClasseView clView = new ClasseView();
        EnseignementView ensView = new EnseignementView();
        Scanner scan = new Scanner(System.in);
        char rep;
        do{
            Classe classe = classeDao.getClasse(clView.getNumClasse());
            Enseignement enseignement = enseignementDao.getEnseignement(ensView.getNumEns(classe));
            Cours cours = new Cours(enseignement, classe);
            coursDao.ajouter(cours);
            System.out.println("Cours ajouté avec succès !");
            
            System.out.print("Voulez-vous ajouter un autre cours ?(o/n) : ");
            rep = scan.nextLine().charAt(0);
        }while(rep != 'n');
    }
    
    public void supprimer(){
        ICoursDao coursDao = new CoursDaoImpl();
        CoursView coView = new CoursView();
        Scanner scan = new Scanner(System.in);
        char rep;
        do{
            coursDao.supprimer(coursDao.getCour(coView.getNum()));
            
            System.out.print("Voulez-vous supprimer un autre cours ?(o/n) : ");
            rep = scan.nextLine().charAt(0);
        }while(rep != 'n');
    }
}
