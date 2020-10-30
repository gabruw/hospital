package br.edu.uniacademia.hospital.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.uniacademia.hospital.dao.EnderecosDao;
import br.edu.uniacademia.hospital.dao.FuncionariosDao;
import br.edu.uniacademia.hospital.dao.TipoFuncionarioDAO;
import br.edu.uniacademia.hospital.model.Funcionarios;

@Named
@ViewScoped
public class FuncionariosBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	Funcionarios funcionarios = new Funcionarios();
	List<Funcionarios> funcionariosList = new ArrayList<Funcionarios>();

	String idEndereco;
	Long idFuncionario;
	String tipoFuncionario;

	public FuncionariosBean() {
		idEndereco = null;
		idFuncionario = null;
		tipoFuncionario = null;
		funcionarios = new Funcionarios();
		idFuncionario = funcionarios.getIdFuncionario();
		funcionariosList = new FuncionariosDao().buscarTodas();
	}

	public void salvar(ActionEvent actionEvent) {
		funcionarios.setEnderecosidEnderecos(new EnderecosDao().buscarPorId(idEndereco));
		funcionarios.setTipoFuncionarioidtipoFuncionario(new TipoFuncionarioDAO().buscarPorId(tipoFuncionario));

		new FuncionariosDao().persistir(funcionarios);

		funcionariosList = new FuncionariosDao().buscarTodas();
		funcionarios = new FuncionariosDao().buscar(idEndereco);
	}

	public void remover(ActionEvent actionEvent) {
		new FuncionariosDao().remover(idFuncionario);
		funcionariosList = new FuncionariosDao().buscarTodas();
		funcionarios = new Funcionarios();
	}

	public Funcionarios getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(Funcionarios funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Funcionarios> getAllFuncionarios() {
		return funcionariosList;
	}

	public void setAllFuncionarios(List<Funcionarios> funcionariosList) {
		this.funcionariosList = funcionariosList;
	}

	public String getidEndereco() {
		return idEndereco;
	}

	public void setidEndereco(String idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getTipoFuncionario() {
		return tipoFuncionario;
	}

	public void setTipoFuncionario(String tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}

	public Long getidFuncionario() {
		return idFuncionario;
	}

	public void setidFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
}
