import java.util.ArrayList;
import java.util.List;

public class GarsonS{

    private List<Garson> garsonS = new ArrayList<>();
    private List<Table> waitingTable = new ArrayList<>();
    private static GarsonS instanceWaiter;
    private static int NUM_OF_GARSON = 4;
   
    private GarsonS(){}
    
    public static synchronized GarsonS getInstanceWaiter(){
        if (instanceWaiter == null) {
        	instanceWaiter = new GarsonS();
        }
        return instanceWaiter;
    }
    public synchronized Garson takeOrder(Table table){
    	if(waitingTable.isEmpty()){       //waiting listesi boş mu?
            if(garsonS.isEmpty()){     //garson boş mu?
                if(NUM_OF_GARSON != 0){   //garson boş durumda ve obje oluşturma hakkımız var
                    return makeGarsonObj();
                }else{              //      garson boş durumda ve obje oluşturulamaz durumdayız.
             
                    waitingTable.add(table);
                    return null;
                }
            }else{                   //waiting listesi boş ve garson dolu ise 
                return getFromGarson();
            }
    	}else{          // Waiting listesinde en az bir kişi varsa
            if(waitingTable.contains(table)){        // Obje isteği yapan masa listesinde ise
                if(garsonS.isEmpty()){                  // garson boş mu?
                    if(NUM_OF_GARSON != 0){  //garson boş ve obje oluşturma hakkımız var ise oluşturulur ve gönderilir.
                        return makeGarsonObj();
                    }
                }else{                                  // waiting listesinde ve garsonda obje var  
                    return getFromGarson();
                }
            }else{      ///waiting'de masa yok ise waiting'e eklenir ve bekletilir
                waitingTable.add(table);
                return null;
            }
        }
		return null;
    	}
    public synchronized void releaseGarson(Garson garson, Table table){
        garsonS.add(garson);
    }
    private Garson makeGarsonObj(){ //garson nesnesi oluşturduk 
        Garson garson = null;
        garson = new Garson(NUM_OF_GARSON);
        NUM_OF_GARSON--;
        return garson;
    }
    private Garson getFromGarson(){
        Garson response = garsonS.get(0);
        garsonS.remove(0);
        return response;
    }



}