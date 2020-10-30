package br.edu.uniacademia.hospital.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.uniacademia.hospital.dao.TipoFuncionarioDAO;
import br.edu.uniacademia.hospital.model.TipoFuncionario;

@Named
@ViewScoped
public class TipoFuncionarioBean implements Serializable {
	private static final long serialVersionUID = 1L;

	TipoFuncionario tipoFuncionario = new TipoFuncionario();
	List<TipoFuncionario> tipoFuncionarios = new ArrayList<TipoFuncionario>();

	public TipoFuncionarioBean() {
		tipoFuncionario = new TipoFuncionario();
		tipoFuncionarios = new TipoFuncionarioDAO().buscarTodas();
	}

	public void salvar(ActionEvent actionEvent) {
		new TipoFuncionarioDAO().persistir(tipoFuncionario);
		tipoFuncionarios = new TipoFuncionarioDAO().buscarTodas();
		tipoFuncionario = new TipoFuncionario();
	}

	public void remover(ActionEvent actionEvent) {
		new TipoFuncionarioDAO().remover(tipoFuncionario);
		tipoFuncionarios = new TipoFuncionarioDAO().buscarTodas();
		tipoFuncionario = new TipoFuncionario();
	}

	public TipoFuncionario getTipoFuncionario() {
		return tipoFuncionario;
	}

	public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}

	public List<TipoFuncionario> getTipoFuncionarios() {
		return tipoFuncionarios;
	}

	public void setTipoFuncionarios(List<TipoFuncionario> tipoFuncionarios) {
		this.tipoFuncionarios = tipoFuncionarios;
	}
}
