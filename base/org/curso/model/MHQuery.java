package org.curso.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHQuery extends X_H_Query {

	public MHQuery(Properties ctx, int H_Query_ID, String trxName) {
		super(ctx, H_Query_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHQuery(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
