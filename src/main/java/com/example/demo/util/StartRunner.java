package com.example.demo.util;

import com.example.demo.socket.DiscardServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by huang on 2018/4/11.
 */
@Component
public class StartRunner implements CommandLineRunner {
//    @Autowired
//    private SocketServer socketServerl;
    @Autowired
    private DiscardServer discardServer;

    @Override
    public void run(String... args) throws Exception {
//        socketServerl.startSocketServer(9999);
//        discardServer.run();
    }
}
