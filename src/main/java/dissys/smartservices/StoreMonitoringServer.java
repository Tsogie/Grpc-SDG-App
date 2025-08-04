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
  
    public static boolean serverStarted;
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
            serverStarted = true;
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
        boolean anyUpdates = true;
        boolean isSectionOpen = true;
        System.out.println("Server recieved message from client: " + request.getSectionName() );
        
        //if(isStockOk), (anyUpdates), (isSectionOpen) boolean will trigger make new response and send it to client
        //so server will streams three times to client and streaming is done, it will onCompleted on response observer
        //and on client side, if onCompleted called on response observer, all the responses will be printed at the end.
        if(isSectionOpen){
            //using isSectionOpen (String sectionName) method, server will get random status of 
            //that section
            MonitoringResponse response = MonitoringResponse.newBuilder().setStockLevelMessage(isSectionOpen(inRequest)).build();
            responseObserver.onNext(response);
        }
        if(isStockOk){
            //using isStockOk (String sectionName) this method, server will get String info about stack randomly
            //send that response back to client first
            MonitoringResponse response = MonitoringResponse.newBuilder().setStockLevelMessage(isStockOk(inRequest)).build();
            responseObserver.onNext(response);
        }
        if(anyUpdates){
            //using findAnyUpdate(String sectionName), server will get 2 random updates as one string
            //and will send that to client lastly
            MonitoringResponse response = MonitoringResponse.newBuilder().setStockLevelMessage(findAnyUpdate(inRequest)).build();
            responseObserver.onNext(response);

        }
        responseObserver.onCompleted();
        
    
    }
    
      public String isSectionOpen (String sectionName){
    
        //this random code will generate random num 1-3
        //depending on number, we will get status of that section
        int randomNum = (int)(Math.random() * 3);
        if(randomNum == 1){
        return "\n" + sectionName + " is open";
        }else if(randomNum == 2){
        return "\n" + sectionName + " is closed";
        }else{
        return "\n" + sectionName + " is opening in 10 minutes";
        }
        
    
    }
    public String isStockOk (String sectionName){
    
        //this random code will generate random num 1-3
        //depending on number, we will assign stock level
        int randomNum = (int)(Math.random() * 3);
        if(randomNum == 1){
        return "\nStock level is low at " + sectionName + " section";
        }else if(randomNum == 2){
        return "\nStock level is full at " + sectionName + " section";
        }else{
        return "\nStock level informational is not found at " + sectionName + " section";
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
