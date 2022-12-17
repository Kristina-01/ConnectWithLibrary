package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public  class Reader {

    public ArrayList<Reader> res_ = new ArrayList<>();
    public String surname;
    public String name;
    public String patrinymic;
    public String date_of_birth;
    public String actual_address;
    public String telephone;
    public String email;
    public String number_card;

    public  String r_login;
    public String r_password;

    public Reader(){}


    public void Init(Connection cont, String login) throws SQLException {

        String sql1 = "SELECT * FROM reader_view_their_data('" + login + "')";

        Statement statement = null;
        statement = cont.createStatement();
        var tmpres1 = statement.executeQuery(sql1);
        int i=0;

        while (tmpres1.next())
        {
            var reader = new Reader();
            reader.surname  = tmpres1.getString(1);
            reader.name  = tmpres1.getString(2);
            reader.patrinymic = tmpres1.getString(3);
            reader.date_of_birth = tmpres1.getString(4);
            reader.actual_address = tmpres1.getString(5);
            reader.telephone = tmpres1.getString(6);
            reader.email = tmpres1.getString(7);
            reader.number_card = tmpres1.getString(8);
            res_.add(reader);
            System.out.println(res_.get(i).patrinymic);
            i++;
        }
    }

    public Reader(String surname, String name,String patrinymic,String date_of_birth, String actual_address, String telephone,String email,String number_card, String r_login, String r_password){
        this.surname = surname;
        this.name = name;
        this.patrinymic = patrinymic;
        this.date_of_birth = date_of_birth;
        this.actual_address = actual_address;
        this.telephone = telephone;
        this.email = email;
        this.number_card = number_card;
    }

}
