/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emploidutemps;

import com.toedter.calendar.JCalendar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author user
 */
public class SemaineDialogue extends JDialog{
    private Semaine semaine;
    private JLabel debutLabel;
    private JLabel finLabel;
    private JTextField dateDebut;
    private JButton btn1;
    private JButton btn2;
    private JPanel debutPanel;
    private JPanel finPanel;
    private JTextField dateFin;
    private JButton valider;
    private JButton annuler;
    private JComboBox type;
    private JLabel typeLabel;
    private Container contenu;
    private JCalendar calendrier1;
    private JCalendar calendrier2;
    private boolean etatCalendrier1 = false;
    private boolean etatCalendrier2 = false;
    private String ddebut;
    private String dfin;
    private JLabel classeLabel;
    private JTextField classe;
    private String typeEmp;
    private final int largeur = 530;
    private final int hauteur = 270;

    public String getTypeEmp() {
        return typeEmp;
    }

    public String getDdebut() {
        return ddebut;
    }

    public void setDdebut(String ddebut) {
        this.ddebut = ddebut;
    }

    public String getDfin() {
        return dfin;
    }

    public void setDfin(String dfin) {
        this.dfin = dfin;
    }
    
    public SemaineDialogue(JFrame parent, String title, boolean modal){
        super(parent, title, modal);
        this.setSize(largeur, hauteur);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 
        
        //recupération du content par défaut
        contenu = this.getContentPane();
        
        calendrier1 = new JCalendar();
        calendrier2 = new JCalendar();
        
        ajouterContenu();
        afficheSemaineDialogue();
        semaine = new Semaine();
        
    }
    public void afficheSemaineDialogue(){
        this.setVisible(true);
    }     
    public void ajouterContenu(){
        
        JPanel panel = new JPanel();
        
        JPanel panelType = new JPanel();
        typeLabel = new JLabel("Type d'emploi du temps");
        type = new JComboBox(new String[]{"Semestre1","Semestre2","Hebdomadaire"});
        typeEmp = "Semestre1";
        
        panelType.add(typeLabel);
        panelType.add(type);
        
        debutLabel = new JLabel();
        finLabel = new JLabel();
        //le champ date de début
        dateDebut = new JTextField();
        dateDebut.setMargin(new Insets(2,2,2,2));
        dateDebut.setColumns(25);
        btn1 = new JButton(new ImageIcon("icones/app.png"));
        btn1.setBorder(null);
        btn1.setMargin(null);
        //le champ date de fin
        dateFin = new JTextField();
        dateFin.setMargin(new Insets(2,2,2,2));
        dateFin.setColumns(25);
        btn2 = new JButton(new ImageIcon("icones/app.png"));
        btn2.setBorder(null);
        btn2.setMargin(null);
        
        valider = new JButton("Valider");
        annuler = new JButton("Annuler");
        debutLabel.setText("Date de début");
        finLabel.setText("Date de fin");
        
        classeLabel = new JLabel("Classe");
        classe = new JTextField();
        classe.setMargin(new Insets(2,2,2,2));
        classe.setColumns(25);
        
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        //ajout automatique d'espacement entre les composants
        layout.setAutoCreateGaps(true);
        //ajout automatique d'espacement entre les composant qui
        //touche les contours du conteneur
        layout.setAutoCreateContainerGaps(true);
        //création de groupe sequentiel pour l'axe horizontal
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        //le groupe sequentiel contient deux groupes paralelles
        //un groupe paralelle contient le label et l'autre le textField
        hGroup.addGroup(layout.createParallelGroup().
                addComponent(classeLabel).addComponent(debutLabel).addComponent(finLabel));
        
        hGroup.addGroup(layout.createParallelGroup().
                addComponent(classe).addComponent(dateDebut).addComponent(calendrier1).addComponent(dateFin).addComponent(calendrier2));
        
        hGroup.addGroup(layout.createParallelGroup().
                addComponent(btn1).addComponent(btn2));
        
        layout.setHorizontalGroup(hGroup);
        
        //création de groupe sequentiel pour l'axe vertical
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
                addComponent(classeLabel).addComponent(classe));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
                addComponent(debutLabel).addComponent(dateDebut).addComponent(btn1));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
                addComponent(calendrier1));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
                addComponent(finLabel).addComponent(dateFin).addComponent(btn2));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
                addComponent(calendrier2));
        layout.setVerticalGroup(vGroup);
        
        //initialement les calendriers sont cachés
        calendrier1.setVisible(etatCalendrier1);
        calendrier2.setVisible(etatCalendrier1);
        
        //initialisation des champs date
        String j = ""+calendrier2.getDayChooser().getDay();
        int m = calendrier2.getMonthChooser().getMonth()+1;
        String mo = ""+m;
        String a = ""+calendrier2.getYearChooser().getYear();
        String ch = j+"/"+mo+"/"+a;
        dateDebut.setText(ch);
        dateFin.setText(ch);
                
        //listener sur les calendriers
        calendrier1.getDayChooser().addPropertyChangeListener(new PropertyChangeListener(){
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int jour = calendrier1.getDayChooser().getDay();
                int mois = calendrier1.getMonthChooser().getMonth() + 1;
                int annee = calendrier1.getYearChooser().getYear();
                
                dateDebut.setText(jour+"/"+mois+"/"+annee);
                calendrier1.setVisible(false);
                etatCalendrier1 = false;
                setSize(largeur,hauteur);
            }
            
        });
        
        calendrier2.getDayChooser().addPropertyChangeListener(new PropertyChangeListener(){
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int jour = calendrier2.getDayChooser().getDay();
                int mois = calendrier2.getMonthChooser().getMonth() + 1;
                int annee = calendrier2.getYearChooser().getYear();
                
                dateFin.setText(jour+"/"+mois+"/"+annee);
                calendrier2.setVisible(false);
                etatCalendrier2 = false;
                setSize(largeur,hauteur);
            }
            
        });
       
        //calendrier1.addDateListener(new DateListener());
        //listener sur le bouton calendrier1
        btn1.addActionListener(new semaineListener());
        //listener sur le bouton calendrier2
        btn2.addActionListener(new semaineListener());
        
        //listener sur le bouton annuler
        annuler.addActionListener(new semaineListener());
        //listener sur le bouton valider
        valider.addActionListener(new semaineListener());
        
       // contenu.setLayout(new FlowLayout());
        JPanel panIcon = new JPanel();
        panIcon.add(new JLabel(new ImageIcon("icones/decor.png")));
        
        //panel vide pour l'espacement
        JPanel vide1 = new JPanel();
        vide1.add(new JLabel(" "));
        vide1.setBackground(new Color(8,34,70));
        vide1.setSize(new Dimension(10,400));
        
        JPanel vide2 = new JPanel();
        vide2.add(new JLabel(" "));
        vide2.setBackground(new Color(8,34,70));
        vide2.setSize(new Dimension(10,400));
        
        JPanel bas = new JPanel();
        bas.setLayout(new BorderLayout());
        
        JPanel bouton = new JPanel();//on crée un panel à part pour les boutons
        bouton.add(annuler);//ajout du bouton annuler
        bouton.add(valider);//ajout du bouton valider
        
        bas.add(bouton,BorderLayout.NORTH);
        bas.add(vide2,BorderLayout.SOUTH);
        
        //JCalendar c1 = new JCalendar();
        JPanel paneau = new JPanel();
        JPanel panelSemestre = new JPanel();
        panelSemestre.add(classeLabel);
        panelSemestre.add(classe);
        
        paneau.setLayout(new BorderLayout());
        paneau.add(panelType,BorderLayout.NORTH);
        paneau.add(panelSemestre,BorderLayout.SOUTH);
        type.addActionListener(new ActionListener(){            
            @Override
            public void actionPerformed(ActionEvent e) {
                typeEmp = type.getSelectedItem().toString();
                if(typeEmp == "Hebdomadaire"){
                    paneau.removeAll();
                    paneau.add(panelType,BorderLayout.NORTH);
                    paneau.add(panel,BorderLayout.SOUTH);
                    paneau.validate();
                }else{
                    if((typeEmp == "Semestre1") || (typeEmp == "Semestre2")){
                        paneau.removeAll();
                        paneau.add(panelType,BorderLayout.NORTH);
                        paneau.add(panelSemestre,BorderLayout.SOUTH);
                        paneau.validate();
                    }
                }               
            }            
        });
        //on ajoute le content bouton sur le content de la boite de dialogue
        contenu.add(vide1,BorderLayout.NORTH);
        contenu.add(panIcon,BorderLayout.WEST);
        contenu.add(paneau,BorderLayout.CENTER);
        contenu.add(bas,BorderLayout.SOUTH);
        //contenu.add(c1,BorderLayout.EAST);
        
    }
    
    class semaineListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            //traitement de l'évènement click sur le bouton annuler
            if(e.getSource().equals(annuler)){
                setVisible(false);//fermeture de la boite de dialogue
            }
            //traitement de l'évènement click sur le bouton valider
            if(e.getSource().equals(valider)){
                //on recupère les dates de début et fin
                ddebut = dateDebut.getText();
                dfin = dateFin.getText();
                setVisible(false);
            }
            
            //traitement de l'évènement click sur le bouton calendrier1
            if(e.getSource().equals(btn1)){
                if(etatCalendrier2 == true){
                    etatCalendrier2 = false;
                    calendrier2.setVisible(etatCalendrier2);
                }
                if(etatCalendrier1 == false){
                    setSize(largeur,hauteur+170);
                }else{
                    setSize(largeur,hauteur);
                }
                calendrier1.setVisible(!etatCalendrier1);
                etatCalendrier1 = !etatCalendrier1;
            }
        
            //traitement de l'évènement click sur le bouton calendrier2
            if(e.getSource().equals(btn2)){
                if(etatCalendrier1 == true){
                    etatCalendrier1 = false;
                    calendrier1.setVisible(etatCalendrier1);
                }
                if(etatCalendrier2 == false){
                    setSize(largeur,hauteur+170);
                }else{
                    setSize(largeur,hauteur);
                }
                calendrier2.setVisible(!etatCalendrier2);
                etatCalendrier2 = !etatCalendrier2;
            }
            
        }
    }

}
