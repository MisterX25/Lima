package cpnv.jav1.limaEntities;

import java.util.Date;

/**
 * Created by Xavier on 07.05.15.
 */
public abstract class Person {

    //========================== Attributes =============================
    protected String _lastName;
    protected String _firstName;
    protected Date _birthDate; // setter is virtual -> subclass must access this attribute

    //========================== Constructor =============================

    //========================== Public methods =============================

    public String dump()
    {
        return getfirstName() + " " + getlastName();
    }

    public abstract void setBirthDate(Date birthDate); // pure virtual

    //========================== getter/setter =============================
    public String getlastName()
    {
        return _lastName;
    }

    public void setlastName(String lastName)
    {
        this._lastName = lastName.toUpperCase(); // requirement: lastname in uppercase
    }

    public String getfirstName()
    {
        return _firstName;
    }

    public void setfirstName(String firstName)
    {
        this._firstName = firstName.substring(0,1).toUpperCase()+firstName.substring(1); // requirement: first letter capitalized
    }

    public Date getbirthDate()
    {
        return _birthDate;
    }

}
