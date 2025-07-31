package dissys.chore;

import grpc.generated.chore.ChoreDividerGrpc;
import grpc.generated.chore.ChoreDividerGrpc.ChoreDividerBlockingStub;
import grpc.generated.chore.ChoreRequest;
import grpc.generated.chore.ChoreResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 *
 * @author Tsogzolmaa;
 */
public class ChoreClient {
    private static final Logger logger = Logger.getLogger(ChoreClient.class.getName());

    public static void main(String[] args) throws Exception {
        int port = 50001;
        String host = "localhost";
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        ChoreDividerBlockingStub stub = ChoreDividerGrpc.newBlockingStub(channel);
    
      try{

        ChoreRequest request = ChoreRequest.newBuilder().setNumPeople(4).build();

        ChoreResponse response = stub.doChoderDivide(request); //ene trigger hiij bgn boluu?

        System.out.println("Response from Server");
        logger.info(response.getChoreResult());

        }catch(StatusRuntimeException e){
            e.printStackTrace();
        }finally {         
	    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	    }
      
      
    }//main

    }//class
    

