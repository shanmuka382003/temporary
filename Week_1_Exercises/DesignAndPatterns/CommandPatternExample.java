// CommandPatternExample.java

// Command Interface
interface Command {
    void execute();
}

// Receiver Class
class Light {
    public void turnOn() {
        System.out.println("The light is ON");
    }

    public void turnOff() {
        System.out.println("The light is OFF");
    }
}

// Concrete Command to turn on the light
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

// Concrete Command to turn off the light
class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

// Invoker Class
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

// Test the Command Implementation
public class CommandPatternExample {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();
        
        // Create command objects
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);
        
        // Create invoker
        RemoteControl remote = new RemoteControl();
        
        // Turn on the light
        remote.setCommand(lightOn);
        remote.pressButton();
        
        // Turn off the light
        remote.setCommand(lightOff);
        remote.pressButton();
    }
}
