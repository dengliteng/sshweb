package cn.tedu.store.sshweb.service;


public interface BaseService<T>{
	
		//��
		public T add(T t);

		//ɾ
		public void delete(int id);
		
		//��
		public void update(T t);
		
		//��
		public T load(int id);
}
