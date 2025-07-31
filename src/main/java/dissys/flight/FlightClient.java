package dissys.flight;

import grpc.generated.flight.CO2Request;
import grpc.generated.flight.CO2Response;
import grpc.generated.flight.FlightEmissionCalculatorGrpc;
import grpc.generated.flight.FlightEmissionCalculatorGrpc.FlightEmissionCalculatorStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;

/**
 *
 * @author Tsogzolmaa;
 */
public class FlightClient {

    /**
     * @param args the command line arguments
     */
    public static FlightEmissionCalculatorStub biStub;
    public static void main(String[] args) throws Exception  {
        // TODO code application logic here
        int port = 50002;
        String host = "localhost";
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        biStub = FlightEmissionCalculatorGrpc
                .newStub(channel);
        doFlightEmissionCalculation();
    }//main
    
    public static void doFlightEmissionCalculation(){
    
    StreamObserver<CO2Response> responseObserver = new StreamObserver<CO2Response>(){
        
        ArrayList<Double> responseArray = new ArrayList<>();
        
        @Override
        public void onNext(CO2Response v) {
            System.out.println("Response from Server: Total emission " + v.getTotalCO2());
            responseArray.add(v.getTotalCO2());
        }

        @Override
        public void onError(Throwable thrwbl) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void onCompleted() {
            System.out.println("Server stopped responding");
            System.out.println("All responses from server " + responseArray.toString());
            
        }

    };
    
    StreamObserver<CO2Request> requestObserver 
            = biStub.doEmissionCalculation(responseObserver);
    
    try{
    
        String startCity = "Ulaanbaatar";
        requestObserver.onNext(CO2Request.newBuilder().setStartCity(startCity).setNextCity("Dublin").build());
        Thread.sleep(500);
        requestObserver.onNext(CO2Request.newBuilder().setStartCity(startCity).setNextCity("London").build());
        Thread.sleep(500);
        
        requestObserver.onCompleted();
        Thread.sleep(10000);
    }
    catch (RuntimeException e) {
        e.printStackTrace();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    
    
    }//doFlightEmissionCalculation
    
}//class
