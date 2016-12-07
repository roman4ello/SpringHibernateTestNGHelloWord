package dao;

import model.CreditCard;

import java.util.List;

public interface CreditCardDAO {

    /**
     * Retrieves all credit cards
     */
    public List<CreditCard> getAll(Integer personId);

    /**
     * Retrieves all credit cards
     */
    public List<CreditCard> getAll();

    /**
     * Retrieves a single credit card
     */
    public CreditCard get(Integer id);

    /**
     * Adds a new credit card
     */
    public void add(Integer personId, CreditCard creditCard);

    /**
     * Deletes an existing credit card
     */
    public void delete(Integer id);

    /**
     * Edits an existing credit card
     */
    public void edit(CreditCard creditCard);

}