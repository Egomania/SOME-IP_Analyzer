package surf.SomeIPAnalyzer;

import java.util.*;

import org.javatuples.*;

import org.hyperic.sigar.ProcMem;
import org.hyperic.sigar.ProcCpu;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;


public class Monitor extends Thread
	{

		private volatile boolean execute;

		public ArrayList<Pair<java.sql.Timestamp, Long>> memVirtList;
		public ArrayList<Pair<java.sql.Timestamp, Long>> memShareList;
		public ArrayList<Pair<java.sql.Timestamp, Long>> memResList;
		public ArrayList<Pair<java.sql.Timestamp, Long>> cpuTotalList;
		private Sigar sigar;
		public long pid;

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

		public void stopExec() {
        		this.execute = false;
    		}

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


		private double memAverage(ArrayList<Pair<java.sql.Timestamp, Long>> list){
			if (list.size() == 0) return 0.0;
			double ave = 0.0;
			for (Pair<java.sql.Timestamp, Long> value: list)
				ave = ave + (Long) value.getValue(1);
			ave = ave / list.size();
			return ave;
		}

		public double memVirtAverage(){
			return memAverage(this.memVirtList);
		}
		public double memShareAverage(){
			return memAverage(this.memShareList);
		}
		public double memResAverage(){
			return memAverage(this.memResList);
		}
		public double cpuTotalAverage(){
			return memAverage(this.cpuTotalList);
		}

	}
