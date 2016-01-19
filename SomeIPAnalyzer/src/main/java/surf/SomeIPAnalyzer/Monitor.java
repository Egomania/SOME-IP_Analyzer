package surf.SomeIPAnalyzer;

import java.util.*;

import org.javatuples.*;

import org.hyperic.sigar.ProcMem;
import org.hyperic.sigar.ProcCpu;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
* Monitoring Class for monitoring the Engine during runtime.
* Usage can be configured using configuration file.
* Uses a single thread.
*
*/

public class Monitor extends Thread
	{

		private volatile boolean execute;

		/** List for measurements of virtual memory usage. */
		public ArrayList<Pair<java.sql.Timestamp, Long>> memVirtList;
		/** List for measurements of shared memory usage. */
		public ArrayList<Pair<java.sql.Timestamp, Long>> memShareList;
		/** List for measurements of resident memory usage. */
		public ArrayList<Pair<java.sql.Timestamp, Long>> memResList;
		/** List for measurements of used CPU Time. */
		public ArrayList<Pair<java.sql.Timestamp, Long>> cpuTotalList;
		/** Private Sigar Instance used for measurements.*/
		private Sigar sigar;
		/** Current PID of the process. */
		public long pid;

		/**
		* Constructor to initialize all needed variables.
		*/
		public Monitor()
		{
			this.memVirtList = new ArrayList<Pair<java.sql.Timestamp, Long>>();
			this.memShareList = new ArrayList<Pair<java.sql.Timestamp, Long>>();
			this.memResList = new ArrayList<Pair<java.sql.Timestamp, Long>>();
			this.cpuTotalList = new ArrayList<Pair<java.sql.Timestamp, Long>>();

			this.sigar=new Sigar();
			this.pid = sigar.getPid();
			System.out.println("Monitor Started with: " + pid);
		}

		/**
		* Method to stop the Monitoring task externally.
		* Used from the main class.
		*/
		public void stopExec() {
        		this.execute = false;
    		}

		/**
		* A maesurement is done every 100ms and the statistics are updated.
		*/
		public void run()
		{
			this.execute = true;
			while (this.execute)
			{
				updateStats();
				try{
				Thread.sleep(100);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				
			}
		}

		/**
		* Updating the statistics (shared /virtual/ resident memory and CPU).
		* New measurement is appended to appropriate list.
		*/
		public void updateStats(){

			ProcMem procMem;
			ProcCpu procCpu;

			long time = System.currentTimeMillis();
			java.sql.Timestamp cur = new java.sql.Timestamp(time);

			try {
			    	procMem = sigar.getProcMem(pid);
				long virt = procMem.getSize();
				this.memVirtList.add(new Pair(cur,virt));
				long share = procMem.getShare();
				this.memShareList.add(new Pair(cur,share));
				long res = procMem.getResident();
				this.memResList.add(new Pair(cur,res));	

				procCpu = sigar.getProcCpu(pid);
				long total = procCpu.getTotal();
				this.cpuTotalList.add(new Pair(cur,total));
			
			} catch (SigarException e) {
			    e.printStackTrace();
			}
		}

		/**
		* Helper fucntion to calculate the average memory
		* @param list List of memory measurements to use.
		* @return avgerage memory consumption.
		*/
		private double memAverage(ArrayList<Pair<java.sql.Timestamp, Long>> list){
			if (list.size() == 0) return 0.0;
			double ave = 0.0;
			for (Pair<java.sql.Timestamp, Long> value: list)
				ave = ave + (Long) value.getValue(1);
			ave = ave / list.size();
			return ave;
		}

		/**
		* Function to calulate the virtual memory used in average.
		*/
		public double memVirtAverage(){
			return memAverage(this.memVirtList);
		}

		/**
		* Function to calulate the shared memory used in average.
		*/
		public double memShareAverage(){
			return memAverage(this.memShareList);

		/**
		* Function to calulate the resident memory used in average.
		*/
		}
		public double memResAverage(){
			return memAverage(this.memResList);
		}

	}
