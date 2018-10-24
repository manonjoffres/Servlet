/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.newservelet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import simplejdbc.*;
/**
 *
 * @author pedago
 */
public class Sous_Classe extends DAO{
    
    public Sous_Classe(DataSource dataSource) {
        super(dataSource);
    }
    
    public List<String> ListOfState() throws DAOException{
        List<String> result = new LinkedList<>();

		String sql ="SELECT DISTINCT STATE AS ETAT FROM CUSTOMER";
		
		try (   Connection connection = myDataSource.getConnection(); // Ouvrir une connexion
			Statement stmt = connection.createStatement(); // On crée un statement pour exécuter une requête
			ResultSet rs = stmt.executeQuery(sql) // Un ResultSet pour parcourir les enregistrements du résultat
		) {
			while (rs.next()) { 
				result.add(rs.getString("ETAT"));
			}
		} catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new DAOException(ex.getMessage());
		}

		return result;
    }
    
}
