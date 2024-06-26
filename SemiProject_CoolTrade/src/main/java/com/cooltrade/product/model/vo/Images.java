package com.cooltrade.product.model.vo;

public class Images {
	
	private int imgNo;
	private int refPno;
	private int refUno;
	private int refRno;
	private int imgLevel;
	private String originName;
	private String changeName;
	private String imgPath;
	private String uploadDate;
	private String imgStatus;
	private String buyerNickname;
	private String sellerNickname;
	private String buyerTitleImg;
	private String sellerTitleImg;

	public Images() {}

	public Images(int imgNo, int refPno, int refUno, int refRno, int imgLevel, String originName, String changeName, String imgPath,
			String uploadDate, String imgStatus, String buyerNickname, String sellerNickname, String buyerTitleImg, String sellerTitleImg) {
		super();
		this.imgNo = imgNo;
		this.refPno = refPno;
		this.refUno = refUno;
		this.refRno = refRno;
		this.imgLevel = imgLevel;
		this.originName = originName;
		this.changeName = changeName;
		this.imgPath = imgPath;
		this.uploadDate = uploadDate;
		this.imgStatus = imgStatus;
		this.buyerNickname = buyerNickname;
		this.sellerNickname = sellerNickname;
		this.buyerTitleImg = buyerTitleImg;
		this.sellerTitleImg = sellerTitleImg;
	}

	public int getImgNo() {
		return imgNo;
	}

	public void setImgNo(int imgNo) {
		this.imgNo = imgNo;
	}

	public int getRefPno() {
		return refPno;
	}

	public void setRefPno(int refPno) {
		this.refPno = refPno;
	}

	public int getRefUno() {
		return refUno;
	}

	public void setRefUno(int refUno) {
		this.refUno = refUno;
	}

	public int getRefRno() {
		return refRno;
	}

	public void setRefRno(int refRno) {
		this.refRno = refRno;
	}

	public int getImgLevel() {
		return imgLevel;
	}

	public void setImgLevel(int imgLevel) {
		this.imgLevel = imgLevel;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getImgStatus() {
		return imgStatus;
	}

	public void setImgStatus(String imgStatus) {
		this.imgStatus = imgStatus;
	}

	public String getBuyerNickname() {
		return buyerNickname;
	}

	public void setBuyerNickname(String buyerNickname) {
		this.buyerNickname = buyerNickname;
	}

	public String getSellerNickname() {
		return sellerNickname;
	}

	public void setSellerNickname(String sellerNickname) {
		this.sellerNickname = sellerNickname;
	}

	public String getBuyerTitleImg() {
		return buyerTitleImg;
	}

	public void setBuyerTitleImg(String buyerTitleImg) {
		this.buyerTitleImg = buyerTitleImg;
	}

	public String getSellerTitleImg() {
		return sellerTitleImg;
	}

	public void setSellerTitleImg(String sellerTitleImg) {
		this.sellerTitleImg = sellerTitleImg;
	}

	@Override
	public String toString() {
		return "Images [imgNo=" + imgNo + ", refPno=" + refPno + ", refUno=" + refUno + ", imgLevel=" + imgLevel
				+ ", originName=" + originName + ", changeName=" + changeName + ", imgPath=" + imgPath + ", uploadDate="
				+ uploadDate + ", imgStatus=" + imgStatus + "]";
	}
	
	
}
