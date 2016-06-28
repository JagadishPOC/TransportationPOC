package com.poc.helpers;

import java.util.List;

public class RedBusDTO {

	private String[] amenitiesData;
	private String status;
	private List<RedBusDTO.Data> data;
	private String DefaultSorting;
	
	public RedBusDTO(){
		
	}
	public String[] getAmenitiesData ()
	{
		return amenitiesData;
	}

	public void setAmenitiesData (String[] amenitiesData)
	{
		this.amenitiesData = amenitiesData;
	}

	public String getStatus ()
	{
		return status;
	}

	public void setStatus (String status)
	{
		this.status = status;
	}

	public List<RedBusDTO.Data> getData ()
	{
		return data;
	}

	public void setData (List<RedBusDTO.Data> data) 
	{
		this.data = data;
	}

	public String getDefaultSorting ()
	{
		return DefaultSorting;
	}

	public void setDefaultSorting (String DefaultSorting)
	{
		this.DefaultSorting = DefaultSorting;
	}

	public static class Data{

		private String Glry;
		private String BsSvId;
		private String IsSpF;
		private String OpId;
		private String RbPrefCode;
		private String vt;
		private Param42 param42;
		private String BsSt;
		private DPLst[] DPLst;
		private String IsDPA;
		private String serviceName;
		private String IsStr;
		private String WnSt;
		private String IsSlpr;
		private Cmpg Cmpg;
		private BPLst[] BPLst;
		private String DpTm;
		private String[] FrLst;
		private String NSA;
		private String Sort;
		private String IsBpDpSearch;
		private String[] Ament;
		private String IsNAc;
		private String ArTm;
		private Rtg Rtg;
		private String Tvs;
		private String BsTp;
		private String serviceId;
		private String IsAc;
		private String IsMTE;
		private String jDur;
		private String RtId;
		private String Tips;

		public String getGlry ()
		{
			return Glry;
		}

		public void setGlry (String Glry)
		{
			this.Glry = Glry;
		}

		public String getBsSvId ()
		{
			return BsSvId;
		}

		public void setBsSvId (String BsSvId)
		{
			this.BsSvId = BsSvId;
		}

		public String getIsSpF ()
		{
			return IsSpF;
		}

		public void setIsSpF (String IsSpF)
		{
			this.IsSpF = IsSpF;
		}

		public String getOpId ()
		{
			return OpId;
		}

		public void setOpId (String OpId)
		{
			this.OpId = OpId;
		}

		public String getRbPrefCode ()
		{
			return RbPrefCode;
		}

		public void setRbPrefCode (String RbPrefCode)
		{
			this.RbPrefCode = RbPrefCode;
		}

		public String getVt ()
		{
			return vt;
		}

		public void setVt (String vt)
		{
			this.vt = vt;
		}

		public Param42 getParam42 ()
		{
			return param42;
		}

		public void setParam42 (Param42 param42)
		{
			this.param42 = param42;
		}

		public String getBsSt ()
		{
			return BsSt;
		}

		public void setBsSt (String BsSt)
		{
			this.BsSt = BsSt;
		}

		public DPLst[] getDPLst ()
		{
			return DPLst;
		}

		public void setDPLst (DPLst[] DPLst)
		{
			this.DPLst = DPLst;
		}

		public String getIsDPA ()
		{
			return IsDPA;
		}

		public void setIsDPA (String IsDPA)
		{
			this.IsDPA = IsDPA;
		}

		public String getServiceName ()
		{
			return serviceName;
		}

		public void setServiceName (String serviceName)
		{
			this.serviceName = serviceName;
		}

		public String getIsStr ()
		{
			return IsStr;
		}

		public void setIsStr (String IsStr)
		{
			this.IsStr = IsStr;
		}

		public String getWnSt ()
		{
			return WnSt;
		}

		public void setWnSt (String WnSt)
		{
			this.WnSt = WnSt;
		}

		public String getIsSlpr ()
		{
			return IsSlpr;
		}

		public void setIsSlpr (String IsSlpr)
		{
			this.IsSlpr = IsSlpr;
		}

		public Cmpg getCmpg ()
		{
			return Cmpg;
		}

		public void setCmpg (Cmpg Cmpg)
		{
			this.Cmpg = Cmpg;
		}

		public BPLst[] getBPLst ()
		{
			return BPLst;
		}

		public void setBPLst (BPLst[] BPLst)
		{
			this.BPLst = BPLst;
		}

		public String getDpTm ()
		{
			return DpTm;
		}

		public void setDpTm (String DpTm)
		{
			this.DpTm = DpTm;
		}

		public String[] getFrLst ()
		{
			return FrLst;
		}

		public void setFrLst (String[] FrLst)
		{
			this.FrLst = FrLst;
		}

		public String getNSA ()
		{
			return NSA;
		}

		public void setNSA (String NSA)
		{
			this.NSA = NSA;
		}

		public String getSort ()
		{
			return Sort;
		}

		public void setSort (String Sort)
		{
			this.Sort = Sort;
		}

