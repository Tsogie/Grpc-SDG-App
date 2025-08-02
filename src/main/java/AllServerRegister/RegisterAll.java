package AllServerRegister;

import ServiceRegistryAndDiscovery.SmartServiceRegistration;
import dissys.smartservices.ChoreServer;
import dissys.smartservices.FlightServer;
import dissys.smartservices.StoreMonitoringServer;
import java.io.IOException;

/**
 *
 * @author Tsogzolmaa;
 */
public class RegisterAll {

    /**
     * @param args the command line arguments
     */
    public static void regiterAllServers() {
        
        try{
            SmartServiceRegistration ssr = SmartServiceRegistration.getInstance();
            
            ssr.registerService("_grpc._tcp.local.", "StoreMonitoringService", 50003, "Grpc server streaming StoreMonitoringService service");

            System.out.println("registered50003");
            ssr.registerService("_grpc._tcp.local.", "FlightEmissionCalculator", 50002, "Grpc bi-di FlightEmissionCalculator service");
            System.out.println("registered50002");

            ssr.registerService("_grpc._tcp.local.", "ChoreDivider", 50001, "Grpc unary, client streaming ChoreDivider service");
            System.out.println("registered50001");

        }catch(IOException e){
        e.getMessage();
        }
    }
    
}
