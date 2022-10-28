public class Garson {

    private int id;
    private Chef chef;

    public Garson(int id){
    	this.id = id;
      
    }

    public void chefRequest(){
        this.setChef(((ChefS) ChefS.getInstance()).takeChef(this));
        if(this.chef==null){
            try {
            	//pişirme süresi
                Thread.sleep(1000);
                chefRequest();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void releaseChef(){
        ChefS.getInstance().releaseChef(this.chef);
    }

    public Chef getCook() throws InterruptedException {
        if(this.chef == null) chefRequest();
        System.out.println("Chef "+chef.getId()+" take order from "+this.id+ " waiter");
        Thread.sleep(10);
        System.out.println("Chef "+chef.getId()+" cooking ");
        return this.chef;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Chef getChef() {
		return chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void notifyOrder() throws InterruptedException{
        this.getCook();
        System.out.println("Waiter "+id+" takes cooked meal from "+this.getChef().getId()+ ". chef ");
        this.getCook().sendDelivery();
        this.releaseChef();
    }
    
}
