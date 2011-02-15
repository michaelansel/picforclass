package util.gui;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import util.reflection.MethodAction;


@SuppressWarnings("serial")
public class MethodButton extends JButton
{
    public MethodButton (ImageIcon image, String tooltip, Object target, String methodName)
    {
        super(image);
        setToolTipText(tooltip);
        init(target, methodName);
    }


    public MethodButton (String title, Object target, String methodName)
    {
        super(title);
        init(target, methodName);
    }


    protected void init (Object target, String name)
    {
        addActionListener(new MethodAction(target, name));
    }
}
