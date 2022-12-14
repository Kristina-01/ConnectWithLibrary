package Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Entity

public class Extradition {
    @Id
    public String copy_book_id;
    public String reader_surname;
    public String reader_name;
    public String reader_numbeer_card;
    public String book_name;
    public String author;
    public String extradition_date_of_issue;
    public String extradition_return_date = null;

    public Extradition( String reader_surname, String reader_name, String reader_numbeer_card, String copy_book_id,String book_name, String author, String extradition_date_of_issue, String extradition_return_date) {
        this.setReader_name(reader_name);
        this.setReader_surname(reader_surname);
        this.setReader_numbeer_card(reader_numbeer_card);
        this.setCopy_book_id(copy_book_id);
        this.setBook_name(book_name);
        this.setAuthor(author);
        this.setExtradition_date_of_issue(extradition_date_of_issue);
        this.setExtradition_return_date(extradition_return_date);
    }


    /*
    public ArrayList<Extradition> Init(String copy_book_id, String reader_numbeer_card, Connection connection) {
        this.copy_book_id = copy_book_id;
        this.reader_numbeer_card = reader_numbeer_card;

        Statement statement = null;
        ArrayList <Extradition> extraditions = new ArrayList<>();
        try {
            statement = connection.createStatement();
            String sql1 = "SELECT reader_surname,reader_name, reader_number_card,copy_book_id, book_name,(author_name, author_patrinymic, author_surname)AS author1, extradition_date_of_issue, extradition_return_date \n" +
                    "FROM extradition\n" +
                    "JOIN reader ON (extradition_reader = reader_id)\n" +
                    "JOIN copy_book ON (copy_book_id = extradition_book)\n" +
                    "JOIN book ON (copy_book_book = book_id)\n" +
                    "JOIN author_book ON (ab_book = book_id)\n" +
                    "JOIN author ON (author_id= ab_author)";
                    var tmpres1 = statement.executeQuery(sql1);

            while (tmpres1.next())
            {
                var extradition = new Extradition();
                        extradition.reader_name = tmpres1.getString(1);
                        extradition.reader_surname = tmpres1.getString(2);
                        extradition.reader_numbeer_card =tmpres1.getString(3);
                        extradition.copy_book_id = tmpres1.getString(4);
                        extradition.book_name = tmpres1.getString(5);
                        extradition.author = tmpres1.getString(6);
                        extradition.extradition_date_of_issue = tmpres1.getString(7);
                        extradition.extradition_return_date = tmpres1.getString(8);
                        extraditions.add(extradition);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return extraditions;


    }

 */

    public String getCopy_book_id() {
        return copy_book_id;
    }

    public void setCopy_book_id(String copy_book_id) {
        this.copy_book_id = copy_book_id;
    }

    public String getReader_surname() {
        return reader_surname;
    }

    public void setReader_surname(String reader_surname) {
        this.reader_surname = reader_surname;
    }

    public String getReader_name() {
        return reader_name;
    }

    public void setReader_name(String reader_name) {
        this.reader_name = reader_name;
    }

    public String getReader_numbeer_card() {
        return reader_numbeer_card;
    }

    public void setReader_numbeer_card(String reader_numbeer_card) {
        this.reader_numbeer_card = reader_numbeer_card;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getExtradition_date_of_issue() {
        return extradition_date_of_issue;
    }

    public void setExtradition_date_of_issue(String extradition_date_of_issue) {
        this.extradition_date_of_issue = extradition_date_of_issue;
    }

    public String getExtradition_return_date() {
        return extradition_return_date;
    }

    public void setExtradition_return_date(String extradition_return_date) {
        this.extradition_return_date = extradition_return_date;
    }
}
