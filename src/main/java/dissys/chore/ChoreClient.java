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
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 *
 * @author Tsogzolmaa;
 */
public class ChoreClient {
    private static final Logger logger = Logger.getLogger(ChoreClient.class.getName());
    private static ManagedChannel channel;
    private static ChoreDividerStub stubAsync;
    public static void main(String[] args) throws Exception {
        
        int port = 50001;
        String host = "localhost";
        channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        ChoreDividerBlockingStub stub = ChoreDividerGrpc.newBlockingStub(channel);
        stubAsync = ChoreDividerGrpc.newStub(channel);
        
        try{

            ChoreRequest request = ChoreRequest.newBuilder().setNumPeople(2).build();

            ChoreResponse response = stub.doChoderDivide(request); //ene trigger hiij bgn boluu?

            System.out.println("Response from Server (Unary, Chore Divide)");
            logger.info(response.getChoreResult());

        }catch(StatusRuntimeException e){
            e.printStackTrace();
        }finally {         
	    //channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}
        
      //this method is for client streaming
      requestReport();
      
    }//main
    
    public static void requestReport() throws InterruptedException{
    
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
    

