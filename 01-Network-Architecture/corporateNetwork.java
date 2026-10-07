import java.util.ArrayList;
import java.util.Scanner;

// ---------------- DEVICE CLASS ----------------
class Device {
    String name;
    String type;
    String ipAddress;
    int vlan;

    Device(String name, String type, String ipAddress, int vlan) {
        this.name = name;
        this.type = type;
        this.ipAddress = ipAddress;
        this.vlan = vlan;
    }

    void display() {
        System.out.println(
            name + " | " +
            type + " | IP: " +
            ipAddress + " | VLAN: " +
            vlan
        );
    }
}

// ---------------- CONNECTION CLASS ----------------
class Connection {
    Device device1;
    Device device2;

    Connection(Device device1, Device device2) {
        this.device1 = device1;
        this.device2 = device2;
    }

    void display() {
        System.out.println(
            device1.name + " <----> " + device2.name
        );
    }
}

// ---------------- DEPARTMENT CLASS ----------------
class Department {
    String name;
    int vlan;
    ArrayList<Device> devices = new ArrayList<>();

    Department(String name, int vlan) {
        this.name = name;
        this.vlan = vlan;
    }

    void addDevice(Device device) {
        devices.add(device);
    }

    void display() {

        System.out.println("\n--------------------------------");
        System.out.println("Department : " + name);
        System.out.println("VLAN       : " + vlan);
        System.out.println("--------------------------------");

        for (Device device : devices) {
            device.display();
        }
    }
}

// ---------------- MAIN CLASS ----------------
public class corporateNetwork {

    static ArrayList<Device> devices = new ArrayList<>();
    static ArrayList<Connection> connections = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ------------------------------------
        // CREATE NETWORK DEVICES
        // ------------------------------------

        Device router = new Device(
            "Main-Router",
            "Router",
            "192.168.1.1",
            0
        );

        Device coreSwitch = new Device(
            "Core-Switch",
            "Switch",
            "192.168.1.2",
            0
        );

        devices.add(router);
        devices.add(coreSwitch);

        // ------------------------------------
        // CREATE DEPARTMENTS
        // ------------------------------------

        Department hr =
            new Department("HR", 10);

        Department finance =
            new Department("Finance", 20);

        Department it =
            new Department("IT", 30);

        Department sales =
            new Department("Sales", 40);

        Department management =
            new Department("Management", 50);

        Department servers =
            new Department("Servers", 60);

        // ------------------------------------
        // HR DEVICES
        // ------------------------------------

        Device hr1 = new Device(
            "HR-PC1",
            "Computer",
            "192.168.10.10",
            10
        );

        Device hr2 = new Device(
            "HR-PC2",
            "Computer",
            "192.168.10.11",
            10
        );

        hr.addDevice(hr1);
        hr.addDevice(hr2);

        // ------------------------------------
        // FINANCE DEVICES
        // ------------------------------------

        Device finance1 = new Device(
            "Finance-PC1",
            "Computer",
            "192.168.20.10",
            20
        );

        Device finance2 = new Device(
            "Finance-PC2",
            "Computer",
            "192.168.20.11",
            20
        );

        finance.addDevice(finance1);
        finance.addDevice(finance2);

        // ------------------------------------
        // IT DEVICES
        // ------------------------------------

        Device it1 = new Device(
            "IT-PC1",
            "Computer",
            "192.168.30.10",
            30
        );

        Device it2 = new Device(
            "IT-PC2",
            "Computer",
            "192.168.30.11",
            30
        );

        it.addDevice(it1);
        it.addDevice(it2);

        // ------------------------------------
        // SALES DEVICES
        // ------------------------------------

        Device sales1 = new Device(
            "Sales-PC1",
            "Computer",
            "192.168.40.10",
            40
        );

        Device sales2 = new Device(
            "Sales-PC2",
            "Computer",
            "192.168.40.11",
            40
        );

        sales.addDevice(sales1);
        sales.addDevice(sales2);

        // ------------------------------------
        // MANAGEMENT
        // ------------------------------------

        Device management1 = new Device(
            "Management-PC1",
            "Computer",
            "192.168.50.10",
            50
        );

