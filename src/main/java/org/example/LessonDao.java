package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonDao {

    public static void main(String[] args) throws SQLException {

        Lesson lesson1 = new Lesson(1, "Lesson_01", 1);
        Lesson lesson2 = new Lesson(2,"Lesson_02", 2);

        System.out.println(add(lesson1));
        System.out.println(add(lesson2));

        System.out.println(delete(2));

        System.out.println(getAll());
        System.out.println(get(1));
    }

    public static int add(Lesson lesson) throws SQLException {
        int status = 0;
        Connection connection = DataBaseConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("insert into lesson(name, updated_at, homework_id) values (?,?,?)");
        ps.setString(1, lesson.name());
        ps.setDate(2, new Date(System.currentTimeMillis()));
        ps.setInt(3, lesson.homework_id());
        status = ps.executeUpdate();
        DataBaseConnection.close(connection);
        return status;
    }

    public static int delete(int id) throws SQLException {
        int status = 0;
        Connection connection = DataBaseConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("delete from lesson where id=?");
        ps.setInt(1, id);
        status = ps.executeUpdate();
        DataBaseConnection.close(connection);
        return status;
    }

    public static List<Lesson> getAll() throws SQLException {
        List<Lesson> lessons = new ArrayList<>();
        Connection connection = DataBaseConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from lesson");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Lesson lesson = new Lesson(rs.getInt(1), rs.getString(2), rs.getInt(4));
            lessons.add(lesson);
        }
        return lessons;
    }


    public static Lesson get(int id) throws SQLException {
        Lesson lesson = null;
        Connection connection = DataBaseConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from lesson where id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            lesson = new Lesson(rs.getInt(1), rs.getString(2), rs.getInt(4));
        }
        return lesson;
    }
}
