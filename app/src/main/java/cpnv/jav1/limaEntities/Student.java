package cpnv.jav1.limaEntities;

import java.util.Calendar;
import java.util.Date;

import cpnv.jav1.lima.LimaException;

/**
 * Created by Xavier on 07.05.15.
 */
public class Student extends Person
{
    //========================== Constants =============================
    public final static int studentMinAge=15; // constant

    //========================== Attributes =============================
    private int _startYear;

    //========================== Methods =============================
    public void setBirthDate(Date birthDate) throws LimaException
    {
        Calendar now = Calendar.getInstance();
        Calendar dob = Calendar.getInstance();
        dob.setTime(birthDate);
        if (now.get(Calendar.YEAR) - dob.get(Calendar.YEAR) < studentMinAge)
            throw new LimaException("Too young to be a student");
        else
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
