package nc.flycamel.vertxtest;

import io.vertx.core.AbstractVerticle;
import org.springframework.stereotype.Service;

@Service
public class MyFirstVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        vertx.createHttpServer()
                .requestHandler(req -> {
                    req.response().end("Hi~ from  " + Thread.currentThread().getName());
                })
                .listen(8080);
    }
}
