package cpnv.jav1.limaEntities;

import cpnv.jav1.lima.LimaException;

/**
 * Created by Xavier on 31.05.15.
 */
public interface Limable
{

    /**
     * =======================================================================
     * Inserts the object in the database
     *
     * @return
     * The id of the record created
     *
     * @throws LimaException
     * =======================================================================
     */
    public int create() throws LimaException;

    /**
     * =======================================================================
     * Reads the object from the database
     * @param artnumber
     * The article number of the record to load
     * @throws LimaException
     * =======================================================================
     */
    public void read(String artnumber) throws LimaException;

    /**
     * =======================================================================
     * Updates the record
     * @throws LimaException
     * =======================================================================
     */
    public void update() throws LimaException;

    /**
     * =======================================================================
     * Deletes the record
     * @throws LimaException
     * =======================================================================
     */
    public void delete() throws LimaException;}
