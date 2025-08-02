package dissys.flight;

import AllServerRegister.RegisterAll;
import ServiceRegistryAndDiscovery.SmartServiceRegistration;
import grpc.generated.flight.CO2Request;
import grpc.generated.flight.CO2Response;
import grpc.generated.flight.FlightEmissionCalculatorGrpc.FlightEmissionCalculatorImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Tsogzolmaa;
 */
public class FlightServer extends FlightEmissionCalculatorImplBase{

   
    public static void main(String[] args){
//        FlightServer flightServer = new FlightServer();
//        int port = 50002;
//        try{
//            
//        Server server = ServerBuilder
//                .forPort(port)
//                .addService(flightServer)
//                .build()
//                .start();
//
//        System.out.println("Server started on port: " + port);
//        
//        SmartServiceRegistration ssr = SmartServiceRegistration.getInstance();
//        System.out.println("Created instance of SmartServiceRegistration for FlightEmissionCalculator ");
//        ssr.registerService("_grpc._tcp.local.", "FlightEmissionCalculator", port, "Grpc bi-di FlightEmissionCalculator service");
//        System.out.println("Service registering");
//        server.awaitTermination();
//            
//        }catch(IOException e){
//            e.printStackTrace();
//        
//        }catch(InterruptedException e){
//            e.printStackTrace();}
    
    }
    
    //Trigger method to start server from GUI
    public void startServer() {
        int port = 50002;
        try {
            Server server = ServerBuilder
                    .forPort(port)
                    .addService(this)
                    .build()
                    .start();

            System.out.println("Server started, listening on " + port);

            SmartServiceRegistration ssr = SmartServiceRegistration.getInstance();
            ssr.registerService("_grpc._tcp.local.", "FlightEmissionCalculator", port, "Bi-directional FlightEmissionCalculator service");

            server.awaitTermination(); // Keep the server running
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }//startServer
    
    @Override 
    
    public StreamObserver<CO2Request> doEmissionCalculation(StreamObserver<CO2Response> responseObserver){
        
       
        return new StreamObserver<CO2Request>(){
            
            //"Dublin", "London", "Paris"
            String currentCity;
            String previousCity;
            double totalCO;
            ArrayList<String> cityArray = new ArrayList<>();
            @Override
            public void onNext(CO2Request v) {
                System.out.println("Server recieved next city " + v.getNextCity());
                currentCity = v.getNextCity().trim();
                
                //0,1
                if(cityArray.isEmpty()){
                        cityArray.add(currentCity);
                        totalCO = 0;
                }else{
                    
                    previousCity = cityArray.get(cityArray.size() - 1);
                    
                    if(previousCity.equalsIgnoreCase(currentCity)){          
                        System.out.println("Same city entered");
                    }else{
                    
                    
                    if(previousCity.equalsIgnoreCase("Dublin")){
                        if(currentCity.equalsIgnoreCase("London")){
                            totalCO = totalCO + 200;
                        }else if (currentCity.equalsIgnoreCase("Paris")){
                            totalCO = totalCO + 400;
                        }
                    }
                    if(previousCity.equalsIgnoreCase("London")){
                        if(currentCity.equalsIgnoreCase("Dublin")){
                            totalCO = totalCO + 200;
                        }else if (currentCity.equalsIgnoreCase("Paris")){
                            totalCO = totalCO + 300;
                        }
                    }
                    if(previousCity.equalsIgnoreCase("Paris")){
                        if(currentCity.equalsIgnoreCase("Dublin")){
                            totalCO = totalCO + 400;
                        }else if (currentCity.equalsIgnoreCase("London")){
                            totalCO = totalCO + 300;
                        }
                    }
                    
                    cityArray.add(currentCity);
                    }//else
                }//else
                
                
            responseObserver.onNext(CO2Response.newBuilder().setTotalCO2(totalCO).build());
    
            }

            @Override
            public void onError(Throwable thrwbl) {
                System.err.println("Error occurred during stream: " + thrwbl.getMessage());
                thrwbl.printStackTrace();             }

            @Override
            //request completes, response will complete.
            public void onCompleted() {
                responseObserver.onCompleted();             }
        };
        
        
    }
    
}
