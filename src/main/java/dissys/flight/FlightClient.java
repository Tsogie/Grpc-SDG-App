package dissys.flight;

import grpc.generated.flight.CO2Request;
import grpc.generated.flight.CO2Response;
import grpc.generated.flight.FlightEmissionCalculatorGrpc;
import grpc.generated.flight.FlightEmissionCalculatorGrpc.FlightEmissionCalculatorStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
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
public class FlightClient {

    /**
     * @param args the command line arguments
     */
    private static ManagedChannel channel;
    public static FlightEmissionCalculatorStub biStub;
    static JmDNS jmdns;
    public static void main(String[] args) throws Exception  {
        
        jmdns = JmDNS.create(InetAddress.getLocalHost());
        String serviceType = "_grpc._tcp.local.";
        String serviceName = "FlightEmissionCalculator";
        //jmdns.requestServiceInfo(serviceType, serviceName, 1);
           
        jmdns.addServiceListener(serviceType, new ServiceListener(){
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
                System.out.println("Service resolved at Flight");
                
                String discoveredHost = serviceInfo.getHostAddresses()[0];
                int discoveredPort = serviceInfo.getPort();
                String inServiceName = serviceInfo.getName();
                
                try{
                    if(inServiceName.equalsIgnoreCase(serviceName)){                    
                        doFlightEmissionCalculation(discoveredHost, discoveredPort);                   
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
        
//doFlightEmissionCalculation();
    }//main
   
    public static void doFlightEmissionCalculation(String host, int port) throws InterruptedException{
    
        channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        biStub = FlightEmissionCalculatorGrpc
                .newStub(channel);
        
        StreamObserver<CO2Response> responseObserver = new StreamObserver<CO2Response>(){
        
            ArrayList<Double> responseArray = new ArrayList<>();

            @Override
            public void onNext(CO2Response v) {
                System.out.println("Response from Server: Total emission " + v.getTotalCO2());
                responseArray.add(v.getTotalCO2());
            }

            @Override
            public void onError(Throwable thrwbl) {
                System.err.println("Error occurred during stream: " + thrwbl.getMessage());
                thrwbl.printStackTrace(); 
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
    
            String startCity = "London";
            requestObserver.onNext(CO2Request.newBuilder().setStartCity(startCity).setNextCity("Dublin").build());
            Thread.sleep(500);
            requestObserver.onNext(CO2Request.newBuilder().setStartCity(startCity).setNextCity("Ulaanbaatar").build());
            Thread.sleep(500);

            requestObserver.onCompleted();
            Thread.sleep(10000);
        }
        catch (RuntimeException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {         
	    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}

    }//doFlightEmissionCalculation
    
}//class
