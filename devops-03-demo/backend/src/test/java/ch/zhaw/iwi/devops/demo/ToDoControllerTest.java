package ch.zhaw.iwi.devops.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ToDoControllerTest {

    private ToDoController controller;

    @BeforeEach
    void setup() {
        controller = new ToDoController();
        controller.init(); // wichtig!
    }

    @Test
    void testTestEndpoint() {
        var result = controller.test();
        assertTrue(result.contains("running"));
    }

    @Test
    void testPing() {
        var result = controller.ping();
        assertTrue(result.startsWith("{"));
        assertTrue(result.endsWith("}"));
        assertTrue(result.contains("\"status\""));
    }

    @Test
    void testInitCount() {
        assertEquals(5, controller.count());
    }

    @Test
    void testGetAllTodos() {
        var todos = controller.todo();
        assertEquals(5, todos.size());
        assertTrue(todos.get(0).getName().length() > 0);
    }

    @Test
    void testGetTodoById() {
        var todo = controller.getTodo(1);
        assertNotNull(todo);
        assertEquals(1, todo.getId());
    }

    @Test
    void testCreateTodo() {
        var todo = new ToDo(null, "New", "Desc");
        controller.createTodo(todo);

        assertEquals(6, controller.count());
        var created = controller.getTodo(6);
        assertEquals("New", created.getTitle());
    }

    @Test
    void testUpdateTodo() {
        var updated = new ToDo(null, "Updated", "Updated Desc");
        controller.createTodo(1, updated);

        var result = controller.getTodo(1);
        assertEquals("Updated", result.getTitle());
    }

    @Test
    void testDeleteTodo() {
        var removed = controller.deleteTodo(1);

        assertNotNull(removed);
        assertEquals(4, controller.count());
        assertNull(controller.getTodo(1));
    }
}
