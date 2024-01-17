package ru.vsu.cs.MaxSemeyonov.web;

import ru.vsu.cs.MaxSemeyonov.models.Reaction;
import ru.vsu.cs.MaxSemeyonov.models.ReactionPost;
import ru.vsu.cs.MaxSemeyonov.sql.repositories.SQLCommentRepository;
import ru.vsu.cs.MaxSemeyonov.sql.repositories.SQLReactionPostRepository;
import ru.vsu.cs.MaxSemeyonov.sql.repositories.SQLReactionRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "reaction_posts", urlPatterns = {"/reaction_posts/*"} )
public class ReactionPostServlet extends CRUDServlet<ReactionPost>{
    public ReactionPostServlet() {
        super(ReactionPost.class, SQLReactionPostRepository.getInstance());
    }

    @Override
    public List<ReactionPost> getWithParams(HttpServletRequest request) {
        String post_id = request.getParameter("post_id");
        if(post_id != null) {
            return SQLReactionPostRepository.getInstance().getReactionsByPost(Integer.parseInt(post_id));
        }

        return SQLReactionPostRepository.getInstance().getAll();
    }
}
