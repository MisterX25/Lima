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
     * @throws LimaException
     * in case of:
     *  - Database error
     *  - Article number is null or empty
     *  - Article number already exists
     * =======================================================================
     */
    public void create() throws LimaException;

    /**
     * =======================================================================
     * Reads a specific object from the database
     * The article number must be set before calling this method
     * @throws LimaException
     * in case of:
     *  - Database error
     *  - Article number is null or empty
     *  - Article number does not exist
     * =======================================================================
     */
    public void read() throws LimaException;

    /**
     * =======================================================================
     * Reads the first object of the table from the database
     * @throws LimaException
     * in case of:
     *  - Database error
     *  - No article in the database
     * =======================================================================
     */
    public void readFirst() throws LimaException;

    /**
     * =======================================================================
     * Loads the object with the content of the next one in the database
     * @throws LimaException
     * in case of:
     *  - Database error
     *  - Passes end of table
     * =======================================================================
     */
    public void readNext() throws LimaException;

    /**
     * =======================================================================
     * Loads the object with the content of the previous one in the database
     * @throws LimaException
     * in case of:
     *  - Database error
     *  - Passes start of table
     * =======================================================================
     */
    public void readPrevious() throws LimaException;

    /**
     * =======================================================================
     * Updates the record
     * @throws LimaException
     * in case of:
     *  - Database error
     *  - Article number is null or empty
     *  - Article number does not exist
     * =======================================================================
     */
    public void update() throws LimaException;

    /**
     * =======================================================================
     * Deletes the record
     * @throws LimaException
     * in case of:
     *  - Database error
     *  - Article number is null or empty
     *  - Article number does not exist
     * =======================================================================
     */
    public void delete() throws LimaException;

}
