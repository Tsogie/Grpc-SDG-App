package disys.storeMonitoring;

import grpc.generated.monitoring.MonitoringRequest;
import grpc.generated.monitoring.MonitoringResponse;
import grpc.generated.monitoring.StoreMonitoringServiceGrpc.StoreMonitoringServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Tsogzolmaa;
 */
public class StoreMonitoringServer extends StoreMonitoringServiceImplBase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        StoreMonitoringServer monitoringServer = new StoreMonitoringServer();
        int port = 50003;
        
        try{
            Server server = ServerBuilder.forPort(port)
                    .addService(monitoringServer)
                    .build()
                    .start();
            server.awaitTermination();
        
        }catch(IOException e){
        e.getMessage();
        
        }catch(InterruptedException e){
        e.getMessage();
        
        }
        
    }//main
    
    @Override 
    public void doMonitoring(MonitoringRequest request, StreamObserver<MonitoringResponse> responseObserver){
    
        String inRequest;
        inRequest = request.getSectionName();
        boolean isStockOk = true;
        String stockMessage;
        boolean anyUpdates = true;
        String updateMessage;
        if(inRequest.equalsIgnoreCase("Beverages")){
        
            if(!isStockOk){
                stockMessage = "Stock level low";
                MonitoringResponse response = MonitoringResponse.newBuilder().setStockLevelMessage(stockMessage).build();
                responseObserver.onNext(response);
            }
            if(anyUpdates){
                
                findAnyUpdate(inRequest);
            
            }
        }
    
    }
    
    public String findAnyUpdate(String sectionName){
    
        ArrayList<String> updateMessages = new ArrayList<>();
        
        updateMessages.add(sectionName + " recently restocked");
        updateMessages.add("Delivery recieved at " + sectionName + " section");
        updateMessages.add("New product PD938h89 added at " + sectionName + " section");
        updateMessages.add("Promotion available at" + sectionName + " section");
        updateMessages.add("Discount on product PD900h70: 20 % off today at" + sectionName + " section");
        updateMessages.add(sectionName + " recently restocked");
        
        
        return"";
    }
    
}//class
