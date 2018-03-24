package com.java.service;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;

import com.java.dao.UsuarioDAO;
import com.java.modelo.Usuario;
import com.java.modeloEspecializado.AlteraSenhaUsuario;
import com.java.util.NegocioException;
import com.java.util.SenhaUtil;
import com.java.util.Session;

public class UsuarioService implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	@Inject
	private UsuarioDAO usuarioDAO;
	
	
	public Usuario retornarUsuarioPorID(String id) throws NegocioException {
		
		try {
			
			Long idUsuario = Long.parseLong(id);
			
			if (idUsuario <= 0) {
				throw new NegocioException("Usuário inválido.");
			}
			
			return usuarioDAO.retornarUsuarioPorID(idUsuario);
			
		} catch (Exception e) {
			
			throw new NegocioException("Usuário inválido.");
			
		}
		
	}
	
	public Boolean validaUsuarioPorLoginSenha(Usuario usuario) {
		
		try {
			
			if (usuario.getLogin() == null || usuario.getLogin().trim().equals("")) {
				return false;
			}
			
			if (usuario.getSenha() == null || usuario.getSenha().trim().equals("")) {
				return false;
			}
			
			Usuario consulta = usuarioDAO.retornaUsuarioPorLoginSenha(usuario.getLogin(), SenhaUtil.criptografar(usuario.getSenha()));
			
			if (consulta == null) {
				return false;
			} else {
				if(consulta.getSituacao().equals("A")) {
					Session.getSession().setAttribute("usuarioLogado", consulta);
					Session.getSession().setAttribute("nomeCompletoUsuarioLogado", consulta.getNomeCompleto());
					return true;
				} else {
					return false;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public void salvar(Usuario usuario) throws NegocioException, NoSuchAlgorithmException, UnsupportedEncodingException, ParseException, SQLException {
		
		if (this.testaSeUsuarioLogadoEhAdmin() == false) {
			throw new NegocioException("Usuário não tem permissão.");
		}
		
		String acao_log;
		if (usuario.getId() == null) {
			acao_log = "I";
		} else {
			acao_log = "A";
		}
		
		if (usuario.getLogin() == null || usuario.getLogin().trim().equals("")) {
			throw new NegocioException("Login do Usuário deve ser informado.");
		}
		
		if (usuario.getNomeCompleto() == null || usuario.getNomeCompleto().trim().equals("")) {
			throw new NegocioException("Nome Completo do Usuário deve ser informado.");
		}
		
		if (usuario.getSenha() == null || usuario.getSenha().trim().equals("")) {
			throw new NegocioException("Senha do Usuário deve ser informado.");
		}
		
		if (usuario.getSituacao() == null || usuario.getSituacao().trim().equals("")) {
			throw new NegocioException("Situação do Usuário deve ser informado.");
		}
		
		if (acao_log == "I") {
			String senhaCriptografada = SenhaUtil.criptografar(usuario.getSenha());
			usuario.setSenha(senhaCriptografada);
			usuarioDAO.incluir(usuario);
		} else {
			usuarioDAO.alterar(usuario);
		}
		
		
		
		//this.usuario_logDAO.salvar(usuario, acao_log, Session.retornaIdUsuarioLogado(), Session.retornaLoginUsuarioLogado());
		
	}
	
	public void alterarSenhaUsuarioLogado(AlteraSenhaUsuario alteraSenhaUsuario) throws NegocioException, NoSuchAlgorithmException, UnsupportedEncodingException, ParseException, SQLException {
		
		if (alteraSenhaUsuario.getLogin() == null || alteraSenhaUsuario.getLogin().trim().equals("")) {
			throw new NegocioException("Login do Usuário deve ser informado.");
		}
		
		if (alteraSenhaUsuario.getNovaSenha() == null || alteraSenhaUsuario.getNovaSenha().trim().equals("")) {
			throw new NegocioException("Senha do Usuário deve ser informado.");
		}
		
		Usuario usuario = new Usuario();
		usuario.setLogin(alteraSenhaUsuario.getLogin());
		usuario.setSenha(alteraSenhaUsuario.getSenhaAtual());
		
		if (validaUsuarioPorLoginSenha(usuario) == false)  {
			throw new NegocioException("Usuário ou Senha inválido.");
        }
		
		String senhaCriptografada = SenhaUtil.criptografar(alteraSenhaUsuario.getNovaSenha());
		
		alteraSenhaUsuario.setSenhaCriptografada(senhaCriptografada);
		
		this.usuarioDAO.alterarSenha(alteraSenhaUsuario);
		
		usuario = usuarioDAO.retornarUsuarioPorID(Session.retornaIdUsuarioLogado());
		
		//this.usuario_logDAO.salvar(usuario, "A", Session.retornaIdUsuarioLogado(), Session.retornaLoginUsuarioLogado());
		
	}
	
	public void alterarSenhaUsuario(AlteraSenhaUsuario alteraSenhaUsuario) throws NegocioException, NoSuchAlgorithmException, UnsupportedEncodingException, ParseException, SQLException {
		
		if (this.testaSeUsuarioLogadoEhAdmin() == false) {
			throw new NegocioException("Usuário não tem permissão.");
		}
		
		if (alteraSenhaUsuario.getLogin() == null || alteraSenhaUsuario.getLogin().trim().equals("")) {
			throw new NegocioException("Login do Usuário deve ser informado.");
		}
		
		if (alteraSenhaUsuario.getNovaSenha() == null || alteraSenhaUsuario.getNovaSenha().trim().equals("")) {
			throw new NegocioException("Senha do Usuário deve ser informado.");
		}
		
		Usuario usuario = new Usuario();
		usuario.setLogin(alteraSenhaUsuario.getLogin());
		usuario.setSenha(alteraSenhaUsuario.getSenhaAtual());
				
		String senhaCriptografada = SenhaUtil.criptografar(alteraSenhaUsuario.getNovaSenha());
		
		alteraSenhaUsuario.setSenhaCriptografada(senhaCriptografada);
		
		this.usuarioDAO.alterarSenha(alteraSenhaUsuario);
		
		//usuario = usuarioDAO.retornaUsuarioPorLogin(alteraSenhaUsuario.getLogin());
		
		//this.usuario_logDAO.salvar(usuario, "A", Session.retornaIdUsuarioLogado(), Session.retornaLoginUsuarioLogado());
		
	}
	
	public void excluir(Usuario usuario) throws NegocioException, ParseException, SQLException {
		
		if (this.testaSeUsuarioLogadoEhAdmin() == false) {
			throw new NegocioException("Usuário não tem permissão.");
		}
		
		if (usuario == null) {
			throw new NegocioException("Usuário deve ser selecionado.");
		} else if (usuario.getId() <= 0) {
			throw new NegocioException("Usuário deve ser selecionado.");
		}
		
		this.usuarioDAO.excluir(usuario);
		
		//this.usuario_logDAO.salvar(usuario, "E", Session.retornaIdUsuarioLogado(), Session.retornaLoginUsuarioLogado());
	}
	
	public List<Usuario> listarTodos(Long id) throws SQLException {
		return usuarioDAO.listarTodos(id);
	}
	
	public boolean testaSeUsuarioLogadoEhAdmin() throws SQLException {
		
		Usuario usuario = usuarioDAO.retornarUsuarioPorID(Session.retornaIdUsuarioLogado());
		
		if (usuario == null) {
			return false;
		} else if (usuario.getAdmin().equals("N")){
			return false;
		} else {
			return true;
		}
	}
	
}
