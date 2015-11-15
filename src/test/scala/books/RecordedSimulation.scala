package books

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

  val httpProtocol = http
    .baseURL("http://localhost:8080")
    .inferHtmlResources()

  val headers_0 = Map("Upgrade-Insecure-Requests" -> "1")

  val headers_1 = Map(
    //"Accept-Encoding" -> "gzip, deflate",
    "Origin" -> "http://localhost:8080",
    "Upgrade-Insecure-Requests" -> "1")

  val uri1 = "http://localhost:8080/books"

  val booksData = Array(
    Map("id" -> 0, "title" -> "hogehoge"),
    Map("id" -> 1, "title" -> "piyopiyo"))

  val scn = scenario("RecordedSimulation")
    .feed(booksData)
    .exec(http("request_0")
      .get("/books")
      .headers(headers_0))
    //.pause(5)
    .exec(http("request_1")
      .post("/books")
      .headers(headers_1)
      .formParam("id", "${id}")
      .formParam("title", "${title}"))

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}