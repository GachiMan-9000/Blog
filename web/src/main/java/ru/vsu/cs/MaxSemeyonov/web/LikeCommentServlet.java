package ru.vsu.cs.MaxSemeyonov.web;

import ru.vsu.cs.MaxSemeyonov.models.Comment;
import ru.vsu.cs.MaxSemeyonov.models.LikeComment;
import ru.vsu.cs.MaxSemeyonov.sql.repositories.SQLCommentRepository;
import ru.vsu.cs.MaxSemeyonov.sql.repositories.SQLLikeCommentRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@WebServlet(name = "like_comments", urlPatterns = {"/like_comments/*"} )
public class LikeCommentServlet extends CRUDServlet<LikeComment>{
    public LikeCommentServlet() {
        super(LikeComment.class, SQLLikeCommentRepository.getInstance());
    }

    @Override
    public List<LikeComment> getWithParams(HttpServletRequest request) {
        String comment_id = request.getParameter("comment_id");
        if(comment_id != null) {
            return SQLLikeCommentRepository.getInstance().getLikesByComment(Integer.parseInt(comment_id));
        }
        return SQLLikeCommentRepository.getInstance().getAll();
    }
}
