package com.example.controldeinventario;

import java.sql.*;
public class Conexion {
    private String servidor="localhost";
    private String usuario="root";
    private String password="";
    private String bd="inventario";
    public Connection conexion;

    public Conexion(){
        try{
            conexion=DriverManager.getConnection("jdbc:mysql://"+servidor+":3306/"+bd+"?useUnicode=true&useJDBCCompilantTimeZoneShift=useLegacyDatetimeCode&serverTimeZone=UTC",usuario,password);




        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }//constructor



    //   C    O   N   S   U   L   T   A   R
    public ResultSet consultar(String consulta){
        ResultSet resultado=null;
        try{
            Statement st=conexion.createStatement();
            resultado =st.executeQuery(consulta);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return resultado;
    }
    // insertar, modificar o eliminar
    public void insmodelim(String sql){
        try{
            Statement st=conexion.createStatement();
            st.executeUpdate(sql);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
