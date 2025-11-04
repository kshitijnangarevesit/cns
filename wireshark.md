I apologize for not being detailed enough in the first step\! You are absolutely right, choosing the correct download file and the installation process are crucial.

Let's start over, and I will provide **minute, detailed, step-by-step instructions** for the entire experiment. We will assume you are using a standard **Windows** or **macOS** computer, as these are the most common for student labs.

-----

## 🛠️ Experiment 8: Wireshark Detailed Step-by-Step Guide

### Part 1: Installation and Setup

#### Step 1.1: Download Wireshark (Detailed)

1.  **Open your web browser** (Chrome, Edge, Firefox, etc.).
2.  **Navigate to the official Wireshark website:** Go to `https://www.wireshark.org/download.html`.
3.  **Identify your Operating System (OS) and Architecture:**
      * **Windows:** Look for the section titled **"Windows Installer"**.
          * If your computer is a standard, modern (last 10 years) PC, choose the **"Windows Installer (64-bit)"**.
          * The **"arm64"** (ARM architecture) is generally only for specific, newer tablets or laptops that use ARM processors (like some Microsoft Surface models). If you are unsure, **choose the 64-bit installer**.
      * **macOS:** Look for the section titled **"macOS"**.
          * If you have a newer Mac with an Apple Silicon chip (M1, M2, M3), choose the **"macOS Arm 64-bit .dmg"**.
          * If you have an older Mac with an Intel chip, choose the **"macOS Intel 64-bit .dmg"**.
      * **Click the download link** for the appropriate installer (e.g., "Windows Installer (64-bit)").
4.  **Save the file:** Choose a location on your computer (like your Downloads folder) and wait for the download to complete.

#### Step 1.2: Install Wireshark (Detailed)

1.  **Locate the downloaded file** (it will likely be named something like `Wireshark-*-x64.exe` for Windows, or a `.dmg` file for macOS).

2.  **Double-click the installer file** to begin the process.

3.  **Windows Installation Process:**

      * **Welcome Screen:** Click **Next**.
      * **License Agreement:** Read the license (GPL) and click **I Agree**.
      * **Choose Components:** Leave all the default components checked (Wireshark, TShark, etc.) and click **Next**.
      * **Installation Options:** Leave the default options checked. Click **Next**.
      * **Choose Install Location:** Accept the default location unless you have a specific reason not to. Click **Next**.
      * **CRITICAL STEP: Npcap Installation:** You will see a prompt asking to install **Npcap**.
          * **MAKE SURE THE BOX IS CHECKED** to install Npcap. Wireshark **cannot** capture live packets without this driver. Click **Next**.
      * **USB Pcap (Optional):** You can skip this or leave it unchecked unless you specifically need to capture USB device traffic. Click **Install**.
      * **Npcap Setup Wizard:** A separate Npcap wizard will pop up.
          * Click **I Agree** for the Npcap license.
          * On the configuration screen, leave the default settings (especially **"Install Npcap in WinPcap API-compatible Mode"**). Click **Install**.
          * Wait for Npcap to finish, then click **Finish**.
      * **Wireshark Finish:** Back in the Wireshark installer, click **Next**, and then click **Finish**. You may be prompted to restart your computer. **It is highly recommended to restart now.**

4.  **macOS Installation Process (Using the .dmg file):**

      * Double-click the downloaded `.dmg` file.
      * Drag the **Wireshark application icon** into the **Applications folder** icon shown in the window.
      * Double-click the **"Install ChmodBPF"** package (or Npcap/Wireshark components installer) within the same window and follow the prompts to install the necessary packet capture libraries. You will need to enter your admin password.

-----

### Part 2: Observing Performance (Promiscuous vs. Non-Promiscuous)

#### Step 2.1: Prepare for Capture

1.  **Launch Wireshark:** Find and open the application from your Start Menu (Windows) or Applications folder (macOS).
2.  **Identify Interfaces:** In the main window, you will see a list of network interfaces (e.g., Wi-Fi, Ethernet, Loopback).
      * **Look for activity:** Identify the interface that is currently connected to the network and shows a small live graph of activity. This is your target interface.

#### Step 2.2: Non-Promiscuous Mode Capture (The Default)

1.  **Access Capture Options:** Go to the main menu bar and select **Capture $\rightarrow$ Options**.
2.  **Check Promiscuous Status:**
      * In the "Input" tab, select your target interface.
      * **CRITICAL:** Look for the **"Promiscuous mode"** setting. **Ensure it is set to "Off"** (or that the corresponding checkbox is **UNCHECKED**). This puts your NIC in non-promiscuous mode.
