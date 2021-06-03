package com.tweetapp.model;

/**
 * @author Seelam Jagadeeswara Reddy
 *
 */
public class ForgotPasswordRequest {

	private String loginId;

	private String newPassword;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
