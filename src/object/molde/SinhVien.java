package object.molde;

public class SinhVien {
	private String maSinhVien;
	private String hoTen;
	private int maNhandaotao;
	public String getMaSinhVien() {
		return maSinhVien;
	}
	public void setMaSinhVien(String maSinhVien) {
		this.maSinhVien = maSinhVien;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public int getMaNhandaotao() {
		return maNhandaotao;
	}
	public void setMaNhandaotao(int maNhandaotao) {
		this.maNhandaotao = maNhandaotao;
	}
	public SinhVien(String maSinhVien, String hoTen, String string) {
		super();
		this.maSinhVien = maSinhVien;
		this.hoTen = hoTen;
		this.maNhandaotao = string;
	}
	@Override
	public String toString() {
		return  maSinhVien + " | " + hoTen + " | " + maNhandaotao ;
	}
	
}
