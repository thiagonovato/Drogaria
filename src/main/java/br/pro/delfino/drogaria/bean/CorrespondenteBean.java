package br.pro.delfino.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.pro.delfino.drogaria.dao.CorrespondenteDAO;
import br.pro.delfino.drogaria.dao.PessoaDAO;
import br.pro.delfino.drogaria.domain.Correspondente;
import br.pro.delfino.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CorrespondenteBean implements Serializable {

	private Correspondente correspondente;

	private List<Correspondente> correspondentes;
	private List<Pessoa> pessoas;

	public Correspondente getCorrespondente() {
		return correspondente;
	}

	public void setCorrespondente(Correspondente correspondente) {
		this.correspondente = correspondente;
	}

	public List<Correspondente> getCorrespondentes() {
		return correspondentes;
	}

	public void setCorrespondentes(List<Correspondente> correspondentes) {
		this.correspondentes = correspondentes;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	@PostConstruct
	public void listar() {
		try {
			CorrespondenteDAO correspondenteDAO = new CorrespondenteDAO();
			correspondentes = correspondenteDAO.listar("dataCadastro");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os correspondentes");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			correspondente = new Correspondente();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo correspondente");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			CorrespondenteDAO correspondenteDAO = new CorrespondenteDAO();
			correspondenteDAO.merge(correspondente);

			correspondente = new Correspondente();

			correspondentes = correspondenteDAO.listar("dataCadastro");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");

			Messages.addGlobalInfo("Correspondente salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o correspondente");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			correspondente = (Correspondente) evento.getComponent().getAttributes().get("correspondenteSelecionado");

			CorrespondenteDAO correspondenteDAO = new CorrespondenteDAO();
			correspondenteDAO.excluir(correspondente);
			correspondentes = correspondenteDAO.listar();

			Messages.addGlobalInfo("Correspondente removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o correspondente");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			correspondente = (Correspondente) evento.getComponent().getAttributes().get("correspondenteSelecionado");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um correspondente");
			erro.printStackTrace();
		}
	}
}
