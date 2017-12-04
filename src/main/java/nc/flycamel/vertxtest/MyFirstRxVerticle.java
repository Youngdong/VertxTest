package nc.flycamel.vertxtest;

import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServer;
import org.springframework.stereotype.Service;

@Service
public class MyFirstRxVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();

        server.requestStream().toObservable()
                .subscribe(req -> req.response().end("Hi rx~ from " + Thread.currentThread().getName()));

        server.rxListen(8080).subscribe();
    }
}
