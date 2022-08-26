package kr.co.board.vo;

/*Vo란 무엇인지 구글검색*/
public class BoardVo {
	
	/*lombok은 아직 설정안함*/

	/*테이블 프라이머리 키 (유일값) */
	private String tbSeq;
	/*게시판 제목*/
	private String tbTitle;
	/*게시판 내용*/
	private String tbContent;
	/*게시판 등록일*/
	private String tbRegDt;
	/*게시판 작성자*/
	private String tbRegId;

	public String getTbSeq() {
		return tbSeq;
	}
	public void setTbSeq(String tbSeq) {
		this.tbSeq = tbSeq;
	}
	public String getTbTitle() {
		return tbTitle;
	}
	public void setTbTitle(String tbTitle) {
		this.tbTitle = tbTitle;
	}
	public String getTbContent() {
		return tbContent;
	}
	public void setTbContent(String tbContent) {
		this.tbContent = tbContent;
	}
	public String getTbRegDt() {
		return tbRegDt;
	}
	public void setTbRegDt(String tbRegDt) {
		this.tbRegDt = tbRegDt;
	}
	public String getTbRegId() {
		return tbRegId;
	}
	public void setTbRegId(String tbRegId) {
		this.tbRegId = tbRegId;
	}
	@Override
	public String toString() {
		return "BoardVo [tbSeq=" + tbSeq + ", tbTitle=" + tbTitle + ", tbContent=" + tbContent + ", tbRegDt=" + tbRegDt
				+ ", tbRegId=" + tbRegId + "]";
	}
	
}
