package com.zmax.common.utils.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class LinuxRun {
	private final static int OUTPUT_BUFFER_SIZE = 1024;

	/**
	 * 执行linux命令，默认是在/root目录下
	 * @param cmd
	 * @return
	 * @throws Exception
	 */
	public static String exec (String cmd) throws Exception  {
		StringBuffer    aOutputBuffer = new StringBuffer();
		if( StringUtils.isBlank(cmd) ) {
			throw new Exception( "Need an argument to execute" );
		}
		try {
			Process exec = Runtime.getRuntime().exec( cmd );

			// stdout from the process comes in here
			InputStream execOut = exec.getInputStream();
			InputStreamReader execReader = new InputStreamReader( execOut );


			char[] buffer = new char[ OUTPUT_BUFFER_SIZE ];
			int bytes_read = execReader.read( buffer );

			while( bytes_read > 0 ) {
				aOutputBuffer.append( buffer, 0, bytes_read );
				bytes_read = execReader.read( buffer );
			}
		}
		catch( IOException ioe ) {
			throw new Exception( ioe.getMessage() );
		}
		return aOutputBuffer.toString();
	}
	/*
	public static void main(String[] args) {
		try {
			String ret=LinuxRun.exec("cmd.exe /C dir");
			System.out.println("ret=" + ret);
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error(e);
		}
	}*/
}
