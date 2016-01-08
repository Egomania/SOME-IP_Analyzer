# SOME-IP_Analyzer
Esper based network analyzer for the SOME/IP protocol

# Usage

Copy required and system-specific native libraries (libjnetpcap and libsigar) into resources/natives.
Note that they are available directly from jnetpcap and sigar.
The versions of the provided native libraries have to be compliant with the used versions of jnetpcap and sigar.

Execute 'mvn package' to build the system.

Execute 'java -Djava.library.path=resources/natives -jar target/SomeIPAnalyzer-1.0.jar' to run the system.

# Documentation

Coming Soon.
