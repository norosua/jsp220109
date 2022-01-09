package addr;

public class Addr {
	
	private int idx;
	private String name2;
	private String address;
	private String phone;
	
	public Addr(int idx, String name2, String address, String phone) {
		super();
		this.idx = idx;
		this.name2= name2;
		this.address = address;
		this.phone = phone;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name2;
	}

	public void setName(String name2) {
		this.name2 = name2;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
}
