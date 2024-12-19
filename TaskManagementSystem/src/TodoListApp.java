import java.util.ArrayList;
import java.util.Scanner;

// Define an interface for Task operations
interface TaskOperations {
    void update(String title, String description);
    String getDetails();
}

// Base class for common task attributes
abstract class Task implements TaskOperations {
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

    @Override
    public void update(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public String getDetails() {
        return "Task ID: " + id + ", Title: " + title + ", Description: " + description;
    }
}

// Subclass for TodoTask
class TodoTask extends Task {
    private double priority; // Example of a double variable

    public TodoTask(int id, String title, String description, double priority) {
        super(id, title, description);
        this.priority = priority;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ", Priority: " + priority;
    }
}

// Main application
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
                case 1 -> createTask(scanner);
                case 2 -> viewTasks();
                case 3 -> updateTask(scanner);
                case 4 -> deleteTask(scanner);
                case 5 -> {
                    System.out.println("Exiting the application. Goodbye!");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
            waitForUserInput(scanner);
        }
    }

    private static void createTask(Scanner scanner) {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter task priority (1.0 - 5.0) 1.0 being highest priority: ");
        double priority = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character
        Task newTask = new TodoTask(taskIdCounter++, title, description, priority);
        tasks.add(newTask);
        System.out.println("Task added successfully: " + newTask.getDetails());
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("\nCurrent Tasks:");
            for (Task task : tasks) {
                System.out.println(task.getDetails());
            }
        }
    }

    private static void updateTask(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available to update.");
            return;
        }
        System.out.print("Enter the task ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        Task task = findTaskById(id);
        if (task != null) {
            System.out.print("Enter new task title: ");
            String newTitle = scanner.nextLine();
            System.out.print("Enter new task description: ");
            String newDescription = scanner.nextLine();
            if (task instanceof TodoTask) {
                System.out.print("Enter new task priority: ");
                double newPriority = scanner.nextDouble();
                scanner.nextLine(); // Consume newline character
                ((TodoTask) task).setPriority(newPriority);
            }
            task.update(newTitle, newDescription);
            System.out.println("Task updated successfully: " + task.getDetails());
        } else {
            System.out.println("Task with ID " + id + " not found.");
        }
    }

    private static void deleteTask(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available to delete.");
            return;
        }
        System.out.print("Enter the task ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        Task task = findTaskById(id);
        if (task != null) {
            tasks.remove(task);
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Task with ID " + id + " not found.");
        }
    }

    private static Task findTaskById(int id) {
        return tasks.stream().filter(task -> task.getId() == id).findFirst().orElse(null);
    }

    private static void waitForUserInput(Scanner scanner) {
        System.out.println("\nPress Enter to return to the main menu...");
        scanner.nextLine(); // Wait for the user to press Enter
    }
}