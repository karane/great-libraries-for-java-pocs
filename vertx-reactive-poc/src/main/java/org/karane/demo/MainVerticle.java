package org.karane.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.vertx.core.Future;
import io.vertx.core.VerticleBase;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.ext.web.handler.BodyHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainVerticle extends VerticleBase {
  private final Map<Integer, Article> db = new HashMap<>();
  private final ObjectMapper mapper = new ObjectMapper();
  private int idCounter = 1;

  @Override
  public Future<?> start() {
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    Router router = Router.router(vertx);

    router.route().handler(BodyHandler.create());
    router.get("/articles").handler(this::getAllArticles);
    router.get("/articles/:id").handler(this::getArticleById);
    router.post("/articles").handler(this::createArticle);
    router.put("/articles/:id").handler(this::updateArticle);
    router.delete("/articles/:id").handler(this::deleteArticle);

    // Create the HTTP server
    return vertx.createHttpServer()
      // Handle every request using the router
      .requestHandler(router)
      // Start listening
      .listen(8888)

      // Print the port on success
      .onSuccess(server -> {
        System.out.println("HTTP server started on port " + server.actualPort());
      })
      // Print the problem on failure
      .onFailure(throwable -> {
        throwable.printStackTrace();
      });
  }

  private void getAllArticles(RoutingContext ctx) {
    List<String> jsonList = db.values().stream()
      .map(this::myToJson)
      .toList();
    ctx.response()
      .putHeader("Content-Type", "application/json")
      .end("[" + String.join(",", jsonList) + "]");
  }

  private void getArticleById(RoutingContext ctx) {
    String idParam = ctx.pathParam("id");
    Long id = Long.parseLong(idParam);
    Article article = db.get(id);
    if (article == null) {
      ctx.response().setStatusCode(404).end("Not found");
    } else {
      ctx.response()
        .putHeader("Content-Type", "application/json")
        .end(myToJson(article));
    }
  }

  private void createArticle(RoutingContext ctx) {
    try {
      Article article = mapper.readValue(ctx.body().asString(), Article.class);
      article.setId(idCounter++);
      db.put(article.getId(), article);
      ctx.response()
        .setStatusCode(201)
        .putHeader("Content-Type", "application/json")
        .end(myToJson(article));
    } catch (Exception e) {
      ctx.response().setStatusCode(400).end("Invalid JSON");
    }
  }

  private void updateArticle(RoutingContext ctx) {
    int id = Integer.parseInt(ctx.pathParam("id"));
    Article existing = db.get(id);
    if (existing == null) {
      ctx.response().setStatusCode(404).end("Not found");
      return;
    }

    try {
      Article update = mapper.readValue(ctx.body().asString(), Article.class);
      update.setId(id);
      db.put(id, update);
      ctx.response()
        .putHeader("Content-Type", "application/json")
        .end(myToJson(update));
    } catch (Exception e) {
      ctx.response().setStatusCode(400).end("Invalid JSON");
    }
  }

  private void deleteArticle(RoutingContext ctx) {
    Integer id = Integer.parseInt(ctx.pathParam("id"));
    Article removed = db.remove(id);
    if (removed == null) {
      ctx.response().setStatusCode(404).end("Not found");
    } else {
      ctx.response().setStatusCode(204).end();
    }
  }

  private String myToJson(Article article) {
    try {
      return mapper.writeValueAsString(article);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }


}
