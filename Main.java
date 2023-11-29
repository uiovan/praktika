import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static ArrayList<Earthquake> arr;

    public static void main(String[] args) {
        arr = new ArrayList<>();
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

        List<Double> avg;
        List<String> count = new ArrayList<>();

        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:demo.db");
            if(conn!=null){
                System.out.println("Соединение установленно.");
                db.connect(conn);
                db.CreateDB();
                db.WriteDB(arr, conn);
                System.out.println();

                //Первое задание
                System.out.println("1) Постройте график по среднему количеству землетрясений для каждого года:\n");
                for (int i = Integer.parseInt(db.getMinYear()); i <= Integer.parseInt(db.getMaxYear()); i ++){
                    count.add(db.countEarthQuakeOfYear(conn, i));
                }
                BarChart chart = new BarChart("Graf", "Количество землетрясений за каждый год", count);
                chart.pack( );
                chart.setAutoRequestFocus(true);
                chart.setSize(800,600);
                chart.setVisible( true );

                //Второе задание
                System.out.println("2) Выведите в консоль среднюю магнитуду для штата \"West Virginia\":");
                avg = db.avgMagnitude(conn, "West Virginia");
                Double sum = 0.0;
                for (int i = 0; i< avg.size(); i++){
                    sum += avg.get(i);
                }
                sum = sum/ avg.size();
                sum = (Double)Math.floor(sum*100)/100.0;
                System.out.println("Cредняя магнитуда в штате West Virginia = " + sum + "\n");

                //Третье задание
                System.out.println("3) Выведите в консоль название штата, в котором произошло самое глубокое землетрясение в 2013 году:");
                db.mostDepthEarthQuake(conn, 2013);
            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Ошибка");
        }

    }

}

