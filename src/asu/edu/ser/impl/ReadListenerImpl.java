package asu.edu.ser.impl;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;

public class ReadListenerImpl implements ReadListener{
	private ServletInputStream input = null; 
	private HttpServletResponse res = null; 
	private AsyncContext ac = null; 
	private Queue queue = new LinkedBlockingQueue();
	public ReadListenerImpl(ServletInputStream in, HttpServletResponse r, AsyncContext c) {
        input = in;
        res = r;
        ac = c;
    }
	@Override
	public void onAllDataRead() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Data is all read");

        // now all data are read, set up a WriteListener to write
        ServletOutputStream output = res.getOutputStream();
        WriteListener writeListener = new WriteListenerImpl(output, queue, ac);
        output.setWriteListener(writeListener);
		
	}

	@Override
	public void onDataAvailable() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Data is available");

        StringBuilder sb = new StringBuilder();
        int len = -1;
        byte b[] = new byte[1024];
        while (input.isReady() && (len = input.read(b)) != -1) {
            String data = new String(b, 0, len);
            sb.append(data);
        }
        queue.add(sb.toString());
		
	}

	@Override
	public void onError(Throwable t) {
		// TODO Auto-generated method stub
		ac.complete();
        t.printStackTrace();
	}

}
