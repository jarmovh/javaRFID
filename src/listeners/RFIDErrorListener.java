/* - RFIDTagLossListener -
 * This is derivative work from original code by Phidgets Inc. 
 * licensed under the Creative Commons Attribution 2.5 Canada License.
 */

package listeners;

import com.phidgets.event.ErrorListener;
import com.phidgets.event.ErrorEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RFIDErrorListener implements ErrorListener{
    
    private JFrame appFrame;
    
    /** Creates a new instance of RFIDErrorListener */
    public RFIDErrorListener(JFrame appFrame)
    {
        this.appFrame = appFrame;
    }

    public void error(ErrorEvent errorEvent)
    {
        JOptionPane.showMessageDialog(appFrame, errorEvent.toString(), "RFID Error Event", JOptionPane.ERROR_MESSAGE);
    }
    
}
