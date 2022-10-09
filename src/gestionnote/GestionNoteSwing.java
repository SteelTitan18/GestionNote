/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote;

import gestionnote.gui.EleveListeBull;
import gestionnote.gui.EleveListe;
import gestionnote.gui.EnseignantListe;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author steeltitan
 */
public class GestionNoteSwing extends JFrame implements ActionListener{
    JMenuBar menu = new JMenuBar(); 
    JMenu mEleve = new JMenu("Elèves");
    JMenuItem listeEl = new JMenuItem("Liste des élèves"); 
    JMenuItem notes = new JMenuItem("Notes");
    JMenuItem bulletin = new JMenuItem("Bulletin");
    JMenu mEnseignant = new JMenu("Enseignants");
    JMenuItem listeEns = new JMenuItem("Liste des enseignants");
    JMenuItem coursEns = new JMenuItem("Assigner un cours");
    JMenu mCours = new JMenu("Cours");
    JMenuItem cours = new JMenuItem("Cours");
    JMenu mEnseignements = new JMenu("Enseignements");
    JMenuItem enseignements = new JMenuItem("Enseignements");
    JMenu mClasses = new JMenu("Classes");
    JMenuItem classe = new JMenuItem("Classes");
    JMenu mMatiere = new JMenu("Matières");
    JMenuItem matiere = new JMenuItem("Matières");
    JMenu mPersonne = new JMenu("Personnes");
    JMenuItem personne = new JMenuItem("Personnes");
    
    public GestionNoteSwing() throws UnsupportedLookAndFeelException{
        setTitle("NumSchool");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        
        this.setJMenuBar(createMenu());
    }
    
    private JMenuBar createMenu(){
        listeEl.addActionListener(this);
        
        
        bulletin.addActionListener(this);
        mEleve.add(listeEl);
        mEleve.add(bulletin);
        menu.add(mEleve);
        
        listeEns.addActionListener(this);
        
        coursEns.addActionListener(this);
        mEnseignant.add(listeEns);
        mEnseignant.add(coursEns);
        menu.add(mEnseignant);
        
        mCours.add(cours);
        cours.addActionListener(this);
        menu.add(mCours);
        
        mEnseignements.add(enseignements);
        mEnseignements.addActionListener(this);
        menu.add(mEnseignements);
        
        mClasses.add(classe);
        classe.addActionListener(this);
        menu.add(mClasses);
        
        mMatiere.add(matiere);
        matiere.addActionListener(this);
        menu.add(mMatiere);
        
        mPersonne.add(personne);
        personne.addActionListener(this);
        menu.add(mPersonne);
        
        return menu;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == listeEl){
            EleveListe liste = new EleveListe();
            liste.setVisible(true);
        }
        else if (ae.getSource() == bulletin){
            EleveListeBull liste = new EleveListeBull();
            liste.setVisible(true);
            liste.setLocationRelativeTo(null);
        }
        else if (ae.getSource() == listeEns){
            EnseignantListe liste = new EnseignantListe();
            liste.setVisible(true);
            liste.setLocationRelativeTo(null);
        }
        else if (ae.getSource() == coursEns){
            
        }
        else if (ae.getSource() == cours){
            
        }
        else if (ae.getSource() == enseignements){
            
        }
        else if (ae.getSource() == classe){
            
        }
        else if (ae.getSource() == matiere){
            
        }
        else if (ae.getSource() == personne){
            
        }
    } 
    
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        GestionNoteSwing window = new GestionNoteSwing();
        window.setVisible(true);
    }

    
    
}
