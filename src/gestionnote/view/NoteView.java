/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.view;

import gestionnote.dao.INoteDao;
import gestionnote.dao.NoteDaoImpl;
import gestionnote.model.Eleve;
import gestionnote.model.Note;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author steeltitanrbrn
 */
public class NoteView {
    
    public NoteView(){
        
    }
    
    public static int getChoix(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\tMENU DES NOTES");
        System.out.println("1. Ajouter une note");
        System.out.println("2. Modifier une note");
        System.out.println("3. Supprimer un note");
        System.out.println("4. Consulter la liste des notes");
        System.out.println("5. Retour");
        System.out.print("\tChoix : ");
        int choix = sc.nextInt();
        return choix;
    }
    
    public int getNumNote(Eleve eleve){
        Scanner sc = new Scanner(System.in);
        INoteDao noteDao = new NoteDaoImpl();
        List<Note> notes = noteDao.getNotes(eleve);
        for (Note note : notes) {
            noteDao.afficher(note);
        }
        System.out.println("Veuillez entrer l'ID de la note : ");
        int num = sc.nextInt();
        return num;
    }
}
