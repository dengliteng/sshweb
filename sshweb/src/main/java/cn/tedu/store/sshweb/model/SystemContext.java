package cn.tedu.store.sshweb.model;

/**
 * ThreadLocal:���ݹ���,�����̱߳����������,ÿһ���߳��д�������,�ڶ����߳���,�����������ֵ,ÿ���߳�ʹ�ò����ͻ
 * ���з�ҳ��Ϣͨ��
 * ������Ϣͨ��
 * @author deng
 *
 */
public class SystemContext {

	//��ҳ��С,ÿҳ��ʾ����������
	private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();
	
	//ÿҳ����ʼ����������
	private static ThreadLocal<Integer> pageOffset = new ThreadLocal<Integer>();
	
	//������ֶ�
	private static ThreadLocal<String> sort = new ThreadLocal<String>();
	
	//����ʽ
	private static ThreadLocal<String> order = new ThreadLocal<String>();
	
	public static Integer getPageSize(){
		return pageSize.get();
	}
	
	public static void setPageSize(Integer _pagerSize){
		pageSize.set(_pagerSize);
	}
	
	public static Integer getPageOffset(){
		return pageOffset.get();
	}
	
	public static void setPageOffset(Integer _pageOffset){
		pageOffset.set(_pageOffset);
	}

	public static String getSort(){
		return sort.get();
	}
	
	public static void setSort(String _sort){
		sort.set(_sort);
	}
	
	public static String getOrder(){
		return order.get();
	}
	
	public static void setorder(String _order){
		order.set(_order);
	}
	
	//��Ӧɾ���ķ���
	public static void removePageSize(){
		pageSize.remove();
	}
	public static void removePageOffset(){
		pageOffset.remove();
	}
	public static void removeSort(){
		sort.remove();
	}
	public static void removeorder(){
		order.remove();
	}
	
}






