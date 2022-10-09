/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.gui;

import gestionnote.*;
import gestionnote.dao.ClasseDaoImpl;
import gestionnote.dao.EleveDaoImpl;
import gestionnote.dao.EvaluationDaoImpl;
import gestionnote.dao.IClasseDao;
import gestionnote.dao.IEleveDao;
import gestionnote.dao.IEvaluationDao;
import gestionnote.dao.PeriodeDaoImpl;
import gestionnote.model.Classe;
import gestionnote.model.Eleve;
import gestionnote.model.Evaluation;
import gestionnote.model.Note;
import gestionnote.model.Periode;
import gestionnote.view.EleveView;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 *
 * @author steeltitanrbrn
 */
public class EleveBull extends JFrame{
    private ModelListeNotes model;
    private JTable table;
    private int matri;
    private JTextField id, valeur;
    private JComboBox<String> evaluationChoix;
    private JTextPane bulletin;
    private IClasseDao classeDao = new ClasseDaoImpl();
    private IEleveDao eleveDao = new EleveDaoImpl();
    private IEvaluationDao evaluationDao = new EvaluationDaoImpl();
    private Eleve eleve = new Eleve();
    private Periode periode = new Periode();
    
    
    public EleveBull(long matric, long idP) throws IOException{
        super();
        eleve = eleveDao.getEleve(matric);
        periode = new PeriodeDaoImpl().getPeriode(idP);
        model = new ModelListeNotes(matric);
        setTitle("BULLETIN DU " + periode.toString() +" DE "+ eleve.getNom()+ " " +eleve.getPrenom());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        table = new JTable(model);
        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
        
        
        
        JPanel boutons = new JPanel();
        
        boutons.add(new JButton(new SaveAction()));
        
        getContentPane().add(boutons, BorderLayout.SOUTH);
        
        
//        IClasseDao classeDao = new ClasseDaoImpl();
//        List<Evaluation> lEval = evaluationDao.getEvaluations(eleve.getClasse());
//        List<Eleve> lEleve = eleveDao.getEleves();
//        String[] evaluations = new String[lEval.size()];
////        String[] eleves = new String[lEleve.size()];
//        int cpt = 0;
//        for (Evaluation eval : lEval) {
//            System.out.println(eval);
//            evaluations[cpt] = eval.getId_evaluation() + " " + eval.getType()
//                    .getLibelle() + " " + eval.getCours().getEnseignement()
//                    .getMatiere().getLibelle();
//            cpt++;
//        }
        
//        cpt = 0;
//        for (Eleve eleve1 : lEleve){
//            eleves[cpt] = eleve1.getNumMatric() + " " + eleve1.getNom() + " "+
//                    eleve1.getPrenom();
//            cpt++;
//        }
        
        JPanel ajout = new JPanel(new GridLayout(1, 0));
//        id = new JTextField();
//        valeur = new JTextField();
////        prenom = new JTextField();
////        age = new JTextField();
////        eleveChoix = new JComboBox<>(eleves);
//        evaluationChoix = new JComboBox<>(evaluations);
        bulletin = new JTextPane();
        bulletin.setText(new EleveView().afficherBulletin(eleve, periode));
//        System.out.println(new EleveView().afficherBulletin(eleve, periode));
//        leval = new JLabel("Evaluation");
//        leleve = new JLabel("Eleve");
//        lsexe = new JLabel("Sexe");
//        lclasse = new JLabel("Classe");
        
        ajout.add(bulletin);
//        ajout.add(matric);
//        ajout.add(new JSeparator());
//        ajout.add(valeur);
//        ajout.add(new JSeparator());
//        ajout.add(leleve);
//        ajout.add(eleveChoix);
//        ajout.add(new JSeparator());
//        ajout.add(leval);
//        ajout.add(evaluationChoix);
//        ajout.add(new JSeparator());
//        ajout.add(lsexe);
//        ajout.add(sexeChoix);
////        ajout.add(new JSeparator());
//        ajout.add((lclasse));
//        ajout.add(classeChoix);
        getContentPane().add(ajout, BorderLayout.CENTER);
        File fBull = new File("/home/steeltitanrbrn/Bureau/arena_782/PROJECTS_782/"
                + "/java/CIC/GestionNote copie/BULLETIN DU " + periode.toString()
                +" DE "+ eleve.getNom()+ " " +eleve.getPrenom()+".txt");
        fBull.createNewFile();
        if (fBull.exists()){
            System.out.println(new EleveView().afficherBulletin(eleve, periode));
            PrintWriter writer = new PrintWriter(fBull);
            writer.write(new EleveView().afficherBulletin(eleve, periode));
            writer.close();
        }
        
        pack();
    }
    
    public static void main(String[] args) {
//        new EleveNotes().setVisible(true);
        
    }
    
    private class SaveAction extends AbstractAction {
        private SaveAction() {
            super("Enregistrer");
        }
 
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    
}
