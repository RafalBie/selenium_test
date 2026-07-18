import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TaskListPage {
    private final WebDriver driver;

    public TaskListPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addTask(String taskName) {
        driver.findElement(By.id("todo-input")).sendKeys(taskName);
        driver.findElement(By.id("todo-add")).click();
    }

    public void markTaskAsDone(int taskId) {
        driver.findElement(By.id("todo-check-" + taskId)).click();
    }

    public void deleteTask(int taskId) {
        driver.findElement(By.id("todo-del-" + taskId)).click();
    }

    public void showAllTasks() {
        driver.findElement(By.id("todo-filter-all")).click();
    }

    public void showActiveTasks() {
        driver.findElement(By.id("todo-filter-active")).click();
    }

    public void showDoneTasks() {
        driver.findElement(By.id("todo-filter-done")).click();
    }

    public void clearDoneTasks() {
        driver.findElement(By.id("todo-clear-done")).click();
    }

    public int getTasksCount() {
        return driver.findElements(By.cssSelector("#todo-list li")).size();
    }

    public String getCounterText() {
        return driver.findElement(By.id("todo-count")).getText();
    }

    public String getMessageText() {
        return driver.findElement(By.id("todo-message")).getText();
    }

    public String getTaskText(int taskId) {
        return driver.findElement(By.id("todo-text-" + taskId)).getText();
    }

    public List<WebElement> getTasks() {
        return driver.findElements(By.cssSelector("#todo-list li"));
    }
}
