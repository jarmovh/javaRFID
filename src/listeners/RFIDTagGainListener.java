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

import sqlite.*;

public class RFIDTagGainListener implements TagGainListener{
    
    private JTextField tagDataField;
    private JTextArea logTextArea;
    private JCheckBox tagAccessCheck;
    private sqlite sql;
    private String tagData;
    
    /** Creates a new instance of RFIDTagGainListener */
    public RFIDTagGainListener(JTextField tagDataField, JTextArea logTextArea, JCheckBox tagAccessCheck, sqlite sql)
    {
        this.tagDataField = tagDataField;
        this.logTextArea = logTextArea;
        this.tagAccessCheck = tagAccessCheck;
        this.sql = sql;
    }

    public void tagGained(TagGainEvent tagGainEvent)
    {
        try
        {
            RFIDPhidget rfid = (RFIDPhidget)tagGainEvent.getSource();
            rfid.setOutputState(0, false); //reset state for output 0
            rfid.setLEDOn(true);
            tagData = tagGainEvent.getValue();
            tagDataField.setText(tagData); 
            logTextArea.append("Tag entered " + tagData+"\n");
            tagAccessCheck.setEnabled(true);
            rfid.setOutputState(0, userAuthCheck(tagData)); //set state for output 0
        }
        catch (PhidgetException ex)
        {
            logTextArea.append("Phidget Error " + ex.getErrorNumber()+"\n");        
        }
    }
    
    public boolean userAuthCheck(String tag)
    {
       int accessState = sql.getAccessState(tag);
       if( accessState == 1 )
       {
           logTextArea.append("Access granted\n");
           tagAccessCheck.setSelected(true);
           return true;
       }
       else
       {
           logTextArea.append("Access denied\n");
           tagAccessCheck.setSelected(false);
           return false;
       }
    }
    
}
