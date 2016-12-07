import dao.PersonDAO;
import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;

@Test
@ContextConfiguration(locations = {"classpath:spring4.xml"})
public class TestSpring extends AbstractTestNGSpringContextTests{

    @Autowired
    PersonDAO personDao;

    @Test()
    void testEmailGenerator() {
        List<Person> providers = null;

        try {
            providers = personDao.list();
            for (Person provider : providers) {
                System.out.println(provider);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test()
    void testInsertCortages() {

      personDao.createIPersons();

    }

}