package ModelView;

import Model.Extradition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public  interface ExtraditionService {
 //  @Autowired
   // ExtraditionRepository ex;
    public  Extradition save(Extradition ex);
/*
    public void addExtradition(String surname, String name, String number_card, String copy_book, String book_name, String author, String date_of_issue, String date_of_return){
        Extradition extradition = new Extradition();
        extradition.reader_surname = surname;
        extradition.reader_name = name;
        extradition.reader_numbeer_card = number_card;
        extradition.copy_book_id = copy_book;
        extradition.book_name = book_name;
        extradition.author = author.replaceAll("[^\\w+]", " ");
        extradition.extradition_date_of_issue = date_of_issue;
        extradition.extradition_return_date = date_of_return;

        ex.save(extradition);
    }

    public void updateExtradition(int id){
        Extradition extradition = ex.findById(id);
        extradition.extradition_return_date = String.valueOf(LocalDate.now());
        ex.save(extradition);

    }

 */


}
