/* - RFIDTagLossListener -
 * This is derivative work from original code by Phidgets Inc. 
 * licensed under the Creative Commons Attribution 2.5 Canada License.
 */

package listeners;

import com.phidgets.RFIDPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.AttachListener;
import com.phidgets.event.AttachEvent;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

public class RFIDAttachListener implements AttachListener{
    
    private JFrame appFrame;
    private JTextArea logTextArea;
    
    /** Creates a new instance of RFIDAttachListener */
    public RFIDAttachListener(JFrame appFrame, JTextArea logTextArea)
    {
        this.appFrame = appFrame;
        this.logTextArea = logTextArea;
    }

    public void attached(AttachEvent ae)
    {
        try
        {
            RFIDPhidget attached = (RFIDPhidget)ae.getSource();
            logTextArea.append("Attached: "+Boolean.toString(attached.isAttached())+"\n");   
            attached.setAntennaOn(true);
        }
        catch (PhidgetException ex)
        {
            logTextArea.append("Phidget Error " + ex.getErrorNumber()+"\n");        
        }
    }
    
}
