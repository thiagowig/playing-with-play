package controllers;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.db.jpa.*;

import views.html.*;

import models.*;

/**
 * Controller central da aplicação, que visa manter operações
 * sobre os jogadores.
 */
public class Application extends Controller {
    
    /**
     * Redireciona para a página de listagem dos jogadores
     */
    public static Result GO_HOME = redirect(
        routes.Application.list(0, "nomeProfissional", "asc", "")
    );
    
    /**
     * Redireciona para a página inicial
     */
    public static Result index() {
        return GO_HOME;
    }

    /**
     * Exibe a listagem de jogadores, paginada e ordenada.
     */
    @Transactional(readOnly=true)
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                Jogador.page(page, 10, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Exibe o formulário de edição de um jogador existente.
     */
    @Transactional(readOnly=true)
    public static Result edit(Long id) {
        Form<Jogador> jogadorForm = form(Jogador.class).fill(
            Jogador.findById(id)
        );
        return ok(
            editForm.render(id, jogadorForm)
        );
    }
    
    /**
     * Atualiza o jogador, previamente editado.
     */
    @Transactional
    public static Result update(Long id) {
        Form<Jogador> jogadorForm = form(Jogador.class).bindFromRequest();
        
        if(jogadorForm.hasErrors()) {
            return badRequest(editForm.render(id, jogadorForm));
        }
        
        jogadorForm.get().update(id);
        flash("success", "Jogador " + jogadorForm.get().nomeProfissional + " foi atualizado");
        
        return GO_HOME;
    }
    
    /**
     * Exibe o formulário de novo jogador.
     */
    @Transactional(readOnly=true)
    public static Result create() {
        Form<Jogador> jogadorForm = form(Jogador.class);
        
        return ok(
            createForm.render(jogadorForm)
        );
    }
    
    /**
     * Salva o novo jogador. 
     */
    @Transactional
    public static Result save() {
        Form<Jogador> jogadorForm = form(Jogador.class).bindFromRequest();
        
        if(jogadorForm.hasErrors()) {
            return badRequest(createForm.render(jogadorForm));
        }
        
        jogadorForm.get().save();
        flash("success", "Jogador " + jogadorForm.get().nomeProfissional + " foi criado");
        
        return GO_HOME;
    }
    
    /**
     * Deleta um jogador e retorna para a página inicial.
     */
    @Transactional
    public static Result delete(Long id) {
        Jogador.findById(id).delete();
        flash("success", "O Jogador foi deletado");
        
        return GO_HOME;
    }

}
            
