package dao;

import model.Animal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class AnimalDAO extends DAO {	
	public AnimalDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Animal animal) {
		boolean status = false;
		try {
			String sql = "INSERT INTO animal (nome, raca, sexo, idade) "
		               + "VALUES ('" + animal.getNome() + "', "
		               + animal.getRaca() + ", " + animal.getSexo() + "," + animal.getIdade();
			PreparedStatement st = conexao.prepareStatement(sql);
		    //st.setTimestamp(1, Timestamp.valueOf(animal.getDataFabricacao()));
			//st.setDate(2, Date.valueOf(animal.getDataValidade()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Animal get(int codigo) {
		Animal animal = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM animal WHERE codigo="+codigo;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 animal = new Animal(rs.getInt("codigo"), rs.getString("nome"), rs.getString("raca"), rs.getChar("raca"), rs.getInt("Idade"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return animal;
	}
	
	
	public List<Animal> get() {
		return get("");
	}

	
	public List<Animal> getOrderByID() {
		return get("id");		
	}
	
	
	public List<Animal> getOrderByDescricao() {
		return get("descricao");		
	}
	
	
	public List<Animal> getOrderByPreco() {
		return get("preco");		
	}
	
	
	private List<Animal> get(String orderBy) {
		List<Animal> animais = new ArrayList<Animal>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM animal" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Animal p = new Animal(rs.getInt("id"), rs.getString("descricao"), (float)rs.getDouble("preco"), 
	        			                rs.getInt("quantidade"),
	        			                rs.getTimestamp("datafabricacao").toLocalDateTime(),
	        			                rs.getDate("datavalidade").toLocalDate());
	            animais.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return animais;
	}
	
	
	public boolean update(Animal animal) {
		boolean status = false;
		try {  
			String sql = "UPDATE animal SET descricao = '" + animal.getDescricao() + "', "
					   + "preco = " + animal.getPreco() + ", " 
					   + "quantidade = " + animal.getQuantidade() + ","
					   + "datafabricacao = ?, " 
					   + "datavalidade = ? WHERE id = " + animal.getID();
			PreparedStatement st = conexao.prepareStatement(sql);
		    st.setTimestamp(1, Timestamp.valueOf(animal.getDataFabricacao()));
			st.setDate(2, Date.valueOf(animal.getDataValidade()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM animal WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}