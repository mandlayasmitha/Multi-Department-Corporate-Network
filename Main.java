import javax.swing.*;
import java.io.IOException;
public class Main {
    public static void main(String[] args) {
        DHCPServer dhcp = new DHCPServer();
        DNSServer dns = new DNSServer();
        WebServer web = new WebServer();
        try {
            web.start();
            dhcp.assignIP("PC-01");
            dhcp.assignIP("PC-02");
            dhcp.assignIP("PC-03");
            System.out.println(
                    "DNS Test:");
            System.out.println(
                    "www.company.com -> "
                    + dns.resolve("www.company.com"));
            System.out.println(
                    "\nDHCP Assignments:");
            dhcp.getAssignedIPs()
                    .forEach(
                            (client, ip) ->
                                    System.out.println(
                                            client + " -> " + ip));
            SwingUtilities.invokeLater(() -> {

                MonitoringDashboard dashboard =
                        new MonitoringDashboard(
                                dhcp,
                                dns,
                                web);

                dashboard.setVisible(true);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}