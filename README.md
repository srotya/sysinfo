# sysinfo
Sysinfo is a linux daemon written purely in Java that exposes OS info and metrics via a REST API.

After chasing around APIs and hacks around system stats, I finally wrote my own System statistics and info daemon that is written
purely in Java.

There are no hacks using JNI or JNA and the code is Java 1.5 compliant specifically so that you can deploy it with GCJ, providing 
full JVM free operation of the daemon.

Why REST? Push is not always the best solution to get system metrics, with this you can query metrics in real-time directly off the host.

Sysinfo has been written with cloud and high performance applications in mind and thus adds negligible overhead (in my tests, consumes less CPU than htop)

The license of this project is Apache 2.0.