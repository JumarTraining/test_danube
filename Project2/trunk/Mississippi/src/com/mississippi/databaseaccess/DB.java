package com.mississippi.databaseaccess;
import java.sql.*;
import java.util.*;

public class DB {

		Statement stat;
		Properties prop = new Properties();
		Driver d;
		String url = "jdbc:mysql://localhost:3306/mississippi";
		
		public DB(){
			
			prop.setProperty("user", "User");
			prop.setProperty("password", defaultPass());
		}
		
		public void setDatabase(String url){
			this.url = url;
		}
		
		public void setLogin(String user, String Pass){
		prop.setProperty("user", user);
		prop.setProperty("password", Pass);
		}
		
		public boolean createConnection(){

			Connection con;
			try {
				d = new com.mysql.jdbc.Driver();
				con = (Connection) d.connect(url,prop);
				if (con == null){
				System.out.println("connection Failed");
				return false;
				}
				stat = con.createStatement();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		
		private boolean executeUpdate(String q) {
			try {
				stat.executeUpdate(q);
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		}
		
		private ResultSet executeQuery(String q) {
			
			try {
				ResultSet rs = stat.executeQuery(q);
				return rs;
			}catch(Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
				
			} 
			
		}
		
	/**
	 * @param Table
	 * @param Columns Needs to be Same amount a values or should be null
	 * @param Values Both this and Values need to be of type ArrayList<String>
	 * @Note Do Not Pass Null ArrayList. Empty Columns List is accepted
	 */
	public boolean Insert(String Table, ArrayList<String> Columns, ArrayList<String> Values ){
	String a = "','";
	String q = "";
	q+="Insert into "+ Table;
	if(Values.size()==0){
		return false;
	}
		if (Columns.size()>0){
			q+=" ( ";
			for(int i = 0; i < Columns.size(); i++){
				q+=Columns.get(i);
				if(i<Columns.size()-1){
					q+=", ";
				}else{q+=" )";}
			}
			
		}
		q+=" Values ('";
		for(int i = 0; i < Values.size(); i++){
			q+=Values.get(i);
			if(i<Values.size()-1){
				q+="', '";
			}
			else
			{
				q+="');";
			}
		}
		
		return executeUpdate(q);
}
	
	/**
	 * @param Table
	 * @param ColumnsToUpdate Needs to be Same amount a values or should be null
	 * @param ValuesToSet Both this and Values need to be of type ArrayList<String>
	 * @Note Do Not Pass Null ArrayList. Empty Columns List is accepted
	 */
	public boolean Update(String Table, ArrayList<String> Columns, ArrayList<String> Values, ArrayList<String> ConditionColumns, ArrayList<String> ConditionValues ){
		String a = "','";
		String q = "";
		q+="UPDATE "+ Table+ " Set ";
		if(ConditionColumns.size()!=ConditionValues.size())return false;
		
		for(int i = 0; i < Columns.size(); i++){
			q+=Columns.get(i)+" = '"+Values.get(i)+"'";
			if(i < Columns.size()-1){
				q+=",";
			}
		}
		q+=" Where ";
				for(int i = 0; i < Columns.size(); i++){
					q+=ConditionColumns.get(i)+" = '"+ConditionValues.get(i)+"'";
					if(i<Columns.size()-1){ q+=", ";}
					else{q+=";";}
					
				}
			return executeUpdate(q);
	}
	
	public ResultSet select(String Table, ArrayList<String> Columns, ArrayList<String> Values){
		String q = "Select * From "+Table+" ";
		if(Columns.size()==0||Values.size()==0||Values.size()!=Columns.size()){
			q+=";";
			return executeQuery(q);
			}
		else{
			q+=" where ";
			for(int i = 0; i < Columns.size(); i++){
				q+=Columns.get(i)+" = '"+Values.get(i)+"'";
				if(i<Columns.size()-1){ q+=", ";}
				else{q+=";";}
			}
			return executeQuery(q);
		}
	}
	
	public ResultSet selectAll(String Table){
		String q = "Select * From "+Table+" ";
		return executeQuery(q);
	}
	/**
	 * @param 
	 * @Note Custom query if preset is not used
	 */
	public ResultSet selectCustom(String q){
		
		return executeQuery(q);
	}
	/**
	 * @param 
	 * @Note Custom Insert/update if preset is not used
	 */
	public boolean insertCustom(String q){
		return executeUpdate(q);
	}
	static String defaultPass(){
		return "goomoonryong";
	}
}
