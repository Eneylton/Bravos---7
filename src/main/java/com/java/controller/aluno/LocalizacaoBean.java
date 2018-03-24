package com.java.controller.aluno;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.java.modelo.Aluno;
import com.java.service.AlunoService;

@Named
@ViewScoped
public class LocalizacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private AlunoService alunoService = new AlunoService();

	private List<Aluno> listarAlunos = new ArrayList<>();

	private Aluno aluno = new Aluno();

	private MapModel draggableModel = new DefaultMapModel();

	private Marker marker;

	private String lat;

	private String logt;

	private double l1;

	private double l2;

	private String titulo;

	private String titulo2;

	private String id;

	private String latitude;

	private String longitude;

	private String foto;

	private String alunos;

	private String endereco;

	@PostConstruct
	public void init() {

		try {

			listarAlunos = alunoService.listarAlunosMapa();

			for (Aluno aluno : listarAlunos) {
				this.lat = aluno.getLatitude();
				this.logt = aluno.getLongitude();
				this.foto = aluno.getFoto();
				this.alunos = aluno.getNome();
				this.endereco = aluno.getEnderecoCompleto();

				double latDouble = Double.parseDouble(this.lat);
				double longDouble = Double.parseDouble(this.logt);

				LatLng coord1 = new LatLng(latDouble, longDouble);

				draggableModel.addOverlay(
						new Marker(coord1, alunos, foto, "../resources/img/ponto.png",alunos + " -> " + " " + endereco));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void onMarkerDrag(MarkerDragEvent event) throws SQLException {
		marker = event.getMarker();

		this.id = marker.getId();
		this.l1 = marker.getLatlng().getLat();
		this.l2 = marker.getLatlng().getLng();
		this.titulo = marker.getTitle();

		this.latitude = Double.toString(this.l1);

		this.longitude = Double.toString(this.l2);

		aluno.setLatitude(latitude);
		aluno.setLongitude(longitude);
		aluno.setNome(titulo);

	}

	public void onMarkerSelect(OverlaySelectEvent event) {
		marker = (Marker) event.getOverlay();
	}

	public void limpar() {
		aluno = new Aluno();
	}

	public List<Aluno> getListarAlunos() {
		return listarAlunos;
	}

	public void setListarAlunos(List<Aluno> listarAlunos) {
		this.listarAlunos = listarAlunos;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public MapModel getDraggableModel() {
		return draggableModel;
	}

	public void setDraggableModel(MapModel draggableModel) {
		this.draggableModel = draggableModel;
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLogt() {
		return logt;
	}

	public void setLogt(String logt) {
		this.logt = logt;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public double getL1() {
		return l1;
	}

	public void setL1(double l1) {
		this.l1 = l1;
	}

	public double getL2() {
		return l2;
	}

	public void setL2(double l2) {
		this.l2 = l2;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo2() {
		return titulo2;
	}

	public void setTitulo2(String titulo2) {
		this.titulo2 = titulo2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getAlunos() {
		return alunos;
	}

	public void setAlunos(String alunos) {
		this.alunos = alunos;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}