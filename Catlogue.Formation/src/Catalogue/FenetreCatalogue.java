package Catalogue;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class FenetreCatalogue extends JFrame 
{
	
	private JButton jButtons = new JButton ("Afficher formation");
	private JButton jButtons2 = new JButton ("Ajouter une formation");
	private JPanel bouton = new JPanel();
	
	public void fenetre()
	{
		this.setTitle("Catalogue");
		this.setSize(300, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		bouton.add(jButtons);
		bouton.add(jButtons2);
		bouton.setBackground(Color.white);
		//bouton.setLayout(null);
		bouton.setBounds(75, 50, 150, 30);
		
		jButtons.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                CatalogueLecture a = new CatalogueLecture();
                a.Lecture();
                dispose();
            } 
        }
        ); 
		
		jButtons2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	CatalogueEcriture a = new CatalogueEcriture();
                a.FenetreEcriture();
                dispose();
            	
                /*CatalogueConnexion a = new CatalogueConnexion();
                a.Connexion();
                dispose();*/
            }
            
        }
        ); 
		
		this.setContentPane(bouton);
		this.setVisible(true);
	}
}
