package com.example.library_management_system.dao;

import com.example.library_management_system.entity.Book;
import com.example.library_management_system.entity.User;
import com.example.library_management_system.rowMapper.BookRowMapper;
import com.example.library_management_system.rowMapper.UserRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // To save the user
    public User save(User user) {

        String query = "INSERT INTO users(name, email, password, city) VALUES(?, ?, ?, ?);";

        int rowAffected = jdbcTemplate.update(
                query,
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getCity()
        );

        System.out.println("user added: " + rowAffected);
        return user;


    }

    // To delete the user
    public void delete(int userId) {

        String query = "DELETE FROM users WHERE userid = ?";

        int rowAffected = jdbcTemplate.update(
                query,
                userId
        );

        System.out.println("user deleted: " + rowAffected);

    }

    // To update the User
    public void update(int userId, User newUser) {

        String query = "UPDATE users SET name = ?, email = ?, password = ?, city = ? WHERE userid= ?";

        int rowAffected = jdbcTemplate.update(
                query,
                newUser.getName(),
                newUser.getEmail(),
                newUser.getPassword(),
                newUser.getCity(),
                userId
        );

        System.out.println("user updated: " + rowAffected);

    }

    // To get single User
    public User getUser(int userId) {

        String query = "SELECT * FROM users WHERE userid = ?";

        User user = jdbcTemplate.queryForObject(
                query,
                new UserRowMapper(),
                userId
        );

        return user;

    }

    // To get All User
    public List<User> getAllUser() {

        String query = "SELECT * FROM users;";

        List<User> users = jdbcTemplate.query(
                query,
                new UserRowMapper()
        );

        return users;
    }

    // To search user
    public List<User> searchBook(String nameKeyword) {

        String query = "SELECT * FROM users WHERE name like ?";

        List<User> users = jdbcTemplate.query(
                query,
                new UserRowMapper(),
                "%" + nameKeyword + "%"
        );

        return users;
    }
}
