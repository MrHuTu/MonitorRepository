package com.zhongda.monitor.business.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class GzipResponse extends HttpServletResponseWrapper {

	private ByteArrayOutputStream bytes = new ByteArrayOutputStream();
	private PrintWriter pwrite;

	public GzipResponse(HttpServletResponse response) {
		super(response);
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return new MyServletOutputStream(bytes); // 将数据写到 byte 中
	}

	/**
	 * 重写父类的 getWriter() 方法，将响应数据缓存在 PrintWriter 中
	 */
	@Override
	public PrintWriter getWriter() throws IOException {
		try {
			pwrite = new PrintWriter(new OutputStreamWriter(bytes, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return pwrite;
	}

	/**
	 * 获取缓存在 PrintWriter 中的响应数据 s
	 * 
	 * @return
	 */
	public byte[] getBytes() {
		if (null != pwrite) {
			pwrite.close();
			return bytes.toByteArray();
		}

		if (null != bytes) {
			try {
				bytes.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return bytes.toByteArray();
	}

	class MyServletOutputStream extends ServletOutputStream {
		private ByteArrayOutputStream ostream;

		public MyServletOutputStream(ByteArrayOutputStream ostream) {
			this.ostream = ostream;
		}

		@Override
		public void write(int b) throws IOException {
			ostream.write(b); // 将数据写到 stream　中
		}

		@Override
		public boolean isReady() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setWriteListener(WriteListener arg0) {
			// TODO Auto-generated method stub

		}

	}

}
