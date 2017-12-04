package nc.flycamel.vertxtest;

import io.vertx.core.Vertx;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@SpringBootApplication
public class VertxTestApplication {

    MyFirstVerticle myFirstVerticle;
    MyFirstRxVerticle myFirstRxVerticle;
    HelloMicroservice helloMicroservice;
    HelloConsumerMicroservice helloConsumerMicroservice;

    @Resource
    public void setMyFirstVerticle(MyFirstVerticle myFirstVerticle) {
        this.myFirstVerticle = myFirstVerticle;
    }

    @Resource
    public void setMyFirstRxVerticle(MyFirstRxVerticle myFirstRxVerticle) {
        this.myFirstRxVerticle = myFirstRxVerticle;
    }

    @Resource
    public void setHelloMicroservice(HelloMicroservice helloMicroservice) {
        this.helloMicroservice = helloMicroservice;
    }

    @Resource
    public void setHelloConsumerMicroservice(HelloConsumerMicroservice helloConsumerMicroservice) {
        this.helloConsumerMicroservice = helloConsumerMicroservice;
    }

    public static void main(String[] args) {
        SpringApplication.run(VertxTestApplication.class, args);
    }

    @PostConstruct
    public void deployVerticle() {
        Vertx vertx = Vertx.vertx();

        //vertx.deployVerticle(myFirstVerticle);
        //vertx.deployVerticle(myFirstRxVerticle);
        vertx.deployVerticle(helloMicroservice);
        vertx.deployVerticle(helloConsumerMicroservice);
    }
}
