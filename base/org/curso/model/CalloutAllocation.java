package org.curso.model;

import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;

public class CalloutAllocation extends CalloutEngine {

	public CalloutAllocation() {
		// TODO Auto-generated constructor stub
	}
	
	public String TableAllocation (Properties ctx, int WindowNo, 
			GridTab mTab, GridField mField, Object value) {
		
		int m_TableAllocation_ID = (int)mTab.getValue(mField);
		
		MRSTableAllocation m_Table_Allocation = new MRSTableAllocation
				(ctx, m_TableAllocation_ID, null);
		
		mTab.setValue("C_BPartner_ID", m_Table_Allocation.getC_BPartner_ID());
		mTab.setValue("AD_User_ID", m_Table_Allocation.getAD_User_ID());
		
		return "";
	}	//	priceList

}
