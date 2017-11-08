package org.curso.process;
import java.sql.Timestamp;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.curso.model.MHBed;
import org.curso.model.MHHospitalization;

public class ReleaseBed extends SvrProcess {

	public ReleaseBed() {
		// TODO Auto-generated constructor stub
	}

	Timestamp p_DateFinish;
	@Override
	protected void prepare() {
		ProcessInfoParameter[] parameter = getParameter();
		for(int i=0; i < parameter.length; i++) {
			String name = parameter[i].getParameterName();
			if(name == null)
				;
			else if(name.equals("DateFinish")) {
				p_DateFinish = parameter[i].getParameterAsTimestamp();
			}
		}
	}

	@Override
	protected String doIt() throws Exception {

		MHHospitalization[] mHospitalization = MHHospitalization.getDate(p_DateFinish); 
		for(int x=0; x<mHospitalization.length; x++ ){
			int mBed_ID = mHospitalization[x].getH_Bed_ID();
			MHBed mBed = new MHBed(getCtx(), mBed_ID, get_TrxName());
			mHospitalization[x].setDateFinish();
			mHospitalization[x].saveEx();
			mBed.setIsAvailable(true);
			mBed.saveEx();
		}
		return null;
	}

}
