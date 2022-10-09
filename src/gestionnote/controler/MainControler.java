/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.controler;

import gestionnote.GestionNote;
import gestionnote.dao.ClasseDaoImpl;
import gestionnote.dao.CoursDaoImpl;
import gestionnote.dao.EleveDaoImpl;
import gestionnote.dao.EnseignantDaoImpl;
import gestionnote.dao.EnseignementDaoImpl;
import gestionnote.dao.EvaluationDaoImpl;
import gestionnote.dao.IClasseDao;
import gestionnote.dao.ICoursDao;
import gestionnote.dao.IEleveDao;
import gestionnote.dao.IEnseignantDao;
import gestionnote.dao.IEnseignementDao;
import gestionnote.dao.IEvaluationDao;
import gestionnote.dao.IMatiereDao;
import gestionnote.dao.INiveauDao;
import gestionnote.dao.INoteDao;
import gestionnote.dao.IPeriodeDao;
import gestionnote.dao.IPersonneDao;
import gestionnote.dao.ITypeDao;
import gestionnote.dao.MatiereDaoImpl;
import gestionnote.dao.NiveauDaoImpl;
import gestionnote.dao.NoteDaoImpl;
import gestionnote.dao.PeriodeDaoImpl;
import gestionnote.dao.PersonneDaoImpl;
import gestionnote.dao.TypeDaoImpl;
import gestionnote.model.Classe;
import gestionnote.model.Cours;
import gestionnote.model.Eleve;
import gestionnote.model.Enseignant;
import gestionnote.model.Enseignement;
import gestionnote.model.Matiere;
import gestionnote.model.Note;
import gestionnote.model.Personne;
import gestionnote.view.ClasseView;
import gestionnote.view.CoursView;
import gestionnote.view.EleveView;
import gestionnote.view.EnseignantView;
import gestionnote.view.EnseignementView;
import gestionnote.view.MainView;
import gestionnote.view.MatiereView;
import gestionnote.view.NoteView;
import java.util.Scanner;

/**
 *
 * @author steeltitan
 */
public class MainControler {
    private GestionNote model;
    private MainView view;
    
    public MainControler(GestionNote model, MainView view){
        this.model = model;
        this.view = view;
    }
    
    public void execution(){
        ICoursDao coursDao = new CoursDaoImpl();
        IPersonneDao personneDao = new PersonneDaoImpl();
        IEleveDao eleveDao = new EleveDaoImpl();
        IEnseignantDao enseignantDao = new EnseignantDaoImpl();
        ITypeDao typeDao = new TypeDaoImpl();
        INoteDao noteDao = new NoteDaoImpl();
        INiveauDao niveauDao = new NiveauDaoImpl();
        IMatiereDao matiereDao = new MatiereDaoImpl();
        IEvaluationDao evaluationDao = new EvaluationDaoImpl();
        IEnseignementDao enseignementDao = new EnseignementDaoImpl();
        IClasseDao classeDao = new ClasseDaoImpl();
        IPeriodeDao periodeDao = new PeriodeDaoImpl();
        
        int ch0;
        Scanner sc = new Scanner(System.in);
        EnseignementView ensView = new EnseignementView();
        Enseignement ensModel = new Enseignement();
        EnseignementControler ensControler = new EnseignementControler(ensModel, ensView);
        
        EleveView elView0 = new EleveView();
        Eleve elModel0 = new Eleve();
        EleveControler Ec0 = new EleveControler(elModel0, elView0);
        
        MatiereView matView = new MatiereView();
        Matiere matModel = new Matiere();
        MatiereControler matControler = new MatiereControler(matModel, matView);
        
        CoursView coView = new CoursView();
        Cours coModel = new Cours();
        CoursControler coControler = new CoursControler(coModel, coView);
        
        NoteView noteView = new NoteView();
        Note noModel = new Note();
        NoteControler noteControler = new NoteControler(noModel, noteView);
        
        ClasseView classeView = new ClasseView();
        Classe classeModel = new Classe();
        ClasseControler classeControler = new ClasseControler(classeModel, classeView);
        
        Enseignant enseignantModel = new Enseignant();
        EnseignantView enseignantView = new EnseignantView();
        EnseignantControler enseignantControler = new EnseignantControler(enseignantModel, enseignantView);
        do{
            ch0 = MainView.getChoix();
            switch (ch0) {
                case 1:
                    noteControler.menuNote();
                    break;

                case 2:
                    Ec0.menuEleve();
                    break;
                    
                case 3:
                    Ec0.afficherBulletin();
                    break;

                case 4:
                    enseignantControler.menuEnseignant();
                    break;
                    
                case 5:
                    classeControler.menuClasse();
                    break;
                    
                case 6:
                    ensControler.menuEnseignement();
                    break;
                    
                case 7:
                    coControler.menuCours();
                    break;   
                    
                case 8:
                    matControler.menuMatiere();
                    break;
                    
                case 9:
                    personneDao.afficherTout();
                    break;
            }
        }while (ch0 <= 8 && ch0 > 0);
    }
    
}
