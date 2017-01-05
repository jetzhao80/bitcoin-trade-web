package com.dianping.ssp.recommend.dashboard.dto;

import java.io.Serializable;

import com.dianping.ssp.recommend.dashboard.constant.FrontGroundText;
import com.dianping.ssp.recommend.dashboard.constant.ResultCode;
import com.google.common.base.MoreObjects;

/**
 *
 * @author Mr.Bian
 *
 */
public class ResultDto<T extends Serializable> implements Serializable{
	
	private static final long serialVersionUID = -7549843697521764838L;
	
	private int code=0;
	private String errMsg="";
	private T data;
	
	public ResultDto(){}
	
	public ResultDto(int code,T data){
		this.code=code;
		this.data=data;
	}
	
	public ResultDto(int code,String errMsg){
		this.code=code;
		this.errMsg=errMsg;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("code", code)
				.add("errMsg", errMsg).add("data", data).toString();
	}
	
	public static ResultDto onSuccess(){
		return new ResultDto(ResultCode.SUCCESS,"");
	}
	
	public static ResultDto onArguErr(){
		return new ResultDto(ResultCode.ARGUMENT_ERROR, FrontGroundText.ARGUMENT_ERROR);
	}
	
	public static ResultDto onServerErr(){
		return new ResultDto(ResultCode.SERVER_ERROR, FrontGroundText.SERVER_ERROR);
	}
}
