package cn.tedu.store.sshweb.model;

import java.util.List;

public class Pager<T> {
	/**
	 * ��ҳ��С
	 */
	private int size;
	/**
	 * ��ҳ��ʼҳ
	 */
	private int offset;
	/**
	 * �ܼ�¼��
	 */
	private long total;
	/**
	 * ��ҳ����
	 */
	private List<T> rows;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "Pager [size=" + size + ", offset=" + offset + ", total=" + total + ", rows=" + rows + "]";
	}

	
}
