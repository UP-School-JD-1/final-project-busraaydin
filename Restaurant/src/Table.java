public class Table {
	private int id;
	private Garson waiter;
	
    public Table(int id) {
        this.id = id;
        
    }
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setWaiter(Garson waiter) {
		this.waiter = waiter;
	}

	public Garson getWaiter() {
		if(this.waiter == null) waiterRequest(); // garson isteğinde bulunan  metot 
		return waiter;
	}
	public void waiterRequest(){ //garson isteği
        this.setWaiter(GarsonS.getInstanceWaiter().takeOrder(this));
        if(this.waiter==null){
            try {
                Thread.sleep(1000); //	çalışma süresi
                waiterRequest();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
	 
	public void thereIsCustomer() throws InterruptedException{ //müşteri var 
        getWaiter();
        
       System.out.println("Waiter "+waiter.getId()+" takes an order from "+id+ " table:" +Order.createFood() + Order.createDrink());
        this.getWaiter().notifyOrder();
  
    }

}