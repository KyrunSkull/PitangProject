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

import com.br.dao.TelefoneDAO;
import com.br.dao.UsuarioDAO;
import com.br.modelo.Telefone;
import com.br.modelo.Usuario;

@WebServlet(description = "administra a tabela de telefones", urlPatterns = { "/telefones" })
public class TelefoneController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public TelefoneController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String opcao = request.getParameter("opcao");	
		
		if (opcao.equals("criar")) {
			System.out.println("Você escolheu a opção de criar");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/criartelefone.jsp");
			requestDispatcher.forward(request, response);
		} else if (opcao.equals("lista")) {
			
			TelefoneDAO telefoneDAO = new TelefoneDAO();
			List<Telefone> lista = new ArrayList<>();
			try {
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				lista = telefoneDAO.getTelofone(usuarioDAO.obterUsuario(Integer.parseInt(request.getParameter("id"))));
				
				
				request.setAttribute("lista", lista);
				
				request.setAttribute("usuario_id", request.getParameter("id"));				
				request.getRequestDispatcher("/views/listatelefone.jsp").forward(request, response);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("Você escolheu a opção de editar");
		} else if (opcao.equals("editar")) {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("Editar id: " + id);
			TelefoneDAO telefoneDAO = new TelefoneDAO();
			Telefone telefone = new Telefone();
			try {
				telefone = telefoneDAO.getTelefone(id);
				System.out.println(telefone);
				request.setAttribute("telefone", telefone);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/editartelefone.jsp");
				requestDispatcher.forward(request, response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if (opcao.equals("deletarTelefone")) {
			TelefoneDAO telefoneDAO = new TelefoneDAO();
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				telefoneDAO.deletarTelefone(id);
				System.out.println("Deletado com sucesso...");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/menu.jsp");
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
		String opcao = request.getParameter("opcao");
		
		if (opcao.equals("salvar")) {
			TelefoneDAO telefoneDAO = new TelefoneDAO();
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Telefone telefone = new Telefone();
			telefone.setDdd(Integer.parseInt(request.getParameter("ddd")));
			telefone.setNumero(request.getParameter("numero"));
			telefone.setTipo(request.getParameter("tipo"));
			try {
				String idUsuario = request.getParameter("usuario_id");
				
				telefoneDAO.salvarTelefone(telefone, usuarioDAO.obterUsuario(Integer.valueOf(idUsuario)));
				System.out.println("Registrado com sucesso...");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/menu.jsp");
				requestDispatcher.forward(request, response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (opcao.equals("editar")) {
			Telefone telefone = new Telefone();
			TelefoneDAO telefoneDAO = new TelefoneDAO();
			
			telefone.setId(Integer.parseInt(request.getParameter("id")));
			telefone.setDdd(Integer.parseInt(request.getParameter("ddd")));
			telefone.setNumero(request.getParameter("numero"));
			telefone.setTipo(request.getParameter("tipo"));
			try {
				telefoneDAO.editarTelefone(telefone);
				System.out.println("Editado com sucesso...");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/menu.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
	}
}
		//doGet(request, response);