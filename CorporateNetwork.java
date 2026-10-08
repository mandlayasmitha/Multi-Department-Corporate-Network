import java.util.Scanner;

public class CorporateNetwork {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== VLAN NETWORK MANAGEMENT =====");
            System.out.println("1. View Departments");
            System.out.println("2. Check Communication");
            System.out.println("3. Monitor Network Status");
            System.out.println("4. View VLAN Details");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\n--- DEPARTMENTS ---");
                    System.out.println("HR | VLAN 10 | IP: 192.168.10.10");
                    System.out.println("Finance | VLAN 20 | IP: 192.168.20.10");
                    System.out.println("IT | VLAN 30 | IP: 192.168.30.10");
                    break;

                case 2:
                    System.out.println("\n1. HR");
                    System.out.println("2. Finance");
                    System.out.println("3. IT");

                    System.out.print("Select Source: ");
                    int source = sc.nextInt();

                    System.out.print("Select Destination: ");
                    int destination = sc.nextInt();

                    if (source == destination && source >= 1 && source <= 3) {
                        System.out.println("Communication Allowed");
                    } else if (source >= 1 && source <= 3 &&
                               destination >= 1 && destination <= 3) {
                        System.out.println("Communication Blocked");
                        System.out.println("Reason: Different VLANs");
                    } else {
                        System.out.println("Invalid Department");
                    }
                    break;

                case 3:
                    System.out.println("\n===== NETWORK STATUS =====");
                    System.out.println("PC0 | HR | VLAN 10 | Status: Active");
                    System.out.println("PC1 | Finance | VLAN 20 | Status: Active");
                    System.out.println("PC2 | IT | VLAN 30 | Status: Active");
                    break;

                case 4:
                    System.out.println("\n===== VLAN DETAILS =====");
                    System.out.println("VLAN 10 - HR");
                    System.out.println("VLAN 20 - Finance");
                    System.out.println("VLAN 30 - IT");
                    System.out.println("Each department is assigned a separate VLAN.");
                    break;

                case 5:
                    System.out.println("Exiting Network Management...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}