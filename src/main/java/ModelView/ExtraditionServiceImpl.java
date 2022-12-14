package ModelView;

import Model.Extradition;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
@Service("jpaContactService")
@Transactional
public class ExtraditionServiceImpl implements ExtraditionService {
    @PersistenceContext
    private EntityManager em;


    public Extradition save(Extradition ex){
        if(ex.copy_book_id==null){
            em.persist(ex);
        }else {
            em.merge(ex);
        }
        System.out.println("Extradition saved with id: " + ex.copy_book_id);
        return ex;
    }



}
