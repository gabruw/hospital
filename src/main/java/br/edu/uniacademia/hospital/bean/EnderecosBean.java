package br.edu.uniacademia.hospital.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.uniacademia.hospital.dao.EnderecosDao;
import br.edu.uniacademia.hospital.model.Enderecos;

@Named
@ViewScoped
public class EnderecosBean implements Serializable {
	private static final long serialVersionUID = 1L;

	Enderecos enderecos = new Enderecos();
	List<Enderecos> enderecosList = new ArrayList<Enderecos>();

	public EnderecosBean() {
		enderecos = new Enderecos();
		enderecosList = new EnderecosDao().buscarTodas();
	}

	public void salvar(ActionEvent actionEvent) {
		new EnderecosDao().persistir(enderecos);
		enderecosList = new EnderecosDao().buscarTodas();
		enderecos = new Enderecos();
	}

	public void remover(ActionEvent actionEvent) {
		new EnderecosDao().remover(enderecos);
		enderecosList = new EnderecosDao().buscarTodas();
		enderecos = new Enderecos();
	}

	public Enderecos buscar(String localidade) {
		return enderecos;
	}

	public Map<Enderecos, String> getEnderecoOptions() {
		List<Enderecos> lista = getEnderecosList();
		Map<Enderecos, String> map = new HashMap<Enderecos, String>();

		lista.stream().forEach((e) -> map.put(e, e.getLogradouro() + ' ' + e.getNumero()));
		return map;
	}

	public Enderecos buscarPorId(String id) {
		new EnderecosDao().remover(enderecos);
		enderecos = new EnderecosDao().buscarPorId(id);

		return enderecos;
	}

	public Enderecos getEnderecos() {
		return this.enderecos;
	}

	public void setEnderecos(Enderecos enderecos) {
		this.enderecos = enderecos;
	}

	public List<Enderecos> getEnderecosList() {
		return enderecosList;
	}

	public void setEnderecosList(List<Enderecos> enderecosList) {
		this.enderecosList = enderecosList;
	}
}
