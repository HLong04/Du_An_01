package object_main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import object.Getter.KetNoiSQL;
import object.molde.NganhDaoTao;
import object.molde.SinhVien;

public class ChuongTrinh {
	class Repo {
	    public List<NganhDaoTao> docDanhSachNDT() {
	        List<NganhDaoTao> lstNganhDaoTao = new ArrayList<>();
	        Connection cnn = KetNoiSQL.getInstance().getCnn();
	        
	        try {
	            Statement stmt = cnn.createStatement();
	            ResultSet rs = stmt.executeQuery("SELECT MaNganh, TenNganh FROM NganhDaoTao ORDER BY TenNganh");
	            
	            while (rs.next()) {
	                lstNganhDaoTao.add(new NganhDaoTao(rs.getInt("MaNganh"), rs.getString("TenNganh")));
	            }
	            rs.close();
	            stmt.close();
	        } catch (SQLException e) {
	            System.out.println("❌ Lỗi khi lấy danh sách ngành đào tạo: " + e.getMessage());
	        }
	        return lstNganhDaoTao;
	    }

	    public List<SinhVien> docDanhSachSV() {
	        List<SinhVien> lstSV = new ArrayList<>();
	        Connection cnn = KetNoiSQL.getInstance().getCnn();
	        
	        try {
	            Statement stmt = cnn.createStatement();
	            ResultSet rs = stmt.executeQuery("SELECT MaSinhVien, HoTen FROM SinhVien");
	            
	            while (rs.next()) {
	                lstSV.add(new SinhVien(rs.getString("MaSinhVien"), rs.getString("HoTen"), rs.getInt("maNhandaotao")));
	            }
	            rs.close();
	            stmt.close();
	        } catch (SQLException e) {
	            System.out.println("❌ Lỗi khi lấy danh sách sinh viên: " + e.getMessage());
	        }
	        return lstSV;
	    }

	    public void boSungSinhVien(SinhVien sv) {
	        Connection cnn = KetNoiSQL.getInstance().getCnn();
	        
	        try {
	            PreparedStatement stmt = cnn.prepareStatement("INSERT INTO SinhVien (MaSinhVien, HoTen) VALUES (?, ?)");
	            stmt.setString(1, sv.maSinhVien);
	            stmt.setString(2, sv.hoTen);
	            
	            int rowsAffected = stmt.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("✅ Thêm sinh viên thành công!");
	            }
	            stmt.close();
	        } catch (SQLException e) {
	            System.out.println("❌ Lỗi khi thêm sinh viên: " + e.getMessage());
	        }
	    }
	}

	
	public static List<SinhVien> docDanhSachSinhVien(String filePath) {
        List<SinhVien> danhSach = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|"); 
                if (data.length == 3) {
                    String maSinhVien = data[0];
                    String hoTen = data[1];
                    int maNhandaotao = Integer.parseInt(data[2]);

                    danhSach.add(new SinhVien(maSinhVien, hoTen, maNhandaotao));
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }
        return danhSach;
    }
	
	
	public static void main(String[] args) {
		Repo repo = new Repo();
        
        List<NganhDaoTao> danhSachNDT = repo.docDanhSachNDT();
        System.out.println("\n📌 Danh sách ngành đào tạo:");
        for (NganhDaoTao ndt : danhSachNDT) {
            System.out.println(ndt);
        }

        // Đọc danh sách sinh viên
        List<SinhVien> danhSachSV = repo.docDanhSachSV();
        System.out.println("\n📌 Danh sách sinh viên:");
        for (SinhVien sv : danhSachSV) {
            System.out.println(sv);
        }

        // Thêm một sinh viên mới
        SinhVien svMoi = new SinhVien("SV011", "Trần Văn K","?");
        repo.boSungSinhVien(svMoi);
        
        // Tao sua o day
        
        // Tao sua tiep
    }
}