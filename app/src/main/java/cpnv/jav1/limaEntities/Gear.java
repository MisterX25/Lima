package cpnv.jav1.limaEntities;

/**
 * Created by Xavier on 03.05.15.
 */
public class Gear extends Article {

    //==========================   Attributes   =============================
    private int _size;

    //==========================  Constructors  =============================
    // default
    public Gear()
    {
        super(); // invoke Article's default constructor
        setSize("?");
    }

    // Constructor setting all members
    public Gear(String name, String number, String resp, String supplier, float price,
                float tVA, int stock, boolean obsolete, String size)
    {
        super(name, number, resp, supplier, price, tVA, stock, obsolete);
        setSize(size);
    }
    //========================== Public methods =============================

    public String dump()
    {
        return super.dump() + "-" + getSize();
    }

    //========================== getter/setter  =============================

    public String getSize()
    {
        String res = "?";
        switch (_size)
        {
            case 1: res="XS"; break;
            case 2: res="S"; break;
            case 3: res="M"; break;
            case 4: res="L"; break;
            case 5: res="XL"; break;
            case 6: res="XXL"; break;
        }
        return res;
    }

    public void setSize(String size)
    {
        _size = 0; // by default
        if (size.equals("XS")) _size = 1;
        if (size.equals("S")) _size = 2;
        if (size.equals("M")) _size = 3;
        if (size.equals("L")) _size = 4;
        if (size.equals("XL")) _size = 5;
        if (size.equals("XXL")) _size = 6;
    }

}
