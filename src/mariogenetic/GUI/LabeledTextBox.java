/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogenetic.GUI;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import mariogenetic.Dbg;
import mariogenetic.Global;

/**
 *
 * @author alice
 */
public class LabeledTextBox extends JPanel{

    JLabel label;
    JTextField text;
    Global.Keys key;

    public LabeledTextBox(Global.Keys key, String valueText)
    {
        this(key.toString(),valueText);
        this.key = key;
    }

    public JLabel getJLabel() {
        return label;
    }

    public void setJLabel(JLabel label) {
        this.label = label;
    }

    public JTextField getText() {
        return text;
    }

    public void setText(JTextField text) {
        this.text = text;
    }
    
    public LabeledTextBox(String labelText, String valueText)
    {
        label = new JLabel(labelText);
        text = new JTextField(valueText);

        label.setSize(50, 50);
        label.setPreferredSize(new Dimension(80, 20));

        text.setSize(50, 50);
        text.setPreferredSize(new Dimension(80,20));

        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        this.add(label);
        this.add(text);
    }
    public String getLabel()
    {
        return label.getText();
    }
    public void setLabel(String text)
    {
        label.setText(text);
    }

    public String getValue()
    {
        return text.getText();
    }
    public void setValue(String value)
    {
        text.setText(value);
    }
    public Global.Keys getKey()
    {
        if(key==null)
        {
            Dbg.o("Error, key is not defined (LabeledTextBox)");
            return Global.Keys.NONE;
        }
        return key;
    }

}
