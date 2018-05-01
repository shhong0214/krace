package kr.co.krace;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		applicationContext = context;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	public static Object getBean(String beanName) throws BeansException {
		return applicationContext.getBean(beanName);
	}

	public static Object getBean(Class type) throws BeansException {
		return BeanFactoryUtils.beanOfType(applicationContext, type);
		
	}	


}
