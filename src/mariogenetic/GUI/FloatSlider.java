/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.GUI;

import javax.swing.JSlider;

/**
 *
 * @author alice
 */
public class FloatSlider extends JSlider{

    double max;    
    public FloatSlider(double max)
    {
        super(0, (int)(1000*max));
        this.max = max;
        this.setValue(0);
    }
    public void setValue(double d)
    {        
        this.setValue((int)(d*this.getMaximum()));
    }
    public double getFloatValue()
    {

        return ((double)this.getValue())/this.getMaximum();
    }

}
