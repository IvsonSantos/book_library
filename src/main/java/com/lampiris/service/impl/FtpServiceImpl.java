package com.lampiris.service.impl;

import com.jcraft.jsch.*;
import com.lampiris.service.FtpService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FtpServiceImpl implements FtpService {

    @Value("${ftp.remoteHost}")
    private String remoteHost;

    @Value("${ftp.username}")
    private String username;

    @Value("${ftp.password}")
    private String password;

    @Value("${ftp.host.name}")
    private String ftpKnowHostName;

    @Override
    public void exportToFTP(String fileName) {
        ChannelSftp channelSftp = null;
        try {
            channelSftp = setupJsch();
            channelSftp.connect();
            String localFile = fileName;
            String remoteDir = "remote_sftp_test/";
            channelSftp.put(localFile, remoteDir + fileName);
            channelSftp.exit();
        } catch (JSchException | SftpException e) {
            e.printStackTrace();
        }
    }

    private ChannelSftp setupJsch() throws JSchException {
        JSch jsch = new JSch();
        jsch.setKnownHosts(ftpKnowHostName);
        Session jschSession = jsch.getSession(username, remoteHost);
        jschSession.setPassword(password);
        jschSession.connect();
        return (ChannelSftp) jschSession.openChannel("sftp");
    }
}
