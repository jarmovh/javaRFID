/*- RFIDTagGainListener -
 * This is derivative work from original code by Phidgets Inc. 
 * licensed under the Creative Commons Attribution 2.5 Canada License.
 */

package listeners;

import com.phidgets.RFIDPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.TagGainListener;
import com.phidgets.event.TagGainEvent;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class RFIDTagGainListener implements TagGainListener{
    
    private JTextField tagDataField;
    private JTextArea logTextArea;
    private JCheckBox tagAccessCheck;
    
    /** Creates a new instance of RFIDTagGainListener */
    public RFIDTagGainListener(JTextField tagDataField, JTextArea logTextArea, JCheckBox tagAccessCheck)
    {
        this.tagDataField = tagDataField;
        this.logTextArea = logTextArea;
        this.tagAccessCheck = tagAccessCheck;
    }

    public void tagGained(TagGainEvent tagGainEvent)
    {
        try
        {
            RFIDPhidget rfid = (RFIDPhidget)tagGainEvent.getSource();
            rfid.setLEDOn(true);
            tagDataField.setText(tagGainEvent.getValue()); 
            logTextArea.append("Tag entered " + tagGainEvent.getValue()+"\n");
            tagAccessCheck.setEnabled(true);

        }
        catch (PhidgetException ex)
        {
            logTextArea.append("Phidget Error " + ex.getErrorNumber()+"\n");        
        }
    }
    
}
