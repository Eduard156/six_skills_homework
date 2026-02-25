package perf.load.cases

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object HttpReqres {

  val getSingleUser = http("GET /api/users/#{userId}") // id подставится из Session
    .get("/api/users/#{userId}")
    .check(status is 404)

  val postCreate = http("POST /api/users")
    .post("/api/users")
    .body(StringBody("""{ "name": "John", "job": "QA" }"""))
    .asJson
    .check(status is 201,
      jsonPath("$.id").saveAs("userId") // кладём id в Session
    )

}