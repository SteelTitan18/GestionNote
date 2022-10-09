/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.gui;

import gestionnote.dao.EleveDaoImpl;
import gestionnote.dao.IEleveDao;
import gestionnote.model.Eleve;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author steeltitanrbrn
 */
public class ModelListeEleve extends AbstractTableModel{
    IEleveDao eleveDao = new EleveDaoImpl();
    public final List<Eleve> eleves = eleveDao.getEleves();
    
    
    private final String[] entetes = {"Matricule", "Nom", "Prénoms", "Age", "Sexe"
            , "Classe"};
    
    public ModelListeEleve(){
        super();
    }

    @Override
    public int getRowCount() {
        return eleves.size();
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
                return eleves.get(i).getNumMatric();
            case 1:
                return eleves.get(i).getNom();
            case 2:
                return eleves.get(i).getPrenom();
            case 3:
                return eleves.get(i).getAge();
            case 4:
                return eleves.get(i).getSexe();
            case 5:
                return eleves.get(i).getClasse().getLibelle();
            default:
                return null;
        }
    }
    
    public void addEleve(Eleve eleve){
        
        eleveDao.ajouter(eleve);
        
        fireTableRowsInserted(eleves.size() - 1, eleves.size() - 1);
        for (Eleve eleve1 : eleves) {
            if (eleve.getNumMatric() == eleve1.getNumMatric()){
                int sup = eleves.indexOf(eleve1);
                removeEleve(sup);
            }
        }
        eleves.add(eleve);
    }
    
    public void removeEleve(int rowIndex){
        eleves.remove(rowIndex);
        
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    
}
