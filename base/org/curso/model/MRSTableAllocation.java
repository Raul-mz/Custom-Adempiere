package org.curso.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.Query;
import org.compiere.util.DB;

public class MRSTableAllocation extends X_RS_Table_Allocation {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2289575789784745513L;

	/* (non-Javadoc)
	 * @see org.compiere.model.PO#beforeSave(boolean)
	 */
	@Override
	protected boolean beforeSave(boolean newRecord) {
//		MTableAllocation tableAllocation = new MTable;
/**		MRSTableAllocation[] m_TableAllocation = get();
		
		
		int m_Table_ID = getRS_Table_ID();
		
		
		for(int i=0; i<m_TableAllocation.length; i++){
			
			
			if(m_Table_ID == m_TableAllocation[i].getRS_Table_ID() 
					&& !m_TableAllocation[i].isClosed()) {
				log.saveError("Error", Msg.getMsg(getCtx(), "SaveIgnored"));
				return false;
			}
		}
	*/		
//		MRSTable table = new MRSTable(getCtx(), , get_TrxName());
		return true;
	}
	
	
	
	/* (non-Javadoc)
	 * @see org.compiere.model.PO#afterSave(boolean, boolean)
	 */
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		
		int m_Table_ID = getRS_Table_ID();
		
		MRSTable mTable = new MRSTable(getCtx(), m_Table_ID, get_TrxName());
		
		mTable.set_CustomColumn("IsAvailable", "N");
		mTable.saveEx();
		
		return super.afterSave(newRecord, success);
	}

	/**
	 * 
	 * @param ctx
	 * @param RS_Table_ID
	 * @param trxName
	 * @return
	 */
	public static List<MRSTableAllocation> getByTable(Properties ctx,
			int RS_Table_ID, String trxName){
		
		final String whereClause = "RS_Table_ID=?";
				
		Query q = new Query(ctx, "RS_Table_Allocation", 
						whereClause, trxName);
				
		q.setParameters(RS_Table_ID);
				
		return (q.list());
	}

	public static List<MRSTableAllocation> getByUser(Properties ctx,
			int AD_User_ID, String trxName){
		
		final String whereClause = "AD_User_ID=?";
				
		Query q = new Query(ctx, "RS_Table_Allocation", 
						whereClause, trxName);
				
		q.setParameters(AD_User_ID);
				
		return (q.list());
	}

	public MRSTableAllocation[] get() {
		
		MRSTableAllocation[] m_TableAllocation;
		
		ArrayList<MRSTableAllocation> list = new ArrayList<MRSTableAllocation>();
		final String sql = "SELECT * FROM RS_Table_Allocation";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
//			pstmt.setInt(1, getC_BPartner_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MRSTableAllocation (getCtx(), rs, get_TrxName()));
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		m_TableAllocation = new MRSTableAllocation[list.size()];
		list.toArray(m_TableAllocation);
		return m_TableAllocation;
		
	}

	public MRSTableAllocation(Properties ctx, int RS_Table_Allocation_ID, String trxName) {
		super(ctx, RS_Table_Allocation_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MRSTableAllocation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
