package com.xi.xlm.common.ueditor.hunter;



import com.xi.xlm.common.ueditor.PathFormat;
import com.xi.xlm.common.ueditor.define.*;
import com.xi.xlm.common.ueditor.upload.StorageManager;
import com.xi.xlm.common.ueditor.utils.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 图片抓取器
 * @author hancong03@baidu.com
 *
 */
public class ImageHunter {

	private HttpServletRequest request = null;
	private String filename = null;
	private String savePath = null;
//	private String rootPath = null;
	private String uploadPath = null;
	private String rootDirName = null;
	private String viewType = null;
	private List<String> allowTypes = null;
	private long maxSize = -1;
	
	private List<String> filters = null;

	public ImageHunter (HttpServletRequest request, Map<String, Object> conf ) {
		this.request = request;
		this.filename = (String)conf.get( "filename" );
		this.savePath = (String)conf.get( "savePath" );
//		this.rootPath = (String)conf.get( "rootPath" );
		this.uploadPath = (String)conf.get( "uploadPath" );
		this.rootDirName = (String)conf.get("rootDirName");
		this.viewType = (String)conf.get("viewType");
		this.maxSize = (Long)conf.get( "maxSize" );
		this.allowTypes = Arrays.asList( (String[])conf.get( "allowFiles" ) );
		this.filters = Arrays.asList( (String[])conf.get( "filter" ) );
		
	}
	
	public State capture (String[] list ) {
		
		MultiState state = new MultiState( true );
		
		for ( String source : list ) {
			state.addState( captureRemoteData( source ) );
		}
		
		return state;
		
	}

	public State captureRemoteData (String urlStr ) {
		
		HttpURLConnection connection = null;
		URL url = null;
		String suffix = null;
		
		try {
			url = new URL( urlStr );

			if ( !validHost( url.getHost() ) ) {
				return new BaseState( false, AppInfo.PREVENT_HOST );
			}
			
			connection = (HttpURLConnection) url.openConnection();
		
			connection.setInstanceFollowRedirects( true );
			connection.setUseCaches( true );
		
			if ( !validContentState( connection.getResponseCode() ) ) {
				return new BaseState( false, AppInfo.CONNECTION_ERROR );
			}
			
			suffix = MIMEType.getSuffix( connection.getContentType() );
			
			if ( !validFileType( suffix ) ) {
				return new BaseState( false, AppInfo.NOT_ALLOW_FILE_TYPE );
			}
			
			if ( !validFileSize( connection.getContentLength() ) ) {
				return new BaseState( false, AppInfo.MAX_SIZE );
			}
			
			String savePath = this.getPath( this.savePath, this.filename, suffix );

			String new_file_url;
			if("/".equals(uploadPath)){//上传到项目根目录
				uploadPath = RequestUtils.getRealPath(request) + rootDirName;
				uploadPath = request.getSession().getServletContext().getRealPath("/") + rootDirName;
				new_file_url = rootDirName + savePath;
			}else{//上传到指定目录
                if("api".equals(this.viewType)) {
                    new_file_url = "/web/files/view?path=" + rootDirName + savePath;
                }else{
                    new_file_url = rootDirName + savePath;
                }
			}

			State state = StorageManager.saveFileByInputStream( connection.getInputStream(), uploadPath + savePath );
			
			if ( state.isSuccess() ) {
					state.putInfo( "url", RequestUtils.getBasePath(request) + PathFormat.format( new_file_url ) );
				state.putInfo( "source", urlStr );
			}
			
			return state;
			
		} catch ( Exception e ) {
			return new BaseState( false, AppInfo.REMOTE_FAIL );
		}
		
	}
	
	private String getPath ( String savePath, String filename, String suffix  ) {
		
		return PathFormat.parse( savePath + suffix, filename );
		
	}
	
	private boolean validHost ( String hostname ) {
		try {
			InetAddress ip = InetAddress.getByName(hostname);
			
			if (ip.isSiteLocalAddress()) {
				return false;
			}
		} catch (UnknownHostException e) {
			return false;
		}
		
		return !filters.contains( hostname );
		
	}
	
	private boolean validContentState ( int code ) {
		
		return HttpURLConnection.HTTP_OK == code;
		
	}
	
	private boolean validFileType ( String type ) {
		
		return this.allowTypes.contains( type );
		
	}
	
	private boolean validFileSize ( int size ) {
		return size < this.maxSize;
	}
	
}
