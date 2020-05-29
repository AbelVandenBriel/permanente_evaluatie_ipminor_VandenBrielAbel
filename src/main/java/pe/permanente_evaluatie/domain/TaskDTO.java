package pe.permanente_evaluatie.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskDTO {

    @GeneratedValue
    private UUID id;
    private String name, description;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dueDate;
    @OneToMany(cascade = CascadeType.ALL)
    private List<SubTaskDTO> subTasks;

    public TaskDTO(){
        subTasks = new ArrayList<>();
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name can't be empty.");
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

    public String getDueDateAsFormattedString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy 'at' HH:mm");
        return formatter.format(dueDate);
    }

    public void addSubTask(SubTaskDTO task) {
        subTasks.add(task);
    }

    public List<SubTaskDTO> getSubTasks() {
        return subTasks;
    }
}
