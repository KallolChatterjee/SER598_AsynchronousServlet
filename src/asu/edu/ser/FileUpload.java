package asu.edu.ser;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import asu.edu.ser.impl.ReadListenerImpl;

/**
 * Servlet implementation class FileUpload
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/uploadFile" })
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 AsyncContext context = request.startAsync();
	        // set up async listener
	        context.addListener(new AsyncListener() {
	            public void onComplete(AsyncEvent event) throws IOException {
	                event.getSuppliedResponse().getOutputStream().print("Complete");

	            }

	            public void onError(AsyncEvent event) {
	                System.out.println(event.getThrowable());
	            }

	            public void onStartAsync(AsyncEvent event) throws IOException{
	            	event.getSuppliedResponse().getOutputStream().print("Asynchronous Read Started");
	            }

	            public void onTimeout(AsyncEvent event) {
	                System.out.println("my asyncListener.onTimeout");
	            }
	        });
	        ServletInputStream input = request.getInputStream();
	        ReadListener readListener = new ReadListenerImpl(input, response, context);
	        input.setReadListener(readListener);
	    }
	

}
