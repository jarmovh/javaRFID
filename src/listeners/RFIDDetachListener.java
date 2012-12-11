/* - RFIDTagLossListener -
 * This is derivative work from original code by Phidgets Inc. 
 * licensed under the Creative Commons Attribution 2.5 Canada License.
 */

package listeners;

import com.phidgets.RFIDPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.DetachListener;
import com.phidgets.event.DetachEvent;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class RFIDDetachListener implements DetachListener{
    
    private JFrame appFrame;
    private JTextArea logTextArea;
    
    /** Creates a new instance of RFIDDetachListener */
    public RFIDDetachListener(JFrame appFrame, JTextArea logTextArea)
    {
        this.appFrame = appFrame;
        this.logTextArea = logTextArea;
    }

    public void detached(DetachEvent de)
    {
        try
         {
            RFIDPhidget detached = (RFIDPhidget)de.getSource();
            logTextArea.append("Attached: "+Boolean.toString(detached.isAttached())+"\n");     
            detached.setAntennaOn(false);
        }
        catch (PhidgetException ex)
        {
            logTextArea.append("Phidget Error " + ex.getErrorNumber()+"\n");        
        }
    }
    
}
