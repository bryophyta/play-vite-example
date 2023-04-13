package controllers

import play.api._
import play.api.mvc._
import play.api.libs.ws.WSClient
import java.net.URL
import play.api.libs.ws.InMemoryBody
import scala.concurrent.Future
import scala.concurrent.ExecutionContext
import play.api.http.HttpEntity

class ViteController(
    val controllerComponents: ControllerComponents,
    wsClient: WSClient,
    assets: Assets,
    mode: Mode
)(implicit executionContext: ExecutionContext)
    extends BaseController {

  private def selectHeader(
      allReturnedHeaders: Map[String, scala.collection.Seq[String]],
      selectableHeader: Seq[String]
  ) = {
    selectableHeader.flatMap { key =>
      for {
        headerValues <- allReturnedHeaders.get(key)
        firstHeaderValue <- headerValues.headOption
      } yield (key, firstHeaderValue)
    }
  }

  private val headersToKeep =
    Seq(CACHE_CONTROL, ETAG, DATE, ACCESS_CONTROL_ALLOW_ORIGIN)

  def index: Action[AnyContent] = asset("index.html")

  def asset(resource: String): Action[AnyContent] = {
    if (mode == Mode.Dev) {
      Action.async { request =>
        val query =
          if (request.rawQueryString.length() > 0) "?" + request.rawQueryString
          else ""
        val location =
          new URL("http", "localhost", 5173, "/assets/" + resource + query)
            .toString()
        wsClient
          .url(location)
          .withMethod(request.method)
          .withFollowRedirects(false)
          .execute()
          .map(res => {
            val headers = selectHeader(res.headers, headersToKeep)
            val contentType =
              res.headers.get(CONTENT_TYPE).flatMap(_.headOption)
            Ok(res.body)
              .as(contentType.getOrElse(TEXT))
              .withHeaders(headers: _*)
          })
      }
    } else {
      assets.at(resource)
    }
  }

}
