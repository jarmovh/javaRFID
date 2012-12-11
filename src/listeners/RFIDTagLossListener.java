/* - RFIDTagLossListener -
 * This is derivative work from original code by Phidgets Inc. 
 * licensed under the Creative Commons Attribution 2.5 Canada License.
 */

package listeners;

import com.phidgets.RFIDPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.TagLossListener;
import com.phidgets.event.TagLossEvent;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class RFIDTagLossListener implements TagLossListener{
    
    private JTextField tagDataField;
    private JTextArea logTextArea;
    private JCheckBox tagAccessCheck;

    
    /** Creates a new instance of RFIDTagLossListener */
    public RFIDTagLossListener(JTextField tagDataField, JTextArea logTextArea, JCheckBox tagAccessCheck)
    {
        this.tagDataField = tagDataField;
        this.logTextArea = logTextArea;
        this.tagAccessCheck = tagAccessCheck;
    }

    public void tagLost(TagLossEvent tagLossEvent)
    {
        try
        {
            RFIDPhidget rfid = (RFIDPhidget)tagLossEvent.getSource();
            rfid.setLEDOn(false);
            tagDataField.setText("");         
            tagAccessCheck.setEnabled(false);
        }
        catch (PhidgetException ex)
        {
            logTextArea.append("Phidget Error " + ex.getErrorNumber()+"\n");        
        }
    }
    
}
