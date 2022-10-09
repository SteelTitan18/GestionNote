/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionnote.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author steeltitan
 */
@Entity
@Table(name="periodes")
public class Periode implements Serializable{
    private static List<Periode> listePeriod = new ArrayList<>();
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long code;
    
    @Column(name="libelle", length=20)
    private String libelle;
    
    public Periode(){
        
    }
    
    public Periode (int code,String libelle){
        this.code = code;
        this.libelle = libelle;
    }
    
    
    public static List<Periode> getListePeriod() {
        return listePeriod;
    }

    public static void setListePeriod(List<Periode> listePeriod) {
        Periode.listePeriod = listePeriod;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    
    
    @Override
    public String toString(){
        return this.code + "e " + this.libelle;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + (int) (this.code ^ (this.code >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Periode other = (Periode) obj;
        return this.code == other.code;
    }
    
}
