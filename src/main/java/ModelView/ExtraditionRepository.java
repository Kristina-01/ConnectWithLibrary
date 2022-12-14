package ModelView;

import Model.Extradition;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ExtraditionRepository extends CrudRepository<Extradition, Integer> {
   Extradition findById(int copy_book_id);
}
