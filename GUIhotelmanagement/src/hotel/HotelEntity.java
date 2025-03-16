package hotel;

import interfaces.IManageable;

public abstract class HotelEntity implements IManageable {
    protected String id;
    protected String status;

    public HotelEntity(String id, String status) {
        if (id == null || id.trim().isEmpty())
            throw new IllegalArgumentException("ID cannot be null or empty.");
        this.id = id;
        this.status = status != null ? status : "Inactive";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id != null && !id.trim().isEmpty())
            this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status != null ? status : "Inactive";
    }

    public boolean isActive() {
        return "Active".equals(status);
    }

    @Override
    abstract public void add();

    @Override
    abstract public void update();

    @Override
    abstract public void delete();
}