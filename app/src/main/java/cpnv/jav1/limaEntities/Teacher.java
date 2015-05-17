package cpnv.jav1.limaEntities;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;

import cpnv.jav1.lima.LimaException;

/**
 * Created by Xavier on 07.05.15.
 */
public class Teacher extends Person
{
    //========================== Constants =============================
    public final static int teacherMinAge=25; // constant

    //========================== Attributes =============================

    private String _section;

    //========================== Constructors =============================
    // default
    public Teacher()
    {
    }

    // basic
    public Teacher(String firstName, String lastName) throws LimaException
    {
        try {
            setfirstName(firstName);
            setlastName(lastName);
        }
        catch (LimaException le){
            throw new LimaException("Unable to create new teacher ("+firstName+" " + lastName+")");
        }
        catch (Exception e){
            Log.i ("LIMA","Exception: "+e.getMessage());
        }
    }

    // Copy constructor
    public Teacher (Teacher teacher)
    {
        try {
            setBirthDate(teacher.getbirthDate());
            setfirstName(teacher.getfirstName());
            setlastName(teacher.getlastName());
        }
        catch (LimaException le){
            // do nothing
        }
    }

    //========================== Methods =============================
    @Override
    public final void setBirthDate(Date birthDate) throws LimaException // final so that it cannot be derived further
    {
        Calendar now = Calendar.getInstance();
        Calendar dob = Calendar.getInstance();
        dob.setTime(birthDate);
        if (now.get(Calendar.YEAR) - dob.get(Calendar.YEAR) < teacherMinAge)
            throw new LimaException("Too young to be a teacher");
        else
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

    public void setsection(String section) throws LimaException
    {
        if (section.length() < 4)
            throw new LimaException("Illegal section name");
        else
            this._section = section;
    }

}
