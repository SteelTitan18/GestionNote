/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.gui;

import gestionnote.dao.EnseignantDaoImpl;
import gestionnote.dao.IEnseignantDao;
import gestionnote.model.Enseignant;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author steeltitanrbrn
 */
public class ModelListeEnseignant extends AbstractTableModel{
    IEnseignantDao enseignantDao = new EnseignantDaoImpl();
    public final List<Enseignant> enseignants = enseignantDao.getListeEnseignant();
    
    
    private final String[] entetes = {"Matricule", "Nom", "Prénoms", "Age", "Sexe"
            , "Salaire"};
    
    public ModelListeEnseignant(){
        super();
    }

    @Override
    public int getRowCount() {
        return enseignants.size();
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
                return enseignants.get(i).getNumMatric();
            case 1:
                return enseignants.get(i).getNom();
            case 2:
                return enseignants.get(i).getPrenom();
            case 3:
                return enseignants.get(i).getAge();
            case 4:
                return enseignants.get(i).getSexe();
            case 5:
                return enseignants.get(i).getSalaire();
            default:
                return null;
        }
    }
    
    public void addEnseignant(Enseignant enseignant){
        enseignantDao.ajouter(enseignant);
        
        fireTableRowsInserted(enseignants.size() - 1, enseignants.size() - 1);
        for (Enseignant ens : enseignants) {
            if (enseignant.getNumMatric() == ens.getNumMatric()){
                int sup = enseignants.indexOf(ens);
                removeEnseignant(sup);
            }
        }
        enseignants.add(enseignant);
    }
    
    public void removeEnseignant(int rowIndex){
        enseignants.remove(rowIndex);
        
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    
}
