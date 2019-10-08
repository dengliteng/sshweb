package cn.tedu.store.sshweb.service;


public interface BaseService<T>{
	
		//Ôö
		public T add(T t);

		//É¾
		public void delete(int id);
		
		//¸Ä
		public void update(T t);
		
		//²é
		public T load(int id);
}
