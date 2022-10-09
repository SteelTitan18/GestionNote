/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestionnote.dao;

import gestionnote.model.Type;
import java.util.List;

/**
 *
 * @author steeltitanrgnk
 */
public interface ITypeDao {
    public void afficherTout();
    public void afficher(Type type);
    public Type ajouter(Type type);
    public void supprimer(Type type);
    public void supprimer(String id);
    public List<Type> getTypes();
    public Type getType(String id);
}
