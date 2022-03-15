package br.edu.ifms.ordem.entities;

import java.io.Serializable;
import java.sql.Date;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.edu.ifms.ordem.entities.enums.Prioridade;
import br.edu.ifms.ordem.entities.enums.Status;

@Entity
@Table(name = "tb_ordem_de_servico")
public class OrdemDeServico  implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "descricao_problema")
	private String descricaoProblema;
	@Column(name = "descricao_solucao")
	private String descricaoSolucao;
	private Date dataCadastro;
	@Enumerated(EnumType.STRING)
	private Status status;
	@Enumerated(EnumType.STRING)
	private Prioridade prioridade;
	
	@ManyToOne
	@JoinColumn(name = "id_tecnico_fk")
	private Tecnico tecnico;
	
	@ManyToMany
	@JoinTable(name = "tb_equipamento_servico",
			joinColumns = @JoinColumn(name = "ordem_id"),
			inverseJoinColumns = @JoinColumn(name = "equipamento_id"))
	Set<Equipamento> equipamentos = new HashSet<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricaoProblema() {
		return descricaoProblema;
	}
	public void setDescricaoProblema(String descricaoProblema) {
		this.descricaoProblema = descricaoProblema;
	}
	public String getDescricaoSolucao() {
		return descricaoSolucao;
	}
	public void setDescricaoSolucao(String descricaoSolucao) {
		this.descricaoSolucao = descricaoSolucao;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Prioridade getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemDeServico other = (OrdemDeServico) obj;
		return Objects.equals(id, other.id);
	}

}
