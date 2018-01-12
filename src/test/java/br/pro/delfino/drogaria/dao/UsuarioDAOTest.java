package br.pro.delfino.drogaria.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Pessoa;
import br.pro.delfino.drogaria.domain.Usuario;

public class UsuarioDAOTest {
	@Test
	//@Ignore
	public void salvar(){
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(3L);
		
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setPessoa(pessoa);
		usuario.setSenhaSemCriptografia("54e8ae");
		
		SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
		usuario.setSenha(hash.toHex());
		
		usuario.setTipo('A');
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
		
		System.out.println("Usuário salvo com sucesso.");
	}
	
	@Test
	@Ignore
	public void autenticar() {
		String cpf = "011.744.341-71";
		String senha = "q1w2e3r4";
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.autenticar(cpf, senha);
		
		System.out.println(usuario);
		
	}
}	
