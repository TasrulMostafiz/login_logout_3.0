package com.example.login_logout_30;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomUserRowMapper implements RowMapper<CustomUser> {
    @Override
    public CustomUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomUser customUser=new CustomUser();
        customUser.setUserid(rs.getLong("userid"));
        customUser.setName(rs.getString("name"));
        customUser.setEmail(rs.getString("email"));
        customUser.setRoles(rs.getString("roles"));
        return customUser;
    }
}
