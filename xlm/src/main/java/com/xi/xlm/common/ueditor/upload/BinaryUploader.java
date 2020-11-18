package com.xi.xlm.common.ueditor.upload;

import com.xi.xlm.common.ueditor.PathFormat;
import com.xi.xlm.common.ueditor.define.AppInfo;
import com.xi.xlm.common.ueditor.define.BaseState;
import com.xi.xlm.common.ueditor.define.FileType;
import com.xi.xlm.common.ueditor.define.State;
import com.xi.xlm.common.ueditor.utils.RequestUtils;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BinaryUploader {

	public static final State save(HttpServletRequest request,
                                   Map<String, Object> conf) {
		FileItemStream fileStream = null;
		boolean isAjaxUpload = request.getHeader( "X_Requested_With" ) != null;

		if (!ServletFileUpload.isMultipartContent(request)) {
			return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
		}

		ServletFileUpload upload = new ServletFileUpload(
				new DiskFileItemFactory());

        if ( isAjaxUpload ) {
            upload.setHeaderEncoding( "UTF-8" );
        }

		try {
			FileItemIterator iterator = upload.getItemIterator(request);

			while (iterator.hasNext()) {
				fileStream = iterator.next();

				if (!fileStream.isFormField()) {
					break;
				}
				fileStream = null;
			}

			if (fileStream == null) {
				return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
			}

			String savePath = (String) conf.get("savePath");
			String originFileName = fileStream.getName();
			String suffix = FileType.getSuffixByFilename(originFileName);

			originFileName = originFileName.substring(0,
					originFileName.length() - suffix.length());
			savePath = savePath + suffix;

			long maxSize = ((Long) conf.get("maxSize")).longValue();

			if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}

			savePath = PathFormat.parse(savePath, originFileName);

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
			InputStream is = fileStream.openStream();
			State storageState = StorageManager.saveFileByInputStream(is, uploadPath + savePath, maxSize);
			is.close();

			if (storageState.isSuccess()) {
				storageState.putInfo("url", RequestUtils.getBasePath(request) + PathFormat.format(new_file_url));
				storageState.putInfo("type", suffix);
				storageState.putInfo("original", originFileName + suffix);
			}

			return storageState;
		} catch (FileUploadException e) {
			return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
		} catch (IOException e) {
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}

	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);

		return list.contains(type);
	}
}
