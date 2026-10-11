import java.util.*;
public class DNSServer {
    private final Map<String, String> dnsRecords = new HashMap<>();
    private int requestCount = 0;
    public DNSServer() {
        dnsRecords.put("www.company.com", "192.168.1.100");
        dnsRecords.put("mail.company.com", "192.168.1.101");
        dnsRecords.put("server.company.com", "192.168.1.102");
    }
    public synchronized String resolve(String domain) {
        requestCount++;
        return dnsRecords.getOrDefault(domain, "Domain not found");
    }
    public int getRequestCount() {
        return requestCount;
    }
    public Map<String, String> getDNSRecords() {
        return dnsRecords;
    }
}