		public String getIsBpDpSearch ()
		{
			return IsBpDpSearch;
		}

		public void setIsBpDpSearch (String IsBpDpSearch)
		{
			this.IsBpDpSearch = IsBpDpSearch;
		}

		public String[] getAment ()
		{
			return Ament;
		}

		public void setAment (String[] Ament)
		{
			this.Ament = Ament;
		}

		public String getIsNAc ()
		{
			return IsNAc;
		}

		public void setIsNAc (String IsNAc)
		{
			this.IsNAc = IsNAc;
		}

		public String getArTm ()
		{
			return ArTm;
		}

		public void setArTm (String ArTm)
		{
			this.ArTm = ArTm;
		}

		public Rtg getRtg ()
		{
			return Rtg;
		}

		public void setRtg (Rtg Rtg)
		{
			this.Rtg = Rtg;
		}

		public String getTvs ()
		{
			return Tvs;
		}

		public void setTvs (String Tvs)
		{
			this.Tvs = Tvs;
		}

		public String getBsTp ()
		{
			return BsTp;
		}

		public void setBsTp (String BsTp)
		{
			this.BsTp = BsTp;
		}

		public String getServiceId ()
		{
			return serviceId;
		}

		public void setServiceId (String serviceId)
		{
			this.serviceId = serviceId;
		}

		public String getIsAc ()
		{
			return IsAc;
		}

		public void setIsAc (String IsAc)
		{
			this.IsAc = IsAc;
		}

		public String getIsMTE ()
		{
			return IsMTE;
		}

		public void setIsMTE (String IsMTE)
		{
			this.IsMTE = IsMTE;
		}

		public String getJDur ()
		{
			return jDur;
		}

		public void setJDur (String jDur)
		{
			this.jDur = jDur;
		}

		public String getRtId ()
		{
			return RtId;
		}

		public void setRtId (String RtId)
		{
			this.RtId = RtId;
		}

		public String getTips ()
		{
			return Tips;
		}

		public void setTips (String Tips)
		{
			this.Tips = Tips;
		}
	}

	public static class DPLst{

		private String DateOfBoardingAlighting;

		private String bpTimeString;

		private String Tm;

		private String isDelayedPaymentAllowed;

		private String BpLandmark;

		private String Loc;

		private String BpAddress;

		private String ID;

		private String BpContactNo;

		private String Vbpname;

		private String bpAdd;
		
		

		public String getDateOfBoardingAlighting ()
		{
			return DateOfBoardingAlighting;
		}

		public void setDateOfBoardingAlighting (String DateOfBoardingAlighting)
		{
			this.DateOfBoardingAlighting = DateOfBoardingAlighting;
		}

		public String getBpTimeString ()
		{
			return bpTimeString;
		}

		public void setBpTimeString (String bpTimeString)
		{
			this.bpTimeString = bpTimeString;
		}

		public String getTm ()
		{
			return Tm;
		}

		public void setTm (String Tm)
		{
			this.Tm = Tm;
		}

		public String getIsDelayedPaymentAllowed ()
		{
			return isDelayedPaymentAllowed;
		}

		public void setIsDelayedPaymentAllowed (String isDelayedPaymentAllowed)
		{
			this.isDelayedPaymentAllowed = isDelayedPaymentAllowed;
		}

		public String getBpLandmark ()
		{
			return BpLandmark;
		}

		public void setBpLandmark (String BpLandmark)
		{
			this.BpLandmark = BpLandmark;
		}

		public String getLoc ()
		{
			return Loc;
		}

		public void setLoc (String Loc)
		{
			this.Loc = Loc;
		}

		public String getBpAddress ()
		{
			return BpAddress;
		}

		public void setBpAddress (String BpAddress)
		{
			this.BpAddress = BpAddress;
		}

		public String getID ()
		{
			return ID;
		}

		public void setID (String ID)
		{
			this.ID = ID;
		}

		public String getBpContactNo ()
		{
			return BpContactNo;
		}

		public void setBpContactNo (String BpContactNo)
		{
			this.BpContactNo = BpContactNo;
		}

		public String getVbpname ()
		{
			return Vbpname;
		}

		public void setVbpname (String Vbpname)
		{
			this.Vbpname = Vbpname;
		}

		public String getBpAdd ()
		{
			return bpAdd;
		}

		public void setBpAdd (String bpAdd)
		{
			this.bpAdd = bpAdd;
		}
	}

	public static class Rtg{

		private Rt Rt;

		private String totRt;

		public Rt getRt ()
		{
			return Rt;
		}

		public void setRt (Rt Rt)
		{
			this.Rt = Rt;
		}

		public String getTotRt ()
		{
			return totRt;
		}

		public void setTotRt (String totRt)
		{
			this.totRt = totRt;
		}
	}

	public static class Cmpg {

		private String Vld;

		public String getVld ()
		{
			return Vld;
		}

		public void setVld (String Vld)
		{
			this.Vld = Vld;
		}
	}

	public static class Param42 {

		private BoReqParams[] BoReqParams;

		private String[] restStopList;

