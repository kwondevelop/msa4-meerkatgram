package com.msa4meerkatgram.domain.file.services;

import com.msa4meerkatgram.domain.file.responses.FileResponse;
import com.msa4meerkatgram.global.util.file.FileConfig;
import com.msa4meerkatgram.global.util.file.LocalFileManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
@RequiredArgsConstructor
public class FileService {
    private final LocalFileManager localFileManager;
    private final FileConfig fileConfig;

    public FileResponse storeProfile(MultipartFile file) {
        // 파일 경로 생성
        String path = localFileManager.generateProfilePath(file);
        
        // 파일 저장
        localFileManager.saveFile(file, path);
        
        return FileResponse.builder()
            .fileUri(fileConfig.serverUri() + path)
            .build();
    }

    public FileResponse storePosts(MultipartFile file) {
        // 파일 경로 생성
        String path = localFileManager.generatePostPath(file);

        // 파일 저장
        localFileManager.saveFile(file, path);

        return FileResponse.builder()
            .fileUri(fileConfig.serverUri() + path)
            .build();
    }
}
