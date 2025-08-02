package dissys.flight;

import grpc.generated.flight.CO2Request;
import grpc.generated.flight.CO2Response;
import grpc.generated.flight.FlightEmissionCalculatorGrpc;
import grpc.generated.flight.FlightEmissionCalculatorGrpc.FlightEmissionCalculatorStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.JTextArea;

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
    private static boolean serviceResolved = false;
    public static StreamObserver<CO2Request> requestObserver;
    
    public static void discoverAndStart(JTextArea resultOutput) throws UnknownHostException, IOException, InterruptedException{
        try{
            jmdns = JmDNS.create(InetAddress.getLocalHost());
            String serviceType = "_grpc._tcp.local.";
            String serviceName = "FlightEmissionCalculator";

                jmdns.addServiceListener(serviceType, new ServiceListener(){
                    //String discoveryMessage = "";
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
                            //requestChoreDivide(discoveredHost, discoveredPort);
                            resultOutput.append("\n"+serviceName + " service discovered at " + discoveredHost+ ":" + discoveredPort);
                            System.out.println("Service discovered at port " + discoveredPort);
                                   channel = ManagedChannelBuilder
                                            .forAddress(discoveredHost, discoveredPort)
                                            .usePlaintext()
                                            .build();
                                    biStub = FlightEmissionCalculatorGrpc
                                            .newStub(channel);
                            resultOutput.append("\nChannel is ready now");
                             // Creating responseObserver, defining its behaviour when recieving reply
        StreamObserver<CO2Response> responseObserver = 
                new StreamObserver<CO2Response>(){
        // created responseArray to collect incoming responses from server
        ArrayList<Double> responseArray = new ArrayList<>();

        @Override
        public void onNext(CO2Response v) {
            resultOutput.setText("\nResponse from Server");
            resultOutput.append("\n" + v.getMessage() + ", total emission: " + v.getTotalCO2() );
            responseArray.add(v.getTotalCO2());
        }

        @Override
        public void onError(Throwable thrwbl) {
            resultOutput.setText("Error message: " + thrwbl.getMessage());
        }

        @Override
        public void onCompleted() {
            //System.out.println("Server stopped responding");
            resultOutput.setText("All responses from server " + responseArray.toString());
            resultOutput.append("\nCompleted !");

            }

        };
            
        requestObserver = biStub.doEmissionCalculation(responseObserver);
        resultOutput.append("\nStub initialized!");
                            }

                    }catch (RejectedExecutionException e){
                        e.printStackTrace();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
                
                
        
        }catch(IOException e){
            e.getMessage();
        }
  
    }//discoverAndStart method
    
    
    
    
    
//    public static void main(String[] args) throws Exception  {
//        
//        jmdns = JmDNS.create(InetAddress.getLocalHost());
//        String serviceType = "_grpc._tcp.local.";
//        String serviceName = "FlightEmissionCalculator";
//        //jmdns.requestServiceInfo(serviceType, serviceName, 1);
//           
//        jmdns.addServiceListener(serviceType, new ServiceListener(){
//            @Override
//            public void serviceAdded(ServiceEvent se) {
//                System.out.println("Service added: " + se.getName());
//                jmdns.requestServiceInfo(serviceType, serviceName, 1);
//            }
//
//            @Override
//            public void serviceRemoved(ServiceEvent se) {
//                System.out.println("Service removed: " + se.getName());
//            }
//
//            @Override
//            public void serviceResolved(ServiceEvent se) {
//                ServiceInfo serviceInfo = se.getInfo();
//                System.out.println("Service resolved at Flight");
//                
//                String discoveredHost = serviceInfo.getHostAddresses()[0];
//                int discoveredPort = serviceInfo.getPort();
//                String inServiceName = serviceInfo.getName();
//                
//                try{
//                    if(inServiceName.equalsIgnoreCase(serviceName)){                    
//                        doFlightEmissionCalculation(discoveredHost, discoveredPort);                   
//                    }
//   
//                }catch(InterruptedException e){
//                    e.printStackTrace();
//                }catch (RejectedExecutionException e){
//                    e.printStackTrace();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        });
//        
////doFlightEmissionCalculation();
//    }//main
//   
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
