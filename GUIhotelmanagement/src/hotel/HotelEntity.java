package hotel;

import interfaces.IManageable;

public abstract class HotelEntity implements IManageable {
    protected String id;
    protected String status;
    
    public HotelEntity(String id, String status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    abstract public void add();

    @Override
    abstract public void update();

    @Override
    abstract public void delete();
}