3.  **Start Capture:** Click the **Start** button in the bottom right of the "Capture Interfaces" window.
4.  **Generate Traffic (Crucial):** While Wireshark is running, open a command prompt (Windows) or Terminal (macOS) and execute a simple command:
      * `ping 8.8.8.8` (This is Google's DNS server, it generates ICMP packets).
      * Open your web browser and go to `https://www.google.com`.
5.  **Stop Capture:** Click the **red square Stop button** in the Wireshark toolbar.
6.  **Analyze (Non-Promiscuous):**
      * **Check Addresses:** Look closely at the **Source** and **Destination** columns in the Packet List pane (top pane).
      * **Observation:** You should see that **every single packet** either has your computer's IP address as the **Source** OR as the **Destination**. The NIC is only accepting packets explicitly meant for your device.

#### Step 2.3: Promiscuous Mode Capture

1.  **Clear the Old Capture:** Close the current capture window (File $\rightarrow$ Close).
2.  **Access Capture Options:** Go back to **Capture $\rightarrow$ Options**.
3.  **Enable Promiscuous Mode:**
      * Select your target interface.
      * **CRITICAL:** Look for the **"Promiscuous mode"** setting. **Ensure it is set to "On"** (or that the corresponding checkbox is **CHECKED**).
4.  **Start Capture:** Click the **Start** button.
5.  **Generate Traffic (Crucial):** Repeat the exact same actions as before:
      * `ping 8.8.8.8`
      * Browse to `https://www.google.com`.
      * If you are on a home/shared network, ask a friend or family member to browse the internet on a different device.
6.  **Stop Capture:** Click the **red square Stop button**.
7.  **Analyze (Promiscuous):**
      * **Filter out your traffic (temporarily):** In the **Display Filter** bar at the top, type: `not ip.addr == YOUR_IP_ADDRESS` (e.g., `not ip.addr == 192.168.1.10`). You can find your IP address by running `ipconfig` (Windows) or `ifconfig` (macOS/Linux) in the terminal.
      * **Observation:** After applying this filter, you might still see packets\! If you see packets whose Source and Destination IP addresses **DO NOT** belong to your computer, it proves that your NIC is capturing traffic meant for **other devices** on the network segment. This is the difference in performance.
      * **Note:** If you are on a highly switched network (most modern wired networks), you might not see much other traffic, but this is the correct procedure to prove the mode difference.

-----

### Part 3: Tracing Packets with Filters

Use the large capture file from **Step 2.3 (Promiscuous Mode)** to practice the filters.

| Filter Type | Display Filter Syntax | Action/Procedure | Expected Observation |
| :--- | :--- | :--- | :--- |
| **Protocol Filter** | `dns` | Type `dns` in the Display Filter bar and press Enter. | Only packets belonging to the **Domain Name System** (used for translating names like https://www.google.com/search?q=google.com to IP addresses) will be displayed. |
| **Port Filter** | `tcp.port == 443` | Type `tcp.port == 443` in the Display Filter bar and press Enter. | Only packets using **TCP port 443** (secure web traffic, **HTTPS**) will be displayed. |
| **Address Filter** | `ip.addr == 8.8.8.8` | Type `ip.addr == 8.8.8.8` and press Enter. | Only packets where Google's DNS server IP address is either the **Source** or the **Destination** will be displayed. |
| **String Filter** | `http contains "User-Agent"` | You must be viewing a capture with **unencrypted HTTP** traffic. Type the filter and press Enter. | Displays HTTP packets whose payload contains the text "User-Agent," which your browser sends to identify itself. |

#### Detailed Procedure for Applying Filters:

1.  **Reset View:** Delete any filter currently in the Display Filter bar and press Enter to see all captured packets.
2.  **Type Filter:** Carefully type the exact filter syntax (e.g., `tcp.port == 443`) into the long bar at the top, just below the menu bar.
3.  **Check Syntax:** As you type, the filter bar should turn **green**. If it turns **red**, the syntax is incorrect.
4.  **Apply Filter:** Press the **Enter** key.
5.  **Observe:** Check the result. To remove the filter, delete the text and press Enter again.

-----

### Part 4: Conclusion (Review)

Review your notes and fill in your experiment sheet with the following key differences:

  * **Non-Promiscuous Mode:** The NIC only accepts packets addressed specifically to its MAC address (or broadcast/multicast).
  * **Promiscuous Mode:** The NIC accepts **all** packets it sees on the segment, regardless of the destination MAC address. This provides a broader view for troubleshooting and security analysis.

This detailed breakdown should allow you to successfully perform every part of your experiment\!

Would you like me to help you find your computer's IP address so you can use it in the address filter step?
