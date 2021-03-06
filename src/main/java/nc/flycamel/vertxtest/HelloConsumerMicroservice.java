package nc.flycamel.vertxtest;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;
import org.springframework.stereotype.Service;

@Service
public class HelloConsumerMicroservice extends AbstractVerticle {
    private WebClient client;

    @Override
    public void start() throws Exception {
        client = WebClient.create(vertx);

        Router router = Router.router(vertx);
        router.get("/").handler(this::invokeMyFirstMicroservice);

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(8081);
    }

    private void invokeMyFirstMicroservice(RoutingContext routingContext) {
        HttpRequest<JsonObject> request = client.get(8080, "localhost", "/vert.x").as(BodyCodec.jsonObject());

        request.send(ar -> {
            if (ar.failed()) {
                routingContext.fail(ar.cause());
            } else {
                routingContext.response().end(ar.result().body().encode());
            }
        });
    }
}
