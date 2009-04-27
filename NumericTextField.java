/*http://www.javaswing.net/numeric-number-only-jtextfield-in-swing-ready-made-code.html
  alapján*/

package Calc;

import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

public class NumericTextField extends JTextField {
  //Add other constructors as required. If you do,
  //be sure to call the "addFilter" method
  /*
  public NumericTextField(String text, int columns){
    super(text, columns);
    addFilter();
  }
   */

  //Add an instance of NumericDocumentFilter as a
  //document filter to the current text field
  protected void addFilter(){
    ((AbstractDocument)this.getDocument()).
       setDocumentFilter(new NumericDocumentFilter());
  }


  class NumericDocumentFilter extends DocumentFilter{
        @Override
      public void insertString(FilterBypass fb,
      int offset, String string, AttributeSet attr)
        throws BadLocationException {

      if(string == null) return;
      if(isStringNumeric(string, offset)){
        String szam = getDocument().getText(0, getDocument().getLength());
        if (!string.equals(".") && szam.equals("0"))
            fb.remove(0, getDocument().getLength());
        else
            super.insertString(fb, offset, string, attr);
      }
      else{
        Toolkit.getDefaultToolkit().beep();
      }
    }

    public void replace(FilterBypass fb, int offset,
      int length, String text, AttributeSet attrs)
        throws BadLocationException {
      if(text == null) return;
      if(isStringNumeric(text, offset)){
        String szam = getDocument().getText(0, getDocument().getLength());
        if (!text.equals(".") && szam.equals("0"))
            fb.replace(0, 1, text, attrs);
        else
            super.insertString(fb, getDocument().getEndPosition().getOffset()-1, text, attrs);
      }
      else{
        Toolkit.getDefaultToolkit().beep();
      }
    }

    private boolean isStringNumeric(String string, int offset){
        try {
            String szam = getDocument().getText(0, getDocument().getLength()).concat(string);
            if ((szam.matches("-?[0-9]*(\\.[0-9]*)?")) || // Lebegőpontos jelölés
                (szam.matches("-?[0-9]\\.[0-9]+E+|-[0-9]+"))) return true;  // Normálalak
        } catch (Exception E) {
            return false;  // Ide elméletileg nem jut el
        }
        return true;
    }

  }
}