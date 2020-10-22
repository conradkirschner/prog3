package app.observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.math.BigDecimal;

public class StorageSize {
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private BigDecimal storageLimit = new BigDecimal(0);

    public StorageSize() {
    }

    public void setStorageLimit(BigDecimal newStorageLimit)
    {
        this.storageLimit = newStorageLimit;
        changes.firePropertyChange("storageLimitCheck", newStorageLimit, null);
    }

    public BigDecimal getGetStorageLimit() {
        return storageLimit;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }
}