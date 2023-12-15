import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {
    public static Statement statmt;
    public static Connection connection;

    public static void connect(Connection conn)throws ClassNotFoundException, SQLException{
        connection = conn;
        statmt = connection.createStatement();
    }

    public static void createDB() throws ClassNotFoundException, SQLException {
        statmt.execute("CREATE TABLE if not exists Earthquake ('id' text primary key, 'depth' INT,'magnitudeType' text, 'magnitude' REAL, 'state' text, 'year' int, 'over_time' text);");
        System.out.println("Таблица создана или уже существует.");
    }

    public static void writeDB(ArrayList<Earthquake> arr) throws SQLException {
        for (Earthquake e : arr){
            String insert = "INSERT INTO Earthquake VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pr = connection.prepareStatement(insert);
            pr.setObject(1, e.getId());
            pr.setObject(2, e.getDepth());
            pr.setObject(3, e.getMagnitudeType());
            pr.setObject(4, e.getMagnitude());
            pr.setObject(5, e.getState());
            pr.setObject(6, e.getYear());
            pr.setObject(7,e.getOverTime());
            pr.execute();
        }
        System.out.println("Таблица заполнена." + "\n");
    }

    public static String getMaxYear() throws java.sql.SQLException{
        ResultSet out = statmt.executeQuery("Select max(year) from Earthquake;");
        return out.getString("max(year)");
    }
    public static String getMinYear() throws java.sql.SQLException{
        ResultSet out = statmt.executeQuery("Select min(year) from Earthquake;");
        return out.getString("min(year)");
    }
    public static void countEarthQuakeOfYear()throws java.sql.SQLException{
        List<String> count = new ArrayList<>();
        for (int i = Integer.parseInt(DB.getMinYear()); i <= Integer.parseInt(DB.getMaxYear()); i ++){
            String request = "Select count(year) from Earthquake where year = ?;";
            PreparedStatement pr = connection.prepareStatement(request);
            pr.setObject(1, i);
            ResultSet out = pr.executeQuery();
            count.add(i + " - " +out.getString("count(year)"));
        }
        System.out.println("1) Постройте график по среднему количеству землетрясений для каждого года:\n");

        BarChart chart = new BarChart("Graf", "Количество землетрясений за каждый год", count);
        chart.pack( );
        chart.setAutoRequestFocus(true);
        chart.setSize(800,600);
        chart.setVisible( true );
    }
    public static String avgMagnitude(String state) throws java.sql.SQLException{
        List<Double> arr = new ArrayList<>();
        String request = "SELECT avg(magnitude) FROM 'Earthquake' where state = ?";
        PreparedStatement pr = connection.prepareStatement(request);
        pr.setObject(1, state);
        ResultSet out = pr.executeQuery();
        return out.getString(1).substring(0,4);
    }
    public static String mostDepthEarthquake(int year)throws java.sql.SQLException{
        String request = "Select state, depth from Earthquake where year = ? order by depth desc limit 1;";
        PreparedStatement pr = connection.prepareStatement(request);
        pr.setInt(1, year);
        ResultSet out = pr.executeQuery();
        return Integer.toString(year) + " - " + out.getString(2) + " - " + out.getString(1);
    }
}
