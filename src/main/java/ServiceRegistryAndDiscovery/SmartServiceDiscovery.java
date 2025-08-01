package ServiceRegistryAndDiscovery;

import static dissys.chore.ChoreClient.requestChoreDivide;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.RejectedExecutionException;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

/**
 *
 * @author Tsogzolmaa;
 */
public class SmartServiceDiscovery {

    /**
     * @param args the command line arguments
     */
    static JmDNS jmdns;
    private static class SmartServiceListener implements ServiceListener {

        @Override
            public void serviceAdded(ServiceEvent se) {
                System.out.println("Service added"); 
                //jmdns.requestServiceInfo(se.getType(), se.getName(), 1);
            }

            @Override
            public void serviceRemoved(ServiceEvent se) {
                System.out.println("Service removed");            
            }
            
            @Override
            public void serviceResolved(ServiceEvent se) {
                ServiceInfo serviceInfo = se.getInfo();
                System.out.println("Service resolved");
                
                String discoveredHost = serviceInfo.getHostAddresses()[0];
                int discoveredPort = serviceInfo.getPort();
                String serviceName = serviceInfo.getName();
                
                try{
                
                    if(serviceName.equalsIgnoreCase("ChoreDivider")){
                        requestChoreDivide(discoveredHost, discoveredPort);                   
                    }
                    
                }catch(IOException e){
                    e.printStackTrace();                
                }catch(InterruptedException e){
                    e.printStackTrace();
                }catch (RejectedExecutionException e){
                    e.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
    
    
    }//SmartServiceListener class
            
    public static void main(String[] args) throws InterruptedException {
        try{
            jmdns = JmDNS.create(InetAddress.getLocalHost());

            jmdns.addServiceListener("_grpc._tcp.local.", new SmartServiceListener());
            Thread.sleep(20000);
        
        }
        catch(IOException e)
        {
        e.getMessage();
        }
        
    }//main
    
}//class
