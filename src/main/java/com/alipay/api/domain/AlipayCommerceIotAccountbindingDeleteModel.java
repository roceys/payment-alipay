package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

/**
 * 用户账号绑定关系删除
 *
 * @author auto create
 * @since 1.0, 2017-09-14 22:22:19
 */
public class AlipayCommerceIotAccountbindingDeleteModel extends AlipayObject {

	private static final long serialVersionUID = 5396334497987991821L;

	/**
	 * 协议服务商id+唯一+指定操作数据归属于哪个协议服务商+协议服务商接入时由支付宝统一分配
	 */
	@ApiField("protocol_supplier_id")
	private String protocolSupplierId;

	/**
	 * 蚂蚁统一会员ID
	 */
	@ApiField("user_id")
	private String userId;

    /**
     * Gets protocol supplier id.
     *
     * @return the protocol supplier id
     */
    public String getProtocolSupplierId() {
		return this.protocolSupplierId;
	}

    /**
     * Sets protocol supplier id.
     *
     * @param protocolSupplierId the protocol supplier id
     */
    public void setProtocolSupplierId(String protocolSupplierId) {
		this.protocolSupplierId = protocolSupplierId;
	}

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserId() {
		return this.userId;
	}

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(String userId) {
		this.userId = userId;
	}

}