package com.mainiway.common.util.code;

/**
 * ***************************************************************************
 *模块名 : IdGenerator
 *文件名 : IdGenerator.java
 *创建时间 : 2017年5月22日
 *实现功能 : 
 *作者 : meng
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2017年5月22日 v0.0.1 meng 创建
 ****************************************************************************
 */
public class IdGenerator {

	final private static VarlenaIdWorker idWorker = new VarlenaIdWorker(0, 0, 8, true);

	/**
	 * ====================================================================
	 *函 数 名： @param deviceId
	 *函 数 名： @return
	 *功 能： 生成订单ID，共16位，前12位为唯一UUID，后4位为用户ID的后4位，为以后分库分表使用
	 *
	 *            订单ID结构
	 *+---------------------------------------------------------+
	 * | 唯一ID | 用户ID尾数
	 *+----------------------------------------------------------+
	 *
	 *         用户ID尾数
	 *+-----------------------+
	 * |    库信息  |  表信息      |
	 *+-----------------------+
	 *
	====================================================================
	 */
	public  long generateOrderId(long uid) {

		long UID_MASK = 10000;

		// 通过取模得到UID的后四位整数
		int uidTail = (int) (uid % UID_MASK);
		long orderId = idWorker.nextId(true) * UID_MASK + uidTail;
		return orderId;
	}

	public static long generateCommdityId() {
		return generateUUID();
	}

	public static long generateUUID() {
		return idWorker.nextId(true);
	}

	public String formatId(long id, int width) {
		return String.format("%1$0" + width + "d", id);
	}
	

}
