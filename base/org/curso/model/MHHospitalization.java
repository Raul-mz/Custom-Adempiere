package org.curso.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;


public class MHHospitalization extends X_H_Hospitalization {

	public MHHospitalization(Properties ctx, int H_Hospitalization_ID, String trxName) {
		super(ctx, H_Hospitalization_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHHospitalization(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * @see org.compiere.model.PO#afterSave(boolean, boolean)
	 */
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		// IsAttended
		MHBed bed;
		if(get_ValueOld(COLUMNNAME_H_Bed_ID) != null){
			int old=(int)get_ValueOld(COLUMNNAME_H_Bed_ID);
			if(old!=getH_Bed_ID()){
				bed = new MHBed(getCtx(),old,null);
				bed.setIsAvailable(true);
				bed.saveEx();
			}
		}

		bed = new MHBed(getCtx(),getH_Bed_ID(),null);
		bed.setIsAvailable(false);
		bed.saveEx();
			
		return super.afterSave(newRecord, success);
	}

	
	
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		
		return super.beforeSave(newRecord);
	}

	public static MHHospitalization[] getDate(Timestamp p_date){
		MHHospitalization[] m_Hospitalization;
		
		ArrayList<MHHospitalization> list = new ArrayList<MHHospitalization>();
		final String sql = "SELECT * FROM H_Hospitalization WHERE DateFinish <= ? "
				+ "AND H_Bed_ID Not IN (Select H_Bed_ID From H_Hospitalization "
				+ "Where DateFinish>=? )";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setTimestamp(1, p_date);
			pstmt.setTimestamp(2, p_date);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MHHospitalization (Env.getCtx(), rs,null));
		}
		catch (Exception e)
		{
//			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}	

		m_Hospitalization = new MHHospitalization[list.size()];
		list.toArray(m_Hospitalization);
		return m_Hospitalization;

				
	}
	/*
	 * (non-Javadoc)
	 * @see org.compiere.model.PO#afterDelete(boolean)
	 */
	@Override
	protected boolean afterDelete(boolean success) {
		// TODO Auto-generated method stub
		return super.afterDelete(success);
	}

}
