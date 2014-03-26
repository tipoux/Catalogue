package Catalogue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class CatalogueLecture extends JFrame
{	
	private JPanel panel = new JPanel();

	private JButton ajout = new JButton ("Ajouter une formation");
	private JButton supp = new JButton ("Supprimer une formation");
	
	public void Lecture()
    {	
		try
    	{
    		Class.forName("com.mysql.jdbc.Driver");
    		String url = "jdbc:mysql://localhost:8889/catalogue", user = "root", password = "root";
    		Connection c = DriverManager.getConnection(url, user, password);
    		String req = "select * from formation";
    		Statement s = c.createStatement();
    		ResultSet rs = s.executeQuery(req);
    		
    		String col[] = { "Titre", "Description", "Prix" };
    	      String cont[][] = new String[10][3];
    	      int i = 0;
    	      while (rs.next()) 
    	      {
    	        String libelle = rs.getString("formation_libelle");
    	        String formation_description = rs.getString("formation_description");
    	        String formation_prix = rs.getString("formation_prix");
    	        cont[i][0] = libelle;
    	        cont[i][1] = formation_description;
    	        cont[i][2] = formation_prix;
    	        i++;
    	      }
    		
    	      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	      this.setTitle("Formations");
    	      this.setLocation(400, 200);
    	      
    		  DefaultTableModel model = new DefaultTableModel(cont, col);
    	      JTable table = new JTable(model);
    	      table.setShowGrid(true);
    	      table.setShowVerticalLines(true);
    	      getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
    	      getContentPane().add(panel, BorderLayout.SOUTH);

    	      panel.add(ajout);
    	      panel.add(supp);
    	      
    	      ajout.addActionListener(new ActionListener()
    	      {
    	    	  public void actionPerformed(ActionEvent e)
    	          {
    	    		  CatalogueEcriture aa = new CatalogueEcriture();
    	              aa.FenetreEcriture();
    	              dispose();
    	           } 
    	       }
    	       );
    	      
    	      supp.addActionListener(new ActionListener()
    	      {
    	    	  public void actionPerformed(ActionEvent e)
    	          {
    	    		  CatalogueSupp aa = new CatalogueSupp();
    	              aa.Suppression();
    	              dispose();
    	           } 
    	       }
    	       );
    	      
    	      this.pack();
    	      this.setVisible(true);
    	} 
		
    	catch (ClassNotFoundException | SQLException e)
    	{
    		e.printStackTrace();
    	}
    }
}
