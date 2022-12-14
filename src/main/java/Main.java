import Model.Data;
import Model.Extradition;
import Model.Reader;
import ModelView.ExtraditionService;
import org.apache.commons.codec.binary.Hex;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.*;


class Owner
{
    public Reader reader = null;
}


public class Main {

    static class TOwn
    {
        public static Reader reader = null;
    }

    static class Data {
        public static Model.Data data = null;
    }

    public class Extradition{
        public  static Model.Extradition extradition = null;
    }


    public static void main (String [] args) throws SQLException {

/*
        HashPassword hashPassword = new HashPassword("321top");
        byte[] hashedBytes = hashPassword.hashPassword();
        String hashedString = Hex.encodeHexString(hashedBytes);
        System.out.println(hashedString);


 */



        //JFrameMainWindowCheifLibrarian jFrameСрушаLibrarian = new JFrameMainWindowCheifLibrarian();

        JFrameLogin JFrameMainWindowLibrarian = new JFrameLogin();
/*
        Connection connection= null;
        String url = "jdbc:postgresql://127.0.0.1:5432/library";
        String name = "postgres";
        String password = "723982";
        try{
            Class.forName("org.postgresql.Driver");
            System.out.println("Драйвер подключен");
            connection = DriverManager.getConnection(url,name,password);
            System.out.println("Соединение установлено");

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result1 =statement.executeQuery("SELECT * FROM book");
            System.out.println("Выводим statement");
            while (result1.next()) {
                System.out.println( result1.getInt("book_id")
                        + "     "+ result1.getString("book_name")
                        + "     "+ result1.getInt("book_genre")
                );
            }

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
                if(connection!=null){
                    try {
                        connection.close();
                    }catch (SQLException ex){
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }


        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring-config.xml");
        ctx.refresh();

        insertUpdateExample(ctx);
        */
        }
        /*
    private static void insertUpdateExample(GenericXmlApplicationContext ctx) {
        ExtraditionService service = ctx.getBean("jpaContactService", ExtraditionService.class);

        Extradition ex = new Extradition();
        ex.setReader_name("Name");
        ex.setReader_surname("Surname");


       // ContactTelDetailEntity contactTelDetail = new ContactTelDetailEntity("Home", "1111111111");
        //contact.addContactTelDetail(contactTelDetail);

       // contactTelDetail = new ContactTelDetailEntity("Городской", "8-499-000-333");
        //contact.addContactTelDetail(contactTelDetail);

       // contactTelDetail = new ContactTelDetailEntity("Mobile", "2222222222");
       // contact.addContactTelDetail(contactTelDetail);

        service.save(ex);
    }


         */

    }

