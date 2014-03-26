package Catalogue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CatalogueSupp extends JFrame
{
	private JPanel fenetresupp = new JPanel();
	
	private JLabel libcombo = new JLabel("ma combo");
	private JComboBox combo = new JComboBox();
	private JButton supp = new JButton ("Supprimer");
	private JButton retour = new JButton ("Retour");
	
	public void Suppression() 
	{
		this.setTitle("Supression");
		this.setSize(300, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		fenetresupp.setBackground(Color.white);
		fenetresupp.setLayout(null);
		
		libcombo.setBounds(20, 5, 100, 30);
		combo.setBounds(100, 10, 100, 20);
		supp.setBounds(100, 50, 100, 20);
		retour.setBounds(100, 90, 100, 20);
		
		fenetresupp.add(libcombo);
		fenetresupp.add(combo);
		fenetresupp.add(supp);
		fenetresupp.add(retour);
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:8889/catalogue", user = "root", password = "root";
			Connection c = DriverManager.getConnection(url, user, password);
			String req = "SELECT formation_libelle FROM formation";
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(req);
			
			while(rs.next())
			{
				combo.addItem(rs.getString(1));
			}
			
			supp.addActionListener(new ActionListener()
  	      	{
  	    	  	@SuppressWarnings("static-access")
				public void actionPerformed(ActionEvent e)
  	    	  	{
  	    	  		try
  	    	  		{
		  	    	  	Class.forName("com.mysql.jdbc.Driver");
		  				String url = "jdbc:mysql://localhost:8889/catalogue", user = "root", password = "root";
		  				Connection c = DriverManager.getConnection(url, user, password);
	  	    	  		String req = "DELETE FROM formation WHERE formation_libelle = '" + combo.getSelectedItem().toString() + "' ";
	  	    	  		Statement s = c.createStatement();
	  	    	  		s.execute(req);
	  	    	  		
		  	    	  	dispose();
	        			
		  	    	  	ImageIcon img = new ImageIcon("images/logo.png");
	        			JOptionPane jop1;  
	        			jop1 = new JOptionPane();
	        			jop1.showMessageDialog(null, "Votre formation a bien été supprimée", "Information", JOptionPane.INFORMATION_MESSAGE,img);
	        			
	        			CatalogueLecture a = new CatalogueLecture();
	        			a.Lecture();
  	    	  		}
	  	    	  	catch (ClassNotFoundException | SQLException ee)
	  	    	  	{
	  	    	  		ee.printStackTrace();
	  	    	  	}
  	    	  		
  	    	  	} 
  	      	}
			);
		}
		
		catch (ClassNotFoundException | SQLException ee)
        {
    		ee.printStackTrace();
		}
		
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
		
		this.setContentPane(fenetresupp);
		this.setVisible(true);
	}
}
