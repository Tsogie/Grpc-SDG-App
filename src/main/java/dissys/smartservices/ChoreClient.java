package dissys.smartservices;


import grpc.generated.chore.ChoreDividerGrpc;
import grpc.generated.chore.ChoreDividerGrpc.ChoreDividerBlockingStub;
import grpc.generated.chore.ChoreDividerGrpc.ChoreDividerStub;
import grpc.generated.chore.ChoreRequest;
import grpc.generated.chore.ChoreResponse;
import grpc.generated.chore.ReportRequest;
import grpc.generated.chore.ReportResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.concurrent.RejectedExecutionException;
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
public class ChoreClient {
    
    private static final Logger logger = Logger.getLogger(ChoreClient.class.getName());
    //declared this stub static to use it in requestChoreDivide() method
    public static ChoreDividerBlockingStub stub;
    static JmDNS jmdns;
    private static boolean serviceResolved = false;
    //declared requestObserver as static, to use it on guiMainController class
    public static StreamObserver<ReportRequest> requestObserver;
    public static boolean streamIsCompleted;
    /**
     * Discovering service
     * using this method on GUI code, service will be discovered, channel will be built
     * stubs will be initialized
     * Additionally, for client streaming RPC, response observer and request observer 
     * will be set up
     * @param resultOutput - text area in gui for printing service information to user
     */
    public static void discoverAndStart(JTextArea resultOutput) throws UnknownHostException, IOException, InterruptedException{
        try{
            jmdns = JmDNS.create(InetAddress.getLocalHost());
            String serviceType = "_grpc._tcp.local.";
            String serviceName = "ChoreDivider";

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
                    //resultOutput.setText(LocalTime.now().toString() + "Service resolved");

                    String discoveredHost = serviceInfo.getHostAddresses()[0];
                    int discoveredPort = serviceInfo.getPort();
                    String inServiceName = serviceInfo.getName();

                    try{
                        if(inServiceName.equalsIgnoreCase(serviceName)){ 
                            serviceResolved = true;
                            resultOutput.append("\n"+serviceName + " service discovered at " + discoveredHost+ ":" + discoveredPort);
                            //once service is found, channel is created here at that port and host
                            ManagedChannel channel = ManagedChannelBuilder
                                    .forAddress(discoveredHost, discoveredPort)
                                    .usePlaintext()
                                    .build();
                            //initializing stub to make connection between server and client
                            //this stub is blocking stub for unary RPC
                            stub = ChoreDividerGrpc.newBlockingStub(channel);
                            //for doChoreReport service, assynchronous stub is used because, we are sending
                            //stream of request to server.
                            ChoreDividerStub stubAsync = ChoreDividerGrpc.newStub(channel);
                            resultOutput.append("\nChannel is ready now");
                        
                        //moved response observer code here, in order to get request observer
                        //from server when service resolved. So when service is ready, user enters 
                        //inputs, request observer can send them to server
                        //in order to get request observer, we need to send response observer. so first sets up
                        //new response observer here
                        StreamObserver<ReportResponse> responseObserver = 
                            new StreamObserver<ReportResponse>(){
                            @Override
                            public void onNext(ReportResponse v) {
                                resultOutput.setText("Response from server (Client streaming, Chore Report): " + v.getReportResult());
                            }                
                            @Override
                            public void onError(Throwable thrwbl) {
                                resultOutput.setText("Error occurred during stream: " + thrwbl.getMessage());
                                thrwbl.printStackTrace();            
                            }
                            @Override
                            public void onCompleted() {
                                streamIsCompleted = true;
                                resultOutput.append("\n" + LocalTime.now().toString() + "\nReport is completed");
                            }
                        };//response observer
       
        //here using stubAsync, method doChoreReport is triggers server. ResponseObserver,
        //we defined its behaviour in this client class, is sent to server as a parameter.
        //then we gets requestObserver
        //in this code, I declared it as public static StreamObserver<ReportRequest> requestObserver;
        //to use it on GUI code, when user enters number, new requests created, and onNext() on 
        //request observer, request will be sent to server

                        requestObserver = stubAsync.doChoreReport(responseObserver);

                        }//if(inServiceName.equalsIgnoreCase(serviceName))

                    }catch (RejectedExecutionException e){
                        e.printStackTrace();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }catch(IOException e){
            //message = "Exception occured " + e.getMessage();
            e.getMessage();
        }
        //return discoveryMessage;
    }//discoverAndStart method
 
    /**
    *UNARY
    *@param numPeople - user input from GUI 
    *@param resultOutput - text area in GUI 
    * requestChoreDivide() method will be called when user 
    * enter the number of people and press the enter button
    * function
    * when this method called, ChoreRequest will be created
    * through stub doChoreDivide method triggered on server
    * when server gets request, it sends back response
    */
    public static void requestChoreDivide(int numPeople, JTextArea resultOutput) throws Exception{

        try{
            //client creates request using newBuilder() instead of constructor and sets number here
            ChoreRequest request = ChoreRequest.newBuilder().setNumPeople(numPeople).build();
            //then calling doChoreDivide() method on server using stub. and gets back one response
            ChoreResponse response = stub.doChoderDivide(request); 
            //from that response gets chore result and prints it on text area
            resultOutput.append("Response from Server (Unary, Chore Divide)");
            resultOutput.append(response.getChoreResult());
            //logger.info(response.getChoreResult());

        }catch(StatusRuntimeException e){
            e.printStackTrace();
        }finally { 
            //commented out channel shut down to keep server alive
	    //channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}
        Thread.sleep(1000);   
    }
    

    }//class
    

