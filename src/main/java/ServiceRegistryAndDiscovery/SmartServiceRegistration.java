package ServiceRegistryAndDiscovery;

import java.net.InetAddress;
import javax.jmdns.JmDNS;

/**
 *
 * @author Tsogzolmaa;
 */
public class SmartServiceRegistration {
    
    private static JmDNS jmdns;
    private static SmartServiceRegistration theRegister;
    
    private SmartServiceRegistration(){
        jmdns = JmDNS.create(InetAddress.getLocalHost());
    }
    
}
