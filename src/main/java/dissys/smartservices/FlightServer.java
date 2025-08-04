package dissys.smartservices;

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
    
    //Trigger method to start server from GUI
    public static boolean serverStarted;
    public void startServer() throws Exception {
        
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

            serverStarted = true;
            
            server.awaitTermination(); // Keep the server running
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } 
    }//startServer
    
    /**
     * Bi-directional RPC for calculating total CO emission when user enter cities one by one
     * @param responseObserver - this parameter passed from Client 
     * @return 
     * currently this code is works between few cities "Dublin", "London", "Paris", "Tokyo"
     * if user enter unsupported city, server still sends reply by saying it is unsupported city
     * 
     */
    @Override 
    public StreamObserver<CO2Request> doEmissionCalculation(StreamObserver<CO2Response> responseObserver){
        
        //Anonymous class StreamObserver<CO2Request>, we can call requestObserver
        return new StreamObserver<CO2Request>(){
            
            //"Dublin", "London", "Paris", "Tokyo"
            String currentCity;
            String previousCity;
            double totalCO;
            //cityArray will contain user input cities when entered ("Dublin", "London", "Paris", "Tokyo")
            //it is for keep track of previous city, using pervious city we can calculate co2 amount between current city
            ArrayList<String> cityArray = new ArrayList<>();
            //provided supported city lists, when user input comes, it checks if that input is supported city or not
            ArrayList<String> supportedCityNames = new ArrayList<>();

            //onNext requestObserver, here server gets city name, and do calculation
            //sends reply on onNext response observer
            @Override
            public void onNext(CO2Request v) {
                System.out.println("Server recieved next city " + v.getNextCity());
                currentCity = v.getNextCity().trim();
                supportedCityNames.add("dublin");
                supportedCityNames.add("paris");
                supportedCityNames.add("london");
                supportedCityNames.add("tokyo");
                
                //if statement check if user input among supported cities
                //if not it send response with following message and total emission
                //amount stays same
                if(!supportedCityNames.contains(currentCity.toLowerCase())){
                
                        responseObserver.onNext(CO2Response.newBuilder()
                                .setTotalCO2(totalCO)
                                .setMessage("Unsupported city entered! " + currentCity)
                                .build());
                        return;
                }
                //0,1
                //if cityArray is empty means it is first valid city, so emission is 0
                if(cityArray.isEmpty()){
                        cityArray.add(currentCity);
                        totalCO = 0;
                        responseObserver.onNext(CO2Response.newBuilder()
                                .setTotalCO2(totalCO)
                                .setMessage("First city entered! " + currentCity)
                                .build());
                        return;
                }else{ 
                    //if input is not first input. it finds previous city name.
                    //and does calculations with following if statements
                    //co2 amount will be accumulated over the time as much as user enters
                    //inputs
                    previousCity = cityArray.get(cityArray.size() - 1);
                    
                    //in case user enters same city name, server sends following message
                    //and co2 amount stays same
                    if(previousCity.equalsIgnoreCase(currentCity)){          
                        System.out.println("Same city entered");
                        responseObserver.onNext(CO2Response.newBuilder()
                                .setTotalCO2(totalCO)
                                .setMessage("Duplicate city name! " + currentCity)
                                .build());
                        return;

                    }else{
                    
                    
                    if(previousCity.equalsIgnoreCase("Dublin")){
                        if(currentCity.equalsIgnoreCase("London")){
                            totalCO = totalCO + 52.9;
                        }else if (currentCity.equalsIgnoreCase("Paris")){
                            totalCO = totalCO + 89.7;
                        }else if (currentCity.equalsIgnoreCase("Tokyo")){
                            totalCO = totalCO + 1265;
                        }
                    }
                    else if(previousCity.equalsIgnoreCase("London")){
                        if(currentCity.equalsIgnoreCase("Dublin")){
                            totalCO = totalCO + 52.9;
                        }else if (currentCity.equalsIgnoreCase("Paris")){
                            totalCO = totalCO + 39.1;
                        }else if (currentCity.equalsIgnoreCase("Tokyo")){
                            totalCO = totalCO + 1092;
                        }
                    }
                    else if(previousCity.equalsIgnoreCase("Paris")){
                        if(currentCity.equalsIgnoreCase("Dublin")){
                            totalCO = totalCO + 89.7;
                        }else if (currentCity.equalsIgnoreCase("London")){
                            totalCO = totalCO + 39.1;
                        }else if (currentCity.equalsIgnoreCase("Tokyo")){
                            totalCO = totalCO + 1115;
                        }
                    }
                    
                    else if(previousCity.equalsIgnoreCase("Tokyo")){
                        if(currentCity.equalsIgnoreCase("Dublin")){
                            totalCO = totalCO + 1265;
                        }else if (currentCity.equalsIgnoreCase("London")){
                            totalCO = totalCO + 1092.5;
                        }else if (currentCity.equalsIgnoreCase("Paris")){
                            totalCO = totalCO + 1115.5;
                        }
                    }
                    
                    cityArray.add(currentCity);
                    }//else
                }//else
                
            //once user enters supported city name, server calculates and
            //creates new response with builder, and sends
            //it as a parameter on onNext response observer with following message and amount of co2
            responseObserver.onNext(CO2Response.newBuilder()
                    .setTotalCO2(totalCO)
                    .setMessage("Emission updated trip to " + currentCity).build());
    
            }

            @Override
            public void onError(Throwable thrwbl) {
                System.err.println("Error occurred during stream: " + thrwbl.getMessage());
                thrwbl.printStackTrace();             }

            @Override
            //request completes, response will complete.
            public void onCompleted() {
                //if user decides to finish its requests, it calls onCompleted on request observer
                //server gets that here
                //and calls onCompleted on response observer
                //which means for bi-directional rpc, user sends and gets, when user stops server stops
                responseObserver.onCompleted();             }
        };//request observer
        
        
    }
    
}
