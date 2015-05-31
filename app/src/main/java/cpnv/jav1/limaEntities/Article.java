package cpnv.jav1.limaEntities;

/**
 * Created by Xavier on 26.04.15.
 */
public class Article
{
    //========================== Constants =============================
    public final static String tbd="(à définir)"; // constant

    //========================== Attributes =============================
    private String _name;         // Article name
    private String _number;       // Article number
    private String _resp;         // Responsible person
    private String _supplier;     // Who we buy the article from
    private float _price;         // The price we pay when we buy it
    private float _TVA;           // VAT
    private int _stock;           // How many are presently in stock
    private boolean _obsolete;    // Still in use or not
    protected int _dbid;          // database record id

    //========================== Constructors =============================
    // default
    public Article()
    {
        setName(tbd);          // Use setters
        setNumber(tbd);
        setResp(tbd);
        setObsolete(false);
        setPrice(0);
        setStock(0);
        setSupplier(tbd);
        setTVA(0);
    }

    // Constructor setting all members
    public Article(String name, String number, String resp, String supplier, float price,
                   float tVA, int stock, boolean obsolete)
    {
        setName(name);          // Use setters
        setNumber(number);
        setResp(resp);
        setObsolete(obsolete);
        setPrice(price);
        setStock(stock);
        setSupplier(supplier);
        setTVA(tVA);
    }

    //========================== Public methods =============================
    // Returns a string format of the object's content
    public String dump()
    {
        return _name + "-" + _number + "-" + _resp + "-" + _supplier + "-" +
               _price + "-" + _TVA + "-" + _stock + "-" + _obsolete;
    }

    //========================== getter/setter =============================
    public String getName()
    {
        return _name;
    }

    public void setName(String name)
    {
        if (name == null || name.length() < 3)
            _name=tbd;
        else
            _name = name;
    }

    public String getNumber()
    {
        return _number;
    }

    public void setNumber(String number)
    {
        if (number == null || number.length() < 3)
            _number=tbd;
        else
            _number = number;
    }

    public String getResp()
    {
        return _resp;
    }

    public void setResp(String resp)
    {
        if (resp == null || resp.length() < 3)
            _resp = tbd;
        else
            _resp = resp;
    }

    public String getSupplier()
    {
        return _supplier;
    }

    public void setSupplier(String supplier)
    {
        if (supplier == null || supplier.length() < 3)
            _supplier = tbd;
        else
            _supplier = supplier;
    }

    public double getPrice()
    {
        return _price;
    }

    public void setPrice(float price)
    {
        if (price < 0.0f)
            _price = 0.0f;
        else
            _price = price;
    }

    public float getTVA()
    {
        return _TVA;
    }

    public void setTVA(float tVA)
    {
        if (tVA < 0.0f)
            _TVA = 0.0f;
        else
            _TVA = tVA;
    }

    public int getStock()
    {
        return _stock;
    }

    public void setStock(int stock)
    {
        if (stock < 0)
            _stock = 0;
        else
            _stock = stock;
    }

    public boolean getObsolete()
    {
        return _obsolete;
    }

    public void setObsolete(boolean obsolete)
    {
        _obsolete = obsolete;
    }

    public int getId()
    {
        return _dbid;
    }

}
