package dissys.chore;

import grpc.generated.chore.ChoreDividerGrpc.ChoreDividerImplBase;
import grpc.generated.chore.ChoreRequest;
import grpc.generated.chore.ChoreResponse;
import grpc.generated.chore.ReportRequest;
import grpc.generated.chore.ReportResponse;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

/**
 *
 * @author Tsogzolmaa;
 */
public class ChoreServer extends ChoreDividerImplBase {

    private static final Logger logger = Logger.getLogger(ChoreServer.class.getName());
    public static void main(String[] args) {

        ChoreServer choreServer = new ChoreServer();
        int port = 50001;
        try{
            Server server =ServerBuilder
                    .forPort(port)
                    .addService(choreServer)
                    .build()
                    .start();
            logger.info("Server started, listening on " + port);
            server.awaitTermination();
            
            
        }catch(IOException e){
            e.printStackTrace();
        
        }catch(InterruptedException e){
            e.printStackTrace();}
    }
    
    public String divideTwo(ArrayList<Integer> randomizedChores){
    
        if (randomizedChores == null || randomizedChores.size() < 11) {
            throw new IllegalArgumentException("randomizedChores must contain at least 11 elements");
        }
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<String> list1String = new ArrayList<>();
        ArrayList<String> list2String = new ArrayList<>();
        
        for (int i = 0; i < 11; i = i + 2) {
            list1.add(randomizedChores.get(i));
        }
        for (int i = 1; i < 11; i = i + 2) {
            list2.add(randomizedChores.get(i));
        }
        
        for (int i = 0; i < list1.size(); i++) {
            if(null != list1.get(i))switch (list1.get(i)) {
                case 1:
                    list1String.add("Making the bed 1");
                    break;
                case 2:
                    list1String.add("Taking out trash 1");
                    break;
                case 3:
                    list1String.add("do grocery 1");
                    break;
                case 4:
                    list1String.add("dusting 2");
                    break;
                case 5:
                    list1String.add("dishes 2");
                    break;
                case 6:
                    list1String.add("walking dog 2");
                    break;
                case 7:
                    list1String.add("dusting 2");
                    break;
                case 8:
                    list1String.add("laundry 3");
                    break;
                case 9:
                    list1String.add("gardening 3");
                    break;
                case 10:
                    list1String.add("cleaning bathroom 4");
                    break;
                case 11:
                    list1String.add("cooking 4");
                    break;                    
                default:
                    break;
            }//switch
                        
        }//for
        
        for (int i = 0; i < list2.size(); i++) {
            if(null != list2.get(i))switch (list2.get(i)) {
                case 1:
                    list2String.add("Making the bed 1");
                    break;
                case 2:
                    list2String.add("Taking out trash 1");
                    break;
                case 3:
                    list2String.add("do grocery 1");
                    break;
                case 4:
                    list2String.add("dusting 2");
                    break;
                case 5:
                    list2String.add("dishes 2");
                    break;
                case 6:
                    list2String.add("walking dog 2");
                    break;
                case 7:
                    list2String.add("dusting 2");
                    break;
                case 8:
                    list2String.add("laundry 3");
                    break;
                case 9:
                    list2String.add("gardening 3");
                    break;
                case 10:
                    list2String.add("cleaning bathroom 4");
                    break;
                case 11:
                    list2String.add("cooking 4");
                    break;                    
                default:
                    break;
            }
                        
        }
       
        

        return "\nPerson1 task: " + list1String.toString() 
                + "\nPerson2 task: " + list2String.toString();


    }
    
