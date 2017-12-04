package nc.flycamel.vertxtest;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.springframework.stereotype.Service;

@Service
public class HelloMicroservice extends AbstractVerticle {
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        Router router = Router.router(vertx);
        router.get("/").handler(this::hello);
        router.get("/:name").handler(this::hello);

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(8080);
    }

    private void hello(RoutingContext routingContext) {
        String hello = "hello";
        if (routingContext.pathParam("name") != null) {
            hello += " "  + routingContext.pathParam("name");
        }

        JsonObject jsonObject = new JsonObject().put("message", hello);
        routingContext.response()
                .putHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .end(jsonObject.encode());
    }
}
