/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.controler;

import gestionnote.dao.EnseignantDaoImpl;
import gestionnote.dao.EnseignementDaoImpl;
import gestionnote.dao.IEnseignantDao;
import gestionnote.dao.IEnseignementDao;
import gestionnote.dao.IMatiereDao;
import gestionnote.dao.INiveauDao;
import gestionnote.dao.MatiereDaoImpl;
import gestionnote.dao.NiveauDaoImpl;
import gestionnote.model.Enseignement;
import gestionnote.model.Matiere;
import gestionnote.model.Niveau;
import gestionnote.view.EnseignementView;
import gestionnote.view.MatiereView;
import gestionnote.view.NiveauView;

/**
 *
 * @author steeltitan
 */
public class EnseignementControler {
    private Enseignement model;
    private EnseignementView view;
    
    
    public EnseignementControler(Enseignement model, EnseignementView view){
        this.model = model;
        this.view = view;
    }
    

    public Enseignement getModel() {
        return model;
    }

    public void setModel(Enseignement model) {
        this.model = model;
    }

    public EnseignementView getView() {
        return view;
    }

    public void setView(EnseignementView view) {
        this.view = view;
    }
    
    public void menuEnseignement(){
        IEnseignementDao enseignementDao = new EnseignementDaoImpl();
        int choix;
        EnseignementView ensV = new EnseignementView();
        Enseignement ensM = new Enseignement();
        EnseignementControler ensCtrl = new EnseignementControler(ensM, ensV);
        do
        {
            choix = EnseignementView.getChoix();
            switch (choix) {
                case 1:
                    ensCtrl.ajouter();
                    break;

                case 2:
                    ensCtrl.supprimer();
                    break;

                case 3:
                    enseignementDao.afficherTout();
                    break;
                    
                case 4:
                    break;
            }
        }while (choix <= 3 && choix > 0);
    }
    
    public void ajouter(){
        INiveauDao niveauDao = new NiveauDaoImpl();
        IEnseignementDao enseignementDao = new EnseignementDaoImpl();
        IMatiereDao matiereDao = new MatiereDaoImpl();
        NiveauView viewN = new NiveauView();
        MatiereView viewM = new MatiereView();
        int choixNiv = viewN.getNumNveau();
        int choixMatiere = viewM.getNumMatiere();
        Niveau niveau = niveauDao.getInstance(choixNiv);
        Matiere matiere = matiereDao.getMatiere(choixMatiere);
        Enseignement enseignement = new Enseignement(matiere, niveau, 2);
        enseignementDao.ajouter(enseignement);
        System.out.println("Enseignement ajouté avec succès !");
    }
    
    public void supprimer(){
        IEnseignementDao enseignementDao = new EnseignementDaoImpl();
        int choix = view.getNumEns();
        enseignementDao.supprimer(choix);
        System.out.println("Enseignement supprimé avec succès !");
    }
    
    
}
