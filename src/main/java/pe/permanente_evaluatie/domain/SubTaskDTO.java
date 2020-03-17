package pe.permanente_evaluatie.domain;

import javax.persistence.GeneratedValue;
import java.util.UUID;

public class SubTaskDTO {

    @GeneratedValue
    private UUID id;
    private String name, description;

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
