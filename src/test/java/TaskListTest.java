import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

//public class TaskListTest extends SeleniumSetup {
//    @Test
//    void getTaskListTest(){
//        driver.get(APP_URL);
//        WebElement element = driver.findElement(By.id("tab-todo"));
//        element.click();
//        driver.findElement(By.id("todo-input")).sendKeys("1");
//        driver.findElement(By.id("todo-add")).click();
//        Assertions.assertEquals(1, driver.findElements(By.cssSelector("#todo-list li")).size());
//    }
//    @Test
//    void checkTaskDoneTest(){
//        driver.get(APP_URL);
//        WebElement element = driver.findElement(By.id("tab-todo"));
//        element.click();
//        driver.findElement(By.id("todo-input")).sendKeys("1");
//        driver.findElement(By.id("todo-add")).click();
//        driver.findElement(By.id("todo-check-1")).click();
//        Assertions.assertEquals("Zadania: 1 | Ukończone: 1 | Pozostałe: 0",driver.findElement(By.id("todo-count")).getText());
//    }
//    @Test
//    void checkTaskNotDoneTest(){
//        driver.get(APP_URL);
//        WebElement element = driver.findElement(By.id("tab-todo"));
//        element.click();
//        driver.findElement(By.id("todo-input")).sendKeys("1");
//        driver.findElement(By.id("todo-add")).click();
//
//        Assertions.assertEquals("Zadania: 1 | Ukończone: 0 | Pozostałe: 1",driver.findElement(By.id("todo-count")).getText());
//    }
//    @Test
//    void checkDeleteTaskTest(){
//        driver.get(APP_URL);
//        WebElement element = driver.findElement(By.id("tab-todo"));
//        element.click();
//        driver.findElement(By.id("todo-input")).sendKeys("1");
//        driver.findElement(By.id("todo-add")).click();
//        driver.findElement(By.id("todo-check-1")).click();
//        driver.findElement(By.id("todo-del-1")).click();
//        Assertions.assertEquals("Zadania: 0 | Ukończone: 0 | Pozostałe: 0",driver.findElement(By.id("todo-count")).getText());
//    }
//    @Test
//    void shouldShowActiveTaskWhenActiveFilterIsSelected() {
//        driver.get(APP_URL);
//        WebElement element = driver.findElement(By.id("tab-todo"));
//        element.click();
//        driver.findElement(By.id("todo-input")).sendKeys("2");
//        driver.findElement(By.id("todo-add")).click();
//        driver.findElement(By.id("todo-filter-active")).click();
//
//        Assertions.assertEquals(1, driver.findElements(By.cssSelector("#todo-list li")).size());
//    }
//    @Test
//    void shouldNotAllowAddEmptySpace() {
//        driver.get(APP_URL);
//        WebElement element = driver.findElement(By.id("tab-todo"));
//        element.click();
//        driver.findElement(By.id("todo-input")).sendKeys(" ");
//        driver.findElement(By.id("todo-add")).click();
//
//
//        Assertions.assertEquals("Wpisz treść zadania.",driver.findElement(By.id("todo-message")).getText());
//    }
//}
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class TaskListTest extends SeleniumSetup {
    private TaskListPage taskListPage = new TaskListPage(driver);

    private void openTodoTab() {
        driver.get(APP_URL);
        driver.findElement(By.id("tab-todo")).click();
    }

    @Test
    void shouldAddTask() {
        openTodoTab();

        taskListPage.addTask("1");

        Assertions.assertEquals(1, taskListPage.getTasksCount());
        Assertions.assertEquals("1", taskListPage.getTaskText(1));
        Assertions.assertEquals("Zadania: 1 | Ukończone: 0 | Pozostałe: 1", taskListPage.getCounterText());
    }

    @Test
    void shouldNotAddEmptyTask() {
        openTodoTab();

        taskListPage.addTask("");

        Assertions.assertEquals(0, taskListPage.getTasksCount());
        Assertions.assertEquals("Wpisz treść zadania.", taskListPage.getMessageText());
    }

    @Test
    void shouldNotAddTaskWithOnlySpaces() {
        openTodoTab();

        taskListPage.addTask("   ");

        Assertions.assertEquals(0, taskListPage.getTasksCount());
        Assertions.assertEquals("Wpisz treść zadania.", taskListPage.getMessageText());
    }

    @Test
    void shouldMarkTaskAsDone() {
        openTodoTab();

        taskListPage.addTask("1");
        taskListPage.markTaskAsDone(1);

        Assertions.assertEquals("Zadania: 1 | Ukończone: 1 | Pozostałe: 0", taskListPage.getCounterText());
    }

    @Test
    void shouldShowOnlyActiveTasks() {
        openTodoTab();

        taskListPage.addTask("aktywne");
        taskListPage.addTask("ukonczone");
        taskListPage.markTaskAsDone(2);

        taskListPage.showActiveTasks();

        Assertions.assertEquals(1, taskListPage.getTasksCount());
        Assertions.assertEquals("aktywne", taskListPage.getTaskText(1));
    }

    @Test
    void shouldShowOnlyDoneTasks() {
        openTodoTab();

        taskListPage.addTask("aktywne");
        taskListPage.addTask("ukonczone");
        taskListPage.markTaskAsDone(2);

        taskListPage.showDoneTasks();

        Assertions.assertEquals(1, taskListPage.getTasksCount());
        Assertions.assertEquals("ukonczone", taskListPage.getTaskText(2));
    }

    @Test
    void shouldDeleteTask() {
        openTodoTab();

        taskListPage.addTask("1");
        taskListPage.deleteTask(1);

        Assertions.assertEquals(0, taskListPage.getTasksCount());
        Assertions.assertEquals("Zadania: 0 | Ukończone: 0 | Pozostałe: 0", taskListPage.getCounterText());
    }

    @Test
    void shouldClearOnlyDoneTasks() {
        openTodoTab();

        taskListPage.addTask("aktywne");
        taskListPage.addTask("ukonczone");
        taskListPage.markTaskAsDone(2);

        taskListPage.clearDoneTasks();

        Assertions.assertEquals(1, taskListPage.getTasksCount());
        Assertions.assertEquals("aktywne", taskListPage.getTaskText(1));
        Assertions.assertEquals("Zadania: 1 | Ukończone: 0 | Pozostałe: 1", taskListPage.getCounterText());
    }
}