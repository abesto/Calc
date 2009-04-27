/**
 * @author Nagy Zoltán
 */

package Calc;

import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;

/**
 *
 * @author abesto
 */
public class SzamMezo extends NumericTextField {
    public SzamMezo() {
        setText("0");
        super.addFilter();
    }

    public void szamGomb(String n) {
        if (getText().equals("0") || getText().matches(".*?[^0-9.]+.*"))
            setText(n);
        else
            try {
                getDocument().insertString(getDocument().getLength(), n, new SimpleAttributeSet());
            } catch (BadLocationException e) {}
    }

    public void szam(String text) {
        if (text.length() == 0)
            torol();
        else {
            try {
                getDocument().remove(0, getDocument().getLength());
                getDocument().insertString(getDocument().getStartPosition().getOffset(), text, new SimpleAttributeSet());
            } catch (BadLocationException e) {}
        }
    }

    public void szam(Float n) {
        Integer kerek = Math.round(n);
        if (n.equals(new Float(kerek)))
            szam(String.valueOf(kerek));
        else
            szam(String.valueOf(n));
    }

    public void torol() {
        try {
            getDocument().remove(0, getDocument().getLength());
            getDocument().insertString(getDocument().getStartPosition().getOffset(), "0", new SimpleAttributeSet());
        } catch (BadLocationException e) {}
    }

    public void vissza() {
        try {
            getDocument().remove(getDocument().getEndPosition().getOffset()-2, 1);
        } catch (BadLocationException e) {}
        if (getText().length() == 0) torol();
    }

    public float getSzam() {
        try {
            return Float.valueOf(getText());
        } catch (java.lang.NumberFormatException e) {
            return new Float(0);
        }
    }
}
