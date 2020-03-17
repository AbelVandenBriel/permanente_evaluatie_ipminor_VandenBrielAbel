package pe.permanente_evaluatie.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class SubTask {

    @Id
    @GeneratedValue
    private UUID id;
    private String name, description;

    public SubTask(){

    }

    public SubTask(String name, String description){
        setName(name);
        setDescription(description);
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        if(name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name can't be empty.");
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
