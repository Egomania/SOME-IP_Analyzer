package surf.SomeIPAnalyzer;

import java.util.*;

import org.javatuples.*;

import org.hyperic.sigar.ProcMem;
import org.hyperic.sigar.ProcCpu;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
* Parsed packets are passed to a seperate sender running in a single thread.
*/

public class EventSender extends Thread{

	/**
	* Standard Constructor.
	*/
	public EventSender(){

	}

	/**
	* Regarding the source of input, the interface is opened, a file is read or a whole directory is used.
	* The appropriate Parsing Option is choosen.
	* Claculation Times are printed afterwards.
	*/
	public void run()
	{

		long start_time, end_time, total_time, start_time_loc;
		
		start_time = System.currentTimeMillis();

		if (!SomeIPAnalyzer.Analyzer.INTERFACE.equals("")){
			SomeIPParser.inputFromInterface(SomeIPAnalyzer.Analyzer.INTERFACE, SomeIPAnalyzer.Analyzer.cepRT);
		}
		else{

			if (!SomeIPAnalyzer.Analyzer.FILE.equals(""))
			{
				System.out.println("Read "+ SomeIPAnalyzer.Analyzer.FILE + "...");
				SomeIPParser.inputFromFile(SomeIPAnalyzer.Analyzer.FILE, SomeIPAnalyzer.Analyzer.cepRT);
			}
			else
			{
				ArrayList<String> Files = helper.getFiles(SomeIPAnalyzer.Analyzer.DIR);
				for (String filename: Files)
				{
					if (!(filename.contains(".pcap")))
						continue;
					else
					{
						System.out.println("Read "+ filename + "...");
						start_time_loc = System.currentTimeMillis();
						SomeIPParser.inputFromFile(filename, SomeIPAnalyzer.Analyzer.cepRT);
						end_time = System.currentTimeMillis();
						total_time = end_time - start_time_loc;
						System.out.println("Single File done in: " + total_time + "ms");
					}
				}
			}
		}


		end_time = System.currentTimeMillis();
		total_time = end_time - start_time;
		System.out.println("Overall done in: " + total_time + "ms");
	
		return;

	}

}
