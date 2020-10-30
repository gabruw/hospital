package br.edu.uniacademia.hospital.bean;

import br.edu.uniacademia.hospital.dao.EnderecosDao;
import br.edu.uniacademia.hospital.dao.PacientesDao;
import br.edu.uniacademia.hospital.model.Pacientes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PacientesBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	Pacientes pacientes = new Pacientes();
	List<Pacientes> pacientesList = new ArrayList<Pacientes>();

	String idEndereco;
	String idPaciente;
	String nomePaciente;

	public PacientesBean() {
		idEndereco = null;
		idPaciente = null;
		nomePaciente = null;
		pacientes = new Pacientes();
		pacientesList = new PacientesDao().buscarTodas();
	}

	public void salvar(ActionEvent actionEvent) {
		pacientes.setEndereco(new EnderecosDao().buscarPorId(idEndereco));
		new PacientesDao().persistir(pacientes);

		nomePaciente = pacientes.getNomePaciente();

		pacientesList = new PacientesDao().buscarTodas();
		pacientes = new PacientesDao().buscar(nomePaciente);
	}

	public void remover(ActionEvent actionEvent) {
		new PacientesDao().remover(Long.valueOf(idPaciente));

		pacientesList = new PacientesDao().buscarTodas();
		pacientes = new Pacientes();
	}

	public Pacientes getPacientes() {
		return pacientes;
	}

	public void setPacientes(Pacientes pacientes) {
		this.pacientes = pacientes;
	}

	public List<Pacientes> getAllPacientes() {
		return pacientesList;
	}

	public void setAllPacientes(List<Pacientes> pacientesList) {
		this.pacientesList = pacientesList;
	}

	public String getidEndereco() {
		return idEndereco;
	}

	public void setidEndereco(String idEndereco) {
		this.idEndereco = idEndereco;
	}
}
