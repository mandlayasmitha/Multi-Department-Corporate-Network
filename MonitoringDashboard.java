import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MonitoringDashboard extends JFrame {

    private final DHCPServer dhcp;
    private final DNSServer dns;
    private final WebServer web;

    private JLabel dhcpStatus;
    private JLabel dnsStatus;
    private JLabel webStatus;
    private JLabel clients;
    private JLabel dnsRequests;
    private JLabel httpRequests;

    public MonitoringDashboard(
            DHCPServer dhcp,
            DNSServer dns,
            WebServer web) {

        this.dhcp = dhcp;
        this.dns = dns;
        this.web = web;

        setTitle("Corporate Network Monitoring Dashboard");

        setSize(750, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        createGUI();

        Timer timer = new Timer(1000, e -> updateDashboard());

        timer.start();
    }

    private void createGUI() {

        JPanel mainPanel = new JPanel();

        mainPanel.setBorder(
                new EmptyBorder(20, 20, 20, 20));

        mainPanel.setLayout(
                new BorderLayout(10, 10));

        JLabel title = new JLabel(
                "CORPORATE NETWORK MONITORING DASHBOARD",
                SwingConstants.CENTER);

        title.setFont(
                new Font("Arial", Font.BOLD, 22));

        mainPanel.add(title, BorderLayout.NORTH);

        JPanel services = new JPanel(
                new GridLayout(3, 2, 15, 15));

        services.setBorder(
                BorderFactory.createTitledBorder(
                        "Server Services"));

        dhcpStatus = new JLabel();
        dnsStatus = new JLabel();
        webStatus = new JLabel();

        clients = new JLabel();
        dnsRequests = new JLabel();
        httpRequests = new JLabel();

        services.add(new JLabel("DHCP Server"));
        services.add(dhcpStatus);

        services.add(new JLabel("DNS Server"));
        services.add(dnsStatus);

        services.add(new JLabel("Web Server"));
        services.add(webStatus);

        services.add(new JLabel("Active Clients"));
        services.add(clients);

        services.add(new JLabel("DNS Requests"));
        services.add(dnsRequests);

        services.add(new JLabel("HTTP Requests"));
        services.add(httpRequests);

        mainPanel.add(services, BorderLayout.CENTER);

        JPanel bottom = new JPanel();

        JLabel status = new JLabel(
                "Network Status: NORMAL");

        status.setFont(
                new Font("Arial", Font.BOLD, 16));

        bottom.add(status);

        mainPanel.add(bottom, BorderLayout.SOUTH);

        add(mainPanel);

        updateDashboard();
    }

    private void updateDashboard() {

        dhcpStatus.setText(
                dhcp != null ? "ONLINE" : "OFFLINE");

        dnsStatus.setText(
                dns != null ? "ONLINE" : "OFFLINE");

        webStatus.setText(
                web != null && web.isRunning()
                        ? "ONLINE"
                        : "OFFLINE");

        clients.setText(
                String.valueOf(
                        dhcp.getActiveClients()));

        dnsRequests.setText(
                String.valueOf(
                        dns.getRequestCount()));

        httpRequests.setText(
                String.valueOf(
                        web.getRequestCount()));
    }
}