package it.smartcommunitylab.platform.idea.beans;

public class Pagination {
	private int currentPage;
	private int elementInPage;
	private String prevURL;
	private String nextURL;

	private ResultWrapper result;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getPrevURL() {
		return prevURL;
	}

	public void setPrevURL(String prevURL) {
		this.prevURL = prevURL;
	}

	public String getNextURL() {
		return nextURL;
	}

	public void setNextURL(String nextURL) {
		this.nextURL = nextURL;
	}

	public ResultWrapper getResult() {
		return result;
	}

	public void setResult(ResultWrapper result) {
		this.result = result;
	}

	public int getElementInPage() {
		return elementInPage;
	}

	public void setElementInPage(int elementInPage) {
		this.elementInPage = elementInPage;
	}

}
