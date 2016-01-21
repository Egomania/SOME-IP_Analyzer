# Configuration Files

## Basic Configuration File: config.properties

The configuration file is provided with -c option, e.g. resources/config/config.properties
The format is as follows:

``` ini
metaDataFile=resources/config/config.xml
verbose=false
monitoring=true
monitoringFile=resources/measurement/monitoring.csv
```

 * **metaDataFile** provides the use case specific meta data
 * **verbose** provides additional output on the console
 * **monitoring** start monitoring thread that is capturing memory usage
 * **monitoringFile** location of monitoring file, not that the information is appended

The meta data file is provided via configuration file, e.g. resources/config/config.xml.
It contains the list of servers and clients with corresponding IP and MAC addresses.

## Metadata File: config.xml

The meta datafile provides information about the running entities in the system.
The file is given as xml.
In the configuration you can specify servers as well as clients with their MAC and IP addresses.
This information is added as Esper internal variable that represent a list of all client and a list of all servers.
The can be accessed as follow:

``` sql
SELECT *
FROM SomeIPPacket s
WHERE s.srcIP in (clientIPs)
```

The meta data file has the following structure:

``` xml
<configuration>
	<servers>
		<server>
			<MAC>1A:AA:AA:AA:AA:AA</MAC>
			<IP>10.0.0.1</IP>
		</server>
	</servers>
	<clients>
		<client>
			<MAC>9B:BB:BB:BB:BB:BB</MAC>
			<IP>10.1.0.9</IP>
		</client>
	</clients>
</configuration>
```


