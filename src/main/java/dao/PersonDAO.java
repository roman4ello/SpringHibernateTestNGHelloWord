package dao;

import model.Person;

import java.util.List;

public interface PersonDAO {

    public void save(Person p);

    public List<Person> list();

    public  void createIPersons();

}