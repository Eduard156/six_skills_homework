package perf.load.cases

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import perf.load.models.UserData
import perf.load.utils.{DataGenerator, GsonUtils}

object HttpReqres {

  val getSingleUser: HttpRequestBuilder = http("GET /api/users/#{userId}") // id подставится из Session
    .get("/api/users/#{userId}")
    .check(status is 404)

  val postCreate: HttpRequestBuilder = http("POST Create new user")
    .post("/api/users")
    .body(
      StringBody(session =>
        GsonUtils.toJson(
          UserData(
            name = session("name").as[String],
            email = session("email").as[String],
          ),
        ),
      ),
    )
    .asJson
    .check(
      status.in(200, 201),
      jsonPath("$.id").saveAs("userId"), // кладём id в Session
    )

  val saveRandomUserData: ChainBuilder = exec { session =>
    val randomGenerate = DataGenerator.randomUser()
    val newData        = session
      .set("name", randomGenerate("name"))
      .set("email", randomGenerate("email"))
    newData
  }

}
