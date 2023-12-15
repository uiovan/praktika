import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static ArrayList<Earthquake> arr;

    public static void main(String[] args) {
        arr = parse();

        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:demo.db");
            if(conn!=null){
                System.out.println("Соединение установленно.");
                DB.connect(conn);
                DB.createDB();
                DB.writeDB(arr);
                
                DB.countEarthQuakeOfYear();
                
                calculateAverage();
                
                findState();
            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Ошибка");
        }

    }

    public static ArrayList<Earthquake> parse(){
        ArrayList<Earthquake> arr = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Землетрясения.csv"))) {
            String title = br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 7){
                    String str1 = values[6].substring(0,4);
                    Earthquake a = new Earthquake(values[0], Integer.parseInt(values[1]), values[2], Double.parseDouble(values[3]),
                            values[4]+", "+values[5], Integer.parseInt(values[6].substring(0,4)), values[6].substring(4));
                    arr.add(a);
                }
                else {
                    Earthquake a = new Earthquake(values[0], Integer.parseInt(values[1]), values[2], Double.parseDouble(values[3]),
                            values[4], Integer.parseInt(values[5].substring(0,4)), values[5].substring(4));
                    arr.add(a);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }
    public static void calculateAverage() throws SQLException {
        System.out.println("2) Выведите в консоль среднюю магнитуду для штата \"WEST VIRGINIA\":");
        System.out.println("Средняя магнитуда в штате WEST VIRGINIA = " + DB.avgMagnitude("WEST VIRGINIA") + "\n");
    }
    public static void findState() throws SQLException{
        String a = DB.mostDepthEarthquake(2013);
        String[] str = a.split(" - ");
        System.out.println("3) Выведите в консоль название штата, в котором произошло самое глубокое землетрясение в 2013 году:");
        System.out.println("Самое глубокое землетрясение за "+str[0]+"г, глубиной = "+str[1]+"м произошло в штате " +str[2]);
    }

}
