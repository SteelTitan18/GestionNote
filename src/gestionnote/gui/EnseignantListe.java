/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.gui;

import gestionnote.dao.ClasseDaoImpl;
import gestionnote.dao.CoursDaoImpl;
import gestionnote.dao.EleveDaoImpl;
import gestionnote.dao.EnseignantDaoImpl;
import gestionnote.dao.IClasseDao;
import gestionnote.dao.ICoursDao;
import gestionnote.dao.IEleveDao;
import gestionnote.dao.IEnseignantDao;
import gestionnote.model.Classe;
import gestionnote.model.Cours;
import gestionnote.model.Eleve;
import gestionnote.model.Enseignant;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author steeltitanrbrn
 */
public class EnseignantListe extends JFrame{
    private ModelListeEnseignant model = new ModelListeEnseignant();
    private JTable table;
    private JTextField matric, nom, prenom, age, salaire;
    private JComboBox<String> sexeChoix, coursChoix;
    private JLabel lid, lnom, lprenom,  lage, lsexe, lsalaire, labcours;
    private ICoursDao coursDao = new CoursDaoImpl();
    
    
    public EnseignantListe(){
        super();
        setTitle("ENSEIGNANTS");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        table = new JTable(model);
        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
        
        
        
        JPanel boutons = new JPanel();
        
        boutons.add(new JButton(new AddAction()));
        boutons.add(new JButton(new RemoveAction()));
        boutons.add(new JButton(new CoursAction()));
        
        getContentPane().add(boutons, BorderLayout.SOUTH);
        
        
        String[] sexe = {"M", "F"};
//        IClasseDao classeDao = new ClasseDaoImpl();
        List<Cours> lcours = coursDao.getCours();
        String[] cours = new String[lcours.size()];
        int cpt = 0;
        for (Cours _cours : lcours) {
            cours[cpt] = _cours.getId() + " " + _cours.getEnseignement()
                    .getMatiere() + " " + _cours.getClasse().getLibelle();
            cpt++;
        }
        JPanel ajout = new JPanel(new GridLayout(1, 0));
        matric = new JTextField();
        nom = new JTextField();
        prenom = new JTextField();
        age = new JTextField();
        salaire = new JTextField();
        sexeChoix = new JComboBox<>(sexe);
        coursChoix = new JComboBox<>(cours);
        lid = new JLabel("Matricule");
        lnom = new JLabel("Nom");
        lprenom = new JLabel("Prénom");
        lage = new JLabel("Age");
        lsexe = new JLabel("Sexe");
        lsalaire = new JLabel("Salaire");
        
        ajout.add(lid);
        ajout.add(matric);
//        ajout.add(new JSeparator());
        ajout.add(lnom);
        ajout.add(nom);
//        ajout.add(new JSeparator());
        ajout.add(lprenom);
        ajout.add(prenom);
//        ajout.add(new JSeparator());
        ajout.add(lage);
        ajout.add(age);
//        ajout.add(new JSeparator());
        ajout.add(lsexe);
        ajout.add(sexeChoix);
//        ajout.add(new JSeparator());
        ajout.add(lsalaire);
        ajout.add(salaire);
        getContentPane().add(ajout, BorderLayout.NORTH);
        
        pack();
    }
    
    public static void main(String[] args) {
        new EnseignantListe().setVisible(true);
    }
    
    private class AddAction extends AbstractAction {
        private AddAction() {
            super("Enregistrer");
        }
 
        @Override
        public void actionPerformed(ActionEvent e) {

            int _id = Integer.parseInt(matric.getText());
            String _nom = nom.getText();
            String _prenom = prenom.getText();
            int _age = Integer.parseInt(age.getText());
            int sal = Integer.parseInt(salaire.getText());
            String _sexe = sexeChoix.getItemAt(sexeChoix.getSelectedIndex());
            Enseignant enseignant = new Enseignant(_nom, _prenom, _sexe, _id, _age, sal);
            model.addEnseignant(enseignant);
            
            nom.setText("");
            matric.setText("");
            prenom.setText("");
            age.setText("");
            salaire.setText("");
        }
    }
    
    private class RemoveAction extends AbstractAction {
        private RemoveAction() {
            super("Supprimer");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int[] selection = table.getSelectedRows();
            IEnseignantDao enseignantDao = new EnseignantDaoImpl();

            for(int i = selection.length - 1; i >= 0; i--){
                long mat = (long)table.getValueAt(selection[i], 0);
                model.removeEnseignant(selection[i]);
                enseignantDao.supprimer(mat);
            }
        }
    }
    
    private class CoursAction extends AbstractAction {
        private CoursAction() {
            super("Notes");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
////            int[] selection = table.getSelectedRows();
////            IEleveDao eleveDao = new EleveDaoImpl();
////
////            for(int i = selection.length - 1; i >= 0; i--){
////                long mat = (long)table.getValueAt(selection[i], 0);
////                model.removeEleve(selection[i]);
////                eleveDao.supprimer(mat);
////            }
//            int ln = table.getSelectedRow();
//            long mat = (long)table.getValueAt(ln, 0);
//            EleveNotes notes = new EleveNotes(mat);
//            notes.setVisible(true);
        }
    }
    
}
