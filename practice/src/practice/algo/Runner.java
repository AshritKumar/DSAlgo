package practice.algo;

import java.util.ArrayList;
import java.util.HashMap;

public class Runner {
    private HashMap<Integer, Resource> resources = new HashMap<Integer, Resource>();

    public Iterable<Resource> getResources() {
        return this.resources.values();
    }

    public Resource acquireResource(int id) {
        Resource w = this.resources.getOrDefault(id, null);
        if (w == null) {
            w = new Resource(id);
            this.resources.put(id, w);
        }

        return w;
    }

    public void releaseResource(int id) {
        Resource w = this.resources.getOrDefault(id, null);
        if (w == null)
            throw new IllegalArgumentException();

        w.dispose();
    }

    public class Resource {
        private ArrayList<String> tasks = new ArrayList<String>();

        private int id;

        public int getId() {
            return this.id;
        }

        public Iterable<String> getTasks() {
            return this.tasks;
        }

        public Resource(int id) {
            this.id = id;
        }

        public void performTask(String task) {
            if (this.tasks == null)
                throw new IllegalStateException(this.getClass().getName());

            this.tasks.add(task);
        }

        public void dispose() {
            this.tasks = null;
        }
    }

    public static void main(String[] args) {
        Runner d = new Runner();
        Resource r1 = d.acquireResource(1);
        Resource r2 = d.acquireResource(2);
        r1.performTask("Task11");
        r2.performTask("Task21"); 
        ArrayList<String> tasks2 = (ArrayList<String>) r2.getTasks();
        printTasks(tasks2);
        d.releaseResource(2);
        r1.performTask("Task12");
        ArrayList<String> tasks1 = (ArrayList<String>)r1.getTasks();
        printTasks(tasks1);
        d.releaseResource(1);
    }

	private static void printTasks(ArrayList<String> tasks) {
		System.out.print(tasks.get(0));
		for(int i=1; i<tasks.size(); i++)
			System.out.print(", "+tasks.get(i));
		System.out.println();
		
	}
}