package com.br.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.br.conexao.Conexao;
import com.br.modelo.Telefone;
import com.br.modelo.Usuario;

public class TelefoneDAO {

	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacao;
	
	public boolean salvarTelefone(Telefone telefone, Usuario usuario) throws SQLException {
		String sql = null;
		estadoOperacao = false;
		connection = getConexao();
		
		try {
			connection.setAutoCommit(false);
			sql = "INSERT INTO telefones (id, ddd, numero, tipo, id_usuario ) VALUES(?,?,?,?,?)";
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, null);
			statement.setInt(2, telefone.getDdd());
			statement.setString(3, telefone.getNumero());
			statement.setString(4, telefone.getTipo());
			statement.setInt(5, usuario.getId());
			
			estadoOperacao = statement.executeUpdate()>0;
			
			connection.commit();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}
		
		return estadoOperacao;
	}
	
	public boolean editarTelefone(Telefone telefone) throws SQLException {
		
		String sql = null;
		estadoOperacao = false;
		connection = getConexao();
		
		try {
			connection.setAutoCommit(false);
			sql = "UPDATE telefones SET ddd = ?, numero = ?, tipo = ? WHERE id = ?";
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1, telefone.getDdd());
			statement.setString(2, telefone.getNumero());
			statement.setString(3, telefone.getTipo());
			statement.setInt(4, telefone.getId());
			
			estadoOperacao = statement.executeUpdate()>0;
			connection.commit();
			statement.close();
			connection.close();
			
			
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}
		
		return estadoOperacao;
	}
	
	public boolean deletarTelefone(int idTelefone) throws SQLException {
		
		String sql = null;
		estadoOperacao = false;
		connection = getConexao();
		
		try {
			connection.setAutoCommit(false);
			sql = "DELETE FROM telefones WHERE id = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, idTelefone);
			
			estadoOperacao = statement.executeUpdate()>0;
			connection.commit();
			statement.close();
			connection.close();
			
			
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}
		
		return estadoOperacao;
	}
	
	public List<Telefone> getTelofone(Usuario usuario) throws SQLException {
		
		ResultSet resultSet = null;
		List<Telefone> listaTelefone = new ArrayList<>();
		
		String sql = null;
		estadoOperacao = false;
		connection = getConexao();
		
		try {
			sql = "SELECT * FROM telefones WHERE id_usuario = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, usuario.getId());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				
				Telefone telefone = new Telefone();
				telefone.setId(resultSet.getInt(1));
				telefone.setDdd(resultSet.getInt(2));
				telefone.setNumero(resultSet.getString(3));
				telefone.setTipo(resultSet.getString(4));
				listaTelefone.add(telefone);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listaTelefone;
	}
	
	public Telefone getTelefone(int idTelefone) throws SQLException {
		
		ResultSet resultSet = null;
		Telefone telefone = new Telefone();
		
		String sql = null;
		estadoOperacao = false;
		connection = getConexao();
		
		try {
			sql = "SELECT * FROM telefones WHERE id = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, idTelefone);
			
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				
				
				telefone.setId(resultSet.getInt(1));
				telefone.setDdd(resultSet.getInt(2));
				telefone.setNumero(resultSet.getString(3));
				telefone.setTipo(resultSet.getString(4));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return telefone;
	}
	
	private Connection getConexao() throws SQLException {
		
		return Conexao.getConnection();
	}
}