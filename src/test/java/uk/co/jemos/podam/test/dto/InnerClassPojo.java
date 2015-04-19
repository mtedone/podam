package uk.co.jemos.podam.test.dto;

public class InnerClassPojo {

	private InnerPojo ip;

	public InnerPojo getIp() {
		return ip;
	}

	public InnerClassPojo(InnerPojo ip) {
		this.ip = ip;
	}

	public class InnerPojo {
		private String data;

		public InnerPojo(String data) {
			this.data = data;
		}

		public String getData() {
			return data;
		}
	}
}
