package com.br.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.br.dao.UsuarioDAO;
import com.br.modelo.Usuario;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet(description = "administra solicitacoes da tabela usuario", urlPatterns = { "/usuario" })
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String opcao = request.getParameter("opcao");
		if (opcao.equals("adicionar")) {
			System.out.println("Você clicou em adicionar");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/adicionar.jsp");
			requestDispatcher.forward(request, response);			
		} else if(opcao.equals("listar")) {
			UsuarioDAO usuarioDAO= new UsuarioDAO();
			List<Usuario> lista= new ArrayList<>();
			try {
				lista=usuarioDAO.obterUsuario();
				for (Usuario usuario : lista) {
					System.out.println(usuario);
				}
				
				request.setAttribute("lista", lista);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/listar.jsp");
				requestDispatcher.forward(request, response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
			System.out.println("Você clicou em listar");
		} else if (opcao.equals("editar")) {
			int id= Integer.parseInt(request.getParameter("id"));
			System.out.println("Editar id: " + id);
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario u= new Usuario();
			
			try {
				u=usuarioDAO.obterUsuario(id);
				System.out.println(u);
				request.setAttribute("usuario", u);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/editar.jsp");
				requestDispatcher.forward(request, response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (opcao.equals("remover")) {
			UsuarioDAO usuarioDAO= new UsuarioDAO();
			int id=Integer.parseInt(request.getParameter("id"));
			try {
				usuarioDAO.remover(id);
				System.out.println("Usuário removido com Sucesso!");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
			
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opcao= request.getParameter("opcao");
		
		if (opcao.equals("adicionar")) {
			UsuarioDAO usuarioDAO= new UsuarioDAO();
			Usuario usuario= new Usuario();
			usuario.setNome(request.getParameter("nome"));
			usuario.setEmail(request.getParameter("email"));
			usuario.setSenha(request.getParameter("senha"));
			
			try {
				usuarioDAO.salvar(usuario);
				System.out.println("Usuário adicionado com Sucesso!");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (opcao.equals("editar")) {
			Usuario usuario = new Usuario();
			UsuarioDAO usuarioDAO= new UsuarioDAO();
			
			usuario.setId(Integer.parseInt(request.getParameter("id")));
			usuario.setNome(request.getParameter("nome"));
			usuario.setEmail(request.getParameter("email"));
			usuario.setSenha(request.getParameter("senha"));
			try {
				usuarioDAO.editar(usuario);
				System.out.println("Usuário atualizado com Sucesso!");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		//doGet(request, response);
	}

}
