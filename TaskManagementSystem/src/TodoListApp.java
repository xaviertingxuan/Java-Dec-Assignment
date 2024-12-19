import java.util.ArrayList;
import java.util.Scanner;

// Task Class
class Task {
    private int id;
    private String title;
    private String description;

    public Task(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task ID: " + id + ", Title: " + title + ", Description: " + description;
    }
}

// Main CRUD Application
public class TodoListApp {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int taskIdCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nTodo List Manager");
            System.out.println("1. Create Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Update Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    createTask(scanner);
                    waitForUserInput(scanner);
                    break;
                case 2:
                    viewTasks(scanner);
                    break;
                case 3:
                    updateTask(scanner);
                    waitForUserInput(scanner);
                    break;
                case 4:
                    deleteTask(scanner);
                    waitForUserInput(scanner);
                    break;
                case 5:
                    System.out.println("Exiting the application. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    waitForUserInput(scanner);
            }
        }
    }

    private static void createTask(Scanner scanner) {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        Task newTask = new Task(taskIdCounter++, title, description);
        tasks.add(newTask);
        System.out.println("Task added successfully: " + newTask);
    }

    private static void viewTasks(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("\nCurrent Tasks:");
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
        waitForUserInput(scanner);
    }

    private static void updateTask(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available to update.");
            return;
        }

        System.out.print("Enter the task ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        for (Task task : tasks) {
            if (task.getId() == id) {
                System.out.print("Enter new task title: ");
                String newTitle = scanner.nextLine();
                System.out.print("Enter new task description: ");
                String newDescription = scanner.nextLine();
                task.setTitle(newTitle);
                task.setDescription(newDescription);
                System.out.println("Task updated successfully: " + task);
                return;
            }
        }
        System.out.println("Task with ID " + id + " not found.");
    }

    private static void deleteTask(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available to delete.");
            return;
        }

        System.out.print("Enter the task ID to delete: ");
        int id = scanner.nextInt();

        for (Task task : tasks) {
            if (task.getId() == id) {
                tasks.remove(task);
                System.out.println("Task deleted successfully.");
                return;
            }
        }
        System.out.println("Task with ID " + id + " not found.");
    }

    private static void waitForUserInput(Scanner scanner) {
        System.out.println("\nPress Enter to return to the main menu...");
        scanner.nextLine(); // Wait for the user to press Enter
    }
}