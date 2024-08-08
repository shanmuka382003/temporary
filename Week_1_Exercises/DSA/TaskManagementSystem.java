class Task {
    int taskId;
    String taskName;
    String status;
    Task next;

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
        this.next = null;
    }

    // Getters
    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getStatus() {
        return status;
    }
}

class TaskManagementSystem {
    private Task head;

    public TaskManagementSystem() {
        head = null;
    }

    // Add a new task at the end of the list
    public void addTask(int taskId, String taskName, String status) {
        Task newTask = new Task(taskId, taskName, status);
        if (head == null) {
            head = newTask; // If the list is empty
        } else {
            Task current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newTask; // Add at the end
        }
        System.out.println("Task added: " + taskName);
    }

    // Search for a task by ID
    public Task searchTask(int taskId) {
        Task current = head;
        while (current != null) {
            if (current.getTaskId() == taskId) {
                return current; // Task found
            }
            current = current.next;
        }
        return null; // Task not found
    }

    // Traverse and display all tasks
    public void traverseTasks() {
        Task current = head;
        if (current == null) {
            System.out.println("No tasks available.");
            return;
        }
        while (current != null) {
            System.out.println("ID: " + current.getTaskId() + 
                               ", Name: " + current.getTaskName() + 
                               ", Status: " + current.getStatus());
            current = current.next;
        }
    }

    // Delete a task by ID
    public boolean deleteTask(int taskId) {
        if (head == null) return false; // Empty list

        // If the task to be deleted is the head
        if (head.getTaskId() == taskId) {
            head = head.next; // Move head to the next task
            System.out.println("Task deleted: " + taskId);
            return true;
        }

        // Search for the task to delete
        Task current = head;
        while (current.next != null) {
            if (current.next.getTaskId() == taskId) {
                current.next = current.next.next; // Bypass the task to delete
                System.out.println("Task deleted: " + taskId);
                return true;
            }
            current = current.next;
        }
        System.out.println("Task not found: " + taskId);
        return false;
    }

    public static void main(String[] args) {
        TaskManagementSystem tms = new TaskManagementSystem();

        tms.addTask(1, "Design Database", "In Progress");
        tms.addTask(2, "Implement API", "Not Started");
        tms.addTask(3, "Write Documentation", "Completed");

        System.out.println("\nAll Tasks:");
        tms.traverseTasks();

        System.out.println("\nSearching for Task ID 2:");
        Task foundTask = tms.searchTask(2);
        if (foundTask != null) {
            System.out.println("Found: " + foundTask.getTaskName());
        } else {
            System.out.println("Task not found.");
        }

        System.out.println("\nDeleting Task ID 2:");
        tms.deleteTask(2);

        System.out.println("\nAll Tasks after deletion:");
        tms.traverseTasks();
    }
}
