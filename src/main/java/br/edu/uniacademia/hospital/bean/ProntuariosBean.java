package br.edu.uniacademia.hospital.bean;

import br.edu.uniacademia.hospital.dao.FuncionariosDao;
import br.edu.uniacademia.hospital.dao.PacientesDao;
import br.edu.uniacademia.hospital.dao.ProntuariosDao;
import br.edu.uniacademia.hospital.model.Prontuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ProntuariosBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	Prontuarios prontuarios = new Prontuarios();
	List<Prontuarios> prontuariosList = new ArrayList<Prontuarios>();

	Long pacienteId;
	String descricao;
	Long funcionarioId;

	public ProntuariosBean() {
		descricao = null;
		pacienteId = null;
		funcionarioId = null;
		prontuarios = new Prontuarios();
		prontuariosList = new ProntuariosDao().buscarTodas();
	}

	public void salvar(ActionEvent actionEvent) {
		prontuarios.setPacientesidPaciente(new PacientesDao().buscarPorId(pacienteId));
		prontuarios.setFuncionariosidFuncionario(new FuncionariosDao().buscarPorId(funcionarioId));

		new ProntuariosDao().persistir(prontuarios);
		descricao = prontuarios.getDescricao();

		prontuariosList = new ProntuariosDao().buscarTodas();
		prontuarios = new ProntuariosDao().buscar(descricao);
	}

	public void remover(ActionEvent actionEvent) {
		new ProntuariosDao().remover(prontuarios.getIdProntuario());

		prontuariosList = new ProntuariosDao().buscarTodas();
		prontuarios = new Prontuarios();
	}

	public Prontuarios getProntuarios() {
		return prontuarios;
	}

	public void setProntuarios(Prontuarios prontuarios) {
		this.prontuarios = prontuarios;
	}

	public List<Prontuarios> getAllProntuarios() {
		return prontuariosList;
	}

	public void setProntuariosList(List<Prontuarios> prontuariosList) {
		this.prontuariosList = prontuariosList;
	}

	public Long getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(Long pacienteId) {
		this.pacienteId = pacienteId;
	}

	public Long getFuncionarioId() {
		return funcionarioId;
	}

	public void setFuncionarioId(Long funcionarioId) {
		this.funcionarioId = funcionarioId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
