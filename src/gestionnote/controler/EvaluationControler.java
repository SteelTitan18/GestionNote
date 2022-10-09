/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.controler;

import gestionnote.model.Evaluation;
import gestionnote.view.EvaluationView;

/**
 *
 * @author steeltitan
 */
public class EvaluationControler {
    private Evaluation model;
    private EvaluationView view;
    
    public EvaluationControler(EvaluationView view, Evaluation model){
        this.view = view;
        this.model = model;
    }
    
    public void setPoids(){
        double poids = EvaluationView.getPoids();
        this.model.setPoids(poids);
    }
    
}
