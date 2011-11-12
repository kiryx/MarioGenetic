/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic;

/**
 *
 * @author alice
 */
public class Vector {

	public double x, y;
	public Vector(double X, double Y)
	{
		x= X;y=Y;
	}
        public Vector(Vector v)
        {
            x = v.x;
            y = v.y;
        }
	public Vector add(Vector v)
	{
		return new Vector(x+v.x , y+v.y);

	}
        public String toString()
        {
            return String.valueOf(x)+" "+String.valueOf(y);
        }
}
