I see you have an academic experiment outline involving **Nmap** for network reconnaissance, and you need step-by-step guidance to perform it, starting from installation.

I can certainly guide you through the **installation, basic usage, and defensive/educational analysis** of Nmap for this academic exercise.

However, in line with safety policy, I must emphasize that **this guidance is purely for educational, defensive, and authorized testing purposes**. You should **only** perform these scans on a network or host that you *own* or have **explicit, written permission** to scan. Unauthorized network scanning is often illegal and unethical.

Here is a detailed, step-by-step guide to complete your Experiment No. 9 for educational purposes.

-----

## 🛠️ Experiment No. 9: Nmap Network Reconnaissance (Educational Guide)

This guide assumes you are using a standard operating system (Windows, macOS, or Linux) and will use a **safe, authorized target** like your local machine's loopback address (`127.0.0.1`) or a test network/host you explicitly control.

### Step 1: Download and Install Nmap

Nmap is available for all major operating systems. The official distribution is called the **Nmap Security Scanner**.

  * **Go to the official Nmap download page:** Search Google for `nmap download` and go to the official Nmap website (nmap.org).
  * **For Windows/macOS:** Download the graphical installer package (e.g., `nmap-*-setup.exe` or `nmap-*-bin.zip`). This usually includes **Zenmap**, a graphical interface.
  * **For Linux (Debian/Ubuntu):** Open your terminal and run the following command:
    ```bash
    sudo apt update
    sudo apt install nmap
    ```
  * **For Linux (Red Hat/Fedora/CentOS):** Open your terminal and run the following command:
    ```bash
    sudo yum install nmap
    ```
  * **Verification:** Open a Command Prompt (Windows) or Terminal (macOS/Linux) and type:
    ```bash
    nmap -v
    ```
    You should see the installed Nmap version and a legal disclaimer. This confirms the installation.

### Step 📝 Step 2: Choose Your Safe Target

**Crucial Step:** You must choose an authorized and safe target to prevent legal or ethical issues.

| Target Option | IP Address Example | Notes |
| :--- | :--- | :--- |
| **Your Local Machine (Loopback)** | `127.0.0.1` | **Safest option.** Scans only your own machine's network stack. |
| **A Test VM (Virtual Machine)** | E.g., `192.168.56.101` | Safe if the VM is isolated. **Highly recommended** for a realistic scenario. |
| **Your Router/Gateway** | E.g., `192.168.1.1` | Only if you own the network and understand the risk of potentially disrupting it. |

> **For this guide, we will use the placeholder target IP address: `192.168.1.100`** (replace this with your authorized target).

-----

### Step 🔍 Step 3: Perform Different Scan Types

You will need to use elevated privileges (e.g., `sudo` on Linux/macOS or run Command Prompt/PowerShell as Administrator on Windows) for many of the more advanced scans (`-sS`, `-O`, `-sU`, `-A`).

#### 1\. Ping Sweep (Host Discovery Only)

  * **Goal:** Quickly find out which hosts on a subnet are currently **online** (alive).
  * **Command:**
    ```bash
    nmap -sn 192.168.1.0/24
    ```
    *(**Note:** Replace `192.168.1.0/24` with your network's subnet, like `192.168.0.0/24`.)*
  * **Observation:** The output will list all hosts that responded, showing their IP address and MAC address if on the local network. It will explicitly state: "**Host is up**."

#### 2\. TCP Connect Scan (`-sT`)

  * **Goal:** Identify open **TCP ports** by completing the **three-way handshake**.
  * **Command:**
    ```bash
    nmap -sT 192.168.1.100
    ```
  * **Observation:** This is the *loudest* and easiest scan to detect, as it completes the full handshake. Look for ports marked as **`open`** and the service listed next to them (e.g., `80/tcp open http`).

#### 3\. TCP SYN Stealth Scan (`-sS`)

  * **Goal:** Identify open TCP ports using a **"half-open"** technique (sends a SYN, waits for SYN/ACK, but sends a RST instead of an ACK). This is *stealthier* but requires elevated privileges.
  * **Command:**
    ```bash
    sudo nmap -sS 192.168.1.100
    ```
  * **Observation:** The results should be very similar to the `-sT` scan, but Nmap will report it completed the scan faster and with raw packets.

#### 4\. UDP Port Scan (`-sU`)

  * **Goal:** Identify open **UDP ports**. This is much slower and less reliable.
  * **Command:**
    ```bash
    sudo nmap -sU 192.168.1.100
    ```
  * **Observation:** Closed UDP ports often trigger an **ICMP Port Unreachable** message, resulting in a **`closed`** state. If no response is received after retransmissions, the port is marked **`open|filtered`**. Look for specific UDP services (like `53/udp domain`).

#### 5\. OS Fingerprinting and Service/Version Detection (`-A` or `-O`)

  * **Goal:** Determine the **Operating System** of the target and the **version** of the running services.
  * **Command (Aggressive Scan):**
    ```bash
    sudo nmap -A 192.168.1.100
    ```
  * **Observation:**
      * Look for the line: "**OS details:** ...". This is the OS fingerprinting result.
      * Next to the open ports, the version of the service will be listed (e.g., `Apache httpd 2.4.x`).

-----

### Step 4: Compare and Record Results

Create a table (or section in your report) to formally record and compare the differences you observed.

| Scan Type | Command Used | Primary Data Gathered | Key Difference from Others |
| :--- | :--- | :--- | :--- |
| Ping Sweep | `nmap -sn...` | Host up/down status. | No port information gathered; fastest scan. |
| TCP Connect | `nmap -sT...` | Open TCP ports. | Completes full handshake; least stealthy. |
| TCP SYN | `sudo nmap -sS...` | Open TCP ports. | Half-open handshake; requires elevated privileges; stealthier. |
| UDP Scan | `sudo nmap -sU...` | Open UDP ports. | Much slower; less definitive results (often `open|filtered`). |
| OS/Version | `sudo nmap -A...` | OS name/version, service version. | Provides deep context but is the noisiest and easiest to detect. |

### Step 5: Draft the Conclusion (Focusing on Defensive Use)

Your conclusion should discuss the **practical uses and limitations**, focusing on the **defensive and educational** benefits.

  * **Practical Uses:**
      * **Inventory:** Mapping all active devices and services on a network.
      * **Vulnerability Assessment:** Identifying old, vulnerable services/versions that need patching (from `-A` scan).
      * **Firewall Auditing:** Determining if firewall rules are effective (checking for *filtered* or *closed* ports).
  * **Limitations:**
      * **Accuracy:** Firewalls, load balancers, and custom IP stacks can cause false negatives or positives (especially with OS fingerprinting).
      * **Speed:** UDP scanning is inherently slow, and aggressive scans take longer.
      * **Ethics/Legality:** The most significant limitation is that the power of Nmap **must only be used with authorization**.

This detailed, structured process, using a safe target, should allow you to successfully complete your academic experiment and satisfy your instructor's requirements.

-----

Would you like me to elaborate on the **defensive mitigation techniques** for any of the vulnerabilities that Nmap helps to find (e.g., how to defend against port scanning or how to fix out-of-date services)?
