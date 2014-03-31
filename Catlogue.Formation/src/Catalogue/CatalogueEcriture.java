package Catalogue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CatalogueEcriture extends JFrame 
{
	private JPanel top = new JPanel();
	private JTextField libelle = new JTextField("");
	private JTextField description = new JTextField("");
	private JTextField prix = new JTextField("");
	private JLabel label = new JLabel("Libelle de la formation");
	private JLabel label2 = new JLabel("Description de la formation");
	private JLabel label3 = new JLabel("Prix de la formation");
	private JButton b = new JButton ("OK");
	private JButton retour = new JButton ("Retour");
	
	
	public void FenetreEcriture()
	{
		this.setTitle("Ajouter une formation");
	    this.setSize(300, 320);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    top.setBackground(Color.white);
	    top.setLayout(null);       
	    Font police = new Font("Arial", Font.BOLD, 14);
	    
	    
	    libelle.setFont(police);
	    label.setBounds(80, 20, 150, 30);
		libelle.setBounds(80, 45, 150, 30);
	    
	    description.setFont(police);
	    label2.setBounds(70, 80, 200, 30);
		description.setBounds(80, 110, 150, 30);
	    
	    prix.setFont(police);
	    label3.setBounds(90, 140, 150, 30);
		prix.setBounds(80, 175, 150, 30);
		
		b.setBounds(100, 220, 100, 30);
		
		retour.setBounds(80, 260, 150, 30);
	    
	    top.add(label);
	    top.add(libelle);
	    top.add(label2);
	    top.add(description);
	    top.add(label3);
	    top.add(prix);
	    top.add(b);
	    top.add(retour);
	    
	    b.addActionListener(new ActionListener()
        {
            @SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e)
            {
            	try
        		{
        			if(libelle.getText().equals("") || description.getText().equals("") || prix.getText().equals(""))
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
            			String req = "INSERT INTO formation (formation_libelle, formation_description, formation_prix) "
            					+ "VALUES ('" + libelle.getText() + "', '" + description.getText() + "', '" + prix.getText() + "')";
            			Statement s = c.createStatement();
            			s.executeUpdate(req);
        				
	        			dispose();
	        			
	        			ImageIcon img = new ImageIcon("images/720.jpg");
	        			JOptionPane jop1;  
	        			jop1 = new JOptionPane();
	        			jop1.showMessageDialog(null, "Votre formation a bien été ajouté", "Information", JOptionPane.INFORMATION_MESSAGE, img);
	        			
	        			CatalogueLecture a = new CatalogueLecture();
	        			a.Lecture();
        			}
        		} 
        		catch (ClassNotFoundException | SQLException ee)
        		{
        			ee.printStackTrace();
        			
        			JOptionPane jop2; 
        			jop2 = new JOptionPane();
        			jop2.showMessageDialog(null, "Votre formation n'a pas été ajouté.", "Information", JOptionPane.INFORMATION_MESSAGE);
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
	    
	    this.setContentPane(top);
	    this.setVisible(true);
	}
}
