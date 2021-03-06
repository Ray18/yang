package com.xi.xlm.common.ueditor;


import com.xi.xlm.common.ueditor.define.ActionMap;
import com.xi.xlm.common.ueditor.define.AppInfo;
import com.xi.xlm.common.ueditor.define.BaseState;
import com.xi.xlm.common.ueditor.define.State;
import com.xi.xlm.common.ueditor.hunter.FileManager;
import com.xi.xlm.common.ueditor.hunter.ImageHunter;
import com.xi.xlm.common.ueditor.upload.Uploader;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ActionEnter {
	
	private HttpServletRequest request = null;
	
	private String rootPath = "/";
	private String configRootPath = null;
	private String uploadPath = null;
    private String rootDirName = null;
	private String viewType = null;

	private String actionType = null;
	
	private ConfigManager configManager = null;

	public ActionEnter (HttpServletRequest request, String configRootPath, String uploadPath, String rootDirName, String viewType ) {
		
		this.request = request;
		this.configRootPath = configRootPath;
		this.uploadPath = uploadPath;
        this.rootDirName = rootDirName;
        this.viewType = viewType;
		this.actionType = request.getParameter( "action" );
		this.configManager = ConfigManager.getInstance( this.rootPath, this.configRootPath );
		
	}
	
	public String exec () {
		
		String callbackName = this.request.getParameter("callback");
		
		if ( callbackName != null ) {

			if ( !validCallbackName( callbackName ) ) {
				return new BaseState( false, AppInfo.ILLEGAL ).toJSONString();
			}
			
			return callbackName+"("+this.invoke()+");";
			
		} else {
			return this.invoke();
		}

	}
	
	public String invoke() {
		
		if ( actionType == null || !ActionMap.mapping.containsKey( actionType ) ) {
			return new BaseState( false, AppInfo.INVALID_ACTION ).toJSONString();
		}
		
		if ( this.configManager == null || !this.configManager.valid() ) {
			return new BaseState( false, AppInfo.CONFIG_ERROR ).toJSONString();
		}
		
		State state = null;
		
		int actionCode = ActionMap.getType( this.actionType );
		
		Map<String, Object> conf = null;
		
		switch ( actionCode ) {
		
			case ActionMap.CONFIG:
				return this.configManager.getAllConfig().toString();
				
			case ActionMap.UPLOAD_IMAGE:
			case ActionMap.UPLOAD_SCRAWL:
			case ActionMap.UPLOAD_VIDEO:
			case ActionMap.UPLOAD_FILE:
				conf = this.configManager.getConfig( actionCode );
				conf.put( "uploadPath" , this.uploadPath );
				conf.put( "rootDirName" , this.rootDirName );
				conf.put( "viewType" , this.viewType );
				state = new Uploader( request, conf ).doExec();
				break;
				
			case ActionMap.CATCH_IMAGE:
				conf = configManager.getConfig( actionCode );
				conf.put( "uploadPath" , this.uploadPath );
				conf.put( "rootDirName" , this.rootDirName );
				conf.put( "viewType" , this.viewType );
				String[] list = this.request.getParameterValues( (String)conf.get( "fieldName" ) );
				state = new ImageHunter( request, conf ).capture( list );
				break;
				
			case ActionMap.LIST_IMAGE:
			case ActionMap.LIST_FILE:
				conf = configManager.getConfig( actionCode );
				int start = this.getStartIndex();
				state = new FileManager( conf ).listFile( start );
				break;
				
		}
		
		return state.toJSONString();
		
	}
	
	public int getStartIndex () {
		
		String start = this.request.getParameter( "start" );
		
		try {
			return Integer.parseInt( start );
		} catch ( Exception e ) {
			return 0;
		}
		
	}
	
	/**
	 * callback参数验证
	 */
	public boolean validCallbackName ( String name ) {
		
		if ( name.matches( "^[a-zA-Z_]+[\\w0-9_]*$" ) ) {
			return true;
		}
		
		return false;
		
	}
	
}