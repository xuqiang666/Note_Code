package com.x.JDBC;

import java.sql.*;
import java.util.*;

/**
 * create by 许庆之 on 2020/3/12.
 */
public class testJdbcUtils {

    public static void main(String[] args) {

        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            cn = jdbcUtils.getConnection();
            st = cn.createStatement();
            String sql = "select * from role";
            rs = st.executeQuery(sql);
            ResultSetMetaData rsm = rs.getMetaData();
            int colnum = rsm.getColumnCount();
            Map<String,String> map = null;
            List<Map> rowList = new ArrayList<Map>();
            while(rs.next()){
                /*String id = rs.getString("id");
                String roleName = rs.getString("roleName");
                String roleDesc = rs.getString("roleDesc");*/
                map = new HashMap<String,String>();
                for(int i=1;i<=colnum;i++){
                    String colName = rsm.getColumnName(i);
                    String colValue = rs.getString(i);
                    //System.out.println(colName);
                    //System.out.println(colValue);
                    if(colValue ==null || colValue.equals("")){
                        colValue = "";
                    }else{
                        //colValue=new String(colValue.getBytes("GBK"),"ISO_8859_1");
                        /**
                         byte[] temp_t=columnValue.getBytes("ISO8859-1");
                         columnValue=new String(temp_t,"gb2312");
                         columnValue=columnValue.replaceAll("'","/");
                         */
                    }
                    map.put(colName,colValue);
                }
                rowList.add(map);
                //lists.add(rowList);
            }

            for(int i = 0 ; i < rowList.size() ; i++) {
                Map<String,String> mapList = rowList.get(i);
                Iterator<String> it = mapList.keySet().iterator();

                while(it.hasNext()){
                    String str = it.next();
                    System.out.println("key = "+str+"value = "+mapList.get(str));
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            jdbcUtils.close(rs,st,cn);
        }

    }
}
