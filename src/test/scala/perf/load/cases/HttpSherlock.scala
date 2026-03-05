package perf.load.cases

import io.gatling.core.Predef._
import io.gatling.core.feeder.BatchableFeederBuilder
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import org.slf4j.{Logger, LoggerFactory}
import perf.load.models.AuthRequest
import perf.load.utils.GsonUtils

object HttpSherlock {

  val credFeeder: BatchableFeederBuilder[String] = csv("credit.csv").queue

  val logger: Logger = LoggerFactory.getLogger("AuthLogger")

  val postToken: HttpRequestBuilder = http("POST /api/token")
    .post("/api/token/")
    .body(
      StringBody(session =>
        GsonUtils.toJson(
          AuthRequest(
            username = session("username").as[String],
            password = session("password").as[String],
          ),
        ),
      ),
    )
    .check(status.saveAs("responseStatus"))

  val getProfile: HttpRequestBuilder = http("GET /profile/#{userId}")
    .get("/profile/#{userId}/")
    .header("Authorization", s"Bearer #{access}")
    .check(
      status is 200,
      jsonPath("$.flag").isNull,
      jsonPath("$.is_privileged").is("false"),
    )

}
