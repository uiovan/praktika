import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class db {
    public static Statement statmt;

    public static void connect(Connection conn)throws ClassNotFoundException, SQLException{
        statmt = conn.createStatement();
    }

    public static void CreateDB() throws ClassNotFoundException, SQLException {
        statmt.execute("CREATE TABLE if not exists Earthquake ('id' text, 'depth' INT,'magnitudeType' text, 'magnitude' REAL, 'state' text, 'year' int, 'over_time' text);");
        System.out.println("Таблица создана или уже существует.");
    }

    public static void WriteDB(ArrayList<Earthquake> arr, Connection conn) throws SQLException {
        for (Earthquake e : arr){
            String insert = "INSERT INTO Earthquake VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pr = conn.prepareStatement(insert);
            pr.setObject(1, e.getId());
            pr.setObject(2, e.getDepth());
            pr.setObject(3, e.getMagnitudeType());
            pr.setObject(4, e.getMagnitude());
            pr.setObject(5, e.getState());
            pr.setObject(6, e.getYear());
            pr.setObject(7,e.getOverTime());
            pr.execute();
        }
        System.out.println("Таблица заполнена.");
    }

    public static String getMaxYear() throws java.sql.SQLException{
        ResultSet out = statmt.executeQuery("Select max(year) from Earthquake;");
        return out.getString("max(year)");
    }
    public static String getMinYear() throws java.sql.SQLException{
        ResultSet out = statmt.executeQuery("Select min(year) from Earthquake;");
        return out.getString("min(year)");
    }
    public static String countEarthQuakeOfYear(Connection conn, int year)throws java.sql.SQLException{
        String insert = "Select count(year) from Earthquake where year = ?;";
        PreparedStatement pr = conn.prepareStatement(insert);
        pr.setObject(1, year);
        ResultSet out = pr.executeQuery();

        return year + " - " +out.getString("count(year)");
    }
    public static List<Double> avgMagnitude(Connection conn, String state) throws java.sql.SQLException{
        List<Double> arr = new ArrayList<>();
        String insert = "Select magnitude from Earthquake where state = ?;";
        PreparedStatement pr = conn.prepareStatement(insert);
        pr.setObject(1, state);
        ResultSet out = pr.executeQuery();

        while (out.next()){
            arr.add(out.getDouble(1));
        }

        return arr;
    }
    public static void mostDepthEarthQuake(Connection conn, int year)throws java.sql.SQLException{
        String insert = "Select state, depth from Earthquake where year = ? order by depth desc;";
        PreparedStatement pr = conn.prepareStatement(insert);
        pr.setObject(1, year);
        ResultSet out = pr.executeQuery();

        System.out.println("Самое глубокое землетрясение за "+year+"г, глубиной = "+out.getInt(2)+"м произошло в штате " +out.getString(1));
    }
}