		private String rgb;

		public BoReqParams[] getBoReqParams ()
		{
			return BoReqParams;
		}

		public void setBoReqParams (BoReqParams[] BoReqParams)
		{
			this.BoReqParams = BoReqParams;
		}

		public String[] getRestStopList ()
		{
			return restStopList;
		}

		public void setRestStopList (String[] restStopList)
		{
			this.restStopList = restStopList;
		}

		public String getRgb ()
		{
			return rgb;
		}

		public void setRgb (String rgb)
		{
			this.rgb = rgb;
		}
	}

	public static class NoRT{

		private String discUnit;

		private String cmpgDesc;

		private String msg;

		private String cmpgType;

		private String spcl;

		private String discount;

		private String[] cmpgNote;

		public String getDiscUnit ()
		{
			return discUnit;
		}

		public void setDiscUnit (String discUnit)
		{
			this.discUnit = discUnit;
		}

		public String getCmpgDesc ()
		{
			return cmpgDesc;
		}

		public void setCmpgDesc (String cmpgDesc)
		{
			this.cmpgDesc = cmpgDesc;
		}

		public String getMsg ()
		{
			return msg;
		}

		public void setMsg (String msg)
		{
			this.msg = msg;
		}

		public String getCmpgType ()
		{
			return cmpgType;
		}

		public void setCmpgType (String cmpgType)
		{
			this.cmpgType = cmpgType;
		}

		public String getSpcl ()
		{
			return spcl;
		}

		public void setSpcl (String spcl)
		{
			this.spcl = spcl;
		}

		public String getDiscount ()
		{
			return discount;
		}

		public void setDiscount (String discount)
		{
			this.discount = discount;
		}

		public String[] getCmpgNote ()
		{
			return cmpgNote;
		}

		public void setCmpgNote (String[] cmpgNote)
		{
			this.cmpgNote = cmpgNote;
		}
	}

	public static class BPLst {

		private String DateOfBoardingAlighting;

		private String bpTimeString;

		private String Tm;

		private String isDelayedPaymentAllowed;

		private String BpLandmark;

		private String Loc;

		private String BpAddress;

		private String ID;

		private String BpContactNo;

		private String Vbpname;

		private String bpAdd;

		public String getDateOfBoardingAlighting ()
		{
			return DateOfBoardingAlighting;
		}

		public void setDateOfBoardingAlighting (String DateOfBoardingAlighting)
		{
			this.DateOfBoardingAlighting = DateOfBoardingAlighting;
		}

		public String getBpTimeString ()
		{
			return bpTimeString;
		}

		public void setBpTimeString (String bpTimeString)
		{
			this.bpTimeString = bpTimeString;
		}

		public String getTm ()
		{
			return Tm;
		}

		public void setTm (String Tm)
		{
			this.Tm = Tm;
		}

		public String getIsDelayedPaymentAllowed ()
		{
			return isDelayedPaymentAllowed;
		}

		public void setIsDelayedPaymentAllowed (String isDelayedPaymentAllowed)
		{
			this.isDelayedPaymentAllowed = isDelayedPaymentAllowed;
		}

		public String getBpLandmark ()
		{
			return BpLandmark;
		}

		public void setBpLandmark (String BpLandmark)
		{
			this.BpLandmark = BpLandmark;
		}

		public String getLoc ()
		{
			return Loc;
		}

		public void setLoc (String Loc)
		{
			this.Loc = Loc;
		}

		public String getBpAddress ()
		{
			return BpAddress;
		}

		public void setBpAddress (String BpAddress)
		{
			this.BpAddress = BpAddress;
		}

		public String getID ()
		{
			return ID;
		}

		public void setID (String ID)
		{
			this.ID = ID;
		}

		public String getBpContactNo ()
		{
			return BpContactNo;
		}

		public void setBpContactNo (String BpContactNo)
		{
			this.BpContactNo = BpContactNo;
		}

		public String getVbpname ()
		{
			return Vbpname;
		}

		public void setVbpname (String Vbpname)
		{
			this.Vbpname = Vbpname;
		}

		public String getBpAdd ()
		{
			return bpAdd;
		}

		public void setBpAdd (String bpAdd)
		{
			this.bpAdd = bpAdd;
		}
	}
	public static class Rt
	{
		private String Ct;

		public String getCt ()
		{
			return Ct;
		}

		public void setCt (String Ct)
		{
			this.Ct = Ct;
		}
	}

	public static class BoReqParams
	{
		private String isBpDpReqTb;

		private String isBpDpReqSl;

		public String getIsBpDpReqTb ()
		{
			return isBpDpReqTb;
		}

		public void setIsBpDpReqTb (String isBpDpReqTb)
		{
			this.isBpDpReqTb = isBpDpReqTb;
		}

		public String getIsBpDpReqSl ()
		{
			return isBpDpReqSl;
		}

		public void setIsBpDpReqSl (String isBpDpReqSl)
		{
			this.isBpDpReqSl = isBpDpReqSl;
		}
	}
	
	public static class FrLst {
		
		
	}
}
