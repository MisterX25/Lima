package cpnv.jav1.limaEntities;

/**
 * Created by Xavier on 07.05.15.
 */
public class ClassTeacher extends Teacher
{
    //========================== Attributes =============================
    private String _class;

    //========================== Constructor =============================
    public ClassTeacher (Teacher jules, String classname)
    {
        setfirstName(jules.getfirstName());
        setlastName(jules.getlastName());
        setBirthDate(jules.getbirthDate());
        setsection(jules.getsection());
        setclass(classname);
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

    public void setclass(String classname)
    {
        this._class = classname;
    }
}
