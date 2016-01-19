# Configuration Files

The configuration file is provided with -c option, e.g. resources/config/config.properties
The format is as follows:

 * metaDataFile=resources/config/config.xml # provide the use case specific meta data
 * verbose=false # additional output
 * monitoring=true # start monitoring thread that is capturing memory usage
 * monitoringFile=resources/measurement/monitoring.csv # location of monitoring file, not that the information is appended

The meta data file is provided via configuration file, e.g. resources/config/config.xml.
It contains the list of servers and clients with corresponding IP and MAC addresses.
