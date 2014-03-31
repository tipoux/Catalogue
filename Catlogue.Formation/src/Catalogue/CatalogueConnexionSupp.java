package Catalogue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CatalogueConnexionSupp extends JFrame
{
	private JPanel fenetre = new JPanel();
	
	private JTextField pseudo = new JTextField("");
	private JTextField mdp = new JTextField("");
	private JLabel labelpseudo = new JLabel("Pseudo");
	private JLabel labelmdp = new JLabel("Mot de passe");
	private JLabel permission = new JLabel("Veuillez vous connecter :");
	private JButton connexion = new JButton("Connexion");
	private JButton retour = new JButton("retour");
	
	public void ConnexionSupp()
	{
		this.setTitle("Connexion");
		this.setSize(300, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		fenetre.setLayout(null);
		
		permission.setBounds(65, 5, 300, 30);
		labelpseudo.setBounds(20, 40, 150, 30);
		pseudo.setBounds(120, 40, 150, 30);
		labelmdp.setBounds(20, 80, 150, 30);
		mdp.setBounds(120, 80, 150, 30);
		connexion.setBounds(70, 120, 150, 30);
		retour.setBounds(70, 160, 150, 30);
		
		fenetre.add(permission);
		fenetre.add(labelpseudo);
		fenetre.add(pseudo);
		fenetre.add(labelmdp);
		fenetre.add(mdp);
		fenetre.add(connexion);
		fenetre.add(retour);
		
		connexion.addActionListener(new ActionListener()
        {
            @SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e)
            {
            	try
        		{
        			if(pseudo.getText().equals("") || mdp.getText().equals(""))
        			{
        				JOptionPane jop2; 
            			jop2 = new JOptionPane();
            			jop2.showMessageDialog(null, "Veuillez saisir tous les champs.", "Information", JOptionPane.INFORMATION_MESSAGE);
        			}
        			
        			else
        			{
        				Class.forName("com.mysql.jdbc.Driver");
            			String url = "jdbc:mysql://localhost:8889/catalogue", user = "root", password = "root";
            			Connection c = DriverManager.getConnection(url, user, password);
            			Statement s = c.createStatement();
            			
            			String req = "SELECT * FROM user WHERE user_pseudo = '" + pseudo.getText() + "' AND user_mdp = '" + mdp.getText() + "' ";
            			ResultSet rs = s.executeQuery(req);
            			rs.last();
            			
            			int nbligne = rs.getRow();
            			
            			rs.beforeFirst();

            			if(nbligne == 1)
            			{
	            			dispose();
		        			
		        			CatalogueSupp a = new CatalogueSupp();
		                    a.Suppression();
            			}
            			
            			else 
            			{
            				JOptionPane jop1;  
    	        			jop1 = new JOptionPane();
    	        			jop1.showMessageDialog(null, "Pseudo et/ou Mot de passe incorrect.", "Information", JOptionPane.INFORMATION_MESSAGE);
            			}
        			}
        			
        			
        		}
            	catch (ClassNotFoundException | SQLException ee)
                {
            		ee.printStackTrace();
                			
                	JOptionPane jop2; 
                	jop2 = new JOptionPane();
                	jop2.showMessageDialog(null, "La connexion a échoué.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            } 
        }
		);
		
		retour.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                
                CatalogueLecture a = new CatalogueLecture();
                a.Lecture();
            }
            
        }
        );
		
		this.setContentPane(fenetre);
		this.setVisible(true);
	}
}
