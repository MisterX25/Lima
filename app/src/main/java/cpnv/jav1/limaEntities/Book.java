package cpnv.jav1.limaEntities;

import cpnv.jav1.lima.LimaDb;
import cpnv.jav1.lima.LimaException;

/**
 * Created by Xavier on 03.05.15.
 */
public class Book extends Article implements Limable {
    //==========================   Attributes   =============================
    private long _ISBN;
    private String _author;

    //==========================   Constants   =============================
    private final String dburl = "http://192.168.0.51/";

    //==========================   Private variables   =============================
    LimaDb ldb = null; // database connection
    String query; // SQL statements

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
        if (author == null || author.length() < 3)
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
        if (ldb == null) ldb = new LimaDb(dburl); // open database connection if needed

        // Now insert data
        query = "INSERT INTO article (articlename, articlenumber, price) VALUES ('"+getName()+"','"+getNumber()+"',"+getPrice()+")";
        if (ldb.executeQuery(query) == 0) throw new LimaException("Error inserting article ("+query+")");

        // get last insert id
        ldb.executeQuery("Select idarticle as lid from article order by idarticle desc limit 1");
        ldb.moveNext();
        String lid = ldb.getField("lid");
        // Now insert the book details
        query = "INSERT INTO bookdetail (author, ISBN, fk_article) VALUES ('"+getAuthor()+"','"+getISBN()+"',"+lid+")";
        if (ldb.executeQuery(query) == 0)
        {
            // we must get rid of the article
            ldb.executeQuery("DELETE FROM article WHERE idarticle = " + lid);
            throw new LimaException("Error inserting book details ("+query+")");
        }
        else
        {
            _dbid = Integer.parseInt(lid); // article id is the id of the object
        }

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
        if (ldb == null) ldb = new LimaDb(dburl); // open database connection if needed

        if (getNumber() == null) throw new LimaException("Can't read undefined article");

        query = "Select idarticle, articlename, articlenumber, author, ISBN, price "+
                "FROM article INNER JOIN bookdetail ON fk_article=idarticle "+
                "WHERE articlenumber = " + getNumber() ;
        if (ldb.executeQuery(query) == 0) throw new LimaException("Nonexistent article number "+getNumber());

        ldb.moveNext(); // read first element
        _dbid = Integer.parseInt(ldb.getField("idarticle"));
        setName(ldb.getField("articlename"));
        setNumber(ldb.getField("articlenumber"));
        setAuthor(ldb.getField("author"));
        String isbn = ldb.getField("ISBN");
        if (isbn != null) setISBN(Long.parseLong(isbn));
        String price = ldb.getField("price");
        if (price != null) setPrice(Float.parseFloat(price));
    }

    /**
     * =======================================================================
     * Reads the first object of the table from the database
     * The record order is the physical one (ascending on id)
     * @throws LimaException
     * in case of:
     *  - Database error
     *  - No article in the database
     * =======================================================================
     */
    public void readFirst() throws LimaException
    {
        if (ldb == null) ldb = new LimaDb(dburl); // open database connection if needed

        query = "Select idarticle, articlename, articlenumber, author, ISBN, price "+
                "FROM article INNER JOIN bookdetail ON fk_article=idarticle "+
                "ORDER BY idarticle";
        if (ldb.executeQuery(query) == 0) throw new LimaException("No books found");

        ldb.moveNext(); // read first element
        _dbid = Integer.parseInt(ldb.getField("idarticle"));
        setName(ldb.getField("articlename"));
        setNumber(ldb.getField("articlenumber"));
        setAuthor(ldb.getField("author"));
        String isbn = ldb.getField("ISBN");
        if (isbn != null) setISBN(Long.parseLong(isbn));
        String price = ldb.getField("price");
        if (price != null) setPrice(Float.parseFloat(price));
    }

    /**
     * =======================================================================
     * Loads the object with the content of the next one in the database
     * The record order is the physical one (ascending on id)
     * @throws LimaException
     * in case of:
     *  - Database error
     *  - Passes end of table
     * =======================================================================
     */
    public void readNext() throws LimaException
    {
        if (ldb == null) ldb = new LimaDb(dburl); // open database connection if needed

        query = "Select idarticle, articlename, articlenumber, author, ISBN, price "+
                "FROM article INNER JOIN bookdetail ON fk_article=idarticle "+
                "WHERE idarticle > " + Integer.toString(_dbid) + " "+
                "ORDER BY idarticle";
        if (ldb.executeQuery(query) == 0) throw new LimaException("Passed end of table");

        ldb.moveNext(); // read first element
        _dbid = Integer.parseInt(ldb.getField("idarticle"));
        setName(ldb.getField("articlename"));
        setNumber(ldb.getField("articlenumber"));
        setAuthor(ldb.getField("author"));
        String isbn = ldb.getField("ISBN");
        if (isbn != null) setISBN(Long.parseLong(isbn));
        String price = ldb.getField("price");
        if (price != null) setPrice(Float.parseFloat(price));
    }

    /**
     * =======================================================================
     * Loads the object with the content of the previous one in the database
     * The record order is the physical one (ascending on id)
     * @throws LimaException
     * in case of:
     *  - Database error
     *  - Passes start of table
     * =======================================================================
     */
    public void readPrevious() throws LimaException
    {
        if (ldb == null) ldb = new LimaDb(dburl); // open database connection if needed

        query = "Select idarticle, articlename, articlenumber, author, ISBN, price "+
                "FROM article INNER JOIN bookdetail ON fk_article=idarticle "+
                "WHERE idarticle < " + Integer.toString(_dbid) + " "+
                "ORDER BY idarticle DESC";
        if (ldb.executeQuery(query) == 0) throw new LimaException("Passed beginning of table");

        ldb.moveNext(); // read first element
        _dbid = Integer.parseInt(ldb.getField("idarticle"));
        setName(ldb.getField("articlename"));
        setNumber(ldb.getField("articlenumber"));
        setAuthor(ldb.getField("author"));
        String isbn = ldb.getField("ISBN");
        if (isbn != null) setISBN(Long.parseLong(isbn));
        String price = ldb.getField("price");
        if (price != null) setPrice(Float.parseFloat(price));
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
        if (ldb == null) ldb = new LimaDb(dburl); // open database connection if needed
        // We need the bookdetail id
        query = "SELECT idbookdetail FROM bookdetail WHERE fk_article = " + _dbid ;
        if (ldb.executeQuery(query) == 0) throw new LimaException("Article not found ("+query+")");
        ldb.moveNext(); // read element
        String bid = ldb.getField("idbookdetail");

        // Update the article part
        query = "UPDATE article SET articlename = '"+getName()+"', articlenumber = '"+getNumber()+"', price = "+Double.toString(getPrice())+ " WHERE idarticle = " + _dbid ;
        ldb.executeQuery(query);
        // Update the book part
        query = "UPDATE bookdetail SET ISBN = '"+Long.toString(getISBN())+"', author = '"+getAuthor()+"' WHERE idbookdetail = " + bid ;
        ldb.executeQuery(query);
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
        if (ldb == null) ldb = new LimaDb(dburl); // open database connection if needed

        query = "DELETE FROM article WHERE idarticle = " + _dbid ;
        if (ldb.executeQuery(query) == 0) throw new LimaException("Article not found");
        try {
            readPrevious();
        } catch (LimaException le) {
            readFirst();
        }
    }

}
