package cpnv.jav1.limaEntities;

import cpnv.jav1.lima.LimaException;

/**
 * Created by Xavier on 03.05.15.
 */
public class Book extends Article implements Limable {
    //==========================   Attributes   =============================
    private long _ISBN;
    private String _author;

    //==========================   Private variables   =============================
    private int dbid; // database record id

    //==========================  Constructors  =============================
    // default
    public Book()
    {
        super(); // invoke Article's default constructor
        setISBN(0);
        setAuthor(super.tbd);
    }

    // Constructor setting all members
    public Book(String name, String number, String resp, String supplier, float price,
                   float tVA, int stock, boolean obsolete, String author, long ISBN)
    {
        super(name, number, resp, supplier, price, tVA, stock, obsolete);
        setISBN(ISBN);
        setAuthor(author);
    }
    //========================== Public methods =============================

    public String dump()
    {
        return super.dump() + "-" + _author + "-" + _ISBN;
    }

    //========================== getter/setter  =============================

    public long getISBN()
    {
        return _ISBN;
    }

    public void setISBN(long ISBN)
    {
        if (ISBN < 0)
            _ISBN = 0;
        else
            _ISBN = ISBN;
    }

    public String getAuthor()
    {
        return _author;
    }

    public void setAuthor (String author)
    {
        if (author.length() < 3)
            _author = super.tbd;
        else
            _author = author;
    }

    //========================== Limable interface  =============================

    /**
     * =======================================================================
     * Inserts the object in the database
     *
     * @throws LimaException
     * in case of:
     *  - Database error
     *  - Article number is null or empty
     *  - Article number already exists
     * =======================================================================
     */
    public void create() throws LimaException
    {

    }

    /**
     * =======================================================================
     * Reads the object from the database
     * The article number must be set before calling this method
     * @throws LimaException
     * in case of:
     *  - Database error
     *  - Article number is null or empty
     *  - Article number does not exist
     * =======================================================================
     */
    public void read() throws LimaException
    {

    }

    /**
     * =======================================================================
     * Updates the record
     * @throws LimaException
     * in case of:
     *  - Database error
     *  - Article number is null or empty
     *  - Article number does not exist
     * =======================================================================
     */
    public void update() throws LimaException
    {

    }

    /**
     * =======================================================================
     * Deletes the record
     * @throws LimaException
     * in case of:
     *  - Database error
     *  - Article number is null or empty
     *  - Article number does not exist
     * =======================================================================
     */
    public void delete() throws LimaException
    {

    }

}
