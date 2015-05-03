package cpnv.jav1.limaEntities;

/**
 * Created by Xavier on 26.04.15.
 */
public class Article
{
    private String _name;         // Article name
    private String _number;       // Article number
    private String _supplier;     // Who we buy the article from
    private float _price;         // The price we pay when we buy it
    private float _TVA;           // VAT
    private int _stock;           // How many are presently in stock
    private boolean _obsolete;    // Still in use or not

    // Constructor setting all members
    public Article(String name, String number, String supplier, float price,
                   float tVA, int stock, boolean obsolete)
    {
        setName(name);          // Use setters
        setNumber(number);
        setObsolete(obsolete);
        setPrice(price);
        setStock(stock);
        setSupplier(supplier);
        setTVA(tVA);
    }

    // Returns a string format of the object's content
    public String dump()
    {
        return "name : " + _name + "\n number : " + _number
                + "\n supplier : " + _supplier + "\n price : " + _price
                + "\n TVA : " + _TVA + "\n stock : " + _stock
                + "\n obsolete : " + _obsolete;
    }

    // getters / setters
    public String getName()
    {
        return _name;
    }

    public void setName(String name)
    {
        _name = name;
    }

    public String getNumber()
    {
        return _number;
    }

    public void setNumber(String number)
    {
        _number = number;
    }

    public String getSupplier()
    {
        return _supplier;
    }

    public void setSupplier(String supplier)
    {
        _supplier = supplier;
    }

    public double getPrice()
    {
        return _price;
    }

    public void setPrice(float price)
    {
        _price = price;
    }

    public float getTVA()
    {
        return _TVA;
    }

    public void setTVA(float tVA)
    {
        _TVA = tVA;
    }

    public int getStock()
    {
        return _stock;
    }

    public void setStock(int stock)
    {
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


}
