package cpnv.jav1.limaEntities;

import cpnv.jav1.lima.LimaException;

/**
 * Created by Xavier on 07.05.15.
 */
public class ClassTeacher extends Teacher
{
    //========================== Attributes =============================
    private String _class;

    //========================== Constructor =============================
    public ClassTeacher (Teacher oneTeacher, String classname) throws LimaException
    {
        try {
            setfirstName(oneTeacher.getfirstName());
            setlastName(oneTeacher.getlastName());
            setBirthDate(oneTeacher.getbirthDate());
            setsection(oneTeacher.getsection());
            setclass(classname);
        }
        catch (LimaException le){
            throw new LimaException("Unable to create new class teacher ("+ oneTeacher.getfirstName()+" " + oneTeacher.getlastName()+", MC of "+classname+")");
        }
    }
    //========================== Methods =============================
    public String dump()
    {
        return getfirstName() + " " + getlastName()+ " (enseigne en "+getsection()+", MC de "+ _class +")";
    }

    //========================== getter/setters =============================
    public String getclass()
    {
        return _class;
    }

    public void setclass(String classname) throws LimaException
    {
        if (!classname.startsWith("SI-") || classname.length() < 6)
            throw new LimaException("illegal classname (" + classname + ")");
        else
            this._class = classname;
    }
}
