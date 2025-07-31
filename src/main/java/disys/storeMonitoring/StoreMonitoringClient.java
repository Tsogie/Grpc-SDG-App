package disys.storeMonitoring;

import grpc.generated.monitoring.MonitoringRequest;
import grpc.generated.monitoring.MonitoringResponse;
import grpc.generated.monitoring.StoreMonitoringServiceGrpc;
import grpc.generated.monitoring.StoreMonitoringServiceGrpc.StoreMonitoringServiceStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 *
 * @author Tsogzolmaa;
 */
public class StoreMonitoringClient {

    /**
     * @param args the command line arguments
     */
    public static ManagedChannel channel;
    public static StoreMonitoringServiceStub stubAsync;
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        int port = 50003;
        String host = "localhost";
        channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        stubAsync = 
                StoreMonitoringServiceGrpc.newStub(channel);
        doStoreMonitoring();
        
    }
    public static void doStoreMonitoring() throws InterruptedException{
    
        try{
        MonitoringRequest request = MonitoringRequest
                .newBuilder().setSectionName("Beverages").build();
        
        StreamObserver<MonitoringResponse> responseObserver= new StreamObserver<MonitoringResponse>(){
            
            ArrayList<String> responseArray = new ArrayList<>();
            @Override
            public void onNext(MonitoringResponse v) {
                System.out.println("Response 1 from server " + v.getStockLevelMessage());
                System.out.println("Response 2 from server " + v.getUpdateMessage());
            }

            @Override
            public void onError(Throwable thrwbl) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void onCompleted() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

        };
        
        stubAsync.doMonitoring(request, responseObserver);
        
        }catch (StatusRuntimeException e) {
            e.getStatus();
        } finally {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }
    
        
    }//doStoreMonitoring
    
}//class
