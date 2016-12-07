package dao;

import model.Person;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PersonDAOImpl implements PersonDAO {

    private SessionFactory sessionFactory;

    private String [] names = {"Roman", "Vladimir","Irina",
            "Joly", "Tom","Elena","Artur","David",
    "Alexandr","Poly"};
    private String [] countries = {"Ukraine", "USA", "Poland",
            "Canada", "Israil","Kuba",
    "Katar", "Danmark","Uzbekistan","Portugal"};

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Person p) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(p);
        tx.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Person> list() {
        Session session = this.sessionFactory.openSession();
        Criteria criteria = session.createCriteria(model.Person.class);
        List<Person> personList = new ArrayList<>(criteria.list());
        session.close();
        return personList;
    }


    @SuppressWarnings("unchecked")
    @Override
    public void createIPersons() {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        Person person;

        for (int i = 0; i < 10; i++) {
            person = new Person();
            person.setName(names[i]);
            person.setId(i+1);
            person.setCountry(countries[i]);
            session.save(person);

            if (i % 50 == 0) { // Same as the JDBC batch size
                //flush a batch of inserts and release memory:
                session.flush();
                session.clear();
            }
        }

        session.getTransaction().commit();
        session.close();
    }


}