
import java.util.*;

public class Maincopy {

    // Represents a PC connected to a VLAN
    static class PC {
        String name;
        String ip;
        String gateway;
        int vlan;

        PC(String name, String ip, String gateway, int vlan) {
            this.name = name;
            this.ip = ip;
            this.gateway = gateway;
            this.vlan = vlan;
        }
    }

    // Represents a router subinterface
    static class RouterInterface {
        int vlan;
        String gateway;
        String subnet;

        RouterInterface(int vlan, String gateway, String subnet) {
            this.vlan = vlan;
            this.gateway = gateway;
            this.subnet = subnet;
        }
    }

    static Map<Integer, RouterInterface> routes = new LinkedHashMap<>();
    static Map<String, PC> computers = new LinkedHashMap<>();
    static Set<Integer> allowedVlans = new HashSet<>();

    static void setupNetwork() {
        // Router-on-a-Stick: one logical interface per VLAN
        routes.put(10, new RouterInterface(
                10, "192.168.10.1", "192.168.10.0/24"));

        routes.put(20, new RouterInterface(
                20, "192.168.20.1", "192.168.20.0/24"));

        // VLANs allowed on the simulated trunk
        allowedVlans.add(10);
        allowedVlans.add(20);

        computers.put("finance", new PC(
                "Finance PC", "192.168.10.10",
                "192.168.10.1", 10));

        computers.put("hr", new PC(
                "HR PC", "192.168.20.10",
                "192.168.20.1", 20));
    }

    static void showNetwork() {
        System.out.println("\n--- NETWORK CONFIGURATION ---");

        for (PC pc : computers.values()) {
            System.out.println(
                pc.name + " | IP: " + pc.ip
                + " | VLAN: " + pc.vlan
                + " | Gateway: " + pc.gateway
            );
        }

        System.out.println("\n--- ROUTER INTERFACES ---");
        for (RouterInterface ri : routes.values()) {
            System.out.println(
                "VLAN " + ri.vlan
                + " | Gateway: " + ri.gateway
                + " | Network: " + ri.subnet
            );
        }

        System.out.println("\nTrunk VLANs: " + allowedVlans);
    }

    static void simulatePing(String sourceKey, String targetKey) {
        PC source = computers.get(sourceKey);
        PC target = computers.get(targetKey);

        if (source == null || target == null) {
            System.out.println("Error: PC not found.");
            return;
        }

        System.out.println("\n--- SIMULATED PING ---");
        System.out.println(source.name + " -> " + target.name);
        System.out.println("Destination IP: " + target.ip);

        // Check whether both endpoints have router gateways
        RouterInterface sourceRoute = routes.get(source.vlan);
        RouterInterface targetRoute = routes.get(target.vlan);

        if (sourceRoute == null || targetRoute == null) {
            System.out.println("FAILED: VLAN gateway is missing.");
            return;
        }

        if (!source.gateway.equals(sourceRoute.gateway)
                || !target.gateway.equals(targetRoute.gateway)) {
            System.out.println("FAILED: Incorrect default gateway.");
            return;
        }

        // Check whether the trunk carries the required VLANs
        if (!allowedVlans.contains(source.vlan)
                || !allowedVlans.contains(target.vlan)) {
            System.out.println("FAILED: VLAN is not allowed on trunk.");
            return;
        }

        System.out.println("1. Source PC sends traffic to its gateway.");
        System.out.println("2. Switch identifies VLAN " + source.vlan + ".");

        if (source.vlan == target.vlan) {
            System.out.println(
                "3. Both PCs are in the same VLAN; "
                + "inter-VLAN routing is not required."
            );
        } else {
            System.out.println("3. Router receives VLAN "
                    + source.vlan + " traffic.");
            System.out.println("4. Router looks up destination network "
                    + targetRoute.subnet + ".");
            System.out.println("5. Router forwards traffic into VLAN "
                    + target.vlan + ".");
        }

        System.out.println("6. Destination PC receives the simulated packet.");
        System.out.println("\nSUCCESS: Simulated ping successful!");
        System.out.println("Reply from " + target.ip
                + ": simulated response received.");
    }

    public static void main(String[] args) {
        setupNetwork();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n==============================");
            System.out.println(" INTER-VLAN ROUTING DEMO");
            System.out.println("==============================");
            System.out.println("1. Show network configuration");
            System.out.println("2. Ping Finance PC -> HR PC");
            System.out.println("3. Ping HR PC -> Finance PC");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    showNetwork();
                    break;
                case "2":
                    simulatePing("finance", "hr");
                    break;
                case "3":
                    simulatePing("hr", "finance");
                    break;
                case "4":
                    System.out.println("Demo ended.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Select 1 to 4.");
            }
        }
    }
}