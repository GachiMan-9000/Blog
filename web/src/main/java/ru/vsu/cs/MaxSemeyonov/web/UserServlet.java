package ru.vsu.cs.MaxSemeyonov.web;

import ru.vsu.cs.MaxSemeyonov.models.Reaction;
import ru.vsu.cs.MaxSemeyonov.models.User;
import ru.vsu.cs.MaxSemeyonov.sql.repositories.SQLReactionRepository;
import ru.vsu.cs.MaxSemeyonov.sql.repositories.SQLUserRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@WebServlet(name = "users", urlPatterns = {"/users/*"} )
public class UserServlet extends CRUDServlet<User>{
    public UserServlet() {
        super(User.class, SQLUserRepository.getInstance());
    }

    @Override
    public List<User> getWithParams(HttpServletRequest request) {
        return SQLUserRepository.getInstance().getAll();
    }
}
