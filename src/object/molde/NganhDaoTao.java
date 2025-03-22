package object.molde;

public class NganhDaoTao {
	private int maNganh;
    private String tenNganh;
	public int getMaNganh() {
		return maNganh;
	}
	public void setMaNganh(int maNganh) {
		this.maNganh = maNganh;
	}
	public String getTenNganh() {
		return tenNganh;
	}
	public void setTenNganh(String tenNganh) {
		this.tenNganh = tenNganh;
	}
	public NganhDaoTao(int maNganh, String tenNganh) {
		super();
		this.maNganh = maNganh;
		this.tenNganh = tenNganh;
	}
	@Override
	public String toString() {
        return "Mã Ngành: " + maNganh + " | Tên Ngành: " + tenNganh;
    }
    
}
