package ru.vsu.cs.MaxSemeyonov.web;

import ru.vsu.cs.MaxSemeyonov.models.Comment;
import ru.vsu.cs.MaxSemeyonov.sql.repositories.SQLCommentRepository;

import javax.servlet.annotation.WebServlet
        ;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@WebServlet(name = "comments", urlPatterns = {"/comments/*"} )
public class CommentServlet extends CRUDServlet<Comment>{
    public CommentServlet() {
        super(Comment.class, SQLCommentRepository.getInstance());
    }

    @Override
    public List<Comment> getWithParams(HttpServletRequest request) {
        String post_id = request.getParameter("post_id");
        if(post_id != null) {
            return SQLCommentRepository.getInstance().getCommentByPost(Integer.parseInt(post_id));
        }
        return SQLCommentRepository.getInstance().getAll();
    }
}