        management.addDevice(management1);

        // ------------------------------------
        // SERVERS
        // ------------------------------------

        Device webServer = new Device(
            "Web-Server",
            "Server",
            "192.168.60.10",
            60
        );

        Device dnsServer = new Device(
            "DNS-Server",
            "Server",
            "192.168.60.11",
            60
        );

        servers.addDevice(webServer);
        servers.addDevice(dnsServer);

        // Add all devices
        devices.add(hr1);
        devices.add(hr2);

        devices.add(finance1);
        devices.add(finance2);

        devices.add(it1);
        devices.add(it2);

        devices.add(sales1);
        devices.add(sales2);

        devices.add(management1);

        devices.add(webServer);
        devices.add(dnsServer);

        // ------------------------------------
        // CREATE CONNECTIONS
        // ------------------------------------

        connections.add(
            new Connection(router, coreSwitch)
        );

        connections.add(
            new Connection(coreSwitch, hr1)
        );

        connections.add(
            new Connection(coreSwitch, finance1)
        );

        connections.add(
            new Connection(coreSwitch, it1)
        );

        connections.add(
            new Connection(coreSwitch, sales1)
        );

        connections.add(
            new Connection(coreSwitch, management1)
        );

        connections.add(
            new Connection(coreSwitch, webServer)
        );

        // ------------------------------------
        // DISPLAY NETWORK
        // ------------------------------------

        System.out.println();
        System.out.println("==============================================");
        System.out.println("   MULTI-DEPARTMENT CORPORATE NETWORK");
        System.out.println("==============================================");

        System.out.println("\nNETWORK DEVICES");

        System.out.println("----------------------------------------------");

        router.display();
        coreSwitch.display();

        // Display departments

        hr.display();
        finance.display();
        it.display();
        sales.display();
        management.display();
        servers.display();

        // ------------------------------------
        // DISPLAY CONNECTIONS
        // ------------------------------------

        System.out.println("\n\nNETWORK CONNECTIONS");

        System.out.println("----------------------------------------------");

        for (Connection connection : connections) {
            connection.display();
        }

        // ------------------------------------
        // NETWORK TOPOLOGY
        // ------------------------------------

        System.out.println("\n\nNETWORK TOPOLOGY");

        System.out.println("----------------------------------------------");

        System.out.println(
            "Internet"
        );

        System.out.println(
            "   |"
        );

        System.out.println(
            "Main-Router"
        );

        System.out.println(
            "   |"
        );

        System.out.println(
            "Core-Switch"
        );

        System.out.println(
            "   |"
        );

        System.out.println(
            "------------------------------------------------"
        );

        System.out.println(
            " |        |        |        |        |        |"
        );

        System.out.println(
            "HR     Finance     IT     Sales   Management  Servers"
        );

        System.out.println(
            "V10      V20      V30      V40       V50       V60"
        );

        // ------------------------------------
        // CONNECTION TEST
        // ------------------------------------

        System.out.println("\n\nCONNECTION TEST");

        System.out.println("----------------------------------------------");

        System.out.println(
            "Enter source device name:"
        );

        String source = sc.nextLine();

        System.out.println(
            "Enter destination device name:"
        );

        String destination = sc.nextLine();

        boolean connected = false;

        for (Connection connection : connections) {

            if (
                (connection.device1.name.equalsIgnoreCase(source)
                &&
                connection.device2.name.equalsIgnoreCase(destination))
                ||
                (connection.device1.name.equalsIgnoreCase(destination)
                &&
                connection.device2.name.equalsIgnoreCase(source))
            ) {

                connected = true;
                break;
            }
        }

        if (connected) {

            System.out.println(
                "\nResult: CONNECTION AVAILABLE"
            );

            System.out.println(
                source + " <----> " + destination
            );

        } else {

            System.out.println(
                "\nResult: DIRECT CONNECTION NOT AVAILABLE"
            );

            System.out.println(
                "Traffic must pass through the network infrastructure."
            );
        }

        System.out.println(
            "\n=============================================="
        );

        System.out.println(
            "Network Architecture Simulation Completed"
        );

        System.out.println(
            "=============================================="
        );

        sc.close();
    }
}