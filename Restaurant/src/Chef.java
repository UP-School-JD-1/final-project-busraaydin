public class Chef {
    
    private int id;

    public Chef(int id) {
    	this.id = id;
    	
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
     
	public void sendDelivery(){
        try {
            Thread.sleep(3*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    }