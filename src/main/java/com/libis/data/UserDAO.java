
package com.libis.data;

import com.libis.model.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO {

    private DataSource getDataSource() throws Exception {
        Context context = new InitialContext();
        return (DataSource) context.lookup("java:comp/env/jdbc/dbdemo");
    }

    public User findByEmail(String email) throws SQLException {
        String sql = """
            SELECT userId, firstName, lastName, email, password
            FROM users
            WHERE email = ?
            """;

        try {
            DataSource dataSource = getDataSource();

            try (Connection conn = dataSource.getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, email);

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        return new User(
                                rs.getInt("userId"),
                                rs.getString("firstName"),
                                rs.getString("lastName"),
                                rs.getString("email"),
                                rs.getString("password")
                        );
                    }
                }
            }
        } catch (Exception e) {
            throw new SQLException("Unable to retrieve user from database.", e);
        }

        return null;
    }
}
