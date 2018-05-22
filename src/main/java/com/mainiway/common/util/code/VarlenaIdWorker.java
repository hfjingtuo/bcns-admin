package com.mainiway.common.util.code;

/**
 * ***************************************************************************
 *模块名 : VarlenaIdWorker
 *文件名 : VarlenaIdWorker.java
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
public class VarlenaIdWorker {

	// 用于重设时间基准，为0时以1970为基准
	private final long twepoch = 0; // 1288834974657L; //2010-11-04 09:42:54

	// 设备ID所占位数
	private long workerIdBits = 2L;

	// 序列数所占位数
	private long sequenceBits = 16L;

	// 服务器设备ID，可以用数据中心+机器号构成
	private long MAX_WORKER_ID;

	// 最大序列数，占16位时最大数为65535，占8位时最大数为255
	private int SEQUENCE_MASK;

	private long workerId;
	private boolean bShortTime = false;
	private long lastTimestamp = -1L;

	private long sequence = 0L;

	public VarlenaIdWorker(int deviceId) {

		init(deviceId);
	}

	public VarlenaIdWorker(int workerId_bits, int workerId, int sequence_bits, boolean shortTime) {

		this.workerIdBits = workerId_bits;
		this.sequenceBits = sequence_bits;
		this.bShortTime = shortTime;

		init(workerId);
	}

	public long nextId(boolean bMutliThread) {
		long newId = 0;

		if (bMutliThread) {
			synchronized (VarlenaIdWorker.class) {
				newId = generateId();
			}
		} else {
			newId = generateId();
		}

		return newId;
	}

	
	private long generateId() {

		long timestamp = timeGen();

		// 如果上次计算ID的时间戳大于当前时间戳，抛出异常
		if (timestamp < lastTimestamp) {
			throw new IllegalArgumentException(String.format(
					"Clock moved backwards.  Refusing to generate id for %d milliseconds",
					lastTimestamp - timestamp));
		}

		// 如果上次计算ID的时间戳与当前相同，说明是在同一时间产生ID，增加原子递增数为序列数
		if (lastTimestamp == timestamp) {
			sequence = (sequence + 1) & SEQUENCE_MASK;

			if (sequence == 0) {
				// 如果序列数达到最大值，延迟到下一个不同的时间戳再计算ID
				timestamp = tilNextMillis(lastTimestamp);
			}
		} else {
			// 序列数重置为0，但会出现大量碰撞
			// sequence = 0;
		}

		lastTimestamp = timestamp;
		return (timestamp - twepoch << (sequenceBits + workerIdBits)) | (workerId << sequenceBits)
						| sequence;

	}

	private long tilNextMillis(final long lastTimestamp) {

		long timestamp = this.timeGen();

		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		// System.out.println("waitNextTime..");
		return timestamp;
	}

	private long timeGen() {
		return (bShortTime ? (long) (System.currentTimeMillis() / 1000) : System
				.currentTimeMillis());
	}

	private void init(int workerId) {

		// 计算最大WORKER_ID和最大序列数
		MAX_WORKER_ID = -1L ^ -1L << workerIdBits;
		SEQUENCE_MASK = (int) (-1L ^ -1L << sequenceBits);

		if (workerId > MAX_WORKER_ID || workerId < 0) {
			throw new IllegalArgumentException(String.format(
					"worker Id can't be greater than %d or less than %d", this.MAX_WORKER_ID));
		}

		this.workerId = workerId;
	}

	
}
