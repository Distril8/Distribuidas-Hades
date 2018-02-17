/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.nosql.mongo;

import java.util.Date;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Version;

/**
 *
 * @author Henry Coral
 */
@Entity
public abstract class BaseEntity {

    @Id
    protected ObjectId id;

    /**
     * We'll only provide getters for these attributes, setting is done in
     * @PrePersist.
     */
    protected Date creationDate;
    protected Date lastChange;

    /**
     * No getters and setters required, the version is handled internally.
     */
    @Version
    private long version;

    public BaseEntity() {
        super();
    }

    public String getId() {
        return id.toHexString();
    }

    public void setId(String id) {
        this.id = new ObjectId(id);
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastChange() {
        return lastChange;
    }

    @PrePersist
    public void prePersist() {
        this.creationDate = (creationDate == null) ? new Date() : creationDate;
        this.lastChange = (lastChange == null) ? creationDate : new Date();
    }

    public abstract String toString();
}
