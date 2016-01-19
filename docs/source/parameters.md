# Parameters

 * Use -r to define Rules File. A sample is given in resources/rules/rules.config.
 * Use -d to define Directory containing .pcap-files, e.g. resources/traces.
 * Use -f to define .pcap File, e.g. resources/traces/trace.pcap.
 * Use -c to define Config File, e.g. resources/config/config.properties.
 * Use -i to define Live Capture Interface.

Configuration and Rules File are mandatory. 
You can choose to either capture from an interface (-i), read a directory containing .pcap files (-d) or directly provide a single .pcap file (-f).
Note that pcapng is not provided, you can use editcap for conversion.

