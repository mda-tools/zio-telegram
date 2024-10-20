import org.mdatools.core.clients.DefaultTelegramBotApiClient
import org.mdatools.core.model.util.TelegramBotApiToken
import zio._

object EchoBotExample extends ZIOAppDefault {

  private val TELEGRAM_BOT_API_TOKEN = "PLACE_YOUR_TOKEN_HERE"

  private val tgBotApiToken = ZLayer.succeed(TelegramBotApiToken(TELEGRAM_BOT_API_TOKEN))

  private val tgBotApiClient = DefaultTelegramBotApiClient.live

  override val bootstrap: ZLayer[ZIOAppArgs, Any, DefaultTelegramBotApiClient] = tgBotApiToken >>> tgBotApiClient

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = ZIO.succeed()
}
