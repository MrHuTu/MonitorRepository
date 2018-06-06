package com.zhongda.monitor.report.exception;

public class ImplErrorException extends RuntimeException {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ImplErrorException() {}
     
    public ImplErrorException(String message) {
    	
        super(message);
        
    }
    public void f(String message) throws ImplErrorException {
    	
        throw new ImplErrorException(message);
        
    } 
 
}
 
/*public class Exception  {
    public static void main(String args[]) {
        try {
            new UserNotFoundException().f();
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}*/  