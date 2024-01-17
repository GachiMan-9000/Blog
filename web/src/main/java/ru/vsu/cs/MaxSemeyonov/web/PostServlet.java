package ru.vsu.cs.MaxSemeyonov.web;

import ru.vsu.cs.MaxSemeyonov.models.LikeComment;
import ru.vsu.cs.MaxSemeyonov.models.Post;
import ru.vsu.cs.MaxSemeyonov.sql.repositories.SQLLikeCommentRepository;
import ru.vsu.cs.MaxSemeyonov.sql.repositories.SQLPostRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@WebServlet(name = "posts", urlPatterns = {"/posts/*"} )
public class PostServlet extends CRUDServlet<Post>{
    public PostServlet() {
        super(Post.class, SQLPostRepository.getInstance());
    }

    @Override
    public List<Post> getWithParams(HttpServletRequest request) {
        String user_id = request.getParameter("user_id");
        if(user_id != null) {
            return SQLPostRepository.getInstance().getPostByUser(Integer.parseInt(user_id));
        }
        return SQLPostRepository.getInstance().getAll();
    }
}
