package singleton;

import java.io.*;

public class SerializationTest {
    static DemoSingleton instanceOne = DemoSingleton.getInstance();
 
    public static void main(String[] args) {
        try {
            // Serialize to a file
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream("demo.txt"));
            out.writeObject(instanceOne);
            out.close();
 
            instanceOne.setI(20);
 
            // Serialize to a file
            // de-serialization always creates a new instance.
            ObjectInput in = new ObjectInputStream(new FileInputStream("demo.txt"));
            DemoSingleton instanceTwo = (DemoSingleton) in.readObject();
            in.close();
 
            System.out.println(instanceOne.getI());
            System.out.println(instanceTwo.getI());
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
