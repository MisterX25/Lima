package cpnv.jav1.limaEntities;

import java.util.Date;

/**
 * Created by Xavier on 07.05.15.
 */
public class Student extends Person
{
    //========================== Attributes =============================
    private int _startYear;

    //========================== Methods =============================
    public void setBirthDate(Date birthDate)
    {
        _birthDate = birthDate;
    }

    public String dump()
    {
        return super.dump()+" (volée "+getstartYear()+")";
    }

    //========================== getter/setters =============================

    public int getstartYear()
    {
        return _startYear;
    }

    public void setstartYear(int startYear)
    {
        this._startYear = startYear;
    }
}
