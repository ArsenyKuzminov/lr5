/**
 * coordinates class, which sets location
 */
public class Coordinates {
    private Integer x; //<=484; !=null
    private Integer y; //!=null

    /**
     * constructor
     * @param x
     * @param y
     * @throws NullValueException, TooBigValueException
     */
    public Coordinates (Integer x, Integer y) throws NullValueException, TooBigValueException{
        setX(x);
        setY(y);
    }

    /**
     * x setter. throws exception if values are inappropriate
     * @param x
     * @throws TooBigValueException, NullValueException
     */
    public void setX(Integer x) throws TooBigValueException, NullValueException{
        if (x==null) throw new NullValueException("x can't be null.\n");
        else if (x>484) throw new TooBigValueException("x can't be bigger than 484.\n");
        else this.x=x;
    }

    /**
     * y setter. throws exception if values are inappropriate
     * @param y
     * @throws NullValueException
     */
    public void setY(Integer y) throws NullValueException{
        if (y==null) throw new NullValueException("y can't be null.\n");
        else this.y=y;
    }

    /**
     * x getter
     * @return x
     */
    public Integer getX() {
        return x;
    }

    /**
     * y getter
     * @return y
     */
    public Integer getY() {
        return y;
    }

    /**
     * print coordinates information
     * @return String
     */
    @Override
    public String toString() {
        return this.x+","+this.y;
    }
}