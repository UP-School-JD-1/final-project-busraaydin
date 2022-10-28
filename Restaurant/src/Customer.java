public class Customer implements Runnable  {

    private int id;
    private Table table;
    
    public Customer(int id){
       this.id = id;
        this.getTable();
        System.out.println("Customer "+id+" sat on the table "+table.getId());
    }
    public Table getTable() {
        if(this.table == null){
            tableRequest();
        }
        return this.table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
    public int getId() {
		return id;
	}
    //masa isteği
    public void tableRequest(){
       this.setTable(TableS.getInstance().takeTable(this));
        if(this.table==null){
            try {
                Thread.sleep(1000); //masa yoksa bekle
                tableRequest(); //yeniden istekte bulunuyor.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
   
	
	public void giveOrder() throws InterruptedException{ //sipariş ver
        System.out.println("Customer "+id +" is giving an order from "+table.getId()+" table"); 
        getTable().thereIsCustomer(); 
    }

    @Override
    public void run() {
            try {
				giveOrder(); 
				//sipariş hazırlanma ve yeme süresi 
				Thread.sleep(1000);
				// müşteri ayrılıyor
				System.out.println(this.id+". Customer got up from the table "+table.getId());
				TableS.getInstance().releaseTable(this.table);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            
        }	 

  

}

