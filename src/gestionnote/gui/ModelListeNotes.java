/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.gui;

import gestionnote.dao.EleveDaoImpl;
import gestionnote.dao.INoteDao;
import gestionnote.dao.NoteDaoImpl;
import gestionnote.model.Note;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author steeltitanrbrn
 */
public class ModelListeNotes extends AbstractTableModel{
    INoteDao noteDao = new NoteDaoImpl();
    private long matric;
    public List<Note> notes = new ArrayList<>();
    
    
    private final String[] entetes = {"ID", "Valeur", "Matière", "Evaluation"};
    
    public ModelListeNotes(long matric){
        super();
        this.matric = matric;
        notes = noteDao.getNotes(new EleveDaoImpl().getEleve(matric));
    }

    @Override
    public int getRowCount() {
        return notes.size();
    }

    @Override
    public int getColumnCount() {
        return entetes.length;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
    
    @Override
    public Object getValueAt(int i, int j) {
        switch(j){
            case 0:
                return notes.get(i).getId_note();
            case 1:
                return notes.get(i).getValeur();
            case 2:
                return notes.get(i).getEvaluation().getCours().getEnseignement().getMatiere().getLibelle();
            case 3:
                return notes.get(i).getEvaluation().getType().getLibelle();
//            case 4:
//                return notes.get(i).getEleve().getNumMatric();
//            case 5:
//                return notes.get(i).getEleve().getNom();
//            case 6:
//                return notes.get(i).getEleve().getPrenom();
//            case 7:
//                return notes.get(i).getEleve().getClasse().getLibelle();
            default:
                return null;
        }
    }
    
    public void addNote(Note note){
        Note note2 = noteDao.ajouter(note);
        
        fireTableRowsInserted(notes.size() - 1, notes.size() - 1);
        for (Note note1 : notes) {
            if (note.getId_note() == note1.getId_note()){
                int sup = notes.indexOf(note1);
                System.out.println(sup);
                notes.remove(sup);
            }
        }
        notes.add(note2);
    }
    
    public void removeNote(int rowIndex){
        long id = notes.get(rowIndex).getId_note();
        notes.remove(rowIndex);
        Note note = noteDao.getNote(id);
        noteDao.supprimer(note);
        
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    
}
