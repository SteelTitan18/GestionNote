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
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author steeltitan
 */
@Entity
@Table(name="types")
public class Type implements Serializable{
    @Id
    private String code;
    
    @Column(name="libelle")
    private String libelle;
    
    private static List <Type> listeType = new ArrayList<>();
    
    public Type(){
        
    }
    
    public Type (String libelle, String code){
        this.libelle = libelle;
        this.code = code;
    }
    
    public static void ajouter(String libelle, String code){
        Type type = new Type (libelle, code);
        listeType.add(type);
    }
    
    public static Type getType(int numType){
        Type type0 = Type.getListeType().get(numType-1);
        return type0;
    }
    
    public static void afficherTout(){
        int cpt = 1;
        for (Type type : listeType){
            System.out.println(cpt++ + ". " +type.libelle);
        }
    }
    
    
    
    
    public static List<Type> getListeType() {
        return listeType;
    }

    public static void setListeType(List<Type> listeType) {
        Type.listeType = listeType;
    }
    
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Code : " + this.code + " => " + this.libelle;
    }
}
