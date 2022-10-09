/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.view;

import gestionnote.dao.IPeriodeDao;
import gestionnote.dao.PeriodeDaoImpl;
import gestionnote.model.Periode;
import java.util.Scanner;

/**
 *
 * @author steeltitan
 */
public class PeriodeView {
    
    public PeriodeView(){
        
    }
    
    public int getNumPeriode(){
        IPeriodeDao periodeDao = new PeriodeDaoImpl();
        Scanner sc = new Scanner(System.in);
        periodeDao.afficherTout();
        System.out.print("\nVeuillez choisir la période de l'évaluation : ");
        int sc0 = sc.nextInt();
        return sc0;
    }
    
    public Periode choixPeriode(){
        IPeriodeDao periodeDao = new PeriodeDaoImpl();
        PeriodeView pView = new PeriodeView();
        int numPeriode = pView.getNumPeriode();
        Periode periode0 = periodeDao.getPeriode(numPeriode);
        return periode0;
    } 
    
}
