package pe.permanente_evaluatie.domain;


import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class SubTaskTest {

    SubTask st;
    @Before
    public void setUp(){
        st = new SubTask("name", "description");
    }

    @Test
    public void SubTask_create_subtask_with_valid_name_and_description(){
        SubTask subTask = new SubTask("name", "description");
        assertEquals("name", subTask.getName());
        assertEquals("description", subTask.getDescription());
    }

    @Test (expected = IllegalArgumentException.class)
    public void SubTask_create_subtask_with_empty_name_throws_error(){
        new SubTask(" ", "description");
    }

    @Test (expected = IllegalArgumentException.class)
    public void SubTask_create_subtask_with_null_name_throws_error(){
        new SubTask(null, "description");
    }

    @Test
    public void setName_sets_name(){
        st.setName("new name");
        assertEquals("new name", st.getName());
    }

    @Test
    public void setDescription_sets_description(){
        st.setName("new description");
        assertEquals("new description", st.getName());
    }

    @Test
    public void setId_sets_id(){
        UUID uuid = UUID.randomUUID();
        st.setId(uuid);
        assertEquals(uuid, st.getId());
    }

}