    public String divideThree(ArrayList<Integer> randomizedChores){
    if (randomizedChores == null || randomizedChores.size() < 11) {
            throw new IllegalArgumentException("randomizedChores must contain at least 11 elements");
        }
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<Integer> list3 = new ArrayList<>();
        ArrayList<String> list1String = new ArrayList<>();
        ArrayList<String> list2String = new ArrayList<>();
        ArrayList<String> list3String = new ArrayList<>();
        
        for (int i = 0; i < 11; i = i + 3) {
            list1.add(randomizedChores.get(i));
        }
        for (int i = 1; i < 11; i = i + 3) {
            list2.add(randomizedChores.get(i));
        }
        for (int i = 2; i < 11; i = i + 3) {
            list3.add(randomizedChores.get(i));
        }   
        
        for (int i = 0; i < list1.size(); i++) {
            if(null != list1.get(i))switch (list1.get(i)) {
                case 1:
                    list1String.add("Making the bed 1");
                    break;
                case 2:
                    list1String.add("Taking out trash 1");
                    break;
                case 3:
                    list1String.add("do grocery 1");
                    break;
                case 4:
                    list1String.add("dusting 2");
                    break;
                case 5:
                    list1String.add("dishes 2");
                    break;
                case 6:
                    list1String.add("walking dog 2");
                    break;
                case 7:
                    list1String.add("dusting 2");
                    break;
                case 8:
                    list1String.add("laundry 3");
                    break;
                case 9:
                    list1String.add("gardening 3");
                    break;
                case 10:
                    list1String.add("cleaning bathroom 4");
                    break;
                case 11:
                    list1String.add("cooking 4");
                    break;                    
                default:
                    break;
            }//switch
                        
        }//for
        for (int i = 0; i < list2.size(); i++) {
            if(null != list2.get(i))switch (list2.get(i)) {
                case 1:
                    list2String.add("Making the bed 1");
                    break;
                case 2:
                    list2String.add("Taking out trash 1");
                    break;
                case 3:
                    list2String.add("do grocery 1");
                    break;
                case 4:
                    list2String.add("dusting 2");
                    break;
                case 5:
                    list2String.add("dishes 2");
                    break;
                case 6:
                    list2String.add("walking dog 2");
                    break;
                case 7:
                    list2String.add("dusting 2");
                    break;
                case 8:
                    list2String.add("laundry 3");
                    break;
                case 9:
                    list2String.add("gardening 3");
                    break;
                case 10:
                    list2String.add("cleaning bathroom 4");
                    break;
                case 11:
                    list2String.add("cooking 4");
                    break;                    
                default:
                    break;
            }
                        
        }
        
        for (int i = 0; i < list3.size(); i++) {
            if(null != list3.get(i))switch (list3.get(i)) {
                case 1:
                    list3String.add("Making the bed 1");
                    break;
                case 2:
                    list3String.add("Taking out trash 1");
                    break;
                case 3:
                    list3String.add("do grocery 1");
                    break;
                case 4:
                    list3String.add("dusting 2");
                    break;
                case 5:
                    list3String.add("dishes 2");
                    break;
                case 6:
                    list3String.add("walking dog 2");
                    break;
                case 7:
                    list3String.add("dusting 2");
                    break;
                case 8:
                    list3String.add("laundry 3");
                    break;
                case 9:
                    list3String.add("gardening 3");
                    break;
                case 10:
                    list3String.add("cleaning bathroom 4");
                    break;
                case 11:
                    list3String.add("cooking 4");
                    break;                    
                default:
                    break;
            }
                        
        }
        
        return"\nPerson1" + list1String.toString() 
                + "\nPerson2" + list2String.toString() 
                + "\nPerson3" + list3String.toString();
    }
    
