package disys.storeMonitoring;

import grpc.generated.monitoring.MonitoringRequest;
import grpc.generated.monitoring.MonitoringResponse;
import grpc.generated.monitoring.StoreMonitoringServiceGrpc;
import grpc.generated.monitoring.StoreMonitoringServiceGrpc.StoreMonitoringServiceStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

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
    static JmDNS jmdns;
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
       jmdns = JmDNS.create(InetAddress.getLocalHost());
        String serviceType = "_grpc._tcp.local.";
        String serviceName = "StoreMonitoringService";
           
        jmdns.addServiceListener(serviceType, new ServiceListener(){
            @Override
            public void serviceAdded(ServiceEvent se) {
                System.out.println("Service added: " + se.getName());
            }

            @Override
            public void serviceRemoved(ServiceEvent se) {
                System.out.println("Service removed: " + se.getName());
            }

            @Override
            public void serviceResolved(ServiceEvent se) {
                ServiceInfo serviceInfo = se.getInfo();
                System.out.println("Service resolved at Monitoring");
                
                String discoveredHost = serviceInfo.getHostAddresses()[0];
                int discoveredPort = serviceInfo.getPort();
                String inServiceName = serviceInfo.getName();
                
                try{
                    if(inServiceName.equalsIgnoreCase(serviceName)){                    
                        doStoreMonitoring(discoveredHost, discoveredPort);                   
                    }
   
                }catch(InterruptedException e){
                    e.printStackTrace();
                }catch (RejectedExecutionException e){
                    e.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
 
    }
    public static void doStoreMonitoring(String host, int port) throws InterruptedException{
    
        channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        stubAsync = 
                StoreMonitoringServiceGrpc.newStub(channel);
        
        try{
        MonitoringRequest request = MonitoringRequest
                .newBuilder().setSectionName("Beverages").build();
        
        StreamObserver<MonitoringResponse> responseObserver= new StreamObserver<MonitoringResponse>(){
            
            ArrayList<String> responseArray = new ArrayList<>();
            @Override
            public void onNext(MonitoringResponse v) {
                System.out.println("Response from server " + v.getStockLevelMessage());
                responseArray.add(v.getStockLevelMessage());
            }

            @Override
            public void onError(Throwable thrwbl) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void onCompleted() {
                System.out.println("Server response(s) completed");
                System.out.println("Recieved response(s) : " + responseArray.toString());
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
