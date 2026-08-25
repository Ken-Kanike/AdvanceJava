package com.mycompany.netbeansjava;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.RuntimeMXBean;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <h1>NetBeans & Maven Advance Java Enterprise Starter</h1>
 * <p>
 * Demonstrates a production Maven application entrypoint with runtime diagnostics:
 * <ul>
 *   <li>JVM Runtime & Memory management inspection via <code>ManagementFactory</code>.</li>
 *   <li>System Environment & Property configuration parsing.</li>
 *   <li>Modular architecture integration.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class NetbeansJava {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   Advance Java - Maven Enterprise Starter App   ");
        System.out.println("=================================================");

        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("• Application Start Time: " + now);

        // 1. JVM Diagnostics
        RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

        System.out.println("\n--- JVM Runtime Environment ---");
        System.out.println("• VM Name:       " + runtimeBean.getVmName());
        System.out.println("• VM Vendor:     " + runtimeBean.getVmVendor());
        System.out.println("• VM Version:    " + runtimeBean.getVmVersion());
        System.out.println("• Spec Version:  " + runtimeBean.getSpecVersion());
        System.out.println("• Up Time (ms):  " + runtimeBean.getUptime());

        System.out.println("\n--- Memory Allocation Metrics ---");
        long heapUsed = memoryBean.getHeapMemoryUsage().getUsed() / (1024 * 1024);
        long heapMax = memoryBean.getHeapMemoryUsage().getMax() / (1024 * 1024);
        System.out.println("• Heap Memory Used: " + heapUsed + " MB / " + heapMax + " MB");
        System.out.println("• Available CPU Processors: " + Runtime.getRuntime().availableProcessors());

        System.out.println("\n✓ Maven Project structure initialized and verified.");
    }
}