    public String divideFour(ArrayList<Integer> randomizedChores){
    if (randomizedChores == null || randomizedChores.size() < 11) {
            throw new IllegalArgumentException("randomizedChores must contain at least 11 elements");
        }
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<Integer> list3 = new ArrayList<>();
        ArrayList<Integer> list4 = new ArrayList<>();

        ArrayList<String> list1String = new ArrayList<>();
        ArrayList<String> list2String = new ArrayList<>();
        ArrayList<String> list3String = new ArrayList<>();
        ArrayList<String> list4String = new ArrayList<>();

        
        for (int i = 0; i < 11; i = i + 4) {
            list1.add(randomizedChores.get(i));
        }
        for (int i = 1; i < 11; i = i + 4) {
            list2.add(randomizedChores.get(i));
        }
        for (int i = 2; i < 11; i = i + 4) {
            list3.add(randomizedChores.get(i));
        }  
        for (int i = 3; i < 11; i = i + 4) {
            list4.add(randomizedChores.get(i));
        } 
    for (int i = 0; i < list1.size(); i++) {
            if(null != list1.get(i))switch (list1.get(i)) {
                case 1:
                    list1String.add("Making the bed 1");
                    break;
                case 2:
                    list1String.add("Taking out trash 1");
                    break;
                case 3:
                    list1String.add("do grocery 1");
                    break;
                case 4:
                    list1String.add("dusting 2");
                    break;
                case 5:
                    list1String.add("dishes 2");
                    break;
                case 6:
                    list1String.add("walking dog 2");
                    break;
                case 7:
                    list1String.add("dusting 2");
                    break;
                case 8:
                    list1String.add("laundry 3");
                    break;
                case 9:
                    list1String.add("gardening 3");
                    break;
                case 10:
                    list1String.add("cleaning bathroom 4");
                    break;
                case 11:
                    list1String.add("cooking 4");
                    break;                    
                default:
                    break;
            }//switch
                        
        }//for
        for (int i = 0; i < list2.size(); i++) {
            if(null != list2.get(i))switch (list2.get(i)) {
                case 1:
                    list2String.add("Making the bed 1");
                    break;
                case 2:
                    list2String.add("Taking out trash 1");
                    break;
                case 3:
                    list2String.add("do grocery 1");
                    break;
                case 4:
                    list2String.add("dusting 2");
                    break;
                case 5:
                    list2String.add("dishes 2");
                    break;
                case 6:
                    list2String.add("walking dog 2");
                    break;
                case 7:
                    list2String.add("dusting 2");
                    break;
                case 8:
                    list2String.add("laundry 3");
                    break;
                case 9:
                    list2String.add("gardening 3");
                    break;
                case 10:
                    list2String.add("cleaning bathroom 4");
                    break;
                case 11:
                    list2String.add("cooking 4");
                    break;                    
                default:
                    break;
            }
                        
        }
        
        for (int i = 0; i < list3.size(); i++) {
            if(null != list3.get(i))switch (list3.get(i)) {
                case 1:
                    list3String.add("Making the bed 1");
                    break;
                case 2:
                    list3String.add("Taking out trash 1");
                    break;
                case 3:
                    list3String.add("do grocery 1");
                    break;
                case 4:
                    list3String.add("dusting 2");
                    break;
                case 5:
                    list3String.add("dishes 2");
                    break;
                case 6:
                    list3String.add("walking dog 2");
                    break;
                case 7:
                    list3String.add("dusting 2");
                    break;
                case 8:
                    list3String.add("laundry 3");
                    break;
                case 9:
                    list3String.add("gardening 3");
                    break;
                case 10:
                    list3String.add("cleaning bathroom 4");
                    break;
                case 11:
                    list3String.add("cooking 4");
                    break;                    
                default:
                    break;
            }
                        
        }
        for (int i = 0; i < list4.size(); i++) {
            if(null != list4.get(i))switch (list4.get(i)) {
                case 1:
                    list4String.add("Making the bed 1");
                    break;
                case 2:
                    list4String.add("Taking out trash 1");
                    break;
                case 3:
                    list4String.add("do grocery 1");
                    break;
                case 4:
                    list4String.add("dusting 2");
                    break;
                case 5:
                    list4String.add("dishes 2");
                    break;
                case 6:
                    list4String.add("walking dog 2");
                    break;
                case 7:
                    list4String.add("dusting 2");
                    break;
                case 8:
                    list4String.add("laundry 3");
                    break;
                case 9:
                    list4String.add("gardening 3");
                    break;
                case 10:
                    list4String.add("cleaning bathroom 4");
                    break;
                case 11:
                    list4String.add("cooking 4");
                    break;                    
                default:
                    break;
            }
                        
        }
        return"\nPerson1" + list1String.toString() 
                + "\nPerson2" + list2String.toString() 
                + "\nPerson3" + list3String.toString() 
                + "\nPerson4" + list4String.toString();
    }

