package com.poc.helpers;

import java.util.ArrayList;

public class RootObject {

	private int status;
	private ArrayList<Datum> data;
	private String DefaultSorting;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() { 
		return this.status; 
	}

	public void setStatus(int status) { 
		this.status = status; 
	}

	public ArrayList<Datum> getData() { 
		return this.data; 
	}

	public void setData(ArrayList<Datum> data) { 
		this.data = data; 
	}

	public String getDefaultSorting() { 
		return this.DefaultSorting; 
	}

	public void setDefaultSorting(String DefaultSorting) { 
		this.DefaultSorting = DefaultSorting; 
	}

	public static class Datum {

		private String serviceName;
		private String Tvs;

		private ArrayList<BPLst> BPLst;
		private ArrayList<Double> FrLst;
		private String BsTp;
		private int NSA;
		
		public String getTvs() {
			return Tvs;
		}

		public void setTvs(String tvs) {
			Tvs = tvs;
		}

		public int getNSA() { 
			return this.NSA;
		}

		public void setNSA(int NSA) { 
			this.NSA = NSA; 
		}

		public String getBsTp() { 
			return this.BsTp; 
		}

		public void setBsTp(String BsTp) { 
			this.BsTp = BsTp; 
		}

		public String getServiceName() { 
			return this.serviceName; 
		}

		public void setServiceName(String serviceName) { 
			this.serviceName = serviceName; 
		}

		public ArrayList<BPLst> getBPLst() { 
			return this.BPLst; 
		}

		public void setBPLst(ArrayList<BPLst> BPLst) { 
			this.BPLst = BPLst; 
		}

		public ArrayList<Double> getFrLst() { 
			return this.FrLst; 
		}

		public void setFrLst(ArrayList<Double> FrLst) { 
			this.FrLst = FrLst;
		}
	}

	public static class BPLst {

		private String bpAdd;
		private String Vbpname;
		public String getBpAdd() {
			return bpAdd;
		}
		public void setBpAdd(String bpAdd) {
			this.bpAdd = bpAdd;
		}
		public String getVbpname() {
			return Vbpname;
		}
		public void setVbpname(String vbpname) {
			Vbpname = vbpname;
		}
	}
}

