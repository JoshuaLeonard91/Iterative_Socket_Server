# Iterative Socket Server

## Overview
This project involves the implementation of an **iterative (single-threaded) server** and a **multi-threaded client** to study the impact of server design on the efficiency (average turnaround time) of processing client requests. The project demonstrates how a server handles client requests serially and includes functionalities like fetching server stats (e.g., memory usage, uptime) and running system commands remotely.

## Features
- **Iterative Server**:
  - Handles one client request at a time using Java `ServerSocket`.
  - Provides responses for:
    - Current Date and Time
    - Server Uptime
    - Memory Usage
    - Netstat Output
    - List of Current Users
    - Running Processes
  - Accepts client connections and performs necessary operations serially.

- **Multi-Threaded Client**:
  - Sends multiple simultaneous requests to the server.
  - Collects turnaround time metrics for performance analysis.
  - Provides a user-friendly menu for requesting server stats.

## Requirements
- **Java Development Kit (JDK) 8 or higher**
- **Access to a Linux-based system** for executing system-level commands like `netstat`, `ps`, and `w`.
- Basic knowledge of socket programming. 
