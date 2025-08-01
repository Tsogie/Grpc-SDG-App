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
import java.time.LocalTime;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

/**
 *
 * @author Tsogzolmaa;
 */
public class ChoreClient {
    private static final Logger logger = Logger.getLogger(ChoreClient.class.getName());
    private static ManagedChannel channel;
    private static ChoreDividerStub stubAsync;
    static JmDNS jmdns;
    public static void main(String[] args) throws IOException, InterruptedException {
        
        jmdns = JmDNS.create(InetAddress.getLocalHost());
        String serviceType = "_grpc._tcp.local.";
        //int port = 50001;
        //String host = "localhost";
        String serviceName = "ChoreDivider";
        //jmdns.requestServiceInfo(serviceType, serviceName, 1);
           
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
                System.out.println("Service resolved at Chore");
                
                String discoveredHost = serviceInfo.getHostAddresses()[0];
                int discoveredPort = serviceInfo.getPort();
                String inServiceName = serviceInfo.getName();
                
                try{
                    if(inServiceName.equalsIgnoreCase(serviceName)){                    
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
        });

        
        Thread.sleep(30000);
    }//main
   
    public static void requestChoreDivide(String host, int port) throws Exception{
    
        channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        ChoreDividerBlockingStub stub = ChoreDividerGrpc.newBlockingStub(channel);
        stubAsync = ChoreDividerGrpc.newStub(channel);
        
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
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    

