package cpnv.jav1.limaEntities;

/**
 * Created by Xavier on 03.05.15.
 */
public class Gear extends Article {

    public static enum Sizes {U, XS, S, M, L, XL, XXL}

    //==========================   Attributes   =============================
    private Sizes _size;

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
        if (_size == Sizes.U)
            return "?";
        else
            return _size.name();
    }

    public void setSize(String size)
    {
        _size = Sizes.U; // by default
        for (Sizes s: Sizes.values())
            if (size.equals(s.name()))
                _size = s;
    }

}
