package com.example.demo.query;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TasksQuery {
	
	private final EntityManager em;
	
	public Query findBalanceByAccount(String id) {
		StringBuffer sb = new StringBuffer();
		
		sb.append("SELECT ACCOUNT_NO AS accountNo ");
		sb.append("     , C.DEPOSIT - C.WITHDRAWAL AS balance ");
		sb.append("  FROM ( ");
		sb.append("        SELECT A.ID ");
		sb.append("             , B.ACCOUNT_NO ");
		sb.append("             , SUM(CASE WHEN B.IO_YN = 'Y' ");
		sb.append("                        THEN B.DEPOSIT_PRC ");
		sb.append("                        ELSE 0 ");
		sb.append("                   END) AS DEPOSIT ");
		sb.append("             , SUM(CASE WHEN B.IO_YN = 'N' ");
		sb.append("                        THEN B.DEPOSIT_PRC ");
		sb.append("                        ELSE 0 ");
		sb.append("                   END) AS WITHDRAWAL ");
		sb.append("          FROM ACCOUNT A ");
		sb.append("          JOIN ACCOUNT_HST B ");
		sb.append("            ON A.ACCOUNT_NO = B.ACCOUNT_NO ");
		sb.append("         WHERE A.ID = "+id+" ");
		sb.append("         GROUP BY B.ACCOUNT_NO ");
		sb.append("       ) C ");
		
		String sql = sb.toString();
		Query query = em.createNativeQuery(sql);	
		
		return query;
	}
	
	public Query findAvgBalanceByAge() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("SELECT D.AGE_GBN AS ageGbn ");
		sb.append("     , SUM(D.DEPOSIT) - SUM(D.WITHDRAWAL) AS balance ");
		sb.append("  FROM ( ");
		sb.append("        SELECT CONCAT(FLOOR(A.AGE/10), 0) AS AGE_GBN ");
		sb.append("             , CASE WHEN C.IO_YN = 'Y' ");
		sb.append("                    THEN C.DEPOSIT_PRC ");
		sb.append("                    ELSE 0 ");
		sb.append("               END DEPOSIT ");
		sb.append("             , CASE WHEN C.IO_YN = 'N' ");
		sb.append("                    THEN C.DEPOSIT_PRC ");
		sb.append("                    ELSE 0 ");
		sb.append("               END WITHDRAWAL ");
		sb.append("          FROM USER A ");
		sb.append("          JOIN ACCOUNT B ");
		sb.append("            ON A.ID = B.ID ");
		sb.append("          JOIN ACCOUNT_HST C ");
		sb.append("            ON B.ACCOUNT_NO = C.ACCOUNT_NO ");
		sb.append("       ) D ");
		sb.append(" GROUP BY D.AGE_GBN ");
		
		String sql = sb.toString();
		Query query = em.createNativeQuery(sql);	
		
		return query;
	}
	
	public Query findBalanceByYear(String year) {
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT DEPOSIT - WITHDRAWAL AS BALANCE ");
		sb.append("   FROM ( ");
		sb.append("		    SELECT SUM(CASE WHEN A.IO_YN = 'Y' ");
		sb.append("                         THEN A.DEPOSIT_PRC ");
		sb.append("                         ELSE 0 ");
		sb.append("                    END) AS DEPOSIT ");
		sb.append("              , SUM(CASE WHEN A.IO_YN = 'N' ");
		sb.append("                         THEN A.DEPOSIT_PRC ");
		sb.append("                         ELSE 0 ");
		sb.append("                    END) AS WITHDRAWAL ");
		sb.append("           FROM ACCOUNT_HST A ");
		sb.append("          WHERE SUBSTR(A.DEPOSIT_DT, 1, 4) = "+year+" ");
		sb.append("        ) B ");
		String sql = sb.toString();
		Query query = em.createNativeQuery(sql);	
		
		return query;
	}
	
	public Query findBalanceBySortingId(String fromDate, String toDate) {
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT D.ID AS id ");
		sb.append("      , D.NAME AS name ");
		sb.append(" 	  , SUM(D.DEPOSIT) - SUM(D.WITHDRAWAL) AS balance ");
		sb.append("   FROM ( ");
		sb.append(" 		 SELECT C.ID ");
		sb.append(" 			  , C.NAME ");
		sb.append(" 			  , CASE WHEN IO_YN = 'Y' ");
		sb.append(" 					 THEN DEPOSIT_PRC ");
		sb.append(" 					 ELSE 0 ");
		sb.append(" 			    END DEPOSIT ");
		sb.append(" 			  , CASE WHEN IO_YN = 'N' ");
		sb.append(" 					 THEN DEPOSIT_PRC ");
		sb.append(" 					 ELSE 0 ");
		sb.append(" 			    END WITHDRAWAL ");
		sb.append(" 		   FROM ACCOUNT_HST A ");
		sb.append(" 		   JOIN ACCOUNT B ");
		sb.append(" 		 	 ON A.ACCOUNT_NO = B.ACCOUNT_NO ");
		sb.append(" 		   JOIN USER C ");
		sb.append(" 			 ON B.ID = C.ID ");
		sb.append(" 		  WHERE A.DEPOSIT_DT BETWEEN '"+fromDate+"' AND '"+toDate+"' ");
		sb.append(" 	    ) D ");
		sb.append("   GROUP BY D.ID ");
		sb.append("          , D.NAME ");
		sb.append("   ORDER BY BALANCE DESC ");
		String sql = sb.toString();
		Query query = em.createNativeQuery(sql);	
		
		return query;
	}

}
