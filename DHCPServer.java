import java.util.*;
public class DHCPServer {
    private final Queue<String> availableIPs = new LinkedList<>();
    private final Map<String, String> assignedIPs = new HashMap<>();
    public DHCPServer() {
        for (int i = 10; i <= 50; i++) {
            availableIPs.add("192.168.1." + i);
        }
    }
    public synchronized String assignIP(String clientName) {
        if (assignedIPs.containsKey(clientName)) {
            return assignedIPs.get(clientName);
        }
        if (availableIPs.isEmpty()) {
            return "No IP available";
        }
        String ip = availableIPs.poll();
        assignedIPs.put(clientName, ip);
        return ip;
    }
    public Map<String, String> getAssignedIPs() {
        return assignedIPs;
    }
    public int getActiveClients() {
        return assignedIPs.size();
    }
}
