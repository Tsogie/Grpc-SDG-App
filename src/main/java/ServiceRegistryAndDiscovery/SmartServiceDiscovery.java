package ServiceRegistryAndDiscovery;

import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;

/**
 *
 * @author Tsogzolmaa;
 */
public class SmartServiceDiscovery {

    /**
     * @param args the command line arguments
     */
    private static class SmartServiceListener implements ServiceListener {

        @Override
        public void serviceAdded(ServiceEvent se) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void serviceRemoved(ServiceEvent se) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void serviceResolved(ServiceEvent se) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    
    
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
