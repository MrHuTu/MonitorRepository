package com.zhongda.monitor.business.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhongda.monitor.business.utils.GzipResponse;

public class GzipFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		GzipResponse mResp = null;
		if (req.getRequestURI().indexOf(".gzip") != -1) {
			mResp = new GzipResponse(resp); // 包装响应对象 resp 并缓存响应数据

			chain.doFilter(req, mResp);

			byte[] bytes = mResp.getBytes(); // 获取缓存的响应数据
			System.out.println("压缩前大小：" + bytes.length);

			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			GZIPOutputStream gzipOut = new GZIPOutputStream(bout); // 创建
																	// GZIPOutputStream
																	// 对象

			gzipOut.write(bytes); // 将响应的数据写到 Gzip 压缩流中
			gzipOut.close(); // 将数据刷新到 bout 字节流数组

			byte[] bts = bout.toByteArray();
			System.out.println("压缩后大小：" + bts.length);

			resp.setHeader("Content-Encoding", "gzip"); // 设置响应头信息
			resp.getOutputStream().write(bts); // 将压缩数据响应给客户端
		} else {
			chain.doFilter(req, resp);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}