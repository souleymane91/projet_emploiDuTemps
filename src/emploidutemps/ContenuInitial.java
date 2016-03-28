/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emploidutemps;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 *
 * @author user
 */
public class ContenuInitial extends JSplitPane{
    //panel du tableau
    private JPanel content;        
    //afficchage texte
    private JPanel affiche;
    private JLabel affichage;
    private String choix;
    private String[] tab;
    
    public ContenuInitial(){
        super(JSplitPane.VERTICAL_SPLIT);
        choix ="";
        content = new JPanel();
        affiche = new JPanel();
    }

    public void setChoix(String choix) {
        this.choix = choix;
    }

    public void setTab(String[] tab) {
        this.tab = tab;
    }

    public String getChoix() {
        return choix;
    }

    public String[] getTab() {
        return tab;
    }

    public JLabel getAffichage() {
        return affichage;
    }

    public void affichage(){
       boolean verif=false;
       if(this.getChoix()!="nouveau" && this.getChoix()!=""){
           verif=true;
       }
       affiche.setBackground(new Color(8,34,70));
       content.removeAll();
       affiche.removeAll();
       switch(choix){
            case "semestre":affichage = new JLabel("Emploi du temps du "+tab[0]);
                            break;
            case "hebdomadaire":affichage = new JLabel("Emploi du temps du "+tab[0]+" au "+tab[1]);
                                break;
            default: affichage = new JLabel("Pour créer un emploi du temps clicker sur Nouveau!");
       }
       affichage.setFont(new Font("Arial", Font.BOLD, 18));
       affichage.setForeground(new Color(255,255,255));
       affiche.add(affichage);

       //On définit le layout manager
       content.setLayout(new GridLayout(7,9));

       JButton vide = new JButton();
       vide.setBackground(Color.white);
       vide.setEnabled(false);
       content.add(vide);
       for(int i=1;i<9;i++){
           JLabel label = new JLabel(new ImageIcon("icones/h"+i+".png"));
           JButton bouton = new JButton();
           bouton.add(label);
           bouton.setEnabled(false);
           content.add(bouton);
       }

       JLabel lundiLab = new JLabel(new ImageIcon("icones/lundi.png"));
       JButton lundi = new JButton();
       lundi.add(lundiLab);
       lundi.setEnabled(false);
       content.add(lundi);
       for(int i=1;i<9;i++){
           JButton bouton1 = new JButton(new ImageIcon("icones/libre.png"));
           bouton1.setBackground(Color.white);
           content.add(bouton1);
           bouton1.setEnabled(verif);

           bouton1.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){                    
                   
               }
           });
       }

       JLabel mardiLab = new JLabel(new ImageIcon("icones/mardi.png"));
       JButton mardi = new JButton();
       mardi.add(mardiLab);
       mardi.setEnabled(false);
       content.add(mardi);
       for(int i=1;i<9;i++){
           JButton bouton2 = new JButton(new ImageIcon("icones/libre.png"));
           bouton2.setBackground(Color.white);
           bouton2.setEnabled(verif);
           content.add(bouton2);
       }

       JLabel mercrediLab = new JLabel(new ImageIcon("icones/mercredi.png"));
       JButton mercredi = new JButton();
       mercredi.add(mercrediLab);
       mercredi.setEnabled(false);
       content.add(mercredi);
       for(int i=1;i<9;i++){
           JButton bouton3 = new JButton(new ImageIcon("icones/libre.png"));
           bouton3.setBackground(Color.white);
           bouton3.setEnabled(verif);
           content.add(bouton3);
       }

       JLabel jeudiLab = new JLabel(new ImageIcon("icones/jeudi.png"));
       JButton jeudi = new JButton();
       jeudi.add(jeudiLab);
       jeudi.setEnabled(false);
       content.add(jeudi);
       for(int i=1;i<9;i++){
           JButton bouton4 = new JButton(new ImageIcon("icones/libre.png"));
           bouton4.setBackground(Color.white);
           bouton4.setEnabled(verif);
           content.add(bouton4);
       }

       JLabel vendrediLab = new JLabel(new ImageIcon("icones/vendredi.png"));
       JButton vendredi = new JButton();
       vendredi.add(vendrediLab);
       vendredi.setEnabled(false);
       content.add(vendredi);
       for(int i=1;i<9;i++){
           JButton bouton5 = new JButton(new ImageIcon("icones/libre.png"));
           bouton5.setBackground(Color.white);
           bouton5.setEnabled(verif);
           content.add(bouton5);
       }

       JLabel samediLab = new JLabel(new ImageIcon("icones/samedi.png"));
       JButton samedi = new JButton();
       samedi.add(samediLab);
       samedi.setEnabled(false);
       content.add(samedi);
       for(int i=1;i<9;i++){
           JButton bouton6 = new JButton(new ImageIcon("icones/libre.png"));
           bouton6.setBackground(Color.white);
           bouton6.setEnabled(verif);
           content.add(bouton6);
       }

       //On ajoute le conteneur
       this.add(affiche);
       this.add(content);
    }
    
}
