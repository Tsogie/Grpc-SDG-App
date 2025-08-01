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
            System.out.println("Service added");
        }

        @Override
        public void serviceRemoved(ServiceEvent se) {
            System.out.println("Service removed");
        }

        @Override
        public void serviceResolved(ServiceEvent se) {
            System.out.println("Service resolved");
        }
    
    
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
