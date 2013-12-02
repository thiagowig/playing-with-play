package models;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints;
import play.db.jpa.JPA;

/**
 * Entidade que representa uma posição.
 */
@Entity 
public class Posicao {

	@Id
	public Long id;

	@Constraints.Required
	public String nome;

	public static Posicao findById(Long id) {
		return JPA.em().find(Posicao.class, id);
	}

	public static Map<String,String> options() {
		@SuppressWarnings("unchecked")
		List<Posicao> posicoes = JPA.em().createQuery("FROM Posicao").getResultList();
		LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
		
		for(Posicao posicao: posicoes) {
			options.put(posicao.id.toString(), posicao.nome);
		}
		
		return options;
	}
}
