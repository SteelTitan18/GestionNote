/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.gui;

import gestionnote.dao.ClasseDaoImpl;
import gestionnote.dao.EleveDaoImpl;
import gestionnote.dao.EvaluationDaoImpl;
import gestionnote.dao.IClasseDao;
import gestionnote.dao.IEleveDao;
import gestionnote.dao.IEvaluationDao;
import gestionnote.model.Classe;
import gestionnote.model.Eleve;
import gestionnote.model.Evaluation;
import gestionnote.model.Note;
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
public class EleveNotes extends JFrame{
    private ModelListeNotes model;
    private JTable table;
    private int matri;
    private JTextField id, valeur;
    private JComboBox<String> evaluationChoix;
    private JLabel lid, lvaleur, leval;
    private IClasseDao classeDao = new ClasseDaoImpl();
    private IEleveDao eleveDao = new EleveDaoImpl();
    private IEvaluationDao evaluationDao = new EvaluationDaoImpl();
    private Eleve eleve = new Eleve();
    
    
    public EleveNotes(long matric){
        super();
        eleve = eleveDao.getEleve(matric);
        model = new ModelListeNotes(matric);
        setTitle("NOTES DE "+eleve.getNom()+ " " +eleve.getPrenom());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        table = new JTable(model);
        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
        
        
        
        JPanel boutons = new JPanel();
        
        boutons.add(new JButton(new AddAction()));
        boutons.add(new JButton(new RemoveAction()));
        
        getContentPane().add(boutons, BorderLayout.SOUTH);
        
        
        IClasseDao classeDao = new ClasseDaoImpl();
        List<Evaluation> lEval = evaluationDao.getEvaluations(eleve.getClasse());
        List<Eleve> lEleve = eleveDao.getEleves();
        String[] evaluations = new String[lEval.size()];
//        String[] eleves = new String[lEleve.size()];
        int cpt = 0;
        for (Evaluation eval : lEval) {
            System.out.println(eval);
            evaluations[cpt] = eval.getId_evaluation() + " " + eval.getType()
                    .getLibelle() + " " + eval.getCours().getEnseignement()
                    .getMatiere().getLibelle();
            cpt++;
        }
        
//        cpt = 0;
//        for (Eleve eleve1 : lEleve){
//            eleves[cpt] = eleve1.getNumMatric() + " " + eleve1.getNom() + " "+
//                    eleve1.getPrenom();
//            cpt++;
//        }
        
        JPanel ajout = new JPanel(new GridLayout(1, 0));
        id = new JTextField();
        valeur = new JTextField();
//        prenom = new JTextField();
//        age = new JTextField();
//        eleveChoix = new JComboBox<>(eleves);
        evaluationChoix = new JComboBox<>(evaluations);
        lid = new JLabel("ID");
        lvaleur = new JLabel("Valeur");
        leval = new JLabel("Evaluation");
//        leleve = new JLabel("Eleve");
//        lsexe = new JLabel("Sexe");
//        lclasse = new JLabel("Classe");
        
        ajout.add(lid);
        ajout.add(id);
        id.setText("0");
//        ajout.add(new JSeparator());
        ajout.add(lvaleur);
        ajout.add(valeur);
        valeur.setText("0");
//        ajout.add(new JSeparator());
//        ajout.add(leleve);
//        ajout.add(eleveChoix);
//        ajout.add(new JSeparator());
        ajout.add(leval);
        ajout.add(evaluationChoix);
//        ajout.add(new JSeparator());
//        ajout.add(lsexe);
//        ajout.add(sexeChoix);
////        ajout.add(new JSeparator());
//        ajout.add((lclasse));
//        ajout.add(classeChoix);
        getContentPane().add(ajout, BorderLayout.NORTH);
        
        pack();
    }
    
    public static void main(String[] args) {
//        new EleveNotes().setVisible(true);
    }
    
    private class AddAction extends AbstractAction {
        private AddAction() {
            super("Enregistrer");
        }
 
        @Override
        public void actionPerformed(ActionEvent e) {

            int _id = Integer.parseInt(id.getText());
//            String _nom = nom.getText();
//            String _prenom = prenom.getText();
            double _val = Integer.parseInt(valeur.getText());
//            long _eleve = Long.parseLong(eleveChoix.getItemAt(eleveChoix.getSelectedIndex()).substring(0, 5));
//            System.out.println(_eleve);
            long _eval = Long.parseLong(evaluationChoix.getItemAt(evaluationChoix.getSelectedIndex()).substring(0, 1));
//            Eleve el = eleveDao.getEleve(_eleve);
            Evaluation ev = evaluationDao.getEvaluation(_eval);
            if(_id == 0){
                model.addNote((new Note(ev, eleve, _val)));
            } else {
                model.addNote((new Note(_id, ev, eleve, _val)));
            }
            
            
            id.setText("0");
            valeur.setText("0");
//            prenom.setText("");
//            age.setText("");
        }
    }
    
    private class RemoveAction extends AbstractAction {
        private RemoveAction() {
            super("Supprimer");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int[] selection = table.getSelectedRows();
            IEleveDao eleveDao = new EleveDaoImpl();

            for(int i = selection.length - 1; i >= 0; i--){
                long mat = (long)table.getValueAt(selection[i], 0);
                model.removeNote(selection[i]);
                eleveDao.supprimer(mat);
            }
        }
    }
    
}
