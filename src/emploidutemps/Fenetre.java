/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emploidutemps;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javafx.scene.paint.Color.color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import static javafx.scene.paint.Color.color;

/**
 *
 * @author user
 */
public class Fenetre extends JFrame{
    private int longueur = 800;
    private int largeur = 500;
    //création de la barre de menu
    private JMenuBar menuBar = new JMenuBar();
    private JButton quitter = new JButton("Quitter");
    private JButton ouvrir = new JButton("Ouvrir un emploi du temps");
    private JButton creer = new JButton("Nouveau");
    private JButton enregistrer = new JButton("Enregistrer");
    private JButton parametrage = new JButton("Paramètres");
    private JButton apropos = new JButton("A propos");       
    private ContenuInitial principal;
    private String[] tab;
    
    
    
    public Fenetre(){
        this.setTitle("EPT: Gestion des emplois du temps");
        this.setSize(longueur, largeur);
        this.setLocationRelativeTo(null);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);//affichage en plein écran      
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        
        
        //application d'une image icone sur la fenetre        
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image icon = tk.getImage("icones/logo.png");
        setIconImage(icon);
        
        //création du menu (on fait appel à la fonction menue()
        menu();
        tab = new String[3];
        
        //appel de la méthode affichage qui permet de mettre les différentes cellules
        principal = new ContenuInitial();
        principal.affichage();
        
       
        this.setContentPane(principal);
        //affichage de la fenetre
        this.setVisible(true);
    }
    
    /****************************************
     * Définition du menu de l'application
     ***************************************/
    private void menu(){  
        
        //ajout de tous les menus sur la barre de menu
        
        menuBar.add(ouvrir);
        menuBar.add(creer);
        menuBar.add(enregistrer);
        //ajout d'un listener sur le bouton nouveau
        creer.addActionListener(new NouveauListener());
        
        menuBar.add(parametrage);
        menuBar.add(apropos);
        menuBar.add(quitter);
        
        //affichage de la barre des menu
        setJMenuBar(menuBar);
        
        //les icones sur les menus
        quitter.setIcon(new ImageIcon("icones/quitter.png"));
        quitter.setBackground(new Color(255,255,255));
        ouvrir.setIcon(new ImageIcon("icones/ouvrir.png"));
        ouvrir.setBackground(Color.WHITE);
        parametrage.setIcon(new ImageIcon("icones/parametrage.png"));
        parametrage.setBackground(Color.WHITE);
        creer.setIcon(new ImageIcon("icones/creer.png"));
        creer.setBackground(Color.WHITE);
        enregistrer.setIcon(new ImageIcon("icones/enregistrer.png"));
        enregistrer.setBackground(Color.WHITE);
        apropos.setIcon(new ImageIcon("icones/apropos.png"));
        apropos.setBackground(Color.WHITE);
        
    }

    
    /*******************************************************************
     * Fonction qui permet de gérer l'ajout d'un nouvel emploi du temps
     *******************************************************************/
    public void ajouter(){
        //création de la boite de dialogue pour le choix du type d'emploi du temps
        JOptionPane type = new JOptionPane();
        ImageIcon icon = new ImageIcon("icones/decor.png");
        //String[] choixTab = {"Semestre1","Semestre2","Hebdomadaire"};
        //String choix = (String)type.showInputDialog(null,"Type d'emploi du temps","Choix du type",JOptionPane.QUESTION_MESSAGE,icon,choixTab,choixTab[0]);
        
        SemaineDialogue bd = new SemaineDialogue(null, "Ajouter un nouvel emploi du temps", true);
        String choix = bd.getTypeEmp();
        String ch = "";
        switch(choix){
            case "Semestre1" : tab[0] = "premier semestre";
                               ch = "semestre";
                               break;
            case "Semestre2" : tab[0] = "deuxième semestre";
                               ch = "semestre";
                               break;
            case "Hebdomadaire" :tab[0] = bd.getDdebut();
                                 tab[1] = bd.getDfin();
                                 if(tab[0] == null){
                                     ch = "nouveau";
                                 }else{
                                    ch = "hebdomadaire";                                   
                                 }
                                 break;
        }
        principal.removeAll();
        principal.setChoix(ch);
        principal.setTab(tab);
        principal.affichage();
    }
    
    /*******************************************************
     * class NouveauListener pour écouter le bouton nouveau
     *******************************************************/
    class NouveauListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            ajouter();
        }
    }
}
