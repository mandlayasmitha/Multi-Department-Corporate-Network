# Routing and Inter-VLAN Communication – Test Plan

## Objective
Verify that the router correctly routes packets between the department VLANs.

## Preconditions
- VLANs 10, 20, 30, 40, 50, and 60 exist.
- The router-to-core link and access-switch uplinks are up.
- Router subinterfaces have the matching 802.1Q VLAN tags and gateway IP addresses.
- End devices have unique IP addresses, `/24` masks, and the correct default gateways.

## Tests

| Test | Command / action | Expected result |
|---|---|---|
| Check router interfaces | `show ip interface brief` | Physical interface and configured subinterfaces are up/up after links are connected |
| Check connected routes | `show ip route` | Connected routes for `192.168.10.0/24` through `192.168.60.0/24` appear |
| Check VLANs | `show vlan brief` | VLANs 10, 20, 30, 40, 50, and 60 appear |
| Check trunk | `show interfaces trunk` | Router-facing trunk carries VLANs 10,20,30,40,50,60 |
| Test gateway | From HR PC: `ping 192.168.10.1` | Replies from HR gateway |
| Test inter-VLAN | From HR PC: `ping 192.168.20.10` | Replies from Finance PC if configured with that address |
| Test another VLAN | From IT PC: `ping 192.168.60.10` | Replies from Server if configured with that address |

## Troubleshooting checklist
1. Confirm the cable and interface names match the topology.
2. Check that the router physical interface is not shutdown.
3. Check the subinterface VLAN ID with `encapsulation dot1Q`.
4. Ensure the router-facing switch port is a trunk and allows all required VLANs.
5. Ensure the access port belongs to the correct VLAN.
6. Confirm each endpoint's IP, subnet mask, and default gateway.
7. Check `show ip interface brief`, `show vlan brief`, `show interfaces trunk`, and `show ip route`.
8. Repeat the ping if the first attempt times out because of ARP resolution.

## Result template
After the tests are actually run, record the results here. Do not mark a test successful until it has been verified in Packet Tracer.

- Router interfaces: [Pass/Fail]
- VLAN and trunk configuration: [Pass/Fail]
- HR-to-Finance ping: [Pass/Fail]
- IT-to-Server ping: [Pass/Fail]
- Notes/screenshots: [add evidence]
