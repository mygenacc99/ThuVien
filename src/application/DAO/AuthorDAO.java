package application.DAO;

import application.model.Author;
import application.util.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
public class AuthorDAO {
    public static ObservableList<Author> getAuthorList() throws SQLException, IOException {
        ObservableList<Author> list = FXCollections.observableArrayList();
        ResultSet rs = null;
        try{
            String sqlNew= "SELECT * FROM TacGia";
            String sql = "select * from TacGia";
            rs = DBUtil.dbExcuteQuery(sqlNew);
            while(rs.next()){
                Author author = new Author(rs.getString("MaTacGia"),rs.getString("HoTen")
                        , rs.getString("NgaySinh"), rs.getString("QuocTich"),rs.getString("GhiChu"));
                System.out.println(author.toString());
                list.add(author);
            }

        } catch(Exception ex){
            ex.printStackTrace();
        }
        return list;
    }

    public static void newAuthor(Author author) {
        try{

            String sql = "Insert into TacGia (MaTacGia, HoTen, NgaySinh, QuocTich, GhiChu) " +
                    "values ('"+ author.getId() + "', '"+ author.getFullName() + "','"+author.getBirthday()+
                    "','"+ author.getNationality()+ "', '"+author.getNote()+"')";
            System.out.println(sql);
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
}
