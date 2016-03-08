package org.cacois.JettyJerseyCassandra.data;

import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

/**
 * Created by cacois on 3/4/16.
 */
@Table(keyspace="testks", name="users")
public class User {
    public User(){}

    @PartitionKey
    private Integer id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
