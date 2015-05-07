package cpnv.jav1.limaEntities;

import java.util.Date;

/**
 * Created by Xavier on 07.05.15.
 */
public class Teacher extends Person
{
    //========================== Attributes =============================

    private String _section;

    //========================== Constructors =============================
    // default
    public Teacher()
    {
    }

    // basic
    public Teacher(String firstName, String lastName)
    {
        setfirstName(firstName);
        setlastName(lastName);
    }

    // Copy constructor
    public Teacher (Teacher teacher)
    {
        setBirthDate(teacher.getbirthDate());
        setfirstName(teacher.getfirstName());
        setlastName(teacher.getlastName());
    }
    //========================== Methods =============================
    @Override
    public final void setBirthDate(Date birthDate) // final so that it cannot be derived further
    {
        _birthDate = birthDate;
    }

    public String dump()
    {
        return super.dump()+ " (enseigne en "+getsection()+")";
    }

    //========================== getter/setters =============================
    public String getsection()
    {
        return _section;
    }

    public void setsection(String section)
    {
        this._section = section;
    }

}
