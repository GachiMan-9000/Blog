package ru.vsu.cs.MaxSemeyonov.web;

import ru.vsu.cs.MaxSemeyonov.models.Post;
import ru.vsu.cs.MaxSemeyonov.models.Reaction;
import ru.vsu.cs.MaxSemeyonov.sql.repositories.SQLPostRepository;
import ru.vsu.cs.MaxSemeyonov.sql.repositories.SQLReactionRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@WebServlet(name = "reactions", urlPatterns = {"/reactions/*"} )
public class ReactionServlet extends CRUDServlet<Reaction>{
    public ReactionServlet() {
        super(Reaction.class, SQLReactionRepository.getInstance());
    }

    @Override
    public List<Reaction> getWithParams(HttpServletRequest request) {
        return SQLReactionRepository.getInstance().getAll();
    }
}
