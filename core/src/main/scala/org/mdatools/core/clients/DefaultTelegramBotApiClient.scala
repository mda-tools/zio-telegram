package org.mdatools.core.clients

import io.circe
import io.circe.generic.auto._
import org.mdatools.core.TelegramBotApiClient
import org.mdatools.core.model.User
import org.mdatools.core.model.response.{Response => TgResponse}
import org.mdatools.core.model.util.TelegramBotApiToken
import sttp.client3.circe.asJson
import sttp.client3.httpclient.zio.HttpClientZioBackend
import sttp.client3.{ResponseException, SttpBackend, UriContext, basicRequest}
import sttp.model.Uri
import sttp.model.Uri.{PathSegmentEncoding, Segment}
import zio.{IO, Task, ZIO, ZLayer}

case class DefaultTelegramBotApiClient(
    backend: SttpBackend[Task, Any],
    token: TelegramBotApiToken,
) extends TelegramBotApiClient[Task] {

  private val baseUri = uri"https://api.telegram.org/bot$token"

  import org.mdatools.core.clients.DefaultTelegramBotApiClient.{
    GET,
    HttpMethod,
    POST,
  }

  private def sendRequest[R : io.circe.Decoder](
    method: HttpMethod,
    uri: Uri,
  ): IO[ResponseException[String, circe.Error], R] = {
    val request =
      method match {
        case GET =>
          basicRequest.get(uri)
        case POST =>
          basicRequest.post(uri)
      }
    request
      .response(asJson[R])
      .send(backend)
      .map(_.body)
      .flatMap {
        case Left(error) =>
          ZIO.fail(error)
        case Right(value) =>
          ZIO.succeed(value)
      }
      .mapError(_.asInstanceOf[ResponseException[String, circe.Error]])
  }

  def getMe: IO[ResponseException[String, circe.Error], TgResponse[User]] = {
    val uri = baseUri
      .addPathSegment(Segment("getMe", PathSegmentEncoding.Standard))
    sendRequest[TgResponse[User]](GET, uri)
  }

}

object DefaultTelegramBotApiClient {

  def live
    : ZLayer[TelegramBotApiToken, Throwable, DefaultTelegramBotApiClient] =
    ZLayer.fromZIO(
      for {
        backend <- HttpClientZioBackend()
        token   <- ZIO.service[TelegramBotApiToken]
        client = DefaultTelegramBotApiClient(backend, token)
        _ <- client.getMe.map(println)
      } yield client,
    )

  sealed private trait HttpMethod
  private case object GET  extends HttpMethod
  private case object POST extends HttpMethod
}
