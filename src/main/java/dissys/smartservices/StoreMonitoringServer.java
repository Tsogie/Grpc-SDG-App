package dissys.smartservices;

import ServiceRegistryAndDiscovery.SmartServiceRegistration;
import grpc.generated.monitoring.MonitoringRequest;
import grpc.generated.monitoring.MonitoringResponse;
import grpc.generated.monitoring.StoreMonitoringServiceGrpc.StoreMonitoringServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Tsogzolmaa;
 */
public class StoreMonitoringServer extends StoreMonitoringServiceImplBase {

    /**
     * @param args the command line arguments
     */
  
    //Trigger method to start server from GUI
    public void startServer(){
    
    StoreMonitoringServer monitoringServer = new StoreMonitoringServer();
        int port = 50003;
        try{
            
            Server server = ServerBuilder.forPort(port)
                    .addService(monitoringServer)
                    .build()
                    .start();
            System.out.println("Server started on port: " + port);
        
            SmartServiceRegistration ssr = SmartServiceRegistration.getInstance();
            System.out.println("Created instance of SmartServiceRegistration for StoreMonitoringService ");
            ssr.registerService("_grpc._tcp.local.", "StoreMonitoringService", port, "Grpc server streaming StoreMonitoringService service");
            System.out.println("Service registering");
            server.awaitTermination();
        
        }catch(IOException e){
        e.getMessage();
        
        }catch(InterruptedException e){
        e.getMessage();
        
        }
    }
    /**
     * SERVER STREAMING RPC for monitoring certain store section. 
     * rpc doMonitoring (MonitoringRequest) returns (stream MonitoringResponse){} 
     * user enters store section name as an input. server will check any updates
     * and sends one by one - stream MonitoringResponse.
     * @param request - it is coming request from client
     * @param responseObserver - it is where server will send response
     */
    @Override 
    public void doMonitoring(MonitoringRequest request, StreamObserver<MonitoringResponse> responseObserver){
    
        String inRequest;
        inRequest = request.getSectionName();
        boolean isStockOk = true;
        String stockMessage;
        boolean anyUpdates = true;
        String updateMessage;
        System.out.println("Server recieved message from client: " + request.getSectionName() );
        if(inRequest.equalsIgnoreCase("Beverages")){
        
            if(isStockOk){
                stockMessage = "Stock level low";
                MonitoringResponse response = MonitoringResponse.newBuilder().setStockLevelMessage(stockMessage).build();
                responseObserver.onNext(response);
            }
            if(anyUpdates){
                
                MonitoringResponse response = MonitoringResponse.newBuilder().setStockLevelMessage(findAnyUpdate(inRequest)).build();
                responseObserver.onNext(response);
     
            }
            responseObserver.onCompleted();
        }
    
    }
    
    public String findAnyUpdate(String sectionName){
    
        ArrayList<String> updateMessages = new ArrayList<>();
        
        updateMessages.add("\n" + sectionName + " recently restocked");
        updateMessages.add("\nDelivery recieved at " + sectionName + " section");
        updateMessages.add("\nNew product PD938h89 added at " + sectionName + " section");
        updateMessages.add("\nPromotion available at " + sectionName + " section");
        updateMessages.add("\nDiscount on product PD900h70: 20 % off today at " + sectionName + " section");
        updateMessages.add("\nNew shipment just arrived at " + sectionName + " section");
        
        Collections.shuffle(updateMessages);
        
        return updateMessages.get(0) + updateMessages.get(2);
    }
    
}//class
