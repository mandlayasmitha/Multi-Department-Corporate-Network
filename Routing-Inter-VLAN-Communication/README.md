# Module 2 – Routing and Inter-VLAN Communication

## Project
**Design and Monitoring of Multi-Department Corporate Network**

## Objective
Configure VLANs and Router-on-a-Stick inter-VLAN routing in Cisco Packet Tracer, then test communication between department networks using `ping`.

> This module follows the VLAN plan documented in the main repository. It provides Cisco IOS configuration templates; adapt interface numbers to match your actual Packet Tracer topology before applying them.

## VLAN and IP addressing plan

| Department | VLAN | Network | Default gateway |
|---|---:|---|---|
| HR | 10 | `192.168.10.0/24` | `192.168.10.1` |
| Finance | 20 | `192.168.20.0/24` | `192.168.20.1` |
| IT | 30 | `192.168.30.0/24` | `192.168.30.1` |
| Sales | 40 | `192.168.40.0/24` | `192.168.40.1` |
| Management | 50 | `192.168.50.0/24` | `192.168.50.1` |
| Servers | 60 | `192.168.60.0/24` | `192.168.60.1` |

Subnet mask for all six networks: `255.255.255.0`.

## Suggested topology

- One router with an Ethernet interface connected to the core switch.
- One core switch.
- Department access switches for HR, Finance, IT, Sales, Management, and Servers (or a smaller lab topology if the full project topology is not built yet).
- At least one test PC in two different VLANs.

The example configurations assume:
- Router `G0/0` connects to core-switch `G0/1`.
- Core-switch `G0/2`–`G0/7` are trunk links to the HR, Finance, IT, Sales, Management, and Server access switches, respectively.
- Each access switch uses `G0/1` as its uplink and `F0/1`–`F0/24` for end devices.
- If your device model uses `Fa0/0`, `Gi0/0/0`, or different ports, replace the interface names accordingly.

## Files

- `configs/router-on-a-stick.txt` – router subinterfaces / gateways.
- `configs/core-switch.txt` – VLAN creation and trunk configuration.
- `configs/access-switch-template.txt` – access-switch template; change the VLAN ID for each department.
- `configs/pc-addressing.txt` – sample end-device IP settings.
- `configs/verification-commands.txt` – commands to verify routing and troubleshoot.
- `TEST-PLAN.md` – communication test plan and expected results.

## Implementation steps in Cisco Packet Tracer

1. Add the router, core switch, department access switches, and PCs/servers.
2. Connect router `G0/0` to core switch `G0/1`.
3. Configure the router using `configs/router-on-a-stick.txt`.
4. Configure the core switch using `configs/core-switch.txt`.
5. Configure each access switch using `configs/access-switch-template.txt`. Set the access port VLAN to the department's VLAN ID.
6. Set each PC's IPv4 address, subnet mask, and default gateway using `configs/pc-addressing.txt`.
7. Wait for links to become active, then run the verification commands.
8. Test between different VLANs using `ping`, for example from an HR PC to a Finance PC.

## Routing explanation

This design uses **Router-on-a-Stick**. A single physical router interface carries multiple VLANs over an 802.1Q trunk. Each router subinterface has a VLAN tag and serves as the default gateway for that VLAN. The router routes packets between the connected VLAN subnets.

Because all six VLAN networks are directly connected to this one router, IOS installs connected routes automatically. OSPF or static routes are not needed for communication among these six local VLANs alone. Additional routing is required only if the wider project adds other routers or remote networks.

## Important

- The VLAN IDs and subnet plan match the repository's current README.
- These are configuration templates, not a completed `.pkt` file. Save your configured Packet Tracer topology as a `.pkt` file after testing.
- Do not paste every access-switch configuration unchanged: change the VLAN ID for the department connected to that switch.
- The example assumes a router model that supports 802.1Q subinterfaces and a switch model that supports VLANs/trunking.
