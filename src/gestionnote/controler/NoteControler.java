/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.controler;

import gestionnote.dao.EleveDaoImpl;
import gestionnote.dao.IEleveDao;
import gestionnote.dao.INoteDao;
import gestionnote.dao.NoteDaoImpl;
import gestionnote.model.Eleve;
import gestionnote.model.Note;
import gestionnote.view.EleveView;
import gestionnote.view.NoteView;
import java.util.Scanner;

/**
 *
 * @author steeltitanrbrn
 */
public class NoteControler {
    private Note model;
    private NoteView view;
    
    public NoteControler(Note model, NoteView view){
        this.model = model;
        this.view = view;
    }
    
    public void menuNote(){
        Eleve eleve = new Eleve();
        EleveView elview = new EleveView();
        EleveControler elctrl = new EleveControler(eleve, elview);
        IEleveDao eleveDao = new EleveDaoImpl();
        Note model0 = new Note();
        NoteView view0 = new NoteView();
        NoteControler notCtrl = new NoteControler(model0, view0);
        INoteDao noteDao = new NoteDaoImpl();
        int choix1;
        do
        {
            choix1 = NoteView.getChoix();
            switch (choix1) {
                case 1:
                    elctrl.getNoteEleves();
                    break;

                case 2:
                    notCtrl.modifier();
                    break;
                    
                case 3:
                    notCtrl.supprimer();
                    break;

                case 4:
                    noteDao.afficherTout();
                    break;
            }
        }while (choix1 <= 4 && choix1 > 0);
    }
    
    
    public void modifier(){
        IEleveDao eleveDao = new EleveDaoImpl();
        INoteDao noteDao = new NoteDaoImpl();
        NoteView noteView = new NoteView();
        Scanner scan = new Scanner(System.in);
        char rep;
        do{
            System.out.println("\n\tVeuillez entrer 0 pour conserver les valeurs initiales ! ");
            Eleve eleve = eleveDao.getEleve(EleveView.getNumMatric());
            Note note = noteDao.getNote(noteView.getNumNote(eleve));
            double val = EleveView.getNote(eleve);
            if (val != 0){
                note.setValeur(val);
            }
            System.out.println("Note modifiée avec succès !");
            
            System.out.print("Voulez-vous modifier une autre note ?(o/n) : ");
            rep = scan.nextLine().charAt(0);
        }while(rep != 'n');
    }
    
    public void supprimer(){
        IEleveDao eleveDao = new EleveDaoImpl();
        INoteDao noteDao = new NoteDaoImpl();
        NoteView noteView = new NoteView();
        Scanner scan = new Scanner(System.in);
        char rep;
        do{
            Eleve eleve = eleveDao.getEleve(EleveView.getNumMatric());
            Note note = noteDao.getNote(noteView.getNumNote(eleve));
            noteDao.supprimer(note);
            System.out.println("Note supprimée avec succès !");
            
            System.out.print("Voulez-vous supprimer une autre note ?(o/n) : ");
            rep = scan.nextLine().charAt(0);
        }while(rep != 'n');
    }
}
