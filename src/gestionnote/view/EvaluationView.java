/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.view;

import gestionnote.dao.EvaluationDaoImpl;
import gestionnote.dao.IEvaluationDao;
import gestionnote.model.Classe;
import gestionnote.model.Enseignement;
import gestionnote.model.Evaluation;
import gestionnote.model.Periode;
import gestionnote.model.Type;
import java.util.Scanner;

/**
 *
 * @author steeltitan
 */
public class EvaluationView {
    private IEvaluationDao evaluationDao = new EvaluationDaoImpl();
    
    public EvaluationView(){
        
    }
    
//    public long getNumEval(Periode periode0, Enseignement enseignement0, Type type0, Classe classe0){
//        int id0 = evaluationDao.getEvaluation(enseignement0, type0, classe0, periode0).getId_evaluation();
//        return id0;    
//    }
    
//    public static Evaluation getEval(int id0){
//        Evaluation evaluation0 = Evaluation.getListeEval().get(id0);
//        return evaluation0;
//    }
    
    public static double getPoids(){
        Scanner sc = new Scanner (System.in);
        System.out.print("\nVeuillez entrez le poids de l'évaluation : ");
        double sc5 = sc.nextDouble();
        return sc5;
    }
}