    @Override 
    public void doChoderDivide(ChoreRequest request, StreamObserver<ChoreResponse> response){
    
        
        int inNumPeople = request.getNumPeople();
        String responseMsg = "not initialized";
        //Common chores
        
        //1.making the bed-1
        //2.taking out trash-1
        //3.do grocery-1
        //4.dusting-2
        //5.dishes-2
        //6.walking dog-2
        //7.dusting-2        
        //8.laundry-3
        //9.gardening-3
        //10.cleaning bathroom-4
        //11.cooking-4
        ArrayList<Integer> point1Chores = new ArrayList<>();
        ArrayList<Integer> point2Chores = new ArrayList<>();
        ArrayList<Integer> point3Chores = new ArrayList<>();
        ArrayList<Integer> point4Chores = new ArrayList<>();
        ArrayList<Integer> randomizedChores = new ArrayList<>();

        for (int i = 1; i < 4; i++) {
            point1Chores.add(i);
        }
        for (int i = 4; i < 8; i++) {
            point2Chores.add(i);
        }
        point3Chores.add(8);
        point3Chores.add(9);

        point4Chores.add(10);
        point4Chores.add(11);
        
        Collections.shuffle(point1Chores);
        Collections.shuffle(point2Chores);
        Collections.shuffle(point3Chores);
        Collections.shuffle(point4Chores);

        randomizedChores.add(point1Chores.get(0));
        randomizedChores.add(point1Chores.get(1));
        randomizedChores.add(point1Chores.get(2));
        
        randomizedChores.add(point2Chores.get(0));
        randomizedChores.add(point2Chores.get(1));
        randomizedChores.add(point2Chores.get(2));
        randomizedChores.add(point2Chores.get(3));
        
        randomizedChores.add(point3Chores.get(0));
        randomizedChores.add(point3Chores.get(1));
        
        randomizedChores.add(point4Chores.get(0));
        randomizedChores.add(point4Chores.get(1));

        switch (inNumPeople) {
            case 2:
                responseMsg = divideTwo(randomizedChores);
                break;
            case 3:
                responseMsg = divideThree(randomizedChores);
                break;
            case 4:
                responseMsg = divideFour(randomizedChores);
                break;
            default:
                break;
        }
        
        response.onNext(ChoreResponse.newBuilder().setChoreResult(responseMsg).build());
//        response.onNext(ChoreResponse.newBuilder()
//                .setChoreResult(request.getNumPeople()).build());
//        
        response.onCompleted();
        
      
    }
    
    @Override 
    public StreamObserver<ReportRequest> doChoreReport(StreamObserver<ReportResponse> responseObserver){
        
        return new StreamObserver<ReportRequest>(){
            
            ArrayList<Integer> completedTaskNums = new ArrayList<>();
            
            @Override
            public void onNext(ReportRequest v) {
                System.out.println(LocalTime.now().toString() + " Recieved num : " + v.getCompletedTaskNum() );
                completedTaskNums.add(v.getCompletedTaskNum());
            }

            @Override
            public void onError(Throwable thrwbl) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void onCompleted() {
                System.out.println(LocalTime.now().toString() + " Recieving is completed");
                ArrayList<String> completedTasksString = new ArrayList<>();
                
                int completedNums = completedTaskNums.size();
                for (int i = 0; i < completedTaskNums.size(); i++) {
                    if(null != completedTaskNums.get(i))switch (completedTaskNums.get(i)) {
                    case 1:
                        completedTasksString.add("Making the bed 1");
                        break;
                    case 2:
                        completedTasksString.add("Taking out trash 1");
                        break;
                    case 3:
                        completedTasksString.add("do grocery 1");
                        break;
                    case 4:
                        completedTasksString.add("dusting 2");
                        break;
                    case 5:
                        completedTasksString.add("dishes 2");
                        break;
                    case 6:
                        completedTasksString.add("walking dog 2");
                        break;
                    case 7:
                        completedTasksString.add("dusting 2");
                        break;
                    case 8:
                        completedTasksString.add("laundry 3");
                        break;
                    case 9:
                        completedTasksString.add("gardening 3");
                        break;
                    case 10:
                        completedTasksString.add("cleaning bathroom 4");
                        break;
                    case 11:
                        completedTasksString.add("cooking 4");
                        break;                    
                    default:
                        break;
                }//switch
                    
                    
                }//for
                
                ReportResponse report = ReportResponse.newBuilder()
                        .setReportResult("\n Report \n Number of completed tasks: " 
                                + completedNums + "\nCompleted tasks : " 
                                + completedTasksString.toString())
                        .build();
                responseObserver.onNext(report);
                responseObserver.onCompleted();
            }//onCompleted
        
        };
    
    }
}
