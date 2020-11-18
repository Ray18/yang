package com.xi.xlm.common.ueditor.upload;

import com.xi.xlm.common.ueditor.PathFormat;
import com.xi.xlm.common.ueditor.define.AppInfo;
import com.xi.xlm.common.ueditor.define.BaseState;
import com.xi.xlm.common.ueditor.define.FileType;
import com.xi.xlm.common.ueditor.define.State;
import com.xi.xlm.common.ueditor.utils.RequestUtils;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public final class Base64Uploader {

	public static State save(HttpServletRequest request, Map<String, Object> conf) {
		String filedName = (String) conf.get("fieldName");
		String content = request.getParameter(filedName);
		byte[] data = decode(content);

		long maxSize = ((Long) conf.get("maxSize")).longValue();

		if (!validSize(data, maxSize)) {
			return new BaseState(false, AppInfo.MAX_SIZE);
		}

		String suffix = FileType.getSuffix("JPG");

		String savePath = PathFormat.parse((String) conf.get("savePath"),
				(String) conf.get("filename"));
		
		savePath = savePath + suffix;

		String uploadPath = (String) conf.get("uploadPath");
		String rootDirName = (String) conf.get("rootDirName");
		String viewType = (String) conf.get("viewType");
		String new_file_url;
		if("/".equals(uploadPath)){//上传到项目根目录
			uploadPath = RequestUtils.getRealPath(request) + rootDirName;
			new_file_url = rootDirName + savePath;
		}else{//上传到指定目录
			if("api".equals(viewType)) {
				new_file_url = "/web/files/view?path=" + rootDirName + savePath;
			}else{
				new_file_url = rootDirName + savePath;
			}
		}

		State storageState = StorageManager.saveBinaryFile(data, uploadPath + savePath);

		if (storageState.isSuccess()) {
			storageState.putInfo("url", RequestUtils.getBasePath(request) + PathFormat.format(new_file_url));
			storageState.putInfo("type", suffix);
			storageState.putInfo("original", "");
		}

		return storageState;
	}

	private static byte[] decode(String content) {
		return Base64.decodeBase64(content);
	}

	private static boolean validSize(byte[] data, long length) {
		return data.length <= length;
	}
	
}