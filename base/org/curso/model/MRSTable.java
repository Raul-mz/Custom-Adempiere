package org.curso.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.apps.ADialog;

public class MRSTable extends X_RS_Table {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8354106091974145428L;

	public MRSTable(Properties ctx, int RS_Table_ID, String trxName) {
		super (ctx, RS_Table_ID, trxName);
	}

	public MRSTable(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.compiere.model.PO#beforeSave(boolean)
	 */
	@Override
	protected boolean beforeSave(boolean newRecord) {
		
		return true;
				//super.beforeSave(newRecord);
	}

	/* (non-Javadoc)
	 * @see org.compiere.model.PO#afterSave(boolean, boolean)
	 */
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		// TODO Auto-generated method stub
		return super.afterSave(newRecord, success);
	}

	/* (non-Javadoc)
	 * @see org.compiere.model.PO#beforeDelete()
	 */
	@Override
	protected boolean beforeDelete() {
		
//		MRSTableAllocation m_TableAllocation = new MRSTableAllocation(getCtx()
//				, 0, get_TrxName());
//		
//		MRSTableAllocation[] tableAllocations = m_TableAllocation.get();
//		
//		int m_Table_ID=getRS_Table_ID();
//		
//		for(int i=0; i<tableAllocations.length; i++ ){
//			if(m_Table_ID == tableAllocations[i].getRS_Table_ID()){
//				tableAllocations[i].deleteEx(true);
//			}
//		}
//	
		int  RS_Table_ID=getRS_Table_ID();
		List<MRSTableAllocation> tableAllocations = MRSTableAllocation.
				getByTable(getCtx(), RS_Table_ID, get_TrxName());
		
		for(int i=0; i<tableAllocations.size(); i++ ){
			tableAllocations.get(i).deleteEx(true);
		}
		return super.beforeDelete();
	}

	/* (non-Javadoc)
	 * @see org.compiere.model.PO#afterDelete(boolean)
	 */
	@Override
	protected boolean afterDelete(boolean success) {
		// TODO Auto-generated method stub
		return super.afterDelete(success);
	}
	
	

}
