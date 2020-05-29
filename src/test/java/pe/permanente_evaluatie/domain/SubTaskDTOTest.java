package pe.permanente_evaluatie.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class SubTaskDTOTest {

    SubTaskDTO st;
    @Before
    public void setUp(){
        st = new SubTaskDTO();
    }

    @Test (expected = IllegalArgumentException.class)
    public void setName_with_null_name_throws_error(){
        st.setName(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setName_with_empty_name_throws_error(){
        st.setName("  ");
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
