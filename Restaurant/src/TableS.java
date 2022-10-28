import java.util.ArrayList;
import java.util.List;

public class TableS {
    private List<Table> tables = new ArrayList<Table>();
	private List<Customer> customersWaiting = new ArrayList<Customer>();
    public static int NUM_OF_TABLE = 5;
    private static TableS tableInstance;
   
    private TableS(){}

	public static synchronized TableS getInstance(){ // masanın durumu
        if (tableInstance == null) {
        	tableInstance = new TableS();
        }
        return tableInstance;
    }
	public synchronized Table takeTable(Customer customer){  //masanın durumuna göre müşteriye cevap veriyor

        if(customersWaiting.isEmpty()){      //customer listesi boş mu?
            if(tables.isEmpty()){     //masa boş mu?
                if(NUM_OF_TABLE != 0){  //max masa sayısına ulaşılmadı ise yeni masa oluşturulabilir
                    return makeTable();   //boş bir masa oluşturuldu
                }else{                  //max müşteri sayısına ulaşıldı artık müşteri beklemeli.
                    System.out.println("Customer "+customer.getId()+" waiting");
                    customersWaiting.add(customer); //bekleme listesine bir müşteri eklendi
                    return null;
                }
            } else{                   //müşteri listesi boş ve masa dolu ise masa verilir
                return getFromTable();
            }
        }else{          // Müşteri listesinde en az bir kişi varsa
            if(customersWaiting.contains(customer)){        // masa isteğinde bulunan müşteri, müşteri listesinde ise
                if(tables.isEmpty()){                  // masa boş mu?
                    if(NUM_OF_TABLE != 0){  //masa boş ve obje oluşturma hakkımız var ise oluşturulur ve gönderilir
                        return makeTable();
                    }else{              //masa boş ve obje oluşturma hakkımız yok ise beklemesi söyledinir
                        System.out.println("Customer "+customer.getId()+" please wait.");
                        return null;
                    }
                }else{                                  // müşteri listesinde ve masada obje var
                    return getFromTable();
                }
            }else{      //customer waitingde müşteri yok ise customer waiting'e müşteri eklenir
                customersWaiting.add(customer);
                return null;
            }
        }
		
        }
        
	public synchronized void releaseTable(Table table){
        tables.add(table);
    }
	private Table makeTable(){ //masa oluştur
        Table table = null;
        table = new Table( NUM_OF_TABLE);
        NUM_OF_TABLE--;
        return table;

}
	private Table getFromTable(){
        Table response = tables.get(0); // masa listesinde ilk sıradakini getirir
        tables.remove(0); //getirir ve siler
        return response;
    }
}
