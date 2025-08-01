package ServiceRegistryAndDiscovery;

import java.io.IOException;
import java.net.InetAddress;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

/**
 *
 * @author Tsogzolmaa;
 */
public class SmartServiceRegistration {
    
    private static JmDNS jmdns;
    private static SmartServiceRegistration theRegister;
    
    private SmartServiceRegistration() throws IOException{
        
        try{
            jmdns = JmDNS.create(InetAddress.getLocalHost());
        }catch(IOException e){
            System.out.println("Error happened. could not create instance of JmDNS" + e.getMessage());
        }
    }
    
    //create instance of SmartServiceRegistration
    public static SmartServiceRegistration getInstance() throws IOException{
        //making only one instance of it
        if (theRegister == null) {
            theRegister = new SmartServiceRegistration();
        }
        return theRegister;
    
    }
    //method server to use to register its service by passing details of service
    public static void registerService(String type, String serviceName, int port, String message) throws IOException{
        ServiceInfo serviceInfo = ServiceInfo.create(type, serviceName, port, message);
        jmdns.registerService(serviceInfo);
        System.out.println("Service registered " + serviceInfo);
    }

    
}
