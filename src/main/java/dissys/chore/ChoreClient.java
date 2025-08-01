package dissys.chore;


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
import java.util.concurrent.TimeUnit;
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
    public static ManagedChannel channel;
    public static ChoreDividerStub stubAsync;
    public static ChoreDividerBlockingStub stub;
    static JmDNS jmdns;
    static String message;
    public static void discoverAndStart() throws UnknownHostException, IOException, InterruptedException{
    
        jmdns = JmDNS.create(InetAddress.getLocalHost());
        String serviceType = "_grpc._tcp.local.";
        String serviceName = "ChoreDivider";
        
            jmdns.addServiceListener(serviceType, new ServiceListener(){
                //String discoveryMessage = "";
            @Override
            public void serviceAdded(ServiceEvent se) {
                System.out.println("Service added: " + se.getName());
                jmdns.requestServiceInfo(serviceType, serviceName, 1);
            }

            @Override
            public void serviceRemoved(ServiceEvent se) {
                System.out.println("Service removed: " + se.getName());
            }

            @Override
            public void serviceResolved(ServiceEvent se) {
                ServiceInfo serviceInfo = se.getInfo();
                System.out.println("Service resolved at Chore");
                
                String discoveredHost = serviceInfo.getHostAddresses()[0];
                int discoveredPort = serviceInfo.getPort();
                String inServiceName = serviceInfo.getName();
                
                try{
                    if(inServiceName.equalsIgnoreCase(serviceName)){                    
                        //requestChoreDivide(discoveredHost, discoveredPort);                   
                        System.out.println("Service discovered at port " + discoveredPort);
                        channel = ManagedChannelBuilder
                                .forAddress(discoveredHost, discoveredPort)
                                .usePlaintext()
                                .build();
                        stub = ChoreDividerGrpc.newBlockingStub(channel);
                        stubAsync = ChoreDividerGrpc.newStub(channel);
                        message = "Channel is built at port";
                        //Gui.serviceTextArea.setText("Channel is built at port");
                    }
   
                }catch (RejectedExecutionException e){
                    e.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
            Thread.sleep(30000);
        //return discoveryMessage;
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        
        Thread.sleep(30000);
    }//main
    
    public static void requestChoreDivide() throws Exception{

        try{

            ChoreRequest request = ChoreRequest.newBuilder().setNumPeople(4).build();

            ChoreResponse response = stub.doChoderDivide(request); //ene trigger hiij bgn boluu?

            System.out.println("Response from Server (Unary, Chore Divide)");
            logger.info(response.getChoreResult());

        }catch(StatusRuntimeException e){
            e.printStackTrace();
        }finally {         
	    //channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}
        Thread.sleep(10000);
        requestReport();
    
    }
    public static void requestReport() throws Exception{
    
        StreamObserver<ReportResponse> responseObserver = 
                new StreamObserver<ReportResponse>(){
            @Override
            public void onNext(ReportResponse v) {
                System.out.println("Response from server (Client streaming, Chore Report): " + v.getReportResult());            }

            @Override
            public void onError(Throwable thrwbl) {
                System.err.println("Error occurred during stream: " + thrwbl.getMessage());
                thrwbl.printStackTrace();            
            }

            @Override
            public void onCompleted() {
                System.out.println(LocalTime.now().toString() + "Report is completed");
            }
        };
        
        StreamObserver<ReportRequest> requestObserver = stubAsync.doChoreReport(responseObserver);
    
        try{
            requestObserver.onNext(ReportRequest.newBuilder().setCompletedTaskNum(11).build());
            Thread.sleep(500);
            requestObserver.onNext(ReportRequest.newBuilder().setCompletedTaskNum(1).build());
            Thread.sleep(500);
            requestObserver.onNext(ReportRequest.newBuilder().setCompletedTaskNum(6).build());
            Thread.sleep(500);
            requestObserver.onNext(ReportRequest.newBuilder().setCompletedTaskNum(3).build());
            Thread.sleep(500);
            requestObserver.onNext(ReportRequest.newBuilder().setCompletedTaskNum(4).build());
            Thread.sleep(500);
            requestObserver.onNext(ReportRequest.newBuilder().setCompletedTaskNum(10).build());
            Thread.sleep(500);

            requestObserver.onCompleted();

            Thread.sleep(10000);
        }catch(RuntimeException e){
        e.printStackTrace();
        }catch(InterruptedException e){
        e.printStackTrace();
        }finally {         
	    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}
    }

    }//class
    

