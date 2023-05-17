package com.codeartist.component.core.entity;

import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * Base64文件对象
 *
 * @author 艾江南
 * @date 2021/7/27
 */
public class Base64File implements MultipartFile {

    private final MediaType type;
    private final byte[] bytes;

    public Base64File(String content) {
        if (content == null) {
            this.type = null;
            this.bytes = new byte[0];
            return;
        }
        int split = content.indexOf(",");
        if (split < 0) {
            this.type = null;
            this.bytes = Base64.getDecoder().decode(content);
            return;
        }
        this.type = MediaType.parseMediaType(content.substring(5, split).replace(";base64", ""));
        this.bytes = Base64.getDecoder().decode(content.substring(split + 1));
    }

    @Override
    public String getName() {
        if (this.type == null) {
            return null;
        }
        return this.type.getType();
    }

    @Override
    public String getOriginalFilename() {
        if (this.type == null) {
            return null;
        }
        return "." + this.type.getSubtype();
    }

    @Override
    public String getContentType() {
        if (this.type == null) {
            return null;
        }
        return this.type.toString();
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public long getSize() {
        return this.bytes.length;
    }

    @Override
    public byte[] getBytes() {
        return this.bytes;
    }

    @Override
    public InputStream getInputStream() {
        return new ByteArrayInputStream(bytes);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        FileCopyUtils.copy(bytes, dest);
    }

    @Override
    public String toString() {
        return "Base64MultipartFile{" +
                "type='" + type + '\'' +
                '}';
    }
}
