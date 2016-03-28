/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emploidutemps;

import java.util.Date;

/**
 *
 * @author user
 */
public class Semaine {
    private Date dateDebut;
    private Date dateFin;
    
    public Semaine(){    
        dateDebut = new Date();
        dateFin = new Date();
    }
    public Semaine(Date debut, Date fin){
        dateDebut = debut;
        dateFin = fin;
    }
    
    //recuperer la date de début
    public Date getDateDebut(){
        return dateDebut;
    }
    //modifier la date de début
    public void setDateDebut(Date debut){
        dateDebut = debut;
    }
    //recuperer la date de fin
    public Date getDateFin(){
        return dateFin;
    }
    //modifier la date de fin
    public void setDateFin(Date fin){
        dateFin = fin;
    }
}
