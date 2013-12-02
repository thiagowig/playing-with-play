package models;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints;
import play.db.jpa.JPA;

/**
 * Entidade que representa um time.
 */
@Entity
public class Time {

	@Id
	public Long id;

	@Constraints.Required
	public String nome;

	public static Time findById(Long id) {
		return JPA.em().find(Time.class, id);
	}

	public static Map<String,String> options() {
		@SuppressWarnings("unchecked")
		List<Time> times = JPA.em().createQuery("FROM Time ORDER BY nome").getResultList();
		LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
		
		for(Time posicao: times) {
			options.put(posicao.id.toString(), posicao.nome);
		}
		
		return options;
	}
}
