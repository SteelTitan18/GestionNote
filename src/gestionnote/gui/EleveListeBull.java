/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.gui;

import gestionnote.dao.ClasseDaoImpl;
import gestionnote.dao.IClasseDao;
import gestionnote.dao.PeriodeDaoImpl;
import gestionnote.gui.EleveBull;
import gestionnote.model.Periode;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author steeltitanrbrn
 */
public class EleveListeBull extends JFrame{
    private ModelListeEleve model = new ModelListeEleve();
    private JTable table;
    private JTextField matric;
    private JComboBox<String> periodeChoix;
    private JLabel leleve, lperiode;
    private IClasseDao classeDao = new ClasseDaoImpl();
    
    
    public EleveListeBull(){
        super();
        setTitle("ELEVES");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        table = new JTable(model);
        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
        
        
        
        JPanel boutons = new JPanel();
        
        boutons.add(new JButton(new BullAction()));
        
        getContentPane().add(boutons, BorderLayout.SOUTH);
        
        
        
        IClasseDao classeDao = new ClasseDaoImpl();
        
//        List<Eleve> leleves = new EleveDaoImpl().getEleves();
//        String[] eleves = new String[leleves.size()];
        
        List<Periode> lperiodes = new PeriodeDaoImpl().getPeriodes();
        String[] periodes = new String[lperiodes.size()];
        
//        int cpt = 0;
//        for (Eleve eleve : leleves) {
//            eleves[cpt] = eleve.getNumMatric() + " " + eleve.getNom() + eleve
//                    .getPrenom() + " " + eleve.getClasse().getLibelle();
//            cpt++;
//        }
        
        int cpt = 0;
        for (Periode periode : lperiodes){
            periodes[cpt] = periode.getCode() + " " + periode.getLibelle();
            cpt++;
        }
        
        JPanel ajout = new JPanel(new GridLayout(1, 0));
//        eleveChoix = new JComboBox<>(eleves);
        periodeChoix = new JComboBox<>(periodes);
//        leleve = new JLabel("Matricule");
        lperiode = new JLabel("Période");
        
//        ajout.add(leleve);
//        ajout.add(matric);
//        ajout.add(new JSeparator());
        ajout.add(lperiode);
        ajout.add(periodeChoix);
//        ajout.add(new JSeparator());
//        ajout.add(lprenom);
//        ajout.add(prenom);
////        ajout.add(new JSeparator());
//        ajout.add(lage);
//        ajout.add(age);
////        ajout.add(new JSeparator());
//        ajout.add(lsexe);
//        ajout.add(sexeChoix);
////        ajout.add(new JSeparator());
//        ajout.add((lclasse));
//        ajout.add(classeChoix);
        getContentPane().add(ajout, BorderLayout.NORTH);
        
        pack();
    }
    
    public static void main(String[] args) {
        new EleveListeBull().setVisible(true);
    }
    
    
    private class BullAction extends AbstractAction {
        private BullAction() {
            super("Bulletin");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
//            int[] selection = table.getSelectedRows();
//            IEleveDao eleveDao = new EleveDaoImpl();
//
//            for(int i = selection.length - 1; i >= 0; i--){
//                long mat = (long)table.getValueAt(selection[i], 0);
//                model.removeEleve(selection[i]);
//                eleveDao.supprimer(mat);
//            }
            int ln = table.getSelectedRow();
            long mat = (long)table.getValueAt(ln, 0);
            long idp = Long.parseLong(periodeChoix.getItemAt(periodeChoix.getSelectedIndex()).charAt(0)+"");
            EleveBull bull;
            try {
                bull = new EleveBull(mat, idp);
                bull.setVisible(true);
                bull.setLocationRelativeTo(null);
            } catch (IOException ex) {
                Logger.getLogger(EleveListeBull.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
}
