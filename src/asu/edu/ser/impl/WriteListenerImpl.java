package asu.edu.ser.impl;

import java.io.IOException;
import java.util.Queue;

import javax.servlet.AsyncContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

public class WriteListenerImpl implements WriteListener{
	private ServletOutputStream output = null;
	private Queue queue = null;
	private AsyncContext context = null;
	WriteListenerImpl(ServletOutputStream sos, Queue q, AsyncContext c) {
        output = sos;
        queue = q;
        context = c;
    }
	
	@Override
	public void onError(Throwable t) {
		// TODO Auto-generated method stub
		context.complete();
        t.printStackTrace();
	}

	@Override
	public void onWritePossible() throws IOException {
		// TODO Auto-generated method stub
		while (queue.peek() != null && output.isReady()) {
            String data = (String) queue.poll();
            output.print(data);
        }
        if (queue.peek() == null) {
            context.complete();
        }
	}

}
