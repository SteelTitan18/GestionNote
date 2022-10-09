/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.gui;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author steeltitan
 */
public class mainFenetre extends JFrame{
    
    public mainFenetre(){
        super("NumSchool");
        build();
    }
    
    private void build(){
        setTitle("NumSchool");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(buildContentPane());
        
//        JMenuBar menuBar = new JMenuBar();
//        JMenu menu1 = new JMenu("Menu");
//        JMenuItem eleve = new JMenuItem()
    }
    
    private JPanel buildContentPane(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout( ));
//        JLabel label = new JLabel("Bienvenue dans ma modeste application\n");
//        panel.add(label);
        
        JButton buton = new JButton("Elèves");
        JButton buton1 = new JButton("Enseignants"); 
        JButton buton2 = new JButton("Enseignements"); 
        JButton buton3 = new JButton("Classes"); 
        JButton buton4 = new JButton("Matières"); 
        JButton buton5 = new JButton("Personnel"); 
        panel.add(buton);
        panel.add(buton1);
        panel.add(buton2);
        panel.add(buton3);
        panel.add(buton4);
        panel.add(buton5);
        return panel;
    }
    
}
