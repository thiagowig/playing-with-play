package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.jpa.JPA;

/**
 * Entidade que representa um jogador.
 */
@Entity 
@SequenceGenerator(name = "jogador_seq", sequenceName = "jogador_seq")
public class Jogador {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jogador_seq")
	public Long id;

	@Constraints.Required
	public String nomeProfissional;

	@Constraints.Required
	public String nomeCompleto;

	@Formats.DateTime(pattern="dd/MM/yyyy")
	public Date dataNascimento;

	/**
	 * Relacionamento com a posicao do jogador
	 */
	@ManyToOne(cascade = CascadeType.MERGE)
	public Posicao posicao;

	/**
	 * Relacionamento com o time do jogador
	 */
	@ManyToOne(cascade = CascadeType.MERGE)
	public Time time;

	/**
	 * Localiza o jogador pelo ID
	 */
	public static Jogador findById(Long id) {
		return JPA.em().find(Jogador.class, id);
	}

	/**
	 * Atualiza o jogador
	 */
	public void update(Long id) {
		this.atribuirPosicaoETime();
		this.id = id;
		JPA.em().merge(this);
	}

	/**
	 * Salva o jogador
	 */
	public void save() {
		this.atribuirPosicaoETime();
		JPA.em().persist(this);
	}

	/**
	 * Atribui a posicao e o time ao jogador.
	 */
	private void atribuirPosicaoETime() {
		if(this.posicao.id == null) {
			this.posicao = null;
		} else {
			this.posicao = Posicao.findById(posicao.id);
		}

		if(this.time.id == null) {
			this.time = null;
		} else {
			this.time = Time.findById(time.id);
		}
	}

	/**
	 * Deleta o jogador
	 */
	public void delete() {
		JPA.em().remove(this);
	}

	/**
	 * Efetua a paginação da listagem
	 */
	@SuppressWarnings("unchecked")
	public static Page page(int page, int pageSize, String sortBy, String order, String filter) {
		if(page < 1) page = 1;
		Long total = (Long)JPA.em()
				.createQuery("select count(c) from Jogador c where lower(c.nomeProfissional) like ?")
				.setParameter(1, "%" + filter.toLowerCase() + "%")
				.getSingleResult();
		
		List<Jogador> data = JPA.em()
		.createQuery("from Jogador c where lower(c.nomeProfissional) like ? order by c." + sortBy + " " + order)
		.setParameter(1, "%" + filter.toLowerCase() + "%")
		.setFirstResult((page - 1) * pageSize)
		.setMaxResults(pageSize)
		.getResultList();
		
		return new Page(data, total, page, pageSize);
	}

	/**
	 * Classe que representa uma paginação.
	 */
	public static class Page {

		private final int pageSize;
		private final long totalRowCount;
		private final int pageIndex;
		private final List<Jogador> list;

		public Page(List<Jogador> data, long total, int page, int pageSize) {
			this.list = data;
			this.totalRowCount = total;
			this.pageIndex = page;
			this.pageSize = pageSize;
		}

		public long getTotalRowCount() {
			return totalRowCount;
		}

		public int getPageIndex() {
			return pageIndex;
		}

		public List<Jogador> getList() {
			return list;
		}

		public boolean hasPrev() {
			return pageIndex > 1;
		}

		public boolean hasNext() {
			return (totalRowCount/pageSize) >= pageIndex;
		}

		/**
		 * Exibe o texto com a página atual e total.
		 */
		public String getDisplayXtoYofZ() {
			int start = ((pageIndex - 1) * pageSize + 1);
			int end = start + Math.min(pageSize, list.size()) - 1;
			return start + " a " + end + " no total de " + totalRowCount;
		}

	}
}
