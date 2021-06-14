package com.br.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.br.conexao.Conexao;
import com.br.modelo.Usuario;


public class UsuarioDAO {
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacao;
	
	//salvar
	public boolean salvar(Usuario usuario) throws SQLException {
		String sql=null;
		estadoOperacao=false;
		connection=obterConexao();
		
		try {
			connection.setAutoCommit(false);
			sql="INSERT INTO usuario (id, nome, email, senha) VALUES(?,?,?,?)";
			statement=connection.prepareStatement(sql);
			
			statement.setString(1, null);
			statement.setString(2, usuario.getNome());
			statement.setString(3, usuario.getEmail());
			statement.setString(4, usuario.getSenha());
					
			estadoOperacao=statement.executeUpdate()>0;
			
			connection.commit();
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}
		
		return estadoOperacao;
	}
	
	//editar usuario
	public boolean editar(Usuario usuario) throws SQLException {
		String sql=null;
		estadoOperacao=false;
		connection=obterConexao();
		try {
			connection.setAutoCommit(false);
			sql="UPDATE usuario SET nome=?, email=?, senha=? WHERE id=?";
			statement=connection.prepareStatement(sql);
			
			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getEmail());
			statement.setString(3, usuario.getSenha());
			statement.setInt(4, usuario.getId());
			
			estadoOperacao=statement.executeUpdate()>0;
			connection.commit();
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}
		
		return estadoOperacao;
	}
	
	//remover usuario
	public boolean remover(int id) throws SQLException {
		String sql=null;
		estadoOperacao=false;
		connection=obterConexao();
		try {
			connection.setAutoCommit(false);
			sql="DELETE FROM usuario WHERE id=?";
			statement=connection.prepareStatement(sql);
			statement.setInt(1, id);
			
			estadoOperacao=statement.executeUpdate()>0;
			connection.commit();
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}
		
		return estadoOperacao;
	}
	
	//obter lista de usuario
	public List<Usuario> obterUsuario() throws SQLException {
		ResultSet resultSet=null;
		List<Usuario> listausuario= new ArrayList<>();
		
		String sql=null;
		estadoOperacao=false;
		connection=obterConexao();
		
		try {
			sql="SELECT * FROM usuario";
			statement=connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {				
				Usuario u=new Usuario();
				u.setId(resultSet.getInt(1));
				u.setNome(resultSet.getString(2));
				u.setEmail(resultSet.getString(3));
				u.setSenha(resultSet.getString(4));
				listausuario.add(u);
			}
			
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		
		return listausuario;
	}
	
	//obter usuario unico
	public Usuario obterUsuario(int id) throws SQLException {
		ResultSet resultSet=null;
		Usuario u=new Usuario();
		
		String sql=null;
		estadoOperacao=false;
		connection = obterConexao();
		
		try {
			sql="SELECT * FROM usuario WHERE id = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {				
				
				u.setId(resultSet.getInt(1));
				u.setNome(resultSet.getString(2));
				u.setEmail(resultSet.getString(3));
				u.setSenha(resultSet.getString(4));
			}
			
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		
		return u;
	}
	
	//obter conexao pool
	private Connection obterConexao() throws SQLException {
		return Conexao.getConnection();
			
	}
	
}
