package cpnv.jav1.limaEntities;

/**
 * Created by Xavier on 03.05.15.
 */
public class Book extends Article {
    //==========================   Attributes   =============================
    private long _ISBN;
    private String _author;

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
}
