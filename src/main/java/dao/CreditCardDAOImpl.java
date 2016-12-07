package dao;

import model.CreditCard;
import model.Person;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class CreditCardDAOImpl implements CreditCardDAO {

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<CreditCard> getAll(Integer personId) {

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Create a Hibernate query (HQL)
        Query query = session.createQuery("FROM model.Person as p LEFT JOIN FETCH  p.creditCards WHERE p.id=" + personId);

        Person person = (Person) query.uniqueResult();

        // Retrieve all
        return new ArrayList<CreditCard>(person.getCreditCards());
    }

    @Override
    public List<CreditCard> getAll() {

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Create a Hibernate query (HQL)
        Query query = session.createQuery("FROM  model.CreditCard");

        // Retrieve all
        return query.list();
    }

    @Override
    public CreditCard get(Integer id) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing credit card
        CreditCard creditCard = (CreditCard) session.get(CreditCard.class, id);

        // Persists to db
        return creditCard;
    }

    @Override
    public void add(Integer personId, CreditCard creditCard) {

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Persists to db
        session.save(creditCard);

        // Add to person as well
        // Retrieve existing person via id
        Person existingPerson = (Person) session.get(Person.class, personId);

        // Assign updated values to this person
        existingPerson.getCreditCards().add(creditCard);

        // Save updates
        session.save(existingPerson);
    }

    @Override
    public void delete(Integer id) {

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Delete reference to foreign key credit card first
        // We need a SQL query instead of HQL query here to access the third table
        Query query = session.createSQLQuery("DELETE FROM PERSON_CREDIT_CARD " +
                "WHERE creditCards_ID=" + id);

        query.executeUpdate();

        // Retrieve existing credit card
        CreditCard creditCard = (CreditCard) session.get(CreditCard.class, id);

        // Delete
        session.delete(creditCard);
    }

    @Override
    public void edit(CreditCard creditCard) {

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing credit card via id
        CreditCard existingCreditCard = (CreditCard) session.get(CreditCard.class, creditCard.getId());

        // Assign updated values to this credit card
        existingCreditCard.setNumber(creditCard.getNumber());
        existingCreditCard.setType(creditCard.getType());

        // Save updates
        session.save(existingCreditCard);
    }
}