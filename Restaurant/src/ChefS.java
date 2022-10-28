import java.util.ArrayList;
import java.util.List;

public class ChefS {


    private static ChefS instanceChef;
    private static int NUMBER_OF_CHEF = 2;
    private List<Chef> chefs = new ArrayList<>();
    private List<Garson> garsonS = new ArrayList<>();


    private ChefS(){}

    public static synchronized ChefS getInstance(){ 
        if (instanceChef == null) {
            instanceChef = new ChefS();
        }
        return instanceChef;
    }

    public synchronized Chef takeChef(Garson garson){
        if(garsonS.isEmpty()){      //garson listesi boş mu?
            if(chefs.isEmpty()){     //chef boş mu?
                if(NUMBER_OF_CHEF != 0){  //chef boş durumda ve obje oluşturma hakkımız var
                    return createChef();
                }else{              //      chef boş durumda ve obje oluşturulamaz durumdayız.
         
                    garsonS.add(garson);
                    return null;
                }
            }   else{                   //garson listesi boş ve chef dolu ise
                return getFromChef();
            }
        }else{          // garson listesinde en az bir kişi varsa
            if(garsonS.contains(garson)){         // Obje isteği yapan garson , garson listesinde ise
                if(chefs.isEmpty()){                   // chef boş mu?
                    if(NUMBER_OF_CHEF != 0){  //chef boş ve obje oluşturma hakkımız var ise oluşturulur ve gönderilir
                        return createChef();
                    }else{              //chef boş ve obje oluşturma hakkımız yok ise bekletilir
                        
                        return null;
                    }
                }else{                                  // garson listesinde ve chef listesinde obje var
                    return getFromChef();
                }
            }else{      //garsonda bir chef yok ise garson eklenir ve bekletilir
               
                garsonS.add(garson);
                return null;
            }
        }
    }

    public synchronized void releaseChef(Chef chef){
        chefs.add(chef);
    }

    private Chef createChef(){
        Chef chef = null;
        chef = new Chef(NUMBER_OF_CHEF);
        NUMBER_OF_CHEF--;
        return chef;
    }

    private Chef getFromChef(){
        Chef response = chefs.get(0);
        chefs.remove(0);
        return response;
    }

    
}
