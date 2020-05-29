package pe.permanente_evaluatie.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Task {

    @Id
    @GeneratedValue
    private UUID id;
    private String name, description;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dueDate;
    @OneToMany(cascade = CascadeType.ALL)
    private List<SubTask> subTasks;

    public Task(){
        subTasks = new ArrayList<>();
    }

    public Task(String name, String description, LocalDateTime dueDate){
        setName(name);
        setDescription(description);
        setDueDate(dueDate);
        subTasks = new ArrayList<>();
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

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
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

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public String getDueDateAsFormattedString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy 'at' HH:mm");
        return formatter.format(dueDate);
    }

    public void addSubTask(SubTask task){
        subTasks.add(task);
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }
}
