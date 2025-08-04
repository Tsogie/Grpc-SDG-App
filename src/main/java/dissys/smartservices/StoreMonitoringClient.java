package dissys.smartservices;

import grpc.generated.monitoring.MonitoringRequest;
import grpc.generated.monitoring.MonitoringResponse;
import grpc.generated.monitoring.StoreMonitoringServiceGrpc;
import grpc.generated.monitoring.StoreMonitoringServiceGrpc.StoreMonitoringServiceStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.JTextArea;

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
    //added this boolean, once service is resolved, at some point it keeps
    //resolving and sending same message, so now once service found, service listener will not find it again
    private static boolean serviceResolved = false;
    public static boolean streamIsCompleted;
    
    /**
     * Discovering service and start
     * @param resultOutput
     * @throws java.net.UnknownHostException
     * @throws java.lang.InterruptedException
     * function - this method will be triggered when user enter button START. 
     * after server publishes itself, client will discover that service by name and type
     * once service resolved, channel and stub will set up
     */
    public static void discoverAndStart(JTextArea resultOutput) throws UnknownHostException, IOException, InterruptedException{
    
        jmdns = JmDNS.create(InetAddress.getLocalHost());
        String serviceType = "_grpc._tcp.local.";
        String serviceName = "StoreMonitoringService";
        jmdns.addServiceListener(serviceType, new ServiceListener(){
            @Override
            public void serviceAdded(ServiceEvent se) {
                resultOutput.setText("Service added: " + se.getName());
                System.out.println("Service added: " + se.getName());
                jmdns.requestServiceInfo(serviceType, serviceName, 1);
            }

            @Override
            public void serviceRemoved(ServiceEvent se) {
                System.out.println("Service removed: " + se.getName());
            }

            @Override
            public void serviceResolved(ServiceEvent se) {
                if(serviceResolved){return;}
                ServiceInfo serviceInfo = se.getInfo();
                System.out.println("Service resolved at Monitoring");
                
                String discoveredHost = serviceInfo.getHostAddresses()[0];
                int discoveredPort = serviceInfo.getPort();
                String inServiceName = serviceInfo.getName();
                
                try{
                    if(inServiceName.equalsIgnoreCase(serviceName)){   
                        serviceResolved = true;
                        resultOutput.append("\n"+serviceName + " service discovered at " + discoveredHost+ ":" + discoveredPort);

                        channel = ManagedChannelBuilder
                                .forAddress(discoveredHost, discoveredPort)
                                .usePlaintext()
                                .build();
                        stubAsync = StoreMonitoringServiceGrpc.newStub(channel);
                        resultOutput.append("\nChannel is ready now");

                    }
   
                }catch (RejectedExecutionException e){
                    e.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    
    }
/**
 * SERVER STREAMING
 * doStoreMonitoring() method will be triggered by GUI code when user clicks button ENTER
 * from GUI code it gets MonitoringRequest type request (store section name) and text area
 * 
 * @param inRequest
 * 
 * @param resultOutput
 * @throws java.lang.InterruptedException
 * 
 * function - it sets up response observer, do method call on asynchronous stub
 * passes this inRequest and response observer to server
 * Response from server will come on onNext method of this response observer
 * when response comes, it prints that message to user resultOutput.append(v.getStockLevelMessage());
 * and stores responses to an array to display later when communication finishes
 */
    public static void doStoreMonitoring(MonitoringRequest inRequest, JTextArea resultOutput) throws InterruptedException{

        try{
        
         StreamObserver<MonitoringResponse> responseObserver= new StreamObserver<MonitoringResponse>(){
            
            ArrayList<String> responseArray = new ArrayList<>();
            @Override
            public void onNext(MonitoringResponse v) {
                resultOutput.append("\nResponse from server " );
                resultOutput.append(v.getStockLevelMessage());
                responseArray.add(v.getStockLevelMessage());
                //thread sleep 1000 to slow server responses
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StoreMonitoringClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void onError(Throwable thrwbl) {
                resultOutput.setText("Error occurred during stream: " + thrwbl.getMessage());
                thrwbl.printStackTrace();             }

            @Override
            public void onCompleted() {
                streamIsCompleted = true;
                resultOutput.append("\n");
                resultOutput.append("\nServer response(s) completed");
                resultOutput.append("\nRecieved response(s) : " + responseArray.toString());
            }

        };
        //using asynchronous stub calling method on server, sending inRequest which is passed in
        //from GUI side code and response observer
        stubAsync.doMonitoring(inRequest, responseObserver);
        Thread.sleep(500);
        
        }catch (StatusRuntimeException e) {
            e.getStatus();
        } finally {
            //if want to keep server alive comment this line out
            //channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }
    
        
    }//doStoreMonitoring
    
}//class
