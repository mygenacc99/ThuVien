package application.DAO;

import application.model.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.*;

public class CategoryDAO{

    public static boolean searchByID(String ID){
        String sql = "select * from TheLoai where MaTheLoai ="+ID;
        System.out.println(sql);
        ResultSet rs = null;
        boolean boolrs = true;
        try{
           rs = DBUtil.dbExcuteQuery(sql);
           if(!rs.next()){
               boolrs = false;
           }
           else{
               boolrs = true;
           }
        } catch(Exception ex){
            System.out.println("Exception occured while search the ID of category.");
            ex.printStackTrace();
        }
        return  boolrs;
    }


    public static void newCategory(Category category) {
        try{

            String sql = "Insert into TheLoai (MaTheLoai, Ten, GhiChu) " +
                    "values ("+ category.getID() + ", '"+ category.getName()+"','"+category.getNote()+"')";
            int Row = DBUtil.dbExcuteUpdate(sql);
            if(Row>0){
                System.out.println("Tao moi the loai thanh cong");
            }
            else {
                System.out.println("Tao moi the loai that bai");
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static  ObservableList<Category> getCategoryList() throws SQLException, IOException{
        ObservableList<Category> list = FXCollections.observableArrayList();
        ResultSet rs = null;
        try{

            String sql = "select * from TheLoai";
            rs = DBUtil.dbExcuteQuery(sql);
            while(rs.next()){
                Category category = new Category(rs.getInt("MaTheLoai"),rs.getString("Ten")
                , rs.getString("GhiChu"));                list.add(category);
            }

        } catch(Exception ex){
            ex.printStackTrace();
        }
        return list;
    }


    public static void editCategory(Category catgrOld, Category catgrNew) {
        try{
            String sql = "update TheLoai set MaTheLoai = "+catgrNew.getID()+
                    ", Ten = '"+ catgrNew.getName() +
                    "', GhiChu = '"+ catgrNew.getNote()+
                    "' where MaTheLoai = "+ catgrOld.getID();
            int Row = DBUtil.dbExcuteUpdate(sql);
            if(Row>0){
                System.out.println("Da sua thanh cong");
            }
            else {
                System.out.println("Sua that bai");
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void delCategory(Category category) {
        try{

            String sql = "delete from TheLoai where MaTheLoai = " + category.getID();
            int Row = DBUtil.dbExcuteUpdate(sql);
            if(Row>0){
                System.out.println("Da xoa thanh cong");
            }
            else {
                System.out.println("Xoa that bai");
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
